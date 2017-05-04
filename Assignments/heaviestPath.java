import java.util.Collection;

import fi.joensuu.cs.tra.*;

public class heaviestPath {
public static void main(String[] args) {
	int vertices = 5;
	int edges = 7;

	if (args.length > 0)
		vertices = Integer.valueOf(args[0]);

	if (args.length > 1)
		edges = Integer.valueOf(args[1]);

	int seed = vertices + edges;

	if (args.length > 2)
		seed = Integer.valueOf(args[2]);

	heaviestPath y = new heaviestPath();

	// Create random graph
	DiGraph graph = GraphMaker.createDiGraph(vertices, edges, seed);
	GraphMaker.setWeights(graph, 10, (float)0.1, seed);
	System.out.println("\nGraph:");
	System.out.println(graph.toString());
	
	Set<Vertex> s = new Set<Vertex>();
	s.addAll((Collection<? extends Vertex>) findNear(graph,graph.firstVertex(),(float) 3.5));
	
	System.out.println(s);
	
	
	
	}


	

public static Vertex findNear(DiGraph g, Vertex v, float MaxPath){
	v.setColor(AbstractGraph.BLACK);
	for(Vertex neighbors: v.neighbors()){
		if(v.getEdge(neighbors).getWeight() <= MaxPath){
			return (findNear(g,neighbors,MaxPath-v.getEdge(neighbors).getWeight()));
		}
		
	}
	
	
	return null;
}

}
