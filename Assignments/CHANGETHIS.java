// skeleton for X2

import fi.joensuu.cs.tra.*;
import java.util.LinkedList;


//       change class name and file name to your username, all lower case
//           ||||||||||
//           vvvvvvvvvv
public class CHANGETHIS {

    // no main program here this time at all
    // in this class there will be only the algorithm for X2
    // and possible help methods

    // I'll put a separate test program that calls this class

    // submit only this file

    // do NOT use package in the final version you submit

    /**
      * X2 Cycle with given vertex.
      *
      * Write an algorithm that returns a cycle of an undirected graph that
      * contains a given vertex. Parameters are a graph and vertex v, and the
      * return value is a list of vertices (LinkedList<Vertex>) of one cycle
      * that contains vertex v, or null if there is no such cycle.  The
      * elements in the list must be in order of the cycle. The vertex v must thus
      * be the first and the last element of the list. What is the time
      * complexity of your algorithm?
      *
      * You can make more methods, but DO NOT CHANGE the header of this method.
      *
      * @param G the input undirected graph
      * @param v the vertex that is required to be part of the cycle
      * @return vertices of one cycle that contains v, or null of no cycle exists
      **/
	
	/*
	 * I used depth first search to solve the problem.
	 * First I colored the graph white  
	 * I added each visited element  in the linked list and
	 * set it black except the given(which is first one on the list)
	 * if current element is equal to given element and size of the list >1  then we have detected the cycle 
	 * then we stop looking and stop iterating over neighbors else we continue at the end of each recursion tree 
	 * if the last element added on the list is not equal to given then we remove it from the  list.
	 * This algorithm will not work in all cases if the graph is directed graph.
	 * 
	 *  It could be done similarly by dfs over the edges (efficient method If it is a directed graph). 
	 * 
	 * It would be more efficient if we could be able to return the value to the main method and end the recursion abruptly
	 * after we detect the cycle rather than going through remaining neighbors.(I think I somehow fixed it) I had trouble 
	 * figuring that out, also I stumbled quite a long time as I was able to print the cycle when there was a match but 
	 * the already running recursion modified my list (even though i had the match).
	 * 
	 * The time complexity of my algorithm will be < O(v+e) as I visit only some vertices+edges until we find the cycle.
	 * 
	 */
    public LinkedList<Vertex> cycleWithVertex(Graph G, Vertex v) {

        // TODO
    	LinkedList<Vertex> L = new LinkedList<Vertex>();
    	for(Vertex v1: G.vertices()){
    		v1.setColor(AbstractGraph.WHITE);
    	}
    	
    	//L.add(v);
    	dfsCyclic(v,null,L);
    
    	/*
    	 * If the linked list is of size one than there is no cycle 
    	 * the only node on the list list will be the one we are checking for
    	 */
    	
    	if (L.size() != 1)
    		return L;
    	return null;
    	}
    
    
    
    public void dfsCyclic(Vertex current, Vertex parent,LinkedList<Vertex> L){
    	
    	Vertex temp = current;
    	L.add(current);
    	
    	
    	/*
    	 * If we have not detected a cycle then continue
    	 */
    	if(!(current == L.getFirst() && L.size()>1)){  	
    		for(Vertex v: temp.neighbors()){
    			/*
    			 * If we have found the cycle already stop iterating over neighbors
    			 */   
    			if(L.getLast() == L.getFirst() && L.size()>1){
					break;
				}
    			/*
    			 * else continue looking at non visited neighbors
    			 */
    			if(v.getColor() != AbstractGraph.BLACK && v!= parent){			
    				
    				v.setColor(AbstractGraph.BLACK);
	        		
    				dfsCyclic(v,temp,L);		
	        		/*
	    			 * if the last added element is not equal to given element remove it from the list
	    			 */
        			if(L.getLast()!=L.getFirst()){
        				L.removeLast();
        			}
    				
    			
    			}
    		
    		}
    						
    	}
    		
    }
   
        
    } // cycleWithVertex()
   // class
