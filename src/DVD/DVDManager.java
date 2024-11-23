package DVD;
import java.util.*;

import javax.swing.JOptionPane;

public class DVDManager {

public static void main(String[] args) {
		DVDUserInterface dlInterface;
		DVDCollection dl = new DVDCollection();
//		String filename = JOptionPane.showInputDialog("Enter filename");
		String filename = "dvddata.txt";
		
		if (filename == null) return;
		
		filename = filename.toUpperCase();
		dl.loadData(filename);
		
		dlInterface = new DVDGUI(dl);
		dlInterface.processCommands();
	}
}
