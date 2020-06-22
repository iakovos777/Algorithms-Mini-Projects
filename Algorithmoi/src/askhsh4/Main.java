package askhsh4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;





public class Main {

	public static void main(String[] args) {
		
		ReadFile rf = new ReadFile();
        rf.chooseFile();
        rf.read();
        HashMap<String,List<String>> graph = rf.getGraph();
        System.out.println("Size of graph: "+graph.size());
        System.out.println("------------BRUTE FORCE APPROACH-------");
        long startTimeBrute = System.nanoTime();
        BruteForceSearch brute = new BruteForceSearch();
        brute.search(graph);
        long endTimeOfBrute   = System.nanoTime();
        System.out.println("NODES:");
        brute.printSubset();
        long totalTimeBrute = endTimeOfBrute - startTimeBrute;
		System.out.println("Elapsed time in milliseconds of brute force: "+totalTimeBrute/ 1000000.0+" miliseconds");
		System.out.println("------------END OF BRUTE FORCE-------");
		System.out.println("------------GREEDY APPROACH-------");
		long startGreedy = System.nanoTime();
        GreedyApproach gr = new GreedyApproach();
        gr.greedy(graph);
        long endTimeOfGreedy   = System.nanoTime();
        ArrayList<String> cover=gr.getCoverNodes();
        System.out.println("NODES:");
        for(int i=0; i<cover.size();i++){
        	System.out.println(cover.get(i));
        }
        long totalTimeGreedy = endTimeOfGreedy - startGreedy;
        System.out.println("Elapsed time in milliseconds of greedy: "+totalTimeGreedy/ 1000000.0+" miliseconds");
        System.out.println("------------END OF GREEDY-------");
	}

}
