package askhsh4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GreedyApproach {
	
	private ArrayList<String> keys;
	private ArrayList<List<String>> values;
	private HashMap<String,String> cover;
	private ArrayList<String> coverNodes;
	
	public GreedyApproach(){
		this.keys = new ArrayList<String>();
		this.values = new ArrayList<List<String>>();
		this.cover = new HashMap<String,String>();
		this.coverNodes=new ArrayList<String>();
		
	}
	
	//ama ola ta key tou cover einai idia me ta key toy graph ok
	public void greedy(HashMap<String,List<String>> graph){
		int size = graph.size();
		fillKeysValues(graph);
		int k = 0;
		//oso oi ta list twn komvwn pou exw parei dn kaluptoun olous tous komvous
		while(k<size){
			String[] best=findBestNode(graph);
			String node = best[0];
			int index = Integer.parseInt(best[1]);
			if(keys.get(index).equals(node)){
				this.coverNodes.add(node);
				updateGraph(graph,node);
				fillKeysValues(graph);
			}
			
			k=this.cover.size();
		}
	}

	private void updateGraph(HashMap<String, List<String>> graph,String node) {
		if(graph==null)
			return ;
		List<String> temp=graph.remove(node);
		for(int i=0;i<temp.size();i++){
			if(this.cover!=null){
				if(!this.cover.containsKey(temp.get(i))){
					cover.put(temp.get(i),node);
				}
			}
			else{
				cover.put(temp.get(i),node);
			}
		}
		if(!cover.containsKey(node))
			this.cover.put(node, node);
		//ananewnw ta values tou graph wste na mhn exoun ton node oute ta values tou node
		for(Map.Entry<String,List<String>> entry:graph.entrySet()){     
            List<String> value =entry.getValue();  
            if(value.contains(node)){
            	value.remove(value.indexOf(node));
            } 
            for(int i=0;i<temp.size();i++){
            	int j;
            	if((j=value.indexOf(temp.get(i)))>=0){
            		value.remove(j);
            	}
            }
        } 
	}

	private String[] findBestNode(HashMap<String, List<String>> graph) {
		if(graph==null)
			return null;
		String node = null;
		String[] ar = new String[2];
		int max =0;
		int index=0;
		int counter=0;
		for(Map.Entry<String,List<String>> entry:graph.entrySet()){    
            String key=entry.getKey(); 
            List<String> value =entry.getValue(); 
            if(value.size()>max){
            	index=counter;
            	max=value.size();
            	node=key;
            }
            counter++;
		}
		String ind = Integer.toString(index);
		ar[0]=node;
		ar[1]=ind;
		return ar;
	}

	

	private void fillKeysValues(HashMap<String, List<String>> graph) {
		this.keys.clear();
		this.values.clear();
		for(Map.Entry<String,List<String>> entry:graph.entrySet()){    
            String key=entry.getKey(); 
            List<String> value =entry.getValue(); 
            this.keys.add(key);
            this.values.add(value);
		}    
		
	}
	
	public HashMap<String,String> getCover(){
		return this.cover;
	} 
	
	public ArrayList<String> getCoverNodes(){
		return this.coverNodes;
	}
	
}
