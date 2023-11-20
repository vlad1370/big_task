package by.itclass;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

@WebServlet(name = "formServlet", urlPatterns = "/form")
public class FormServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (PrintWriter writer = resp.getWriter()) {
            String name = req.getParameter("userName");
            String age = req.getParameter("userAge");
            String gender = req.getParameter("gender");
            String country = req.getParameter("country");
            String[] courses = req.getParameterValues("courses");

            writer.write("<p> Name: " + name + "</p>");
            writer.write("<p> Age: " + age + "</p>");
            writer.write("<p> Gender: " + gender + "</p>");
            writer.write("<p> Country: " + country + "</p>");
            writer.write("<p> Courses: " + "</p>");
            writer.write("<ul>");
            Arrays.stream(courses)
                    .forEach(it -> writer.write("<li>" + it + "</li>"));
            writer.write("</ul>");
        }

    }
}
