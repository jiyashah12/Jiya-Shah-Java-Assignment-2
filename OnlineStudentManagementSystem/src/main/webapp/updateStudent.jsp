<html>
<head>
    <title>Update Student</title>
    <link rel="stylesheet" href="css/updatestudent.css">
</head>
<body>

<%
String name = (String)request.getAttribute("name");
String email = (String)request.getAttribute("email");
String phone = (String)request.getAttribute("phone");
String course = (String)request.getAttribute("course");
String yos = (String)request.getAttribute("year_of_study");
String studentId = (String) request.getAttribute("id");
%>

<div class="form-box">
    <h1>Update Student</h1>
    <form action="updatestudent" method="post">

        <input type="hidden" name="id" value=<%= studentId %> />
        Name: <input type="text" name="name" value=<%= name %> required /><br/><br/>
        Email: <input type="email" name="email" value=<%= email %> required /><br/><br/>
        Phone: <input type="text" name="phone" value=<%= phone %> required /><br/><br/>
        Course: <input type="text" name="course" value=<%= course %> required /><br/><br/>
        Year of Study: <input type="text" name="yos" value=<%= yos %> required /><br/><br/>

        <input type="submit" value="Update" />
    </form>
</div>
</body>
</html>
