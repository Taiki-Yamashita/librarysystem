<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="./css/styleOkada.css" rel="stylesheet" type="text/css">
<link href="./css/styleTaiki.css" rel="stylesheet" type="text/css">
<link href="./css/styleKuniyoshi.css" rel="stylesheet" type="text/css">
<title>リクエスト</title>
</head>
<body>
	<h1>リクエスト</h1>

		<c:if test="${ not empty errorMessages }">
			<div class="errorMessages">
				<c:forEach items="${errorMessages}" var="message">
					<c:out value="${message}" />
				</c:forEach>
			</div>
			<c:remove var="errorMessages" scope="session"/>
		</c:if>
		<a href = "./logout" class="right">ログアウト</a>
		<br/>
		<table border="1" class="center">
			<tr>
				<td><a href = "./">トップ</a></td>
				<td><a href = "./search">検索</a></td>
				<td><a href = "./ranking">ランキング</a></td>
				<td><a href = "./user">マイページ</a></td>
			</tr>
		</table>

		<div class="center">

			<form action="require" method="post">
<table class="form">
	<tr>
		<td>
			<label for="userName">申請者:</label><br/>
			<c:out value="${loginUser.name}"></c:out><br/>
			<input type="hidden" name="userName" value="${loginUser.name}">
		</td>
	</tr>
	<tr>
		<td>
			<label for="bookName">書籍名:</label><br/>
			<input name="bookName" value="${newRequire.bookName}"/>*必須<br/>
		</td>
	</tr>
	<tr>
		<td>
			<label for="author">著者:</label><br/>
			<input name="author" value="${newRequire.author}"/><br/>
		</td>
	</tr>
	<tr>
		<td>
			<label for="publisher">出版社:</label><br/>
			<input name="publisher"  value="${newRequire.publisher}"/><br/>
		</td>
	</tr>
	<tr>
		<td>
			<label for="comment">備考:</label><br/>
			<pre><textarea name="comment" rows="10" cols="100"><c:out value="${newRequire.comment}"/></textarea></pre>
			（500文字以下で入力してください）
		</td>
	</tr>
	<tr>
		<td>
			<input type="submit" value="申請" />
		</td>
	</tr>
</table>
			</form>

		</div>
</body>
</html>