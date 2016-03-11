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

  @Test
  public void rootTest() {
      goTo("http://localhost:4567/");
      assertThat(pageSource()).contains("Login");
  }

  @Test
  public void loginTest() {
    User newUser = new User("matt", "123", "user");
    goTo("http://localhost:4567/");
    fill("#name").with("matt");
    fill("#password").with("123");
    submit("#loginBtn");
    assertThat(pageSource()).contains("Choose A Game");
  }

  @Test
  public void simonTest() {
    User newUser = new User("matt", "123", "user");
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
    goTo("http://localhost:4567/");
    fill("#name").with("matt");
    fill("#password").with("123");
    submit("#loginBtn");
    click("#tamagotchiGameHomeImage");
    fill("#name").with("toby");
    submit("#hatch");
    assertThat(pageSource()).contains("toby");
  }
}
