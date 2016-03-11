import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/game_website_test", null, null);
   }

  protected void after() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM turns *;";
      String deleteUsers = "DELETE FROM users *;";
      String deleteCards = "DELETE FROM cards *;";
      String deleteTamagotchis = "DELETE FROM tamagotchis *;";
      con.createQuery(sql).executeUpdate();
      con.createQuery(sql).executeUpdate();
      con.createQuery(deleteUsers).executeUpdate();
      con.createQuery(deleteCards).executeUpdate();
      con.createQuery(deleteTamagotchis).executeUpdate();
    }
  }
}
