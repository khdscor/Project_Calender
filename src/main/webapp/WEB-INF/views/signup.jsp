<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>signup</title>
    <link href="${pageContext.request.contextPath}/css/signup.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="container">
    <form>
        <input class="id" type="text" placeholder="아이디를 입력해주세요">
        <input class="password" type="password" placeholder="비밀번호를 입력해주세요"/>
        <input class="rePassword" type="password" placeholder="비밀번호를 다시한번 입력해주세요"/>
        <input class="signUp" type="button" value="회원가입" onclick="location='/'">
    </form>
</div>
</body>
</html>
