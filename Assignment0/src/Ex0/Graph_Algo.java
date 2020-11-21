package Ex0;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class Graph_Algo implements graph_algorithms {

	private graph g_alg;
	private HashMap<Integer,Integer> d;
	private HashMap<Integer,node_data> p;
	
	/** 
	 * The constructor.
	 * @param g - the given graph.
	 */
	public Graph_Algo(graph g) {
		g_alg = g;
	}
	
	/** 
	 * Init the graph on which this set of algorithms operates on.
	 * @param g - the given graph.
	 */
	public void init(graph g) {
		g_alg = g;
	}
	
	/** 
	 * Compute a deep copy of this graph.
	 * @return graph.
	 */
	public graph copy() {
		graph g  = new Graph_DS();
		HashMap<Integer,Integer> keymap = new HashMap<Integer,Integer>();
		for (node_data i : g_alg.getV()) {
			node_data n = new NodeData();
			keymap.put(i.getKey(),n.getKey());
			g.addNode(n);
		}
		for (node_data i : g_alg.getV()) {
			int key = i.getKey(); 
			for (node_data j : g_alg.getV(key)) {
				g.connect(keymap.get(key), keymap.get(j.getKey()));
			}
		}
		return g;
	}
	/** 
	 * Breadth-first search (BFS) is an algorithm for traversing or searching tree or graph data structures.
	 * We used Elizabeth's lecture on BFS and implemented its pseudo code.
	 * @param s - the given node_data.
	 */
	public void BFS(node_data s) { 
		d = new HashMap<Integer,Integer>();
		p = new HashMap<Integer,node_data>();
		Queue<node_data> q = new LinkedList<>();
		for(node_data n : g_alg.getV() ) { 
			n.setTag(0); 
			d.put(n.getKey(),-1); 
			p.put(n.getKey(),null); 
		}
		s.setTag(1); 
		d.put(s.getKey(),0); 
		p.put(s.getKey(),null); 
		q.clear();
		q.add(s); 
		while(!q.isEmpty()) { 
			node_data u= q.remove(); 
			for(node_data v : u.getNi()) { 
				if(v.getTag()==0) { 
					v.setTag(1); 
					d.put(v.getKey(),d.get(u.getKey())+1); 
					p.put(v.getKey(),u); 
					q.add(v); 
				}
			}
			u.setTag(2); 
		}
	}

	/**
	 * Returns true if and only if (iff) there is a valid path from EVREY node to each other node.
	 * NOTE: assume ubdirectional graph.
	 * @return boolean.
	 */
	public boolean isConnected() {
		if (!g_alg.getV().isEmpty())
			BFS(g_alg.getV().iterator().next());
		else 
			return true;
		for(int i : d.values() ){
			if(i==-1) {
				return false;
			}	
		}
	return true;
}
	
	/**
	 * returns the length of the shortest path between src to dest
     * @param src - start node
     * @param dest - end (target) node
     * @return int.
	 */
	public int shortestPathDist(int src, int dest) {
		BFS(g_alg.getNode(src));
		return d.get(dest);		
	}	
	
	/**
	 * returns the the shortest path between src to dest - as an ordered List of nodes:
	 * src--> n1-->n2-->...dest
	 * see: https://en.wikipedia.org/wiki/Shortest_path_problem
	 * @param src - start node
	 * @param dest - end (target) node
	 * @return List.
	 */
	public List<node_data> shortestPath(int src, int dest) {
		BFS(g_alg.getNode(src));
		node_data s= g_alg.getNode(dest);
		List<node_data> list=new ArrayList<>();
		while(s!=null) {
			list.add(s);
			s=p.get(s.getKey());	
		}
		Collections.reverse(list);
		return list;
	}
}
