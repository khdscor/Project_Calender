<%@ page import="calender.calender.dto.ArticleDetailsResponse" %>
<%@ page import="calender.calender.domain.Comment" %>
<%@ page import="java.util.List" %>
<%@ page import="calender.calender.dto.CommentResponse" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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

  function deletes() {
    <sec:authorize access="isAuthenticated()">
    <sec:authentication property="Principal.user.id" var="userId"/>
    if (${userId}  ==  <%=details.getWriterId()%>) {
      deleteForm.submit();
    } else {
      alert("작성자만 삭제가 가능합니다.")
    }
    </sec:authorize>
    <sec:authorize access="isAnonymous()">
    alert("작성자만 삭제가 가능합니다.")
    </sec:authorize>
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
        <div class="createdDate">
            작성날짜:
            <%=details.getArticleCreatedDate().getYear() + 1900%> 년
            <%=details.getArticleCreatedDate().getMonth() + 1%> 월
            <%=details.getArticleCreatedDate().getDay() + 5%> 일
        </div>
        <form action="delete/<%=details.getArticleId()%>" method="post" name="deleteForm"
              class="delete" onclick="deletes()">삭제
        </form>
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
                            + "<div class=\"commentWriter\">작성자: " + comment.getWriter()
                            + "</div>\n"
                            + "<div class=\"commentCreatedDate\">작성날짜: " + (
                            comment.getCreatedDate().getYear() + 1900) + "년 "
                            + (comment.getCreatedDate().getMonth() + 1) + "월 "
                            + (comment.getCreatedDate().getDay() - 2) + "일 "
                            + "</div>\n" + "</div>");
                }
            %>
        </div>
    </div>
</div>
</body>
</html>
