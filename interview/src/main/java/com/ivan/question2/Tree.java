package com.ivan.question2;

import java.util.Iterator;

/**
 * 由图可见每层都是从小到大，从头结点向下也是依次变大
 * <p>
 * 使用层序遍历：
 * -- 先对每一层进行进行解析存入队列
 * -- 然后从队列中删除结点
 * -- 然后将队列的节点重新存入树中
 *
 * @author: WB
 * @version: v1.0
 */
public class Tree {
    private TreeNode head; //头结点
    private SortQueue<Integer> sortQueue;
    private int size;

    public boolean isEmpty() {
        return size == 0;
    }

    public void push(int item) {
        if (size == 0) {
            sortQueue = new SortQueue<>();
        }
        boolean success = sortQueue.push(item);
        if (success) {
            size++;
            // 重置树层级关系
            reset();
        }
    }

    public void remove(int item) {
        if (isEmpty()) {
            return;
        }
        boolean isSuccess = sortQueue.remove(item);
        if (isSuccess) {
            size--;
            // 重置树层级关系
            reset();
        }
    }

    public void reset() {
        this.head = null;
        Queue<TreeNode> queue = new Queue<>();

        Iterator<Integer> sortIterator = sortQueue.iterator();
        while (sortIterator.hasNext()) {
            TreeNode node = new TreeNode(sortIterator.next());

            if (head == null) {
                this.head = node;
                queue.push(this.head);
            } else {
                TreeNode treeNode = queue.pop();
                if (treeNode != null) {
                    treeNode.left = node;
                    queue.push(treeNode.left);

                    if (sortIterator.hasNext()) {
                        node = new TreeNode(sortIterator.next());

                        treeNode.right = node;
                        queue.push(treeNode.right);
                    }
                }
            }
        }
    }

    public TreeNode getHead() {
        return head;
    }

    public SortQueue<Integer> getSortQueue() {
        return sortQueue;
    }

    static class TreeNode implements Comparable<TreeNode> {
        private final int item;

        private TreeNode left;
        private TreeNode right;

        public TreeNode(int item) {
            this.item = item;
        }

        public int getItem() {
            return item;
        }

        public TreeNode getLeft() {
            return left;
        }

        public void setLeft(TreeNode left) {
            this.left = left;
        }

        public TreeNode getRight() {
            return right;
        }

        public void setRight(TreeNode right) {
            this.right = right;
        }

        @Override
        public int compareTo(TreeNode treeNode) {
            return this.item - treeNode.item;
        }
    }

}
