import java.io.*;

public class DVDCollection {
	private int numdvds;
	private DVD[] dvdArray;
	private String sourceName;
	private boolean modified;
	
	public DVDCollection() {
		numdvds = 0;
		dvdArray = new DVD[7];
	}
	
	public String toString() {
		// have a result string
		String res = "";
		int lastIndex = dvdArray.length - 1;
		
		res += "numdvds = " + Integer.toString(numdvds) + "\n";
		res += "dvdArray.length = " + Integer.toString(dvdArray.length) + "\n";
		
		for (int i = 0; i < numdvds; i ++) {
			String index = Integer.toString(i);
			String title = dvdArray[i].getTitle();
			String rating = dvdArray[i].getRating();
			String runningTime = Integer.toString(dvdArray[i].getRunningTime());
			
			if (i == lastIndex) {
				res += "dvdArray[" + index + "] = " + title + "/" + rating + "/" + runningTime;
			} else {
				res += "dvdArray[" + index + "] = " + title + "/" + rating + "/" + runningTime + "\n";
			}
			

		}
		

		return res;
	}
	
	
	
	
}
