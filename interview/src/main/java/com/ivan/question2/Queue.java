package com.ivan.question2;

import java.util.Iterator;

/**
 * @author: WB
 * @version: v1.0
 */
public class Queue<T> implements Iterable<T> {
    private Node<T> head;
    private Node<T> last;
    private int size;

    public Queue() {
        this.head = null;
        this.last = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void push(T item) {
        Node<T> newNode = new Node<>(item);
        if (isEmpty()) {
            this.head = new Node<>();
            this.head.next = newNode;
            this.last = newNode;
        } else {
            this.last.next = newNode;
            this.last = newNode;
        }
        size++;
    }

    public T pop() {
        if (isEmpty()) {
            return null;
        }
        Node<T> node = this.head.next;
        this.head.next = node.next;
        size--;
        if (size == 0) {
            this.head = null;
            this.last = null;
        }
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
