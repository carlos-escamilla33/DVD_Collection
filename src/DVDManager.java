import java.util.*;

import javax.swing.JOptionPane;

public class DVDManager {

public static void main(String[] args) {
		DVDGUI dlInterface;
		DVDCollection dl = new DVDCollection();
		String filename = JOptionPane.showInputDialog("Enter filename");
		
		if (filename == null) return;
		
		filename = filename.toUpperCase();
		dl.loadData(filename);
		
		dlInterface = new DVDGUI(dl);
	}
}
