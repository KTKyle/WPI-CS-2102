package HW4;

import org.junit.Test;

import javax.xml.validation.Validator;

import static org.junit.Assert.*;

public class Examples {
    BTEmpty<Integer> mt = new BTEmpty<>();

    IBinTree<Integer> exMaxHeap =
            new BTNode<>(7,
                    new BTNode<>(3,
                            new BTNode<>(1, mt, mt),
                            new BTNode<>(2, mt, mt)),
            new BTNode<>(5,
                    mt,
                    new BTNode<>(4, mt, mt)));


    IBinTree<Integer> afterAdd6 =
            new BTNode<>(7,
                    new BTNode<>(3,
                            new BTNode<>(1, mt, mt),
                            new BTNode<>(2, mt, mt)),
                    new BTNode<>(6,
                            new BTNode<>(4, mt, mt),
                            new BTNode<>(5, mt, mt)));

    IBinTree<Integer> falseAdd6 =
            new BTNode<>(7,
                    new BTNode<>(3,
                            new BTNode<>(1, mt, mt),
                            new BTNode<>(2, new BTNode<>(6, mt ,mt), mt)),
                    new BTNode<>(5,
                            mt,
                            new BTNode<>(4, mt, mt)));

    IBinTree<Integer> afterRemove5 =
            new BTNode<>(7,
                    new BTNode<>(3,
                            new BTNode<>(1, mt, mt),
                            new BTNode<>(2, mt, mt)),
                    new BTNode<>(4, mt, mt));

    IBinTree<Integer> falseRemove7 =
            new BTNode<>(3,
                    new BTNode<>(4,
                            new BTNode<>(1, mt, mt),
                            new BTNode<>(mt, mt, mt)),
                    new BTNode<>(2, mt, mt));

    IBinTree<Integer> falseRemove7again = mt;

    IBinTree<Integer> falseAdd1 =
            new BTNode<>(7,
                    new BTNode<>(3,
                            new BTNode<>(1, new BTNode<>(1, mt, mt), mt),
                            new BTNode<>(2, mt, mt)),
                    new BTNode<>(5,
                            mt,
                            new BTNode<>(4, mt, mt)));







    IBinTree<Integer> exMinHeap =
            new BTNode<>(1,
                    new BTNode<>(2,
                            new BTNode<>(4, mt, mt),
                            new BTNode<>(5, mt, mt)),
                    new BTNode<>(3,
                            new BTNode<>(7, mt, mt),
                            new BTNode<>(8, mt, mt)));

    IBinTree<Integer> afterAdd10 =
            new BTNode<>(1,
                    new BTNode<>(10,
                            new BTNode<>(5, mt, mt),
                            new BTNode<>(4, new BTNode<>(2, mt ,mt), mt)),
                    new BTNode<>(3,
                            new BTNode<>(7, mt, mt),
                            new BTNode<>(8, mt, mt)));


    @Test
    public void testAdd60k(){
        ValidatorMaxHeap<Integer> maxHValid = new ValidatorMaxHeap<>();
        assertTrue(maxHValid.validAdd(exMaxHeap, 6, afterAdd6));
    }

    @Test
    public void testRemove50k(){
        ValidatorMaxHeap<Integer> maxHValid = new ValidatorMaxHeap<>();
        assertTrue(maxHValid.validRemove(exMaxHeap, 5, afterRemove5));
    }

    @Test
    public void falseAdd60k(){
        ValidatorMaxHeap<Integer> maxHValid = new ValidatorMaxHeap<>();
        assertFalse(maxHValid.validAdd(exMaxHeap, 6, falseAdd6));
    }

    @Test
    public void falseRemove70k(){
        ValidatorMaxHeap<Integer> maxHValid = new ValidatorMaxHeap<>();
        assertFalse(maxHValid.validRemove(exMaxHeap, 7, falseRemove7));
    }

    @Test
    public void falseRemove70k2(){
        ValidatorMaxHeap<Integer> maxHValid = new ValidatorMaxHeap<>();
        assertFalse(maxHValid.validRemove(exMaxHeap, 7, falseRemove7again));
    }

    @Test
    public void falseAdd1(){
        ValidatorMaxHeap<Integer> maxHValid = new ValidatorMaxHeap<>();
        assertFalse(maxHValid.validAdd(exMaxHeap, 1, exMaxHeap));
    }

    @Test
    public void falseAdd1k(){
        ValidatorMinHeap<Integer> minHValid = new ValidatorMinHeap<>();
        assertFalse(minHValid.validAdd(exMaxHeap, 1, exMaxHeap));
    }

    @Test
    public void falseAdd10k(){
        ValidatorMinHeap<Integer> minHValid = new ValidatorMinHeap<>();
        assertFalse(minHValid.validAdd(exMaxHeap, 1, afterAdd10));
    }

    @Test
    public void find4(){
        BST<Integer> maxBST = new BST<>(exMaxHeap);
        assertEquals(new BTNode<>(4, mt, mt),maxBST.search(4));
    }

}
