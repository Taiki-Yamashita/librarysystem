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

		<p>◎フリーワード検索</p>

		<form action="./searchFreeWord" method="POST">
			<table>
				<tr>
					<td>
						<select name="selectBox">
							<c:if test="${not empty selectBox}">
								<option value="${selectBoxId}">${selectBox}</option>
							</c:if>
							<c:if test="${selectBoxId != 1}"><option value="1">全て</option></c:if>
							<c:if test="${selectBoxId != 2}"><option value="2">本</option></c:if>
							<c:if test="${selectBoxId != 3}"><option value="3">著者</option></c:if>
							<c:if test="${selectBoxId != 4}"><option value="4">出版社</option></c:if>
							<c:if test="${selectBoxId != 5}"><option value="5">カテゴリ</option></c:if>
							<c:if test="${selectBoxId != 6}"><option value="6">ISBN番号</option></c:if>
						</select>
					</td>
					<td>
						<c:if test="${not empty freeWord}">
							<input type="text" name="freeWord" value="${freeWord}"/>で
						</c:if>
						<c:if test="${empty freeWord}">
							<input type="text" name="freeWord" placeholder="未記入で全て検索"/>で
						</c:if>
					</td>
					<td><input type="submit" value="検索"></td>
				</tr>
			</table>
		</form>
		<hr width="1500px">

		<p>◎絞込み検索</p>

		<form action="./searchRefine" method="POST">
			<table>
				<tr>
					<td>
						<input type="checkbox" name="new" value="1">新着本のみ
					</td>
				</tr>
				<tr>
					<td>
						<input type="checkbox" name="library" value="1">【図書館名1】
						<input type="checkbox" name="library" value="2">【図書館名2】
						<input type="checkbox" name="library" value="3">【図書館名3】
						<input type="checkbox" name="library" value="4">【図書館名4】
						<input type="checkbox" name="library" value="5">【図書館名5】
					</td>
				</tr>
				<tr>
					<td>
						<input type="checkbox" name="category" value="1">文学
						<input type="checkbox" name="category" value="2">経済
						<input type="checkbox" name="category" value="3">芸能
						<input type="checkbox" name="category" value="4">歴史
						<input type="checkbox" name="category" value="5">学問
						<input type="checkbox" name="category" value="6">政治
						<input type="checkbox" name="category" value="7">暮らし
						<input type="checkbox" name="category" value="8">教育
						<input type="checkbox" name="category" value="9">SF
					</td>
				</tr>
				<tr>
					<td>
						<input type="checkbox" name="category" value="1">文庫
						<input type="checkbox" name="category" value="2">新書
						<input type="checkbox" name="category" value="3">雑誌
						<input type="checkbox" name="category" value="4">コミックス
					</td>
				</tr>
				<tr>
					<td>
						で<input type="submit" value="絞り込む">
						<input type="submit" value="クリア">
					</td>
				</tr>
			</table>
		</form>

		<hr width="1500px">

		<c:if test="${ empty errorMessages && not empty booksCount}">
			検索結果は
			<font color="#ff0000"><c:out value="${booksCount}件" /></font>
			です
		</c:if>

		<c:if test="${ not empty errorMessages }">
			<c:forEach items="${errorMessages}" var="message">
				<font color="#ff0000"><c:out value="${message}" /></font><br>
			</c:forEach>
		</c:if>

		<c:if test="${not empty selectedBooks}">
			<form action="./search" method="POST">
				<table>
					<tr><th>本</th><th>予約</th></tr>
					<c:forEach items="${selectedBooks}" var="book">
						<c:if test="${book.id != 0}">
							<tr>
								<td><c:out value="${book.name}"/></td>
								<td><input type="submit"  value="予約" /></td>
							</tr>
						</c:if>
					</c:forEach>
				</table>
			</form>
		</c:if>

		<c:remove var="selectBox" scope="session"/>
		<c:remove var="selectBoxId" scope="session"/>
		<c:remove var="freeWord" scope="session"/>
		<c:remove var="booksCount" scope="session"/>
		<c:remove var="selectedBooks" scope="session"/>
		<c:remove var="errorMessages" scope="session"/>

	</body>
</html>