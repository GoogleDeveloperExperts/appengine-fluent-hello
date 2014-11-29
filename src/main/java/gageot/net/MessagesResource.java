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

import net.codestory.http.annotations.Get;
import net.codestory.http.annotations.Post;

import java.util.List;

public class MessagesResource {
  private final Messages messages;

  public MessagesResource(Messages messages) {
    this.messages = messages;
  }

  @Get("/message/list")
  public List<String> list() {
    return messages.list();
  }

  @Post("/message")
  public void create(String message) {
    messages.create(message);
  }
}
