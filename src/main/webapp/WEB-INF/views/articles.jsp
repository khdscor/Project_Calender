<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Articles</title>
    <link href="${pageContext.request.contextPath}/css/articles.css" rel="stylesheet"
          type="text/css">
</head>
<body>
<div class="outside">
    <div class="container">
        <div class="list">글목록</div>
        <div class="write">글쓰기</div>
        <div class="cancel" onclick="location='/'">x</div>
        <div class="article">
            <div class="writer">
                작성자
            </div>
            <div class="title">
                제목
            </div>
            <div class="subInfo">
                <div class="createdDate">
                    작성일
                </div>
                <div class="comment">
                    댓글 수
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
