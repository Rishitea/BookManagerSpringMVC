<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<style type="text/css">
table {
   min-width: 80%;
   text-align: center;
}
li {list-style:none; float:left; padding:6px;}
</style>
<meta charset="UTF-8">
<title>도서 조회</title>
</head>
<script>
function send_form(){
	var obj = document.getElementById("category");
	if(!obj.value) {
		obj.value="";
	}
	return true;
}

</script>
<body>
<h1>도서정보관리 - 리스트</h1>

   
 <a href="add">도서 등록</a>
 <a href="list">리스트 보기</a><br>
 
	검색 키워드 입력:  
	<form:form modelAttribute="scmd">
	<select name="category">
		<option value="title">제목</option>
		<option value="writer">저자</option>
		<option value="publisher">출판사</option>
	</select>
	<form:input path="keyword"/>	
	<input type="submit" value="찾기">
	</form:form>
   <table border="1">
      <tr>
         <th>등록 번호</th><th>도서 표지</th><th>도서 ISBN</th>
         <th>도서 제목</th><th>저자</th><th>출판사</th><th>가격</th>
      </tr>
      <c:forEach var="book" items="${listData}">
         <tr>
            <td>${book.no}</td>
            <td><img src="${pageContext.request.contextPath}/resources/upload/${book.imgsaved}" width="120" height="150"></td>
            <td>${book.isbn}</td>
            <td><a href="<c:url value='/read/${book.no}'/>">
            ${book.title}</a></td>
            <td>${book.writer}</td>
            <td>${book.publisher}</td>
            <td>${book.price}</td>
      </c:forEach>
   </table>

   <div>
   	<ul>
   		<c:if test="${pageMaker.prev}">
   			<li><a href="list${pageMaker.makeSearch(pageMaker.startPage - 1) }">이전</a></li>
   		</c:if>
   		
   		<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage }" var="idx">
   			<li><a href="list${pageMaker.makeSearch(idx)}">${idx}</a></li>
   		</c:forEach>
   		
   		<c:if test="${pageMaker.next && pageMaker.endPage >0 }">
   			<li><a href="list${pageMaker.makeSearch(pageMaker.endPage + 1) }">다음</a></li>
   		</c:if>
   	</ul>
   	</div>
 
</body>
</html>