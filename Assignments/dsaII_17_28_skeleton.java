// dsaII_17_28_skeleton.java SJ

import fi.joensuu.cs.tra.*;
import java.lang.RuntimeException;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;


public class dsaII_17_28_skeleton {

    public static void main(String[] args) {

        // DiGraph graph = GraphMaker.createDiGraph(vertices, edges, rseed);
        DiGraph graph = Q1();

        System.out.println(GraphMaker.toString(graph, 1));

        Vertex dst = null;
        for (Vertex v : graph.vertices())
            if (v.getLabel().equals("0"))
                dst = v;
        if (dst == null)
            return;


        // test quorum algorithm
        Set<Vertex> q = quorum(graph, dst, 0.5F);
        System.out.println("\nCompanies that are under quorum of " + dst + " : " + q);

    }   // main() 


    /**
      * 28 Set of vertices under quorum.
      * A company x has quorum (decision making power) in company y if and only
      * if there exists companies z1,z2,...,zk that are under quorum of
      * compary x, and companies x,z1,z2,...,zk combined own over 50% of
      * shares of company y. Such information is needed in, e.g., co-operation
      * negotiations and other legal situations. We model the ownership
      * relations between companies by a graph, where each company is a vertex,
      * and for each company x owning r% of shares company y, there is an edge
      * (x,y) with weight r. Sketch an algorithm (pseudocode) that finds all
      * those companies that are under quorum of company x.
      *
      * @param g graph of owning stocks
      * @param v the company under inspection
      * @param limit required limit of owning (e.g., 0.5)
      * @return the set of companies under quorum of v. Including v.
      **/
    static Set<Vertex> quorum(DiGraph g, Vertex v, float limit) {

        // TODO

        // Pseudocode is enough, but actual implementation is then very easy and 
        // very good example, thus feel free to implement your solution here
        // as well. I'll show mine at exercise class.
        // Regards, Simo

        
        Set<Vertex> S = new HashSet<Vertex>();

        // TODO
        Vertex[] vertices = GraphMaker.getVertexArrayIndex(g);
        float[] shares = new float[g.size()];
        dfs(vertices,shares,v,limit,S);

        return S;
    }
    
    static void dfs(Vertex[] vertices, float[] shares, Vertex v, float limit,Set<Vertex> S){
    	for (Vertex n :v.neighbors()){
    		Edge e = v.getEdge(n);
    		shares[n.getIndex()] = shares[n.getIndex()] + e.getWeight();
    		
    		if(! S.contains(n) && shares[n.getIndex()] > limit){
    			S.add(n);
    			dfs(vertices,shares,n,limit,S);	
    		}
    	}
    	
    	
    }


    // example graph
    // for company "0" and limit:
    //  0.5, the result should be (0,) 1, 2, 3, 4
    //  0.6, the result should be (0,) 2
    //  0.39, the result should be (0,) 1, 2, 3, 4, 5, 6
    static DiGraph Q1() {

        int n = 7;
        DiGraph g = new DiGraph();
        Vertex[] va = new Vertex[n];
        for (int i = 0; i < n; i++) 
            va[i] = g.addVertex(""+i);

        va[0].addEdge(va[1], 0.3F);
        va[0].addEdge(va[2], 0.7F);
        va[0].addEdge(va[4], 0.2F);
        va[1].addEdge(va[3], 0.2F);
        va[2].addEdge(va[1], 0.3F);
        va[2].addEdge(va[3], 0.6F);
        va[3].addEdge(va[4], 0.4F);
        va[3].addEdge(va[5], 0.4F);
        va[3].addEdge(va[6], 0.2F);
        va[4].addEdge(va[6], 0.2F);
        va[6].addEdge(va[5], 0.2F);

        return g;

    }

}