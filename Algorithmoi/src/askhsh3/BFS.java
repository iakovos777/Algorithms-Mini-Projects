package askhsh3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class BFS {
	
	private HashMap<String,String> path;
	private boolean pathFound;
	private ArrayList<String> shortestPath;
	
	public BFS(){
		this.path=new HashMap<String,String>();
		this.pathFound=false;
		this.shortestPath = new ArrayList<String>();
	}
	
	public ArrayList<String> searchPath(HashMap<String,List<String>> graph, String start, String end){
		path.clear();
		if(start==null){
			System.out.println("Null start node");
			return null;
		}
		if(end==null){
			System.out.println("Null end node");
			return null;
		}
		// Mark all the vertices as not visited(By default
		// set as false)
		
		HashMap<String,Boolean> visited = new HashMap<String,Boolean>();
		for(Map.Entry<String,List<String>> entry:graph.entrySet()){    
            String key=entry.getKey();
            visited.put(key, false);
		}
		// Create a queue for BFS
		Queue<String> queue = new LinkedList<String>();

		// Mark the current node as visited and enqueue it
		visited.put(start,true);
		queue.add(start);
		this.path.put(start,"none");

		while (queue.size() != 0)
		{
			
			// Dequeue a vertex from queue 
			start = queue.poll();
			pathFound= (start.equals(end)) ? true : false;
			if(pathFound){
				String temp=end;
				while(!temp.equals("none")){
			    	   shortestPath.add(temp);
						temp = path.get(temp);
				}
				
				break;
			}

			// Get all adjacent vertices of the dequeued vertex s
			// If a child has not been visited, then mark it
			// visited and enqueue it
			List<String> children = takeValues(graph,start);
			if(children==null){
				System.out.println("Something got wrong");
				return null;
			}
			Iterator<String> i = children.listIterator();
			while (i.hasNext()){
				
				String n = i.next();
				if (graph.containsKey(n)){
					
					if(!checkBool(visited, n)){
						this.path.put(n,start);
						visited.put(n, true);
						queue.offer(n);
					}
				}
				else{
					System.out.println("There is not such node in graph");
					return this.shortestPath;
				}
			}
		}
		return this.shortestPath;
	}

	

	private Boolean checkBool(HashMap<String, Boolean> graph, String n) {
		if(graph==null)
			return null;
		if(n==null)
			return null;
		Boolean check = null;
		for(Map.Entry<String,Boolean> entry:graph.entrySet()){    
            String key=entry.getKey();
            if(key.equals(n)){
            	check= entry.getValue();
            	break;
            }
		}
		return check;
	}

	private List<String> takeValues(HashMap<String, List<String>> graph, String start) {
		if(graph==null)
			return null;
		if(start==null)
			return null;
		List<String> children = new ArrayList<String>();
		for(Map.Entry<String,List<String>> entry:graph.entrySet()){    
            String key=entry.getKey();
            if(key.equals(start)){
            	children =entry.getValue();
            	break;
            }
		}
		return children;
	}
	
	public ArrayList<String> reverse(ArrayList<String> aList){
		if(aList==null)
			return null;
		ArrayList<String> reverseList = new ArrayList<String>();
		for(int i=aList.size()-1;i>=0;i--){
	    	   reverseList.add(aList.get(i));
	    }
		return reverseList;
	}
	
}
