<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>signup</title>
    <link href="${pageContext.request.contextPath}/css/signup.css" rel="stylesheet" type="text/css">
</head>
<body>
<%
    if (request.getAttribute("message") != null) {
        String message = request.getAttribute("message").toString();
%>
<script>
  alert("<%=message %>")
</script>
<%
    }
%>
<div class="container">
    <form action="/signup" method="post">
        <input class="id" type="text" name="id" placeholder="아이디를 입력해주세요">
        <input class="password" type="password" name="password" placeholder="비밀번호를 입력해주세요"/>
        <input class="rePassword" type="password" name="rePassword"
               placeholder="비밀번호를 다시한번 입력해주세요"/>
        <input class="signUp" type="submit" value="회원가입">
    </form>
</div>
</body>
</html>
