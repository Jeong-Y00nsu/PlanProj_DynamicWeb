<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2024-03-08
  Time: 오전 6:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>일정 수정</title>
</head>
<body>
<div class  = "editPlan">
    <form id="editPlan" action="/modifyPlan">
        <h1>일정 수정</h1>
        일정명: <input type="text" id="planName" name="planName">
        시작일: <input type="text" id="startDt" name="startDt">
        종료일: <input type="text" id="endDt" name="endDt">
        메  모: <input type="text" id="text" name="text">
        <button type="submit">수정</button>
    </form>
</div>
</body>
</html>
