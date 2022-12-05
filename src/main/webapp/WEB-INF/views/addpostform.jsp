<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>

<h1>Add New Book</h1>
<form action="addok" method="post" >
        <fieldset style = "width:200px;margin:5px">
            <legend>책 정보 입력</legend>
            Title: <input type="text" name="title"/><br><br>
            Writer: <input type="text" name="writer"/><br><br>
            Content: <textarea cols="50" rows="5" name="content"></textarea><br><br>
            Category: <textarea cols="20" rows="5" name="category"></textarea><br><br>
            Read_OR_Not: <input type="radio" name = "read_yn" value = "0" checked> NO
            <input type="radio"  name = "read_yn" value = "1">YES <br><br>
            <input type="submit" value="Add Post"/><br><br>
        </fieldset><br>

    <table>
        <tr><td><a href="list">View All Records</a></td><td align="right"></td></tr>
    </table>
</form>

</body>
</html>