
import fi.joensuu.cs.tra.*;

import java.util.LinkedList;
import java.util.HashSet;
import java.util.LinkedHashSet;


// skeleton and test program for X1 and 14.

//       change class name and file name to your username, all lower case
//           ||||||||||
//           vvvvvvvvvv
public class amritreg {

	public static void main(String[] args) {

		// defaults
		// testing with there values gives some idea, but is not
		// exhaustive
		int vertices = 15;
		int edges = 15;

		// first parameter: number of edges
		if (args.length > 0)
			vertices = Integer.valueOf(args[0]);

		// second parameter: number of edges
		if (args.length > 1)
			edges = Integer.valueOf(args[1]);

		int seed = vertices+edges;

		// third parameter: random number seed
		if (args.length > 2)
			seed = Integer.valueOf(args[2]);


		// change to your username also here
		amritreg y = new amritreg();

		// create random graph
		DiGraph graph = GraphMaker.createDiGraph(vertices, edges, seed);
		System.out.println("\nGraph:");

		GraphMaker.setWeights(graph, 10, (float)0.1, seed);

		// GraphMaker.toString(graph, 1) prints edges in form "d-w",
		// where d is the destination vertex and w is weight of edge
		if (edges < 20)
			System.out.println(GraphMaker.toString(graph, 1));

		System.out.println("Roots: " + y.rootVertices(graph));


		LinkedList<Vertex> heaviest = new LinkedList<Vertex>();
		float mw = y.heaviestTree(graph, heaviest);
		System.out.println("heaviestTree: " + mw + " : " + heaviest);


		// create directed acyclig graph
		graph = GraphMaker.createDAG(vertices, edges, seed);
		System.out.println("\nDAG:");

		GraphMaker.setWeights(graph, 10, (float)0.1, seed);
		if (edges < 20)
			System.out.println(GraphMaker.toString(graph, 1));

		System.out.println("Roots: " + y.rootVertices(graph));

		heaviest = new LinkedList<Vertex>();
		mw = y.heaviestTree(graph, heaviest);
		System.out.println("heaviestTree: " + mw + " : " + heaviest);


		// create forest, useful to use edges < vertices
		graph = GraphMaker.createForest(vertices, edges, seed, 10);
		System.out.println("\nForest:");

		GraphMaker.setWeights(graph, 10, (float)0.1, seed);
		if (edges < 20)
			System.out.println(GraphMaker.toString(graph, 1));

		System.out.println("Roots: " + y.rootVertices(graph));

		heaviest = new LinkedList<Vertex>();
		mw = y.heaviestTree(graph, heaviest);
		System.out.println("heaviestTree: " + mw + " : " + heaviest);

		// mess the forest

		System.out.println("\nMessed forest 1");
		GraphMaker.addRandomEdges(graph, 1, false, 10);
		if (edges < 20)
			System.out.println(GraphMaker.toString(graph, 1));

		System.out.println("Roots: " + y.rootVertices(graph));


		heaviest = new LinkedList<Vertex>();
		mw = y.heaviestTree(graph, heaviest);
		System.out.println("heaviestTree: " + mw + " : " + heaviest);

		System.out.println("\nMessed forest 2");
		GraphMaker.addRandomEdges(graph, 2, false, 10);

		if (edges < 20)
			System.out.println(GraphMaker.toString(graph, 1));

		System.out.println("Roots: " + y.rootVertices(graph));

		heaviest = new LinkedList<Vertex>();
		mw = y.heaviestTree(graph, heaviest);
		System.out.println("heaviestTree: " + mw + " : " + heaviest);


		System.out.println();

	} // main()





	/**
	 * X1 Heaviest tree.
	 * 
	 * X1. Write an algorithm that finds the heaviest tree component of a
	 * directed graph. The weight of a tree is the sum of the weights of its
	 * edges. A legitimate tree is a separate full component of a graph that
	 * has no back, forward, or cross edges (within the tree, or from
	 * outside). Algorithm returns the weight of the heaviest tree, or
	 * Float.NaN if there is not a single legitimate tree in the graph. The
	 * heaviest tree is also placed to the list of vertices given as a
	 * parameter, root as the first element of the list. What it the time
	 * complexity of your algorithm?
	 *
	 * Do not start coding (or touch the keyboard) before you have a proper
	 * plan and a working algorithm on paper. Make your solution modular,
	 * solve the problem in stages.
	 *
	 * Hints: You need to first find the potential root vertices in the graph,
	 * then check that the compo- nents of the roots really are distinct trees
	 * and calculate the weights of the trees. Take a skeleton and test
	 * program from course www-page. A partial solution is better than no
	 * solution. If you make a partial solution, write the self evaluation
	 * correspondingly.
	 *
	 * @param G the input directed graph
	 * @param resultTree an empty list to which resulting heaviest tree will be stored if found.
	 * @return Weight of the tree, or Float.NaN if no tree in graph.
	 **/

	/*
	 * I tried to solve the problem using three recursive functions and Array of LinkedHashSets and Array of floats 
	 * both of length of number of roots  to store perspective tree and other to store the weight of corresponding tree
	 * First I did dfs starting from roots until I have the valid node vertices  and kept adding it to hashset and painting black. 
	 * If I encounter the black neighbor then my tree is not valid I check the Array of hashsets if there is such vertex
	 * and remove the corresponding roots and tree.
	 *  
	 * Then I again use dfs search from remaining unpainted vertices and paint them RED, if I meet black vertex again I do the 
	 * same thing as before.
	 * 
	 * Piant WHITE all nodes
	 * Propective tree nodes until valid = > BLACK 
	 * other visited nodes paint RED
	 * 
	 * I belive that the time complexity of my algorithm will be O(v+e) as I visit all the edges and vertex exactly once.
	 * I was not sure if it could be done the cheaper way, I couldn't figure out how to use adjacency matrix and if it 
	 * was implemented on  the given graph, the algorithm would be much faster if we could know if there is any incoming edge to given vertices,
	 * i think this would be possible using adjacency matrix but I might be wrong though or there may be other ways to achieve that.
	 */
	float heaviestTree(DiGraph G, LinkedList<Vertex> resultTree) {

		/*
		 * Getting roots from previous quesion
		 */
		Set<Vertex> roots = rootVertices(G);
		LinkedHashSet<Vertex>[] treeArray = new LinkedHashSet[roots.size()];
		Float weight = (float) 0;
		Float[] weightArray = new Float[roots.size()];

		/*
		 * Painting the graph all white
		 */
		for (Vertex v : G.vertices())
			v.setColor(AbstractGraph.WHITE);

		/*
		 * Checking if the vertices originating from the roots are valid tree nodes
		 */

		int i = 0;
		for (Vertex v : roots){

			treeArray[i]= new LinkedHashSet();
			weightArray[i]=(float) 0;
			v.setColor(AbstractGraph.BLACK);		
			treeArray[i].add(v);

			CheckFromInside(v,treeArray, weightArray, i);
			i++;
		}
		
		
		/*If all the roots have invalid trees*/
		
		int check=0;
		for(i = 0; i< treeArray.length; i++){
			if (treeArray[i]==null || treeArray[i].size()<=1){
				check++;
			}
		}

		if(check == i){
			return Float.NaN;
		}
		
		/*
		 * Checking if the vertices have incoming edges from other than valid vertices
		 * Time complexity: O(v+e)
		 */
		for (Vertex v : G.vertices())	
		{
			if(v.getColor()==AbstractGraph.WHITE){
				CheckFromOtherVertex(v,treeArray,weightArray);
			}

		}

		/*
		 * Getting the smallest weight value from the WeightArray
		 * and corresponding HashSet
		 */
		LinkedHashSet<Vertex> Temp= new LinkedHashSet<Vertex>();
		for (int c = 0; c < weightArray.length; c++)
		{

			if (weightArray[c] >weight)
			{
				weight = weightArray[c];
				Temp = treeArray[c]; 
			}
		}

		/*
		 * Adding the Vertices from hashset to resultTree
		 */
		for(Vertex v : Temp){
			if(v!=null)
				resultTree.add(v);
		}
		
		if(resultTree.size()==0){

			return Float.NaN;
		}

		return weight;

	} // heaviestTree()


	/*
	 * Function to check if the tree is valid from inside starting from the root
	 * ie: vertices doesnot have double edge, cross edges, back and forward edge
	 * such vertices are added to corresponding hashset on the array and painted black recursively
	 * if any vertices reaches  the neighbor which is already painted black , we check each hashset for that
	 * neighbor vertex, if found we purge the whole index storing the hashset and set weight to 0. 
	 * Time complexity = < O(v+e)
	 * 
	 */
	public static void CheckFromInside(Vertex v,LinkedHashSet[] treeArray, Float[] weightArray, int index){


		for(Vertex neighbors: v.neighbors()){



			if (neighbors.getColor() == AbstractGraph.WHITE){

				/*If treeArray[index] is null means we purged the tree and the root at that index is 
    			 no longar valid*/


				if(treeArray[index] != null){
					treeArray[index].add(neighbors);
					neighbors.setColor(AbstractGraph.BLACK);
					weightArray[index]=weightArray[index]+ v.getEdge(neighbors).getWeight();  	
					CheckFromInside(neighbors,treeArray,weightArray,index);

				}
				/*else
				{
					neighbors.setColor(DiGraph.RED);	
				}*/

				//CheckFromInside(neighbors,treeArray,weightArray,index); This could be used here if we wan to continue painting 
			}
			else{


				RemoveFromTreeArray(neighbors,treeArray,weightArray);
				RemoveFromTreeArray(v,treeArray,weightArray);


			}


		} 

	}
	/*
	 * Function to check if the vertices stored on the tree are valid from the remaining non painted vertices
	 * ie: tree vertices doesnot have incoming edge from other vertices other than valid ones
	 * other visited vertices are painted red recursively.
	 * if any vertices reaches has the neighbor which is already painted black, we check each hashset for the 
	 * neighbor vertices, if found we purge the whole index storing the hashset and set weight to 0. 
	 * 
	 * Time Complexity = < O(v+e)
	 */
	public static void CheckFromOtherVertex(Vertex v,LinkedHashSet[] treeArray, Float[] weightArray){

		if(v.getColor()==AbstractGraph.WHITE){
			v.setColor(AbstractGraph.RED);
			for(Vertex neighbor: v.neighbors()){
				CheckFromOtherVertex(neighbor,treeArray,weightArray);
			}
		}
		else if(v.getColor() == AbstractGraph.BLACK){
			System.out.println(v);
			RemoveFromTreeArray(v,treeArray,weightArray);
		}


	}
	/*
	 * Function to check  if given vertex exist on the treeArray. If exist we purge the correspinding index and set the 
	 * weight to 0. Time complexity = O(number of RootVertices) < O(v)
	 */
	public static void RemoveFromTreeArray(Vertex v,LinkedHashSet[] treeArray, Float[] weightArray){
		//System.out.println("here"+ v);
		for(int i= 0; i<treeArray.length;i++){

			if(treeArray[i]!= null &&treeArray[i].contains(v)){
				treeArray[i]=null;
				weightArray[i]=(float) 0;

			}

		}

	}


	// Task 14.
	// Set of vertices with no incoming edge
	Set<Vertex> rootVertices(DiGraph g) {

		Set<Vertex> R = new Set<Vertex>();

		HashSet<Vertex> Temp = new HashSet<Vertex>();

		for (Vertex v : g.vertices())
			v.setColor(AbstractGraph.WHITE);

		for (Vertex v : g.vertices()){
			if (v.getColor() != AbstractGraph.BLACK){
				Temp.add(v);
				dfs(v,Temp);
			}

		}

		for(Vertex v: Temp){
			R.add(v);
		}

		return R;
	}   // rootVertices()


	public static void dfs(Vertex V, HashSet<Vertex> temp){
		V.setColor(AbstractGraph.BLACK);
		for (Vertex neighbors : V.neighbors()) {
			if (neighbors.getColor() == AbstractGraph.BLACK) {
				if(temp.contains(neighbors)){
					temp.remove(neighbors);
				}
			}
			else{
				dfs(neighbors,temp);
			}
		}

	}




}   // class
