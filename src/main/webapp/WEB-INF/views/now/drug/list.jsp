<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="page-content">
	<div class="row ">
		<div class="col-xs-12 ">
			<table id="drugTable"></table>
			<div id="drugTablepager"></div>
		</div>
	</div>
</div>

<script type="text/javascript" charset="utf-8">
<%@ include file="list.js" %>
</script>