// Dijkstra.java SJ

import fi.joensuu.cs.tra.*;
import java.util.Random;
import java.util.Vector;

public class Dijkstra {

    public static void main(String[] args) {

        // defaults
        int vertices = 3;
        int edges = 6;

        if (args.length > 0)
            vertices = Integer.valueOf(args[0]);

        if (args.length > 1)
            edges = Integer.valueOf(args[1]);

        int seed = vertices+edges;

        if (args.length > 2)
            seed = Integer.valueOf(args[2]);

        Random r = new Random(seed);

        Dijkstra y = new Dijkstra();

        // create random graph
        DiGraph graph = GraphMaker.createDiGraph(vertices, edges, seed);
        // set weigths for graphs
        for (Edge e : graph.edges())
            e.setWeight((float)(r.nextInt(edges)+1));

        System.out.println("\nGraph:");
        // System.out.println(graph);
        System.out.println(GraphMaker.toString(graph, 1));  // simpler output

        float[] et = y.Dijkstra(graph);
        System.out.print("\nDistances");
        for (int i = 1; i < graph.size(); i++)
            System.out.print(" " + i + ":" + et[i]);
        System.out.println();
        System.out.println(graph);
    }


    float inf = Float.POSITIVE_INFINITY;

    float[] Dijkstra(DiGraph G) {
        // enumerate vertices
        Vertex[] va = GraphMaker.getVertexArrayIndex(G);
        int n = G.size();

        // 0 is starting vertex
        va[0].setWeight((float)0.0);
        va[0].setColor(DiGraph.BLACK);

        // instead of using separate array D, we use vertex weight
        for (int i = 1; i < n; i++) {
            va[i].setWeight(inf);   // default
            va[i].setColor(DiGraph.WHITE);
        }
       
        // direct edges from start vertex
        for (Edge e : va[0].edges()) {
            Vertex w = e.getEndPoint();
            if (e.getWeight() < w.getWeight()) // minimum in multigraphs
                w.setWeight(e.getWeight());
        }

        // priority queue, array of priority queue tree nodes
        AdjustablePriorityQueue<Vertex> Q = new AdjustablePriorityQueue<Vertex>();
        BTreeNode[] pqn = new BTreeNode[n];
        //Vector<BTreeNode<Vertex>> pqn = new Vector<BTreeNode<Vertex>>(n);
        //pqn.setSize(n);
        for (int i = 1; i < n; i++)
            pqn[i] = Q.offer(va[i]);

        // algorithm main loop
        while (! Q.isEmpty()) {
            Vertex v = Q.poll();       // closest remaining vertex
            v.setColor(DiGraph.BLACK);
            for (Edge e : v.edges()) {  // iteration over leaving edges & neighbours
                Vertex w = e.getEndPoint();
                if (w.getColor() != DiGraph.WHITE)  // already processed, skip
                    continue;
                if (w.getWeight() > v.getWeight() + e.getWeight()) {
                    // found shorter route to w
                    w.setWeight(v.getWeight() + e.getWeight());
                    Q.improvePriority(pqn[w.getIndex()]);
                   
                }
            }
        }   // while()

        // create distance array D
        float[] D = new float[n];
        for (int i = 0; i < n; i++)
            D[i] = va[i].getWeight();

        return D;
    }   // Dijkstra()



}   // class
