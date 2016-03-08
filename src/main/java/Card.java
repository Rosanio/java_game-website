import java.util.*;
import org.sql2o.*;

public class Card {
  private int id;
  private String symbol;
  private boolean shown;

  public Card (String symbol) {
    this.id = id;
    this.symbol = symbol;
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
      String sql = "INSERT INTO cards (symbol) VALUES (:symbol)";
      this.id = (int) con.createQuery(sql, true).addParameter("symbol", symbol).executeUpdate().getKey();
    }
  }

  public static Card find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM cards WHERE id = :id";
      return con.createQuery(sql).addParameter("id", id).executeAndFetchFirst(Card.class);
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM cards";
      con.createQuery(sql).executeUpdate();
    }
  }
}
