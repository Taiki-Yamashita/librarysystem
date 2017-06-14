<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>検索</title>
	</head>
	<body>
		<h2>検索</h2>

		<a href = "./">トップ</a>
		<a href = "./favorite">お気に入り</a>
		<a href = "./require">本のリクエスト</a>
		<a href = "./admin/manage">管理画面</a>
		<a href = "./introduction">本の紹介</a><br/>

		<hr width="1500px">

		<c:if test="${ empty errorMessages }">
			検索結果は
			<font color="#ff0000"><c:out value="${booksCount}件" /></font>
			です
		</c:if>

		<c:if test="${ not empty errorMessages }">
			<c:forEach items="${errorMessages}" var="message">
				<font color="#ff0000"><c:out value="${message}" /></font><br>
			</c:forEach>
			<c:remove var="errorMessages" scope="session"/>
		</c:if>

		<!--
			new books
			clear button
		-->

		<p>◎フリーワード検索</p>

		<form action="./search" method="POST">
			<table>
				<tr>
					<td>
						<select name="selectBox">
							<option value="1">全て</option>
							<option value="2">名前</option>
							<option value="3">著者</option>
							<option value="4">出版社</option>
							<option value="5">カテゴリ</option>
							<option value="6">ISBN番号</option>
						</select>
					</td>
					<td><input type="text" name="freeWord" placeholder="未記入で全て検索"/>で</td>
					<td><input type="submit" value="検索"></td>
				</tr>
			</table>
		</form>
		<hr width="1500px">

		<p>◎絞込み検索</p>
		<table>
			<tr>
				<td>
					<input type="checkbox" name="refine" value="1">全て
					<input type="checkbox" name="refine" value="2">名前
					<input type="checkbox" name="refine" value="3">著者
					<input type="checkbox" name="refine" value="4">出版社
					<input type="checkbox" name="refine" value="5">カテゴリ
				</td>
				<td>
					で<input type="submit" value="絞り込む">
				</td>
			</tr>
		</table>

		<hr width="1500px">
		<form action="./search" method="POST">
			<table>
				<tr><th>本</th><th>予約</th></tr>
				<c:forEach items="${selectedBooks}" var="book">
					<tr>
						<td><c:out value="${book.name}"/></td>
						<td><input type="submit"  value="予約" /></td>
					</tr>
				</c:forEach>
			</table>
		</form>
	</body>
</html>