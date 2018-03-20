package value;

import java.util.ArrayList;

public class CheckinValue {
	
	
	public ArrayList<String> isbn = new ArrayList<String>();
	public ArrayList<String> fname = new ArrayList<String>();
	public ArrayList<String> lname= new ArrayList<String>();
	public ArrayList<String> checkoutBy= new ArrayList<String>();
	
	public ArrayList<String> getIsbn() {
		return isbn;
	}
	public void setIsbn(ArrayList<String> isbn) {
		this.isbn = isbn;
	}
	
	public ArrayList<String> getFName() {
		return fname;
	}
	public void setFName(ArrayList<String> fname) {
		this.fname = fname;
	}
	public ArrayList<String> getLName() {
		return lname;
	}
	public void setLName(ArrayList<String> lname) {
		this.lname = lname;
	}
	public ArrayList<String> getCheckoutBy() {
		return checkoutBy;
	}
	public void setCheckoutBy(ArrayList<String> checkoutBy) {
		this.checkoutBy = checkoutBy;
	}
	

}

