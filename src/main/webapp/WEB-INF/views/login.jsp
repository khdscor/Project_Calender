<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
    <link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet" type="text/css">
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
    <form>
        <input class="id" type="text" name = "id" placeholder="아이디를 입력해주세요">
        <input class="password" type="password" name ="password" placeholder="비밀번호를 입력해주세요"/>
        <input class="signUp" type="button" value="회원가입" onclick="location='signup'">
        <input class="login" type="submit" value="로그인">
    </form>
</div>
</body>
</html>
