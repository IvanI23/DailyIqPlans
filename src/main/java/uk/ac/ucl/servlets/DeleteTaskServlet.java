package uk.ac.ucl.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uk.ac.ucl.model.DeleteModel;
import java.io.IOException;


@WebServlet("/delete.html")
public class DeleteTaskServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String date = request.getParameter("date");
        String subject = request.getParameter("subject");
        String period = request.getParameter("period");
        String time = request.getParameter("time");
        String note = request.getParameter("note");
        String complete = request.getParameter("complete");

        DeleteModel model = new DeleteModel(date, subject, period, time, note, complete);
        model.delete();

        String url = "http://localhost:8080/plan.html?date="+date;
        response.sendRedirect(url);
    }
}
