package Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import connection.ConnectionClass;


@WebServlet(name="addborrower",urlPatterns={"jsp/addborrower"})
public class addborrower extends HttpServlet {
       private static final long serialVersionUID = 1L;
       
    
    public addborrower() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        HttpSession session=request.getSession();  

		ConnectionClass obj = new  ConnectionClass();
		Connection con = null;
		try {
			con = obj.getConnection();
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}
		 int cardno=0;
		 PreparedStatement preparedStmt2;
		 String query2="Select count(*) from borrower";
		 try {
			preparedStmt2 = con.prepareStatement(query2);
			ResultSet rs=preparedStmt2.executeQuery();
			while(rs.next()) {
				  cardno=rs.getInt(1);
				  System.out.println("cardnumber: "+ cardno);
				  cardno=cardno+1;
		} } catch (SQLException e1) {
			
			e1.printStackTrace();
		}
	            	 
		 
		 
	     String query3 = String.format("%06d", cardno);
	     System.out.println("card"+query3);
	     String query4 = "ID" + query3;
	     System.out.println("card"+query4);
				 
		
		
		
	      String query = "insert into Borrower (Ssn, first_name,last_name,Address,city,state, Phone,card_id)"
	        + " values (?,?, ?, ?, ?,?,?,?)";
	  
	      PreparedStatement preparedStmt;
	      

		try {
			  preparedStmt = con.prepareStatement(query);
		
			  preparedStmt.setString (1, request.getParameter("ssn"));
		
		      preparedStmt.setString (2, request.getParameter("fname"));
		      preparedStmt.setString (3, request.getParameter("lname"));
		      preparedStmt.setString (4, request.getParameter("addr"));
		      preparedStmt.setString (5, request.getParameter("city"));
		      preparedStmt.setString (6, request.getParameter("state"));
		      if(request.getParameter("phone")!=null && request.getParameter("phone")!="") {
		      preparedStmt.setString(7, request.getParameter("phone"));
		      }else {
			  preparedStmt.setString(7, request.getParameter(null));
			  }
		      preparedStmt.setString   (8, query4);
		      
		   
		     preparedStmt.execute();
		   
		     session.setAttribute("flag", "success");
		     session.setAttribute("cardid", query4);
		     
		      } catch (SQLException e) {
				     session.setAttribute("flag", "fail");

				
				e.printStackTrace();
			}
		
        response.sendRedirect("jsp/index.jsp");

	}

	
}