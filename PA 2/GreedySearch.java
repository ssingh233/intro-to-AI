package PA1;

import java.util.HashSet;
import java.util.Set;

public class GreedySearch {
	// Partner David
	// I went to Tiantian Office hours on Oct 10 to get help with translating the algorthims she helped me with may of the if statements
	// I used David Noell's solutions for BFSearch.java and DFSearch.java as a sample format

	
	// declaration of variables
	public  StreetMap graph;
	//public Object nodeExpansionCount;
	public int nodeExpansionCount =0;
	public String initialLoc;
	public String destinationLoc;
	public int limit= 10000;

	public GreedySearch() {
		this.graph= null;
		this.initialLoc = "";
		this.destinationLoc = "";
		this.limit = 10000;
		this.nodeExpansionCount = 0;

	}




	public GreedySearch(StreetMap graph, String initialLoc, String destinationLoc, int limit) {
		// TODO Auto-generated constructor stub
		this();
		this.graph = graph;
		this.initialLoc = initialLoc;
		this.destinationLoc = destinationLoc;
		this.limit = limit;
		
	}



	public Node search(boolean useRepeatedStateChecking) {
		// TODO Auto-generated method stub
		// Sets the initial node to starting location
		Location Node_initial_node =graph.findLocation(initialLoc);
		Location finialLoc = graph.findLocation(destinationLoc);
		if (Node_initial_node == null) 
		{
			return (null);
		}
		//Call the Good Heuristic function
		GoodHeuristic h = new GoodHeuristic(finialLoc);
		//h.initialize(initialLoc,destinationLoc);
		// Create a new Node and "Set"
		Node initialNode = new Node(Node_initial_node, null);
		Set<String> closedList = null;
		
		SortedFrontier fringe = new SortedFrontier(SortBy.h);
		fringe.addSorted(initialNode);
		
		// this is for repeated state Checking
		//Create a hashset called closedList
		if (useRepeatedStateChecking) 
		{
			closedList = new HashSet<String>();
		}
		// set counter equal to zero
		nodeExpansionCount= 0;
		Node node;
		// if fringe is not empty do a loop until conditions have been met
		while (!(fringe.isEmpty())) {
			// Remove top node
			node = fringe.removeTop();
			// Compare node and limit
			if (node.depth >= limit) {
				// if limit greater than or equal to node depth then return null	
				return (null);
			}
			// If the destination is the destination we want then return the node
			if (node.isDestination(destinationLoc)) {
				
				return (node);
				// else statement: if fringe is empty
			} else {
				
				
				// add a location to the hash set
				if (useRepeatedStateChecking) {
					closedList.add(node.loc.name);
				}
				
				//node.expand();
				
				node.expand(h);
				// Increment by one
				nodeExpansionCount += 1;
			
				// Add the children of this node to the frontier. 
				for (Node child : node.children) {
					if (useRepeatedStateChecking) {
						if(!closedList.contains(child.loc.name)) {
							if(fringe.contains(child)) {
								//if(child.partialPathCost < fringe.find(child).partialPathCost) {
									//fringe.remove(fringe.find(child));
									//fringe.addSorted(child);
									
								//}
								return null;
							}
							
						} 
						// And statment which checks to see is there no fringe that contain child and no closedList that contains child loc.name
						else if(!fringe.contains(child) && !(closedList.contains(child.loc.name))) {
							fringe.addSorted(child);
						}
						
					}
					// add child to fringe
					else {
						fringe.addSorted(child);
					}
				}
				
			
			}}
		
		
		return null;
	}}