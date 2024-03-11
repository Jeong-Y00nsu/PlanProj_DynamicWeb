<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2024-03-11
  Time: 오후 11:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
    <title>Calendar</title>
</head>
<body>
  <script src="/js/jquery-3.7.1.min.js"></script>
  <script src="/js/calendar.js"></script>
  <div class="planDiv">
    <div class="plans">
      <h1>일정</h1>
      <div class="yearDiv">
        <input class="preYearBtn" type="button" id="preYearBtn" value="◀"/>
        <input class="year" type="text" id="year" disabled/>
        <input class="nextYearBtn" type="button" id="nextYearBtn" value="▶"/>
      </div>
      <div class="monthDiv">
        <input class="preMonthBtn" type="button" id="preMonthBtn" value="◀"/>
        <input class="month" type="text" id="month" disabled/>
        <input class="nextMonthBtn" type="button" id="nextMonthBtn" value="▶"/>
      </div>
      <fieldset>
        <legend>달력</legend>
        <table class="calendar" id="calendar">
          <tr>
            <th>일</th>
            <th>월</th>
            <th>화</th>
            <th>수</th>
            <th>목</th>
            <th>금</th>
            <th>토</th>
          </tr>
        </table>
      </fieldset>
    </div>
  </div>
</body>
</html>
