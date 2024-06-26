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
    <script src="/js/jquery-3.7.1.min.js"></script>
    <script src="/js/signUp.js"></script>
    <script src="/js/util.js"></script>
    <div class ="signUp">
        <form id="signUpForm" action="/signUp">
            <h1>회원 가입</h1>
            아이디: <input type="text" id="id" name="id">
            <a id="idMessage"></a>
            <input type="button" id="checkDuplicateId" value="ID중복체크" onclick="">
            비밀번호: <input type="text" id="pw" name="pw">
            비밀번호: <input type="text" id="repeatPw" name="repeatPw">
            <a id="pwMessage"></a>
            이름: <input type="text" id="name" name="name">
            <input type="button" value="회원 가입" id="signUpBtn"/>
            <a id="nameMessage"></a>
        </form>
    </div>
</body>
</html>
