package Harjoitustyo.test;
// Generated by ComTest BEGIN
import Harjoitustyo.Ryhma;
import static org.junit.Assert.*;
import org.junit.*;
import static Harjoitustyo.Ryhma.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2021.04.22 11:42:34 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class RyhmaTest {



  // Generated by ComTest BEGIN
  /** testMain135 */
  @Test
  public void testMain135() {    // Ryhma: 135
    Ryhma r = new Ryhma(); 
    assertEquals("From: Ryhma line: 138", "Ryhmälle ei ole määritelty nimeä!", r.getNimi()); 
    assertEquals("From: Ryhma line: 139", "Ryhmälle ei ole määritelty tehtävää!", r.getTehtava()); 
    r.lisaaNimi("Rekkaryhmä"); 
    assertEquals("From: Ryhma line: 141", "Rekkaryhmä", r.getNimi()); 
    r.lisaaTehtava("Tavaroiden ja ihmisten kuljettaminen"); 
    assertEquals("From: Ryhma line: 143", "Tavaroiden ja ihmisten kuljettaminen", r.getTehtava()); 
    assertEquals("From: Ryhma line: 144", 0, r.getRid()); 
    assertEquals("From: Ryhma line: 145", 0, r.getSotilaatlkm()); 
    assertEquals("From: Ryhma line: 146", false, r.lisaaJasen(1)); 
    assertEquals("From: Ryhma line: 147", 1, r.getSotilaatlkm()); 
    r.poistaJasen(1); 
    assertEquals("From: Ryhma line: 149", 0, r.getSotilaatlkm()); 
  } // Generated by ComTest END
}