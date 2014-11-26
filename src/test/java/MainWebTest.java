import net.codestory.http.WebServer;
import net.codestory.simplelenium.SeleniumTest;
import org.junit.Test;

public class MainWebTest extends SeleniumTest {
  WebServer webServer = new WebServer().configure(MainWeb.WebConfiguration.class).startOnRandomPort();

  @Override
  protected String getDefaultBaseUrl() {
    return "http://localhost:" + webServer.port();
  }

  @Test
  public void index() {
    goTo("/");

    find("h1").should().contain("Hello World!");
    find("h3").should().contain("Welcome to fluent-http");
    find("p").should().contain("Brought to you by Google App Engine.");
  }
}