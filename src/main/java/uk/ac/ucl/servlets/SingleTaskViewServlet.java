package uk.ac.ucl.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/display.html")
public class SingleTaskViewServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String date = request.getParameter("date");
        String period = request.getParameter("period");
        String subject = request.getParameter("subject");
        String time = request.getParameter("time");
        String note = request.getParameter("note");
        String complete = request.getParameter("complete");
        String imageUrl = request.getParameter("url");

        List<String> activity = Arrays.asList(date, subject, period, time, note, complete, imageUrl);

        request.setAttribute("activity", activity);
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/single.jsp");
        dispatch.forward(request, response);
    }
}
