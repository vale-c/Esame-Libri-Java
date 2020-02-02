<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="it.unipr.informatica.modello.Book"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book List</title>
<link rel="stylesheet" type="text/css" href="style.css" />
</head>
<script>
function delete_book(id) {
	var ok = confirm("Are you sure?");
	
	if(!ok)
		return;
	
	var node = document.getElementById("delete_id");
	
	node.value = id;
	
	node = document.getElementById("form_delete");
	
	node.submit();
}

function modify_book(id) {
	var node = document.getElementById("get_id");
	
	node.value = id;
	
	node = document.getElementById("form_get");
	
	node.submit();
}
</script>
	
<body>
	<h3 class="book-list-header">Book List</h3>
	<p class="main-actions">
	  <a href="/EsameLibri">Home</a> 
	  <a href="add_book.jsp">Add book</a>
	</p>
	<div id="container">
		<table class="book_table">
			<thead>
				<tr>
					<td> </td>
					<td> </td>
					<td>ID</td>
					<td>Author</td>
					<td>Title</td>
					<td>Number Of Pages</td>
				</tr>
			</thead>
			<tbody>
				<%
					@SuppressWarnings("unchecked")
					List<Book> allBooks = (List<Book>) session.getAttribute("allBooks");

					if (allBooks == null)
						allBooks = new ArrayList<>();

					for (Book book : allBooks) {
				%>
				<tr>
					<td><a href="javascript:modify_book(<%= book.getId() %>)">Modify</a></td>
					<td><a href="javascript:delete_book(<%= book.getId() %>)">Delete</a></td>
					<td><%= book.getId() %></td>
					<td><%= book.getAuthor() %></td>
					<td><%= book.getTitle() %></td>
					<td><%= book.getNumOfPages() %></td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
	</div>
	
	<form id="form_delete" action="delete_book" method="post" hidden="hidden">
	  <input id="delete_id" name="delete_id" type="text" value="" />
	</form>
	<form id="form_get" action="get_book" method="post" hidden="hidden">
	  <input id="get_id" name="get_id" type="text" value="" />
	</form>
</body>
</html>