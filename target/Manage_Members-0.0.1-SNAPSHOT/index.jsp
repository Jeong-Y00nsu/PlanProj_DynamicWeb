<!-- 
�����̸�: index.jsp
��   ��: ȸ�� ���� ���������� �ʱ� ȭ��(html), sevlet��� ����Ǿ� �־ �� event�� ó���Ѵ�.
�� �� ��: 201921288 ������
�ۼ�����: 2021/09/08 
-->
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>ȸ�� ���� ����</title>
</head>
<body>
  <p>ȸ�� ���� ����</p>
  <p>update date: 2021/09/21</p>
  <p> </p>
  <form method="post" action="add">
  	<p> ȸ�� �߰� </p>
  	<p>  ��: <input type="text" name="lastName"> �̸�: <input type="text" name="firstName"></p>
  	<p> �������: <input type="text" name="birth"></p>
  	<p> ��ȭ��ȣ: <input type="text" name="telephone_number"></p>
  <button type="submit">�߰�</button>
  </form>
  <p> </p>
  <form method="get" action="getAll">
  <p> ȸ�� ��� ��ȸ</p>
  <button type="submit">��ȸ</button>
  </form>
  <p> </p>
  <form method="get" action="find">
  <p> ȸ�� ã�� </p>
  <p> ��: <input type="text" name="find_lastName"> �̸�: <input type="text" name="find_firstName"> <button>ã��</button></p>
  </form>
  <p> </p>
</body>
<footer>
  <p> ���� : 201921288 ������ </p>
</footer>
</html>
