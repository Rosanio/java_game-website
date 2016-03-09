import java.util.*;
import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class TamagotchiTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void tamagotchi_instantiatesCorrectly_true() {
    Tamagotchi myPet = new Tamagotchi("Moneybags");
    assertEquals("Moneybags", myPet.getName());
    assertEquals(4, myPet.getHungerLevel());
    assertEquals(15, myPet.getSleepLevel());
    assertEquals(8, myPet.getHappyLevel());
    assertTrue(myPet.isAlive());
  }

  @Test
  public void tamagotchi_statusAdjustAccordinglyWhenFed_true(){
    Tamagotchi myPet = new Tamagotchi("Moneybags");
    myPet.updateOnFeed();
    assertEquals(7, myPet.getHungerLevel());
    assertEquals(14, myPet.getSleepLevel());
    assertEquals(9, myPet.getHappyLevel());
  }

  @Test
  public void tamagotchi_capsAtMaxSleepStat_true(){
    Tamagotchi myPet = new Tamagotchi("Moneybags");
    myPet.updateOnFeed();
    myPet.updateOnFeed();
    myPet.updateOnFeed();
    myPet.updateOnFeed();
    assertEquals(15, myPet.getHungerLevel());
    assertEquals(11, myPet.getSleepLevel());
    assertEquals(12, myPet.getHappyLevel());
  }

  @Test
  public void tamagotchi_capsAtMaxPlayStat_true(){
    Tamagotchi myPet = new Tamagotchi("Moneybags");
    myPet.updateOnPlay();
    myPet.updateOnPlay();
    myPet.updateOnPlay();
    assertEquals(0, myPet.getHungerLevel());
    assertEquals(6, myPet.getSleepLevel());
    assertEquals(15, myPet.getHappyLevel());
  }

  @Test
  public void tamagotchi_adjustsStatsAfterPlaying_true() {
    Tamagotchi myPet = new Tamagotchi("Moneybags");
    myPet.updateOnPlay();
    assertEquals(2, myPet.getHungerLevel());
    assertEquals(12, myPet.getSleepLevel());
    assertEquals(11, myPet.getHappyLevel());
  }

  @Test
  public void tamagotchi_adjustsStatsAfterSleeping_true() {
    Tamagotchi myPet = new Tamagotchi("Meeple");
    myPet.updateOnPlay();
    myPet.updateOnSleep();
    assertEquals(15, myPet.getSleepLevel());
    assertEquals(0, myPet.getHungerLevel());
  }

  @Test
  public void tamagotchi_isAliveIfFoodLevelIsGreaterThan0() {
    Tamagotchi myPet = new Tamagotchi("Meeple");
    assertTrue(myPet.isAlive());
    myPet.updateOnPlay();
    myPet.updateOnPlay();
    assertFalse(myPet.isAlive());
  }

  @Test
  public void tamagotchi_returnsStatsInStrings() {
    Tamagotchi myPet = new Tamagotchi("Meeple");
    assertEquals("extremely hungry", myPet.getHungerStat());
    assertEquals("well rested", myPet.getSleepStat());
    assertEquals("feeling neutral", myPet.getHappyStat());
  }

  @Test
  public void tamagotchi_savesTamagotchiIntoDatabase_true(){
    Tamagotchi myPet = new Tamagotchi("Meeple");
    myPet.save();
    assertTrue(Tamagotchi.all().get(0).equals(myPet));
  }
}
