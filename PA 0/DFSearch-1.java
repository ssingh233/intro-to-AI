//Lab partner David Philip

public class DFSearch {
	
	public int nodeExpansionCount;
	Map m;
	String SLname;
	String FLname;
	Frontier frontier;
	Location SL;
	Location FL; 
	Node n;
	Frontier ES;
	int depthlim;
	
	//Constructor 
	public DFSearch(Map graph, String initialLoc, String destinationLoc, int limit){		
	
		this.m=graph;
		this.SLname=initialLoc;
		this.FLname=destinationLoc;
		this.depthlim=limit;
	}
	//method
	public Node search(boolean b)
	{
		nodeExpansionCount=0;
		SL=m.findLocation(SLname);
		FL=m.findLocation(FLname);
		n=new Node(SL);
		frontier= new Frontier();
		if(n.isDestination(FLname))
		{
			return n;
		}
		frontier.addToTop(n);
		if (b)
		{
			ES=new Frontier();
			ES.addToBottom(n);
		}
		while(true)
		{
// Check if a destination way
			if(frontier.isEmpty())
					{
						return null;
					}
			n=frontier.removeTop();
			if(n.isDestination(FLname))
			{
				return n;
			}
			if(b)
			{
				ES.addToTop(n);
			}
			if(n.depth>=depthlim)
			{
				return null;
			}
			n.expand();
			nodeExpansionCount++;
			//Reading a .dat
			if(b)
			{
				for(Node node : n.children)
				{
					if(!frontier.contains(node.loc) && !ES.contains(node.loc))
						{
							frontier.addToTop(node);
						}
									
				}
				
			}
			// New frontier
			else{
				frontier.addToTop(n.children);
			}
		}
	}
}
		//https://web.cs.ship.edu/~djmoon/ai/ai-notes/search-blind.pdf
		
