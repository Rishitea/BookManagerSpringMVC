<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서관리</title>
<script>
function PreviewImage() {
   var preview = new FileReader();
   preview.onload = function(e) {
      document.getElementById("book_image").src = e.target.result;
   };
   preview.readAsDataURL(document.getElementById("img").files[0]);
};
</script>
</head>
<body>
<h1>도서정보관리-도서추가</h1>
<form:form action="add" modelAttribute="formData" enctype="multipart/form-data" method="post">
<table border="2">
         <tr>
            <td colspan="3" align="right">*표시는 필수입니다.</td>
         </tr>
         <tr>
         <td rowspan="6" width="200" height="250" id="imgTd">
         <img id="book_image"></td>
            <td><b>*BOOK ISBN</b></td>
            <td><form:input path="isbn"/>
            <form:errors path="isbn"/></td>
         </tr>
         <tr>
            <td><b>*도서명</b></td>
            <td><form:input path="title"/>
            <form:errors path="title"/></td>
         </tr>
         <tr>
            <td><b>*저자</b></td>
            <td><form:input path="writer"/>
            <form:errors path="writer"/></td>
         </tr>
         <tr>
            <td><b>*출판사</b></td>
            <td><form:input path="publisher"/>
            <form:errors path="publisher"/></td>
         </tr>
         <tr>
            <td><b>*도서 가격</b></td>
            <td><form:input path="price"/>
            <form:errors path="price"/></td>
         </tr>
         <tr>
            <td><b>*이미지</b></td>
            <td><input type="file" name="img" id="img" onchange="PreviewImage();"/>
            <form:errors path="imgoriginal"/></td>
		 </tr>
         <tr>
            <td><b>*책소개</b></td>
            <td colspan="2"><textarea rows="10" cols="47" style="resize: none;" name="intro" id="intro">
            </textarea><form:errors path="intro"/></td>
         </tr>
      </table>
<input type="submit" value="등록 완료">

</form:form>
<a href="list">도서정보 목록</a>

</body>
</html>