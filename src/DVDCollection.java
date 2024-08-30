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
				res += "dvdArray[" + index + "] = " + title + "/" + rating + "/" + runningTime + "min" + "\n";
			} else {
				res += "dvdArray[" + index + "] = " + title + "/" + rating + "/" + runningTime + "min" + "\n";
			}
		}
		

		return res;
	}
	
	public void addOrModifyDVD(String title, String rating, String runningTime) {
		// check to see if the rating and running time is valid
		if (rating.length() > 0 && runningTime.length() > 0) {
			// call the modifyDVD helper method
			this.modifyDVD(title, rating, runningTime);
			
			// check to see if the modified attribute is true because if it is not
			if (!this.modified) {
				// check to see if we need to double the size of our dvdArray
				// add the dvd to the collection in abc order
			}
		}
	}
	
	
	// Additional helper methods
	private void modifyDVD(String title, String rating, String runningTime) {
		for (int i = 0; i < numdvds; i ++) {
			if (this.modified) {
				break;
			}
			if (title == dvdArray[i].getTitle()) {
				dvdArray[i].setRating(rating);
				dvdArray[i].setRunningTime(Integer.parseInt(runningTime));
				this.modified = true;
			}
		}
		
	}
	
	
}
