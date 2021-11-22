<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>write</title>
    <link href="${pageContext.request.contextPath}/css/write.css" rel="stylesheet"
          type="text/css">
</head>
<body>
<%
    String year = request.getParameter("year");
    String month = request.getParameter("month");
    String day = request.getParameter("day");
%>
<script>
  function cancel() {
    if (confirm("작성하던 글이 사라집니다. 계속 진행하시겠습니까?")) {
      location.href = "/articles?year=" + <%=year%> + "&month=" + <%=month%> + "&day=" + <%=day%>;
    }
  }

  function check() {
    if (document.getElementById("title").value == "") {
      alert("제목을 입력하세요");
      return;
    }
    if (document.getElementById("content").value == "") {
      alert("내용을 입력하세요");
      return;
    }
    form.submit();
  }
</script>
<div class="outside">
    <div class="container">
        <div class="cancel" onclick="cancel()">x</div>
        <sec:authorize access="isAuthenticated()">
            <sec:authentication property="Principal.user.id" var="userId"/>
            <form action="/articles/write" method="post" name="form">
                <input class="title" id="title" type="text" name="title" placeholder="제목을 입력해주세요">
                <textarea class="content" id="content" name="content"
                          placeholder="내용을 입력해 주세요"></textarea>
                <input class="dummy" type="text" name="year" value=<%=year%>>
                <input class="dummy" type="text" name="month" value=<%=month%>>
                <input class="dummy" type="text" name="day" value=<%=day%>>
                <input class="dummy" type="text" name="userId" value=${userId}>
                <input class="write" type="button" value="완료" onclick="check()">
            </form>
        </sec:authorize>
        <sec:authorize access="isAnonymous()">
            <script>
              alert("로그인 먼저 진행해주세요!")
              location.href = "/login"
            </script>
        </sec:authorize>
    </div>
</div>
</body>
</html>
