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
    <script src="/js/editPlan.js"></script>
    <script src="/lib/tui-date-picker/dist/tui-date-picker.js"></script>
    <script src="/lib/tui-date-picker/dist/tui-date-picker.css"></script>
    <div class  = "addPlan">
        <a id="message"></a>
        <form id="addPlan" action="/addPlan">
            <input type="hidden" id="year" name="year"/>
            <input type="hidden" id="month" name="month"/>
            <input type="hidden" id="day" name="day"/>
            <h1>일정 추가</h1>
            일정명: <input type="text" id="title" name="title"/>
            <div id="tuiDatePickerDiv">
                <table style="width: 650px;">
                    <thead>
                    <tr>
                        <td><h3>시작일</h3></td>
                        <td><h3>종료일</h3></td>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>
                            <div class="tui-datepicker-input tui-datetime-input tui-has-focus">
                                <input type="text" id="startDt" aria-label="Date-Time">
                                <span class="tui-ico-date"></span>
                            </div>
                            <div id="wrapper" style="margin-top: -1px;"></div>
                        </td>
                        <td>
                            <div class="tui-datepicker-input tui-datetime-input tui-has-focus">
                                <input type="text" id="endDt" aria-label="Date-Time">
                                <span class="tui-ico-date"></span>
                            </div>
                            <div id="wrapper-2" style="margin-top: -1px;"></div>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            메  모: <input type="text" id="text" name="text">
            <input type="button" id="addPlanBtn" value="추가"/>
            <input type="button" id="cancelBtn" value="취소"/>
        </form>
    </div>
</body>
</html>
