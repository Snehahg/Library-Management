function validatingsearch(){
	if(document.getElementById('searchfn').value == "" || document.getElementById('searchfn').value == null){
		alert("Please enter a search criteria!");
		return false;
	}
	return true;
}

function validatingadd(){
	if(document.getElementById('fname').value == "" || document.getElementById('fname').value == null){
		alert("Please enter First Name!");
		return false;
	}
	if(document.getElementById('lname').value == "" || document.getElementById('lname').value == null){
		alert("Please enter Last Name!");
		return false;
	}
	if(document.getElementById('addr').value == "" || document.getElementById('addr').value == null){
		alert("Please enter address!");
		return false;
	}
	if(document.getElementById('city').value == "" || document.getElementById('city').value == null){
		alert("Please enter city!");
		return false;
	}
	if(document.getElementById('state').value == "" || document.getElementById('state').value == null){
		alert("Please enter state!");
		return false;
	}
	
	
	if( document.getElementById('ssn').value == "" || document.getElementById('ssn').value == null){
		alert("Please enter  SSN!");
		return false;
	}
	
	if(document.getElementById('phone').value != "" &&  !regex.test(document.getElementById('phone').value)){
		alert("Please enter valid Phone number!");
		return false;
	}
	return true;
	
}

function validatingcheckin(){
	if(document.getElementById('sfcheckin').value == "" || document.getElementById('sfcheckin').value == null){
		alert("Please enter a search criteria!");
		return false;
	}
	return true;
}


function checkoutbook(isbn){
	
	var cardid = prompt("Please enter a valid card number for book check out", "");
	
	
	if(cardid){
	var xmlhttp;
    if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp = new XMLHttpRequest();
        
    } else {// code for IE6, IE5
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function() {
    	if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
           
    		
    		alert(xmlhttp.responseText);
    		location.reload();
        }
    }
    xmlhttp.open('GET', 'http://localhost:8080/Library_Management/Checkout?isbn='+isbn+'&cardid='+cardid, true);
    xmlhttp.send(null);
}

}

function bookcheckin(isbnandid){
	var xmlhttp;
	var arg=isbnandid.split('+');
    if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp = new XMLHttpRequest();
    } else {// code for IE6, IE5
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function() {
    	if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
    		alert(xmlhttp.responseText);
    		location.reload();

        }
    }
    xmlhttp.open('GET', 'http://localhost:8080/Library_Management/Checkin2?isbn='+arg[0]+'&id='+arg[1], true);
    xmlhttp.send(null);

}

function pay(loanid, amount){

	var xmlhttp;
    if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp = new XMLHttpRequest();
    } else {// code for IE6, IE5
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function() {
    	if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

    		alert("Payment Successfull!");
    		location.reload();


        }
    }
    xmlhttp.open('GET', 'http://localhost:8080/Library_Management/finepayment?loanid='+loanid+'&amount='+amount, true);
    xmlhttp.send(null);
}
	

