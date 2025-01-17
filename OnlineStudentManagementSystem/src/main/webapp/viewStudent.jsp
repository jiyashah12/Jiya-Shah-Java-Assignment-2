<!DOCTYPE html>
<html>
<head>
    <title>View Students</title>
    <link rel="stylesheet" type="text/css" href="css/viewstudent.css">
</head>
<%@ page import="java.util.ArrayList" %>
<body>
    <h2>Student List</h2>
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Course</th>
            <th>Year of Study</th>
            <th>Actions</th>
        </tr>
        <%
            ArrayList<ArrayList<Object>> students = (ArrayList<ArrayList<Object>>) request.getAttribute("students");
            if (students != null && !students.isEmpty()) {
                for (ArrayList<Object> student : students) {
        %>
        <tr>
            <td><%= student.get(0) %></td>
            <td><%= student.get(1) %></td>
            <td><%= student.get(2) %></td>
            <td><%= student.get(3) %></td>
            <td><%= student.get(4) %></td>
            <td><%= student.get(5) %></td>
            <td class="actions">
                <a href="updatestudent?id=<%= student.get(0) %>">Update</a>
                <a href="deletestudent?id=<%= student.get(0) %>">Delete</a>
            </td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="7" style="text-align: center;">No students found</td>
        </tr>
        <%
            }
        %>
    </table>
    <div>
        <a href="dashboard.jsp">Back to Dashboard</a>
    </div>
</body>
</html>
