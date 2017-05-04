import java.util.Queue;
import fi.joensuu.cs.tra.BTree;
import fi.joensuu.cs.tra.BTreeNode;
import fi.joensuu.cs.tra.LinkedQueue;
public class q25 {

	public static void main(String[] args) {
		
		BTree T = exampleBTree();
        int depth = findShortestPath(T);
        
        System.out.println("The minimum depth is" + depth);
		
		
	}
	
	/*
	 * Returns the least depth of the tree and prints the leaf elements on the same depth;
	 */
	public static <E> int findShortestPath(BTree T)
	{
	   Queue<BTreeNode> queue = new LinkedQueue();
	   BTreeNode current = T.getRoot();
	   
	   if(current == null){
		   System.out.println("Empty tree");
		   return 0;
	   }
		   
	   queue.add(current);
	   BTreeNode flag = new BTreeNode("levelIndicator");
	   queue.add(flag);
	   Integer depth = 0;
	   Integer mindepth = null;
	   
	
	    
	    while(!queue.isEmpty())
	    {
            BTreeNode temp = queue.poll();
            
            if(temp != flag){
            	
            	
	            if(temp.getLeftChild() == null && temp.getRightChild() == null){
	            	System.out.println("shortest path leaf node: ");
	            	System.out.println(temp.getElement() + "  ");
	            	mindepth = depth;
	            }
	            else {
	            	if (temp.getLeftChild() != null){
		                queue.add(temp.getLeftChild());
		                
		                
		            }
	            	if (temp.getRightChild() != null) {
	                	queue.add(temp.getRightChild());
	                
	            	}
	            	
	            }
	            
	        }
	        else if (!queue.isEmpty()){
	            	 queue.add(flag);
	            	 if(depth.equals(mindepth)){
	            		 return depth;
	            		 //break;
	            	 }
	            	 depth++; 	
	        }
            
	    }
	    return depth;
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
        l.getRightChild().setRightChild(new BTreeNode<Integer>(9));

        // oikea haara
        l = n.getRightChild();

        l.setLeftChild(new BTreeNode<Integer>(12));
        l.setRightChild(new BTreeNode<Integer>(18));
        l.getRightChild().setRightChild(new BTreeNode<Integer>(19));
        l.getRightChild().setLeftChild(new BTreeNode<Integer>(16));

        l.getLeftChild().setLeftChild(new BTreeNode<Integer>(11));
        l.getLeftChild().setRightChild(new BTreeNode<Integer>(13));
        


        System.out.println("                 ");
        System.out.println("       10        ");
        System.out.println("    __/  \\__     ");
        System.out.println("   5          15   ");
        System.out.println("  / \\        /   \\  ");
        System.out.println(" 3   8      12     18");
        System.out.println("  \\   \\    / \\     / \\ ");
        System.out.println("   4   9  11  13  16 19");

        return T;

    }
}
	
	
	/*
	 * Qno 28
	 * 
	 */
	
	/*public static Tree createTree(int height){
		
	}*//* A tree node structure */
	
	

