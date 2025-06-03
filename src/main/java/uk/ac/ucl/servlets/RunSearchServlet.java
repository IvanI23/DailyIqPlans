package uk.ac.ucl.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uk.ac.ucl.model.SearchModel;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@WebServlet("/runsearch.html")
public class RunSearchServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String search = request.getParameter("search");
        String[] searchArray = search.split(" ");
        List<String> searchFormat = Arrays.asList(searchArray);

        SearchModel model = new SearchModel("ignore", searchFormat);
        HashMap<String, List<List<String>>> plan = model.returnSearch();
        plan.get("ignore").add(searchFormat);
        request.setAttribute("Plan", plan);

        // Invoke the JSP.
        // A JSP page is actually converted into a Java class, so behind the scenes everything is Java.
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/viewSearch.jsp");
        dispatch.forward(request, response);
    }
}