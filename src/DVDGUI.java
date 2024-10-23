import java.util.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 *  This class is an implementation of DVDUserInterface
 *  that uses JOptionPane to display the menu of command choices. 
 */

public class DVDGUI implements DVDUserInterface {
	 
	 private DVDCollection dvdlist; // This is an instance of the dvd collection
	 
	 public DVDGUI(DVDCollection dl) {
		 this.dvdlist = dl;
		 this.getDVDArray();
	 }
	 
	 public void processCommands() { // this is the interface method that is used from DVDUserInterface
		 JFrame frame = new JFrame("DVD Manager");
		 JPanel mainPanel = new JPanel(new BorderLayout());
			
		 JPanel buttonPanel = new JPanel();
		 JPanel dvdListPanel = new JPanel();
		 JPanel detailsPanel = new JPanel();
		 
		 JButton addButton = new JButton("Add DVD");
		 JButton displayButton = new JButton("Display DVDs");
		 JButton detailsButton = new JButton("Details");
		 
		 // Event listeners
		 
		 displayButton.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		           displayDVDs();
		        }
		    });
		 
		 buttonPanel.add(addButton);
		 buttonPanel.add(displayButton);
		 buttonPanel.add(detailsButton);
		 
		 mainPanel.add(buttonPanel);
		 frame.add(mainPanel);
		 
		 frame.setLocation(100,100);
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frame.setSize(600, 400);
		 frame.setVisible(true);
	 }

	private void doAddOrModifyDVD() {

		// Request the title
		String title = JOptionPane.showInputDialog("Enter title");
		if (title == null) {
			return;		// dialog was cancelled
		}
		title = title.toUpperCase();
		
		// Request the rating
		String rating = JOptionPane.showInputDialog("Enter rating for " + title);
		if (rating == null) {
			return;		// dialog was cancelled
		}
		rating = rating.toUpperCase();
		
		// Request the running time
		String time = JOptionPane.showInputDialog("Enter running time for " + title);
		if (time == null) {
		}
		
                // Add or modify the DVD (assuming the rating and time are valid
                dvdlist.addOrModifyDVD(title, rating, time);
                
                // Display current collection to the console for debugging
//                System.out.println("Adding/Modifying: " + title + "," + rating + "," + time);
//                System.out.println(dvdlist);
//		
	}
	
	private void doRemoveDVD() {

		// Request the title
		String title = JOptionPane.showInputDialog("Enter title");
		if (title == null) {
			return;		// dialog was cancelled
		}
		title = title.toUpperCase();
		
                // Remove the matching DVD if found
                dvdlist.removeDVD(title);
                
                // Display current collection to the console for debugging
//                System.out.println("Removing: " + title);
//                System.out.println(dvdlist);

	}
	
	private void doGetDVDsByRating() {

		// Request the rating
		String rating = JOptionPane.showInputDialog("Enter rating");
		if (rating == null) {
			return;		// dialog was cancelled
		}
		rating = rating.toUpperCase();
		
                String results = dvdlist.getDVDsByRating(rating);
//                System.out.println("DVDs with rating " + rating);
//                System.out.println(results);

	}

        private void doGetTotalRunningTime() {
                 
                int total = dvdlist.getTotalRunningTime();
//                System.out.println("Total Running Time of DVDs: ");
//                System.out.println(total);
                
        }

	private void doSave() {
		
		dvdlist.save();
		
	}
	
private void displayDVDs() {
	JFrame frame = new JFrame("All DVDs");
	JPanel mainPanel = new JPanel(new BorderLayout());
	ArrayList<String[]> dvds = getDVDArray();
	
	JPanel moviePanel = new JPanel();
	
	for (int i = 0; i < dvds.size(); i ++) {
		final int index = i;
		JButton currDVDBtn = new JButton(dvds.get(i)[0]);
		
		currDVDBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayDVDInfo(dvds.get(index)[0], dvds.get(index)[1], dvds.get(index)[2]);
			}
		});
		
		moviePanel.add(currDVDBtn);
	}
	
	mainPanel.add(moviePanel);
	frame.add(mainPanel);
	
	frame.setLocation(800, 100);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(600, 400);
	frame.setVisible(true);
	
}

private void displayDVDInfo(String title, String rating, String runningTime) {
	JFrame frame = new JFrame("Single DVD Info");
	JPanel mainPanel = new JPanel(new BorderLayout());
	
	JPanel infoPanel = new JPanel();
	
	JButton dvdTitleBtn = new JButton(title);
	JButton dvdRatingBtn = new JButton(rating);
	JButton dvdTotalTimeBtn = new JButton(runningTime);
	
	infoPanel.add(dvdTitleBtn);
	infoPanel.add(dvdRatingBtn);
	infoPanel.add(dvdTotalTimeBtn);
	
	mainPanel.add(infoPanel);
	frame.add(mainPanel);
	
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(600, 400);
	frame.setVisible(true);
}
	

// HELPER METHODS
	

private ArrayList<String[]> getDVDArray() {
	String[] dvdRatings = {"G", "PG", "PG-13", "R", "NC-17"};
	ArrayList<String[]> allDVDs = new ArrayList<>();
	String dvdCollectionInfo = dvdlist.toString();
	String[] lines = dvdCollectionInfo.split("\n");
	
	
	
	for (String line: lines) {
		if (line.contains("dvdArray[")) {
			String[] sections = line.split("=");
			
			if (sections.length == 2) {
				String rightSection = sections[1].trim();
				String[] movieDetails = rightSection.split("/");
				allDVDs.add(movieDetails);
			}
		}
	}
	
	
	return allDVDs;
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		
}