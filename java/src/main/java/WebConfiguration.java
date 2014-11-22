import net.codestory.http.Configuration;
import net.codestory.http.routes.Routes;

public class WebConfiguration implements Configuration {
  @Override
  public void configure(Routes routes) {
    routes.get("/_ah/start", "ok");
    routes.get("/_ah/health", "ok");
  }
}
