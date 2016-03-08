import java.util.*;
import org.sql2o.*;

import org.junit.*;
import static org.junit.Assert.*;

public class CardTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(0, Card.all().size());
  }

  @Test
  public void equals_returnsTrueIfCardsHaveSameId() {
    Card newCard = new Card("x");
    Card newCard2 = new Card("x");
    assertTrue(newCard.equals(newCard2));
  }

  @Test
  public void save_savesCardToDatabase() {
    Card newCard = new Card("x");
    newCard.save();
    assertTrue(Card.all().get(0).equals(newCard));
  }

  @Test
  public void save_savesCardIdToDatabase() {
    Card newCard = new Card("x");
    newCard.save();
    assertEquals(Card.all().get(0).getId(),newCard.getId());
  }

  @Test
  public void find_returnsCardBasedOnId() {
    Card newCard = new Card("x");
    newCard.save();
    Card newCard2 = new Card("y");
    newCard2.save();
    assertTrue(Card.find(newCard.getId()).equals(newCard));
    assertTrue(Card.find(newCard2.getId()).equals(newCard2));
  }

  @Test
  public void delete_removesCardFromDatabase() {
    Card newCard = new Card("Matt");
    newCard.save();
    newCard.delete();
    assertEquals(Card.all().size(), 0);
  }
}
