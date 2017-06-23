<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>問い合わせ、リクエスト受信</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>
	<a href = "manage">管理画面</a>
	<form action="recieve" method = "post">
		<c:if test="${empty num}">
			<input type="radio" name="num" value="2" checked><label for = "num" >全て</label>
			<input type="radio" name="num" value="0"><label for = "num">未読表示</label>
			<input type="radio" name="num" value="1"><label for = "num">既読表示</label>
		</c:if>
		<c:if test="${not empty num}">
			<input type="radio" name="num" value="2"><label for = "num">全て</label>
			<c:if test="${num == 0}">
				<input type="radio" name="num" value="0" checked><label for = "num">未読表示</label>
			</c:if>
			<c:if test="${num != 0}">
				<input type="radio" name="num" value="0"><label for = "num">未読表示</label>
			</c:if>
			<c:if test="${num == 1}">
				<input type="radio" name="num" value="1" checked><label for = "num">既読表示</label>
			</c:if>
			<c:if test="${num != 1}">
				<input type="radio" name="num" value="1"><label for = "num">既読表示</label>
			</c:if>
		</c:if>
		<input type="submit" value="絞込み" />
	</form>



		<table>

			<tr>
				<th>ユーザー名</th>
				<th>書籍名</th>
				<th>著者</th>
				<th>出版社</th>
				<th>リクエスト日</th>
				<th>既読</th>
				<th>未読にするよ</th>
			</tr>

			<c:forEach items="${recieves}" var="recieve">
				<tr>
					<td><c:out value="${recieve.userName}" /></td>
					<td><c:out value="${recieve.bookName}" /></td>
					<td><c:out value="${recieve.author}" /></td>
					<td><c:out value="${recieve.publisher}" /></td>
					<td>
						<fmt:parseDate var="date" value="${recieve.requiredDate}" pattern="yyyy-MM-dd HH:mm:ss" />
						<fmt:formatDate pattern = "yyyy年MM月dd日" value = "${date}" />
					</td>
					<td>
						<c:if test="${recieve.showing == 0 }">
							<form action="recieve" method = "post">
								<input type="hidden" name="flag" id="flag" value="1">
								<input type="checkbox" name="recieveId" id="recieveId" value="${recieve.id}">
							</form>
						</c:if>
						<c:if test="${recieve.showing != 0 }">
							<c:out value="既読"></c:out>
						</c:if>
					</td>
					<td>
						<c:if test="${recieve.showing == 1 }">
							<form action="recieve" method = "post">
								<input type="hidden" name="flag" id="flag" value="0">
								<input type="checkbox" name="recieveId2" id="recieveId2" value="${recieve.id}">
							</form>
						</c:if>
						<c:if test="${recieve.showing != 1 }">
							<c:out value="未読なう"></c:out>
						</c:if>
					</td>
					<td>
					</td>
				</tr>
			</c:forEach>
		</table>
		<input type="submit" value="送信" />

</body>
</html>