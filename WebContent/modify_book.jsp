<!--
  modify_book.jsp
-->
<%@page import="it.unipr.informatica.modello.Book"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Esame Libri</title>
<link rel="stylesheet" type="text/css" href="style.css" />
<script>
function modify_book() {
	var node = document.getElementById("author");
	
	var s = node.value;
	
	if(s.length == 0 || s.length > 250) {
		alert("Invalid author provided");
		
		return;
	}

	node = document.getElementById("title");
	
	var s = node.value;
	
	if(s.length == 0 || s.length > 250) {
		alert("Invalid length provided");
		
		return;
	}
	
	node = document.getElementById("pages");
	
	var s = node.value;
	
	if(s.length == 0 || s.length > 5000) {
		alert("Invalid length provided");
		
		return;
	}

	node = document.getElementById("form");
	
	node.submit();
}
</script>
</head>
<body>
<% 
	Book book = (Book)session.getAttribute("modify_book");

	int id;
	int pages;
	
	String author;
	String title;
	
	if(book == null) {
		id = pages = 0;
		
		title = author = "";
		
	} else {
		id = book.getId();
		
		author = book.getAuthor();
		
		title = book.getTitle();
		
		pages = book.getNumOfPages();
	}
%>
<h1>Esame Libri</h1>
<h2>Modify Book</h2>
<p><a href="/EsameLibri">Home</a></p>
<form id="form" action="modify_book" method="post">
<table>
<tbody>
<tr>
<td>ID:</td> 
<td><input id="id" name="id" type="text" value="<%=id %>" readonly="readonly" /></td>
</tr>
<tr>
<td>Author:</td> 
<td><input id="author" name="author" type="text" value="<%= author %>" /></td>
</tr>
<tr>
<td>Title:</td>
<td><input id="title" name="title" type="text" value="<%= title %>" /></td>
</tr>
<tr>
<td>Number Of Pages:</td>
<td><input id="pages" name="pages" type="number" value="<%= pages %>" /></td>
</tr>
</tbody>
</table>
<p><a href="javascript:modify_book()">Modify Book</a></p>
</form>
</body>
</html>
