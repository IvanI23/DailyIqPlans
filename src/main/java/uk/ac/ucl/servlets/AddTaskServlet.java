package uk.ac.ucl.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uk.ac.ucl.model.AddModel;
import uk.ac.ucl.model.PlanModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet("/add.html")
public class AddTaskServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    //Method requires both get and post processes to format a argument for the model
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String day = request.getParameter("day");
        String period = request.getParameter("period");
        String subject = request.getParameter("subject");
        String time = request.getParameter("time");
        String note = request.getParameter("note");
        String imageUrl = request.getParameter("url");

        if (imageUrl == null || imageUrl.trim().isEmpty()) {
            imageUrl = "null";
        }
        List<String> activity = new ArrayList<>(Arrays.asList(day, subject, period, time, note, "FALSE", imageUrl));
        AddModel model = new AddModel();
        model.addEntry(activity);

        String url = "http://localhost:8080/plan.html?date="+day;
        response.sendRedirect(url);

    }
}

