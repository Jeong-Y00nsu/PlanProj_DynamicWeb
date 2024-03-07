<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2024-03-06
  Time: 오전 6:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SignUp</title>
</head>
<body>
    <div class ="signUp">
        <form id="signUpForm" action="/signUp">
            <h1>회원 가입</h1>
            아이디: <input type="text" id="id" name="id">
            <input type="button" id="checkDuplicateId" value="ID중복체크" onclick="">
            비밀번호: <input type="text" id="pw" name="pw">
            비밀번호: <input type="text" id="repeatPw" name="repeatPw">
            <a>* 재입력한 비밀번호가 일치하지 않습니다. 다시 입력해주세요.</a>
            이름: <input type="text" id="name" name="name">
            <button type="submit">회원 가입</button>
        </form>
    </div>
</body>
</html>
