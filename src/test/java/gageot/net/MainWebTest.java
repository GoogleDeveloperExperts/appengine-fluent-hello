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

    find("h2").should().contain("Managed VMs Sample Project");
  }
}