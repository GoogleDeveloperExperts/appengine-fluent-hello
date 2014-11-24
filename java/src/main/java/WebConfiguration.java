import net.codestory.http.Configuration;
import net.codestory.http.filters.log.LogRequestFilter;
import net.codestory.http.routes.Routes;

public class WebConfiguration implements Configuration {
  @Override
  public void configure(Routes routes) {
    routes
      .filter(LogRequestFilter.class)
      .get("/_ah/start", "ok")
      .get("/_ah/stop", "ok")
      .get("/_ah/health", "ok")
      .get("/greeting", "Welcome to fluent-http");
  }
}
