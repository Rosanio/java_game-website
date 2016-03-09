import java.util.*;
import org.sql2o.*;

public class Card {
  private int id;
  private String symbol;
  private boolean shown;

  public Card (String symbol) {
    this.id = id;
    this.symbol = symbol;
    this.shown = false;
  }

  public int getId() {
    return id;
  }

  public String getSymbol() {
    return symbol;
  }

  public static List<Card> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM cards";
      return con.createQuery(sql).executeAndFetch(Card.class);
    }
  }

  @Override
  public boolean equals(Object otherCard) {
    if(!(otherCard instanceof Card)) {
      return false;
    } else {
      Card newCard = (Card) otherCard;
      return newCard.getId() == (id);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO cards (symbol, shown) VALUES (:symbol, false)";
      this.id = (int) con.createQuery(sql, true).addParameter("symbol", symbol).executeUpdate().getKey();
    }
  }

  public static Card find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM cards WHERE id = :id";
      return con.createQuery(sql).addParameter("id", id).executeAndFetchFirst(Card.class);
    }
  }

  public static void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM cards";
      con.createQuery(sql).executeUpdate();
    }
  }

  public boolean checkMatch(Card secondCard) {
    if (secondCard.getSymbol().equals(symbol)) {
      return true;
    }
    return false;
  }

  public void removePair() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM cards WHERE symbol = :symbol";
      con.createQuery(sql).addParameter("symbol", symbol).executeUpdate();
    }
  }
  //shuffle
  public static List<Card> makeListOfCards(int limit) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM cards LIMIT :limit";
      return con.createQuery(sql).addParameter("limit", limit).executeAndFetch(Card.class);
    }
  }

  public static void fillDatabase() {
    String[] symbols = {"⌛️", "🌈", "🎾", "🐤", "👍", "✊", "👻", "💚", "💰", "🚴", "🖕", "🐼", "🦄", "🎎", "", "🐠", "🍷", "🐈", "🐷", "😈", "👯", "💃", "🐮", "🌟", "🍡", "🎀"};
    for(String symbol : symbols) {
      Card newCard = new Card(symbol);
      newCard.save();
      Card newCard2 = new Card(symbol);
      newCard2.save();
    }
  }
}
