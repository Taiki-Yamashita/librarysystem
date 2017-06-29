<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>問合わせ受取</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
function check(){

	if(window.confirm('本当によろしいですか？')){ // 確認ダイアログを表示

		return true; // 「OK」時は送信を実行

	}
	else{ // 「キャンセル」時の処理

		window.alert('キャンセルしました'); // 警告ダイアログを表示
		return false; // 送信を中止
	}
}
</script>
</head>
<body>
	<h1>図書システム借りたいナ☆</h1>
	<h2>問合わせ受取</h2>
	<a href = "manage">管理画面</a>
	<form action="receive" method = "post">
		<c:if test="${empty num}">
			<input type="radio" name="num" value="2" checked><label for = "num" >全て</label>
			<input type="radio" name="num" value="0"><label for = "num">未読表示</label>
			<input type="radio" name="num" value="1"><label for = "num">既読表示</label>
		</c:if>
		<c:if test="${not empty num}">
			<input type="radio" name="num" value="2" checked><label for = "num">全て</label>
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
		<label for="freeWord">フリーワード検索</label>
		<input type="text" name="freeWord" id="freeWord" value="${freeWord}">
		<input type="submit" value="絞込み" />
	</form>

	<c:if test="${ not empty errorMessages }">
		<c:forEach items="${errorMessages}" var="message">
			<font color="#ff0000"><c:out value="${message}" /></font><br>
		</c:forEach>
	</c:if>
	<c:if test="${empty errorMessages }">

	<form action="receive" method = "post" onSubmit="return check()">
				<button type="submit" value="edit">実行</button>

		<table>

			<tr>
				<th>ユーザー名</th>
				<th>書籍名</th>
				<th>著者</th>
				<th>出版社</th>
				<th>リクエスト日</th>
				<th>既読</th>
				<th>既読チェック</th>
				<th>削除チェック</th>

			</tr>

			<c:if test="${not empty receives}">
				<c:forEach items="${receives}" var="receive">
					<tr>
						<td><c:out value="${receive.userName}" /></td>
						<td>
							<c:if test="${receive.comment == '特になし' || empty receive.comment}">
								<c:out value="${receive.bookName}" />
							</c:if>
							<c:if test="${receive.comment != '特になし' && not empty receive.comment}">
								<a href="remark?id=${receive.id}"><c:out value="${receive.bookName}" /></a>
							</c:if>
						</td>
						<td><c:out value="${receive.author}" /></td>
						<td><c:out value="${receive.publisher}" /></td>
						<td>
							<fmt:parseDate var="date" value="${receive.requiredDate}" pattern="yyyy-MM-dd HH:mm:ss" />
							<fmt:formatDate pattern = "yyyy年MM月dd日" value = "${date}" />
						</td>
						<td>
							<c:if test="${receive.showing == 0 }">
								<c:out value="未読"></c:out>
								<input type="hidden" name="flag" id="flag" value="1">
								<td><input type="checkbox" name="receiveId" id="receiveId" value="${receive.id}"></td>
							</c:if>
							<c:if test="${receive.showing != 0 }">
								<c:out value="済"></c:out>
								<input type="hidden" name="flag" id="flag" value="0">
								<td><input type="checkbox" name="receiveId2" id="receiveId2" value="${receive.id}"></td>
								<td><input type="checkbox" name="deleteId" value="${receive.id}"></td>
							</c:if>
						</td>


					</tr>
				</c:forEach>
			</c:if>

			<c:if test="${not empty books}">
				<c:forEach items="${books}" var="ReaquiredBook">
					<tr>
						<td><c:out value="${ReaquiredBook.userName}" /></td>
						<td>
							<c:if test="${ReaquiredBook.comment == '特になし' || empty ReaquiredBook.comment}">
								<c:out value="${ReaquiredBook.bookName}" />
							</c:if>
							<c:if test="${ReaquiredBook.comment != '特になし' && not empty ReaquiredBook.comment}">
								<a href="remark?id=${ReaquiredBook.id}"><c:out value="${ReaquiredBook.bookName}" /></a>
							</c:if>
						</td>
						<td><c:out value="${ReaquiredBook.author}" /></td>
						<td><c:out value="${ReaquiredBook.publisher}" /></td>
						<td>
							<fmt:parseDate var="date" value="${ReaquiredBook.requiredDate}" pattern="yyyy-MM-dd HH:mm:ss" />
							<fmt:formatDate pattern = "yyyy年MM月dd日" value = "${date}" />
						</td>
						<td>
							<c:if test="${ReaquiredBook.showing == 0 }">
								<input type="hidden" name="flag" id="flag" value="1">
								<input type="checkbox" name="receiveId" id="receiveId" value="${ReaquiredBook.id}">
							</c:if>
							<c:if test="${ReaquiredBook.showing != 0 }">
								<c:out value="既読"></c:out>
							</c:if>
						</td>
						<td>
							<c:if test="${ReaquiredBook.showing == 1 }">
								<input type="hidden" name="flag" id="flag" value="0">
								<input type="checkbox" name="receiveId2" id="receiveId2" value="${ReaquiredBook.id}">
							</c:if>
							<c:if test="${ReaquiredBook.showing != 1 }">
								<c:out value="未読なう"></c:out>
							</c:if>
						</td>
						<td>
							<c:if test="${ReaquiredBook.showing == 1 }">
								<input type="checkbox" name="deleteId" value="${ReaquiredBook.id}">
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
	</form>
	</c:if>
	<c:remove var="errorMessages" scope="session"/>
</body>
</html>