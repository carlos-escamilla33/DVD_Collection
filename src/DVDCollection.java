import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

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
	
	public int getTotalRunningTime() {
		int totalTime = 0;
		
		for (int i = 0; i < this.numdvds; i ++) {
			totalTime += this.dvdArray[i].getRunningTime();
		}
		
		return totalTime;
	}
	
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
	
	private String outputDVDs() {
		String res = "";
		
		for (int i = 0; i < this.numdvds; i ++) {
			res += this.dvdArray[i].toString() + "\n";
		}
		
		return res;
	}
	
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
	
	private Boolean isNotAlpha(int asciiCode) {
		return (asciiCode < 65 || asciiCode > 90);
	}
	
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
			if (title.toUpperCase().equals(dvdArray[i].getTitle())) {
				dvdArray[i].setRating(rating);
				dvdArray[i].setRunningTime(Integer.parseInt(runningTime));
				this.modified = true;
			}
		}
		
	}
	
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


