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
	ArrayList<String> dvds = getDVDArray();
	
	JPanel moviePanel = new JPanel();
	
	for (String dvd: dvds) {
		System.out.println(dvd);
		moviePanel.add(new JButton(dvd));
	}
	
	mainPanel.add(moviePanel);
	frame.add(mainPanel);
	
	frame.setLocation(420, 100);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(600, 400);
	frame.setVisible(true);
	
}
	

// HELPER METHODS
	

private ArrayList<String> getDVDArray() {
	String[] dvdRatings = {"G", "PG", "PG-13", "R", "NC-17"};
	ArrayList<String> allDVDs = new ArrayList<>();
	
	
	for (String rating : dvdRatings) {
		String currDVDsByRating = dvdlist.getDVDsByRating(rating);
		String[] movieArray = currDVDsByRating.split("\\n");
		
		for (String movie : movieArray) {
			allDVDs.add(movie);
		}
	}
	
	return allDVDs;
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		
}