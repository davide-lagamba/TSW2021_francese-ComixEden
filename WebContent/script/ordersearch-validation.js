var boolean=true;
var bool2=true;
function formEValidation()
{
	boolean=true;
    var email = document.searchEmail.email;
    
if(ValidateEmail(email))
{
	if(boolean===false){
		
		return false;
	}
	document.getElementById("searchEmail").submit();
} 
}

function formDValidation()
{	bool2=true;
    var datai = document.searchDate.inizio;
    var dataf = document.searchDate.fine;

if(ValidateDate(datai,dataf))
{
	if(bool2===false){
		
		return false;
	}
	document.getElementById("searchDate").submit();
} }

function ValidateDate(datai,dataf)
{
	 
	var dataival= datai.value;
	var datafval= dataf.value;
	var Date1= new Date(dataival);
	var Date2= new Date(datafval);
if((Date1 <= Date2))
{
	 document.getElementById("dataError").textContent="";
	datai.classList.remove("errorForm");
	dataf.classList.remove("errorForm");
	return true;
}
else
{
	 document.getElementById("dataError").textContent="Inserisci un periodo valido";
	datai.classList.add("errorForm");
	dataf.classList.add("errorForm");
	 bool2= false;
	 return true;
}
} 
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
