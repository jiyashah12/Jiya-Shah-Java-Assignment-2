import java.io.*;
import java.sql.*;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
//import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet("/addstudent")
public class AddStudent extends HttpServlet{

    private static final String url = "jdbc:postgresql://localhost:5433/StudentManagement";
    private static final String username = "postgres";
    private static final String password = "postgrejiya12";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("text/html");

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            resp.sendRedirect("login.jsp");
            return;
        }
        PrintWriter out = resp.getWriter();

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String course = req.getParameter("course");
        int yos = Integer.parseInt(req.getParameter("yos"));


        try
        {
            Class.forName("org.postgresql.Driver");

            try(Connection conn = DriverManager.getConnection(url, username, password))
            {
                String query = "INSERT INTO students ( name, email, phone, course, year_of_study) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement pstm = conn.prepareStatement(query);
                pstm.setString(1, name);
                pstm.setString(2, email);
                pstm.setString(3, phone);
                pstm.setString(4, course);
                pstm.setInt(5, yos);


                int rowsAffected = pstm.executeUpdate();
                if(rowsAffected > 0)
                {   resp.setContentType("text/html");
                    out.print("<h3 style='color:green'> Student added successfully  </h3>");
                    RequestDispatcher rd = req.getRequestDispatcher("viewStudent");    //use "/profile.jsp"
                    rd.include(req, resp);
                    // Redirect to viewStudent.jsp after successful addition
//                    resp.sendRedirect("/viewStudent.jsp");  // Redirect to view students page
                }
                else
                {
                    out.print("<h3 style='color:red'> Error adding student </h3>");
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




