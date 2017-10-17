<%@ page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib uri="http://vdab.be/tags" prefix='v'%>
<!DOCTYPE html>
<html lang='nl'>
<head>
  <v:head title="Algemene prijsverhoging artikels"/>
</head>
<body>
  <v:menu/>
  <h1>Algemene prijsverhoging artikels</h1>
  <form method='post' id='prijsverhogingform'>
    <label>Percentage:<span>${fouten.percentage}</span>
      <input name='percentage' value='${param.percentage}' type='number' min='0.01'
      step='0.01' autofocus required></label>
    <input type='submit' value='Bevestigen' id='submitknop'>
  </form>
  <script>
    document.getElementById('prijsverhogingform').onsubmit = function() {
    	document.getelementById('submitknop').disabled = true;
    }
  </script>
</body>
</html>