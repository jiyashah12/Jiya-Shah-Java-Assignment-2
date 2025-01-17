import java.io.*;
import java.sql.*;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
//import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet("/loginForm")
public class Login extends HttpServlet{

    private static final String url = "jdbc:postgresql://localhost:5433/StudentManagement";
    private static final String username = "postgres";
    private static final String password = "postgrejiya12";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();

        String myuser = req.getParameter("username1");
        String mypass = req.getParameter("pass1");

        try
        {
            Class.forName("org.postgresql.Driver");

            try(Connection conn = DriverManager.getConnection(url, username, password))
            {
                String query = "SELECT * FROM admin WHERE username = ? AND password = ?";
                PreparedStatement pstm = conn.prepareStatement(query);
                pstm.setString(1, myuser);
                pstm.setString(2, mypass);


                ResultSet rs = pstm.executeQuery();
                if(rs.next())
                {
                    HttpSession session = req.getSession();
                    session.setAttribute("username", myuser);
                    RequestDispatcher rd = req.getRequestDispatcher("/dashboard.jsp");    //use "/profile.jsp"
                    rd.forward(req, resp);
                }
                else
                {
                    req.setAttribute("errorMessage", "Wrong Credentials");
                    RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
                    rd.include(req, resp);
                }
            }
            catch(SQLException s)
            {
                System.out.println(s.getMessage());
            }

        }
        catch(ClassNotFoundException c)
        {
            System.out.println(c.getMessage());
        }
    }
}




