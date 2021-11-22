<%@ page import="calender.calender.dto.ArticleResponse" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>articleDetails</title>
    <link href="${pageContext.request.contextPath}/css/articleDetails.css" rel="stylesheet"
          type="text/css">
</head>
<body>

<%
    ArticleResponse details = (ArticleResponse) request.getAttribute(
            "details");
%>
</body>
</html>
