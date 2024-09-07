
public class DVD {
	
	private String title;
	private String rating;
	private int runningTime;
	
	public DVD(String dvdTitle, String dvdRating, int dvdRunningTime) {
		this.title = dvdTitle;
		this.rating = dvdRating;
		this.runningTime = dvdRunningTime;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getRating() {
		return this.rating;
	}
	
	public int getRunningTime() {
		return this.runningTime;
	}
	
	public void setTitle(String newTitle) {
		this.title = newTitle;
	}
	
	public void setRating(String newRating) {
		this.rating = newRating;
	}
	
	public void setRunningTime(int newRunningTime) {
		this.runningTime = newRunningTime;
	}
	
	public String toString() {
		return this.getTitle() + "," + this.getRating() + "," + this.getRunningTime() + "min";
	}
	
}
