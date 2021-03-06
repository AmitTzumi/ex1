package ex1.src;
import java.util.Collection;
import java.util.HashMap;


public class WGraph_DS implements weighted_graph,java.io.Serializable {
	private HashMap<Integer,node_info> nodes;
	private int NumofEdges;
	private int ModeCount;
	
	public static class NodeInfo implements node_info,java.io.Serializable{
		private int key;
		private String info;
		private double tag;
		private HashMap<Integer,node_info> neighbors;
		private HashMap<Integer,Double> weights;
		
		/**
		 * The constructor.
		 * @param key - given.
		 */
		public NodeInfo(int key) {
			this.key=key;
		    neighbors=new HashMap<Integer,node_info>();
		    weights=new HashMap<Integer,Double>();
		    //counter++;
		}
		
		/**
		 * Removes the edge this-key.
		 * @param node - the given NodeInfo.
		 */
		public void removeNode(NodeInfo node) {
			if(neighbors.containsKey(node.getKey())) {
				neighbors.remove(node.getKey());
				weights.remove(node.getKey());
			}
		}
		
		/**
		 * return the weight of the given key.
		 * @param key - the given key.
		 * @return weight.
		 */
		public double GetW(int key) {
			if(!hasNi(key)) {
				return -1;
			}
			else {
			return weights.get(key);
			}
		}
		
		/**
		 * return true iff this<==>key are adjacent, as an edge between them.
		 * @param key- the given int.
		 * @return boolean.
		 */
		public boolean hasNi(int key) {
			if ((!(neighbors.isEmpty())) && (neighbors.containsKey(key)))
				return true;
			else
				return false;	
		}
		
		/** This method adds the node_info (t) to this node_info.*/
		public void addNi(NodeInfo t, double w) {
			neighbors.put(t.getKey(), t);
			weights.put(t.getKey(), w);
		}
		
		/**
		 * This method returns a collection with all the Neighbor nodes of this node_info */
		public Collection<node_info> getNi(){
			return neighbors.values();
		}
		
		/**
	     * Return the key (id) associated with this node.
	     * Note: each node_data should have a unique key.
	     * @return
	     */
	    public int getKey() {
	    	return this.key;
	    }

	    /**
	     * return the remark (meta data) associated with this node.
	     * @return
	     */
	    public String getInfo() {
	    	return info;
	    }
	    
	    /**
	     * Allows changing the remark (meta data) associated with this node.
	     * @param s
	     */
	    public void setInfo(String s) {
	    	this.info=s;
	    }
	    
	    /**
	     * Temporal data (aka distance, color, or state)
	     * which can be used be algorithms
	     * @return
	     */
	    public double getTag() {
	    	return tag;
	    }
	    
	    /**
	     * Allow setting the "tag" value for temporal marking an node - common
	     * practice for marking by algorithms.
	     * @param t - the new value of the tag
	     */
	    public void setTag(double t) {
	    	this.tag=t;
	    }
	    
	    /**
	     * equals function - return true if the two object are equals.
	     * else, return false.
	     */
	    public boolean equals(Object o) {
	    	if (key !=((NodeInfo)o).key)
	    		return false;
	    	for (int i: weights.keySet())
	    	{
	    		if (!((NodeInfo)o).weights.containsKey(i))
	    			return false;
	    		if (!((NodeInfo)o).weights.get(i).equals(weights.get(i)))
	    			return false;
	    	}
	    	return true;
	    }
	}
	
	/**
	 * The constructor.
	 */
	public WGraph_DS() {
		nodes=new HashMap<Integer,node_info>();
		NumofEdges=0;
		ModeCount=0;
	}

	/**
     * return the node_info by the node_id,
     * @param key - the node_id
     * @return the node_info by the node_id, null if none.
     */
    public node_info getNode(int key) {
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
     * @param node1
     * @param node2
     * @return
     */
    public boolean hasEdge(int node1, int node2) {
    	return ((NodeInfo)nodes.get(node1)).hasNi(node2);
    }
    /**
     * return the weight if the edge (node1, node1). In case
     * there is no such edge - should return -1
     * Note: this method should run in O(1) time.
     * @param node1
     * @param node2
     * @return
     */
    public double getEdge(int node1, int node2) {
    	return ((NodeInfo)nodes.get(node1)).GetW(node2);	
    	
    }
    /**
     * add a new node to the graph with the given key.
     * Note: this method should run in O(1) time.
     * Note2: if there is already a node with such a key -> no action should be performed.
     * @param key
     */
    public void addNode(int key) {
    	NodeInfo n = new NodeInfo(key);
    	if (!nodes.containsKey(key)) {
    		nodes.put(key, n);
    		ModeCount++;	
    	}
    }
    
    /**
     * Connect an edge between node1 and node2, with an edge with weight >=0.
     * Note: this method should run in O(1) time.
     * Note2: if the edge node1-node2 already exists - the method simply updates the weight of the edge.
     */
    public void connect(int node1, int node2, double w) {
    	if(!hasEdge(node1,node2) && (node1!=node2)) {
    		((NodeInfo)nodes.get(node1)).addNi(((NodeInfo)nodes.get(node2)),w);
    		((NodeInfo)nodes.get(node2)).addNi(((NodeInfo)nodes.get(node1)),w);
			NumofEdges++;
			ModeCount++;	
    	}
    }
    
    /**
     * This method return a pointer (shallow copy) for a
     * Collection representing all the nodes in the graph.
     * Note: this method should run in O(1) tim
     * @return Collection<node_info>
     */
    public Collection<node_info> getV(){
    	return nodes.values();	
    }
    
    /**
     *
     * This method returns a Collection containing all the
     * nodes connected to node_id
     * Note: this method can run in O(k) time, k - being the degree of node_id.
     * @return Collection<node_info>
     */
    public Collection<node_info> getV(int node_id){
		return ((NodeInfo)nodes.get(node_id)).getNi();	
    }
    
    /**
     * Delete the node (with the given ID) from the graph -
     * and removes all edges which starts or ends at this node.
     * This method should run in O(n), |V|=n, as all the edges should be removed.
     * @return the data of the removed node (null if none).
     * @param key
     */
    public node_info removeNode(int key) {
    	if(getNode(key)!=null) {
			for(node_info i : getV(key)){
					((NodeInfo) i).removeNode((NodeInfo)nodes.get(key));
					NumofEdges--;
					ModeCount++;
			}
			node_info n=nodes.get(key);
			nodes.remove(key);
			return n;
		}
		else
			return null;	
    }
    
    /**
     * Delete the edge from the graph,
     * Note: this method should run in O(1) time.
     * @param node1
     * @param node2
     */
    public void removeEdge(int node1, int node2) {
    	if(hasEdge(node1,node2)) {
        	((NodeInfo)nodes.get(node1)).removeNode((NodeInfo)nodes.get(node2));
        	((NodeInfo)nodes.get(node2)).removeNode((NodeInfo)nodes.get(node1));
        	NumofEdges--;
        	ModeCount++;
    	}	
    }
    
    /** return the number of vertices (nodes) in the graph.
     * Note: this method should run in O(1) time.
     * @return
     */
    public int nodeSize() {
    	return nodes.size();
    }
    
    /**
     * return the number of edges (undirectional graph).
     * Note: this method should run in O(1) time.
     * @return
     */
    public int edgeSize() {
    	 return NumofEdges;
    }
    
    /**
     * return the Mode Count - for testing changes in the graph.
     * Any change in the inner state of the graph should cause an increment in the ModeCount
     * @return
     */
    public int getMC() {
    	return ModeCount;
    }
    
    /**
     * equals function - return true if the two object are equals.
     * else, return false.
     */
    public boolean equals(Object o) {
    	if (nodes.size()!=((WGraph_DS)o).nodes.size())
    		return false;
    	for (int i: nodes.keySet()){
    		if (!((WGraph_DS)o).nodes.containsKey(i))
				return false;
    		if (!((NodeInfo)nodes.get(i)).equals((NodeInfo)((WGraph_DS)o).nodes.get(i)))
    			return false;
    	}
    	return true;
    }
}
