package DVD;
import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class DVDCollection {
	private int numdvds;
	private DVD[] dvdArray;
	private String sourceName;
	private boolean modified;
	
	/**
     * Creates a new DVD Collection with the specified details.
     * The collection starts with initializing numdvds to 0
     * The array of the collection is initialized to length seven
     */
	public DVDCollection() {
		numdvds = 0;
		dvdArray = new DVD[7];
	}
	
	/**
     * Returns the string representation of the DVD Collection.
     * 
     * @return String representation of the DVD Collection.
     */
	
	public String toString() {
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
	
	 /**
     * Adds a new DVD to the collection.
     * Modifies exisiting title in the collection
     * If the array of the collection is valid, double the size
     * 
     * @param runningTime The running time in minutes. It must be greater than zero.
     * @param rating The rating must be valid. It must be checked to see if its valid.
     * 
     */
	
	public void addOrModifyDVD(String title, String rating, String runningTime) {
		
		if (this.checkRating(rating) && Integer.parseInt(runningTime) > 0) {
			
			this.modifyDVDHelper(title, rating, runningTime);
			
			if (!this.modified) {
				if (numdvds == dvdArray.length) {
					this.doubleArraySize();
				}
				this.addDVDHelper(title, rating, runningTime);
			}
		}
	}
	
	 /**
     * Removes dvd from collection if it exists.
     *
     * @param title The title of the dvd is in all caps.
     */
	public void removeDVD(String title) {
		// iterate through the array
		for (int i = 0; i < this.numdvds; i ++) {
			DVD curr = this.dvdArray[i];
			
			if (curr.getTitle().equals(title)) {
				this.shiftDVDCollectionLeft(i);
				numdvds --;
				this.dvdArray[dvdArray.length - 1] = null;
				break;
			}
		}
	}
	
	
	 /**
     * Returns the dvds that match the rating input parameter.
     *
     * @param rating The rating must be present in one of the dvds
     * @return string concatenation of the valid dvds that match the rating input
     */
	public String getDVDsByRating(String rating) {
		String res = "";
		
		for (int i = 0; i < this.numdvds; i ++) {
			String currRating = this.dvdArray[i].getRating();
			if (currRating.equals(rating)) {
				res += this.dvdArray[i].getTitle() + "\n";
			}
		}
	
		return res;
	}
	
	 /**
     * Returns the total running time of all the dvds.
     *
     * @return totalTime The calculated total of all the dvds runningTime
     */
	
	public int getTotalRunningTime() {
		int totalTime = 0;
		
		for (int i = 0; i < this.numdvds; i ++) {
			totalTime += this.dvdArray[i].getRunningTime();
		}
		
		return totalTime;
	}
	
	 /**
     * Loads up the data line by line from the input filename.
     * Input file should contain valid data
     * 
     * This method reads each document line and creates/modifies a dvd object
     * 
     * This method initializes the dvdArray to an empty one if the input file is invalid
     *
     * @param filename The filename must be in the correct format
     */
	
	public void loadData(String filename) {
		this.sourceName = filename;
		File inFile = new File(this.sourceName);
		
		try {
			Scanner scanner = new Scanner(inFile);
			while (scanner.hasNextLine()) {
				String data = scanner.nextLine();
				data.trim();
			
				String[] dvdInfo = this.getDVDInfo(data);
				if (dvdInfo[2] == null) {
					throw new Exception("Corrupt data in DVD inputs");
				}
				this.addOrModifyDVD(dvdInfo[0], dvdInfo[1], dvdInfo[2]);
			}
			
			scanner.close();
			
		} catch (Exception e) {
			System.out.println(e);
			this.dvdArray = new DVD[7];
			this.numdvds = 0;
		}
	}
	
	
	/**
	 * Writes out to an exisiting file
	 * 
	 * @catch catches errors in attempting to writing to the sourceName file
	 */
	public void save() {
		File file = new File(this.sourceName);
		
		try {
			FileWriter myWriter = new FileWriter(this.sourceName);
			myWriter.write(this.outputDVDs());
			myWriter.close();
			
		} catch (IOException el) {
			el.printStackTrace();
		}
	}
	
	
	
	
	
	
	// ******** Additional helper methods ********
	
	/**
	 * This helper method formats the dvds for the save() method
	 * This helper takes advantage of each dvd's toString method
	 * 
	 * @return the formatted dvd information
	 */
	private String outputDVDs() {
		String res = "";
		
		for (int i = 0; i < this.numdvds; i ++) {
			res += this.dvdArray[i].toString() + "\n";
		}
		
		return res;
	}
	
	/**
	 * Parses a coma separated string into an array of DVD attributes
	 * 
	 * This helper method is used by the loadData class method to correctly
	 * extract the contents of each line being read
	 * 
	 * @param data This is the line that was read from the input file in loadData
	 * @return An array of strings that contain infromation on the current DVD being read
	 * 
	 */
	
	private String[] getDVDInfo(String data) {
		String[] dvdInfoArray = new String[3];
		int startIdx = 0;
		int j = 0;
		for (int i = 0; i < data.length(); i ++) {
			
			if (data.charAt(i) == ',') {
				
				String currData = "";
				int k = startIdx;
				
				while (k < i) {
					currData += data.charAt(k);
					k ++;
				}
				
				if (j < 3) {
					dvdInfoArray[j] = currData;
					j ++;
				}
				
				startIdx = i + 1;
			}
		}
		
		if (j < 3 && startIdx < data.length()) {
			String endData = "";
			
			while(startIdx < data.length()) {
				endData += data.charAt(startIdx);
				startIdx ++;
			}
			
			dvdInfoArray[j] = endData;
		}
		
		return dvdInfoArray;
	}
	
	
	/**
	 * Shifts the dvd array to the left after removing from the dvd collection
	 * This is a helper method for removeDVD
	 * 
	 * @param startIndex The index that we will start shifting the array over
	 * 
	 */
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
	
	/**
	 * This helper method returns a boolean of the ascii code being inserted
	 * The ascii code must be traced back to an uppercase letter
	 * 
	 * @param asciiCode The ascii code of the current letter being processed in findInsertionIndex
	 * @return True or False when it comes to finding out of the asciiCode belongs to a uppercase letter
	 */
	
	private Boolean isNotAlpha(int asciiCode) {
		return (asciiCode < 65 || asciiCode > 90);
	}
	
	
	/**
	 * This method compares every dvd title to the newest title 
	 * and sorts them in the correct spot
	 * 
	 * @param newDVD An instance of the newest dvd being added to the collection
	 * @return insertionIndex The correct index for the newest dvd to be inserted into
	 */
	private int findInsertionIndex(DVD newDVD) {
		int insertionIndex = 0;
		String newTitle = newDVD.getTitle().toUpperCase();
		
		for (int i = 0; i < this.numdvds; i ++) {
			String currTitle = dvdArray[i].getTitle().toUpperCase();
			int j = 0;
			int k = 0;
			
			while (j < currTitle.length() && k < newTitle.length()) {
				
				while (j < currTitle.length() && this.isNotAlpha((int) currTitle.charAt(j))) {
					j ++;
				}
				
				while (k < newTitle.length() && this.isNotAlpha((int) newTitle.charAt(k))) {
					k ++;
				}
				
				int newTitleCode = (int) newTitle.charAt(k);
				int currTitleCode = (int) currTitle.charAt(j);
				
				if (newTitleCode < currTitleCode) {
					return insertionIndex = i;
				} else if (newTitleCode > currTitleCode) {
					insertionIndex = i + 1;
					break;
				}
				
				j ++;
				k ++;
			}
		}
		
		return insertionIndex;
	}
	
	/**
	 * This method shifts over the contents of the dvd collection array
	 * There is swapping that achieves the end result
	 * 
	 * @param endIndex Is used as a stopping index as the dvd array is shifted over to the right
	 */
	
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
	
	
	/**
	 * Helper method to addOrModify class method to add a dvd to the collection
	 * 
	 * This method uses other helper methods to find the correct location to insert
	 * the newest dvd addition (insertionIndex)
	 * 
	 * The shiftDVDCollection is used to move over dvds for the newest addition
	 * 
	 * The insertionIndex is them used to assign that memory location to the newest dvd
	 * 
	 * Bookeeping by updating the number of dvds
	 * 
	 * 
	 */
	private void addDVDHelper(String title, String rating, String runningTime) {
		DVD newestDvd = new DVD(title, rating, Integer.parseInt(runningTime));

		int insertionIndex = this.findInsertionIndex(newestDvd);
		this.shiftDVDCollectionRight(insertionIndex);
		this.dvdArray[insertionIndex] = newestDvd;
	
		this.numdvds ++;
	}
	
	/**
	 * Doubles the size of the DVD collection array if it is full
	 * This helper method is used in addOrModify class method
	 * 
	 * A temp array is created an then is referenced by dvdArray
	 */
	private void doubleArraySize() {
		DVD[] tempArr;
		
		tempArr = new DVD[this.dvdArray.length * 2];
	
		for (int i = 0; i < this.dvdArray.length; i ++) {
			tempArr[i] = this.dvdArray[i];
		}
		
		this.dvdArray = tempArr;
	}
	
	/**
	 * Modifies an existing dvd information like rating and runningTime
	 * Updates the modified class attribute if the title exists in the collection
	 * 
	 * @param title The title of the DVD
	 * @param rating The rating of the DVD
	 * @param runningTime The runningTime of the DVD
	 */
	
	private void modifyDVDHelper(String title, String rating, String runningTime) {
		
		for (int i = 0; i < numdvds; i ++) {
			if (this.modified) {
				break;
			}
			if (title.toUpperCase().equals(dvdArray[i].getTitle())) {
				dvdArray[i].setRating(rating);
				dvdArray[i].setRunningTime(Integer.parseInt(runningTime));
				this.modified = true;
			}
		}
		
	}
	
	/**
	 * Constructs a hashtable to store the dvd ratings
	 * 
	 * @param rating The current input rating
	 * @return A boolean that concludes if the rating is of valid format or not
	 */
	
	private Boolean checkRating(String rating) {
		Set<String> tvRatings = new HashSet<>();
	    
	    // Add TV ratings to the set
	    tvRatings.add("G");
	    tvRatings.add("PG");
	    tvRatings.add("PG-13");
	    tvRatings.add("R");
	    tvRatings.add("NC-17");
	    
	    if (tvRatings.contains(rating)) {
	    	return true;
	    }
	    
	    return false;
	    
	}
	
}


