<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2024-03-06
  Time: 오전 6:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>SignIn</title>
</head>

<body>
<script src="/js/jquery-3.7.1.min.js"></script>
<script src="/js/signIn.js"></script>
<script src="/js/util.js"></script>
<div class="login">
    <form id="loginForm" action="/signIn" method="post">
        <h1>로그인 </h1>
        아이디: <input type="text" name="id" placeholder="아이디"></br>
        <a id="idMessage"></a>
        비밀번호: <input type="text" id="pw" name="password" placeholder="비밀번호">
        <a id="pwMessage"></a>
        <input type="button" id="signInBtn" name="signInBtn" value="로그인"/>
        <input type="button" id="signUpBtn" name="signUpBtn" value="회원가입"/>
    </form>
</div>
</body>
</html>