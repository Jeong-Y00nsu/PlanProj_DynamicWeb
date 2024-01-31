<!-- 
파일이름: index.jsp
기   능: 회원 관리 웹페이지의 초기 화면(html), sevlet들과 연결되어 있어서 각 event를 처리한다.
작 성 자: 201921288 정윤수
작성일자: 2021/09/08 
-->
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>회원 정보 관리</title>
</head>
<body>
  <p>회원 정보 관리</p>
  <p>update date: 2021/09/21</p>
  <p> </p>
  <form method="post" action="add">
  	<p> 회원 추가 </p>
  	<p>  성: <input type="text" name="lastName"> 이름: <input type="text" name="firstName"></p>
  	<p> 생년월일: <input type="text" name="birth"></p>
  	<p> 전화번호: <input type="text" name="telephone_number"></p>
  <button type="submit">추가</button>
  </form>
  <p> </p>
  <form method="get" action="getAll">
  <p> 회원 목록 조회</p>
  <button type="submit">조회</button>
  </form>
  <p> </p>
  <form method="get" action="find">
  <p> 회원 찾기 </p>
  <p> 성: <input type="text" name="find_lastName"> 이름: <input type="text" name="find_firstName"> <button>찾기</button></p>
  </form>
  <p> </p>
</body>
<footer>
  <p> 제작 : 201921288 정윤수 </p>
</footer>
</html>
