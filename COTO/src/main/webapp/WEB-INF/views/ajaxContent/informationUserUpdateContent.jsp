<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<div class="table">
	<c:forEach items="${users}" var="user" varStatus="status">
		<input name="id" value="${user.id}" type="hidden" />
		<div class="tableRow">
			<span class="tableCell th3">이름</span> 
			<span class="tableCell td8">
				<input type="text" name="name" value="${user.name}"
					placeholder="${user.name}" /> </span>
		</div>
		<div class="tableRow">
			<span class="tableCell th3">이메일</span> <span class="tableCell td8"><input
				type="text" name="email" value="${user.email}"
				placeholder="${user.email}" /> </span>
		</div>
		<div class="tableRow">
			<span class="tableCell th3">닉네임</span> <span
				class="tableCell td8"><input type="text" name="nickName"
				value="${user.nickName}" placeholder="${user.nickName}" /> </span>
		</div>
		<div class="tableRow">
			<span class="tableCell th3">자기소개</span> <span
				class="tableCell td8"><input type="text" name="intro"
				value="${user.intro}" placeholder="${user.intro}" /> </span>
		</div>
	</c:forEach>
</div>