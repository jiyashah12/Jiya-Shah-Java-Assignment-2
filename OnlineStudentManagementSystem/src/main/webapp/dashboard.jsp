<html>
<head>
<title>Admin Dashboard</title>
<link rel="stylesheet" href="css/dashboard.css">
</head>
<body>
<h3 style="text-align: center;">Welcome, <%= session.getAttribute("username") %>!</h3>

    <div class="dashboard-container">
            <div class="card">
                <a href="viewStudent">View All Students</a>
            </div>

            <div class="card">
                <a href="addStudent.jsp">Add New Student</a>
            </div>

            <div class="card">
                <a href="viewStudent">Update Student</a>
            </div>

            <div class="card">
                <a href="viewStudent">Delete Student</a>
            </div>
        </div>

        <div class="logout-button">
            <a href="logout" class="logout">Logout</a>
        </div>

</body>
</html>


