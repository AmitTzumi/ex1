package ex0;

import java.util.Collection;
import java.util.HashMap;

public class Graph_DS implements graph {
	private HashMap<Integer,node_data> nodes;
	private int NumofEdges;
	private int ModeCount;
	
	/**
	 * The consturctor.
	 */
	public Graph_DS() {
		nodes=new HashMap<Integer,node_data>();
		NumofEdges=0;
		ModeCount=0;
	}
	
	/**
	 * return the node_data by the node_id,
	 * @param key - the node_id
	 * @return the node_data by the node_id, null if none.
	 */
	public node_data getNode(int key) {
		if(!nodes.containsKey(key)) { 
			return null;
		}
		else {
			return nodes.get(key);
		}			
	}
	/**
	 * return true iff (if and only if) there is an edge between node1 and node2
	 * Note: this method should run in O(1) time.
	 * @param node1 - one node from the graph.
	 * @param node2 - second node from the graph. 
	 * @return boolean
	 */
	public boolean hasEdge(int node1, int node2) {
		return nodes.get(node1).hasNi(node2);	
	}
	
	/**
	 * add a new node to the graph with the given node_data.
	 * Note: this method should run in O(1) time.
	 * @param n the key of node_data n.
	 */
	public void addNode(node_data n) {
		nodes.put(n.getKey(), n);
		ModeCount++;
	}
	
	/**
	 * Connect an edge between node1 and node2.
	 * Note: this method should run in O(1) time.
	 * Note2: if the edge node1-node2 already exists - the method simply does nothing.
	 * @param node1 - one node from the graph.
	 * @param node2 - second node from the graph.
	 */
	public void connect(int node1, int node2) {
		if(!hasEdge(node1,node2) && (node1!=node2)) {
				nodes.get(node1).addNi(nodes.get(node2));
				nodes.get(node2).addNi(nodes.get(node1));
				NumofEdges++;
				ModeCount++;
		}
	}
	
	/**
	 * This method return a pointer (shallow copy) for the
	 * collection representing all the nodes in the graph. 
	 * Note: this method should run in O(1) time.
	 * @return Collection<node_data> 
	 */
	public Collection<node_data> getV(){
		return nodes.values();
	}
	
	/**
	 * This method return a collection of  the
	 * collection representing all the nodes connected to node_id
	 * Note: this method should run in O(1) time.
	 * @return Collection<node_data>
	 * @param node_id - the given node key.
	 */
	public Collection<node_data> getV(int node_id){
		return nodes.get(node_id).getNi();
	}
	
	/**
	 * Delete the node (with the given ID) from the graph -
	 * and removes all edges which starts or ends at this node.
	 * This method should run in O(n), |V|=n, as all the edges should be removed.
	 * @return the data of the removed node (null if none). 
	 * @param key the given node key.
	 */
	public node_data removeNode(int key) {
		if(getNode(key)!=null) {
			for(node_data i : getV(key)){
					i.removeNode(nodes.get(key));
					NumofEdges--;
					ModeCount++;
			}
			node_data n=nodes.get(key);
			nodes.remove(key);
			return n;
		}
		else
			return null;	
	}
	
	/**
	 * Delete the edge from the graph, 
	 * Note: this method should run in O(1) time.
	 * @param node1 - one node from the graph.
	 * @param node2 - second node from the graph.
	 */
	public void removeEdge(int node1, int node2) {
		if(hasEdge(node1,node2)) {
	        	nodes.get(node1).removeNode(nodes.get(node2));
	        	nodes.get(node2).removeNode(nodes.get(node1));
	        	NumofEdges--;
	        	ModeCount++;
		}
	}
	
	/** return the number of vertices (nodes) in the graph.
	 * Note: this method should run in O(1) time. 
	 * @return the number of vertices.
	 */
	public int nodeSize() {
		return nodes.size();
	}
	
	/** 
	 * return the number of edges (undirectional graph).
	 * Note: this method should run in O(1) time.
	 * @return the number of edges.
	 */
	public int edgeSize() {
		return NumofEdges;
	}
	
	/**
	 * return the Mode Count - for testing changes in the graph.
	 * Any change in the inner state of the graph should cause an increment in the ModeCount
	 * @return the Mode Count.
	 */
	public int getMC() {
		return ModeCount;
	}

}
