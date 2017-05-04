import fi.joensuu.cs.tra.*;

import java.util.ListIterator;
import java.util.LinkedList;


public class dsaII_17_X2_test {

    public static void main(String[] args) {

        // defaults
        int vertices =54;
        int edges = 76;

        if (args.length > 0)
            vertices = Integer.valueOf(args[0]);

        if (args.length > 1)
            edges = Integer.valueOf(args[1]);

        int seed = vertices+edges;

        if (args.length > 2)
            seed = Integer.valueOf(args[2]);

        int print = 0;
        if (args.length > 3)
            print = Integer.valueOf(args[3]);


        System.out.println("\nGraph1: ");
        Graph graph = GraphMaker.createGraph(vertices, edges, seed);
       /* Vertex temp=null;
        for(Vertex v: graph.vertices()){
        	if(v.getLabel().compareTo("4")==0){
        		System.out.println("yes");
        		temp =v;
            }
        	if(v.getLabel().compareTo("6")==0){
        		System.out.println("yes   yes");
        		temp.removeEdge(v.getEdge(temp));
        		
        	}
        
        }*/
        // System.out.println(graph.toString());
        System.out.println(GraphMaker.toString(graph, 0));

        // create instance of the class containing X2 algorithm

        // use your own class name here
        // dsaII_17_X2 inst = new dsaII_17_X2();
        amritregX2dsa2 inst = new amritregX2dsa2();

        boolean allOk = true;
        // for each vertex v, find a cycle containing vertex v
        for (Vertex v : graph.vertices()) {

            LinkedList<Vertex> cycle = inst.cycleWithVertex(graph, v);
            System.out.println("cycle with vertex " + v + ": " + cycle);

            // call test.
            // note: the test can only check if the found cycle is a valid one
            // it cannot know if there is such cycle in graph if your cycle is null
            if (cycle != null)
                allOk &= isCorrectCycle(v, cycle, print); // use 1 or 2 to see more

        }

        if (allOk)
            System.out.println("No invalid cycles");
        else
            System.out.println("Some invalid cycles");

    } // main()


    /**
      * Cycle checker method.
      * Check if given cycle is valid and contains vertex v.
      * @param v the required vertex
      * @param cycle the cycle to be checked
      * @param print amount of printing: 0 = nothing, 1 = errors, 2 = even more
      * @return if the cycle was corect or not
      **/
    static boolean isCorrectCycle(Vertex v, LinkedList<Vertex> cycle, int print) {

        boolean ret = true;

        if (print > 1)
            System.out.println("Checking cycle with vertex " + v + ": " + cycle);

        // check for invalid parameters
        if (v == null || cycle == null || cycle.isEmpty()) {
            if (print > 0)
                System.out.println("null or empty parameter, no check");
            return false;
        }

        // check for too short cycles, 4 is minimum (v-x-y-v)
        if (cycle.size() < 4) {
            if (print > 0)
                System.out.println("too short cycle: " + cycle.size());
            ret = false;
        }

        // check for correct first and last in cycle
        if (cycle.getFirst() != v) {
            if (print > 0)
                System.out.println("Wrong first vertex " + cycle.getFirst());
            ret = false;
        }
        if (cycle.getLast() != v) {
            if (print > 0)
                System.out.println("Wrong last vertex " + cycle.getLast());
            ret = false;
        }

        // extra: check if first and last are different (other than v)

        // check that all successive vertices in list are adjacent in graph

        ListIterator<Vertex> li = cycle.listIterator();
        Vertex prev = null;
        while (li.hasNext()) {
            Vertex w = li.next();
            if (prev != null && ! prev.isAdjacent(w)) {
                if (print > 0)
                    System.out.println("No edge between " + prev + " and " + w);
                ret = false;
            }
            // check that v is not in the middle of cycle
            if (prev != null && li.hasNext() && w == v) {
                if (print > 0)
                    System.out.println("Required vertex " + v + " in the middle of cycle");
                ret = false;
            }

            prev = w;
        } // while

        if (print > 1 && ret)
            System.out.println("Valid cycle");

        return ret;

    }


}