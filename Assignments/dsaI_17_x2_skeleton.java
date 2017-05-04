// dsaI_17_x2_skeleton.java SJ
// test program for X2

import fi.joensuu.cs.tra.*;

import java.util.ArrayList;
import java.util.Random;

// TODO: change the class name to your username
// all lower case
// comment out any package -lines
public class dsaI_17_x2_skeleton /* <---- CHANGE */ {

    public static void main(String[] args) {

        BTree<Integer> tree = new BTree<Integer>();

        int N = 20;
        if (args.length > 0)
            N = Integer.valueOf(args[0]);

        System.out.println("Into tree:");
        Random r = new Random(4);
        Integer x = new Integer(0);

        // insert successfully N values (unless we need > 10*N tries)
        int iter = 0, k = 0;
        while (k < N && iter < N*10) {
            x = r.nextInt(N*2);
            if (N <= 40)
                System.out.print(x + " ");
            if (inorderInsert(tree, x))
                k++;
            iter++;
        }
        System.out.println();

        int nodes = 0;

        if (N <= 40) {
            System.out.println("In in-order:");
            nodes = inorderPrint(tree);
        }
        System.out.println("     (" + nodes + " elements)");



        // you can use this as well
        // tree = exampleBTree();

        System.out.println("Height = " + height(tree));
        System.out.println("Optimal = " + (int)(Math.floor(Math.log(nodes)/Math.log(2))));

        System.out.println("Tree to array");

        // tree to array
        ArrayList<Integer> AL = inorderBTreeToArray(tree);
        if (N <= 40)
            System.out.println("As a list: " + AL);
        System.out.println("     (" + AL.size() + " elements)");

        // array to tree
        System.out.println("Array to tree");
        BTree<Integer> tree2 = arrayToInorderBTree(AL);

        if (N <= 40) {
            System.out.println("In in-order:");
            nodes = inorderPrint(tree);
        }
        System.out.println("     (" + nodes + " elements)");


        System.out.println("Height = " + height(tree2));

        System.out.println("Optimal = " + (int)(Math.floor(Math.log(nodes)/Math.log(2))));

        /*
        for (int i = 0; i < 16; i++) {
            System.out.println("Contains " + i + " : " +
                               inorderMember(tree2, new Integer(i)));
        }
        */

        System.out.println("Is is in-ordered: " + isInorder(tree));


    } // main()


    /**
     * X2. Write two algorithms that build a sorted array of an in-ordered
     * binary tree, and vice versa. Using these algorithms and algorithm like
     * in task 11, we can implement set difference, union, etc. in linear time.

     * First algorithm gets as parameter an in-ordered binary tree (BTree) and
     * it creates and returns an array (ArrayList) to which it has stored the
     * elements of the tree in the same order as the elements are in the input
     * tree.

     * Second algorithm gets as parameter a sorted array (ArrayList) and
     * creates and returns a balanced (as low as possible) in-ordered binary
     * tree. Neither of algorithms may change the input.

     * For full points, both algorithms must have linear time complexity.
     * Building a balanced tree in linear time is possible by starting from the
     * middle element of the input array and setting it as the root of the new
     * tree, and creating recursively left and right sub-tree from remaining
     * start and end halves of the ar- ray. Building can be done also using
     * other methods, but then time complexity and/or tree height may increase.
     * Take a skeleton from course www-page. Do not change the method names or
     * parameters.
     **/ 

    /**
      * X2a: binary tree to array.
      * @param T input binary tree
      * @return array of elements of the binary tree T in in-order
      **/
    public static <E> ArrayList<E> inorderBTreeToArray(BTree<E> T) {

        ArrayList<E> L = new ArrayList<E>();

        // TODO

        return L;
    }

    /**
      * X2b array to binary tree
      * @param L arraylist of elements
      * @return balanced binary tree formed from elements of L
      */
    public static <E> BTree<E> arrayToInorderBTree(ArrayList<E> L) {

         BTree<E> T = new BTree<E>();
         if (L.size() == 0) return T;   // trivial case

         // TODO

         return T;
    }

    /**
      * hint: make a helper method subarrayToSubtree
      **/


    // -------------------------------
    // examples/test methods


    /**
     * 19. Write an algorithm that inserts an element to an in-ordered binary
     * tree so that the tree remains in in-order. If there was the same element
     * already in the tree, no insert will be made and algorithm returns false.
     * If insert is made, the algorithm creates a new node, places it to the
     * correct position in the tree and returns true. Parameters are a tree and
     * an element. The idea of the algorithm was presented at lectures. What is
     * the time complexity of your algorithm?  
     **/
	public static <E extends Comparable<? super E>>
                boolean inorderInsert(BTree<E> T, E x) {
		BTreeNode<E> n = T.getRoot();
		if (n == null) {
			T.setRoot(new BTreeNode<E>(x));
			return true;
		}

		while (true) {

			if (x.compareTo(n.getElement()) == 0)
				// x already in tree, do not add
				return false;
			
			else if (x.compareTo(n.getElement()) < 0) {
				// x precedes element if n
				if (n.getLeftChild() == null) {
					// insertion point found
					n.setLeftChild(new BTreeNode<E>(x));
					return true;
				} else
					n = n.getLeftChild();
			} else {
				// x follows element in n
				if (n.getRightChild() == null) {
					// insertion point found
					n.setRightChild(new BTreeNode<E>(x));
					return true;
				} else
					n = n.getRightChild();
			}
		} // while

	} // inorderInsert()





    /**
     * 20. Write an algorithm that calculates the height if a given binary
     * tree. I.e., the longest path from root node to a leaf node. Hint:
     * recursion. What is the time complexity of your algorithm?  
     **/

    public static int height(BTree t) {
        return height(t.getRoot());
    }


    public static int height(BTreeNode n) {

        // "height"  of null is -1 to have leaf vertex height
        // max(-1,-1)+1 = 0
        if (n == null)
            return -1;

        // height of vertex is max of chidren heights + 1
        return java.lang.Math.max(height(n.getLeftChild()),
                                  height(n.getRightChild())) + 1;
    }




    public static BTree<Integer> exampleBTree() {

        BTree<Integer> T = new BTree<Integer>();

        // juuri
        T.setRoot(new BTreeNode<Integer>(10));

        BTreeNode<Integer> n = T.getRoot();

        // juuren lapset
        n.setLeftChild(new BTreeNode<Integer>(5));
        n.setRightChild(new BTreeNode<Integer>(15));

        // vasen haara
        BTreeNode<Integer> l = n.getLeftChild();

        l.setLeftChild(new BTreeNode<Integer>(3));
        l.setRightChild(new BTreeNode<Integer>(8));

        l.getLeftChild().setRightChild(new BTreeNode<Integer>(4));

        // oikea haara
        l = n.getRightChild();

        l.setLeftChild(new BTreeNode<Integer>(12));
        l.setRightChild(new BTreeNode<Integer>(18));

        l.getLeftChild().setLeftChild(new BTreeNode<Integer>(11));
        l.getLeftChild().setRightChild(new BTreeNode<Integer>(13));


        System.out.println("                 ");
        System.out.println("       10        ");
        System.out.println("    __/  \\__     ");
        System.out.println("   5        15   ");
        System.out.println("  / \\      /  \\  ");
        System.out.println(" 3   8   12    18");
        System.out.println("  \\      /\\      ");
        System.out.println("   4    11 13    ");
        System.out.println("                 ");

        return T;

    } // exampleBTree()


    public static <E extends Comparable<? super E>>
        boolean inorderMember(BTree<E> T, E x) {
            BTreeNode<E> n = T.getRoot();

            while (n != null) {
                if (x.compareTo(n.getElement()) == 0)
                    return true;
                else if (x.compareTo(n.getElement()) < 0)
                    n = n.getLeftChild();
                else
                    n = n.getRightChild();
            } // while
            return false;

        } // inorderMember()


    // print in in-order
    // returns number of nodes
    public static int inorderPrint(BTree T) {
        int nodes = inorderPrintBranch(T.getRoot());
        System.out.println();
        return nodes;
    } // inorderPrint()


    public static int inorderPrintBranch(BTreeNode n) {
        if (n == null)
            return 0;

        int nodes = 0;
        nodes += inorderPrintBranch(n.getLeftChild());
        System.out.print(n.getElement() + " ");
        nodes++;
        nodes += inorderPrintBranch(n.getRightChild());

        return nodes;

    } // inorderPrintBranch()

    /**
      * Are binary tree elements in in-order or not?
      * @param T input binary tree
      * @return true if elements are in in-order, false otherwise
      */
    public static <E extends Comparable<? super E>>
        boolean isInorder(BTree<E> T) {
            return isInorder(T.getRoot(), null, null);
    }

    // actual checking
    // checks that low.elem <= n.elem <= high.elem
    // null will always do
    public static <E extends Comparable<? super E>>
        boolean isInorder(BTreeNode<E> n, BTreeNode<E> low, BTreeNode<E> high) {
            if (n == null)
                return true;

            if (low != null && n.getElement().compareTo(low.getElement()) < 0)
                return false;

            if (high != null && n.getElement().compareTo(high.getElement()) > 0)
                return false;

            return  isInorder(n.getLeftChild(), low, n) &&
                    isInorder(n.getRightChild(), n, high);
        }


} // class dsaI_17_x2

