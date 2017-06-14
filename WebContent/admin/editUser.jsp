<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザーの編集</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>


<form action="editUser"method="post"><br />

	<input name="id" type="hidden" value="${editUser.id}"/>

	<label for="loginId">ログインID</label><br>
	<input name="loginId" id="loginId" value="${editUser.loginId }"/><br />

	<label for="password">パスワード</label><br>
	<input name="password" id="password" /><br />

	<label for="name">名前</label><br>
	<input name="name" id="name"  value="${editUser.name }"/><br />

	<label for="address">住所</label><br>
	<input name="address" id="address" value="${editUser.address }"/><br />

		<label for="tel">TEL</label><br>
	<input name="tel" id="tel" value="${editUser.tel }"/><br />

	<label for="mail">メールアドレス</label><br>
	<input name="mail" id="mail" value="${editUser.mail }"/><br />

	<label for="point">ポイント</label><br>
	<input name="point" id="point" value="${editUser.point }"/><br />

	<label for="registerDate">登録日</label><br>
	<input name="registerDate" id="registerDate" value="${editUser.registerDate }"/><br />

		<label for="libraryId">図書館</label><br>
	<input name="libraryId" id="libraryId" value="${editUser.libraryId }"/><br />

	<label for="stopping">停止中</label><br>
	<input name="stopping" id="stopping" value="${editUser.stopping }"/><br />

	<br><input type="submit" value="投稿">
</form>