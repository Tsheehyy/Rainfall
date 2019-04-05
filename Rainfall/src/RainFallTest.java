
public class RainFallTest {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		RainFall rf = new RainFall("rainfalldata.txt");
		//rf.rainFall("rainfalldata.txt");
		
		rf.outputInOrder();
		rf.getMin();
		rf.getMax();
		System.out.printf("On average, the total monthly rainfall "
				+ "over %d months is %.2f inch(es).\n", rf.getInputLines(),rf.getAvg());
		rf.getTotal();
		
		
	}

}
