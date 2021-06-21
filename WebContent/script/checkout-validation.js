var boolean=true;
function formValidation()
{
	boolean=true;
var capsped = document.checkout.cap_spedizione;
var capfat = document.checkout.cap_fatturazione;
var numcar = document.checkout.numero_carta;
var scad = document.checkout.scadenza;
{
if(capS_validation(capsped))
{
if(capF_validation(capfat))
{
if(num_validation(numcar))
{
if(scad_validation(scad))
{
	
	if(boolean==false){
		
		return false;
	}
	document.getElementById("checkout").submit();
}
}
}
}
}

}
function capS_validation(cap)
{
var cap_len = cap.value.length;
var regex= /\b\d{5}\b/g;
if (cap.value.match(regex))
{
	document.getElementsByClassName("cap_spedizioneError")[0].textContent = "";
	cap.classList.remove("errorForm");
	return true;}{
boolean= false;

document.getElementsByClassName("cap_spedizioneError")[0].textContent="Cap non corretto";
cap.classList.add("errorForm");
cap.focus();
return true;
}}

function capF_validation(cap)
{
var regex= /\b\d{5}\b/g;
if (cap.value.match(regex))
{
	document.getElementsByClassName("cap_fatturazioneError")[0].textContent = "";
	cap.classList.remove("errorForm");
	return true;}{
boolean= false;

document.getElementsByClassName("cap_fatturazioneError")[0].textContent="Cap non corretto";
cap.classList.add("errorForm");
cap.focus();
return true;
}
}

function num_validation(num) {
	
	var regex= /\b\d{4}[ -]?\d{4}[ -]?\d{4}[ -]?\d{4}\b/;
	if (num.value.match(regex))
	{
		document.getElementsByClassName("numero_cartaError")[0].textContent = "";
		num.classList.remove("errorForm");
		return true;}{
	boolean= false;

	document.getElementsByClassName("numero_cartaError")[0].textContent="Numero carta non corretto";
	num.classList.add("errorForm");
	num.focus();
	return true;}
}

function scad_validation(scad) {
	
	var Data= new Date(scad.value)
	if (Data >= new Date())
	{
		document.getElementsByClassName("scadenzaError")[0].textContent = "";
		scad.classList.remove("errorForm");
		return true;}{
	boolean= false;

	document.getElementsByClassName("scadenzaError")[0].textContent="Carta scaduta";
	scad.classList.add("errorForm");
	scad.focus();
	return true;}
}

