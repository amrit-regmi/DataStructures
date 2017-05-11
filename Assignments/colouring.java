/* randomized and raw power colouring SJ
/*
 * Write a randomized algorithm that colours given undirected graph with as few colours as
possible so that no neighbouring vertices have the same colour. Minimal colouring is NP-hard,
but now we make an algorithm that finds a reasonably good solution in reasonable time. Use
greedy algorithm (from course www-page) and repeat it given time (e.g., 10 seconds) using
different random iteration orders. The greedy colouring algorithm uses single colour to colour
as many vertices as possible, and it takes a new colour if some vertices are still uncoloured.
This is then repeated using several orders of iteration. The best found colouring is returned
as the result of the algorithm. You can use method Permutation.randomPermutation() from
course www-page as a helper. Collect all vertices to an array (GraphMaker.getVertexArray())
and iterate the array in order of a permutation.

26. Modify the algorithm of task 25 to find the minimal colouring. Minimal colouring can be
found by trying all possible iteration orders. You can generate all permutations with class
Permutation in course www-page. What is the time complexity of the solution? Calculate
(extrapolate) running time in seconds (etc.) when n=20, e=70.

 */

import fi.joensuu.cs.tra.*;
import java.lang.Iterable;
import java.util.Iterator;

public class colouring {

    public static void main(String[] args) {

        // traj dsaII_17_25_26_skeleton 6 9 17

        // parameters
        int vertexCount = 6;
        int edgeCount = 9;
        int randTime = 3*1000; // how many seconds to try

        if (args.length > 0)
            vertexCount = Integer.valueOf(args[0]);

        if (args.length > 1)
            edgeCount = Integer.valueOf(args[1]);

        int seed = vertexCount+edgeCount+2;

        if (args.length > 2)
            seed = Integer.valueOf(args[2]);

        if (args.length > 3)
            randTime = Integer.valueOf(args[3]);

        Graph G;
        colouring y = new colouring();

        // random graph

        System.out.println("\nGraph1: ");
        G = GraphMaker.createGraph(vertexCount, edgeCount, seed);
        System.out.println(GraphMaker.toString(G, 0));

        // test
        System.out.println("Greedy colouring: " + y.greedyColouring(G));
        System.out.println("Check = " + checkColouring(G));
        // System.out.println(G);


        System.out.println("Randomized colouring (" + randTime + "ms): "
                           + y.randColouringTime(G, randTime));
        System.out.println("Check = " + checkColouring(G));
        // System.out.println(G);

        if (vertexCount <= 15) {
            DsaTimer a = new DsaTimer("optimal " + vertexCount);
            System.out.println("Optimal: " + y.optimalColouring(G));
            a.stop();
            System.out.println("Check = " + checkColouring(G));
            System.out.println(a);
            // System.out.println(G);
        }

        // create bipartite graph, where we should be able to find
        // 2-colouring
        System.out.println("\nbipartite graph: ");
        G = GraphMaker.createBiPartie(vertexCount, vertexCount, edgeCount, seed);
        System.out.println(GraphMaker.toString(G, 0));

        System.out.println("Greedy colouring: " + y.greedyColouring(G));
        System.out.println("Check = " + checkColouring(G));
        System.out.println(G);

        System.out.println("#####");
        System.out.println("Randomized colouring (" + randTime + "ms): "
                           + y.randColouringTime(G, randTime));
        System.out.println("Check = " + checkColouring(G));
        System.out.println(G);

        if (vertexCount <= 5) {
            System.out.println("Optimal: " + y.optimalColouring(G));
            System.out.println("Check = " + checkColouring(G));
            System.out.println(G);
        }

    } // main()

    /**
      * Check validity of colouring.
      * All vertices should have positive colours, and no neigbouring vertices
      * may have the same colour.
      * @param G Parameters to check.
      * @return largest colour used, or -1 if colouring is invalid.
      **/
    static int checkColouring(Graph G) {
        int max = 0;
        for (Vertex v : G.vertices()) {
            if (v.getColor() > max)
                max = v.getColor();
            else if (v.getColor() < 1)
                return -1;
            for (Vertex w : v.neighbors())
                if (v.getColor() == w.getColor())
                    return -1;
        }
        return max;
    }



    /**
     * Greedy colouring of graph.
     * @param G graph to colour
     * @return the number of colours used
     **/
    int greedyColouring(Graph G) {
        int n = 0;  // number of vertices

        // count vertices and initialize colours with 0
        for (Vertex v : G.vertices()) {
            v.setColor(0);
            n++;
        }

        int i = 0; // # of coloured vertices
        int c = 0; // current colour

        // repeat until all vertices coloured
        while (i < n) {
            c++; // new colour

            // try to colour with colour c as many vertives as possible
            for (Vertex v : G.vertices()) {

                if (v.getColor() == 0) {
                    // still uncoloured vertex, check if
                    // colour c is used in neighbours
                    boolean okColor = true;
                    for (Vertex w : v.neighbors()) {
                        if (w.getColor() == c) {
                            okColor = false;
                            break;
                        }
                    }

                    // if c was not found, colour v with c
                    if (okColor) {
                        v.setColor(c);
                        i++;
                    }
                } // if (v.getColor)
            }   // for
        } // while


        return c;

    } // greedyColouring()



    /**
     * Randomized Monte Carlo -colouring.
     * Repeats randomized colouring until time is up.
     * @param G graph to colour
     * @param ms time (in milliseconds) to use
     * @return the number of colours used
     **/
    int randColouringTime(Graph G, long ms) {

        long terminateTime = System.nanoTime() + ms * 1000*1000;

        // hints:
        // make a variant of greedyColouring() which takes a permutation as
        // parameter and makes greedy colouring in order of that permutation
        // remember the permuation of the best result
        int n= G.size();
        Permutation P = new Permutation(n);
        int numberOfcolor = G.size();
        int[] temp = null;
        int color = 0;
        //int[] bestResult = new int[G.size()];
       
        while (System.nanoTime() < terminateTime){
	            	temp = P.randomPermutation();
	            	
	            	color = greedyColouring_helper(G,temp);
	            	if( color < numberOfcolor){
	            		numberOfcolor = color;
	            	}
	            	
        } 

        return numberOfcolor;

    } // randColouring()
    
 

    /**
     * Optimal colouring.
     * Repeats randomized colouring for all orderings (permutations) of vertices.
     * @param G graph to colour
     * @return the number of colours used
     **/
    int optimalColouring(Graph G) {

        // TODO
        int n = G.size();
        Permutation P = new Permutation(n);
        int numberOfcolor = n;
        // iterate over all permutations
        for (int[] perm : P) {
        	
       	 	int color = greedyColouring_helper(G,perm);
       	 	if(color<numberOfcolor){
       	 		numberOfcolor = color;
       	 	} 
        }

        return numberOfcolor;

    } // optimalColouring()


   int greedyColouring_helper(Graph G, int[] permutation) {
    	
        // count vertices and initialize colours with 0
        for (Vertex v : G.vertices()) {
            v.setColor(0);  
        }
        
        int colorCount = 1; // min colour count
    	
    	Vertex[] graph = GraphMaker.getVertexArray(G);
    	
    	for(int in = 0; in<permutation.length; in++){
    			int color = 1;
                boolean okColor = false;
                while (okColor != true){
                	for (Vertex w : graph[in].neighbors()) {
                        if (w.getColor() == color) { 
                            okColor = false;
                            color++; //if colour match try another colour
                            break;
                        }
                        graph[in].setColor(color);
                        okColor = true;
                    }
                	
                }
                if(color>colorCount){
                	colorCount = color;
                }
    		
    	}

        return colorCount;

    }


}
