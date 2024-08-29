
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
	
	
	
}
