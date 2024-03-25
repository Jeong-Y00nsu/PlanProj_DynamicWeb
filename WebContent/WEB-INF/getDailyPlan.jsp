<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2024-03-25
  Time: 오후 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title>일별 일정</title>
</head>
<body>
    <script src="/js/jquery-3.7.1.min.js"></script>
    <script src="/js/getDailyPlan.js"></script>
    <input type="hidden" id="year"/>
    <input type="hidden" id="month"/>
    <div id="dailyPlanDiv">
        <table id="dailyPlanTable">
            <thead>
                <tr>
                    <th>순서</th>
                    <th>제목</th>
                    <th>시작일</th>
                    <th>종료일</th>
                </tr>
            </thead>
            <tbody></tbody>
        </table>
        <input type="button" id="monthlyPlanBtn" value="월별 일정"/>
        <input type="button" id="addPlanBtn" value="일정 추가"/>
    </div>
</body>
</html>
