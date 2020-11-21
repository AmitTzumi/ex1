package ex0;

import java.util.Collection;
import java.util.HashMap;

public class NodeData implements node_data {
private static int counter=0;
private int key;
private String info;
private int tag;
private HashMap<Integer,node_data> neighbors;

/**
 * The constructor.
 */
public NodeData() {
	this.key=counter;
    neighbors=new HashMap<Integer,node_data>();
    counter++;
}

/**
 * Return the key (id) associated with this node.
 * Note: each node_data should have a unique key.
 * @return this key.
 */
public int getKey() {
	return this.key;
}

/**
 * This method returns a collection with all the Neighbor nodes of this node_data */
public Collection<node_data> getNi(){
	return neighbors.values();
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

/** This method adds the node_data (t) to this node_data.*/
public void addNi(node_data t) {
	neighbors.put(t.getKey(), t);
}

/**
 * Removes the edge this-key,
 * @param node - the given node_data.
 */
public void removeNode(node_data node) {
	if(neighbors.containsKey(node.getKey())) {
		neighbors.remove(node.getKey());
	}
}

/**
 * return the remark (meta data) associated with this node.
 * @return the info
 */
public String getInfo() {
	return info;
}

/**
 * Allows changing the remark (meta data) associated with this node.
 * @param s - the given string.
 */
public void setInfo(String s) {
this.info=s;	
}

/**
 * Temporal data (aka color: e,g, white, gray, black) 
 * which can be used be algorithms 
 * @return the tag.
 */
public int getTag() {
	return tag;	
}

/** 
 * Allow setting the "tag" value for temporal marking an node - common 
 * practice for marking by algorithms.
 * @param t - the new value of the tag.
 */
public void setTag(int t) {
this.tag=t;	
}
	
}

