package tests;
import static org.junit.Assert.*;
import org.junit.Test;
import DVD.DVD;

public class DVDTest {

    @Test
    public void testConstructor() {
        DVD dvd = new DVD("Inception", "PG-13", 148);
        
        assertEquals("Inception", dvd.getTitle());
        assertEquals("PG-13", dvd.getRating());
        assertEquals(148, dvd.getRunningTime());
    }

    @Test
    public void testGetTitle() {
        DVD dvd = new DVD("Inception", "PG-13", 148);
        
        assertEquals("Inception", dvd.getTitle());
    }

    @Test
    public void testSetTitle() {
        DVD dvd = new DVD("Inception", "PG-13", 148);
        
        dvd.setTitle("Interstellar");
        assertEquals("Interstellar", dvd.getTitle());
    }

    @Test
    public void testGetRating() {
        DVD dvd = new DVD("Inception", "PG-13", 148);
        
        assertEquals("PG-13", dvd.getRating());
    }

    @Test
    public void testSetRating() {
        DVD dvd = new DVD("Inception", "PG-13", 148);
        
        dvd.setRating("R");
        assertEquals("R", dvd.getRating());
    }

    @Test
    public void testGetRunningTime() {
        DVD dvd = new DVD("Inception", "PG-13", 148);
        
        assertEquals(148, dvd.getRunningTime());
    }

    @Test
    public void testSetRunningTime() {
        DVD dvd = new DVD("Inception", "PG-13", 148);
        
        dvd.setRunningTime(169);
        assertEquals(169, dvd.getRunningTime());
    }

    @Test
    public void testToString() {
        DVD dvd = new DVD("Inception", "PG-13", 148);
        assertEquals("Inception,PG-13,148", dvd.toString());
        
        dvd.setTitle("Dunkirk");
        dvd.setRating("PG");
        dvd.setRunningTime(106);
        assertEquals("Dunkirk,PG,106", dvd.toString());
    }
}
