package HW4;

import java.util.Optional;

public class BST<T extends Comparable<T>> extends BinaryTree<T> {

    /**
     * Constructs a Binary Search Tree with the given immutable binary tree as its data
     * Sets the strategy and validator specific to BST's
     * @param data the immutable binary tree to initialize the BST
     */
    public BST(IBinTree<T> data){
        this.data = data;
        this.setStrategy(new StrategyBST());
        this.setValidator(new ValidatorBST());
    }

    /**
     * Searches for a specific key in the binary search tree.
     * @param key the element to search for
     * @return the found element if it exists in the tree, or {@code null} if not found
     */
    public Optional<T> search(T key) {
        return search(key, this.data);
    }

    /**
     * Recursively searches for a specific key in a given binary tree.
     * @param key the element to search for
     * @param tree the binary tree to search in
     * @return the found element if it exists in the tree, or {@code null} if not found
     */
    private Optional<T> search(T key, IBinTree<T> tree) {
        if (tree.isEmpty()) return Optional.empty();
        T root = tree.getRoot();
        int cmp = key.compareTo(root);
        if (cmp == 0) {
            return Optional.of(root);
        } else if (cmp < 0) {
            return search(key, tree.getLeft());
        } else {
            return search(key, tree.getRight());
        }
    }

}
