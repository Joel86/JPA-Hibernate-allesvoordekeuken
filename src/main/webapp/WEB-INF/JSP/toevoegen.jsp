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
    <input type='submit' value='Toevoegen' id='toevoegknop'>
  </form>
  <script>
    document.getElementById('toevoegform').onsubmit = function() {
    	document.getElementById('toevoegknop').disabled = true;
    };
  </script>
</body>
</html>