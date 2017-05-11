
import fi.joensuu.cs.tra.*;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.HashMap;


public class MaximalMatching {

    static boolean print = true;

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

        if (vertices > 30 || args.length > 2)
            print = false;

        Graph graph;
        MaximalMatching y = new MaximalMatching();



        System.out.println("\nCreate bipartite graph: ");
        graph = GraphMaker.createBiPartie(vertices, vertices, edges, seed);

        LinkedList<Edge> match = y.maximalMatching(graph, vertices);
        System.out.println("Matching: " + match);
        if (match != null) System.out.println("Matching: " + match.size());

    }


    // Maximal matching of bipartite graph
    LinkedList<Edge> maximalMatching(Graph g) {
        Vertex[] V1 = biPartite(g);
        if (V1 == null)
            return null;

        return maximalMatching(g, V1);
    }


    // returns an array of vertices that are in first subset
    // of vertices in bipartite graph
    Vertex[] biPartite(Graph g) {
        boolean ok = isBiPartite(g);
        if (! ok)
            return null;
        int n1 = 0;
        for (Vertex v : g.vertices())
            if (v.getColor() == 0)
                n1++;
        Vertex[] V1 = new Vertex[n1];
        int i = 0;
        for (Vertex v : g.vertices())
            if (v.getColor() == 0)
                V1[i++] = v;

        return V1;
    }


    // Maximal matching of bipartite graph
    // requires v1 first vertices to be the first subset
    LinkedList<Edge> maximalMatching(Graph g, int v1) {
        Vertex[] V1 = new Vertex[v1];
        int i = 0;
        for (Vertex v : g.vertices()) {
            V1[i++] = v;
            if (i == v1)
                break;
        }

        return maximalMatching(g, V1);
    }



    // Maximal matching of bipartite grap
    // V1 are the first subset
    // Vertex.color is used in dfs
    // Vertex.index is used to sign selected vertices
    LinkedList<Edge> maximalMatching(Graph g, Vertex[] V1) {

        for (Vertex v : g.vertices())
            v.setIndex(0);
        
        for (Edge e : g.edges())
            e.setColor(0);

        LinkedList<Edge> p;
       
        while (true) {
            p = augmentingPath(g, V1);
            if (p == null)
                break;

            if (print) System.out.println("Augmenting path: " + p);

            for (Edge e : p) {
                e.setColor(e.getColor() ^ 1);
                e.getEndPoint().setIndex(1);
                e.getStartPoint().setIndex(1);
            }
        }

        p = new LinkedList<Edge>();
        for (Edge e : g.edges())
            if (e.getColor() == 1)
                p.add(e);

        return p;
    }   // maximalMatching()

    LinkedList<Edge> augmentingPath(Graph g, Vertex[] V1) {
        int n1 = V1.length;

        LinkedList<Edge> p = new LinkedList<Edge>();

        for (int i = 0; i < n1; i++) {
            if (V1[i].getIndex() == 0 && augmentingPath2(g, V1[i], p))
                return p;
        }

        return null;

    }

    boolean augmentingPath(Graph g, Vertex v, LinkedList<Edge> p) {

        color(g, 0);
        p.clear();

        return augmentingPath_r(v, p, 0);
    }

    boolean augmentingPath_r(Vertex v, LinkedList<Edge> p, int level) {

        if (level%2 == 1 && v.getIndex() == 0)
            return true;

        v.setColor(1);
        for (Edge e : v.edges()) {

            // every outher edge not in matching
            if (e.getColor() != level%2)
                continue;

            // not back using same edge
            Vertex w = e.getEndPoint(v);
            if (w.getColor() != 0)
                continue;

            p.addLast(e);

            if (augmentingPath_r(w, p, level+1))
                return true;

            p.removeLast();     // not found
        }

        return false;
    } // augmentingPath_r()

    // BDF version
    boolean augmentingPath2(Graph g, Vertex v, LinkedList<Edge> p) {

        p.clear();

        // for each open search, we have HashMap<Vertex, Integer>
        // Vertices are vertices of path, Integer is distance from start of path
        // (needed to reconstruct the path at the end)
        LinkedQueue<HashMap<Vertex, Integer>> PQ = new LinkedQueue<HashMap<Vertex, Integer>>();
        LinkedQueue<Vertex> Q = new LinkedQueue<Vertex>();

        HashMap<Vertex, Integer> h = new HashMap<Vertex, Integer>();
        h.put(v, 0);
        PQ.offer(h);
        h = null;
        Q.offer(v);
        Q.offer(null);

        int level = 0;

        while (!Q.isEmpty()) {

            v = Q.poll();

            if (v == null) {
                if (Q.isEmpty())
                    return false;
                level++;
                Q.offer(null);
                continue;
            }

            h = PQ.poll();

            if (level%2 == 1 && v.getIndex() == 0)
                break;

            for (Edge e : v.edges()) {

                // every other edge not matched
                if (e.getColor() != level%2)
                    continue;

                // not back using same edge
                Vertex w = e.getEndPoint(v);
                if (h.get(w) != null)
                    continue;

                // New branch, put to queue
                HashMap<Vertex, Integer> h2 = new HashMap<Vertex, Integer>(h);
                h2.put(w, level+1);
                Q.offer(w);
                PQ.offer(h2);

            }

            h = null;

        } // while !isEmpty

        if (h == null)
            return false;

        // build edge path using vertex-location -mapping
        int n = h.size();
        Vertex[] path = new Vertex[n];
        for (Vertex w : h.keySet())
            path[h.get(w)] = w;

        for (int i = 0; i < n-1; i++)
            p.add(path[i].getEdge(path[i+1]));

        return true;
    }   // augmentingPath2()



    // check if graph is bipartite, colour with two colours
    boolean isBiPartite(Graph g) {

        color(g, 2);

        for (Vertex v : g.vertices())
            if (v.getColor() == 2)
                if (! dfs2color(v, 0))
                    return false;

        return true;
    }

    boolean dfs2color(Vertex v, int c) {
        v.setColor(c);

        for (Vertex w : v.neighbors()) {

            if (w.getColor() == c)
                return false;
            else if (w.getColor() == 2) {
                if (! dfs2color(w, (c+1)%2))
                    return false;
            }
        }

        return true;
    }

    // vertices to color c
    void color(AbstractGraph g, int c) {
        for (Vertex v : g.vertices())
            v.setColor(c);
    }



}