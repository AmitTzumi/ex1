In this task we will improve task 0, by generalizing the data structure we have developed so that it can also support weighted graphs.
After adjusting the data structure, we will try to implement a number of algorithms on the weighted graph, including the ability to save and load the graph from a file, calculate a short trajectory (minimum distances by weight of edges), and find the shortest trajectory.
We were asked to implement the DS_WGraph class that implements the graph_weighted interface, and we need to do so by implementing the info_node interface as an internal class which describes the properties of Vertex in the the graph.

We have created an internal class within WGraph_DS called NodeInfo that implements the node_info interface and has the following features: int key, String info, double tag, HashMap<Integer,node_info> neighbors, HashMap<Integer,Double> weights.
Also in this NodeInfo class we have created a number of methods such as:
NodeInfo(int key) - The constructor.
void removeNode (NodeInfo node) - Removes the edge this-key. 
double GetW(int key) - return the weight of the given key.
boolean hasNi (int key) - return true iff this<==>key are adjacent, as an edge between them.
void addNi (NodeInfo t, double w) - This method adds the node_info (t) to this node_info. 
Collection <node_info> getNi () - This method returns a collection with all the Neighbor nodes of this node_info.  
int getKey () - Return the key (id) associated with this node.
String getInfo () - return the remark (meta data) associated with this node.
void setInfo (String s) - Allows changing the remark (meta data) associated with this node.
double getTag () - Temporal data (aka color: e,g, white, gray, black) which can be used be algorithms 
void setTag (double t) - Allow setting the "tag" value for temporal marking an node - common.
boolean equals(Object o) - equals function - return true if the two object are equals. else, return false.

In addition, we were asked to implement another interface called weighted_graph using the WGraph_DS class,where we created a number of attributes which are: HashMap <Integer, node_info> nodes, int numofEdges, int ModeCount.
Also in this WGraph_DS class we have created a number of methods such as:
WGraph_DS () - The constructor.
node_info getNode (int key) - return the node_info by the node_id.
boolean hasEdge (int node1, int node2) - return true iff (if and only if) there is an edge between node1 and node2.
boolean getEdge(int node1, int node2) - return the weight if the edge (node1, node1). In case there is no such edge - should return -1.
void addNode (int key) - add a new node to the graph with the given key.
void connect (int node1, int node2, double w) - Connect an edge between node1 and node2, with an edge with weight >=0.
Collection <node_info > getV () - This method return a pointer (shallow copy) for the collection representing all the nodes in the graph. 
Collection <node_info> getV (int node_id) - This method return a collection of  the collection representing all the nodes connected to node_id.
node_info removeNode (int key) - Delete the node (with the given ID) from the graph and removes all edges which starts or ends at this node.
void removeEdge (int node1, int node2) - Delete the edge from the graph.
int nodeSize () - return the number of vertices (nodes) in the graph.
int edgeSize () - return the number of edges (undirectional graph).
int getMC () - return the Mode Count - for testing changes in the graph.
boolean equals(Object o) - equals function - return true if the two object are equals. else, return false.

Furthermore, we were asked to implement another interface called weighted_graph_algorithms using the WGraph_Algo class,where we created several attributes which are: weighted_graph wg_alg, HashMap<Integer, Integer> d, HashMap<Integer, node_info> p, HashMap<Integer, Double> d1, HashMap<Integer, node_info> p1.
Also in this WGraph_Algo class we have created a number of methods like: 
WGraph_Algo () - Default constructor.
WGraph_Algo (weighted_graph g) - The constructor.
void init (weighted_graph g) - Init the graph on which this set of algorithms operates on.
weighted_graph getGraph () - Return the underlying graph of which this class works.
weighted_graph copy () - Compute a deep copy of this weighted graph.
void BFS (node_info s) - Breadth-first search (BFS) is an algorithm for traversing or searching tree or graph data structures.
boolean isConnected () - Returns true if and only if (iff) there is a valid path from EVREY node to each other node.
void Dijkstra(node_info s) - Dijkstra's algorithm is an algorithm for finding the shortest paths between nodes in a graph.
double shortestPathDist (int src, int dest) - returns the length of the shortest path between src to dest.
List <node_info> shortestPath (int src, int dest) - returns the the shortest path between src to dest - as an ordered List of nodes.
boolean save(String file) - saves this weighted (undirected) graph to the given file name.
boolean load(String file) - this method load a graph to this graph algorithm. if the file was successfully loaded - the underlying graph of this class will be changed (to the loaded one), in case the graph was not loaded the original graph should remain "as is".
Also, I created Node class thats implements Comparator to fill the QriorityQueue by distance order.