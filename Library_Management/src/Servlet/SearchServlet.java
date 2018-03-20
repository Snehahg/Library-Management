package Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import connection.ConnectionClass;
import value.Searchfields;


@WebServlet(name="SearchServlet",urlPatterns={"jsp/SearchServlet"})

public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public SearchServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
        HttpSession session=request.getSession();  

		
		String sc= request.getParameter("searchfn");
		ConnectionClass obj = new  ConnectionClass();
		Connection con = null;
		try {
			con = obj.getConnection();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Map<String, String> dictba = new HashMap<>();
		
	
		Searchfields searchvalue = new Searchfields();
		
		ArrayList<String> title = new ArrayList<String>();
		ArrayList<String> available = new ArrayList<String>();
		ArrayList<String> isbn = new ArrayList<String>() ;
		ArrayList<String> auth = new ArrayList<String>() ;

        try {
			PreparedStatement ps=con.prepareStatement("select b.isbn, b.title, b.Availability, a.name from book b, book_authors ba, authors a where b.isbn=ba.isbn AND ba.author_id= a.author_id AND (b.isbn LIKE '%"+sc+"%' OR b.title LIKE '%"+sc+"%' OR a.name LIKE '%"+sc+"%') ");
			ResultSet rs=ps.executeQuery();
			System.out.println(ps);
			
			String author;
			while(rs.next())
            {
				String book =  rs.getString(1);
				if( rs.getString(4).isEmpty())
				{
					 author = " ";
				}
				else{
				 author =  (String)rs.getString(4);
				}
				if (author.contains("&")) {
					author = author.replaceAll("&amp;", ",");
				}
				if (!dictba.containsKey(book)) {
					dictba.put(book, author);
					title.add(rs.getString(2));
					available.add(rs.getString(3));
					isbn.add(rs.getString(1));
					
				}
				else {
					dictba.put(book, dictba.get(book) + ", " + author);
				}
				
			}
			
			for(int i=0;i<isbn.size();i++){
				if(dictba.containsKey(isbn.get(i))) {
					auth.add(dictba.get(isbn.get(i)));
				}
			}
			
			if(isbn!=null) {
			searchvalue.setIsbn(isbn);
			searchvalue.setTitle(title);
			searchvalue.setName(auth);
			searchvalue.setAvailable(available);
			}
			
		if(searchvalue!=null && !isbn.isEmpty()) {
		session.setAttribute("searchvalues", searchvalue);
		session.setAttribute("searchval", "present");
		}
		else{
		session.setAttribute("searchval", "empty");
		}
		
		
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        response.sendRedirect("jsp/index.jsp");
	}

	

}
