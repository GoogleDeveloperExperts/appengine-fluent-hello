import com.google.appengine.api.datastore.*;
import net.codestory.http.Configuration;
import net.codestory.http.WebServer;
import net.codestory.http.annotations.Get;
import net.codestory.http.routes.Routes;
import net.codestory.http.templating.Model;
import net.codestory.http.templating.ModelAndView;

import static com.google.appengine.api.datastore.KeyFactory.createKey;

public class MainWeb {
  public static void main(String[] args) {
    new WebServer().configure(WebConfiguration.class).start();
  }

  public static class WebConfiguration implements Configuration {
    @Override
    public void configure(Routes routes) {
      routes
        .filter(AppEngineFilter.class)
        .add("/_ah", AhResource.class)
        .add("/api", CloudStorageTestResource.class)
        .get("/greeting", "Welcome to fluent-http")
        .get("/env", Model.of("variables", System.getenv()))
        .get("/request", context -> Model.of("headers", context.request().headers()));
    }
  }

  public static class AhResource {
    @Get("start")
    public String start() {
      return "ok";
    }

    @Get("stop")
    public String stop() {
      return "ok";
    }

    @Get("health?IsLastSuccessful:isLastSuccessful")
    public String health(String isLastSuccessful) {
      return "ok";
    }
  }

  public static class CloudStorageTestResource {
    @Get("/add/:name")
    public ModelAndView add(String name) throws EntityNotFoundException {
      DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

      Key key = createKey("Entries", name);
      Entity entity = new Entity("Test", key);
      entity.setProperty("content", "Hello World");
      datastore.put(entity);

      return ModelAndView.of("add", "entry", entity);
    }
  }
}
