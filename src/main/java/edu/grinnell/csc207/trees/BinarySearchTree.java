package edu.grinnell.csc207.trees;

import java.util.ArrayList;
import java.util.List;

/**
 * A binary tree that satisifies the binary search tree invariant.
 */
public class BinarySearchTree<T extends Comparable<? super T>> {

    ///// From the reading
    /**
     * A node of the binary search tree.
     */
    private static class Node<T> {

        T value;
        Node<T> left;
        Node<T> right;

        /**
         * @param value the value of the node
         * @param left the left child of the node
         * @param right the right child of the node
         */
        Node(T value, Node<T> left, Node<T> right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        /**
         * @param value the value of the node
         */
        Node(T value) {
            this(value, null, null);
        }
    }

    private Node<T> root;

    /**
     * Constructs a new empty binary search tree.
     */
    public BinarySearchTree() {
    }

    private int sizeH(Node<T> node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + sizeH(node.left) + sizeH(node.right);
        }
    }

    /**
     * @return the number of elements in the tree
     */
    public int size() {
        return sizeH(root);
    }

    private Node<T> insertH(T value, Node<T> root) {
        if (root == null) {
            return new Node(value);
        } else {
            if (value.compareTo(root.value) < 0) {
                root.left = insertH(value, root.left);
            } else {
                root.right = insertH(value, root.right);
            }
            return root;
        }
    }

    /**
     * @param value the value to add to the tree
     */
    public void insert(T value) {
        root = insertH(value, root);
    }

    ///// Part 1: Traversals
    /**
     * @return the elements of this tree collected via an in-order traversal
     */
    public List<T> toListInorder() {
        List<T> list = new ArrayList();
        toListInorderH(root, list);
        return list;
    }

    private void toListInorderH(Node<T> root, List<T> list) {
        if (root != null) {
            toListInorderH(root.left, list);
            list.add(root.value);
            toListInorderH(root.right, list);
        }

    }

    /**
     * @return the elements of this tree collected via a pre-order traversal
     */
    public List<T> toListPreorder() {
        List<T> list = new ArrayList();
        toListPreorderH(root, list);
        return list;
    }

    private void toListPreorderH(Node<T> root, List<T> list) {
        if (root != null) {
            list.add(root.value);
            toListPreorderH(root.left, list);
            toListPreorderH(root.right, list);
        }
    }

    /**
     * @return the elements of this tree collected via a post-order traversal
     */
    public List<T> toListPostorder() {
        List<T> list = new ArrayList();
        toListPostorderH(root, list);
        return list;
    }

    private void toListPostorderH(Node<T> root, List<T> list) {
        if (root != null) {
            toListPostorderH(root.left, list);
            toListPostorderH(root.right, list);
            list.add(root.value);
        }
    }

    ///// Part 2: Contains
    /**
     * @param value the value to search for
     * @return true iff the tree contains <code>value</code>
     */
    public boolean contains(T value) {
        return containsH(value, root);
    }

    private boolean containsH(T key, Node<T> cur) {
        if (cur == null) {
            return false;
        } else if (key.compareTo(cur.value) < 0) {
            return containsH(key, cur.left);
        } else if (key.compareTo(cur.value) > 0) {
            return containsH(key, cur.right);
        } else {
            return true;
        }
    }
    ///// Part 3: Pretty Printing

    /**
     * @return a string representation of the tree obtained via an pre-order
     * traversal in the form: "[v0, v1, ..., vn]"
     */
    public String toStringPreorder() {
        StringBuffer buf = new StringBuffer("[");
        buf.append(root.value);
        toStringPreorderH(buf, root.left);
        toStringPreorderH(buf, root.right);
        buf.append("]");
        return buf.toString();
    }

    private void toStringPreorderH(StringBuffer buf, Node<T> root) {
        if (root != null) {
            buf.append(", ");
            buf.append(root.value);
            toStringPreorderH(buf, root.left);
            toStringPreorderH(buf, root.right);
        }
    }

    ///// Part 4: Deletion
    /*
     * The three cases of deletion are:
     * 1. no nodes
     * 2. one node
     * 3. two nodes
     */
    /**
     * Modifies the tree by deleting the first occurrence of <code>value</code>
     * found in the tree.
     *
     * @param value the value to delete
     */
    public void delete(T value) {
        root = deleteH(root, value);
    }

    private Node<T> deleteH(Node<T> cur, T key) {
        if (cur == null) {
            return null;
        } else if (key.compareTo(cur.value) < 0) {
            cur.left = deleteH(cur.left, key);
        } else if (key.compareTo(cur.value) > 0) {
            cur.right = deleteH(cur.right, key);
        } else {
            if (cur.left == null && cur.right == null) {
                return null;
            } else if (cur.right == null) {
                return cur.left;
            } else if (cur.left == null) {
                return cur.right;
            } else {
                Node<T> right = rightmost(cur.left);
                cur.value = right.value;
                cur.right = deleteH(right, right.value);
            }
        }
        return cur;
    }

    private Node<T> rightmost(Node<T> cur) {
        if (cur.right == null) {
            return cur;
        }
        return rightmost(cur.right);
    }

}


/*
temp = cur.right;
restructure(cur, cur.left;)
restructure(cur.left.right, temp)
meaning we swap cur left's right side with temp
*/
