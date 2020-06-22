package askhsh1;

import java.util.List;

public class BinarySearch {
	
	public BinarySearch(){
	}
	
	/* if x is present in table then returns 
    the count of occurrences of x, 
    otherwise returns -1. */
	public  int changedBinarySearch(List<Integer> table, int x, int n){
		// index of first occurrence of x in table
		int i; 
    
		// index of last occurrence of x in table
		int j; 
       
		/* get the index of first occurrence of x */
		i = firstSearch(table, 0, n-1, x, n);
   
		/* If x doesn't exist in table then return -1 */
		if(i == -1)
			return i;
      
		/* Else get the index of last occurrence of x. 
      	Note that we are only looking in the 
      	subarray after first occurrence */ 
		j = lastPosition(table, i, n-1, x, n);     
      
		/* return count */
		return j-i+1;
	}
   
	/* if x is present in table then returns the 
    index of FIRST occurrence of x in table, 
    otherwise returns -1 */
	private int firstSearch(List<Integer> table, int low, int high, int x, int n){
		if(high >= low)
		{
			 
			int mid = (low + high)/2;  
			if( ( mid == 0 || x > table.get(mid-1)) && table.get(mid) == x)
				return mid;
			else if(x > table.get(mid))
				return firstSearch(table, (mid + 1), high, x, n);
			else
				return firstSearch(table, low, (mid -1), x, n);
		}
		return -1;
	}
   
	/* if x is present in table then returns the 
    index of LAST occurrence of x in table, 
    otherwise returns -1 */
	private  int lastPosition(List<Integer> table, int low, int high, int x, int n){
		if(high >= low){   
			int mid = (low + high)/2; 
			if( ( mid == n-1 || x < table.get(mid+1)) && table.get(mid)== x )
				return mid;
			else if(x < table.get(mid))
				return lastPosition(table, low, (mid -1), x, n);
			else
				return lastPosition(table, (mid + 1), high, x, n);      
		}
		return -1;
	}

}
