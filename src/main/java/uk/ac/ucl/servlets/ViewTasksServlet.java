package uk.ac.ucl.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uk.ac.ucl.model.PlanModel;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;

// The servlet invoked to display a list of patients. Note that this data is just example data,
// you replace it with your data.
// The url http://localhost:8080/patientList.html is mapped to calling doGet on the servlet object.
// The servlet object is created automatically, you just provide the class.
@WebServlet("/plan.html")
public class ViewTasksServlet extends HttpServlet
{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        String date = request.getParameter("date");
        // Get the data from the model
        PlanModel model = new PlanModel(date);
        HashMap<String, List<List<String>>> plan = model.getDaysPlan();
        // Then add the data to the request object that will be sent to the Java Server Page, so that
        // the JSP can access the data (a Java data structure).


        request.setAttribute("Plan", plan);

        // Invoke the JSP.
        // A JSP page is actually converted into a Java class, so behind the scenes everything is Java.
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/plan.jsp");
        dispatch.forward(request, response);
    }
}