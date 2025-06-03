<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Objects" %>
<html>
<head>
    <title>Activity Details</title>
    <link rel = "stylesheet" href="static/styles.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(135deg, #15489c, #113233);
            color: #c8e6e6;
            margin: 0;
            padding: 0;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .activity-details {
            background-color: rgba(18, 61, 115, 0.8);
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
            width: 300px;
            text-align: left;
        }
        .activity-details h1 {
            color: #80e0d3;
            text-shadow: 0 0 10px rgba(128, 224, 211, 0.7), 0 0 25px rgba(128, 224, 211, 0.5);
            margin-bottom: 20px;
            text-align: center;
        }
        .activity-details p {
            margin: 10px 0;
        }
        .activity-details strong {
            color: #0bdada;
        }

        .modal{
            left: 0%;
            top: -10%;
            width: 100%;
        }
    </style>
</head>
<body>

<nav>
    <a href="http://localhost:8080">Home</a>
    <a href="search.html">Search</a>
</nav>

<div class="activity-details">
    <h1>Activity Details</h1>
    <%
        List<String> activity = (List<String>) request.getAttribute("activity");
        if (activity != null && activity.size() >= 7) {
            String date = activity.get(0);
            String subject = activity.get(1);
            String period = activity.get(2);
            String time = activity.get(3);
            String note = activity.get(4);
            String complete = activity.get(5);
            String url = activity.get(6);
    %>
    <p>Date: <%= date %></p>
    <p>Subject: <%= subject %></p>
    <p>Period: <%= period %></p>
    <p>Time: <%= time %></p>
    <p>Note: <%= note %></p>
    <p>Status: <%= "TRUE".equals(complete) ? "Completed" : "Incomplete" %></p>

    <div id="button-container">
        <form method="POST" action="/delete.html">
        <input type="hidden" id="date1" name="date" value="<%= date %>">
        <input type="hidden" id="date-period1" name="period" value = "<%=period%>">
        <input type="hidden" id="subjectName1" name="subject" value="<%=subject%>">
        <input type="hidden" id="subjectTime1" name="time" value="<%=time%>">
        <input type="hidden" id="note1" name="note" value="<%=note%>">
        <input type="hidden" id="complete1" name="complete" value="<%=complete%>">
        <button class="submit" >🗑️</button>
        </form>
    </div>

    <button onclick="openEditModal()">✏️</button>
    <div id="editModal" class="modal">
        <div class="modal-content">
            <span class="close" onclick="closeModal()">&times;</span>

            <form action="/edit.html" method="post">
                <h2>Edit Activity for <%=period%></h2>

                <input type="hidden" name="oldDate" value="<%= date %>">
                <input type="hidden" name="oldPeriod" value = "<%=period%>">
                <input type="hidden" name="oldSubject" value="<%=subject%>">
                <input type="hidden" name="oldTime" value="<%=time%>">
                <input type="hidden" name="oldNote" value="<%=note%>">
                <input type="hidden" name="oldComplete" value="<%=complete%>">

                <label for="new-subject">Subject:</label>
                <input type="text" id="new-subject" name="newSubject" value="<%=subject%>" required><br><br>

                <label for="new-time">Time:</label>
                <input type="number" step="any" id="new-time" name="newTime" value="<%=time%>" required><br><br>

                <label for="new-note">Note:</label>
                <input type="text" id="new-note" name="newNote" value="<%=note%>" required><br><br>

                <label for="image">Image URL:</label>
                <input type="url" id="image" name="url"><br><br>

                <label for="new-complete">Completed:</label>
                <select id="new-complete" name="newComplete">
                    <option value="FALSE">Not Completed</option>
                    <option value="TRUE">Completed</option>
                </select><br><br>

                <input type="hidden" id="new-period" name="newPeriod" value="<%= period %>">
                <input type="hidden" id="new-day" name="newDate" value="<%= date %>">

                <button type="submit" id="save">Save Changes</button>
            </form>
        </div>
    </div>

    <% if (url != null && !url.equals("null")){%>
    <button onclick="window.location.href = '<%= url %>'">View Picture</button>
    <%}%>
    <script>
        function openEditModal() {
            let modal = document.getElementById("editModal");
            modal.style.display = "block";
        }


        window.onclick = function(event) {
            let modal = document.getElementById('editModal');
            if (event.target === modal || event.target === modal2) {
                closeModal();
            }
        }
        function closeModal() {
            document.getElementById("editModal").style.display = "none";
        }

    </script>

    <%
    } %>

</div>
</body>
</html>