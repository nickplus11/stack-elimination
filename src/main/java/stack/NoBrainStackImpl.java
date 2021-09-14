package stack;

import kotlinx.atomicfu.AtomicRef;

public class NoBrainStackImpl implements Stack {
    private static class Node {
        final AtomicRef<Node> next;
        final int x;

        Node(int x, Node next) {
            this.next = new AtomicRef<>(next);
            this.x = x;
        }
    }

    // head pointer
    private AtomicRef<Node> head = new AtomicRef<>(null);

    @Override
    public void push(int x) {
        while (true) {
            Node curHead = head.getValue();
            Node newNode = new Node(x, curHead);
            if (head.compareAndSet(curHead, newNode)) return;
        }
    }

    @Override
    public int pop() {
        while (true) {
            Node curHead = head.getValue();

            if (curHead == null) return Integer.MIN_VALUE;

            if (head.compareAndSet(curHead, curHead.next.getValue())) {
                return curHead.x;
            }
        }
    }
}

