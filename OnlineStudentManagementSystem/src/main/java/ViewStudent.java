import java.io.*;
import java.sql.*;
import java.util.*;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
//import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.annotation.*;

@WebServlet("/viewStudent")
public class ViewStudent extends HttpServlet {

    private static final String url = "jdbc:postgresql://localhost:5433/StudentManagement";
    private static final String username = "postgres";
    private static final String password = "postgrejiya12";

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            resp.sendRedirect("login.jsp");
            return;
        }
        ArrayList<ArrayList<Object>> studentList = new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");

            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                String sql = "SELECT * FROM students";
                try (Statement stm = conn.createStatement();
                     ResultSet rs = stm.executeQuery(sql)) {

                    while (rs.next()) {
                        ArrayList<Object> student = new ArrayList<>();
                        student.add(rs.getInt("id"));
                        student.add(rs.getString("name"));
                        student.add(rs.getString("email"));
                        student.add(rs.getString("phone"));
                        student.add(rs.getString("course"));
                        student.add(rs.getInt("year_of_study"));

                        studentList.add(student);
                    }
                }
                req.setAttribute("students", studentList);
                RequestDispatcher rd = req.getRequestDispatcher("viewStudent.jsp");
                rd.forward(req, resp);

            }
            catch(SQLException s) {
//                resp.sendRedirect("error.jsp"); // Redirect to error page in case of failure
                System.out.println(s.getMessage());
            }
        }
        catch(ClassNotFoundException c)
        {
            System.out.println(c.getMessage());
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
