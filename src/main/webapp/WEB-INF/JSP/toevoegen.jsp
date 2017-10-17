<%@ page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix='c'%>
<%@ taglib uri="http://vdab.be/tags" prefix='v' %>
<!DOCTYPE html>
<html lang='nl'>
<head>
  <v:head title="Artikel toevoegen"/>
</head>
<body>
  <v:menu/>
  <h1>Artikel toevoegen</h1>
  <form method='post' id='toevoegform'>
    <label>Naam:<span>${fouten.naam}</span>
      <input name='naam' value='${param.naam}' required autofocus></label>
    <label>Aankoopprijs:<span>${fouten.aankoopprijs}</span>
      <input name='aankoopprijs' value='${param.aankoopprijs}' type='number'
      min='0' step='0.01' required></label>
    <label>Verkoopprijs:<span>${fouten.verkoopprijs}</span>
      <input name='verkoopprijs' value='${param.verkoopprijs}' type='number'
      min='0' step='0.01' required></label>
    <div><span>${fouten.soort}</span><label>
    <input type='radio' name='soort' id='food' 
    value='Food' ${param.soort == "Food" ? "checked" : ""}>Food</label></div>
    <label>Houdbaarheid<span>${fouten.houdbaarheid}</span>
      <input name='houdbaarheid' id='houdbaarheid' type='number' min='1'
      value='${param.houdbaarheid}'></label>
    <div><label><input type='radio' name='soort' id='nonfood'  
    value='Non-Food' ${param.soort == "Non-Food" ? "checked" : ""}>Non-Food</label></div>
    <label>Garantie:<span>${fouten.garantie}</span>
      <input name='garantie' id='garantie' type='number' min='0'
      value='${param.garantie}'></label>
    <input type='submit' value='Toevoegen' id='toevoegknop'>
  </form>
  <script>
    document.getElementById('toevoegform').onsubmit = function() {
    	document.getElementById('toevoegknop').disabled = true;
    };
    document.getElementById('food').onclick =
    	enableDisableInputs;
    	document.getElementById('nonfood').onclick =
    	enableDisableInputs;
    	enableDisableInputs();
    	function enableDisableInputs() {
    	document.getElementById('houdbaarheid').disabled =
    	! document.getElementById('food').checked;
    	document.getElementById('garantie').disabled =
    	! document.getElementById('nonfood').checked;
    	};
  </script>
</body>
</html>