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
import net.codestory.http.appengine.AppEngineFilter;
import net.codestory.http.routes.Routes;

public class MainWeb {
  public static void main(String[] args) {
    new WebServer().configure(WebConfiguration.class).start();
  }

  public static class WebConfiguration implements Configuration {
    @Override
    public void configure(Routes routes) {
      routes
        .filter(AppEngineFilter.class)
        .add(MessagesResource.class);
    }
  }
}
