import java.util.PriorityQueue;
import java.util.Comparator;
import java.io.File;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RainFall {
	private double rainTotal = 0.0;
	private double rainMax = 0.0;
	private int rainMaxMonth = 0;
	private double rainMin = 500.0;
	private int rainMinMonth = 0;
	private double rainAvg = 0.0;
	private PriorityQueue<Entry> pq = new PriorityQueue<Entry>();
	private PriorityQueue<Entry> pqAlt = new PriorityQueue<Entry>();
	private int inputLines = 0;
	
	public RainFall(String fileName) throws Exception {
		// TODO Auto-generated method stub
		double runningTotal = 0;
		
		// Figured out how to count lines in a text file from StackExchange:
		// https://stackoverflow.com/questions/26448352/counting-the-number-of-lines-in-a-text-file-java
		inputLines = (int) Files.lines(Paths.get(fileName)).count();
		
		
		File file = new File(fileName);
		Scanner inputFile = new Scanner(file);
		
		int[] dates = new int[inputLines];
		double[] totals = new double[inputLines];
		
		for (int i = 0; i < inputLines; i++) {
			String inputData = inputFile.nextLine();
			String[] monthAndTotSplit = inputData.split(" ");

			String total = monthAndTotSplit[1];
			
			double totalDbl = Double.parseDouble(total);
			String month = monthAndTotSplit[0];
			int monthInt = Integer.parseInt(month);
			///////////////////////////////////////////////////
			pq.add(new Entry(monthInt,totalDbl));
			///////////////////////////////////////////////////
		}
		
		for (int i = 0; i < inputLines; i++) {
			Entry ent = new Entry();
			ent = pq.poll();
			dates[i] = ent.d;
			totals[i] = ent.t;
			///////////////////////////////////////////////////
			pqAlt.add(new Entry(ent.d,ent.t));
			///////////////////////////////////////////////////
			if (ent.t < this.rainMin ){
				this.rainMin = ent.t;
				this.rainMinMonth = ent.d;
			}
			if (ent.t > this.rainMax){
				this.rainMax = ent.t;
				this.rainMaxMonth = ent.d;
			}
		runningTotal += ent.t;	
		}
		
		rainTotal = runningTotal;
		rainAvg = runningTotal/inputLines;
		//for(Entry ent : qp) {
		//	if (ent.t < this.rainMin )
		//		this.rainMin = ent.t;
		//		this.rainMinMonth = ent.d;
		//	if (ent.t > this.rainMax)
		//		this.rainMax = ent.t;
		//		this.rainMaxMonth = ent.d;
		//}
		
	}

	public class Entry implements Comparable<Entry> {
		int d;
		double t;

		public Entry() {
		}

		public Entry(int date, double total) {
			d = date;
			t = total;
		}

		public String toString() {
			return ("Month: " + d + " -- Total rainfall: " + t + " inch(es).");
		}

		public int compareTo(Entry b) {
			return Double.compare(this.d, b.d);
		}
	}
	
	public int getInputLines() { 
		return this.inputLines;
	}
	
	public void getTotal() {
		System.out.printf("The total amount of rainfall is %.2f inch(es).", rainTotal);
	}
	
	public double getAvg() {
		//System.out.println("The average total monthly rainfall is " + rainAvg + " inch(es).");
		return rainAvg;
	}
	
	public void getMin() {
		System.out.println("The minimum rainfall occurred in " + rainMinMonth + " and was " + rainMin + " inch(es).");
	}
	
	public void getMax() {
		System.out.println("The maximum rainfall occurred in " + rainMaxMonth + " and was " + rainMax + " inch(es).");
	}
	public void outputInOrder() {
		System.out.println("Total Monthly Rainfall Data (in chronological order):");
		if (pq.isEmpty()) {

			while (!pqAlt.isEmpty()) {
				Entry ent = pqAlt.remove();
				System.out.println(ent);
				pq.add(ent);
			}
		}
		else {
			while (!pq.isEmpty()) {
				Entry ent = pq.remove();
				System.out.println(ent);
				pqAlt.add(ent);
			}
		}
	}
}
