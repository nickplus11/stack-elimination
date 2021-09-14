package stack;

import kotlinx.atomicfu.AtomicRef;

import java.util.ArrayList;

public class StackImpl implements Stack {
    private static class Node {
        final AtomicRef<Node> next;
        final int x;

        Node(int x, Node next) {
            this.next = new AtomicRef<>(next);
            this.x = x;
        }
    }

    public static final int eliminationArraySize = 512;
    public static final int eliminationArrayCheckTimes = 64;
    // head pointer
    private AtomicRef<Node> head = new AtomicRef<>(null);

    private ArrayList<AtomicRef<Integer>> eliminationArray = new ArrayList<>(eliminationArraySize);

    @Override
    public void push(int x) {
        while (true) {
            int tryAddIndex = tryAddNewNode(x);

            if (tryAddIndex >= 0) {
                for (int i = 0; i < eliminationArrayCheckTimes; ++i) {
                    if (eliminationArray.get(tryAddIndex) == null) return;
                }
            }

            Node curHead = head.getValue();
            Node newNode = new Node(x, curHead);
            if (head.compareAndSet(curHead, newNode)) return;
        }
    }

    @Override
    public int pop() {
        while (true) {
            Integer anyNotNullNode = tryRemoveAnyNotNullNode();
            if (anyNotNullNode != null) return anyNotNullNode;

            Node curHead = head.getValue();
            if (curHead == null) return Integer.MIN_VALUE;

            if (head.compareAndSet(curHead, curHead.next.getValue())) {
                return curHead.x;
            }
        }
    }

    private int tryAddNewNode(int x) {
        for (int i = 0; i < eliminationArray.size(); ++i) {
            Integer curElem = eliminationArray.get(i).getValue();
            if (curElem != null) {
                eliminationArray.get(i).compareAndSet(curElem, x);
                return i;
            }
        }
        return -1;
    }

    private Integer tryRemoveAnyNotNullNode() {
        for (int i = 0; i < eliminationArray.size(); ++i) {
            Integer curElem = eliminationArray.get(i).getValue();
            if (curElem != null) {
                eliminationArray.get(i).compareAndSet(curElem, null);
                return curElem;
            }
        }
        return null;
    }
}
