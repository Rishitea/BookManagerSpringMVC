<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서관리</title>
</head>
<body>
<h1>도서정보관리-도서정보</h1>
<table border="2">
		
         <tr>
            <td colspan="3" align="right">*표시는 필수입니다.</td>
         </tr>
         <tr>
            <td rowspan="6" width="200" height="250"><img src="${pageContext.request.contextPath}/resources/upload/${bookData.imgsaved}"></td>
            <td><b>*BOOK ISBN</b></td>
            <td>${bookData.isbn}</td>
         </tr>
         <tr>
            <td><b>*도서명</b></td>
            <td>${bookData.title}</td>
         </tr> 
         <tr>
            <td><b>*저자</b></td>
            <td>${bookData.writer}</td>
         </tr>
         <tr>
            <td><b>*출판사</b></td>
            <td>${bookData.publisher}</td>
         </tr>
         <tr>
            <td><b>*도서 가격</b></td>
            <td>${bookData.price}</td>
         </tr>
         <tr>
            <td><b>*이미지</b></td>
            <td>${bookData.imgoriginal}</td>
         </tr>
         <tr>
            <td><b>*책소개</b></td>
            <td colspan="2"><textarea rows="10" cols="47" style="resize: none;" name="intro" id="intro">${bookData.intro}</textarea></td>
         </tr>
       
      </table>
<a href="/books/list">도서정보 목록</a>
</body>
</html>