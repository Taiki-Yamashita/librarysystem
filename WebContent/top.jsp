<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="./css/styleKuniyoshi.css" rel="stylesheet" type="text/css">
		<title>トップ</title>
	</head>
	<body>
		<h1>図書システム借りたいナ☆</h1>

		<c:if test="${ not empty errorMessages }">
			<div class="errorMessages">
				<c:forEach items="${errorMessages}" var="message">
					<c:out value="${message}" />
				</c:forEach>
			</div>
			<c:remove var="errorMessages" scope="session"/>
		</c:if>

		<c:if test="${ not empty doneMessages }">
			<div class="errorMessages">
				<c:forEach items="${doneMessages}" var="message">
					<li><c:out value="${message}" />
				</c:forEach>
			</div>
			<c:remove var="doneMessages" scope="session"/>
		</c:if>

		<c:if test="${ not empty registerMessage}">
			<div class="registerMessages">
				<c:out value="${registerMessage}" />
			</div>
			<c:remove var="registerMessage" scope="session"/>
		</c:if>

		<p>ログインするといろいろな機能がつかえます(*^◯^*)</p>
		<c:if test="${loginUser.id == null}">
			<table border="1" class="notLoginTopTable">

				<tr>
					<td rowspan="2"><input class="searchButton" type="button" onclick="location.href='./search'"value="検索"></td>
					<td><input class="rankingButton" type="button" onclick="location.href='./ranking'"value="ランキング"></td>
				</tr>
				<tr>
					<td><input type="button" onclick="location.href='./login'"value="ログイン"></td>
				</tr>
			</table>
		</c:if>

		<c:if test="${loginUser.id != null}">
			<c:if test="${loginUser.id == 0}">
				<input type="button" onclick="location.href='./admin/manage'"value="管理用">
			</c:if>
			<input type="button" onclick="location.href='./logout'"value="ログアウト">
			<table border="1">
				<tr>
					<td><input type="button" onclick="location.href='./user'"value="マイページ"></td>
					<td><input type="button" onclick="location.href='./ranking'"value="ランキング"></td>
					<td><input type="button" onclick="location.href='./favorite'"value="お気に入り"></td>
					<td><input type="button" onclick="location.href='./require'"value="本のリクエスト"></td>
					<td><input type="button" onclick="location.href='./renewPassword'"value="パスワード編集"></td>
				</tr>
			</table>
			<table border="1" class="loginTopTable">
				<tr>
					<td><input type="button" onclick="location.href='./search'"value="検索"></td>
					<td><input type="button" onclick="location.href='./ranking'"value="ランキング"></td>
				</tr>
			</table>
		</c:if>

		<div class="notification">
			<h5>お知らせ</h5>
			<c:forEach items="${informations}" var="information" varStatus="count">
				<c:if test="${count.index >= (pageNumber-1)*4 && count.index <= (pageNumber*4)-1}">
					<a href="information?id=${information.id }"name="id"><c:out value="${information.title}"/></a>
					<div class="registeredDate">投稿日時:
						<fmt:parseDate var="date" value="${information.registeredDate}" pattern="yyyy-MM-dd HH:mm:ss" />
						<fmt:formatDate pattern = "yyyy年MM月dd日" value = "${date}" />
					</div>
					<div class="libraryId">
						<c:forEach items="${libraries}" var="library">
							<c:if test = "${library.id == information.libraryId}">
								図書館:<c:out value="${library.name}" /></br>
								-------------------------------------------------------------------------
							</c:if>
						</c:forEach>
					</div>
				</c:if>
			</c:forEach>

			<table class="pageNumber">
				<tr>
					<c:forEach items="${pageCountList}" var="pageCount">
						<form action="./" method="GET">
							<td>
								<div class="currentPage">
									<c:if test="${pageNumber == pageCount}">
										<c:out value="${pageCount}"></c:out>
									</c:if>
								</div>
								<c:if test="${pageNumber != pageCount}">
									<input class="otherPage" type="submit" value="${pageCount}"/>
									<input type="hidden" name="pageNumber" value="${pageCount}">
								</c:if>
							</td>
						</form>
					</c:forEach>
				</tr>
			</table>
		</div>

	</body>
</html>