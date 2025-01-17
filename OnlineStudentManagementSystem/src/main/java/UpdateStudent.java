import java.io.*;
import java.sql.*;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
//import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet("/updatestudent")
public class UpdateStudent extends HttpServlet {

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

        try {
            Class.forName("org.postgresql.Driver");
            int studentId = Integer.parseInt(req.getParameter("id"));


            System.out.println(studentId);
            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                String query = "SELECT * FROM students WHERE id = ?";
                PreparedStatement pstm = conn.prepareStatement(query);
                pstm.setInt(1, studentId);

                ResultSet rs = pstm.executeQuery();
                if (rs.next()) {

                    req.setAttribute("id", String.valueOf(rs.getInt("id")));

                    req.setAttribute("name", rs.getString("name"));
                    req.setAttribute("email", rs.getString("email"));
                    req.setAttribute("phone", rs.getString("phone"));
                    req.setAttribute("course", rs.getString("course"));
                    req.setAttribute("year_of_study", String.valueOf(rs.getInt("year_of_study")));

                    RequestDispatcher rd = req.getRequestDispatcher("updateStudent.jsp");
                    rd.forward(req, resp);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException c) {
            System.out.println(c.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Set content type for response
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Retrieve form parameters
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String course = req.getParameter("course");
        int yos;
        int studentId;

        try {
            // Parse integer parameters with error handling
            yos = Integer.parseInt(req.getParameter("yos"));
            studentId = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException e) {
            out.print("<h3 style='color:red'> Invalid input for Year of Study or Student ID </h3>");
            RequestDispatcher rd = req.getRequestDispatcher("/dashboard.jsp");
            rd.include(req, resp);
            return;
        }

        try {
            Class.forName("org.postgresql.Driver");

            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                String query = "UPDATE students SET name = ?, email = ?, phone = ?, course = ?, year_of_study = ? WHERE id = ?";
                try (PreparedStatement pstm = conn.prepareStatement(query)) {
                    // Set query parameters
                    pstm.setString(1, name);
                    pstm.setString(2, email);
                    pstm.setString(3, phone);
                    pstm.setString(4, course);
                    pstm.setInt(5, yos);
                    pstm.setInt(6, studentId);

                    // Execute update query
                    int rowsAffected = pstm.executeUpdate();

                    // Check if update was successful
                    if (rowsAffected > 0) {
                        out.print("<h3 style='color:green'> Student updated successfully </h3>");
                        RequestDispatcher rd = req.getRequestDispatcher("viewStudent");
                        rd.include(req, resp);
                    } else {
                        out.print("<h3 style='color:red'> Error: Student ID not found </h3>");
                        RequestDispatcher rd = req.getRequestDispatcher("/dashboard.jsp");
                        rd.include(req, resp);
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            out.print("<h3 style='color:red'> Database driver not found </h3>");
            e.printStackTrace();
        } catch (SQLException e) {
            out.print("<h3 style='color:red'> Database error occurred: " + e.getMessage() + "</h3>");
            e.printStackTrace();
        }
    }
}
