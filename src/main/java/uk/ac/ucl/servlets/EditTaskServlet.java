package uk.ac.ucl.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uk.ac.ucl.model.EditModel;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/edit.html")
public class EditTaskServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oldDate = request.getParameter("oldDate");
        String oldSubject = request.getParameter("oldSubject");
        String oldPeriod = request.getParameter("oldPeriod");
        String oldTime = request.getParameter("oldTime");
        String oldNote = request.getParameter("oldNote");
        String oldComplete = request.getParameter("oldComplete");

        String newDate = request.getParameter("newDate");
        String newSubject = request.getParameter("newSubject");
        String newPeriod = request.getParameter("newPeriod");
        String newTime = request.getParameter("newTime");
        String newNote = request.getParameter("newNote");
        String newComplete = request.getParameter("newComplete");

        String imageUrl = request.getParameter("url");

        //Helps to condense arguments
        List<String> oldActivity = Arrays.asList(oldDate, oldSubject, oldPeriod, oldTime, oldNote, oldComplete);
        List<String> newActivity = Arrays.asList(newDate, newSubject, newPeriod, newTime, newNote, newComplete, imageUrl);

        EditModel model = new EditModel(oldActivity, newActivity);
        model.edit();

        String url = "http://localhost:8080/plan.html?date="+newDate;
        response.sendRedirect(url);

    }
}
