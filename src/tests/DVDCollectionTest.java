package tests;
import static org.junit.Assert.*;

import org.junit.Test;

import DVD.DVDCollection;

import java.io.File;


public class DVDCollectionTest {

    @Test
    public void testConstructor() {
        DVDCollection collection = new DVDCollection();
        String result = collection.toString();
        
        assertTrue(result.contains("numdvds = 0"));
        assertTrue(result.contains("dvdArray.length = 7"));
    }

    @Test
    public void testAddOrModifyDVD() {
        DVDCollection collection = new DVDCollection();
        collection.addOrModifyDVD("Inception", "PG-13", "148");
        String result = collection.toString();
        
        assertTrue(result.contains("numdvds = 1"));
        assertTrue(result.contains("dvdArray[0] = Inception/PG-13/148"));
    }

    @Test
    public void testRemoveDVD() {
        DVDCollection collection = new DVDCollection();
        
        collection.addOrModifyDVD("Inception", "PG-13", "148");
        collection.addOrModifyDVD("Interstellar", "PG-13", "169");
        collection.removeDVD("Inception");

        String result = collection.toString();
        
        assertTrue(result.contains("numdvds = 1"));
        assertTrue(result.contains("dvdArray[0] = Interstellar/PG-13/169"));
        assertFalse(result.contains("Inception"));
    }

    @Test
    public void testGetDVDsByRating() {
        DVDCollection collection = new DVDCollection();
        
        collection.addOrModifyDVD("Inception", "PG-13", "148");
        collection.addOrModifyDVD("Interstellar", "PG-13", "169");
        collection.addOrModifyDVD("The Lion King", "G", "88");

        String result = collection.getDVDsByRating("PG-13");
        
        assertTrue(result.contains("Inception"));
        assertTrue(result.contains("Interstellar"));
        assertFalse(result.contains("The Lion King"));
    }

    @Test
    public void testGetTotalRunningTime() {
        DVDCollection collection = new DVDCollection();
        
        collection.addOrModifyDVD("Inception", "PG-13", "148");
        collection.addOrModifyDVD("Interstellar", "PG-13", "169");
        int totalTime = collection.getTotalRunningTime();
        assertEquals(317, totalTime);
    }

    @Test
    public void testToString() {
        DVDCollection collection = new DVDCollection();
        
        collection.addOrModifyDVD("Inception", "PG-13", "148");
        
        String result = collection.toString();
        
        assertTrue(result.contains("numdvds = 1"));
        assertTrue(result.contains("dvdArray[0] = Inception/PG-13/148"));
    }

    @Test
    public void testLoadDataInvalidFile() {
        DVDCollection collection = new DVDCollection();
        
        collection.loadData("invalidDVDs.txt");
        
        String result = collection.toString();
        
        assertTrue(result.contains("numdvds = 0"));
        assertTrue(result.contains("dvdArray.length = 7"));
    }

    @Test
    public void testSave() {
        DVDCollection collection = new DVDCollection();
        
        collection.addOrModifyDVD("Inception", "PG-13", "148");
        collection.loadData("outputDVDs.txt");
        collection.save();

        File savedFile = new File("outputDVDs.txt");
        assertTrue(savedFile.exists());
    }
}
