package Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import value.Fine;
import connection.ConnectionClass;


@WebServlet(name="computepayment",urlPatterns={"jsp/computepayment"})

public class computepayment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public computepayment() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("inside fine table servlet");
        HttpSession session=request.getSession();  

		ConnectionClass obj = new  ConnectionClass();
		Connection con = null;
		try {
			con = obj.getConnection();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Fine fine = new Fine();
		
		ArrayList<Float> amount = new ArrayList<Float>();
		ArrayList<Boolean> paid = new ArrayList<Boolean>();
	    ArrayList<Integer> loanid = new ArrayList<Integer>();
		ArrayList<String> datein = new ArrayList<String>();
		ArrayList<String> cardid = new ArrayList<String>();


		try {
			PreparedStatement ps=con.prepareStatement("select  f.fine_amt, f.paid,  f.Loan_id, bl.Date_in, b.Card_id from fines f, book_loans bl, borrower b where f.Loan_id=bl.loan_id AND bl.card_id=b.card_id ");
			ResultSet rs=ps.executeQuery();
			while(rs.next())
            {
				
				
				amount.add(rs.getFloat(1));
	            paid.add(rs.getBoolean(2));
				loanid.add(rs.getInt(3));
				
				datein.add(rs.getString(4));
				cardid.add(rs.getString(5));
            }
			
			if(amount!=null) {
				
				fine.setFineamt(amount);
				fine.setPaid(paid);
				
				fine.setLoanID(loanid);
				fine.setDatein(datein);
				fine.setCardid(cardid);
				}
			
			if(fine!=null ) {
				session.setAttribute("payfineresult", fine);
				session.setAttribute("payfine", "present");

			}else{
				
				session.setAttribute("payfine", "empty");
			}
			
	        response.sendRedirect("jsp/index.jsp");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

	
}
