package HW4;

public class ValidatorMinHeap<E extends Comparable<E>> extends HeapValidator<E> implements IBTValidator<E> {

    /**
     * Validates that adding an element to the MinHeap produces a valid heap compared to the original MinHeap
     * @param oldTree the original heap before the addition
     * @param elt the element being added
     * @param newTree the resulting heap after the addition
     * @return true if the resulting heap contains the correct elements after adding or false otherwise
     */
    @Override
    public boolean validAdd(IBinTree<E> oldTree, E elt, IBinTree<E> newTree) {
        return validateElements(oldTree, elt, newTree, true) && validateHeapProperty(newTree, false);
    }

    /**
     * Validates that removing an element to the MinHeap produces a valid heap compared to the original MinHeap
     * @param oldTree the original heap before the addition
     * @param elt the element being added
     * @param newTree the resulting heap after the addition
     * @return true if the resulting heap contains the correct elements after removing or false otherwise
     */
    @Override
    public boolean validRemove(IBinTree<E> oldTree, E elt, IBinTree<E> newTree) {
        return validateElements(oldTree, elt, newTree, false) && validateHeapProperty(newTree, false);
    }
}