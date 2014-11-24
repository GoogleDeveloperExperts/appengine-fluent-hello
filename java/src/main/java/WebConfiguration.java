import net.codestory.http.Configuration;
import net.codestory.http.routes.Routes;

public class WebConfiguration implements Configuration {
  @Override
  public void configure(Routes routes) {
    configureAppEngineRoutes(routes);

    routes.get("/greeting", () -> "Welcome to fluent-http");
  }

  private void configureAppEngineRoutes(Routes routes) {
    routes
      .get("/_ah/start", "ok")
      .get("/_ah/health", "ok");
  }
}
