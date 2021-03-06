package fxHarjoitustyo.test;
// Generated by ComTest BEGIN
import Harjoitustyo.*;
import java.io.IOException;
import static org.junit.Assert.*;
import org.junit.*;
import static fxHarjoitustyo.PaavalikkoController.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2021.04.15 21:56:38 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class PaavalikkoControllerTest {



  // Generated by ComTest BEGIN
  /** testTallennaJaokset226 */
  @Test
  public void testTallennaJaokset226() {    // PaavalikkoController: 226
    String[] jaokset = new String[] { "Ekajaos", "Tokajaos", "Kolmasjaos"} ; 
    try {
    tallennaJaokset(jaokset, "jaosTest.dat"); 
    } catch (IOException e) {
    e.printStackTrace(); 
    }
    assertEquals("From: PaavalikkoController line: 235", "Ekajaos Tokajaos Kolmasjaos ", tiedostonLuku("jaosTest.dat")); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testTallennaRyhmat259 */
  @Test
  public void testTallennaRyhmat259() {    // PaavalikkoController: 259
    String[] ryhmat = new String[]{ "Rötinäryhmä", "Munkkiryhmä", "Moveryhmä"} ; 
    try {
    tallennaRyhmat(ryhmat, "ryhmaTest.dat"); 
    } catch (IOException e) {
    e.printStackTrace(); 
    }
    assertEquals("From: PaavalikkoController line: 268", "Rötinäryhmä Munkkiryhmä Moveryhmä ", tiedostonLuku("ryhmaTest.dat")); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testTallennaJasenet294 */
  @Test
  public void testTallennaJasenet294() {    // PaavalikkoController: 294
    Jasen[] jasenet = new Jasen[]{ new Jasen(), new Jasen(), new Jasen()} ; 
    jasenet[0].lisaaNimi("Hatti","Vattinen"); 
    jasenet[1].lisaaNimi("Rölli","Peikko"); 
    jasenet[2].lisaaNimi("Uno","Turhapuro"); 
    try {
    tallennaJasenet(jasenet, "sotilasTest.dat"); 
    } catch (IOException e) {
    e.printStackTrace(); 
    }
    assertEquals("From: PaavalikkoController line: 306", "Hatti|Vattinen|0|0|0|0|Sotilasarvo puuttuu!|Sotilaan tehtävä puuttuu!|0|Sotilaalle ei ole kirjattu lisätietoa.| Rölli|Peikko|0|0|0|0|Sotilasarvo puuttuu!|Sotilaan tehtävä puuttuu!|0|Sotilaalle ei ole kirjattu lisätietoa.| Uno|Turhapuro|0|0|0|0|Sotilasarvo puuttuu!|Sotilaan tehtävä puuttuu!|0|Sotilaalle ei ole kirjattu lisätietoa.| ", tiedostonLuku("sotilasTest.dat")); 
  } // Generated by ComTest END
}