package com.ivan.question2;

import java.util.Iterator;

/**
 * @author: WB
 * @version: v1.0
 */
public class SortQueue<T extends Comparable<T>> implements Iterable<T> {
    private Node<T> head;
    private int size;

    public SortQueue() {
        this.head = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean push(T item) {
        int curSize = size;
        if (this.head == null) {
            this.head = new Node<>();
            this.head.next = new Node<>(item);
            size++;
        } else {
            push(this.head, item);
        }
        return curSize != size;
    }

    private void push(Node<T> parent, T item) {
        Node<T> next = parent.next;
        if (next != null) {
            int cmp = item.compareTo(next.item);
            if (cmp < 0) {
                Node<T> newNode = new Node<>(item);
                parent.next = newNode;
                newNode.next = next;
                size++;
            } else if (cmp > 0) {
                Node<T> childNext = next.next;
                if (childNext != null) {
                    push(next, item);
                } else {
                    next.next = new Node<>(item);
                    size++;
                }
            }
        } else {
            parent.next = new Node<>(item);
            size++;
        }
    }

    public T pop() {
        if (isEmpty()) {
            return null;
        }
        Node<T> node = this.head.next;
        this.head.next = node.next;
        size--;
        return node.item;
    }

    public boolean remove(T item) {
        if (isEmpty()) {
            return false;
        }
        return remove(this.head, item);
    }

    private boolean remove(Node<T> parent, T item) {
        Node<T> next = parent.next;
        if (next.item.equals(item)) {
            parent.next = next.next;
            size--;
            return true;
        } else {
            Node<T> childNext = next.next;
            if (childNext != null) {
                return remove(next, item);
            } else {
                return false;
            }
        }
    }

    private static class Node<T> {
        private T item;
        private Node<T> next;

        public Node() {
        }

        public Node(T item) {
            this.item = item;
        }

        public T getItem() {
            return item;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        public Node<T> getNext() {
            return next;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Itr<>(head);
    }

    private static class Itr<T> implements Iterator<T> {
        private Node<T> node;

        public Itr(Node<T> node) {
            this.node = node;
        }

        @Override
        public boolean hasNext() {
            if (node == null) {
                return false;
            }
            node = node.next;
            return node != null;
        }

        @Override
        public T next() {
            return node.item;
        }
    }
}
