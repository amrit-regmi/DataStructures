// BinTreeExample17.java SJ

import fi.joensuu.cs.tra.*;
import java.util.Random;

public class BinTreeExample17 {

	public static void main(String[] args) {

		BTree<Integer> tree = new BTree<Integer>();

		int N = 10;
		if (args.length > 0)
			N = Integer.valueOf(args[0]);

		System.out.println("To tree:");
		Random r = new Random(42);
		Integer x;
		for (int i = 0; i < N; i++) {
			x = r.nextInt(N*2);
			System.out.print(x + " ");
			inorderInsert(tree, x);
		}
		System.out.println();

		System.out.println("Elements in in-order:");
		inorderPrint(tree);

		for (int i = 0; i < 16; i++) {
			System.out.println("Member " + i + " : " +
							   inorderMember(tree, new Integer(i)));
		}

		tree = exampleBTree();
		
		System.out.println("Elements in in-order:");
		inorderPrint(tree);

		for (int i = 0; i < 20; i++) {
			System.out.println("Member " + i + " : " +
							   inorderMember(tree, new Integer(i)));
		}
		
		//q20 test
		System.out.println("Height of example tree is " + treeHeight(tree));
		
		//q21 test
		System.out.println("Comparing example tree with itself should be true " + compareTree(tree,tree));
		
		inorderInsert(tree, 14);
		System.out.println("Comparing example tree with modified tree should be false " + compareTree(exampleBTree(),tree));
		
		//q20 test
				System.out.println("Height of example tree is " + treeHeight(tree));
		
		

		System.out.println("Remove:");
		for (int i = 0; i < N; i++) {
			x = r.nextInt(N*2);
			System.out.print(x + ":");
			BTreeNode<Integer> n = inorderFindNode(tree, x);
			if (n != null) {
				inorderRemoveNode(tree, n);
				inorderPrint(tree);
			} else
				System.out.println();
		}
		System.out.println();

		
	} // main()

	/* Create small in-ordered example binary tree
	                 
	       10        
	    __/  \__     
	   5        15   
	  / \      /  \  
	 3   8   12    18
	  \      /\      
	   4    11 13    
	                 
	*/
	public static BTree<Integer> exampleBTree() {

		BTree<Integer> T = new BTree<Integer>();

		// root

		BTreeNode<Integer> n = T.setRoot(new BTreeNode<Integer>(10));

		// children or root
		n.setLeftChild(new BTreeNode<Integer>(5));
		n.setRightChild(new BTreeNode<Integer>(15));

		// left branch
		BTreeNode<Integer> l = n.getLeftChild();

		l.setLeftChild(new BTreeNode<Integer>(3));
		l.setRightChild(new BTreeNode<Integer>(8));

		l.getLeftChild().setRightChild(new BTreeNode<Integer>(4));

		// right branch
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


	// Print in in-order
	public static void inorderPrint(BTree<Integer> T) {
		inorderPrintBranch(T.getRoot());
		System.out.println();
	} // inorderPrint()


	public static void inorderPrintBranch(BTreeNode<Integer> n) {
		if (n == null)
			return;

		inorderPrintBranch(n.getLeftChild());
		System.out.print(n.getElement() + " ");
		inorderPrintBranch(n.getRightChild());

	} // inorderPrintBranch()


    // insert to in-ordered binary tree
	public static <E extends Comparable<? super E>>
                boolean inorderInsert(BTree<E> T, E x) {
		BTreeNode<E> n = T.getRoot();
		if (n == null) {
			T.setRoot(new BTreeNode<E>(x));
			return true;
		}
		 
		
		// code removed
        // Exercise for week 4 
		//Q.19 Time Complexity = O(height)
		BTreeNode<E> temp = n;
		
		while (temp != null){
			
			if(x.compareTo(temp.getElement())>0){
				
				if(temp.getRightChild()==null){
					temp.setRightChild(new BTreeNode<E>(x));
					return true;
				}
				temp = temp.getRightChild();
				
			}
			else if(x.compareTo(temp.getElement())<0){
				
				if(temp.getLeftChild()==null){
					temp.setLeftChild(new BTreeNode<E>(x));
					return true;
					
				}
				temp = temp.getLeftChild();
				
			}
			else if(x.compareTo(temp.getElement())==0){
				return false;
			}
			
			
		}
		return false;
	
       

        


	} // Q.19 inorderInsert()
	
	
	//Q. 20 getting height of Binary Tree Time complexity = O(height)
	
	public static int treeHeight(BTree<Integer> T){
		BTreeNode<Integer> n= T.getRoot();
		int height = nodeHeight(n)-1;
		return height;
	}
	
	public static int nodeHeight(BTreeNode<Integer> node){ 
		
		if(node==null /*|| node.getLeftChild() == null && node.getRightChild() == null&|*/){
			return 0;
		}
		else{
			return (1+Math.max(nodeHeight(node.getLeftChild()), nodeHeight(node.getRightChild())));	
		}
	}//Q.20
	
	
	
	//Q.21
	public static boolean compareTree(BTree<Integer> T1,BTree<Integer> T2){
		
		BTreeNode<Integer> TN1 = T1.getRoot();
		BTreeNode<Integer> TN2 = T2.getRoot();
		
		return(compareNode(TN1,TN2));
	}
	
	//Time complexity = O(height)
	
	public static boolean compareNode(BTreeNode<Integer> TN1, BTreeNode<Integer> TN2){
		
		if (TN1 == null && TN2 == null){ //null tree are equal
			return true;
		}
		if (TN1 == null && TN2 != null || TN1 != null && TN2 == null  ){ //if there is comparison between empty and non empty node 
			return false;
		}
		//else we check recursively until every comparison returns true
		return(compareNode(TN1.getLeftChild(),TN2.getLeftChild()) && compareNode(TN1.getRightChild(),TN2.getRightChild()));
		
	}//Q21
	
	
	//Q22 TIME COMPLEXITY O(log(n))
	
	public static BTreeNode<?> inorderFirst(BTree<?> T){
		BTreeNode<?> node = T.getRoot();
		if(node.getLeftChild() == null){
			return null;
		}
		while(node.getLeftChild() != null){
				node = node.getLeftChild();		
		}
		return node;
	}

	// finds given element from tree, returns boolean value
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


	// finds the node of given element in a tree
	public static <E extends Comparable<? super E>>
                BTreeNode<E> inorderFindNode(BTree<E> T, E x) {
		BTreeNode<E> n = T.getRoot();

		while (n != null) {
			if (x.compareTo(n.getElement()) == 0)
				return n;
			else if (x.compareTo(n.getElement()) < 0)
				n = n.getLeftChild();
			else
				n = n.getRightChild();
		} // while
		return null;

	} // inorderFindNode()


	// finds the node of given element in a tree
    // recursive version, just for example

    // recursion starting method
	public static <E extends Comparable<? super E>>
                BTreeNode<E> inorderFindNode2(BTree<E> T, E x) {
        return inorderFindNode2_r(T.getRoot(), x);
	} // inorderFindNode2()

    // actual recursive method
	public static <E extends Comparable<? super E>>
                BTreeNode<E> inorderFindNode2_r(BTreeNode<E> n, E x) {
        if (n == null)
            return null;

        // comparison
        int cmp = x.compareTo(n.getElement());
        
        if (cmp == 0)   // found here
            return n;

        if (cmp < 0)    // left or right
            return inorderFindNode2_r(n.getLeftChild(), x);
        else
            return inorderFindNode2_r(n.getRightChild(), x);

	} // inorderFindNode2_r()



	public static <E extends Comparable<? super E>>
                BTreeNode<E> inorderNext(BTreeNode<E> n) {

        // code removed
        // exercise for week 4
		//Q.24

        // if node has right child, find it's leftmost
        // descendant timeComplexity = O(heightOfNode)
		
		if(n.getRightChild() != null){
			
			n=n.getRightChild();
			
				while(n.getLeftChild() != null){
					n = n.getLeftChild();		
				}
				
				return n;
			
			
		}
		
		
        // otherwise, find the first ancestor whose
        // left subtree node n is
		if(n.getRightChild() == null && n.getParent() != null){
			
			BTreeNode<E> temp =n.getParent();

			while (temp.getLeftChild() != n ){			
				n= temp;
				temp = temp.getParent();
			}
			
			return temp;
				
		}


		// not found
		
		return null;
		

	} // inorderNext() Q.24


    // removes given node so that tree remains in in-order
	public static <E extends Comparable<? super E>>
            void inorderRemoveNode(BTree<E> T, BTreeNode<E> n) {

		// references to relatives 
		BTreeNode<E> lc = n.getLeftChild();
		BTreeNode<E> rc = n.getRightChild();

		BTreeNode<E> par = n.getParent();

		// remember whether we a left of right child of the parent
		boolean left = false;
		if (par != null) {
			if (par.getLeftChild() == lc)
				left = true;
			else
				left = false;
		}

        // 0 and 1 child cases 

		// if root, no left child: right child replaces
		if (n == T.getRoot() && lc == null) {
			T.setRoot(rc);
            return;
        }

		// if root, no right child: left child replaces
		else if (n == T.getRoot() && rc == null) {
			T.setRoot(lc);
            return;
        }

		// if no root, no left child: right child replaces
		if (lc == null) {
			if (left)
				par.setLeftChild(rc);
			else if (rc != null)
				par.setRightChild(rc);
		}

		// if no root, no right child: left child replaces
        else if (rc == null) {
			if (left)
				par.setLeftChild(lc);
			else if (lc != null)
				par.setRightChild(lc);
		}


        // both children exists
		else {

			BTreeNode<E> repl = inorderNext(n);
			E replElement = repl.getElement();

			// remoce replacing element recursively
			inorderRemoveNode(T, repl);

			// rigth child was possible changed
			rc = n.getRightChild();

			// new node
			repl = new BTreeNode<E>(replElement);
			if (n == T.getRoot())
				T.setRoot(repl);
			else if (left)
				par.setLeftChild(repl);
			else
				par.setRightChild(repl);

			// restore children
			repl.setLeftChild(lc);
			repl.setRightChild(rc);

            // alternative version: no now node, instead
            // replace the element only

			// n.setElement(replElement);

		}


	} // inorderRemoveNode

	
} // class BinTreeExample17