<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>write</title>
    <link href="${pageContext.request.contextPath}/css/write.css" rel="stylesheet"
          type="text/css">
</head>
<body>
<script>
  function cancel() {
    if (confirm("작성하던 글이 사라집니다. 계속 진행하시겠습니까?")) {
      location.href="/articles";
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
        <form action="/articles/write" method="post" name="form">
            <input class="title" id="title" type="text" name="title" placeholder="제목을 입력해주세요">
            <textarea class="content" id="content" name="content"
                      placeholder="내용을 입력해 주세요"></textarea>
            <input class="write" type="button" value="완료" onclick="check()">
        </form>
    </div>
</div>
</body>
</html>
