<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Activity List</title>
    <link rel="stylesheet" href="static/styles.css">
    <style>
        body {
            display: block;
            justify-content: normal;
            align-items: normal;
        }

        h1 {
            margin-bottom: 20px;
            padding-top: 60px;
        }
        p:hover{
            transform: scale(1);
        }
        .activity {
            margin: 20px;
            padding: 15px;
            background-color: rgba(18, 61, 115, 0.8);
            border-radius: 10px;
        }
    </style>
</head>
<body>
<nav>
    <a href="http://localhost:8080">Home</a>
    <a href="search.html">Search</a>
</nav>


<%
    HashMap<String, List<List<String>>> plan = (HashMap<String, List<List<String>>>) request.getAttribute("Plan");
    List<String> search = plan.get("ignore").get(0);
    String searchFormat= search.toString();
 %>
<h1> &emsp;  Displaying results for: <%=searchFormat%></h1> <%
    if (plan != null && !plan.isEmpty()) {
        List<String> keys = new ArrayList<>(plan.keySet());
        for (String key : keys) {
            List<List<String>> activities = plan.get(key);
            if (activities != null) {
                for (List<String> activity : activities) {

                    if (activity != null && activity.size() >= 4) {
                        String day = activity.get(5);
                        String subject = activity.get(0);
                        String time = activity.get(1);
                        String note = activity.get(2);
                        String complete = activity.size() > 4 ? activity.get(3) : "FALSE";
                        String url = activity.get(4);
%>


<div class="activity">
    <p>Day: <%= day %></p>
    <p>Period: <%= key %></p>
    <p>Subject: <%= subject %></p>
    <p>Time: <%= time %></p>
    <p>Note: <%= note %></p>
    <p>Status: <%= "TRUE".equals(complete) ? "Completed" : "Incomplete" %></p>

    <form method="POST" action = "/display.html">
        <input type="hidden"  name="date" value="<%=day%>">
        <input type="hidden"  name="subject" value="<%=subject%>">
        <input type="hidden"  name="period" value="<%=key%>">
        <input type="hidden"  name="time" value="<%=time%>">
        <input type="hidden"  name="note" value="<%=note%>">
        <input type="hidden"  name="complete" value="<%=complete%>">
        <input type="hidden"  name="url" value="<%=url%>">
        <button type="Submit" >VIEW</button>
    </form>
</div>
<%
                }
            }
        }
    }
} else {
%>
<p>No activities found.</p>
<%
    }
%>
</body>
</html>