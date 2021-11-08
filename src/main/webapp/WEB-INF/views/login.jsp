<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>login</title>
    <link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet" type="text/css">
</head>
<body>
<sec:authorize access="isAuthenticated()">
    <%
        response.sendRedirect("/");
    %>
</sec:authorize>
<script>
  function check() {
    if (document.getElementById("id").value == "") {
      alert("아이디를 입력하세요");
      return;
    }
    if (document.getElementById("password").value == "") {
      alert("비밀번호를 입력하세요");
      return;
    }
    form.submit();
  }
</script>
<div class="container">
    <form action="/login" method="post" name="form">
        <input class="id" id="id" type="text" name="id" placeholder="아이디를 입력해주세요">
        <input class="password" id="password" type="password" name="password"
               placeholder="비밀번호를 입력해주세요"/>
        <input class="signUp" type="button" value="회원가입" onclick="location='signup'">
        <input class="login" type="button" value="로그인" onclick="check()">
    </form>
</div>
</body>
</html>
