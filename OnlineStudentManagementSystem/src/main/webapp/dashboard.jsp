<html>
<head>
<title>Admin Dashboard</title>
<link rel="stylesheet" href="css/dashboard.css">
</head>
<body>
<h3 style="text-align: center;">Welcome, <%= session.getAttribute("username") %>!</h3>

<!-- Dashboard Content -->
    <div class="dashboard-container">
            <!-- View All Students Card -->
            <div class="card">
                <a href="viewStudent">View All Students</a>
            </div>

            <!-- Add New Student Card -->
            <div class="card">
                <a href="addStudent.jsp">Add New Student</a>
            </div>

            <!-- Update Student Card -->
            <div class="card">
                <a href="viewStudent">Update Student</a>
            </div>

            <!-- Delete Student Card -->
            <div class="card">
                <a href="viewStudent">Delete Student</a>
            </div>
        </div>

    <!-- Logout Button -->
        <div class="logout-button">
            <a href="logout" class="logout">Logout</a>
        </div>

</body>
</html>


