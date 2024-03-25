<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2024-03-08
  Time: 오전 6:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title>일정 추가</title>
</head>
<body>
    <script src="/js/jquery-3.7.1.min.js"></script>
    <script src="/js/addPlan.js"></script>
    <div class  = "addPlan">
        <form id="addPlan" action="/addPlan">
            <h1>일정 추가</h1>
            일정명: <input type="text" id="planName" name="planName">
            시작일: <input type="text" id="startDt" name="startDt">
            종료일: <input type="text" id="endDt" name="endDt">
            메  모: <input type="text" id="text" name="text">
            <input type="button" id="addPlanBtn" value="추가"/>
            <input type="button" id="cancelBtn" value="취소"/>
        </form>
    </div>
</body>
</html>
