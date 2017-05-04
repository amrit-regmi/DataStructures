import java.util.Random;
/*
 * Question 27
 */
public class Tree<T>
{
	private TrunkNode<T> root;
	/*
	 * Constructor for tree object creates empty tree
	 */
	public Tree (){
		root = null;
	}
	/*
	 * Return the root of the tree
	 */
	public TrunkNode<T> getRoot(){
		return this.root;
	}
	/*
	 * Sets the given TrunkNode as root of the tree
	 */
	private void setRoot(TrunkNode<Object> trunkNode){
		trunkNode.parent=null;
    	this.root = (TrunkNode<T>) trunkNode;
    }
	 /*
	  * Class for leaf nodes
	  */
	public  static class Node<T>{
         private TrunkNode<?> parent; //parent points to Parent trunknode
         private Object data;
         
         
         /*
          * Constructor for leaf nodes, takes parentnode and data as argument
          */
         public Node(TrunkNode<?> parentNode,Object data){
         	this.parent = parentNode;
         	this.data = data;
         	
         }
         
    } 
    
	/*
	 * Class for TrunkNode
	 */
	public static class TrunkNode<T> {
        private Object data;
        private TrunkNode<?> parent; 
        private Node<?> leftChild;
        private Node<?> rightChild;
        private Node<?> leftMostChild;
        private Node<?> rightMostChild;
        private TrunkNode<?> trunkChild;
        
        /*
         * Constructor for TrunkNode object takes parent node and data
         */
        private TrunkNode(TrunkNode<?> t,Object data){
       	 this.data = data;
       	 this.parent = t; 
         this.leftChild= null;
         this.rightChild=null;
         this.leftMostChild=null;
         this.rightMostChild=null;
         this.trunkChild=null;
       	
       } 
        /*
         * Methods for setting childNodes
         * Creates a new node with parent and data 
         */
       
        private void setLeftChild(Object data){
        	 this.leftChild= new Node<Object>(this,data);  
        }
        private void setRightChild(Object data){
        	this.rightChild=new Node<Object>(this,data); 
     	 // System.out.println(data);
        }
        private void setLeftMostChild(Object data){
        	this.leftMostChild =new Node<Object>(this,data);  
        }
        private void setRightMostChild(Object data){
        	this.rightMostChild= new Node<Object>(this,data);  
        }
        
        private void setTrunkChild(Object data){
        	this.trunkChild=new TrunkNode<Object>(this,data);
        	
        }
        
       
        
	}
	
	
	public static void main(String[] args){
		Tree<?> myTree  = new Tree<Object>();
		populateTree(4, myTree);
		printTree(myTree);
		
	}
		/*
		 * Method to populate the tree	
		 */
		public static Tree<?> populateTree(int height, Tree<?> T){
		
			/*
			 * Creating the array of size height of tree +1 
			 * there will be 5*height+1 nodes 
			 */
		int Rand[] = new int[height*5+1];
		System.out.println("Array data");
		for (int i = 0; i < height*5+1; i++) {
			
			
			Rand[i] = getRandomNumberInRange(3, height*5);
			System.out.print(" "+Rand[i]);
			
		}
		
		
		
		TrunkNode<?> temp = T.getRoot();
		/*
		 * If root of tree is not set 
		 * we set the element at first index as root 
		 */
		if( T.getRoot() == null){
			T.setRoot(new TrunkNode<Object>(null,Rand[0]));
			 temp = T.getRoot();
		}
		System.out.println();
		
		int i= 1;
		//System.out.println(temp.getData());
		
		/*
		 * Starting from the next index, checking if we are at end of array
		 * if not setting the consecutive elements to child node and moving
		 * down the tree
		 */
		
		 while (i <Rand.length){
			 //System.out.println("index"+i);
			 	
				temp.setLeftMostChild(Rand[i]);
				temp.setLeftChild(Rand[i+1]);
				temp.setRightChild(Rand[i+2]);
				temp.setRightMostChild(Rand[i+3]);
				temp.setTrunkChild(Rand[i+4]);
				
				i= i+5;
				/*
				 * Moving to the new TrunkNode which will be the TrunKChild node
				 * of current TrunkNode
				 */
				temp =temp.trunkChild;
				
			}
		 /*
		  * Exit and return the tree
		  */
		 return T;
		
		}
		
		/*
		 * Method to print the tree TrunkNode first and leftmost to rightmost
		 */
	public static void printTree(Tree<?> tree){
		System.out.println("To Tree  ");
		if (tree.getRoot()!= null ){
			TrunkNode<?> t= tree.getRoot();
			System.out.println();
			
			while(t != null ){
				if(t.trunkChild== null){
					System.out.println("       "+t.data);
					
					/*System.out.println("parent  "+t.parent);
					System.out.println("self  "+t + "  data  "+ t.data);
					t = t.trunkChild;
					System.out.println("child  "+t);
					*/
					break;
				}
				else{
					
					System.out.print("       "+t.data);
					/*System.out.println(t.leftMostChild.parent.data+" lmctest");
					System.out.println(t.leftChild.parent.data+" lctest");
					System.out.println(t.trunkChild.parent.data+" tcptest");
					System.out.println(t.trunkChild.data+" tctest");*/
					System.out.println(" ");
					System.out.println(" --- / | \\---");
					System.out.println("/   /  |  \\  \\");
					System.out.print(t.leftMostChild.data);
					System.out.print("   "+t.leftChild.data);
					System.out.print("    ");
					System.out.print(t.rightChild.data);
					System.out.println("  "+t.rightMostChild.data);
					
					
				}
				
				/*System.out.println("parent  "+t.parent);
				System.out.println("self  "+t + "  data  "+ t.data);*/
				t = t.trunkChild;
				//System.out.println("child  "+t);
				
				
			
			}
		}
		
		
		
	}
	
	
	
	private static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
                 
}
	
	
	
	
	  


