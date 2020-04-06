
function replace1() {
	var http = false;
	if (navigator.appName == "Microsoft Internet Explorer") {
	http = new ActiveXObject("Microsoft.XMLHTTP");
	} else {
	http = new XMLHttpRequest();
	}

	http.open("GET", "/IndianEmergencyCommunicationsFinanceSystem/ViewBudgetReport", true);
http.onreadystatechange=function() {
if (http.readyState == 4) {
	var doc=document.getElementById('box4');
	doc.style.display = "block"
		var doc2=document.getElementById('2');
	doc2.style.display = "none"
		var doc3=document.getElementById('box1');
	doc3.style.display = "none"
		var doc4=document.getElementById('box2');
	doc4.style.display = "none"
		var doc5=document.getElementById('box3');
	doc5.style.display = "none"
		var doc6=document.getElementById('box5');
	doc6.style.display = "none"
		var doc7=document.getElementById('box6');
	doc7.style.display = "none"
		var doc7=document.getElementById('box7');
	doc7.style.display = "none"
		var doc2=document.getElementById('1');
	doc2.style.display = "none"
		var doc2=document.getElementById('3');
	doc2.style.display = "none"
		var doc2=document.getElementById('4');
	doc2.style.display = "none"
		var doc2=document.getElementById('6');
	doc2.style.display = "none"
	
	
document.getElementById('box4').innerHTML = http.responseText;
}

}
http.send(null);
}
