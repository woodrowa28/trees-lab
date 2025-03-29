package edu.grinnell.csc207.trees;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class BinarySearchTreeTests {

    private BinarySearchTree<Integer> makeSampleTree() {
        BinarySearchTree<Integer> tree = new BinarySearchTree();
        tree.insert(5);
        tree.insert(0);
        tree.insert(7);
        tree.insert(11);
        tree.insert(6);
        return tree;
    }
    
    private BinarySearchTree<Integer> makeSampleTree2() {
        BinarySearchTree<Integer> tree = new BinarySearchTree();
        tree.insert(6);
        tree.insert(7);
        tree.insert(8);
        tree.insert(9);
        tree.insert(10);
        return tree;
    }
    
    private BinarySearchTree<Integer> makeSampleTree3() {
        BinarySearchTree<Integer> tree = new BinarySearchTree();
        tree.insert(6);
        tree.insert(3);
        tree.insert(1);
        tree.insert(4);
        tree.insert(8);
        tree.insert(7);
        tree.insert(9);
        return tree;
    }

    @Test
    public void emptyTreeTest() {
        BinarySearchTree<Integer> tree = new BinarySearchTree();
        assertEquals(0, tree.size());
    }

    @Test
    public void basicSizeInsertTest() {
        BinarySearchTree<Integer> tree = makeSampleTree();
        assertEquals(5, tree.size());
    }

    @Test
    public void basicToListInorderTest() {
        // N.B., need to upcast the Integer[] array to avoid a ClassCastException that
        // arises from downcasting the result of toArray to Integer[].
        assertArrayEquals((Object[]) new Integer[] {0, 5, 6, 7, 11},
                          makeSampleTree().toListInorder().toArray());
        assertArrayEquals((Object[]) new Integer[] {6, 7, 8, 9, 10},
                          makeSampleTree2().toListInorder().toArray());
        assertArrayEquals((Object[]) new Integer[] {1, 3, 4, 6, 7, 8, 9},
                          makeSampleTree3().toListInorder().toArray());
    }

    @Test
    public void basicToListPreorderTest() {
        assertArrayEquals((Object[]) new Integer[] {5, 0, 7, 6, 11},
                          makeSampleTree().toListPreorder().toArray());
        assertArrayEquals((Object[]) new Integer[] {6, 7, 8, 9, 10},
                          makeSampleTree2().toListPreorder().toArray());
        assertArrayEquals((Object[]) new Integer[] {6, 3, 1, 4, 8, 7, 9},
                          makeSampleTree3().toListPreorder().toArray());
    }

    @Test
    public void basicToListPostorderTest() {
        assertArrayEquals((Object[]) new Integer[] {0, 6, 11, 7, 5},
                          makeSampleTree().toListPostorder().toArray());
        assertArrayEquals((Object[]) new Integer[] {10, 9, 8, 7, 6},
                          makeSampleTree2().toListPostorder().toArray());
        assertArrayEquals((Object[]) new Integer[] {1, 4, 3, 7, 9, 8, 6},
                          makeSampleTree3().toListPostorder().toArray());
    }

    @Test
    public void basicContainsTest() {
        BinarySearchTree<Integer> tree1 = makeSampleTree();
        BinarySearchTree<Integer> tree2 = makeSampleTree2();
        assertEquals(true, tree1.contains(5));
        assertEquals(false, tree1.contains(8));
        assertEquals(false, tree2.contains(0));
        assertEquals(true, tree2.contains(10));
    }

    @Test
    public void basicToStringPreorderTest() {
        assertEquals("[5, 0, 7, 6, 11]", makeSampleTree().toStringPreorder());
        assertEquals("[6, 7, 8, 9, 10]", makeSampleTree2().toStringPreorder());
        assertEquals("[6, 3, 1, 4, 8, 7, 9]", makeSampleTree3().toStringPreorder());
    }

    @Test
    public void noChildrenDeleteTest() {
        BinarySearchTree<Integer> tree = makeSampleTree();
        assertEquals(5, tree.size());
        assertTrue(tree.contains(6));
        tree.delete(6);
        assertEquals(4, tree.size());
        assertFalse(tree.contains(6));
    }
    
    @Test
    public void oneChildDeleteTest() {
        BinarySearchTree<Integer> tree = makeSampleTree2();
        assertEquals(5, tree.size());
        assertTrue(tree.contains(7));
        tree.delete(7);
        assertEquals(4, tree.size());
        assertFalse(tree.contains(7));
    }
    
    @Test
    public void twoChildrenDeleteTest() {
        BinarySearchTree<Integer> tree = makeSampleTree3();
        assertEquals(7, tree.size());
        assertTrue(tree.contains(8));
        tree.delete(8);
        assertEquals(6, tree.size());
        assertFalse(tree.contains(8));
    }
}
