import fi.joensuu.cs.tra.*;

import java.util.HashSet;
import java.util.Set;
import java.util.LinkedList;

public class dsaII_17_11_12_skeleton {

	public static void main(String[] args) {

		// defaults
		int vertices = 5;
		int edges = 7;

		if (args.length > 0)
			vertices = Integer.valueOf(args[0]);

		if (args.length > 1)
			edges = Integer.valueOf(args[1]);

		int seed = vertices + edges;

		if (args.length > 2)
			seed = Integer.valueOf(args[2]);

		dsaII_17_11_12_skeleton y = new dsaII_17_11_12_skeleton();

		// Create random graph
		DiGraph graph = GraphMaker.createDiGraph(vertices, edges, seed);
		System.out.println("\nGraph:");
		System.out.println(graph.toString());

		// test 11
		// find all reachable vertices from all vertices
		// at same time, take two vertex references
		System.out.println("\nReachable:");
		Vertex v1 = null;
		Vertex v2 = null;
		for (Vertex v : graph.vertices()) {
			System.out.println(v + " : " + y.reachable(graph, v));
			if (v1 == null)
				v1 = v;
			else if (v2 == null)
				v2 = v;
		}

		// test 12
		System.out.println("v1=" + v1 + " v2=" + v2);
		System.out.println("Is path: " + y.isPath(graph, v1, v2));
		System.out.println("One path: " + y.isPath12(graph, v1, v2));

	} // main()

	/**
	 * 11. Write an algorithm that finds all the vertices that are reachable
	 * from a given vertex. Parameters are a graph and a vertex, and return
	 * value is a set of vertices. Hint: depth first search. What is the time
	 * complexity of your algorithm?
	 * 
	 * @param G
	 *            the graph
	 * @param start
	 *            the vertex of which followers are searched
	 * @return set of vertices that can be reached from start.
	 **/
	Set<Vertex> reachable(DiGraph G, Vertex start) {
		Set<Vertex> s = new HashSet<Vertex>();
		// TODO
		recahable_helper(start, s);
		return s;
	}

	public static void recahable_helper(Vertex v, Set<Vertex> s) {
		for (Vertex neighborVertex : v.neighbors()) {
			recahable_helper(neighborVertex, s);
			s.add(neighborVertex);
		}
	}

	/**
	 * 12. Write an algorithm that finds any path (list of vertices) between
	 * given two vertices in a directed graph. Do not use Dijkstra algorithm,
	 * but depth first search. What is the time complexity of your algorithm?
	 * 
	 * @param G
	 *            the graph
	 * @param start
	 *            source of the path
	 * @param end
	 *            destination of the path
	 * @return list of vertices in the path from start to end, or null if there
	 *         is no path.
	 **/
	public LinkedList<Vertex> isPath12(DiGraph G, Vertex start, Vertex end) {

		color(G, AbstractGraph.WHITE);
		LinkedList<Vertex> L = new LinkedList<Vertex>();
		getPath(start, end, L);

		L.removeFirst();
		L.removeLast();

		return L;

	}

	public static boolean getPath(Vertex start, Vertex end, LinkedList<Vertex> L) {

		start.setColor(AbstractGraph.GRAY);
		L.add(start);
		if (start == end) {
			return true;
		}

		for (Vertex neighbors : start.neighbors()) {
			if (neighbors.getColor() == AbstractGraph.WHITE) {
				neighbors.setColor(AbstractGraph.GRAY);
				if (getPath(neighbors, end, L)) {
					return true;
				}
			}
		}

		L.removeLast();
		return false;
	}

	// ---- examples ----

	// depth first search/traversal/colouring with colour c
	void dfsColor(Vertex vertex, int color) {
		vertex.setColor(color);
		for (Vertex neighborVertex : vertex.neighbors())
			if (neighborVertex.getColor() != color)
				dfsColor(neighborVertex, color);

	}

	// set colour of all vertices to c
	void color(AbstractGraph g, int c) {
		for (Vertex v : g.vertices())
			v.setColor(c);
	}

	// is there a path between two vertices or not

	public boolean isPath(DiGraph g, Vertex start, Vertex end) {
		color(g, AbstractGraph.WHITE);
		return pathDfs(start, end);
	}

	// recursive part
	boolean pathDfs(Vertex start, Vertex end) {

		// mark vertex processed
		start.setColor(AbstractGraph.GRAY);

		// iterate over neighbours
		for (Vertex w : start.neighbors()) {
			// if destination is found, return true
			if (w == end)
				return true;

			// check only unprocessed neighbours
			else if (w.getColor() == AbstractGraph.WHITE) {

				// recursively call the neighbour
				// and notice the result
				if (pathDfs(w, end))
					return true;
			}
		}

		// path not found here
		return false;
	} // pathDfs()

} // class
