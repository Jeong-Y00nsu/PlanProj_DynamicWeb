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
  <p>update date: 2024/02/13</p>
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
</html>
