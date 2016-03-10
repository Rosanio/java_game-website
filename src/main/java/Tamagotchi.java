import java.util.*;
import org.sql2o.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Tamagotchi {
  public static final int MAX_SLEEP = 15;
  public static final int MAX_HUNGER = 15;
  public static final int MAX_HAPPY = 15;
  private int id;
  private String name;
  public int age;
  private String gender;
  private boolean alive;
  private int sleep_level;
  private int hunger_level;
  private int happy_level;
  private String sleepStatus;
  private String hungerStatus;
  private String happyStatus;
//getters & constructor
  public Tamagotchi(String tamagotchiName){
    Random randomNumberGenerator = new Random();
    int randomNumber = randomNumberGenerator.nextInt(2);
    if (randomNumber == 1){
      gender = "Female";
    } else {
      gender = "Male";
    }
    name = tamagotchiName;
    age = 0;
    sleep_level = MAX_SLEEP;
    hunger_level = 4;
    happy_level = 8;
    alive = true;
  }

  public String getName(){
    return name;
  }

  public int getId(){
    return id;
  }

  public int getAge(){
    return age;
  }

  public String getGender(){
    return gender;
  }

  public int getSleepLevel(){
    return sleep_level;
  }

  public int getHungerLevel(){
    return hunger_level;
  }

  public int getHappyLevel(){
    return happy_level;
  }

  public String getSleepStat(){
    if (sleep_level < 5){
      sleepStatus = "extremely tired";
    } else if (sleep_level < 10){
      sleepStatus = "getting sleepy";
    } else {
      sleepStatus = "well rested";
    }
    return sleepStatus;
  }

  public String getHungerStat(){
    if (hunger_level < 5){
      hungerStatus = "extremely hungry";
    } else if (hunger_level < 10){
      hungerStatus = "fairly hungry";
    } else {
      hungerStatus = "full";
    }
    return hungerStatus;
  }

  public String getHappyStat(){
    if (happy_level < 5){
      happyStatus = "extremely sad";
    } else if (happy_level < 10){
      happyStatus = "feeling neutral";
    } else {
      happyStatus = "very happy";
    }
    return happyStatus;
  }
//life/age status
  public boolean isAlive(){
    if (hunger_level == 0 || age >= 90){
      try (Connection con = DB.sql2o.open()){
        String sql = "UPDATE tamagotchis SET alive = false";
        con.createQuery(sql)
        .executeUpdate();
      }
    } else {
        alive = true;
      }
    return alive;
  }

//create
  @Override
  public boolean equals(Object otherTamagotchi) {
    if(!(otherTamagotchi instanceof Tamagotchi)) {
      return false;
    } else {
      Tamagotchi newTamagotchi = (Tamagotchi) otherTamagotchi;
      return newTamagotchi.getId() == (id);
    }
  }

  public void save(){
    try(Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO tamagotchis (name, age, gender, sleep_level, hunger_level, happy_level, alive) VALUES (:name, :age, :gender, :sleep_level, :hunger_level, :happy_level, true)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("name", name)
      .addParameter("age", age)
      .addParameter("gender", gender)
      .addParameter("sleep_level", sleep_level)
      .addParameter("hunger_level", hunger_level)
      .addParameter("happy_level", happy_level)
      .executeUpdate()
      .getKey();
    }
  }
//read
  public static List<Tamagotchi> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM tamagotchis";
      return con.createQuery(sql).executeAndFetch(Tamagotchi.class);
      }
    }

  public static Tamagotchi find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM tamagotchis WHERE id = :id";
      return con.createQuery(sql).addParameter("id", id).executeAndFetchFirst(Tamagotchi.class);
    }
  }
//update

  public void updateAge(){
    this.age+=1;
    if ((this.hunger_level-1) < 0){
      this.hunger_level = 0;
    } else {
      this.hunger_level-=1;
    }
    try(Connection con = DB.sql2o.open()){
      String sql = "UPDATE tamagotchis SET (age, hunger_level) = (:age, :hunger_level)";
      con.createQuery(sql)
      .addParameter("age", this.age)
      .addParameter("hunger_level", hunger_level)
      .executeUpdate();
    }
  }

  public void updateOnFeed() {
    if((this.hunger_level + 3) > 15){
      this.hunger_level = MAX_HUNGER;
    } else {
      this.hunger_level+=3;
    }
    if((this.sleep_level-1) <= 0){
      this.sleep_level = 0;
    } else {
      this.sleep_level--;
    }
    if((this.happy_level+1) > 15){
      this.happy_level = MAX_HAPPY;
    } else {
      this.happy_level++;
    }
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE tamagotchis SET (hunger_level, sleep_level, happy_level) = (:hunger_level, :sleep_level, :happy_level) WHERE id = :id";
      con.createQuery(sql)
      .addParameter("id", this.id)
      .addParameter("hunger_level", this.hunger_level)
      .addParameter("sleep_level", this.sleep_level)
      .addParameter("happy_level", this.happy_level)
      .executeUpdate();
    }
  }

  public void updateOnPlay(){
    if ((this.happy_level + 3) > 15){
      this.happy_level = MAX_HAPPY;
    } else {
      this.happy_level+=3;
    }
    if ((this.hunger_level-2) <= 0){
      this.hunger_level = 0;
    } else {
      this.hunger_level-=2;
    }
    if ((this.sleep_level-3) <= 0){
      this.sleep_level = 0;
    } else {
      this.sleep_level-=3;
    }
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE tamagotchis SET (hunger_level, sleep_level, happy_level) = (:hunger_level, :sleep_level, :happy_level) WHERE id = :id";
      con.createQuery(sql)
      .addParameter("id", this.id)
      .addParameter("hunger_level", this.hunger_level)
      .addParameter("sleep_level", this.sleep_level)
      .addParameter("happy_level", this.happy_level)
      .executeUpdate();
    }
  }

  public void updateOnSleep(){
    this.sleep_level = MAX_SLEEP;
    if ((this.hunger_level-2) <= 0){
      this.hunger_level = 0;
    } else {
      this.hunger_level-=2;
    }
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE tamagotchis SET (hunger_level, sleep_level, happy_level) = (:hunger_level, :sleep_level, :happy_level) WHERE id = :id";
      con.createQuery(sql)
      .addParameter("id", this.id)
      .addParameter("hunger_level", this.hunger_level)
      .addParameter("sleep_level", this.sleep_level)
      .addParameter("happy_level", this.happy_level)
      .executeUpdate();
    }
  }

  //delete
  public static void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM tamagotchis";
      con.createQuery(sql).executeUpdate();
    }
  }
}
