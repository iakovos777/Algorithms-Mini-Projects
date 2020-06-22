package askhsh3;


import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;



public class ReadFile {
	
	private File file;
	private  HashMap<String,List<String>> graph;  
	
	public ReadFile(){
		this.graph= new HashMap<String,List<String>>();
	}
	
	public void chooseFile(){
		JFileChooser fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

    	fc.setDialogTitle("Select an input file");
		fc.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("text files", "txt");
		fc.addChoosableFileFilter(filter);
		
		int returnValue = fc.showOpenDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			file = fc.getSelectedFile();
			System.out.println(file.getAbsolutePath());
		}
	}
	public void read(){
		try {
    		file = new File(file.getAbsolutePath());
    	} catch(NullPointerException e)  {
    		System.err.println("Error! File not found.");
    	}
    	
    	
        BufferedReader br = null;

        try {
           
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        } catch(FileNotFoundException e) {
        	System.err.println("Error opening file!");
        	e.printStackTrace();
        }

        try {
        	
        	String value;
            String line;
            StringTokenizer st;
            while ((line = br.readLine()) != null) {
                st = new StringTokenizer(line.trim());
                
                if (st.hasMoreTokens()) {
                    line = st.nextToken();
                    //prwta psaxnw ama uparxei hdh to key sto hashmap kai ama uparxei->
                    if(graph.containsKey(line)){
                    	value = st.nextToken();
                    	graph.get(line).add(value);
                    }
                    else{
                    	value = st.nextToken();
                    	List<String> list  = new ArrayList<>();
                    	list.add(value);
                    	graph.put(line,list);
                    }
                    
                    //psaxnw ama uparxei hdh to key tou 2ou komvou pou diavazw sto grafo
                    if(graph.containsKey(value)){
                    	graph.get(value).add(line);
                    		
                    }
                    else{
                    	List<String> listValue  = new ArrayList<>();
                    	listValue.add(line);
                    	graph.put(value,listValue);
                    		
                    }
                    
                    
                        
                 }
             }
             
            
                
            	

        } catch (IOException e) {

            e.printStackTrace();

        }
        try {
        	if (br != null)
        		br.close();
        } catch (IOException ex) {
        	
        	System.err.println("Error closing file!");
        	ex.printStackTrace();

        }
    }
    
    
   

	public HashMap<String,List<String>> getGraph() {
        return this.graph;
    }
	
}
