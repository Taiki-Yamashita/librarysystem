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
<title>本のリクエスト</title>
</head>
<body>
	<h1>図書システム借りたいナ☆</h1>
	<h2>本のリクエスト</h2>

		<c:if test="${ not empty errorMessages }">
			<div class="errorMessages">
				<c:forEach items="${errorMessages}" var="message">
					<c:out value="${message}" />
				</c:forEach>
			</div>
			<c:remove var="errorMessages" scope="session"/>
		</c:if>
		<div class="subButton">
			<input class="logout" type="button" onclick="location.href='./logout'"value="ログアウト">
		</div>
		<br/><br>
		<table class="menuBar">
			<tr>
				<td><input type="button" onclick="location.href='./'"value="トップ"></td>
				<td><input type="button" onclick="location.href='./search'"value="検索"></td>
				<td><input type="button" onclick="location.href='./ranking'"value="貸出/予約ランキング"></td>
				<td><input type="button" onclick="location.href='./user'"value="マイページ"></td>
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
			<input class="request" type="submit" value="申請" />
		</td>
	</tr>
</table>
			</form>

		</div>
</body>
</html>