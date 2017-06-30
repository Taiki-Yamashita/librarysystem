<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>管理画面</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="../css/styleOkada.css" rel="stylesheet" type="text/css">
		<link href="../css/styleTaiki.css" rel="stylesheet" type="text/css">
		<link href="../css/styleKuniyoshi.css" rel="stylesheet" type="text/css">
	</head>
	<body>
		<h1>かりたいナ管理</h1>
		<input class="manageToTop" type="button" onclick="location.href='../'"value="トップ画面">
		<table border="1" class="manageTable">
			<tr>
				<td><input type="button" onclick="location.href='./manageUser'"value="ユーザー情報管理"></td>
				<td><input type="button" onclick="location.href='./manageBook'"value="本の情報管理"></td>
			</tr>
			<tr>
				<td><input type="button" onclick="location.href='./receive'"value="問い合わせ受取"></td>
				<td><input type="button" onclick="location.href='./notification'"value="お知らせ投稿"></td>
			</tr>
		</table>
	</body>
</html>