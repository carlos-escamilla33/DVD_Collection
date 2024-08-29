import java.io.*;

public class DVDCollection {
	private int numdvds;
	private DVD[] dvdArray;
	private String sourceName;
	private boolean modified;
	
	public String toString() {
		// have a result string
		String res = "";
		
		res += "numdvds = " + Integer.toString(numdvds) + "\n";
		res += "dvdArray.length = " + Integer.toString(dvdArray.length) + "\n";
		
		for (int i = 0; i < numdvds; i ++) {
			String index = Integer.toString(i);
			String title = dvdArray[i].getTitle();
			String rating = dvdArray[i].getRating();
			String runningTime = Integer.toString(dvdArray[i].getRunningTime());
			
			res += "dvdArray[" + index + "] = " + title + "/" + rating + "/" + runningTime + "\n";
		}
		

		return res;
		
	}
}
