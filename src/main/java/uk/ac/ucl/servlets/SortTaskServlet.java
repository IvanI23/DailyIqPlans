package uk.ac.ucl.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uk.ac.ucl.model.SortPlanModel;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@WebServlet("/sort.html")
public class SortTaskServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        String period = request.getParameter("period");
        String date = request.getParameter("date");
        String sortType = request.getParameter("sortType");

        SortPlanModel model = new SortPlanModel(date, period, sortType);
        HashMap<String, List<List<String>>> plan = model.getSortPlan();

        request.setAttribute("Plan", plan);
        RequestDispatcher dispatch = request.getRequestDispatcher("plan.jsp");
        dispatch.forward(request, response);

    }
}
