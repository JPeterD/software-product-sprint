package com.google.sps.servlets;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.KeyFactory;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

@WebServlet("/form-handler")
public class FormHandlerServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    //Gets the name, email and message submitted by a user and clean the input
    String textValue = Jsoup.clean(request.getParameter("message"), Safelist.none());
    String name = Jsoup.clean(request.getParameter("name"), Safelist.none());
    String email = Jsoup.clean(request.getParameter("email"), Safelist.none());

    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    KeyFactory keyFactory = datastore.newKeyFactory().setKind("Form");

    //Creates the form entity comprising of a name, an email and a message
    FullEntity formEntity =
        Entity.newBuilder(keyFactory.newKey())
            .set("name", name)
            .set("email", email)
            .set("message", textValue)
            .build();

    //Places the entity into the database
    datastore.put(formEntity);

    // This prints out all details in the serverlogs
    System.out.println(name + " submitted this message: " + textValue + "You can contact them at " + email);

    // Redirects the user back to the contact page
    response.sendRedirect("/contact.html");
  }
}