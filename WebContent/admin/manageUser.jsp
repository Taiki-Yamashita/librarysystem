<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<title>ユーザー編集</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="./css/styleTaiki.css" rel="stylesheet" type="text/css">
		<script type="text/javascript">
		function check(){

			if(window.confirm('よろしいですか？')){ // 確認ダイアログを表示

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
		<p>ユーザー編集</p>
		<p><a href="./manage">管理画面</a></p>

		<p>◎検索</p>

		<table border="1">
			<tr>
				<td>
					<form action="./manageUser" method="GET">
						<table>
							<tr>
								<td>
									電話番号や住所など
									<c:if test="${not empty freeWord}">
										<input type="text" name="freeWord" value="${freeWord}"/>
									</c:if>
									<c:if test="${empty freeWord}">
										<input type="text" name="freeWord" placeholder="未記入で全て検索"/>
									</c:if>
								</td>
							</tr>
						</table>

						<table>
							<tr>
								<td>登録図書館</td>
								<td>
									<c:if test="${empty selectedLibrary}">
										<input type="radio" name="selectedLibrary" value="0" checked>全て
										<c:forEach items="${libraries}" var="library" varStatus="status">
											<input type="radio" name="selectedLibrary" value="${status.count}">${library.name}
										</c:forEach>
									</c:if>
									<c:if test="${not empty selectedLibrary}">
										<c:if test="${selectedLibrary == 0}"><input type="radio" name="selectedLibrary" value="0" checked>全て</c:if>
										<c:if test="${selectedLibrary != 0}"><input type="radio" name="selectedLibrary" value="0">全て</c:if>
										<c:forEach items="${libraries}" var="library" varStatus="status">
											<c:if test="${selectedLibrary == status.count}">
												<input type="radio" name="selectedLibrary" value="${status.count}" checked>${library.name}
											</c:if>
											<c:if test="${selectedLibrary != status.count}">
												<input type="radio" name="selectedLibrary" value="${status.count}">${library.name}
											</c:if>
										</c:forEach>
									</c:if>
								</td>
							</tr>
						</table>
						<input type="hidden" name="isSearching" value="1">
						<input type="submit" value="絞込み">
						<input type="button" onclick="location.href='./manageUser'"value="クリア">
					</form>
				</td>
				<td>
					<form action="addUser" method ="get">
						<input type = "submit" value = "ユーザーの追加" />
					</form>
				</td>
			</tr>
		</table>


		<hr width="1500px">

		<c:if test="${ not empty errorMessages }">
			<c:forEach items="${errorMessages}" var="message">
				<font color="#ff0000"><c:out value="${message}" /></font><br>
			</c:forEach>
		</c:if>

		<c:if test="${not empty users}">
			<table>
				<tr>
					<th>ID</th>
					<th>ログインID</th>
					<th>パスワード</th>
					<th>名前</th>
					<th>住所</th>
					<th>TEL</th>
					<th>メールアドレス</th>
					<th>ポイント</th>
					<th>登録日</th>
					<th>図書館</th>
					<th>編集</th>
					<th>停止</th>
				</tr>
				<c:forEach items="${users}" var="user">
					<tr>
						<td>${user.id}</td>
						<td>${user.loginId}</td>
						<td>${user.password}</td>
						<td>${user.name}</td>
						<td>${user.address}</td>
						<td>${user.tel}</td>
						<td>${user.mail}</td>
						<td>${user.point}</td>

						<td>
							<fmt:parseDate var="date" value="${user.registerDate}" pattern="yyyy-MM-dd HH:mm:ss" />
							<fmt:formatDate pattern = "yyyy年MM月dd日" value = "${date}" />
						</td>
						<td>
							<c:forEach items="${libraries}" var="library">
								<c:if test="${user.libraryId ==library.id}">
									<option value="${library.id}">${library.name}</option>
								</c:if>
							</c:forEach>
						</td>
						<td>
			   	 			<form action="editUser" method="get">
					   	 		<input type="hidden" name="id" value="${user.id }" >
					   	 		<input type = "hidden" name = "time" value = "${user.registerDate }">
					   	 		<input type="submit" value="編集" />
			   	 			</form>
					 	</td>
					 	<td>
			   	 			<form action = "stopUser" method = "post" onSubmit="return check()">
			   	 				<input type = "hidden" name = "id" value = "${user.id }" >
			   	 				<input type = "hidden" name = "time" value = "${user.registerDate }">
								<c:if test = "${user.stopping == 0 }">
									<input type = "hidden" name = "num" value = 1 >
									<input type = "submit" value = "停止" />
								</c:if>
								<c:if test = "${user.stopping == 1 }">
									<input type = "hidden" name = "num" value = 0>
									<input type = "submit" value = "復活" />
								</c:if>
			   	 			</form>
		   	 			</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>

		<!-- エラーメッセージ -->
		<c:remove var="errorMessages" scope="session"/>
	</body>
</html>