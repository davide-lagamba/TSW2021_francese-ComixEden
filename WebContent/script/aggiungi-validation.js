var boolean=true;
function formValidation()
{
	boolean=true;
var prezzo = document.aggiungi.prezzo_base;
var peso = document.aggiungi.peso;
var disp = document.aggiungi.disponibilita;
var pagine = document.aggiungi.pagine;
var sconto=document.aggiungi.sconto;
var iva=document.aggiungi.iva;
{
if(prezzo_validation(prezzo))
{
if(peso_validation(peso))
{
if(disp_validation(disp))
{
if(pagine_validation(pagine))
{
if(sconto_validation(sconto)){
if(iva_validation(iva)){
	if(boolean==false){
		
		return false;
	}
	document.getElementById("aggiungi").submit();
}}}
}
}
}
}

}
function prezzo_validation(prezzo)
{
if(prezzo.value>0)
{
	document.getElementsByClassName("prezzoError")[0].textContent = "";
	prezzo.classList.remove("errorForm");
	return true;}{
boolean= false;

document.getElementsByClassName("prezzoError")[0].textContent="Prezzo > 0";
prezzo.classList.add("errorForm");
prezzo.focus();
return true;
}}

function peso_validation(peso)
{
if(peso.value>0)
{
	document.getElementsByClassName("pesoError")[0].textContent = "";
	peso.classList.remove("errorForm");
	return true;}{
boolean= false;

document.getElementsByClassName("pesoError")[0].textContent="Peso > 0";
peso.classList.add("errorForm");
peso.focus();
return true;
}}

function disp_validation(disp)
{
if(disp.value>0)
{
	document.getElementsByClassName("disponibilitaError")[0].textContent = "";
	disp.classList.remove("errorForm");
	return true;}{
boolean= false;

document.getElementsByClassName("disponibilitaError")[0].textContent="Disponibilità > 0";
disp.classList.add("errorForm");
disp.focus();
return true;
}}

function pagine_validation(pagine)
{
if(pagine.value>0)
{
	document.getElementsByClassName("pagineError")[0].textContent = "";
	pagine.classList.remove("errorForm");
	return true;}{
boolean= false;

document.getElementsByClassName("pagineError")[0].textContent="Pagine > 0";
pagine.classList.add("errorForm");
pagine.focus();
return true;
}}
function sconto_validation(sconto)
{
if(sconto.value>=0)
{
	document.getElementsByClassName("scontoError")[0].textContent = "";
	sconto.classList.remove("errorForm");
	return true;}{
boolean= false;

document.getElementsByClassName("scontoError")[0].textContent="Sconto >= 0";
sconto.classList.add("errorForm");
sconto.focus();
return true;
}}
function iva_validation(iva)
{
if(iva.value>0)
{
	document.getElementsByClassName("ivaError")[0].textContent = "";
	iva.classList.remove("errorForm");
	return true;}{
boolean= false;

document.getElementsByClassName("ivaError")[0].textContent="Iva > 0";
iva.classList.add("errorForm");
iva.focus();
return true;
}}
