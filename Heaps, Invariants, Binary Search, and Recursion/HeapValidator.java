package HW4;


public abstract class HeapValidator<E extends Comparable<E>> implements IBTValidator<E> {

    /**
     * Validates the heap property for a given binary tree.
     *
     * @param tree the binary tree to validate
     * @param isMaxHeap true if validating for a max-heap, false for a min-heap
     * @return true if the tree satisfies the heap property, false otherwise
     */
    public boolean validateHeapProperty(IBinTree<E> tree, boolean isMaxHeap) {
        if (tree.isEmpty()) return true;
        E root = tree.getRoot();
        IBinTree<E> left = tree.getLeft();
        IBinTree<E> right = tree.getRight();
        boolean leftValid = left.isEmpty() || compare(root, left.getRoot(), isMaxHeap);
        boolean rightValid = right.isEmpty() || compare(root, right.getRoot(), isMaxHeap);

        return leftValid && rightValid &&
                validateHeapProperty(left, isMaxHeap) &&
                validateHeapProperty(right, isMaxHeap);
    }

    /**
     * Validates the elements of the tree after an add or remove operation
     * @param oldTree the original binary tree before the operation
     * @param elt the element added or removed
     * @param newTree the binary tree after the operation
     * @param heapAdd   true if validating an add operation, false for a remove operation
     * @return true if the element consistency is valid, false otherwise
     */
    public boolean validateElements(IBinTree<E> oldTree, E elt, IBinTree<E> newTree, boolean heapAdd) {
        return (heapAdd ? equalAfterAdd(oldTree, elt, newTree) : equalAfterRemove(oldTree, elt, newTree));
    }

    /**
     * Checks that the new tree contains all elements of the old tree plus the added element
     * @param oldTree the original binary tree
     * @param elt the element added
     * @param newTree the resulting binary tree
     * @return true if the new tree contains all elements correctly, false otherwise
     */
    public boolean equalAfterAdd(IBinTree<E> oldTree, E elt, IBinTree<E> newTree) {
        return containsAll(oldTree, newTree) && contains(newTree, elt) && newTree.size() == oldTree.size() + 1;
    }

    /**
     * Checks that the new tree contains all elements of the old tree except the removed element
     * @param oldTree the original binary tree
     * @param elt the element removed
     * @param newTree the resulting binary tree
     * @return true if the new tree contains all elements correctly, false otherwise
     */
    public boolean equalAfterRemove(IBinTree<E> oldTree, E elt, IBinTree<E> newTree) {
        return containsAll(newTree, oldTree) && !contains(newTree, elt) && newTree.size() == oldTree.size() - 1;
    }

    /**
     * Checks if a tree contains a specific element
     * @param tree the binary tree
     * @param elt the element to check for
     * @return true if the tree contains the element, false otherwise
     */
    public boolean contains(IBinTree<E> tree, E elt) {
        if (tree.isEmpty()) return false;
        if (tree.getRoot().equals(elt)) return true;
        return contains(tree.getLeft(), elt) || contains(tree.getRight(), elt);
    }

    /**
     * Checks if one tree contains all elements of another tree
     * @param tree1 the first tree (whose elements are to be checked)
     * @param tree2 the second tree (to search within)
     * @return true if all elements of tree1 are present in tree2, false otherwise
     */
    public boolean containsAll(IBinTree<E> tree1, IBinTree<E> tree2) {
        if (tree1.isEmpty()) return true;
        return contains(tree2, tree1.getRoot()) &&
                containsAll(tree1.getLeft(), tree2) &&
                containsAll(tree1.getRight(), tree2);
    }

    /**
     * Compares two elements according to the heap property
     * @param parent the parent element
     * @param child the child element
     * @param isMaxHeap true for max-heap validation, false for min-heap validation
     * @return true if the comparison satisfies the heap property, false otherwise
     */
    public boolean compare(E parent, E child, boolean isMaxHeap) {
        return isMaxHeap ? parent.compareTo(child) >= 0 : parent.compareTo(child) <= 0;
    }
}
