<%@ page import="calender.calender.dto.ArticleResponse" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<% pageContext.setAttribute("replaceChar", "\n"); %>
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
<div class="outside">
    <div class="container">
        <div class="cancel" onclick="history.back()">x</div>
        <div class="title"><%=details.getTitle()%>
        </div>
        <div class="content">
            ${fn:replace(details.getContent(), replaceChar , "<br/>")}
        </div>
        <div class="writer">작성자: <%=details.getWriter()%>
        </div>
        <div class="createdDate"><%=details.getCreatedDate()%>
        </div>
    </div>
</div>
</body>
</html>
