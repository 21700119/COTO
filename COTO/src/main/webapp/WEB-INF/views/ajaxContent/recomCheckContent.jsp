<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<p style="display: inline-block;">${rp.name}</p>
<c:choose>
	<c:when test="${rp.date eq null}">
		<i class="small smaller material-icons" onclick="checkProblem(${rp.id})" style="color:lightgray !important; height: 30px; float: right; cursor: pointer;">done</i>
	</c:when> 
	<c:otherwise>
		<i class="small smaller material-icons" onclick = "uncheckProblem(${rp.id})" style="color:green; height: 30px; float: right; cursor: pointer;">done</i>
	</c:otherwise>
</c:choose>