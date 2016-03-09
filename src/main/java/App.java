import java.util.*;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import java.lang.Thread;
import java.util.Timer;
import java.util.TimerTask;
import org.sql2o.*;

import static spark.Spark.*;

public class App {
  public static void main(String[] args) {


      // TimerTask task = new TimerTask(){
      // @Override
      //   public void run(){
      //     Tamagotchi newTama = Tamagotchi.find(8);
      //     newTama.updateAge();
      //     newTama.isAlive();
      //     System.out.println(newTama.getAge());
      //     System.out.println(newTama.isAlive());
      //
      //   }
      // };
      //
      // Timer timer = new Timer();
      // long delay = 0;
      // long intervalPeriod = 3000;
      //
      // timer.scheduleAtFixedRate(task, delay, intervalPeriod);


    staticFileLocation("/public");
    String layout = "templates/layout.vtl";
//user info & game page
    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());

    get("/takeTwo", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      boolean incorrectUsername = request.session().attribute("incorrectUsername");
      boolean incorrectPassword = request.session().attribute("incorrectPassword");
      User user = request.session().attribute("user");
      model.put("incorrectUsername", incorrectUsername);
      model.put("incorrectPassword", incorrectPassword);
      model.put("user", user);
      model.put("template", "templates/index.vtl");
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());

    get("/signUp", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/sign_up.vtl");
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());

    post("/checkPassword", (request, response) -> {
      String inputPassword = request.queryParams("password");
      String inputName = request.queryParams("name");
      User user = User.findByName(inputName);
      if(user != null) {
        if(user.getPassword().equals(inputPassword)) {
          request.session().attribute("incorrectPassword", false);
          request.session().attribute("incorrectUsername", false);
          request.session().attribute("user", user);
          response.redirect("/games");
          return null;
        } else {
          request.session().attribute("incorrectPassword", true);
          request.session().attribute("incorrectUsername", false);
          request.session().attribute("user", user);
          response.redirect("/takeTwo");
          return null;
        }
      }
      request.session().attribute("incorrectPassword", false);
      request.session().attribute("incorrectUsername", true);
      request.session().attribute("user", null);
      response.redirect("/takeTwo");
      return null;
    });

    post("/signedUp", (request, response) -> {
      String inputPassword = request.queryParams("password");
      String inputName = request.queryParams("name");
      String inputPasswordHint = request.queryParams("passwordhint");
      if(inputName.trim().length() > 0 && inputPassword.trim().length() > 0) {
        User user = new User(inputName, inputPassword, "user");
        user.save();
        if(inputPasswordHint.length() > 0) {
          user.assignPasswordHint(inputPasswordHint);
        }
      }
      response.redirect("/");
      return null;
    });

    get("/games", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      User user = request.session().attribute("user");
      model.put("user", user);
      model.put("template", "templates/games.vtl");
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());

    get("/profile", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      User user = request.session().attribute("user");
      model.put("user", user);
      model.put("template", "templates/profile.vtl");
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());

    post("/updateImage", (request, response) -> {
      String imageUrl = request.queryParams("profilePic");
      User user = request.session().attribute("user");
      user.assignPorfilepic(imageUrl);
      response.redirect("/profile");
      return null;
    });

    post("/changePassword", (request, response) -> {
      String updatedPassword = request.queryParams("updatePassword");
      User user = request.session().attribute("user");
      user.updatePassword(updatedPassword);
      response.redirect("/profile");
      return null;
    });
//simon says
    get("/simonSays", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Turn.delete();
      User user = request.session().attribute("user");
      model.put("user", user);
      model.put("users", User.getSimonHighScores());
      model.put("template", "templates/simonSays.vtl");
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());

    post("/next-turn", (request, response) -> {
      //score based on length of sequence, number of turns completed and difficulty multiplier
      request.session().attribute("simonScore", 0);
      Turn.resetShownStatus();
      Turn.deleteUserGuess();
      Double difficulty = Double.parseDouble(request.queryParams("difficulty")) * -1.0;
      request.session().attribute("time", difficulty);
      Double diffMultiplierDouble = 1.0 / difficulty;
      Integer diffMultiplier = diffMultiplierDouble.intValue();
      request.session().attribute("diffMultiplier", diffMultiplier);
      Turn newTurn = new Turn();
      newTurn.save();
      response.redirect("/3");
      return null;
      });

    get("/next-turn", (request, response) -> {
      Integer turns = Turn.all().size();
      Integer score = request.session().attribute("simonScore");
      Integer diffMultiplier = request.session().attribute("diffMultiplier");
      Integer addedScore = turns * diffMultiplier;
      score += addedScore;
      request.session().attribute("simonScore", score);
      Turn.resetShownStatus();
      Turn.deleteUserGuess();
      Turn newTurn = new Turn();
      newTurn.save();
      response.redirect("/replay");
      return null;
      });

      get("/3", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object>();
        model.put("template", "templates/3.vtl");
        return new ModelAndView (model, layout);
      }, new VelocityTemplateEngine());

      get("/2", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object>();
        model.put("template", "templates/2.vtl");
        return new ModelAndView (model, layout);
      }, new VelocityTemplateEngine());

      get("/1", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object>();
        model.put("template", "templates/1.vtl");
        return new ModelAndView (model, layout);
      }, new VelocityTemplateEngine());

    get("/replay", (request, response) -> {
      if (Turn.allShown() == false) {
        Turn unshownTurn = Turn.getNextUnshownTurn();
        String color = unshownTurn.getGeneratedColor();
        unshownTurn.updateShownStatus();
        if(color.equals("red")) {
          response.redirect("/red");
          return null;
        } else if(color.equals("yellow")) {
          response.redirect("/yellow");
          return null;
        } else if(color.equals("green")) {
          response.redirect("/green");
          return null;
        } else if(color.equals("blue")) {
          response.redirect("/blue");
          return null;
        } else {
          response.redirect("/error-replay");
          return null;
        }
      } else {
        response.redirect("/play");
        return null;
        }
    });

    get("/yellow", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      User user = request.session().attribute("user");
      Double time = request.session().attribute("time");
      Integer currentScore = request.session().attribute("simonScore");
      model.put("currentScore", currentScore);
      model.put("time", time);
      model.put("user", user);
      model.put("template", "templates/yellow.vtl");
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());

    get("/red", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      User user = request.session().attribute("user");
      Double time = request.session().attribute("time");
      Integer currentScore = request.session().attribute("simonScore");
      model.put("currentScore", currentScore);
      model.put("time", time);
      model.put("user", user);
      model.put("template", "templates/red.vtl");
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());

    get("/green", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      User user = request.session().attribute("user");
      Double time = request.session().attribute("time");
      Integer currentScore = request.session().attribute("simonScore");
      model.put("currentScore", currentScore);
      model.put("time", time);
      model.put("user", user);
      model.put("template", "templates/green.vtl");
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());

    get("/blue", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      User user = request.session().attribute("user");
      Double time = request.session().attribute("time");
      Integer currentScore = request.session().attribute("simonScore");
      model.put("currentScore", currentScore);
      model.put("time", time);
      model.put("user", user);
      model.put("template", "templates/blue.vtl");
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());

    get("/grey", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      User user = request.session().attribute("user");
      Double time = request.session().attribute("time");
      Integer currentScore = request.session().attribute("simonScore");
      model.put("currentScore", currentScore);
      model.put("time", time);
      model.put("user", user);
      model.put("template", "templates/grey.vtl");
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());

    get("/play", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      if (Turn.isFull()){
        response.redirect("/next-turn");
        return null;
      }
      User user = request.session().attribute("user");

      Integer currentScore = request.session().attribute("simonScore");
      System.out.println(currentScore);
      model.put("currentScore", currentScore);
      model.put("user", user);
      model.put("template", "templates/play.vtl");
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());

    post("/play", (request, response) -> {
      if (!Turn.isFull()) {
        Turn currentTurn = Turn.getCurrentTurn();
        String userGuess = request.queryParams("color");
        currentTurn.update(userGuess);
        if (currentTurn.checkGuess()){
          response.redirect("/play");
          return null;
        }
        response.redirect("/gameover");
        return null;
      }
      response.redirect("/next-turn");
      return null;
    });

    get("/gameover", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      User user = request.session().attribute("user");
      Integer currentScore = request.session().attribute("simonScore");
      Integer userHighScore = user.getSimonHighScore();
      if (currentScore > userHighScore) {
        user.updateSimonScore(currentScore);
        String congrats = "Congratulations you set a new record!";
        model.put("congrats", congrats);
      }
      userHighScore = user.getSimonHighScore();
      model.put("currentScore", currentScore);
      model.put("highScore", userHighScore);
      model.put("user", user);
      model.put("users", User.getSimonHighScores());
      model.put("template", "templates/gameover.vtl");
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());
//tamagotchi
    get("/tamagotchi", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/tamagotchi.vtl");
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());

    get("/newtamagotchi", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/newtamagotchi.vtl");
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());

    post("/newtamagotchi", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      Tamagotchi tamagotchi = new Tamagotchi(name);
      tamagotchi.save();
      model.put("tamagotchi", tamagotchi);
      model.put("template", "templates/newtamagotchi.vtl");
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());

    post("/tamagotchiupdate/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Tamagotchi tamagotchi = Tamagotchi.find(id);
      String action = request.queryParams("action");
      // if(tamagotchi.isAlive() == false){
      //
      // }
      if (action.equals("feed")){
        tamagotchi.updateOnFeed();
        response.redirect("/feedtamagotchi/" + tamagotchi.getId());
        return null;
      } else if (action.equals("play")){
        tamagotchi.updateOnPlay();
        response.redirect("/playtamagotchi/" + tamagotchi.getId());
        return null;
      } else if (action.equals("sleep")){
        tamagotchi.updateOnSleep();
        response.redirect("/sleeptamagotchi/" + tamagotchi.getId());
        return null;
      }
      model.put("tamagotchi", tamagotchi);
      model.put("template", "templates/newtamagotchi.vtl");
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());

    get("/feedtamagotchi/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Tamagotchi tamagotchi = Tamagotchi.find(id);
      model.put("tamagotchi", tamagotchi);
      model.put("template", "templates/feedtamagotchi.vtl");
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());

    get("/playtamagotchi/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Tamagotchi tamagotchi = Tamagotchi.find(id);
      model.put("tamagotchi", tamagotchi);
      model.put("template", "templates/playtamagotchi.vtl");
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());

    get("/sleeptamagotchi/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Tamagotchi tamagotchi = Tamagotchi.find(id);
      model.put("tamagotchi", tamagotchi);
      model.put("template", "templates/sleeptamagotchi.vtl");
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());


    get("/memory", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      User user = request.session().attribute("user");
      model.put("user", user);
      // model.put("users", User.getSimonHighScores());
      model.put("template", "templates/memory.vtl");
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());

    post("/memoryBoard", (request, response) -> {
      Card.delete();
      Card.fillDatabase();
      List<Card> cards = Card.makeListOfCards(16).shuffle();
      request.session().attribute("cards", cards);
      response.redirect("/memoryBoard");
      return null;
    });

    get("/memoryBoard", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      User user = request.session().attribute("user");
      List<Card> cards = request.session().attribute("cards");
      model.put("user", user);
      model.put("cards", cards);
      // model.put("users", User.getSimonHighScores());
      model.put("template", "templates/memoryBoard.vtl");
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());

  } //end of main
} //end of app
