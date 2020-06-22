package askhsh2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class Main {

	public static void main(String[] args) {
		File file = null;
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
    	try {
    		file = new File(file.getAbsolutePath());
    	} catch(NullPointerException e)  {
    		System.err.println("Error! File not found.");
    	}
    	List<List<Integer>> list = new ArrayList<>();
    	try {
			list=Utilities.convertFileMatrixToListOfLists(file);
		} catch (IOException e) {
			System.err.println("Error! Propably file null ");
		}
    	
    	
    	TrainTrip tr = new TrainTrip();
    	int c = tr.cost(list);
    	System.out.println("---------------------");
    	System.out.println("Min cost is: "+c);
    	tr.printPath();
	}

}
