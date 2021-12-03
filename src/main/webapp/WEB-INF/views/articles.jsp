<%@ page import="calender.calender.dto.ArticleResponse" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Articles</title>
    <link href="${pageContext.request.contextPath}/css/articles.css" rel="stylesheet"
          type="text/css">
</head>
<body>
<%
    String year = request.getParameter("year");
    String month = request.getParameter("month");
    String day = request.getParameter("day");
%>
<div class="outside">
    <div class="container">
        <div class="list">글목록</div>
        <sec:authorize access="isAuthenticated()">
            <div class="write"
                 onclick="location.href='/articles/write?year=' + <%=year%> + '&month=' + <%=month%> + '&day=' + <%=day%>">
                글쓰기
            </div>
        </sec:authorize>
        <div class="cancel" onclick="location='/'">x</div>
        <%
            List<ArticleResponse> articles = (List<ArticleResponse>) request.getAttribute(
                    "articles");
            for (ArticleResponse article : articles) {
                out.print("<div class=\"article\" onclick=\"location='/articles/"
                        + article.getArticleId() + "'\" >\n"
                        + "            <div class=\"writer\">\n"
                        + "                작성자: " + article.getWriter() + "\n"
                        + "            </div>\n"
                        + "            <div class=\"title\">\n"
                        + "                " + article.getTitle() + "\n"
                        + "            </div>\n"
                        + "            <div class=\"subInfo\">\n"
                        + "                <div class=\"createdDate\">작성날짜: "
                        + (article.getCreatedDate().getYear() + 1900) + "년 "
                        + (article.getCreatedDate().getMonth() + 1) + "월 "
                        + (article.getCreatedDate().getDay() - 2) + "일 "
                        + "                </div>\n"
                        + "                <div class=\"comment\">\n"
                        + "                    댓글 수: " + article.getCommentCount()+ "\n"
                        + "                </div>\n"
                        + "            </div>\n"
                        + "        </div>");
            }
        %>
    </div>
</div>
</body>
</html>
