package PA1;
// GoodHeuristic
//
// This class extends the Heuristic class, providing a reasonable
// implementation of the heuristic function method. The provided "good"
// heuristic function is admissible.
//
// Simranjit Singh -- October 11, 2019
//

import java.util.HashSet;
import java.util.Set;

// IMPORT ANY PACKAGES THAT YOU NEED.
// import java.util.*;


public class GoodHeuristic extends Heuristic {

        // YOU CAN ADD ANYTHING YOU LIKE TO THIS CLASS ... WHATEVER WOULD
        // ASSIST IN THE CALCULATION OF YOUR GOOD HEURISTIC VALUE.

	// heuristicValue -- Return the appropriate heuristic values for the
	// given search tree node. Note that the given Node should not be
	// modified within the body of this function.
	
	public StreetMap graph;
	public String initialLoc; 
	public String destinationLoc;
	int depthLimit = 10000;
	public int nodeExpansionCount = 0;

// Default constructor ...
public GoodHeuristic() {
	this.graph = null;
	this.initialLoc = "";
	this.destinationLoc = "";
	this.depthLimit = 10000;
	this.nodeExpansionCount = 0;
}
public double heuristicValue(Node thisNode) {
	double hVal = 0.0;

	// COMPUTE A REASONABLE HEURISTIC VALUE HERE

	return (hVal);
}
	

public GoodHeuristic(StreetMap graph, String initialLoc, String destinationLoc, int depthLimit) {
	// TODO Auto-generated constructor stub

// Constructor with state space and search parameters specified ...

	
		this();
		this.graph = graph;
		this.initialLoc = initialLoc;
		this.destinationLoc = destinationLoc;
		this.depthLimit = depthLimit;
		this.nodeExpansionCount = 0;
}
public GoodHeuristic(Location finialLoc) {
	// TODO Auto-generated constructor stub
}
public Node search(boolean useRepeatedStateChecking) {
	// TODO Auto-generated method stub
	// Find initial location ...
	Location Node_initial_node = (graph.findLocation(initialLoc));
	if (Node_initial_node == null)
	
	{
		return null;
	}
	
	
	Node initialNode = new Node(null);
		Set<String> closedList = null;
	// Create a frontier object ...
	SortedFrontier fringe = new SortedFrontier(SortBy.g);
	// Add the initial node to the frontier ...
	fringe.addSorted(initialNode);
	

	if (useRepeatedStateChecking) {
		closedList = new HashSet<String>();
	}
	// Initialize the expansion count ...
	nodeExpansionCount = 0;
	// Start searching, and keep searching until the fringe is empty ...
	Node node;
	while (!(fringe.isEmpty())) {
		// Get the next node on the frontier ...
		node = fringe.removeTop();
		// Check to see if we have reached the depth limit ...
		if (node.depth >= depthLimit) {
			// Failure to find a solution within the allowed depth ...
			return (null);
		}
		// Check to see if it is a solution ...
		// From BF Solutions 
		if (node.isDestination(destinationLoc)) {
			// We have found a goal node, so return it from this method ...
			return (node);
		} else {
			
			// We are about to expand this node, so add it to the
			// "explored set" (i.e., the "closed list") if we are doing
			// repeated state checking ...
			if (useRepeatedStateChecking) {
				closedList.add(node.loc.name);
			}
			// This is not a goal node, so we need to expand it ... // From BF Search Solution
			node.expand();
			nodeExpansionCount++;
			// Add the children of this node to the frontier. If we
			// are doing repeated state checking, only add a node if
			// its state is not already in the frontier and its state
			// is not in the "explored set".
			for (Node child : node.children) {
				if (!useRepeatedStateChecking
						|| (!(fringe.contains(child)) && !(closedList.contains(child.loc.name))))
					fringe.addSorted(child);
			}
			
			// We're now ready to loop back and try the next node ...
			
		}
	}
	
		
		return null;
}

}
