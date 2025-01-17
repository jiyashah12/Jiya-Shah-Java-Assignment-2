<html>
<head>
<title>Login</title>
<link rel="stylesheet" href="css/logincss.css">
</head>
<body>
<div class="container">
    <form action="loginForm" method="post">
       <h1 style="text-align: center;">Welcome</h1>
       Username:<input type="text" name="username1" placeholder="Enter username" required /> <br/> <br/>
       Password: <input type="password" name="pass1" placeholder="Enter password" required/> <br/> <br/>
      <input type="submit" value="Login" /> <br/> <br/>
    </form>
   <p class="error-message">
       <%= request.getAttribute("errorMessage") != null ? request.getAttribute("errorMessage") : "" %>
   </p>

</div>

</body>
</html>
