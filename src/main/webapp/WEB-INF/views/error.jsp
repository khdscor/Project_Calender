<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String message = request.getAttribute("message").toString();
%>
<script>
  alert("<%=message%>")
  location.href = "/";
</script>
</body>
</html>
