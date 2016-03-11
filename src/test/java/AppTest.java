import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.*;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();
  public WebDriver getDefaultDriver() {
      return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void rootTest() {
      goTo("http://localhost:4567/");
      assertThat(pageSource()).contains("Login");
  }

  @Test
  public void loginTest() {
    User newUser = new User("matt", "123", "user");
    newUser.save();
    goTo("http://localhost:4567/");
    fill("#name").with("matt");
    fill("#password").with("123");
    submit("#loginBtn");
    assertThat(pageSource()).contains("Choose A Game");
  }

  @Test
  public void simonTest() {
    User newUser = new User("matt", "123", "user");
    newUser.save();
    goTo("http://localhost:4567/");
    fill("#name").with("matt");
    fill("#password").with("123");
    submit("#loginBtn");
    click("#simonGameHomeImage");
    assertThat(pageSource()).contains("Simon Says");
    assertThat(pageSource()).contains("Select a difficulty level");
  }

  @Test
  public void memoryTest() {
    User newUser = new User("matt", "123", "user");
    newUser.save();
    goTo("http://localhost:4567/");
    fill("#name").with("matt");
    fill("#password").with("123");
    submit("#loginBtn");
    click("#memoryGameHomeImage");
    assertThat(pageSource()).contains("Memory");
    assertThat(pageSource()).contains("Select a difficulty level");
  }

  @Test
  public void tamagotchiTest() {
    User newUser = new User("matt", "123", "user");
    newUser.save();
    goTo("http://localhost:4567/");
    fill("#name").with("matt");
    fill("#password").with("123");
    submit("#loginBtn");
    click("#tamagotchiGameHomeImage");
    assertThat(pageSource()).contains("Tamagotchi Simulator");
    assertThat(pageSource()).contains("Name your new pet");

  }

  @Test
  public void createTamagotchiTest() {
    User newUser = new User("matt", "123", "user");
    newUser.save();
    goTo("http://localhost:4567/");
    fill("#name").with("matt");
    fill("#password").with("123");
    submit("#loginBtn");
    click("#tamagotchiGameHomeImage");
    fill("#name").with("toby");
    submit("#hatch");
    assertThat(pageSource()).contains("toby");
  }

  @Test
  public void profileTest() {
    User newUser = new User("matt", "123", "user");
    newUser.save();
    goTo("http://localhost:4567/");
    fill("#name").with("matt");
    fill("#password").with("123");
    submit("#loginBtn");
    click("#profileDropDown");
    click("#profileLink");
    assertThat(pageSource()).contains("Welcome matt");
  }

  @Test
  public void signOutTest() {
    User newUser = new User("matt", "123", "user");
    newUser.save();
    goTo("http://localhost:4567/");
    fill("#name").with("matt");
    fill("#password").with("123");
    submit("#loginBtn");
    click("#profileDropDown");
    click("#signOutLink");
    assertThat(pageSource()).contains("Login");
  }

  @Test
  public void changePasswordTest() {
    User newUser = new User("matt", "123", "user");
    newUser.save();
    goTo("http://localhost:4567/");
    fill("#name").with("matt");
    fill("#password").with("123");
    submit("#loginBtn");
    click("#profileDropDown");
    click("#profileLink");
    fill("#updatePassword").with("1234");
    submit("#updatePasswordBtn");
    click("#profileDropDown");
    click("#signOutLink");
    fill("#name").with("matt");
    fill("#password").with("1234");
    submit("#loginBtn");
    assertThat(pageSource()).contains("Choose A Game");
  }
}
