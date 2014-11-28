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

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;
import net.codestory.http.annotations.Get;
import net.codestory.http.annotations.Post;

import java.util.Date;
import java.util.List;

import static com.google.appengine.api.datastore.DatastoreServiceFactory.getDatastoreService;
import static com.google.appengine.api.datastore.FetchOptions.Builder.withLimit;
import static com.google.appengine.api.datastore.KeyFactory.createKey;
import static com.google.appengine.api.datastore.Query.SortDirection.DESCENDING;
import static net.codestory.Fluent.of;

public class MessagesResource {
  @Get("/message/list")
  public List<String> list() {
    Query query = new Query("Message", createKey("Messages", "list")).addSort("date", DESCENDING);

    List<Entity> messages = getDatastoreService().prepare(query).asList(withLimit(100));

    return of(messages).map(message -> (String) message.getProperty("message")).toList();
  }

  @Post("/message")
  public void create(String message) {
    Entity entity = new Entity("Message", createKey("Messages", "list"));
    entity.setProperty("message", message);
    entity.setProperty("date", new Date());

    getDatastoreService().put(entity);
  }
}
