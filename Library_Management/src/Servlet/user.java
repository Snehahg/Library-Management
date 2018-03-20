package Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import value.Fine;
import connection.ConnectionClass;

/**
 * Servlet implementation class ShowUserServlet
 */
@WebServlet(name="user",urlPatterns={"jsp/user"})

public class user extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public user() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
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
		ArrayList<String> isbn = new ArrayList<String>();
		ArrayList<Float> amount = new ArrayList<Float>();
		ArrayList<String> paid = new ArrayList<String>();
		ArrayList<String> borrower = new ArrayList<String>();
		ArrayList<String> loanid = new ArrayList<String>();
		ArrayList<String> datein = new ArrayList<String>();
		ArrayList<String> cardid = new ArrayList<String>();


		try {
			PreparedStatement ps=con.prepareStatement("select SUM(f.fine_amt), b.first_name,b.last_name, b.Card_id from fines f, borrower b, book_loans bl where bl.card_id=b.card_id and f.Loan_id=bl.loan_id and  f.paid=0  group by Card_id");
			ResultSet rs=ps.executeQuery();
			while(rs.next())
            {
				
				amount.add(rs.getFloat(1));
				
				borrower.add(rs.getString(2)+rs.getString(3));
			
				cardid.add(rs.getString(4));
            }
			
			if(isbn!=null && amount!=null) {
				fine.setFineamt(amount);
				fine.setBorrower(borrower);
				fine.setCardid(cardid);
				}
			
			if(null!=fine && !cardid.isEmpty()) {
				session.setAttribute("user", fine);
				session.setAttribute("userfine", "present");

			}else{
				session.setAttribute("userfine", "empty");
			}
			
	        response.sendRedirect("jsp/index.jsp");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}