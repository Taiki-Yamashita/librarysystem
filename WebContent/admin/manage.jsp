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
<link href="../css/styleTaiki.css" rel="stylesheet" type="text/css">
<link href="../css/styleOkada.css" rel="stylesheet" type="text/css">
</head>
<body>
	<p class="center"><a href = "../">トップ画面</a></p>
		<table border="1" class="centermanage">
			<tr>
				<td><a class="font" href = "./manageUser">ユーザー情報管理</a></td>
				<td><a class="font" href = "./manageBook">本の情報管理</a></td>
			</tr>
			<tr>
				<td><a class="font" href = "./receive">問い合わせ受取</a></td>
				<td><a class="font" href = "./notification">お知らせ投稿</a></td>
			</tr>
		</table>
</body>
</html>