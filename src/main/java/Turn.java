import java.util.*;
import org.sql2o.*;

public class Turn {
  private int id;
  private String comp_turn;
  private String user_turn;
  private boolean shown;

  public Turn() {
    int randomNumber = this.randomNumber();
    this.shown = false;
    if(randomNumber == 0) {
      this.comp_turn = "red";
    } else if(randomNumber == 1) {
      this.comp_turn = "blue";
    } else if(randomNumber == 2) {
      this.comp_turn = "yellow";
    } else {
      this.comp_turn = "green";
    }
  }

  public int getId() {
    return id;
  }

  public String getGeneratedColor() {
    return comp_turn;
  }

  public String getUserTurn() {
    return user_turn;
  }

  public static List<Turn> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM turns";
      return con.createQuery(sql).executeAndFetch(Turn.class);
    }
  }

  @Override
  public boolean equals(Object otherTurn) {
    if(!(otherTurn instanceof Turn)) {
      return false;
    } else {
      Turn newTurn = (Turn) otherTurn;
      return newTurn.getId() == (id);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO turns (comp_turn, shown) VALUES (:comp_turn, false)";
      this.id = (int) con.createQuery(sql, true).addParameter("comp_turn", comp_turn).executeUpdate().getKey();
    }
  }

  public static Turn find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM turns WHERE id = :id";
      return con.createQuery(sql).addParameter("id", id).executeAndFetchFirst(Turn.class);    }
  }

  public static void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM turns";
      con.createQuery(sql).executeUpdate();
    }
  }

  public static void deleteUserGuess() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE turns SET user_turn = null";
      con.createQuery(sql).executeUpdate();
    }
  }

  public void update(String user_guess) {
    this.user_turn = user_guess;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE turns SET user_turn = :user_guess WHERE id = :id";
      con.createQuery(sql).addParameter("id", id).addParameter("user_guess", user_guess).executeUpdate();
    }
  }

  public int randomNumber() {
    Random randomNumberGenerator = new Random();
    return randomNumberGenerator.nextInt(4);
  }

  public static boolean isFull() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT count(*) FROM turns WHERE user_turn IS NULL";
      Integer nullTurns = con.createQuery(sql).executeAndFetchFirst(Integer.class);
      if(nullTurns > 0) {
        return false;
      }
      return true;
    }
  }

  public boolean checkGuess() {
    return comp_turn.equals(user_turn);
  }

  public static Turn getCurrentTurn() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM turns WHERE user_turn IS NULL";
      return con.createQuery(sql).executeAndFetchFirst(Turn.class);
    }
  }


  public static Turn getNextUnshownTurn() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM turns WHERE shown = false ORDER BY id";
      return con.createQuery(sql).executeAndFetchFirst(Turn.class);
    }
  }

  public static boolean allShown() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT count(*) FROM turns WHERE shown = false";
      Integer nullShown = con.createQuery(sql).executeAndFetchFirst(Integer.class);
      if(nullShown > 0) {
        return false;
      }
      return true;
    }
  }

  public void updateShownStatus() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE turns SET shown = true WHERE id = :id";
      con.createQuery(sql).addParameter("id", id).executeUpdate();
    }
  }

  public static void resetShownStatus() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE turns SET shown = false";
      con.createQuery(sql).executeUpdate();
    }
  }
}
