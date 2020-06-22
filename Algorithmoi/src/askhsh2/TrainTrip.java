package askhsh2;


import java.util.ArrayList;
import java.util.List;

public class TrainTrip {
	
	private static int INF = Integer.MAX_VALUE;
	private List<Integer> path;
	
	
	public TrainTrip(){
		path = new ArrayList<Integer>();
	}
		
	
	public int cost(List<List<Integer>> list) {
		if(list==null)
			return -1;
		
		
		int n = list.size();
		int d[] = new int[n];
		for(int k=0;k<n;k++){
			d[k]=INF;
		}
		d[0]=0;
		
		for(int i=0;i<n;i++){
			for(int j=i+1;j<n;j++){
				if (d[j] > d[i] + list.get(i).get(j))
	                 d[j] = d[i] + list.get(i).get(j);
			}
		}
		for(int k=0; k<n; k++){
			if(d[n-1]>d[k])
				path.add(k);
		}
		path.add(n-1);
		return d[n-1];
	}
	
	public List<Integer> getPath(){
		return this.path;
	}
	
	public void printPath(){
		if(this.path==null){
			System.out.println("There isn't min cost");
    	}
		
		else{
			System.out.println("Path is: ");
			for(int i=0;i<path.size();i++){
				if(i==path.size()-1)
					System.out.print(path.get(i));
				else
					System.out.print(path.get(i)+"--->");
			}
		}
		
	}
}
