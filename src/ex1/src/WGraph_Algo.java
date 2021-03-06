package ex1.src;

import java.io.*;
import java.util.*;

import ex1.src.WGraph_DS.*;


public class WGraph_Algo implements weighted_graph_algorithms,java.io.Serializable {
	private weighted_graph wg_alg;
	private HashMap<Integer,Integer> d;
	private HashMap<Integer,node_info> p;
	private HashMap<Integer,Double> d1;
	private HashMap<Integer,node_info> p1;
	
	/**
	 * Default constructor.
	 */
	public WGraph_Algo() {
	}
	
	/**
	 * The constructor.
	 * @param g - the given graph.
	 */
	public WGraph_Algo(weighted_graph g) {
		wg_alg = g;
	}
	
	/**
     * Init the graph on which this set of algorithms operates on.
     * @param g
     */
    public void init(weighted_graph g) {
    	wg_alg = g;
    }

    /**
     * Return the underlying graph of which this class works.
     * @return
     */
    public weighted_graph getGraph() {
    	return wg_alg;
    }
    
    /**
     * Compute a deep copy of this weighted graph.
     * @return
     */
    public weighted_graph copy() {
    	weighted_graph g  = new WGraph_DS();
		for (node_info i : wg_alg.getV()) {	
			g.addNode(i.getKey());
		}
		for (node_info i : wg_alg.getV()) {
			int key = i.getKey(); 
			for (node_info j : wg_alg.getV(key)) {
				g.connect(key, j.getKey(),wg_alg.getEdge(key, j.getKey()));
			}
		}
		return g;
    }
    
    /** 
	 * Breadth-first search (BFS) is an algorithm for traversing or searching tree or graph data structures.
	 * We used Elizabeth's lecture on BFS and implemented its pseudo code.
	 * @param s - the given node_data.
	 */
	public void BFS(node_info s) { 
		d = new HashMap<Integer,Integer>();
		p = new HashMap<Integer,node_info>();
		Queue<node_info> q = new LinkedList<>();
		for(node_info n : wg_alg.getV() ) { 
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
			node_info u= q.remove(); 
			for(node_info v : ((NodeInfo)u).getNi()) {
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
     * Returns true if and only if (iff) there is a valid path from EVREY node to each
     * other node. NOTE: assume ubdirectional graph.
     * @return
     */
    public boolean isConnected() {
    	if (!wg_alg.getV().isEmpty())
			BFS(wg_alg.getV().iterator().next());
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
     * Dijkstra's algorithm is an algorithm for finding the shortest paths between nodes in a graph.
     * @param s - the given source node.
     */
    public void Dijkstra(node_info s) {
    	d1 = new HashMap<Integer,Double>();
    	p1 = new HashMap<Integer,node_info>();
    	HashSet<Integer> visited = new HashSet<Integer>();
    	PriorityQueue<Node> q = new PriorityQueue<Node>(wg_alg.nodeSize(), new Node());
    	for(node_info n : wg_alg.getV()) {
    		d1.put(n.getKey(),Double.POSITIVE_INFINITY);
    		p1.put(n.getKey(),null);
    	}
    	d1.put(s.getKey(),(double) 0);
    	q.add(new Node(s.getKey(),0));
    	while(visited.size()!=wg_alg.nodeSize()) {
    		int key = q.remove().key;
    		if(!visited.contains(key)) {
				for(node_info v : wg_alg.getV(key)) {
					if(!visited.contains(v.getKey())) {
		    			double sumDest=d1.get(key)+wg_alg.getEdge(key, v.getKey());
		    			if(sumDest<d1.get(v.getKey())){
		    				d1.replace(v.getKey(),sumDest);
		    				p1.replace(v.getKey(), wg_alg.getNode(key));
		    			}
		    			q.add(new Node(v.getKey(),d1.get(v.getKey())));
					}
				}
				visited.add(key);
    		}
    	}
    }
    
    /**
     * returns the length of the shortest path between src to dest
     * Note: if no such path --> returns -1
     * @param src - start node
     * @param dest - end (target) node
     * @return
     */
    public double shortestPathDist(int src, int dest) {
    	Dijkstra(wg_alg.getNode(src));
		return d1.get(dest);
    }
    /**
     * returns the the shortest path between src to dest - as an ordered List of nodes:
     * src--> n1-->n2-->...dest
     * see: https://en.wikipedia.org/wiki/Shortest_path_problem
     * Note if no such path --> returns null;
     * @param src - start node
     * @param dest - end (target) node
     * @return
     */
    public List<node_info> shortestPath(int src, int dest){
    	Dijkstra(wg_alg.getNode(src));
		node_info s= wg_alg.getNode(dest);
		List<node_info> list=new ArrayList<>();
		while(s!=null) {
			list.add(s);
			s=p1.get(s.getKey());	
		}
		Collections.reverse(list);
		return list;
    }

    /**
     * Saves this weighted (undirected) graph to the given
     * file name
     * @param file - the file name (may include a relative path).
     * @return true - iff the file was successfully saved
     */
    public boolean save(String file) {
    	try {
    		FileOutputStream MyFile = new FileOutputStream(file); 
    		ObjectOutputStream oos = new ObjectOutputStream(MyFile); 
    		oos.writeObject(this.wg_alg);
    		oos.close();
    		MyFile.close();
    		return true;
    	} 
    	catch(Exception e) {
    		e.printStackTrace();
    		return false;
    	}	
    }

    /**
     * This method load a graph to this graph algorithm.
     * if the file was successfully loaded - the underlying graph
     * of this class will be changed (to the loaded one), in case the
     * graph was not loaded the original graph should remain "as is".
     * @param file - file name
     * @return true - iff the graph was successfully loaded.
     */
    public boolean load(String file) {
    	try {
    		FileInputStream MyFile = new FileInputStream(file);
    		ObjectInputStream ois = new ObjectInputStream(MyFile);
    		this.wg_alg=(weighted_graph) ois.readObject();
    		ois.close();
    		MyFile.close();
    		return true;
    	} 
    	catch(Exception e) {
    		e.printStackTrace();
    		return false;
    	}		
    }
    
    /**
     * I created this Node class thats implements Comparator to fill the QriorityQueue by distance order.
     * @author Amitt
     */
    class Node implements Comparator<Node> { 
        public int key;
        public double distance; 
      
        public Node() 
        { 
        } 
      
        public Node(int key, double distance) 
        { 
            this.key = key; 
            this.distance = distance; 
        } 
      
        @Override
        public int compare(Node node1, Node node2) 
        { 
            if (node1.distance < node2.distance) 
                return -1; 
            if (node1.distance > node2.distance) 
                return 1; 
            return 0; 
        } 
    } 
}
