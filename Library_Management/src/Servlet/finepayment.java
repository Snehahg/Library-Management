package Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import connection.ConnectionClass;


@WebServlet(name="finepayment",urlPatterns={"jsp/finepayment"})

public class finepayment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public finepayment() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String loanid=request.getParameter("loanid");
		String amount=request.getParameter("amount");
		
		HttpSession session=request.getSession();  

		ConnectionClass obj = new  ConnectionClass();
		Connection con = null;
		try {
			con = obj.getConnection();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			PreparedStatement ps=con.prepareStatement("Select * from fines where Loan_id="+loanid);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
            {
				float amount1=rs.getFloat(2);
				float amount2=(amount1-Float.parseFloat(amount));
				boolean paid=false;
				if(amount2==0.0) {
					
					paid=true;
				}
				
				
				PreparedStatement ps3=con.prepareStatement("update fines set Paid="+paid+" where Loan_id="+loanid+";");
				ps3.executeUpdate();
				
				

            }
			 response.setContentType("text/html;charset=UTF-8");
			 response.getWriter().write("Payment Successfull!");
		}catch(Exception e) {
			 response.setContentType("text/html;charset=UTF-8");
			 response.getWriter().write("Payment failed due to some error!");
			e.printStackTrace();
		}
		
        response.sendRedirect("jsp/index.jsp");

	}


}
