import java.util.*;
import org.sql2o.*;

import org.junit.*;
import static org.junit.Assert.*;

public class UserTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(0, User.all().size());
  }

  @Test
  public void equals_returnsTrueIfUsersHaveSameId() {
    User newUser = new User("Matt", "123", "admin");
    User newUser2 = new User("Matt", "123", "admin");
    assertTrue(newUser.equals(newUser2));
  }

  @Test
  public void save_savesUserToDatabase() {
    User newUser = new User("Matt", "123", "admin");
    newUser.save();
    assertTrue(User.all().get(0).equals(newUser));
  }

  @Test
  public void save_savesUserIdToDatabase() {
    User newUser = new User("Matt", "123", "admin");
    newUser.save();
    assertEquals(User.all().get(0).getId(),newUser.getId());
  }

  @Test
  public void find_returnsUserBasedOnId() {
    User newUser = new User("Matt", "123", "admin");
    newUser.save();
    User newUser2 = new User("Anna", "321", "admin");
    newUser2.save();
    assertTrue(User.find(newUser.getId()).equals(newUser));
    assertTrue(User.find(newUser2.getId()).equals(newUser2));
  }

  @Test
  public void delete_removesUserFromDatabase() {
    User newUser = new User("Matt", "123", "admin");
    newUser.save();
    newUser.delete();
    assertEquals(User.all().size(), 0);
  }

  @Test
  public void passwordHint_returnsPasswordWithDashesInPlaceOfVowels() {
    User newUser = new User("Matt", "cat", "admin");
    newUser.save();
    assertEquals(newUser.passwordPuzzle(), "c-t");
  }

  @Test
  public void getHighScores_returnsTopTenHighScoringPlayers() {
    User newUser = new User("Matt", "cat", "admin");
    newUser.save();
    newUser.updateSimonScore(100);
    User newUser2 = new User("Matt", "cat", "admin");
    newUser2.save();
    newUser2.updateSimonScore(100);
    User newUser3 = new User("Matt", "cat", "admin");
    newUser3.save();
    newUser3.updateSimonScore(100);
    User newUser4 = new User("Matt", "cat", "admin");
    newUser4.save();
    newUser4.updateSimonScore(100);
    User newUser5 = new User("Matt", "cat", "admin");
    newUser5.save();
    newUser5.updateSimonScore(90);
    User newUser6 = new User("Matt", "cat", "admin");
    newUser6.save();
    newUser6.updateSimonScore(100);
    User newUser7 = new User("Matt", "cat", "admin");
    newUser7.save();
    newUser7.updateSimonScore(100);
    User newUser8 = new User("Matt", "cat", "admin");
    newUser8.save();
    newUser8.updateSimonScore(100);
    User newUser9 = new User("Matt", "cat", "admin");
    newUser9.save();
    newUser9.updateSimonScore(100);
    User newUser10 = new User("Matt", "cat", "admin");
    newUser10.save();
    newUser10.updateSimonScore(100);
    User newUser11 = new User("Matt", "cat", "admin");
    newUser11.save();
    newUser11.updateSimonScore(100);
    assertTrue(User.getSimonHighScores().size() == 10);
    assertFalse(User.getSimonHighScores().contains(newUser5));
  }
}
