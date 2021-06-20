var boolean=true;
function formValidation()
{
	boolean=true;
var email = document.registration.email;
var nome = document.registration.nome;
var cognome = document.registration.cognome;
var pass = document.registration.password;
var passrep= document.getElementById("psw-repeat");
var telef= document.registration.telefono;
{
if(passid_validation(pass,4,12))
{
if(allLetter(nome))
{
if(allLetterCognome(cognome))
{
if(ValidateEmail(email))
{
	if(ValidatePassRep(passrep, pass)){
		if(ValidateTelephone(telef)){
	if(boolean===false){
		
		return false;
	}
	document.getElementById("registration").submit();
} }}
}
}
}
}

}
function passid_validation(pass,mx,my)
{
var pass_len = pass.value.length;
if (pass_len == 0 ||pass_len >= my || pass_len < mx)
{
	 document.getElementById("passError").textContent="Password deve essere non vuota e tra "+mx+" e "+my+" caratteri";
	pass.classList.add("errorForm");
	 pass.focus();

boolean= false;
}else{
document.getElementById("passError").textContent="";
pass.classList.remove("errorForm");}
return true;
}
function ValidatePassRep(passrep, pass) {
	var upass = pass.value;
	var upassrep = passrep.value;
	if (upass === upassrep) {
		document.getElementById("passrepError").textContent = "";
		passrep.classList.remove("errorForm");
	} else {
		document.getElementById("passrepError").textContent = "Password non identiche, ricontrolla";
		passrep.classList.add("errorForm");
		boolean = false;
	}
	return true;
}

function allLetter(uname)
{ 
var letters = /^[A-Za-z]+$/;
if(uname.value.match(letters))
{
	document.getElementById("nomeError").textContent="";
	uname.classList.remove("errorForm");
return true;
}
else
{
	 document.getElementById("nomeError").textContent="Il nome deve avere solo lettere";
	 uname.classList.add("errorForm");
uname.focus();
boolean= false;
return true;
}
}
function allLetterCognome(uname)
{ 
var letters = /^[A-Za-z]+$/;
if(uname.value.match(letters))
{
	 document.getElementById("cognomeError").textContent="";
	uname.classList.remove("errorForm");
return true;

}
else
{
	 document.getElementById("cognomeError").textContent="Il cognome deve avere solo lettere";
	 uname.classList.add("errorForm");
uname.focus();
boolean= false;
return true;
}
}
function alphanumeric(uadd)
{ 
var letters = /^[0-9a-zA-Z]+$/;
if(uadd.value.match(letters))
{
return true;
}
else
{
alert('User address must have alphanumeric characters only');
uadd.focus();
boolean= false;
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
function ValidateTelephone(telef){
	var utelef= telef.value;
	var teleformat=/\+[0-9]{8}\d*|\d{8}\d*/gm;
	if(utelef.match(teleformat)){
		document.getElementById("telefError").textContent="";
		telef.classList.remove("errorForm");
		return true;
		
	}else{
		 document.getElementById("telefError").textContent="Formato telefono errato";
		 telef.classList.add("errorForm");
		 boolean= false;
		 return true;
	}
}