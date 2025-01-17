import java.io.*;
import java.sql.*;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
//import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet("/deletestudent")
public class DeleteStudent extends HttpServlet{

    private static final String url = "jdbc:postgresql://localhost:5433/StudentManagement";
    private static final String username = "postgres";
    private static final String password = "postgrejiya12";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        String id = req.getParameter("id");

        try
        {
            Class.forName("org.postgresql.Driver");

            try(Connection conn = DriverManager.getConnection(url, username, password))
            {
                String query = "DELETE FROM students WHERE id = ?";
                PreparedStatement pstm = conn.prepareStatement(query);
                pstm.setInt(1, Integer.parseInt(id));

                int rowsAffected = pstm.executeUpdate();
                if(rowsAffected > 0)
                {
                    out.print("<h3 style='color:green'> Student deleted successfully  </h3>");
                    RequestDispatcher rd = req.getRequestDispatcher("/viewStudent");    //use "/profile.jsp"
                    rd.include(req, resp);
                }
                else
                {
                    out.print("<h3 style='color:red'> Error deleting student </h3>");
                    RequestDispatcher rd = req.getRequestDispatcher("/dashboard.jsp");
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




