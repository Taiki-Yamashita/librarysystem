<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>本登録</title>
</head>
<body>
	<br>
	<a href = "./manageBook">本の情報管理</a>


<c:if test="${ not empty errorMessages }">
	<div class="errorMessages">
		<ul>
			<c:forEach items="${errorMessages}" var="message">
				<li><c:out value="${message}" />
				</c:forEach>
				</ul>
	</div>
	<c:remove var="errorMessages" scope="session"/>
</c:if>




<form action = "./addBook"method = "post"><br />
	<label for = "name">名前</label><br>
	<input name = "name" id = "name" value = "${newBook.name }"/><br />

	<label for = "author">著者</label><br>
	<input name = "author" id = "author" value = "${newBook.author }"/><br />

	<label for = "publisher">出版社</label><br>
	<input name = "publisher" id = "publisher" value = "${newBook.publisher }"/><br />


	<label for = "category">カテゴリ</label><br>
						<%
							String[] checkBoxCategoryNumbers = {"1","2","3","4","5","6","7","8","9"};
							session.setAttribute("checkBoxCategoryNumbers", checkBoxCategoryNumbers);
						%>
						<c:forEach items="${checkBoxCategoryNumbers}" var="checkBoxCategoryNumber">
							<%session.setAttribute("checkCategory", 0);%>
							<c:forEach items="${categories}" var="category">
								<c:if test="${category == '文学' && checkBoxCategoryNumber == 1}">
									<%session.setAttribute("checkCategory",1);%>
									<input type="radio" name="category" value="1"  >文学
								</c:if>
								<c:if test="${category == '経済' && checkBoxCategoryNumber == 2}">
									<%session.setAttribute("checkCategory",1);%>
									<input type="radio" name="category" value="2" >経済
								</c:if>
								<c:if test="${category == '芸能' && checkBoxCategoryNumber == 3}">
									<%session.setAttribute("checkCategory",1);%>
									<input type="radio" name="category" value="3" >芸能
								</c:if>
								<c:if test="${category == '歴史' && checkBoxCategoryNumber == 4}">
									<%session.setAttribute("checkCategory",1);%>
									<input type="radio" name="category" value="4" >歴史
								</c:if>
								<c:if test="${category == '学問' && checkBoxCategoryNumber == 5}">
									<%session.setAttribute("checkCategory",1);%>
									<input type="radio" name="category" value="5" >学問
								</c:if>
								<c:if test="${category == '政治' && checkBoxCategoryNumber == 6}">
									<%session.setAttribute("checkCategory",1);%>
									<input type="radio" name="category" value="6" >政治
								</c:if>
								<c:if test="${category == '暮らし' && checkBoxCategoryNumber == 7}">
									<%session.setAttribute("checkCategory",1);%>
									<input type="radio" name="category" value="7" >暮らし
								</c:if>
								<c:if test="${category == '教育' && checkBoxCategoryNumber == 8}">
									<%session.setAttribute("checkCategory",1);%>
									<input type="radio" name="category" value="8" >教育
								</c:if>
								<c:if test="${category == 'SF' && checkBoxCategoryNumber == 9}">
									<%session.setAttribute("checkCategory",1);%>
									<input type="radio" name="category" value="9" >SF
								</c:if>
							</c:forEach>

							<c:if test="${checkCategory == 0 && checkBoxCategoryNumber == 1}">
								<input type="radio" name="category" value="1" >文学
							</c:if>
							<c:if test="${checkCategory == 0 && checkBoxCategoryNumber == 2}">
								<input type="radio" name="category" value="2">経済
							</c:if>
							<c:if test="${checkCategory == 0 && checkBoxCategoryNumber == 3}">
								<input type="radio" name="category" value="3">芸能
							</c:if>
							<c:if test="${checkCategory == 0 && checkBoxCategoryNumber == 4}">
								<input type="radio" name="category" value="4">歴史
							</c:if>
							<c:if test="${checkCategory == 0 && checkBoxCategoryNumber == 5}">
								<input type="radio" name="category" value="5">学問
							</c:if>
							<c:if test="${checkCategory == 0 && checkBoxCategoryNumber == 6}">
								<input type="radio" name="category" value="6">政治
							</c:if>
							<c:if test="${checkCategory == 0 && checkBoxCategoryNumber == 7}">
								<input type="radio" name="category" value="7">暮らし
							</c:if>
							<c:if test="${checkCategory == 0 && checkBoxCategoryNumber == 8}">
								<input type="radio" name="category" value="8">教育
							</c:if>
							<c:if test="${checkCategory == 0 && checkBoxCategoryNumber == 9}">
								<input type="radio" name="category" value="9">SF
							</c:if>
						</c:forEach><br>




	<label for = "type">タイプ</label><br>
				<%
					String[] checkBoxTypeNumbers = {"1","2","3","4"};
					session.setAttribute("checkBoxTypeNumbers", checkBoxTypeNumbers);
				%>
						<c:forEach items="${checkBoxTypeNumbers}" var="checkBoxTypeNumber">
							<%session.setAttribute("checkType", 0);%>
							<c:forEach items="${types}" var="type">
								<c:if test="${type == '文庫' && checkBoxTypeNumber == 1}">
									<%session.setAttribute("checkType",1);%>
									<input type="radio" name="type" value="1">文庫
								</c:if>
								<c:if test="${type == '新書' && checkBoxTypeNumber == 2}">
									<%session.setAttribute("checkType",1);%>
									<input type="radio" name="type" value="2">新書
								</c:if>
								<c:if test="${type == '雑誌' && checkBoxTypeNumber == 3}">
									<%session.setAttribute("checkType",1);%>
									<input type="radio" name="type" value="3" >雑誌
								</c:if>
								<c:if test="${type == 'コミックス' && checkBoxTypeNumber == 4}">
									<%session.setAttribute("checkType",1);%>
									<input type="radio" name="type" value="4">コミックス
								</c:if>
							</c:forEach>

							<c:if test="${checkType == 0 && checkBoxTypeNumber == 1}">
								<input type="radio" name="type" value="1">文庫
							</c:if>
							<c:if test="${checkType == 0 && checkBoxTypeNumber == 2}">
								<input type="radio" name="type" value="2">新書
							</c:if>
							<c:if test="${checkType == 0 && checkBoxTypeNumber == 3}">
								<input type="radio" name="type" value="3">雑誌
							</c:if>
							<c:if test="${checkType == 0 && checkBoxTypeNumber == 4}">
								<input type="radio" name="type" value="4">コミックス
							</c:if>
						</c:forEach><br>




	<label for = "library">図書館</label><br>
	<select name="libraryId">
		<option value="0">選択してください</option>
			<c:forEach items="${libraries}" var="library">
			<c:if test="${newBook.libraryId == library.id }">
					<option selected value="${library.id}">${library.name } </option>
				</c:if>
				<c:if test="${newBook.libraryId != library.id }">
					<option  value="${library.id}">${library.name } </option>
				</c:if>
		</c:forEach>
	</select><br>

	<label for="shelfId">棚番号</label><br>
	<input name = "shelfId" id = "shelfId" value = "${newBook.shelfId }"/><br />

	<label for = "isbnId">ISBN番号</label><br>
	<input name = "isbnId" id = "isbnId" value = "${newBook.isbnId }"/><br />

	<label for = "publishedDate">出版日</label><br>
	<input name = "publishedDate" id = "publishedDate" value ="${newBook.publishedDate }"/><br />



	<input type="submit" value="登録" />

	</form>
</body>
</html>