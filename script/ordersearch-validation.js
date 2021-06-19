var boolean=true;
var bool2=true;
function formEValidation()
{
	boolean=true;
    var email = document.registration.email;
{   
if(ValidateEmail(email))
{
	if(boolean===false){
		
		return false;
	}
	document.getElementById("searchEmail").submit();
} 
}
}
/*
function formDValidation()
{
    var datai = document.ordersearch.email;
    var dataf = document.registration.email;

if(ValidateDate(datai,dataf))
{
	if(bool2===false){
		
		return false;
	}
	document.getElementById("searchDate").submit();
} }

function ValidateDate(datai,dataf)
{
if(dates.compare (datai, dataf)>=0)
{
	 document.getElementById("dataError").textContent="";
	uemail.classList.remove("errorForm");
	return true;
}
else
{
	 document.getElementById("dataError").textContent="Inserisci un periodo valido";
	 uemail.classList.add("errorForm");
	 boolean= false;
	 return true;
}
} 
*/
function ValidateEmail(uemail)
{
var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
if(uemail.value.match(mailformat))
{
	 document.getElementById("emailError").textContent="";
	uemail.classList.remove("errorForm");
	return true;
}
else
{
	 document.getElementById("emailError").textContent="Inserisci una email valida";
	 uemail.classList.add("errorForm");
	 boolean= false;
	 return true;
}
} 
