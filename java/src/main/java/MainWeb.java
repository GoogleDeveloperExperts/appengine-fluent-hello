import net.codestory.http.WebServer;

public class MainWeb {
  public static void main(String[] args) {
    new WebServer().configure(WebConfiguration.class).start();
  }
}
