package askhsh4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BruteForceSearch {
	
	private ArrayList<List<String>> coverValues;
	private ArrayList<String> keys;
	private ArrayList<List<String>> values;
	private ArrayList<List<String>> subset;
	
	private int subIndex;
	
	
	public BruteForceSearch(){
		this.coverValues = new ArrayList<List<String>>();
		this.keys = new ArrayList<String>();
		this.values = new ArrayList<List<String>>();
		this.subset = new ArrayList<List<String>>();
		
	}
	
	public void search(HashMap<String,List<String>> graph){
		int k=1;
		fillKeysValues(graph);
		List<String> temp = new ArrayList<String>();
		if(graph==null)
			return;
		for(Map.Entry<String,List<String>> entry:graph.entrySet()){    
            String key=entry.getKey(); 
			temp = entry.getValue();
			//-1 gt eksairoume ton eauto tou
			if(graph.size()-1==temp.size()){
				setIndexOfSubset(0);
				List<String> l = new ArrayList<String>();
				l.add(key);
				this.subset.add(l);
				return;
			}
		}
		k++;
		temp.clear();
		while(k<=graph.size()){
			if(k==graph.size()){
				setIndexOfSubset(0);
				this.subset.add(this.keys);
				return;
			}
			else{
				this.subset.clear();
				this.coverValues.clear();
				combination(k);
				if(this.subIndex>=0)
					return;
				
			}
			k++;
		}
		
	}
	
	
	private int checkSubset() {
		for(int i=0; i<this.coverValues.size();i++){
			if(this.coverValues.get(i).size()==this.keys.size())
				return i;
		}
		return -1;
		
	}

	/**
	 * @param k
	 */
	public void combination(int k){
		
        int N = this.keys.size();
        
        if(k > N){
            System.out.println("Invalid input, k > N");
            return;
        }
        
         
        // get the combination by index 
        int combination[] = new int[k];
         
        // position of current index
        //  if (r = 1)              r*
        //  index ==>        0   |   1   |   2
        //  element ==>      A node   |   B node  |   C node
        int r = 0;      
        int index = 0;
         
        while(r >= 0){
            // possible indexes for 1st position "r=0" are "0,1,2" --> "A,B,C"
            // possible indexes for 2nd position "r=1" are "1,2,3" --> "B,C,D"
             
            
            if(index <= (N + (r - k))){
                    combination[r] = index;
                     
                // if we are at the last position add in subset and increase the index
                if(r == k-1){
 
                    //add to list subset the combination 
                   setSubset(combination);
                   if((this.subIndex=checkSubset())>=0){
                	   setIndexOfSubset(subIndex);
                	   return;
                    }
                   this.subset.clear();
                   this.coverValues.clear();
                   index++;                
                }
                else{
                    // select index for next position
                    index = combination[r]+1;
                    r++;                                        
                }
            }
            else{
                r--;
                if(r > 0)
                    index = combination[r]+1;
                else
                    index = combination[0]+1;   
            }           
        }
	}
	
	private void setSubset(int[] combination) {
		List<String> temp = new ArrayList<String>();
		for(int z = 0 ; z < combination.length;z++){
			temp.add(keys.get(combination[z]));
		}
		this.subset.add(temp);
		setCoverValues(subset.size()-1,combination);
		
	}
	
	
	//na vazw kai tous eautous tous
	private void setCoverValues(int j, int[] combination) {
		if(j<0)
			return;
		List<String> temp = new ArrayList<String>();
		for(int z = 0 ; z < combination.length;z++){
			for(int i=0;i<this.values.get(combination[z]).size();i++){
				if(!temp.contains(this.values.get(combination[z]).get(i)))
					temp.add(this.values.get(combination[z]).get(i));
			}
		}
		for(int l=0; l<subset.get(j).size();l++){
			if(!temp.contains(subset.get(j).get(l)))
				temp.add(subset.get(j).get(l));
		}
		this.coverValues.add(temp);
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
	
	private void setIndexOfSubset(int index) {
		this.subIndex=index;
		
	}
	
	private int getIndexOfSubset(){
		return this.subIndex;
	}
	
	public List<String> getSubset(){
		if(this.subset==null)
			return null;
		
		return this.subset.get(getIndexOfSubset());
	}
	
	public void printSubset(){
		List<String> list = getSubset();
		for(String s:list){
			System.out.println(s);
		}
	}

}
