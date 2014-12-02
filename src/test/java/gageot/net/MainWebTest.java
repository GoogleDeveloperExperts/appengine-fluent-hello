/**
 * Copyright (C) 2014 david@gageot.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */
package gageot.net;

import net.codestory.http.Configuration;
import net.codestory.http.WebServer;
import net.codestory.http.injection.Singletons;
import net.codestory.simplelenium.SeleniumTest;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MainWebTest extends SeleniumTest {
  Messages messages = new Messages() {
    private final List<String> messages = new ArrayList<>();

    @Override
    public List<String> list() {
      return messages;
    }

    @Override
    public void create(String message) {
      messages.add(message);
    }
  };

  WebServer webServer = new WebServer().configure(
      override(new MainWeb.WebConfiguration())
          .with(routes -> routes.setIocAdapter(new Singletons().register(Messages.class, messages))));

  @Override
  protected String getDefaultBaseUrl() {
    return "http://localhost:" + webServer.port();
  }

  @Test
  public void show_messages() {
    messages.create("Message01");
    messages.create("Message02");

    goTo("/");

    find("h2").should().contain("Managed VMs Sample Project");
    find("#messages").should().contain("Message01", "Message02");
  }

  @Test
  public void add_message() {
    messages.create("FirstMessage");

    goTo("/");

    find("#message").fill("SecondMessage");
    find("#add").click();

    find("#messages").should().contain("FirstMessage", "SecondMessage");
  }

  private static ConfigurationOverride override(Configuration configuration) {
    return new ConfigurationOverride(configuration);
  }

  private static class ConfigurationOverride {
    private final Configuration configuration;

    public ConfigurationOverride(Configuration configuration) {
      this.configuration = configuration;
    }

    public Configuration with(Configuration override) {
      return routes -> {
        configuration.configure(routes);
        override.configure(routes);
      };
    }
  }
}