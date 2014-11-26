import net.codestory.http.Configuration;
import net.codestory.http.WebServer;
import net.codestory.http.filters.log.LogRequestFilter;
import net.codestory.http.routes.Routes;
import net.codestory.http.templating.Model;

public class MainWeb {
  public static void main(String[] args) {
    new WebServer().configure(WebConfiguration.class).start();
  }

  public static class WebConfiguration implements Configuration {
    @Override
    public void configure(Routes routes) {
      routes
        .filter(LogRequestFilter.class)
        .get("/_ah/start", "ok")
        .get("/_ah/stop", "ok")
        .get("/_ah/health", "ok")
        .get("/greeting", "Welcome to fluent-http")
        .get("/env", Model.of("variables", System.getenv()))
        .get("/request", context -> Model.of("headers", context.request().headers()));
    }
  }
}
