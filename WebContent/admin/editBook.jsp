<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>本の編集</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>


<form action="editBook"method="post"><br />

	<input name="book_id" type="hidden" value="${editBook.id}"/>

	<label for="name">名前</label><br>
	<input name="name" id="name" value="${editBook.name }"/><br />

	<label for="author">著者</label><br>
	<input name="author" id="author" value="${editBook.author }"/><br />

	<label for="publisher">出版社</label><br>
	<input name="publisher" id="publisher"/><br />

	<label for="category">カテゴリ</label><br>
	<input name="category" id="category"/><br />

		<label for="type">種類</label><br>
	<input name="type" id="type" value="${editBook.type }"/><br />

	<label for="libraryId">図書館</label><br>
	<input name="libraryId" id="libraryId"/><br />

	<label for="shelfId">棚番号</label><br>
	<input name="shelfId" id="shelfId"/><br />

	<label for="isbnId">ISBN番号</label><br>
	<input name="isbnId" id="isbnId" value="${editBook.isbnId }"/><br />

	<label for="publishedDate">出版日</label><br>
	<input name="publishedDate" id="publishedDate" value="${editBook.publishedDate }"/><br />

	<label for="keeping">保管中</label><br>
	<input name="keeping" id="keeping"/><br />

	<label for="lending">貸出中</label><br>
	<input name="lending" id="lending"/><br />

		<label for="reserving">予約中</label><br>
	<input name="reserving" id="reserving" value="${editBook.reserving }"/><br />

	<label for="disposing">整理中</label><br>
	<input name="disposing" id="disposing" value="${editBook.disposing }"/><br />


	<br><input type="submit" value="投稿">
</form>