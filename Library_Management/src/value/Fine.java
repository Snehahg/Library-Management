package value;

import java.util.ArrayList;
import java.util.Date;

public class Fine {
	
	public ArrayList<Integer> getLoanID() {
		return loanID;
	}
	public void setLoanID(ArrayList<Integer> loanID) {
		this.loanID = loanID;
	}
	public ArrayList<Boolean> getPaid() {
		return paid;
	}
	public void setPaid(ArrayList<Boolean> paid) {
		this.paid = paid;
	}
	public ArrayList<Float> getFineamt() {
		return fineamt;
	}
	public void setFineamt(ArrayList<Float> fineamt) {
		this.fineamt = fineamt;
	}
	public ArrayList<String> getIsbn() {
		return isbn;
	}
	public void setIsbn(ArrayList<String> isbn) {
		this.isbn = isbn;
	}
	public ArrayList<String> getBorrower() {
		return borrower;
	}
	public void setBorrower(ArrayList<String> borrower) {
		this.borrower = borrower;
	}
	public ArrayList<Integer> loanID = new ArrayList<Integer>();
	public ArrayList<Boolean> paid = new ArrayList<Boolean>();
	public ArrayList<Float> fineamt = new ArrayList<Float>();
	public ArrayList<String> isbn = new ArrayList<String>();
	public ArrayList<String> borrower = new ArrayList<String>();
	public ArrayList<String> datein = new ArrayList<String>();
	public ArrayList<String> cardid = new ArrayList<String>();

	public ArrayList<String> getCardid() {
		return cardid;
	}
	public void setCardid(ArrayList<String> cardid) {
		this.cardid = cardid;
	}
	public ArrayList<String> getDatein() {
		return datein;
	}
	public void setDatein(ArrayList<String> datein) {
		this.datein = datein;
	}
	
	

	
}
