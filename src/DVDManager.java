import java.util.*;

import javax.swing.JOptionPane;

public class DVDManager {

public static void main(String[] args) {
		
		DVDUserInterface dlInterface; // what does this do? This is an interface that has the obligation of using the processCommands
		DVDCollection dl = new DVDCollection(); // this is the code I wrote to the DVD class and DVD Collection class
		String filename = JOptionPane.showInputDialog("Enter filename");
		
		if (filename == null) return;
		
		filename = filename.toUpperCase();
		dl.loadData(filename);
		
		dlInterface = new DVDGUI(dl);
//		dlInterface.processCommands();

//		String interfaceType = scan.nextLine();
//		if (interfaceType.equals("C")) {
//			dlInterface = new DVDConsoleUI(dl);
//			dlInterface.processCommands();
//		} else if (interfaceType.equals("G")) {
//			dlInterface = new DVDGUI(dl);
//			dlInterface.processCommands();
//		} else {
//			System.out.println("Unrecognized interface type. Program exiting.");
//			System.exit(0);
//		}
		
	}
}
