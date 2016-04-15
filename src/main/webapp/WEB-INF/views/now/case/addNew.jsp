<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<form id="addNew_form" role="form" class="form-horizontal page-content" action="${ctx}/module/case/save" method="post">
	<div class="form-group col-md-6" >
		<label class="col-md-4 control-label no-padding-right" for="name"><span style="color: red">*</span>姓名：</label>
		<div class="col-md-8">
			<input type="text" class="form-control" id="name" name="name" maxlength="32" required class="input-xlarge" />
		</div>
	</div>
	<div class="form-group col-md-6">
		<label class="col-md-4 control-label no-padding-right" for="sex"><span style="color: red">*</span>性别：</label>
		<div class="col-md-8">
			<input type="text" class="form-control" id="sex" name="sex" maxlength="16" class="input-xlarge"  required/> 
		</div>
	</div>
	<div class="form-group col-md-6" >
		<label class="col-md-4 control-label no-padding-right" for="age"><span style="color: red">*</span>年龄：</label>
		<div class="col-md-8">
			<input type="text" class="form-control" id="age" name="age" maxlength="16" class="input-xlarge" required/>
		</div>
	</div>
	<div class="form-group col-md-6">
		<label class="col-md-4 control-label no-padding-right" for="mobileNumber"><span style="color: red">*</span>手机号：</label>
		<div class="col-md-8">
			<input type="text" class="form-control" class="input-xlarge" id="mobileNumber" name="mobileNumber" maxlength="16" required/> 
		</div>
	</div>
	
	
<!-- 	<div class="form-group col-md-12 drugTable"> -->
<!-- 		<table id="drugTable"></table> -->
<!-- 		<div id="drugTablepager"></div> -->
<!-- 	</div> -->
	
	<div class="row"> 
	</div>
</form>

<script type="text/javascript">
	$(function() {
		validate("#addNew_form");
	});
	
<%-- 	<%@ include file="drugList.js" %> --%>
</script>