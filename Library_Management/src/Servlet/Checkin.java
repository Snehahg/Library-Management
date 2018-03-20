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

import value.CheckinValue;
import connection.ConnectionClass;


@WebServlet(name="Checkin",urlPatterns={"jsp/Checkin"})

public class Checkin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public Checkin() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        HttpSession session=request.getSession();  
		String kw= request.getParameter("sfcheckin");
		ConnectionClass obj = new  ConnectionClass();
		Connection con = null;
		try {
			con = obj.getConnection();
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}
	    CheckinValue CheckinValue = new CheckinValue();
		ArrayList<String> isbn = new ArrayList<String>();
		ArrayList<String> fname = new ArrayList<String>();
		ArrayList<String> lname = new ArrayList<String>();
		ArrayList<String> checkoutBy = new ArrayList<String>();

        try {
        	
			PreparedStatement ps=con.prepareStatement("select b.isbn, b1.card_id, b1.last_name,b1.first_name from book_loans b, borrower b1 where ( b.Card_id=b1.Card_id AND  b.Date_in IS NULL AND (b.isbn LIKE '%"+kw+"%' OR b1.card_id LIKE '%"+kw+"%' OR concat(b1.last_name,' ', b1.first_name) LIKE '%"+kw+"%'))");
			System.out.println(ps);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
            {
				isbn.add(rs.getString(1));
				checkoutBy.add(rs.getString(2));
				lname.add(rs.getString(3));
				fname.add(rs.getString(4));


            }
			if(isbn!=null & checkoutBy!=null & lname!=null & fname!=null ) {
				CheckinValue.setIsbn(isbn);
				CheckinValue.setCheckoutBy(checkoutBy);

				CheckinValue.setFName(fname);
				CheckinValue.setLName(lname);
			}
			
		if(CheckinValue!=null && !isbn.isEmpty()) {
		session.setAttribute("checkinvalues", CheckinValue);
		session.setAttribute("valcheckin", "present");
		}
		else{
		System.out.println("empty");
		session.setAttribute("valcheckin", "empty");
		}
		
		
        } catch (SQLException e) {
			
			e.printStackTrace();
		}

		
        response.sendRedirect("jsp/index.jsp");

	}

}
