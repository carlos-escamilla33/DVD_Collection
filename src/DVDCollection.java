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
				res += "dvdArray[" + index + "] = " + title + "/" + rating + "/" + runningTime + "min";
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
			this.modifyDVDHelper(title, rating, runningTime);
			
			// check to see if the modified attribute is true because if it is not
			if (!this.modified) {
				// check to see if we need to double the size of our dvdArray
				if (numdvds == dvdArray.length) {
					DVD[] tempArr;
					
					tempArr = new DVD[this.dvdArray.length * 2];
					// might have to go back and alter this
					System.arraycopy(this.dvdArray, 0, tempArr, 0, this.dvdArray.length);
					this.dvdArray = tempArr;
				}
				this.addDVDHelper(title, rating, runningTime);
			}
		}
	}
	
	
	// Additional helper methods
	private Integer findInsertionIndex(DVD newDVD) {
		int correctIdx = 0;
		
		for (int i = 0; i < dvdArray.length; i ++) {
			String currTitle = dvdArray[i].getTitle();
			String newTitle = newDVD.getTitle();
			
			int j = 0;
			int k = 0;
			
			while (j < currTitle.length() && k < newTitle.length()) {
				int currLetterAscii = (int) currTitle.charAt(j);
				int newLetterAscii = (int) newTitle.charAt(k);
				
			}
			
		}
		
		
		return 1;
	}
	
	private void addDVDHelper(String title, String rating, String runningTime) {
		// create the new dvd instance and parse the runningTime because the constructor expects an integer for that input
		DVD newestDvd = new DVD(title, rating, Integer.parseInt(runningTime));
		
		// iterate through the dvdArr
		// find position
		// shift everything over
		// insert into correct position
	}
	
	private void modifyDVDHelper(String title, String rating, String runningTime) {
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
