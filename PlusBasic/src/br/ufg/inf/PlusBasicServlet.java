package br.ufg.inf;

/*
 * Copyright (c) 2013 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this 
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under
 * is distributed on an "AS IS" BASIS, WHITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * or implied. See the License for the specific language governing permissions and 	   * limitations the License.
 */

/**
 * A aplicação retorna atividades contendo a palavra-chave Google.
 */

import com.google.api.client.extensions.appengine.http.UrlFetchTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.services.plus.Plus;
import com.google.api.services.plus.PlusRequestInitializer;
import com.google.api.services.plus.model.Activity;
import com.google.api.services.plus.model.ActivityFeed;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PlusBasicServlet extends HttpServlet {

// Chave criada a partir do Google Developer's Console para esta aplicação, em específico.
private static final String API_KEY = "AIzaSyBQ7PTI97Pa9Qk7oA7fpjwze7OqXHhYHD8"; 
private static final long serialVersionUID = 1;

public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		HttpTransport httpTransport = new UrlFetchTransport();
		JsonFactory jsonFactory = new JacksonFactory();

		Plus plus = new Plus.Builder(httpTransport, jsonFactory, null)
				.setApplicationName("")
				.setGoogleClientRequestInitializer(
						new PlusRequestInitializer(API_KEY)).build();

		ActivityFeed myActivityFeed = plus.activities().search("Google")
				.execute();
		List<Activity> myActivities = myActivityFeed.getItems();

		resp.setContentType("text/html");
		resp.setStatus(200);
		Writer writer = resp.getWriter();
		writer.write("<ul>");

		for (Activity a : myActivities) {
			writer.write("<li>" + a.getTitle() + "</li>");
		}
		writer.write("</ul>");

	}

}