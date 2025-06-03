<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Arrays" %>

<html>
<head>
    <%
        HashMap<String, List<List<String>>> plan = (HashMap<String, List<List<String>>>) request.getAttribute("Plan");
        List<String> keys = new ArrayList<>(plan.keySet());
        String date= "Today";
        for (String n: keys) {
            if (n.equals("Tomorrow") || n.equals("Today") || n.equals("Yesterday")) {
                date = n;
            }
        }
        List<String> sortedKeys = new ArrayList<>(Arrays.asList("MORNING", "AFTERNOON", "EVENING"));
    %>

    <title><%=date%></title>
    <link rel="stylesheet" href="static/styles.css">
    <style>
        .main {
            display: flex;
            gap: 30px;
        }
        .container {
            width: 250px;
            height: 300px;
            overflow: auto;
            scrollbar-width: thin;
        }
        #Fixed {
            margin-top: -20px;
            margin-bottom: -15px;
        }

    </style>
</head>
<body>

<nav>
    <% if (date.equals("Today")){%>
        <button id="Tomorrow" onclick="window.location.href='/plan.html?date=Tomorrow';">Tomorrow</button>
        <button id="Yesterday" onclick="window.location.href='/plan.html?date=Yesterday';">Yesterday</button>
    <% }else if(date.equals("Yesterday")){%>
        <button id= "Today1" onclick="window.location.href='/plan.html?date=Today';">Today</button>
        <button id="Tomorrow" onclick="window.location.href='/plan.html?date=Tomorrow';">Tomorrow</button>
    <%} else{ %>
        <button id="Today" onclick="window.location.href='/plan.html?date=Today';">Today</button>
        <button id="Yesterday" onclick="window.location.href='/plan.html?date=Yesterday';">Yesterday</button>
    <%} %>
    <a href="search.html">Search</a>
</nav>

<div class="main">
    <%
        for (String key : sortedKeys) {
    %>
    <div class="container">
        <h2 id="Fixed"><%= key %></h2>

        <button id="all" class = "smallBTN" onclick="window.location.href='/plan.html?date=<%=date%>';">All</button>
        <button id="<%=key%>toDO" class = "smallBTN" onclick="window.location.href='/sort.html?date=<%=date%>&period=<%=key%>&sortType=FALSE';">📝</button>
        <button id="<%=key%>completed" class = "smallBTN" onclick="window.location.href='/sort.html?date=<%=date%>&period=<%=key%>&sortType=TRUE';">✔</button>
        <button id="add" class = "smallBTN" onclick="openModal('<%= key %>')">+</button>

        <%
            for (List<String> activity : plan.get(key)) {
                String subject = activity.get(0);
                String time = activity.get(1);
                String note = activity.get(2);
                String complete = activity.get(3);
                String url = activity.get(4);
        %>
        <div class="subject-row">
            <button
                    class="subject-name"
                    onclick="openSUBJECT('<%= date %>', '<%= key %>', '<%= subject %>', '<%= time %>', '<%=note%>', '<%=complete%>', '<%=url%>')">
                <%= subject %>
            </button>

            <form method="post" action="edit.html">
                <input type="hidden"  name="oldDate" value="<%=date%>">
                <input type="hidden"  name="oldSubject" value="<%=subject%>">
                <input type="hidden"  name="oldPeriod" value="<%=key%>">
                <input type="hidden"  name="oldTime" value="<%=time%>">
                <input type="hidden"  name="oldNote" value="<%=note%>">
                <input type="hidden"  name="oldComplete" value="<%=complete%>">

                <input type="hidden"  name="newDate" value="<%=date%>">
                <input type="hidden"  name="newSubject" value="<%=subject%>">
                <input type="hidden"  name="newPeriod" value="<%=key%>">
                <input type="hidden"  name="newTime" value="<%=time%>">
                <input type="hidden"  name="newNote" value="<%=note%>">
                <%  String value;
                    if (complete.equals("TRUE")) {
                        value = "FALSE";
                    }else{
                        value = "TRUE";
                    }
                %>
                <input type="hidden"  name="newComplete" value="<%=value%>">

                <% if (complete.equals("FALSE")){%>
                    <button type = "submit" class="incomplete">✔</button>
                <%}else{%>
                    <button type = "submit" class="complete">✔</button>
                <%}%>
            </form>

        </div>


        <% } %>
    </div>
    <% } %>
</div>


<div id="modal" class="modal">
    <div class="modal-content">
        <span class="close" onclick="closeModal()">&times;</span>
        <h2>Add Activity to <span id="modal-title"></span></h2>

        <form action="/add.html" method="post">
            <label for="subject">Subject:</label>
            <input type="text" id="subject" name="subject" required><br><br>

            <label for="time">Time:</label>
            <input type="number" step="any" id="time" name="time" required><br><br>

            <label for="note">Note</label>
            <input type="text" id="noteR" name="note" required><br><br>

            <label for="image">Image URL:</label>
            <input type="url" name="url"><br><br>

            <input type="hidden" id="period" name="period">
            <input type="hidden" id="day" name="day" value=<%=date%>>

            <button type="submit">Add</button>
        </form>
    </div>
</div>

<div id="modal2" class="modal">
    <div class="modal-content">
        <span class="close" onclick="closeModal()">&times;</span>
        <h2><span id="data-period"></span> of <span id="date"></span></h2>
        <p id ="disclaimer"> (Click VIEW to view pictures)</p>
        <p> Subject: <span id="subjectName"></span> </p>
        <p> Hours to Spend: <span id="subjectTime"></span> </p>
        <p> Completed status: <span id="complete"></span></p>
        <p>Note: <span id="note"></span></p>

        <div class="button-container">
            <form method="POST" action="/delete.html">
                <input type="hidden" id="date1" name="date">
                <input type="hidden" id="date-period1" name="period">
                <input type="hidden" id="subjectName1" name="subject">
                <input type="hidden" id="subjectTime1" name="time">
                <input type="hidden" id="note1" name="note">
                <input type="hidden" id="complete1" name="complete">
                <button class="submit">🗑️</button>
            </form>

            <button class="edit-btn" onclick="openEditModal(
                document.getElementById('date').textContent,
                document.getElementById('data-period').textContent,
                document.getElementById('subjectName').textContent,
                document.getElementById('subjectTime').textContent,
                document.getElementById('note').textContent,
                document.getElementById('complete').textContent
            )">✏️</button>

            <form method="POST" action="/display.html">
                <input type="hidden" id="date2" name="date">
                <input type="hidden" id="date-period2" name="period">
                <input type="hidden" id="subjectName2" name="subject">
                <input type="hidden" id="subjectTime2" name="time">
                <input type="hidden" id="note2" name="note">
                <input type="hidden" id="complete2" name="complete">
                <input type="hidden" id="url" name="url">
                <button class="submit">VIEW</button>
            </form>
        </div>

    </div>
</div>

<div id="editModal" class="modal">
    <div class="modal-content">
        <span class="close" onclick="closeModal()">&times;</span>

        <form action="/edit.html" method="post">
            <h2>Edit Activity for <span id="edit-modal-title"></span></h2>

            <input type="hidden" id="old-date" name="oldDate">
            <input type="hidden" id="old-subject" name="oldSubject">
            <input type="hidden" id="old-period" name="oldPeriod">
            <input type="hidden" id="old-time" name="oldTime">
            <input type="hidden" id="old-note" name="oldNote">
            <input type="hidden" id="old-complete" name="oldComplete">

            <label for="new-subject">Subject:</label>
            <input type="text" id="new-subject" name="newSubject" required><br><br>

            <label for="new-time">Time:</label>
            <input type="number" step="any" id="new-time" name="newTime" required><br><br>

            <label for="new-note">Note:</label>
            <input type="text" id="new-note" name="newNote" required><br><br>

            <label for="image">Image URL:</label>
            <input type="url" id="image" name="url"><br><br>

            <label for="new-complete">Completed:</label>
            <select id="new-complete" name="newComplete">
                <option value="FALSE">Not Completed</option>
                <option value="TRUE">Completed</option>
            </select><br><br>

            <input type="hidden" id="new-period" name="newPeriod">
            <input type="hidden" id="new-day" name="newDate">

            <button type="submit" id="save">Save Changes</button>
        </form>

    </div>
</div>


<script>
    function openEditModal(date, period, subject, time, note, complete) {
        let modal = document.getElementById("editModal");

        document.getElementById("old-date").value = date;
        document.getElementById("old-subject").value = subject;
        document.getElementById("old-period").value = period;
        document.getElementById("old-time").value = time;
        document.getElementById("old-note").value = note;
        document.getElementById("old-complete").value = complete;

        document.getElementById("edit-modal-title").textContent = period;
        document.getElementById("new-subject").value = subject;
        document.getElementById("new-time").value = time;
        document.getElementById("new-note").value = note;
        document.getElementById("new-complete").value = complete;
        document.getElementById("new-period").value = period;
        document.getElementById("new-day").value = date;

        modal.style.display = "block";
    }

    function openSUBJECT(date, period, subject, time, note, complete, url) {
        let modal = document.getElementById("modal2");

        document.getElementById("date").textContent = date;
        document.getElementById("data-period").textContent = period;
        document.getElementById("subjectName").textContent = subject;
        document.getElementById("subjectTime").textContent = time;
        document.getElementById("note").textContent = note;
        document.getElementById("complete").textContent = complete;

        document.getElementById("date1").value = date;
        document.getElementById("date-period1").value = period;
        document.getElementById("subjectName1").value = subject;
        document.getElementById("subjectTime1").value = time;
        document.getElementById("note1").value = note;
        document.getElementById("complete1").value = complete;

        document.getElementById("date2").value = date;
        document.getElementById("date-period2").value = period;
        document.getElementById("subjectName2").value = subject;
        document.getElementById("subjectTime2").value = time;
        document.getElementById("note2").value = note;
        document.getElementById("complete2").value = complete;
        document.getElementById("url").value = url;

        modal.style.display = "block";
    }

    function openModal(period) {
        document.getElementById('modal').style.display = 'block';
        document.getElementById('modal-title').textContent = period;
        document.getElementById('period').value = period;
    }

    function closeModal() {
        document.getElementById("modal").style.display = "none";
        document.getElementById("modal2").style.display = "none";
        document.getElementById("editModal").style.display = "none";
    }

    window.onclick = function(event) {
        let modal = document.getElementById("modal");
        let modal2 = document.getElementById("modal2");
        let editModal = document.getElementById("editModal")
        if (event.target === modal) {
            modal.style.display = "none";
        }
        if (event.target === modal2) {
            modal2.style.display = "none";
        }
        if (event.target === editModal){
            editModal.style.display = "none";
        }
    }
    document.addEventListener("DOMContentLoaded", function () {
        let buttons = document.querySelectorAll("#MORNINGtoDO, #MORNINGcompleted, " +
            "#AFTERNOONtoDO, #AFTERNOONcompleted, " +
            "#EVENINGtoDO, #EVENINGcompleted, #all, #add", "#save");

        // Retrieve the last active button from localStorage
        let activeButton = localStorage.getItem("activeButton");

        if (activeButton) {
            document.getElementById(activeButton)?.classList.add("active");
        }

        buttons.forEach(button => {
            button.addEventListener("click", function () {
                buttons.forEach(btn => btn.classList.remove("active"));

                if (this.id !== "all" && this.id !== "add" && this.id !== "save") {
                    this.classList.add("active");
                    localStorage.setItem("activeButton", this.id);
                } else {
                    localStorage.removeItem("activeButton");
                }
            });
        });
    });



</script>

</body>
</html>
