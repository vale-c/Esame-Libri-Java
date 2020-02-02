<!--
  add_book.jsp
-->

<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Esame Libri</title>
<link rel="stylesheet" type="text/css" href="style.css" />
</head>
<body>
<h1>Library</h1>
<h2>New Book</h2>
	<p><a href="/EsameLibri">Home</a></p>
	<form id="form" action="add_book" method="post">
		<table>
			<tbody>
				<tr>
					<td>Author:</td>
					<td><input id="author" name="author" type="text" value="" /></td>
				</tr>
				<tr>
					<td>Title:</td>
					<td><input id="title" name="title" type="text" value="" /></td>
				</tr>
				<tr>
					<td>Number of Pages:</td>
					<td><input id="pages" name="pages" type="number" value="" /></td>
				</tr>
			</tbody>
		</table>
	  <button type ="button">
	  	<a href="javascript:add_book()">Add book</a>
	  </button>
	</form>
	
<script>
function add_book() {
	
	var node = document.getElementById("author");
	
	var s = node.value;
	
	if(s.length == 0 || s.length > 50) {
		alert("Invalid Author Provided");
		
		return;
	}

	node = document.getElementById("title");
	
	var s = node.value;
	
	if(s.length == 0 || s.length > 50) {
		alert("Invalid Book Title provided");
		
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
</body>
</html>
