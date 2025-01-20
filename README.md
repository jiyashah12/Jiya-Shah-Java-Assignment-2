# Online Student Management System

## Overview
The Online Student Management System is a web-based application that allows administrators to manage student records effectively. The system includes features like adding, viewing, updating, and deleting student details, along with secure authentication and user session management.


## Features
- Login Authentication: Secure user login with session management.
- Add Student: Input student details like name, email, phone, course, and year of study.
- View Students: Display all student records in a table format.
- Update Student: Modify existing student records.
- Delete Student: Remove student records permanently.
- Logout: Securely end the user session.


## Technologies Used
- Frontend:
  - HTML5
  - CSS3
  - JSP (Java Server Pages)
- Backend:
  - Java Servlets
  - PostgreSQL Database
- Server: Apache Tomcat
- Build Tool: Maven


## Project Structure

![image](https://github.com/user-attachments/assets/b7c7960e-ff30-4061-95d3-c483825adc69)


## Installation Steps

1. Clone the Repository:
   - Clone the repository using the following command:
     git clone <repository_link>
     
   - Navigate to the cloned directory:
     cd <repository>
     

2. Create the Database in PostgreSQL:
   - Open your PostgreSQL client (e.g., a graphical tool like pgAdmin).
   - Create a database named StudentManagement:
     CREATE DATABASE StudentManagement;
     

3. Create Tables in the Database:
   - Switch to the StudentManagement database:
          
   - Create the 'admin' table:
     CREATE TABLE admin (
    	id SERIAL PRIMARY KEY,
    	username VARCHAR(50) NOT NULL,
    	password VARCHAR(255) NOT NULL
     );

   - Create the 'students' table:
     CREATE TABLE students (
    	id SERIAL PRIMARY KEY,
    	name VARCHAR(100) NOT NULL,
    	email VARCHAR(100) NOT NULL,
    	phone VARCHAR(15) NOT NULL,
    	course VARCHAR(50) NOT NULL,
    	year_of_study INTEGER NOT NULL
     );
     

4. Install JDBC Driver for PostgreSQL:
   - Download the PostgreSQL JDBC driver jar file from the official PostgreSQL website or Maven repository.
   - Add the downloaded jar file to your project's classpath.

5. Install Apache Tomcat:
   - Download Apache Tomcat from its [official website](https://tomcat.apache.org/).
   - Extract the downloaded archive and set up Tomcat on your system.
   - Configure your project to deploy on the Tomcat server.

6. Import Servlet API and PostgreSQL Jar into Project:

7. Set PostgreSQL Username and Password in the Project:

8. Run the Server:

9. Login to the Application:

You have successfully set up the project! You can now interact with the application for managing students and administrators.

