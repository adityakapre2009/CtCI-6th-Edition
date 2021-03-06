/*
Q:
Route Between Nodes: Given a directed graph, design an algorithm to find out whether there is a
route between two nodes.

A:
This problem can be solved by just simple graph traversal, such as depth-first search or breadth-first search.
We start with one of the two nodes and, during traversal, check if the other node is found. We should mark
any node found in the course of the algorithm as "already visited" to avoid cycles and repetition of the
nodes.
It may be worth discussing with your interviewer the tradeoffs between breadth-first search and depth-first
search for this and other problems. For example, depth-first search is a bit simpler to implement since it can
be done with simple recursion. Breadth-first search can also be useful to find the shortest path, whereas
depth-first search may traverse one adjacent node very deeply before ever going onto the immediate
neighbors.
*/
package Q4_01_Route_Between_Nodes;

import java.util.LinkedList;

class Graph {

    private Node[] vertices;
    public static int MAX_VERTICES = 6;
    public int count;

    public Graph() {
    	vertices = new Node[MAX_VERTICES];
    	count = 0;
    }
	
    public void addNode(Node x) {
	if (count < vertices.length) {
		vertices[count] = x;
		count++;
	} else {
		System.out.print("Graph full");
	}
    }
    
    public Node[] getNodes() {
        return vertices;
    }
}

class Node {

    private String vertex;
    public Question.State state;
    private Node[] adjacent;
    public int adjacentCount;

    public Node(String vertex, int adjacentLength) {
        this.vertex = vertex;                
        adjacentCount = 0;        
        adjacent = new Node[adjacentLength];
    }
    
    public void addAdjacent(Node x) {
        if (adjacentCount < adjacent.length) {
            this.adjacent[adjacentCount] = x;
            adjacentCount++;
        } else {
            System.out.print("No more adjacent can be added");
        }
    }
	
    public Node[] getAdjacent() {
        return adjacent;
    }
	
    public String getVertex() {
        return vertex;
    }
}

public class Question {
	
	public enum State {
		Unvisited, Visited, Visiting;
	} 

	public static boolean search(Graph g, Node start, Node end) {  
		LinkedList<Node> q = new LinkedList<Node>();
		//Initialize everything as unvisited
		for (Node u : g.getNodes()) {
		    u.state = State.Unvisited;
		}
		//Visit "Start" node & add to queue
		start.state = State.Visiting;
		q.add(start);
		Node u;
		//Loop till queue is not empty
		while(!q.isEmpty()) {
		    //Remove from queue & explore adjacent nodes in loop
		    u = q.removeFirst();
		    if (u != null) {
			    for (Node v : u.getAdjacent()) {
				if (v.state == State.Unvisited) {
				    //If destination node found, return true
				    if (v == end) {
					return true;
				    } else {
					//Mark adjacent nodes as "Visiting"
					v.state = State.Visiting;
					//Add adjacent nodes to queue
					q.add(v);
				    }
				}
			    }
			    //Mark node removed from queue as "Visited"
			    u.state = State.Visited;
		    }
		}
		return false;
	}
	
	public static void main(String a[]) {
		Graph g = createNewGraph();
		Node[] n = g.getNodes();
		Node start = n[3];
		Node end = n[5];
		System.out.println(search(g, start, end));
	}
	
	public static Graph createNewGraph() {
		Graph g = new Graph();        
		Node[] temp = new Node[6];

		temp[0] = new Node("a", 3);
		temp[1] = new Node("b", 0);
		temp[2] = new Node("c", 0);
		temp[3] = new Node("d", 1);
		temp[4] = new Node("e", 1);
		temp[5] = new Node("f", 0);

		temp[0].addAdjacent(temp[1]);
		temp[0].addAdjacent(temp[2]);
		temp[0].addAdjacent(temp[3]);
		temp[3].addAdjacent(temp[4]);
		temp[4].addAdjacent(temp[5]);
		for (int i = 0; i < 6; i++) {
			g.addNode(temp[i]);
		}
		return g;
	}
}
