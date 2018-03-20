package Servlet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import connection.ConnectionClass;


@WebServlet(name="Checkin2",urlPatterns={"jsp/Checkin2"})

public class Checkin2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Checkin2() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String isbn=request.getParameter("isbn");
		String id=request.getParameter("id");
		ConnectionClass obj = new  ConnectionClass();
		Connection con = null;
		try {
			con = obj.getConnection();
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}
	      java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());

		String query = "Update Book_Loans set Date_in='"+sqlDate+"' where isbn='"+isbn+"' AND Card_id='"+id+"'";
		String query2="Update book set availability='yes' where isbn='"+isbn+"'";
	     try {
	    	 PreparedStatement	preparedStmt = con.prepareStatement(query);
	    	 preparedStmt.executeUpdate();
	    	 response.setContentType("text/html;charset=UTF-8");
		 response.getWriter().write("Checked in Successfully!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	     try {
	    	 PreparedStatement	preparedStmt2 = con.prepareStatement(query2);
		     preparedStmt2.executeUpdate();

		} catch (SQLException e) {
			
			e.printStackTrace();
		} 

	}
}