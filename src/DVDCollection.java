import java.io.*;
import java.util.Scanner;

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
			
			res += "dvdArray[" + index + "] = " + title + "/" + rating + "/" + runningTime + "min" + "\n";
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
	
	public String getDVDsByRating(String rating) {
		String res = "";
		
		for (int i = 0; i < this.numdvds; i ++) {
			if (this.dvdArray[i].getRating() == rating) {
				res += this.dvdArray[i].getTitle() + "\n";
			}
		}
	
		return res;
	}
	
	public int getTotalRunningTime() {
		int totalTime = 0;
		
		for (int i = 0; i < this.numdvds; i ++) {
			totalTime += this.dvdArray[i].getRunningTime();
		}
		
		return totalTime;
	}
	
	public void loadData(String filename) {
		// init the infile
		this.sourceName = filename;
		File inFile = new File(this.sourceName);
		// try to open the file input
		try {
			// scanner will help us read the line
			Scanner scanner = new Scanner(inFile);
			// checking to see if there exists a next line of text
			while (scanner.hasNextLine()) {
				// reading the current line
				String data = scanner.nextLine();
				// create a helper function to get the title rating and runningTime
				String[] dvdInfo = this.getDVDInfo(data);
				// check to see if data is corrupted, if the data is corrupted then stop intializing
				if (dvdInfo.length != 3) {
					break;
				}
				// use those three values to create a dvd by using the addOrModify method
				this.addOrModifyDVD(dvdInfo[0], dvdInfo[1], dvdInfo[2]);
			}
			
			scanner.close();
			
		} catch (Exception e) {
			System.out.println(e);
			// if the file cannot be found start with an empty array
			this.dvdArray = new DVD[7];
			this.numdvds = 0;
		}
	}
	
	public void save() {
		File file = new File(this.sourceName);
		
		try {
			if (file.createNewFile()) {
				FileWriter myWriter = new FileWriter(this.sourceName);
				myWriter.write(this.toString());
				myWriter.close();
			}
			
		} catch (IOException el) {
			el.printStackTrace();
		}
	}
	
	
	
	
	
	
	// ******** Additional helper methods ********
	
	private String[] getDVDInfo(String data) {
		// create the dvdInfo array with a length of 3
		String[] dvdInfoArray = new String[3];
		int j = 0;
		
		for (int i = 0; i < data.length(); i ++) {
			String currData = "";
			
			while (data.charAt(i) != ',') {
				currData += data.charAt(i);
				i ++;
			}
			dvdInfoArray[j] = currData;
			j ++;
		}
		
		return dvdInfoArray;
	}
	
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
		return (asciiCode >= 65 && asciiCode <= 90);
	}
	
	private int findInsertionIndex(DVD newDVD) {
		int insertionIndex = 0;
		String newTitle = newDVD.getTitle().toUpperCase();
		
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
		DVD newestDvd = new DVD(title, rating, Integer.parseInt(runningTime));

		int insertionIndex = this.findInsertionIndex(newestDvd);
		this.shiftDVDCollectionRight(insertionIndex);
		this.dvdArray[insertionIndex] = newestDvd;
	
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
			if (title.toUpperCase() == dvdArray[i].getTitle()) {
				dvdArray[i].setRating(rating);
				dvdArray[i].setRunningTime(Integer.parseInt(runningTime));
				this.modified = true;
			}
		}
		
	}
	
	
}
