<%@page import="java.util.ArrayList"%>
<%@page import="value.Searchfields"%>
<%@page import="javax.swing.text.Document"%>
<%@page import="value.CheckinValue"%>
<%@page import="value.Searchfields"%>
<%@page import="value.Fine"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="../js/library.js"></script>
</head>
<body>
<div class="main">

  <h1>Library Management</h1>
</div>

<div class="inner">

 
  
  <div class="first">
  <h3>Book Search</h3>
   <!-- Search -->

<div id="Search1" class="content">
<br/><br/>
<form method="GET" action="http://localhost:8080/Library_Management/SearchServlet" onsubmit="return validatingsearch();">
  <p><input type="text" style="width: 250px; height: 40px;" id="searchfn" name="searchfn"/></p>
  <p><button type="submit" class="btn btn-primary" >Search</button>  </p>
  
 </form>
  <br/><br/>
  </div>
 
  <div id="search2">
  <br/><br/>
   <% if((String)session.getAttribute("searchval") =="present") {
  
  		session.removeAttribute("searchval");
  %>
  <p>Search Results</p>            
  <table class="table table-striped">
    <thead>
      <tr>
        <th>Book ISBN</th>
        <th>Book Title</th>
        <th>Book Author</th>
        <th>Availability</th>
      </tr>
    </thead>
    <tbody>
    
    <%
	Searchfields searchvalue = (Searchfields)session.getAttribute("searchvalues");
    ArrayList<String> isbn = new ArrayList<String>();
	ArrayList<String> name = new ArrayList<String>();

	ArrayList<String> title = new ArrayList<String>();
	ArrayList<String> available = new ArrayList<String>();
	
	isbn=searchvalue.getIsbn();
	name=searchvalue.getName();
	title=searchvalue.getTitle();
	available=searchvalue.getAvailable();
	System.out.println("isbn"+isbn);
	System.out.println("name"+name);

	
	for(int i=0;i<name.size();i++){
    %>
      <tr>
       <td><%= isbn.get(i)%></td>
       
       <td><%= title.get(i)%></td>

       <td><%= name.get(i)%></td> 

       <td><%= available.get(i)%></td>
       
       <% if(available.get(i).equals("yes")){ %>
       
       <td> <input type="button" name="checkout" id="<%= isbn.get(i)%>"  onclick="checkoutbook(this.id);" value="Checkout"></td>
     
       <%}else if(available.get(i).equals("no")) {%>
 		
 	   <td></td>
       	
       <%}%>
      </tr>
    <%} %>  
    </tbody>
  </table>
  <% }
   
   else if((String)session.getAttribute("searchval") =="empty"){
 		session.removeAttribute("searchval");

   %>
   
   No Results found for this criteria!
   
   <%} %>
</div>
<!-- add div -->
<h3>Add a borrower</h3>

<div id="Add" class="content" style="text-align: left;">


  <br/>
 
    
<form method="GET" action="http://localhost:8080/Library_Management/addborrower"  margin-left:" 450px;" onsubmit="return validatingadd();">
  <table>
  <tr>
  
<td>    <label for="firstname" >First Name:</label></td>
<td>
    <input type="text" id="fname" name="fname" >
  </td>
  </tr>
  <tr>
<td>    <label for="lastname">Last Name:</label></td>
 <td>   <input type="text" id="lname" name="lname" >
  </td>
  </tr>
    <tr>
<td>    <label for="address" >Address:</label></td>
 <td>   <input type="text" id="addr" name="addr" >
  </td>
  </tr>
  <td>    <label for="city">city:</label></td>
 <td>   <input type="text" id="city" name="city">
  </td>
  </tr>
  <td>    <label for="state">state:</label></td>
 <td>   <input type="text" id="state" name="state">
  </td>
  </tr>
  
  <tr>
<td>    <label for="ssn">SSN:</label></td>
  <td>  <input type="text"  id="ssn" name="ssn">
  </td>
  </tr>
  <tr>
<td>    <label for="phone">Phone Number:</label></td>
   <td> <input type="text"  id="phone" name="phone">
  </td>
  </tr>
  <tr>
 <td> <button type="submit" class="btn btn-default" style="align: center">Submit</button></td>
  </tr>
  </table>
</form>
</div>
<div id="insdiv">
 <%
System.out.println("flag: "+ (String)session.getAttribute("flag"));


if((String)session.getAttribute("flag") =="success") {
%>

<br/><br/>
Borrower added successfully! <br/>
Card id: <%=(String)session.getAttribute("cardid")%>

<%
session.removeAttribute("flag");
}else if((String)session.getAttribute("flag") =="fail"){ %>
Borrower was not added ,kindly check if you are already registered in the system!
<%
session.removeAttribute("flag");
} %> 
</div>

<!-- Checkin-->

<div id="Check-in" class="content" style="text-align: left;">
<br/><br/>
<form method="GET" action="http://localhost:8080/Library_Management/Checkin" onsubmit="return validatingcheckin();">
  <p><input type="text" style="width: 250px; height: 40px;" id="sfcheckin" name="sfcheckin"/></p>
  <p><button type="submit" class="btn btn-primary" >Search book for check in</button>  </p>
  
 </form>
  <br/><br/>
  </div>

 <div id="stcheckin">
  <br/><br/>
   <% if((String)session.getAttribute("valcheckin") =="present") {
  
  		session.removeAttribute("valcheckin");
  %>
  <p>Search Results</p>            
  <table >
    <thead>
      <tr>
        <th>Book ISBN</th>
        <th>Borrower First Name</th>
        <th>Borrower Last Name</th>
        <th>Card ID</th>
      </tr>
    </thead>
    <tbody>
    
    <%
	CheckinValue search = (CheckinValue)session.getAttribute("checkinvalues");
    ArrayList<String> isbn = new ArrayList<String>();
	ArrayList<String> fname = new ArrayList<String>();
	ArrayList<String> lname = new ArrayList<String>();
	ArrayList<String> checkout = new ArrayList<String>();

	
	isbn=search.getIsbn();
	fname=search.getFName();
	lname=search.getLName();
	checkout=search.getCheckoutBy();
	System.out.println(isbn);
	System.out.println(fname);
	System.out.println(lname);
	System.out.println(checkout);
	
	
	for(int i=0;i<isbn.size();i++){
    %>
      <tr>
       <td><%= isbn.get(i)%></td>
       
       <td><%= fname.get(i)%></td>
       
       <td><%= lname.get(i)%></td>
       
       <td><%= checkout.get(i)%></td>
     
       <td>  <button onclick="bookcheckin(this.id)" id="<%=isbn.get(i)%>+<%=checkout.get(i)%>">Check In</button>  </td>
      </tr>
    <%} %>  
    </tbody>
  </table>
  <% }
   
   else if((String)session.getAttribute("valcheckin") =="empty"){
 		session.removeAttribute("valcheckin");

   %>
   
   No Results found for this criteria!
   
   <%} %>
</div>
</div>
</div>
<!-- fines div -->

<div id="Fines" class="content">
<br/><br/>
 
 <form method="GET" action="http://localhost:8080/Library_Management/user" >
  <p><button type="submit" class="btn btn-primary" >Show fines for each user</button>  </p>
  
 </form>
 
 <form method="GET" action="http://localhost:8080/Library_Management/refreshfine" >
  <p><button type="submit" class="btn btn-primary" >Refresh fines</button>  </p>
  
 </form> 
 
 <form method="GET" action="http://localhost:8080/Library_Management/computepayment" >
  <p><button type="submit" class="btn btn-primary" >Check for book returns</button>  </p>
  
 </form> 
  
  <br/><br/>
  </div>
   <div id="userfinetable">
  
  <%if(((String)session.getAttribute("userfine")) == "present"){
	  session.removeAttribute("userfine"); 
	  %>
  <p>Search Results</p>            
  <table >
    <thead>
      <tr>
     
        <th>Borrower Name</th>
        <th>Card ID</th>
        <th>Fine Amount till Date</th>
   
      </tr>
    </thead>
    <tbody>
    
     <%
     Fine fine = (Fine)session.getAttribute("user");

     ArrayList<String> BName = new ArrayList<String>();
     ArrayList<Float> FineAmt = new ArrayList<Float>();
     ArrayList<String> cardid = new ArrayList<String>();


   
     BName=fine.getBorrower();
     FineAmt=fine.getFineamt();
     cardid=fine.getCardid();
     for(int i=0;i<FineAmt.size();i++){
    %>
    <tr>

    <td><%=BName.get(i) %></td>
    <td><%=cardid.get(i) %></td>
    <td>$<%=FineAmt.get(i) %></td>
    </tr>
    
    <%} %>
    </tbody>
    </table>
  <%}else if(((String)session.getAttribute("userfine")) == "empty"){ session.removeAttribute("userfine");%>
  
  No Data to Display!
  
  <%} %>
    </div>  
    
    <div id="payfinetable">
   
  
  <%if(((String)session.getAttribute("payfine")) == "present"){
	 
	  session.removeAttribute("payfine"); %>
    <%
     Fine fine = (Fine)session.getAttribute("payfineresult");
    
     ArrayList<Integer> LoanID = new ArrayList<Integer>();
    
     ArrayList<Float> FineAmt = new ArrayList<Float>();
     ArrayList<Boolean> Paid = new ArrayList<Boolean>();
     ArrayList<String> datein = new ArrayList<String>();
     ArrayList<String> cardid = new ArrayList<String>();

     LoanID= fine.getLoanID();
     FineAmt=fine.getFineamt();
     Paid=fine.getPaid();
     datein=fine.getDatein();
     cardid=fine.getCardid();
     for(int i=0;i<Paid.size();i++){
    System.out.println("in jsp loan.get(i): "+ LoanID.get(i));
    System.out.println("in jsp card.get(i): "+ cardid.get(i));
    System.out.println("in jsp datein.get(i): "+ datein.get(i));
    System.out.println("in jsp Paid.get(i): "+ Paid.get(i));
    if(Paid.get(i)==false && datein.get(i)!=null){ %>
    <br/>
    <th>Book returned loan id:</th>
    <td><%=LoanID.get(i) %></td>
    <br/>
    <td><button onclick="pay('<%=LoanID.get(i) %>','<%=FineAmt.get(i) %>');">Pay Fine</button></td>
    <%}else {  %>
     <tr>
     <th>Book not yet returned or payment already done! loan id:</th>
     <td><%=LoanID.get(i)   %></td>
     <br/>
     </tr>
    <%}} %>
    </tbody>
    </table>
  <%}else if((((String)session.getAttribute("payfine") == "empty") )  ){ 
  session.removeAttribute("payfine");%>
  
  No Data to Display!
  
  <%}  %>
    </div>

 <!-- fine contd -->
  
  
  
  <%if(((String)session.getAttribute("refresh")) == "present"){
	  System.out.println("present");
	  session.removeAttribute("refresh"); %>
  <div id="searchfinetable" > 
  
           
Fine Refresh Successful! 
   
  </div>
  <%} else if (((String)session.getAttribute("refresh")) == "empty"){
  session.removeAttribute("refresh");%>
Fine Refresh encountered a problem!
 
<%} %>
  

</body>
</html>
    

</body>
</html>