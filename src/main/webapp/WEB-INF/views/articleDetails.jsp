<%@ page import="calender.calender.dto.ArticleDetailsResponse" %>
<%@ page import="calender.calender.domain.Comment" %>
<%@ page import="java.util.List" %>
<%@ page import="calender.calender.dto.CommentResponse" %>
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
    ArticleDetailsResponse details = (ArticleDetailsResponse) request.getAttribute(
            "details");
    List<CommentResponse> comments = details.getCommentResponses();
%>
<script>
  function check() {
    if (document.getElementById("comment").value == "") {
      alert("댓글을 입력하세요");
      return;
    }
    form.submit();

  }
</script>
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
        <div class="createdDate"><%=details.getArticleCreatedDate()%>
        </div>
        <div class="commentContainer">
            <form action="/comment/write" method="post" name="form" class="writeComment">
                <input type="text" class="commentInput" id="comment" name="content"
                       placeholder="댓글을 입력해주세요">
                <input class="dummy" type="text" name="articleId" value=<%=details.getArticleId()%>>
                <input class="commentInputSubmit" type="button" value="댓글 작성" onclick="check()">
            </form>
            <%
                for (CommentResponse comment : comments) {
                    out.print("<div class=\"comment\">\n"
                            + "<div class=\"commentContent\">" + comment.getContent() + "</div>\n"
                            + "<div class=\"commentWriter\">작성자: " + comment.getWriter() + "</div>\n"
                            + "<div class=\"commentCreatedDate\">작성날짜: " + comment.getCreatedDate() + "</div>\n"
                            + "</div>");
                }
            %>
        </div>
    </div>
</div>
</body>
</html>
