import fi.joensuu.cs.tra.*;

public class task7<E> {
	public static void main(String[] args){
		BTree T = exampleBTree();
		Set s = getLeaf(T,new Set());
		System.out.println(s);
		
	}
	
	@SuppressWarnings("unchecked")
	public static Set getLeaf(BTree T, Set S){
		
		BTreeNode n= T.getRoot();
		
		
		if(n.getLeftChild() == null && n.getRightChild() == null ){
			S.add(n.getElement());
			
		}
		
		if(n.getLeftChild() != null){
			getLeaf(new BTree(n.getLeftChild()),S);	
		}
		
		if(n.getRightChild() != null){
			getLeaf(new BTree(n.getRightChild()),S);	
		}
		
		return S;
		
	}
	
	
	
	
	
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

	}

}
