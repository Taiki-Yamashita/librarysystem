<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>検索</title>
		<link href="./css/style.css" rel="stylesheet" type="text/css">
	</head>
	<body>
		<h2>検索</h2>

		<!--
			AND検索OR検索
		 -->

		<a href = "./">トップ</a>
		<a href = "./favorite">お気に入り</a>
		<a href = "./require">本のリクエスト</a>
		<a href = "./admin/manage">管理画面</a>
		<a href = "./introduction">本の紹介</a><br/>

		<hr width="1500px">

		<p>◎検索</p>
		<form action="./search" method="GET">
			<table>
				<tr>
					<td>【状態】</td>
					<td>
						<c:if test="${empty bookStatus}">
							<input type="radio" name="bookStatus" value="1" checked>全て
							<input type="radio" name="bookStatus" value="2">棚保管中
							<input type="radio" name="bookStatus" value="3">貸出中
						</c:if>
						<c:if test="${not empty bookStatus}">
							<c:if test="${bookStatus == 1}"><input type="radio" name="bookStatus" value="1" checked>全て</c:if>
							<c:if test="${bookStatus != 1}"><input type="radio" name="bookStatus" value="1">全て</c:if>
							<c:if test="${bookStatus == 2}"><input type="radio" name="bookStatus" value="2" checked>棚保管中</c:if>
							<c:if test="${bookStatus != 2}"><input type="radio" name="bookStatus" value="2">棚保管中</c:if>
							<c:if test="${bookStatus == 3}"><input type="radio" name="bookStatus" value="3" checked>貸出中</c:if>
							<c:if test="${bookStatus != 3}"><input type="radio" name="bookStatus" value="3">貸出中</c:if>
						</c:if>
					</td>
				</tr>
			</table>
			<table>
				<tr>
					<td>
						<select name="selectBox">
							<c:if test="${not empty selectBox}">
								<option value="${selectBoxId}">${selectBox}</option>
							</c:if>
							<c:if test="${selectBoxId != 1}"><option value="1">全て</option></c:if>
							<c:if test="${selectBoxId != 2}"><option value="2">書籍</option></c:if>
							<c:if test="${selectBoxId != 3}"><option value="3">著者</option></c:if>
							<c:if test="${selectBoxId != 4}"><option value="4">出版社</option></c:if>
							<c:if test="${selectBoxId != 5}"><option value="5">ISBN番号</option></c:if>
						</select>が
					</td>
					<td>
						<c:if test="${not empty freeWord}">
							<input type="text" name="freeWord" value="${freeWord}"/>
						</c:if>
						<c:if test="${empty freeWord}">
							<input type="text" name="freeWord" placeholder="未記入で全て検索"/>
						</c:if>
					</td>
					<td>
						<select name="condition">
							<c:if test="${not empty condition}">
								<c:if test="${condition == 1}"><option value="1">を含む</option></c:if>
								<c:if test="${condition == 2}"><option value="2">から始まる</option></c:if>
								<c:if test="${condition == 3}"><option value="3">で終わる</option></c:if>
								<c:if test="${condition == 4}"><option value="4">と一致する</option></c:if>

							</c:if>
							<c:if test="${condition != 1}"><option value="1">を含む</option></c:if>
							<c:if test="${condition != 2}"><option value="2">から始まる</option></c:if>
							<c:if test="${condition != 3}"><option value="3">で終わる</option></c:if>
							<c:if test="${condition != 4}"><option value="4">と一致する</option></c:if>
						</select>
					</td>
				</tr>
			</table>

			<table>
				<tr>
					<td>
						<c:forEach items="${libraryList}" var="libraryName" varStatus="libraryCount">
							<%session.setAttribute("checkLibrary", 0);%>
							<c:forEach items="${libraries}" var="library">
								<c:if test="${library == libraryCount.count}">
									<%session.setAttribute("checkLibrary",1);%>
									<input type="checkbox" name="library${libraryCount.count}" value="${libraryCount.count}" checked="checked">${libraryName.name}
								</c:if>
							</c:forEach>
							<c:if test="${checkLibrary == 0}">
								<input type="checkbox" name="library${libraryCount.count}" value="${libraryCount.count}">${libraryName.name}
							</c:if>
						</c:forEach>
					</td>
				</tr>
				<tr>
					<td>
						<%
							String[] checkBoxCategoryNumbers = {"1","2","3","4","5","6","7","8","9"};
							session.setAttribute("checkBoxCategoryNumbers", checkBoxCategoryNumbers);
						%>
						<c:forEach items="${checkBoxCategoryNumbers}" var="checkBoxCategoryNumber">
							<%session.setAttribute("checkCategory", 0);%>
							<c:forEach items="${categories}" var="category">
								<c:if test="${category == '文学' && checkBoxCategoryNumber == 1}">
									<%session.setAttribute("checkCategory",1);%>
									<input type="checkbox" name="category1" value="1" checked="checked">文学
								</c:if>
								<c:if test="${category == '経済' && checkBoxCategoryNumber == 2}">
									<%session.setAttribute("checkCategory",1);%>
									<input type="checkbox" name="category2" value="2" checked="checked">経済
								</c:if>
								<c:if test="${category == '芸能' && checkBoxCategoryNumber == 3}">
									<%session.setAttribute("checkCategory",1);%>
									<input type="checkbox" name="category3" value="3" checked="checked">芸能
								</c:if>
								<c:if test="${category == '歴史' && checkBoxCategoryNumber == 4}">
									<%session.setAttribute("checkCategory",1);%>
									<input type="checkbox" name="category4" value="4" checked="checked">歴史
								</c:if>
								<c:if test="${category == '学問' && checkBoxCategoryNumber == 5}">
									<%session.setAttribute("checkCategory",1);%>
									<input type="checkbox" name="category5" value="5" checked="checked">学問
								</c:if>
								<c:if test="${category == '政治' && checkBoxCategoryNumber == 6}">
									<%session.setAttribute("checkCategory",1);%>
									<input type="checkbox" name="category6" value="6" checked="checked">政治
								</c:if>
								<c:if test="${category == '暮らし' && checkBoxCategoryNumber == 7}">
									<%session.setAttribute("checkCategory",1);%>
									<input type="checkbox" name="category7" value="7" checked="checked">暮らし
								</c:if>
								<c:if test="${category == '教育' && checkBoxCategoryNumber == 8}">
									<%session.setAttribute("checkCategory",1);%>
									<input type="checkbox" name="category8" value="8" checked="checked">教育
								</c:if>
								<c:if test="${category == 'SF' && checkBoxCategoryNumber == 9}">
									<%session.setAttribute("checkCategory",1);%>
									<input type="checkbox" name="category9" value="9" checked="checked">SF
								</c:if>
							</c:forEach>

							<c:if test="${checkCategory == 0 && checkBoxCategoryNumber == 1}">
								<input type="checkbox" name="category1" value="1">文学
							</c:if>
							<c:if test="${checkCategory == 0 && checkBoxCategoryNumber == 2}">
								<input type="checkbox" name="category2" value="2">経済
							</c:if>
							<c:if test="${checkCategory == 0 && checkBoxCategoryNumber == 3}">
								<input type="checkbox" name="category3" value="3">芸能
							</c:if>
							<c:if test="${checkCategory == 0 && checkBoxCategoryNumber == 4}">
								<input type="checkbox" name="category4" value="4">歴史
							</c:if>
							<c:if test="${checkCategory == 0 && checkBoxCategoryNumber == 5}">
								<input type="checkbox" name="category5" value="5">学問
							</c:if>
							<c:if test="${checkCategory == 0 && checkBoxCategoryNumber == 6}">
								<input type="checkbox" name="category6" value="6">政治
							</c:if>
							<c:if test="${checkCategory == 0 && checkBoxCategoryNumber == 7}">
								<input type="checkbox" name="category7" value="7">暮らし
							</c:if>
							<c:if test="${checkCategory == 0 && checkBoxCategoryNumber == 8}">
								<input type="checkbox" name="category8" value="8">教育
							</c:if>
							<c:if test="${checkCategory == 0 && checkBoxCategoryNumber == 9}">
								<input type="checkbox" name="category9" value="9">SF
							</c:if>
						</c:forEach>
					</td>
				</tr>
				<tr>
					<td>
						<%
							String[] checkBoxTypeNumbers = {"1","2","3","4"};
							session.setAttribute("checkBoxTypeNumbers", checkBoxTypeNumbers);
						%>
						<c:forEach items="${checkBoxTypeNumbers}" var="checkBoxTypeNumber">
							<%session.setAttribute("checkType", 0);%>
							<c:forEach items="${types}" var="type">
								<c:if test="${type == '文庫' && checkBoxTypeNumber == 1}">
									<%session.setAttribute("checkType",1);%>
									<input type="checkbox" name="type1" value="1" checked="checked">文庫
								</c:if>
								<c:if test="${type == '新書' && checkBoxTypeNumber == 2}">
									<%session.setAttribute("checkType",1);%>
									<input type="checkbox" name="type2" value="2" checked="checked">新書
								</c:if>
								<c:if test="${type == '雑誌' && checkBoxTypeNumber == 3}">
									<%session.setAttribute("checkType",1);%>
									<input type="checkbox" name="type3" value="3" checked="checked">雑誌
								</c:if>
								<c:if test="${type == 'コミックス' && checkBoxTypeNumber == 4}">
									<%session.setAttribute("checkType",1);%>
									<input type="checkbox" name="type4" value="4" checked="checked">コミックス
								</c:if>
							</c:forEach>

							<c:if test="${checkType == 0 && checkBoxTypeNumber == 1}">
								<input type="checkbox" name="type1" value="1">文庫
							</c:if>
							<c:if test="${checkType == 0 && checkBoxTypeNumber == 2}">
								<input type="checkbox" name="type2" value="2">新書
							</c:if>
							<c:if test="${checkType == 0 && checkBoxTypeNumber == 3}">
								<input type="checkbox" name="type3" value="3">雑誌
							</c:if>
							<c:if test="${checkType == 0 && checkBoxTypeNumber == 4}">
								<input type="checkbox" name="type4" value="4">コミックス
							</c:if>
						</c:forEach>
					</td>
				</tr>
				<tr>
					<td>
						で
						<input type="hidden" name="isSearching" value="1">
						<input type="hidden" name="sort" value="0">
						<input type="submit" value="絞り込む">
					</td>
				</tr>
			</table>
		</form>

		<hr width="1500px">

		<c:if test="${not empty isSearching}">
			<p>◎並び替え</p>
			<form action="./search" method="GET">
				<c:if test="${sort == 1}"><input type="radio" name="sort" value="1" checked>新しい順</c:if>
				<c:if test="${sort != 1}">
					<c:if test="${sort == 0}"><input type="radio" name="sort" value="1" checked>新しい順</c:if>
					<c:if test="${sort != 0}"><input type="radio" name="sort" value="1">新しい順</c:if>
				</c:if>

				<c:if test="${sort == 2}"><input type="radio" name="sort" value="2" checked>古い順</c:if>
				<c:if test="${sort != 2}"><input type="radio" name="sort" value="2">古い順</c:if>

				<c:if test="${sort == 3}"><input type="radio" name="sort" value="3" checked>書籍順</c:if>
				<c:if test="${sort != 3}"><input type="radio" name="sort" value="3">書籍順</c:if>

				<c:if test="${sort == 4}"><input type="radio" name="sort" value="4" checked>著者順</c:if>
				<c:if test="${sort != 4}"><input type="radio" name="sort" value="4">著者順</c:if>

				<c:if test="${sort == 5}"><input type="radio" name="sort" value="5" checked>カテゴリ順</c:if>
				<c:if test="${sort != 5}"><input type="radio" name="sort" value="5">カテゴリ順</c:if>

				<c:if test="${sort == 6}"><input type="radio" name="sort" value="6" checked>出版社順</c:if>
				<c:if test="${sort != 6}"><input type="radio" name="sort" value="6">出版社順</c:if>

				<!-- freeWord -->
				<input type="hidden" name="selectBox" value="${selectBoxId}">
				<input type="hidden" name="freeWord" value="${freeWord}">
				<input type="hidden" name="condition" value="${condition}">

				<!-- refine -->
				<c:forEach items="${libraries}" var="library" varStatus="status">
					<c:if test="${library == 1}"><input type="hidden" name="library1" value="1"></c:if>
					<c:if test="${library == 2}"><input type="hidden" name="library2" value="2"></c:if>
					<c:if test="${library == 3}"><input type="hidden" name="library3" value="3"></c:if>
					<c:if test="${library == 4}"><input type="hidden" name="library4" value="4"></c:if>
					<c:if test="${library == 5}"><input type="hidden" name="library5" value="5"></c:if>
				</c:forEach>
				<c:forEach items="${categories}" var="category" varStatus="status">
					<c:if test="${category == '文学'}"><input type="hidden" name="category1" value="1"></c:if>
					<c:if test="${category == '経済'}"><input type="hidden" name="category2" value="2"></c:if>
					<c:if test="${category == '芸能'}"><input type="hidden" name="category3" value="3"></c:if>
					<c:if test="${category == '歴史'}"><input type="hidden" name="category4" value="4"></c:if>
					<c:if test="${category == '学問'}"><input type="hidden" name="category5" value="5"></c:if>
					<c:if test="${category == '政治'}"><input type="hidden" name="category6" value="6"></c:if>
					<c:if test="${category == '暮らし'}"><input type="hidden" name="category7" value="7"></c:if>
					<c:if test="${category == '教育'}"><input type="hidden" name="category8" value="8"></c:if>
					<c:if test="${category == 'SF'}"><input type="hidden" name="category9" value="9"></c:if>
				</c:forEach>
				<c:forEach items="${types}" var="type" varStatus="status">
					<c:if test="${type == '文庫'}"><input type="hidden" name="type1" value="1"></c:if>
					<c:if test="${type == '新書'}"><input type="hidden" name="type2" value="2"></c:if>
					<c:if test="${type == '雑誌'}"><input type="hidden" name="type3" value="3"></c:if>
					<c:if test="${type == 'コミックス'}"><input type="hidden" name="type4" value="4"></c:if>
				</c:forEach>

				<input type="hidden" name="isSearching" value="1">
				<input type="hidden" name="bookStatus" value="${bookStatus}">

				<input type="submit" value="並び替える">
			</form>
			<hr width="1500px">
		</c:if>

		<p><input type="button" onclick="location.href='./search'"value="クリア"></p>

		<c:if test="${ not empty errorMessages }">
			<c:forEach items="${errorMessages}" var="message">
				<font color="#ff0000"><c:out value="${message}" /></font><br>
			</c:forEach>
			<p><a href="./require">本をリクエストする</a></p>
		</c:if>
		<c:if test="${ not empty loginErrorMessages }">
			<c:forEach items="${loginErrorMessages}" var="message">
				<font color="#ff0000"><c:out value="${message}" /></font><br>
			</c:forEach>
		</c:if>

		<c:if test="${ empty errorMessages && not empty booksCount}">
			検索結果は
			<font color="#ff0000"><c:out value="${booksCount}件" /></font>
			です
		</c:if>

		<c:if test="${not empty books}">
			<table border="1">
				<tr>
					<th>書籍</th><th>著者</th><th>出版日</th><th>カテゴリ</th><th>種類</th>
					<th>図書館</th><th>状態</th><th>ISBN番号</th><th>予約</th><th>お気に入り</th>
				</tr>
				<c:forEach items="${books}" var="book" varStatus="count">
					<c:if test="${book.id != 0}">
						<c:if test="${count.index >= (pageNumber-1)*3 && count.index <= (pageNumber*3)-1}">
							<tr>
								<td><c:out value="${book.name}"/></td>
								<td><c:out value="${book.author}"/></td>
								<td>
									<fmt:parseDate var="date" value="${book.publishedDate}" pattern="yyyy-MM-dd HH:mm:ss" />
									<fmt:formatDate pattern = "yyyy年MM月dd日" value = "${date}" />
								</td>
								<td><c:out value="${book.category}"/></td>
								<td><c:out value="${book.type}"/></td>
								<td>
									<c:forEach items="${libraryNames}" var="library">
										<c:if test="${library.id == book.libraryId}">
											<c:out value="${library.name}"/>
										</c:if>
									</c:forEach>
								</td>
								<td>
									<c:if test="${book.keeping ==1}">保管中</c:if>
									<c:if test="${book.lending ==1}">貸出中</c:if>
									<c:if test="${book.disposing ==1}">整理中</c:if>
								</td>
								<td><c:out value="${book.isbnId}"/></td>
								<td>
									<c:forEach items="${isReserving}" var="reserve">
										<c:if test="${reserve == count.index}">
											<form action="./reservingBook" method="POST">
												<!-- freeWord -->
												<input type="hidden" name="selectBox" value="${selectBoxId}">
												<input type="hidden" name="freeWord" value="${freeWord}">
												<input type="hidden" name="condition" value="${condition}">

												<!-- refine -->
												<c:forEach items="${libraries}" var="library" varStatus="status">
													<c:if test="${library == 1}"><input type="hidden" name="library1" value="1"></c:if>
													<c:if test="${library == 2}"><input type="hidden" name="library2" value="2"></c:if>
													<c:if test="${library == 3}"><input type="hidden" name="library3" value="3"></c:if>
													<c:if test="${library == 4}"><input type="hidden" name="library4" value="4"></c:if>
													<c:if test="${library == 5}"><input type="hidden" name="library5" value="5"></c:if>
												</c:forEach>
												<c:forEach items="${categories}" var="category" varStatus="status">
													<c:if test="${category == '文学'}"><input type="hidden" name="category1" value="1"></c:if>
													<c:if test="${category == '経済'}"><input type="hidden" name="category2" value="2"></c:if>
													<c:if test="${category == '芸能'}"><input type="hidden" name="category3" value="3"></c:if>
													<c:if test="${category == '歴史'}"><input type="hidden" name="category4" value="4"></c:if>
													<c:if test="${category == '学問'}"><input type="hidden" name="category5" value="5"></c:if>
													<c:if test="${category == '政治'}"><input type="hidden" name="category6" value="6"></c:if>
													<c:if test="${category == '暮らし'}"><input type="hidden" name="category7" value="7"></c:if>
													<c:if test="${category == '教育'}"><input type="hidden" name="category8" value="8"></c:if>
													<c:if test="${category == 'SF'}"><input type="hidden" name="category9" value="9"></c:if>
												</c:forEach>
												<c:forEach items="${types}" var="type" varStatus="status">
													<c:if test="${type == '文庫'}"><input type="hidden" name="type1" value="1"></c:if>
													<c:if test="${type == '新書'}"><input type="hidden" name="type2" value="2"></c:if>
													<c:if test="${type == '雑誌'}"><input type="hidden" name="type3" value="3"></c:if>
													<c:if test="${type == 'コミックス'}"><input type="hidden" name="type4" value="4"></c:if>
												</c:forEach>

												<input type="hidden" name="isSearching" value="1">
												<input type="hidden" name="bookStatus" value="${bookStatus}">
												<input type="hidden" name="sort" value="${sort}" >
												<input type="hidden" value="${pageNumber}" name="pageNumber">
												<input type = "hidden" name = "fromSearch" value="1">

												<input type = "hidden" name = "bookId" value = "${book.id}" >
												<input type = "hidden" id = "libraryId" name = "libraryId" value = "${book.libraryId}" >
												<input type = "hidden" id = "${loginUser.id}" name = "userId" value = "${loginUser.id}"  >
												<input type = "hidden" name = "num" value = 1>
												<input type = "hidden" name = "reservation" value="${book.id}">

												<c:if test="${empty loginUser}"><input type="hidden" name="notLogin" value="1"></c:if>
												<input type="submit"  value="予約" />
											</form>
										</c:if>
									</c:forEach>
								</td>
								<td>
									<c:forEach items="${isFavorites}" var="favorite">
										<c:if test="${favorite == count.index}">
											<form action="./favorite" method="POST">
												<!-- freeWord -->
												<input type="hidden" name="selectBox" value="${selectBoxId}">
												<input type="hidden" name="freeWord" value="${freeWord}">
												<input type="hidden" name="condition" value="${condition}">

												<!-- refine -->
												<c:forEach items="${libraries}" var="library" varStatus="status">
													<c:if test="${library == 1}"><input type="hidden" name="library1" value="1"></c:if>
													<c:if test="${library == 2}"><input type="hidden" name="library2" value="2"></c:if>
													<c:if test="${library == 3}"><input type="hidden" name="library3" value="3"></c:if>
													<c:if test="${library == 4}"><input type="hidden" name="library4" value="4"></c:if>
													<c:if test="${library == 5}"><input type="hidden" name="library5" value="5"></c:if>
												</c:forEach>
												<c:forEach items="${categories}" var="category" varStatus="status">
													<c:if test="${category == '文学'}"><input type="hidden" name="category1" value="1"></c:if>
													<c:if test="${category == '経済'}"><input type="hidden" name="category2" value="2"></c:if>
													<c:if test="${category == '芸能'}"><input type="hidden" name="category3" value="3"></c:if>
													<c:if test="${category == '歴史'}"><input type="hidden" name="category4" value="4"></c:if>
													<c:if test="${category == '学問'}"><input type="hidden" name="category5" value="5"></c:if>
													<c:if test="${category == '政治'}"><input type="hidden" name="category6" value="6"></c:if>
													<c:if test="${category == '暮らし'}"><input type="hidden" name="category7" value="7"></c:if>
													<c:if test="${category == '教育'}"><input type="hidden" name="category8" value="8"></c:if>
													<c:if test="${category == 'SF'}"><input type="hidden" name="category9" value="9"></c:if>
												</c:forEach>
												<c:forEach items="${types}" var="type" varStatus="status">
													<c:if test="${type == '文庫'}"><input type="hidden" name="type1" value="1"></c:if>
													<c:if test="${type == '新書'}"><input type="hidden" name="type2" value="2"></c:if>
													<c:if test="${type == '雑誌'}"><input type="hidden" name="type3" value="3"></c:if>
													<c:if test="${type == 'コミックス'}"><input type="hidden" name="type4" value="4"></c:if>
												</c:forEach>

												<input type="hidden" name="isSearching" value="1">
												<input type="hidden" name="bookStatus" value="${bookStatus}">
												<input type="hidden" name="sort" value="${sort}" >

												<input type="hidden" value="${loginUser.id}" name="userId">
												<input type="hidden" value="${book.id}" name="bookId">
												<input type="hidden" value="${pageNumber}" name="pageNumber">
												<c:if test="${empty loginUser}"><input type="hidden" name="notLogin" value="1"></c:if>
												<input type="submit"  value="お気に入り" />
											</form>
										</c:if>
									</c:forEach>
								</td>
							</tr>
						</c:if>
					</c:if>
				</c:forEach>
			</table>
			<table>
				<tr>
					<c:forEach items="${pageCountList}" var="pageCount">
						<form action="./search" method="GET">
							<td>
								<c:if test="${pageNumber == pageCount}"><c:out value="${pageCount}"></c:out></c:if>
								<c:if test="${pageNumber != pageCount}">
									<input type="submit" value="${pageCount}"/>
									<input type="hidden" name="pageNumber" value="${pageCount}">
								</c:if>
							</td>
							<c:if test="${not empty isSearching}">
								<input type="hidden" name="isSearching" value="1">

								<input type="hidden" name="selectBox" value="${selectBoxId}">
								<input type="hidden" name="freeWord" value="${freeWord}">
								<input type="hidden" name="condition" value="${condition}">

								<!-- refine -->
								<c:forEach items="${libraries}" var="library" varStatus="status">
									<c:if test="${library == 1}"><input type="hidden" name="library1" value="1"></c:if>
									<c:if test="${library == 2}"><input type="hidden" name="library2" value="2"></c:if>
									<c:if test="${library == 3}"><input type="hidden" name="library3" value="3"></c:if>
									<c:if test="${library == 4}"><input type="hidden" name="library4" value="4"></c:if>
									<c:if test="${library == 5}"><input type="hidden" name="library5" value="5"></c:if>
								</c:forEach>
								<c:forEach items="${categories}" var="category" varStatus="status">
									<c:if test="${category == '文学'}"><input type="hidden" name="category1" value="1"></c:if>
									<c:if test="${category == '経済'}"><input type="hidden" name="category2" value="2"></c:if>
									<c:if test="${category == '芸能'}"><input type="hidden" name="category3" value="3"></c:if>
									<c:if test="${category == '歴史'}"><input type="hidden" name="category4" value="4"></c:if>
									<c:if test="${category == '学問'}"><input type="hidden" name="category5" value="5"></c:if>
									<c:if test="${category == '政治'}"><input type="hidden" name="category6" value="6"></c:if>
									<c:if test="${category == '暮らし'}"><input type="hidden" name="category7" value="7"></c:if>
									<c:if test="${category == '教育'}"><input type="hidden" name="category8" value="8"></c:if>
									<c:if test="${category == 'SF'}"><input type="hidden" name="category9" value="9"></c:if>
								</c:forEach>
								<c:forEach items="${types}" var="type" varStatus="status">
									<c:if test="${type == '文庫'}"><input type="hidden" name="type1" value="1"></c:if>
									<c:if test="${type == '新書'}"><input type="hidden" name="type2" value="2"></c:if>
									<c:if test="${type == '雑誌'}"><input type="hidden" name="type3" value="3"></c:if>
									<c:if test="${type == 'コミックス'}"><input type="hidden" name="type4" value="4"></c:if>
								</c:forEach>

							</c:if>
							<input type="hidden" name="sort" value="${sort}">
							<input type="hidden" name="bookStatus" value="${bookStatus}">
						</form>
					</c:forEach>
				</tr>
			</table>
		</c:if>

		<!-- エラーメッセージ -->
		<c:remove var="errorMessages" scope="session"/>
		<c:remove var="loginErrorMessages" scope="session"/>

		<!-- 絞込み検索 -->
		<c:remove var="checkLibrary" scope="session"/>
		<c:remove var="checkBoxCategoryNumber" scope="session"/>
		<c:remove var="checkCategory" scope="session"/>
		<c:remove var="checkBoxTypeNumber" scope="session"/>
		<c:remove var="checkType" scope="session"/>

	</body>
</html>