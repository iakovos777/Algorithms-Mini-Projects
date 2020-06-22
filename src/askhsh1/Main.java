package askhsh1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class Main {

	
	public static void main(String[] args){
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
		Integer k = Integer.parseInt(JOptionPane.showInputDialog(null,"Give number to search in array")); 
		List<Integer> list = new ArrayList<>();
		try {
			list=Utilities.convertFileSequenceToList(file);
		} catch (IOException e) {
			System.err.println("Error! Propably file null ");
		}
		BinarySearch bs= new BinarySearch();
		int hasFind=bs.changedBinarySearch(list,k,list.size());
		if(hasFind==-1){
			System.out.println("Element "+k+" doesn't exist");
		}
		else{
			System.out.println("Element "+k+" exists in table: "+hasFind+" times");
		}
	}
}
