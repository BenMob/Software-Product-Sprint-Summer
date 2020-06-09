// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.servlets;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet that returns some example content. TODO: modify this file to handle comments data */
@WebServlet("/data")
public class DataServlet extends HttpServlet {

  private String name;
  private String comment;

  @Override
  public void init() throws ServletException {
      name = "N/A";
      comment = "N/A";
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    name = request.getParameter("author");
    comment = request.getParameter("comment");

    response.setContentType("text/html;");
    response.getWriter().println("<h2>Name: " + name + "</h2>");
    response.getWriter().println("<p>Comment: " + comment + " </p> <hr>");
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
      doGet(request, response);
  }
}
