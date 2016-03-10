import java.util.*;
import org.sql2o.*;

public class User {
  private int id;
  private String name;
  private String password;
  private String permissions;
  private String passwordHint;
  private String profilepic;
  private int simon_high_score;
  private int tamagotchi_id;

  public User(String name, String password, String permissions) {
    this.name = name;
    this.password = password;
    this.permissions = permissions;
    this.simon_high_score = 0;
  }

  public int getId() {
    return id;
  }

  public int getTamagotchiId() {
    return tamagotchi_id;
  }

  public String getName() {
    return name;
  }

  public String getPassword() {
    return password;
  }

  public String getPermissions() {
    return permissions;
  }

  public String getPasswordHint() {
    return passwordHint;
  }

  public String getProfilepic() {
    return profilepic;
  }

  public int getSimonHighScore() {
    return simon_high_score;
  }

  public static List<User> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM users";
      return con.createQuery(sql).executeAndFetch(User.class);
    }
  }

  @Override
  public boolean equals(Object otherUser) {
    if(!(otherUser instanceof User)) {
      return false;
    } else {
      User newUser = (User) otherUser;
      return newUser.getId() == (id);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO users (name, password, permissions, simon_high_score) VALUES (:name, :password, :permissions, 0)";
      this.id = (int) con.createQuery(sql, true).addParameter("name", name).addParameter("password", password).addParameter("permissions", permissions).executeUpdate().getKey();
    }
  }

  public static User find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM users WHERE id = :id";
      return con.createQuery(sql).addParameter("id", id).executeAndFetchFirst(User.class);    }
  }

  public static User findByName(String name) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM users WHERE name = :name";
      return con.createQuery(sql).addParameter("name", name).executeAndFetchFirst(User.class);    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM users WHERE id = :id";
      con.createQuery(sql).addParameter("id", id).executeUpdate();
    }
  }

  public String passwordPuzzle() {
    String puzzlePassword = password;
    char[] vowels = {'A', 'E', 'I', 'O', 'U', 'a', 'e', 'i', 'o', 'u'};
    for(char vowel : vowels) {
      puzzlePassword = puzzlePassword.replace(vowel, '-');
    }
    return puzzlePassword;
  }

  public void assignPasswordHint(String passwordHint) {
    this.passwordHint = passwordHint;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE users SET passwordHint = :passwordHint WHERE id = :id" ;
      con.createQuery(sql).addParameter("id", id).addParameter("passwordHint", passwordHint).executeUpdate();
    }
  }

  public void assignPorfilepic(String profilepic) {
    this.profilepic = profilepic;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE users SET profilepic = :profilepic WHERE id = :id" ;
      con.createQuery(sql).addParameter("id", id).addParameter("profilepic", profilepic).executeUpdate();
    }
  }

  public void updateSimonScore(int simon_high_score) {
    this.simon_high_score = simon_high_score;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE users SET simon_high_score = :simon_high_score WHERE id = :id" ;
      con.createQuery(sql).addParameter("id", id).addParameter("simon_high_score", simon_high_score).executeUpdate();
    }
  }

  public void updateTamagotchi(int tamagotchiId) {
    this.tamagotchi_id = tamagotchiId;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE users SET tamagotchi_id = :tamagotchi_id WHERE id = :id" ;
      con.createQuery(sql).addParameter("id", id).addParameter("tamagotchi_id", tamagotchiId).executeUpdate();
    }
  }

  public void updatePassword(String newPassword) {
    this.password = newPassword;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE users SET password = :newPassword WHERE id = :id" ;
      con.createQuery(sql).addParameter("id", id).addParameter("newPassword", newPassword).executeUpdate();
    }
  }

  public static List<User> getSimonHighScores() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM users ORDER BY simon_high_score DESC LIMIT 10" ;
      return con.createQuery(sql).executeAndFetch(User.class);
    }
  }


}
