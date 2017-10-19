<%@ page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix='c'%>
<%@taglib uri="http://vdab.be/tags" prefix='v'%>
<!DOCTYPE html>
<html lang='nl'>
<head>
  <v:head title="Artikellijst"/>
</head>
<body>
  <v:menu/>
  <h1>Artikellijst</h1>
  <ul>
    <c:forEach items='${artikels}' var='artikel'>
      <li>${artikel.naam} (${artikel.artikelgroep.naam})</li>
    </c:forEach>
  </ul>
</body>
</html>