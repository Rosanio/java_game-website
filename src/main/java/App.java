import java.util.*;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import java.lang.Thread;
import java.util.Timer;
import java.util.TimerTask;
import org.sql2o.*;
import static spark.Spark.*;

public class App {
  public static Integer globalUserId;
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";
      TimerTask task = new TimerTask(){
        public void run(){
          if (globalUserId != null){
            User userTimer = User.find(globalUserId);
            Tamagotchi newTama = Tamagotchi.find(userTimer.getTamagotchiId());
            if (newTama != null && newTama.isAlive()){
              newTama.updateAge();
              newTama.isAlive();
              if (newTama.getAge() == 30){
                int tamaPoints = userTimer.getPoints();
                tamaPoints += 200;
                userTimer.updatePoints(tamaPoints);
              }
              if (newTama.getAge() == 60){
                int tamaPoints = userTimer.getPoints();
                tamaPoints += 500;
                userTimer.updatePoints(tamaPoints);
              }
              if (newTama.getAge() == 90){
                int tamaPoints = userTimer.getPoints();
                tamaPoints += 1000;
                userTimer.updatePoints(tamaPoints);
              }
            }
          }
        }
      };
      Timer timer = new Timer();
      long delay = 0;
      long intervalPeriod = 60000;
      timer.scheduleAtFixedRate(task, delay, intervalPeriod);
//user info & game page
    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      request.session().attribute("user", null);
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
          globalUserId = user.getId();
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

    //profile

    get("/profile", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      User user = request.session().attribute("user");
      model.put("user", user);
      model.put("points", user.getPoints());
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

    post("/buyFood", (request, response) -> {
      User user = request.session().attribute("user");
      user.updateTamagotchiFood(1);
      int points = user.getPoints();
      points -= 300;
      user.updatePoints(points);
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
      Double diffMultiplierDouble = (1.1 + difficulty) / ((1.1 * difficulty + 0.349) * (1.1 * difficulty + 0.349));
      Integer diffMultiplier = diffMultiplierDouble.intValue();
      request.session().attribute("diffMultiplier", diffMultiplier);
      Turn newTurn = new Turn();
      newTurn.save();
      response.redirect("/5");
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

    get("/5", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      User user = request.session().attribute("user");
      model.put("user", user);
      model.put("template", "templates/5.vtl");
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());

    get("/4", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      User user = request.session().attribute("user");
      model.put("user", user);
      model.put("template", "templates/4.vtl");
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());

    get("/3", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      User user = request.session().attribute("user");
      model.put("user", user);
      model.put("template", "templates/3.vtl");
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());

    get("/2", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      User user = request.session().attribute("user");
      model.put("user", user);
      model.put("template", "templates/2.vtl");
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());

    get("/1", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      User user = request.session().attribute("user");
      model.put("user", user);
      model.put("template", "templates/1.vtl");
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());

    get("/go", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      User user = request.session().attribute("user");
      model.put("user", user);
      model.put("template", "templates/go.vtl");
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
      int points = user.getPoints();
      points += currentScore;
      user.updatePoints(points);
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
      User user = request.session().attribute("user");
      if (User.find(globalUserId).getTamagotchiId() != 0){
        response.redirect("/newtamagotchi");
        return null;
      }
      model.put("user", user);
      model.put("template", "templates/tamagotchi.vtl");
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());

    get("/newtamagotchi", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      User user = request.session().attribute("user");
      Tamagotchi tamagotchi = Tamagotchi.find(User.find(globalUserId).getTamagotchiId());
      if (!tamagotchi.isAlive()){
        User.find(globalUserId).clearTamagotchi();
        tamagotchi.delete();
      }
      model.put("remainingFood", user.getTamagotchiFood());
      model.put("fed", request.session().attribute("wasFed"));
      model.put("tamagotchi", tamagotchi);
      model.put("user", user);
      model.put("template", "templates/newtamagotchi.vtl");
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());

    post("/newtamagotchi", (request, response) -> {
      User user = request.session().attribute("user");
      String name = request.queryParams("name");
      Tamagotchi tamagotchi = new Tamagotchi(name);
      tamagotchi.save();
      user.updateTamagotchi(tamagotchi.getId());
      // if (gameSpeed == 1){
      //   System.out.println("teststestestset");
      //   intervalPeriod = 1000;
      //   task.cancel();
      //   timer = new Timer();
      //   timer.scheduleAtFixedRate(task, delay, intervalPeriod);
      // } else if (gameSpeed == 2){
      //   intervalPeriod = 30000;
      // } else {
      //   intervalPeriod = 60000;
      // }
      request.session().attribute("wasFed", true);
      response.redirect("/newtamagotchi");
      return null;
    });

    post("/tamagotchiupdate/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      User user = request.session().attribute("user");
      int id = Integer.parseInt(request.params("id"));
      Tamagotchi tamagotchi = Tamagotchi.find(id);
      String action = request.queryParams("action");
      if(!tamagotchi.isAlive()){
        response.redirect("/newtamagotchi");
        return null;
      }
      if (action.equals("feed")){
        if (user.getTamagotchiFood() > 0){
          tamagotchi.updateOnFeed();
          int tamaFood = user.getTamagotchiFood();
          tamaFood-=1;
          user.updateTamagotchiFood(tamaFood);
          request.session().attribute("wasFed", true);
        } else {
          request.session().attribute("wasFed", false);
        }
        response.redirect("/newtamagotchi");
        return null;
      } else if (action.equals("play")){
        tamagotchi.updateOnPlay();
        response.redirect("/newtamagotchi");
        return null;
      } else if (action.equals("sleep")){
        tamagotchi.updateOnSleep();
        response.redirect("/newtamagotchi");
        return null;
      }
      model.put("remainingFood", user.getTamagotchiFood());
      model.put("tamagotchi", tamagotchi);
      model.put("user", user);
      model.put("template", "templates/newtamagotchi.vtl");
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());

    get("/feedtamagotchi/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      User user = request.session().attribute("user");
      int id = Integer.parseInt(request.params("id"));
      Tamagotchi tamagotchi = Tamagotchi.find(id);
      model.put("fed", request.session().attribute("wasFed"));
      model.put("tamagotchi", tamagotchi);
      model.put("user", user);
      model.put("template", "templates/newtamagotchi.vtl");
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());

    get("/playtamagotchi/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      User user = request.session().attribute("user");
      int id = Integer.parseInt(request.params("id"));
      Tamagotchi tamagotchi = Tamagotchi.find(id);
      model.put("user", user);
      model.put("fed", request.session().attribute("wasFed"));
      model.put("tamagotchi", tamagotchi);
      model.put("template", "templates/newtamagotchi.vtl");
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());

    get("/sleeptamagotchi/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      User user = request.session().attribute("user");
      int id = Integer.parseInt(request.params("id"));
      Tamagotchi tamagotchi = Tamagotchi.find(id);
      model.put("fed", request.session().attribute("wasFed"));
      model.put("user", user);
      model.put("tamagotchi", tamagotchi);
      model.put("template", "templates/newtamagotchi.vtl");
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());
//memory
    get("/memory", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      User user = request.session().attribute("user");
      request.session().attribute("player2", null);
      model.put("user", user);
      model.put("users", User.getMemoryHighScores());
      model.put("template", "templates/memory.vtl");
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());

    post("/memory", (request, response) -> {
      Card.delete();
      Card.fillDatabase();
      List<Card> cards = Card.all();
      ArrayList<Card> cardDeck = new ArrayList<Card>();
      int numberOfCards = 0;
      if(request.session().attribute("player2") != null) {
        numberOfCards = request.session().attribute("numberOfCards");
      } else {
        numberOfCards = Integer.parseInt(request.queryParams("cardNumber"));
        request.session().attribute("numberOfCards", numberOfCards);
      }
      while (cardDeck.size() < numberOfCards) {
        int number = Card.randomEvenNumber();
        if (!cardDeck.contains(cards.get(number))) {
          cardDeck.add(cards.get(number));
          cardDeck.add(cards.get(number + 1));
        }
      }
      int memoryScore = numberOfCards*10;
      request.session().attribute("memoryScore", memoryScore);
      request.session().attribute("cardNumber", request.queryParams("cardNumber"));
      Collections.shuffle(cardDeck);
      int counter = 0;
      for(Card card : cardDeck) {
        card.assignOrderId(counter);
        counter += 1;
      }
      // String players = request.queryParams("players");
      request.session().attribute("cards", cardDeck);
      if(request.session().attribute("player2") != null) {
        int player1score = request.session().attribute("player1score");
        int player2score = request.session().attribute("player2score");
        if(player1score > player2score) {
          request.session().attribute("turn", 2);
        } else {
          request.session().attribute("turn", 1);
        }
        request.session().attribute("player1score", 0);
        request.session().attribute("player2score", 0);
        response.redirect("/memoryBoard");
        return null;
      }
      String players = request.queryParams("players");
      if(players.equals("1")) {
        response.redirect("/memoryBoard");
        return null;
      }
      response.redirect("/player2Login");
      return null;
    });

    get("/player2Login", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      User user = request.session().attribute("user");
      model.put("user", user);
      model.put("users", User.all());
      model.put("template", "templates/player2Login.vtl");
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());

    post("/player2Login", (request, response) -> {
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
      response.redirect("/player2Login");
      return null;
    });

    post("/player2LoggedIn", (request, response) -> {
      int player2Id = Integer.parseInt(request.queryParams("player2"));
      User player2 = User.find(player2Id);
      int player1score = 0;
      int player2score = 0;
      request.session().attribute("player2score", player2score);
      request.session().attribute("player1score", player1score);
      request.session().attribute("player2", player2);
      int turn = 1;
      request.session().attribute("turn", turn);
      response.redirect("/memoryBoard");
      return null;
    });

    get("/memoryBoard", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      User user = request.session().attribute("user");
      User player2 = request.session().attribute("player2");
      if(player2 != null) {
        int player1score = request.session().attribute("player1score");
        int player2score = request.session().attribute("player2score");
        model.put("player1score", player1score);
        model.put("player2score", player2score);

      }
      List<Card> cards = request.session().attribute("cards");
      int score = request.session().attribute("memoryScore");
      model.put("score", score);
      model.put("user", user);
      model.put("player2", player2);
      model.put("cards", cards);
      model.put("turn", request.session().attribute("turn"));
      model.put("template", "templates/memoryBoard.vtl");
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());

    post("/memoryBoard", (request, response) -> {
      //score based on deck size, wrong guesses
      User player2 = request.session().attribute("player2");
      List<Card> cards = request.session().attribute("cards");
      Card card = cards.get(Integer.parseInt(request.queryParams("cards")));
      card.updateShown();
      int counter = 0;
      for(Card cardd : cards) {
        if (cardd.getShown()) {
          counter += 1;
        }
      }
      if (counter == 1) {
        request.session().attribute("firstCard", card);
      }
      if (counter == 2) {
        Card caard = request.session().attribute("firstCard");
        Card secondCard = cards.get(Integer.parseInt(request.queryParams("cards")));
        request.session().attribute("secondCard", secondCard);
        boolean cardMatch = caard.checkMatch(secondCard);

        if (cardMatch) {
          if(player2 != null) {
            int turn = request.session().attribute("turn");
            int player1score = request.session().attribute("player1score");
            int player2score = request.session().attribute("player2score");
            if (turn == 1) {
              player1score += 1;
              request.session().attribute("player1score", player1score);
            }
            if (turn == 2) {
              player2score += 1;
              request.session().attribute("player2score", player2score);
            }
            if(turn == 1) {
              turn = 2;
              request.session().attribute("turn", turn);
            } else {
              turn = 1;
              request.session().attribute("turn", turn);
            }
          }
          else {
            int score = request.session().attribute("memoryScore");
            score += 10;
            request.session().attribute("memoryScore", score);
          }
          caard.matched();
          caard.updateShown();
          secondCard.matched();
          secondCard.updateShown();
          int matchedCounter = 0;
          for(Card ccard : cards) {
            if(ccard.getMatch()) {
              matchedCounter += 1;
            }
          }
          if(matchedCounter == cards.size()) {
            response.redirect("/memoryGameOver");
            return null;
          }
        } else {
          if (player2 == null) {
            int score = request.session().attribute("memoryScore");
            score -= 5;
            request.session().attribute("memoryScore", score);
          } else {
            int turn = request.session().attribute("turn");
            if(turn == 1) {
              turn = 2;
              request.session().attribute("turn", turn);
            } else {
              turn = 1;
              request.session().attribute("turn", turn);
            }
          }
          response.redirect("/showCards");
          return null;
        }

      }
      request.session().attribute("cards", cards);
      response.redirect("/memoryBoard");
      return null;
    });

    get("/showCards", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      User user = request.session().attribute("user");
      User player2 = request.session().attribute("player2");
      List<Card> cards = request.session().attribute("cards");
      int score = request.session().attribute("memoryScore");
      model.put("score", score);
      model.put("user", user);
      model.put("player1score", request.session().attribute("player1score"));
      model.put("player2score", request.session().attribute("player2score"));
      model.put("player2", player2);
      model.put("cards", cards);
      model.put("turn", request.session().attribute("turn"));
      model.put("template", "templates/showCards.vtl");
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());

    get("/flipCards", (request, response) -> {
      User user = request.session().attribute("user");
      List<Card> cards = request.session().attribute("cards");
      Card firstCard = request.session().attribute("firstCard");
      firstCard.updateShown();
      Card secondCard = request.session().attribute("secondCard");
      secondCard.updateShown();
      response.redirect("/memoryBoard");
      return null;
    });

    get("/memoryGameOver", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      User user = request.session().attribute("user");
      User player2 = request.session().attribute("player2");
      if(player2 != null) {
        int player1score = request.session().attribute("player1score");
        int player2score = request.session().attribute("player2score");
        if(player1score > player2score) {
          int p1wins = user.getMemoryWins();
          p1wins += 1;
          user.updateMemoryWins(p1wins);
          int p2losses = player2.getMemoryLosses();
          p2losses += 1;
          player2.updateMemoryLosses(p2losses);
        } else if (player1score < player2score) {
          int p1losses = user.getMemoryLosses();
          p1losses += 1;
          user.updateMemoryLosses(p1losses);
          int p2wins = player2.getMemoryWins();
          p2wins += 1;
          player2.updateMemoryWins(p2wins);
        } else {
          int p1wins = user.getMemoryWins();
          p1wins += 1;
          user.updateMemoryWins(p1wins);
          int p2wins = player2.getMemoryWins();
          p2wins += 1;
          player2.updateMemoryWins(p2wins);
        }
        model.put("player2", player2);
        model.put("player1score", player1score);
        model.put("player2score", player2score);
      } else {
        int cardNumber = Integer.parseInt(request.session().attribute("cardNumber"));
        int score = request.session().attribute("memoryScore");
        score += cardNumber*10;
        int points = user.getPoints();
        points += score;
        user.updatePoints(points);
        if (score > user.getMemoryHighScore()) {
          user.updateMemoryScore(score);
          String congrats = "Congratulations you set a new record!";
          model.put("congrats", congrats);
        }
        request.session().attribute("memoryScore", score);
        model.put("score", score);
        model.put("highScore", user.getMemoryHighScore());
        model.put("users", User.getMemoryHighScores());

      }
      model.put("user", user);
      model.put("template", "templates/memoryGameOver.vtl");
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());
//hangman
    // get("/hangman", (request, response) -> {
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   User user = request.session().attribute("user");
    //   model.put("user", user);
    //   model.put("users", User.getSimonHighScores());
    //   model.put("template", "templates/hangman.vtl");
    //   return new ModelAndView (model, layout);
    // }, new VelocityTemplateEngine());
    //
    // get("/hangman", (request, response) -> {
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   User user = request.session().attribute("user");
    //   model.put("user", user);
    //   model.put("users", User.getSimonHighScores());
    //   String userGuess = request.queryParams("guess");
    //
    //   model.put("template", "templates/hangman.vtl");
    //   return new ModelAndView (model, layout);
    // }, new VelocityTemplateEngine());

    get("/manageUsers", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      User user = request.session().attribute("user");
      model.put("user", user);
      model.put("users", User.all());
      model.put("template", "templates/manageUsers.vtl");
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());

    get("/delete/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      User user = request.session().attribute("user");
      model.put("user", user);
      model.put("users", User.all());

      User selectedUser = User.find(Integer.parseInt(request.params("id")));
      model.put("selectedUser", selectedUser);
      model.put("template", "templates/manageUsers-delete.vtl");
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());


    post("/delete/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      User selectedUser = User.find(Integer.parseInt(request.params("id")));
      User user = request.session().attribute("user");
      selectedUser.delete();
      model.put("user", user);
      model.put("users", User.all());
      model.put("template", "templates/manageUsers.vtl");
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());

    post("/updatePermissions/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      User selectedUser = User.find(Integer.parseInt(request.params("id")));
      User user = request.session().attribute("user");
      String permission = request.queryParams("updatePermissions");
      selectedUser.updatePermissions(permission);
      model.put("user", user);
      model.put("users", User.all());
      model.put("template", "templates/manageUsers.vtl");
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());

  } //end of main
} //end of app
