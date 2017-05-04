import fi.joensuu.cs.tra.*;

import java.util.Collection;
import java.util.LinkedList;
import java.util.HashMap;


public class dsaII_17_22_24_skeleton {

    public static void main(String[] args) {

        // defaults
        int vertices = 10;
        int edges = 12; //10 - to check disconnected graph;

        if (args.length > 0)
            vertices = Integer.valueOf(args[0]);

        if (args.length > 1)
            edges = Integer.valueOf(args[1]);

        int seed = vertices+edges;

        if (args.length > 2)
            seed = Integer.valueOf(args[2]);

        Graph graph;
        dsaII_17_22_24_skeleton y = new dsaII_17_22_24_skeleton();


        System.out.println("\nGraph1: ");
        graph = GraphMaker.createGraph(vertices, edges, seed);
        System.out.println(graph.toString());
        System.out.println(GraphMaker.toString(graph, 0));

        System.out.print("\nConnected: ");
        System.out.println(y.connected(graph));

        if (! y.connected(graph)) {
            System.out.println("Augment to connected:");
            y.augmentConnected(graph);
        }

        if (y.connected(graph)) {
            System.out.print("Articulation vertices:   ");
            Collection<Vertex> cutV = y.cutVertices(graph);

            System.out.println(cutV);

            if (cutV.isEmpty())
                System.out.println("Is already 2-connected");
            else {
                System.out.println("Augment to 2-connected:");
                y.augment2connected(graph);

                System.out.print("Articulation vertices now: ");
                cutV = y.cutVertices(graph);
                System.out.println(cutV);
            }
        }


    } // main()

    /**
      * 22. Write an algorithm that augments an unconnected undirected graph to
      * a connected one using as few added edges as possible. Take skeleton
      * from course www-page. You can use examples and previous exercises as
      * bases. What is the time complexity of your algorithm?
      * @param g the graph to be augmented.
      **/
    void augmentConnected(Graph g) {
    	Vertex temp = null;
    	
    	color(g, AbstractGraph.WHITE);
    	
        // TODO
    	for(Vertex v: g.vertices()){
    		
    		if(v.getColor()!=AbstractGraph.RED){
    			
    			dfsColor(v,AbstractGraph.RED);
    			if(temp != null){
        			temp.addEdge(v);
        		}
        		temp=v;
    			
    		}
    	}
    	System.out.println("\nGraph2: ");
    	System.out.println(GraphMaker.toString(g,0));

    }   // augmentConnected()



    /**
      * 23-24. Write an algorithm that augments an undirected graph to
      * 2-connected by adding edges. You can assume graph to be 1-connected.
      * Use the articulation point algorithm from course www-page as a basis
      * and add edges between (tree) neighbours of cut vertices. The algorithm
      * marks tree edges with black colour. At easiest, modify cutVertices
      * method. Note, that you can repeat search of cut vertices to check that
      * the adds you made were sufficient. No need to get optimal (minimum)
      * amount of added edges, but try to keep amount of added edges
      * reasonable.
      * @param g the graph to be augmented. t
      **/
    void augment2connected(Graph g) {

        // TODO
    color(g,AbstractGraph.WHITE);
    	
    	Vertex[] ver= new Vertex[2]; //ver[0] = cutvertex , ver[1] = leafvertex
		HashMap<Vertex, Integer> visitedTime= new HashMap<Vertex, Integer>(); 
    	HashMap<Vertex, Integer> lowTime= new HashMap<Vertex, Integer>();
    	
		for(Vertex v : g.vertices()){
    		
    		if(v.getColor()!=AbstractGraph.BLACK){	
    			dfss(v,null,visitedTime,lowTime,0,ver);
    			
    		}
    	}
    	
		/*	if(L != null && L.getFirst() != null){
    		LinkedList t = (LinkedList) L.getFirst();
    		temp = ((Edge) t.getLast()).getEndPoint();
    		tempfirst = ((Edge) t.getLast()).getStartPoint();
    		LinkedList tt =I.next();
    		System.out.println(((Edge) tt.getLast()).getStartPoint() +"  "+ ((Edge) tt.getLast()).getEndPoint());
    		
    	}
    	 	
    	while(I.hasNext()){
    		LinkedList tt =I.next();
    		System.out.println(((Edge) tt.getLast()).getStartPoint() +"  "+ ((Edge) tt.getLast()).getEndPoint());
    		Edge tempEdge = ((Edge) tt.getLast());
    		Vertex temp1 = tempEdge.getEndPoint();
    		if(tempfirst != temp1){
    			if(temp1.getEdge(temp)==null){
    				temp1.addEdge(temp);
    				System.out.println(" added  between "+temp1+  " and"+ (temp));}
    			temp=temp1;
    		}
    		tempfirst = tempEdge.getStartPoint();
    	}*/
    }
    
    void dfss(Vertex v, Vertex parent, HashMap<Vertex, Integer> visitedTime, HashMap<Vertex, Integer> lowTime, int i,Vertex[] ver){
    	i++;
    	v.setColor(AbstractGraph.BLACK);
    	
    	visitedTime.put(v,i);
    	lowTime.put(v, i);
    	int child = 0;
    	
    	for(Vertex n: v.neighbors()){
    		if(n!=parent){
    			/*if(v.getEdge(n).getColor()!= DiGraph.RED){
					St.add(v.getEdge(n));
					v.getEdge(n).setColor(DiGraph.RED);
					//System.out.println(v +"  #"+ n+ " =>"+v.getEdge(n).getStartPoint());
					
					
				}*/
    			
    			if(n.getColor()!= AbstractGraph.BLACK){
    				child++;
    				dfss(n,v,visitedTime,lowTime,i,ver);	
    				
    				if(visitedTime.get(v) <= lowTime.get(n) /*|| (parent == null && child >= 2)*/){
    					
    					//System.out.println("cutVertex = " +ver[0] +"n ="+n);
        						if(n != ver[0]){
        							
        							if(ver[1] != null){
        								//System.out.println("Leaf = "+ver[1]);
        								ver[1].addEdge(n);
        							}
        							ver[1] = n;
        						}
        						ver[0] = v;
        						
        					/*	LinkedList<Edge> temp = new LinkedList<Edge>();
        						while(St.getLast()!= v.getEdge(n)){		
        							temp.add(St.pollLast());
        						}	
            					temp.add(St.pollLast());				
            					SubG.add(temp);*/
        				
        				
        			}
    				else{
       					lowTime.put(v, Math.min( lowTime.get(n),lowTime.get(v)));
    				}
    			}
    			else{
    				
    				lowTime.put(v, Math.min(visitedTime.get(n),lowTime.get(v)));
    			}
    		}
    		
    	}
    	
    	
    	
    	
    	
    }
   /* public LinkedList<LinkedList> getSubgraphs(Graph g)
    {
     	LinkedList<Edge> St = new LinkedList<Edge>();
     	LinkedList<LinkedList> subG = new LinkedList();
    	HashMap<Vertex, Integer> visitedTime= new HashMap<Vertex, Integer>(); 
    	HashMap<Vertex, Integer> lowTime= new HashMap<Vertex, Integer>();
    	LinkedList<Edge> temp=new LinkedList<Edge>();
    	for(Vertex v : g.vertices()){
    		
    		if(v.getColor()!=DiGraph.BLACK){	
    			dfss(v,null,visitedTime,lowTime,St,0,subG);
    			
    		}
    		
    		while (St.size() > 0)
            {
                temp.add(St.pollLast());
            }	
    	}
    	subG.add(temp);
    	/*Iterator I = subG.iterator();
    	while(I.hasNext()){
    		System.out.println(I.next());
    	}
    	
    	return subG;
    }
    */
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /*Set<Vertex> articulationVertices(Graph g){
    	Set<Vertex> S = new Set<Vertex>();
    	HashMap<Vertex, Integer> visitedTime= new HashMap<Vertex, Integer>(); 
    	HashMap<Vertex, Integer> lowTime= new HashMap<Vertex, Integer>();
    	for(Vertex v : g.vertices()){
    		
    		if(v.getColor()!=DiGraph.BLACK){
    			
    			dfs(v,null,visitedTime,lowTime,S,0);
    		}
    	}
    	
    	//System.out.println(S);
    	return S;
    	
    }
    
    void dfs(Vertex v, Vertex parent, HashMap<Vertex, Integer> visitedTime, HashMap<Vertex, Integer> lowTime, Set<Vertex> S, int i){
    	i++;
    	v.setColor(DiGraph.BLACK);
    	visitedTime.put(v,i);
    	lowTime.put(v, i);
    	int child = 0;
    	
    	for(Vertex n: v.neighbors()){
    		if(n!=parent){
    			
    			if(n.getColor()!= DiGraph.BLACK){
    				child++;
    			
    				
    				dfs(n,v,visitedTime,lowTime,S,i);	
    				
    				if((int)visitedTime.get(v) <= (int)lowTime.get(n)){
        				if(parent != null){
        					S.add(v);
        				}
        				
        			}
    				else{
    					lowTime.put(v, (int)lowTime.get(n));
    				}
    			}
    			else{
    				lowTime.put(v, Math.min((int)visitedTime.get(n),(int)lowTime.get(v)));
    			}
    		}
    		
    	}
    	
    	if(child >= 2 && parent == null){
    		S.add(v);
    	}
    	
  
    }*/
    


    // help/test methods

    // use these as skeletons for tasks 22 and 23-24.

    // checks if graph g is connected (1 component) or not
    boolean connected(Graph g) {
        // all to white
        color(g, AbstractGraph.WHITE);

        // depth first search from some vertex
        Vertex w = g.firstVertex(); // or g.vertices().getFirst();
        dfsColor(w, AbstractGraph.BLACK);

        // if some vertices were unreached -> not connected
        for (Vertex v : g.vertices())
            if (v.getColor() == AbstractGraph.WHITE)
                return false;
        return true;
    }

    // dfs with color col
    void dfsColor(Vertex v, int col) {
        v.setColor(col);

        for (Vertex w : v.neighbors())
            if (w.getColor() != col)
                dfsColor(w, col);
    }

    // vertices of graph to color c
    void color(AbstractGraph g, int c) {
        for (Vertex v : g.vertices())
            v.setColor(c);
    }



    // articulation points (vertices) from connected undirected graph
    LinkedList<Vertex> cutVertices(Graph g) {

        Vertex[] vA = GraphMaker.getVertexArrayIndex(g);
        int n = g.size();

        color(g, AbstractGraph.WHITE);
        for (Edge e : g.edges())
            e.setColor(AbstractGraph.WHITE);

        int[] dfsnumber = new int[n];
        int[] low = new int[n];
        int i = 0;
        LinkedList<Vertex> L = new LinkedList<Vertex>();
        for (Vertex v : g.vertices())
            if (v.getColor() == AbstractGraph.WHITE)
                i = numberdfs(v, dfsnumber, low, i, L, null);

        return L;
    }

    // dfs-numbering to an array
    // classify edges: tree edges black, back edges gray
    int numberdfs(Vertex v, int[] dfsnumber, int[] low, int i,
                  LinkedList<Vertex> L, Vertex parent) {

        // pre-order numbering
        v.setColor(AbstractGraph.BLACK);
        dfsnumber[v.getIndex()] = i++;

        // dfs recursion and classification of edges
        for (Edge e : v.edges()) {

            // edge already processed in other direction
            // (parent-edge, or back edge of a descendant)
            if (e.getColor() != AbstractGraph.WHITE)
                continue;

            Vertex w = e.getEndPoint(v);

            if (w.getColor() == AbstractGraph.WHITE) {
                e.setColor(AbstractGraph.BLACK);    // tree edge
                i = numberdfs(w, dfsnumber, low, i, L, v);
            } else if (w.getColor() == AbstractGraph.BLACK)
                e.setColor(AbstractGraph.GRAY); // back edge
        }

        // calculation low number (post-order)
        int min = dfsnumber[v.getIndex()];
        for (Edge e : v.edges()) {
            Vertex w = e.getEndPoint(v);
            if (w == parent)   // ignore parent
               continue;

            // children low numbers
            if (e.getColor() == AbstractGraph.BLACK) {
                int childLow = low[w.getIndex()];
                if (childLow < min)
                    min = childLow;

                // dfsnumbers of predecessors with back edge
            } else if (e.getColor() == AbstractGraph.GRAY) {
                int predecessorDfsn = dfsnumber[w.getIndex()];
                if (predecessorDfsn < min)
                    min = predecessorDfsn;
            } // no whites at all

        }
        low[v.getIndex()] = min;

        // recognizing cut vertices
        if (v.getIndex() == 0) { // root vertex
            int childnumber = 0;
            for (Edge e : v.edges())
                if (e.getColor() == AbstractGraph.BLACK)
                    childnumber++;
            if (childnumber > 1)
                L.add(v);

            // other vertices
        } else {
            for (Edge e : v.edges())
                if (e.getColor() == AbstractGraph.BLACK) {
                    Vertex w = e.getEndPoint(v);
                    if (low[w.getIndex()] >= dfsnumber[v.getIndex()]) {
                        L.add(v);
                        break;
                    }
                } // if BLACK
        } // else
        return i;
    }





}