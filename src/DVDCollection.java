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
		if (rating.length() > 0 && Integer.parseInt(runningTime) > 0) {
			// call the modifyDVD helper method
			this.modifyDVDHelper(title, rating, runningTime);
			
			// check to see if the modified attribute is true because if it is not
			if (!this.modified) {
				// check to see if we need to double the size of our dvdArray
				if (numdvds == dvdArray.length) {
					this.doubleArraySize();
				}
				this.addDVDHelper(title, rating, runningTime);
			}
		}
	}
	
	public void removeDVD(String title) {
		// iterate through the array
		for (int i = 0; i < this.numdvds; i ++) {
			DVD curr = this.dvdArray[i];
			
			if (curr.getTitle() == title.toUpperCase()) {
				this.shiftDVDCollectionLeft(i);
				numdvds --;
				this.dvdArray[dvdArray.length - 1] = null;
				break;
			}
		}
	}
	
	
	
	
	// Additional helper methods ********
	private void shiftDVDCollectionLeft(int startIndex) {
		int p1 = startIndex;
		int p2 = p1 + 1;
		
		while (p2 < this.dvdArray.length) {
			DVD tmp = this.dvdArray[p1];
			this.dvdArray[p1] = this.dvdArray[p2];
			this.dvdArray[p2] = tmp;
			p1 ++;
			p2 ++;
		}
	}
	
	private Boolean isAlpha(int asciiCode) {
		return (asciiCode >= 97 && asciiCode <= 122);
	}
	
	private Integer findInsertionIndex(DVD newDVD) {
		int insertionIndex = 0;
		String newTitle = newDVD.getTitle().toLowerCase();
		
		for (int i = 0; i < numdvds; i ++) {
			String currTitle = dvdArray[i].getTitle().toLowerCase();
			int j = 0;
			int k = 0;
			
			while (j < currTitle.length() && k < newTitle.length()) {
				
				while (j < currTitle.length() && !this.isAlpha((int) currTitle.charAt(j))) {
					j ++;
				}
				
				while (k < newTitle.length() && !this.isAlpha((int) newTitle.charAt(k))) {
					k ++;
				}
				
				if ((int) newTitle.charAt(k) > (int) currTitle.charAt(j)) {
					insertionIndex = i;
					break;
				}
				
				j ++;
				k ++;
			}
		}
		
		return insertionIndex;
	}
	
	private void shiftDVDCollectionRight(int endIndex) {
		int p1 = this.numdvds - 1;
		int p2 = this.numdvds;
		
		while (p1 >= endIndex) {
			DVD tmp = this.dvdArray[p1];
			this.dvdArray[p1] = this.dvdArray[p2];
			this.dvdArray[p2] = tmp;
			p1 --;
			p2 --;
		}
	}
	
	private void addDVDHelper(String title, String rating, String runningTime) {
		// create the new dvd instance and parse the runningTime because the constructor expects an integer for that input
		DVD newestDvd = new DVD(title, rating, Integer.parseInt(runningTime));
		
		// find position
		int insertionIndex = this.findInsertionIndex(newestDvd);
		// shift everything over up to the correct position
		this.shiftDVDCollectionRight(insertionIndex);
		// insert into correct position
		this.dvdArray[insertionIndex] = newestDvd;
		// update number of dvds
		this.numdvds ++;
	}
	
	private void doubleArraySize() {
		DVD[] tempArr;
		
		tempArr = new DVD[this.dvdArray.length * 2];
	
		for (int i = 0; i < this.dvdArray.length; i ++) {
			tempArr[i] = this.dvdArray[i];
		}
		
		this.dvdArray = tempArr;
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
