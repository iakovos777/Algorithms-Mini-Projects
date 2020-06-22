package askhsh3;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;







import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public class Main {

	public static void main(String[] args) {
		
		ArrayList<String> path = new ArrayList<String>();
		boolean ok1 = false, ok2 = false;
		String start = null, end = null;
		ReadFile rf = new ReadFile();
        rf.chooseFile();
        rf.read();
        HashMap<String,List<String>> graph = rf.getGraph();
       while(!ok1 && !ok2){
        	start = JOptionPane.showInputDialog(null,"Give name of source node");
        	if(graph.containsKey(start))
        		ok1=true;
        	end = JOptionPane.showInputDialog(null,"Give name of destination node");
        	if(graph.containsKey(end))
        		ok2=true;
        }
      
       BFS bfs = new BFS();
       path=bfs.searchPath(graph,start,end);
       path=bfs.reverse(path);
       String source= new String();
       for(int i=0;i<path.size();i++){
    	   if(i>0){
    		   source+="--->";
    		   System.out.print("--->");
    	   }
    	   source+=path.get(i);
    	   System.out.print(path.get(i));
       }
       /* GRAFIKA GIA TO OUTPUT
       JFrame frame = new JFrame("Path");
       frame.setMinimumSize(new Dimension(500,400));
       JLabel label =new JLabel(source);
       frame.getContentPane().add(label);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.pack();
       frame.setVisible(true);
       */
       
   }    
}
