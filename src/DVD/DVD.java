package DVD;

public class DVD {
	
	private String title;
	private String rating;
	private int runningTime;
	
	 /**
     * Creates a new DVD with the specified details.
     * 
     * @param title The title of the DVD.
     * @param rating The rating of the DVD.
     * @param runningTime The running time of the DVD in minutes.
     */
	public DVD(String dvdTitle, String dvdRating, int dvdRunningTime) {
		this.title = dvdTitle;
		this.rating = dvdRating;
		this.runningTime = dvdRunningTime;
	}
	
	/**
     * Gets the title of the DVD.
     * 
     * @return The title of the DVD.
     */
	public String getTitle() {
		return this.title;
	}
	
	/**
     * Gets the rating of the DVD.
     * 
     * @return The rating of the DVD.
     */
	public String getRating() {
		return this.rating;
	}
	
	/**
     * Gets the runningTime of the DVD.
     * 
     * @return The runningTime of the DVD.
     */
	
	public int getRunningTime() {
		return this.runningTime;
	}
	
	/**
     * Sets the title of the DVD.
     * 
     * @param title of the DVD.
     */
	
	public void setTitle(String newTitle) {
		this.title = newTitle;
	}
	
	/**
     * Sets the rating of the DVD.
     * 
     * @param rating of the DVD.
     */
	
	public void setRating(String newRating) {
		this.rating = newRating;
	}
	
	/**
     * Sets the runningTime of the DVD.
     * 
     * @param runningTime of the DVD.
     */
	
	public void setRunningTime(int newRunningTime) {
		this.runningTime = newRunningTime;
	}
	
	/**
     * Returns the string representation of the DVD.
     * 
     * @return String representation of the DVD in the format "Title,Rating,RunningTime".
     */
	
	public String toString() {
		return this.getTitle() + "," + this.getRating() + "," + this.getRunningTime();
	}
	
}
