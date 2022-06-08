package com.google.sps.servlets;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/form-handler")
public class FormHandlerServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    //Gets the name, email and message submitted by a user
    String textValue = request.getParameter("message");
    String name = request.getParameter("name");
    String email = request.getParameter("email");

    // This prints out all details in the serverlogs
    System.out.println(name + " submitted this message: " + textValue + "You can contact them at " + email);

    // Redirects the user back to the contact page
    response.sendRedirect("/contact.html");
  }
}