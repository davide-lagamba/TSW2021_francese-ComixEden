
function ValidateJqueryEmail(uemail)
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
	 
	 return false;
}
}
$(document).ready(function(){
	$("#email").change(function(){
		if(ValidateJqueryEmail(document.registration.email)){
		$.get("email?email="+$("#email").val(), function(data, status){
			
			if(data==("true")){
				
				$("#emailError").html("&#10003");
				$("#email").css("border-color", "green");
			}else{
				$("#emailError").html("Email già in uso");
				$("#email").css("border-color", "red");
			}
		});
		}else{
			$("#emailError").html("Email non valida");
			$("#email").css("border-color", "red");
		}
	})
	
});