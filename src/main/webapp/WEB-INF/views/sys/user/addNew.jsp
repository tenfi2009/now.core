<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<form id="addNew_form" role="form" class="form-horizontal" action="${ctx}/sys/user/save" method="post">
	<div class="form-group col-md-6" >
		<label class="col-md-4 control-label no-padding-right" for="code"><span style="color: red">*</span>用户编码：</label>
		<div class="col-md-8">
			<input type="text" class="form-control" id="code" name="code" maxlength="32" required class="input-xlarge" />
		</div>
	</div>
	<div class="form-group col-md-6">
		<label class="col-md-4 control-label no-padding-right" for="name"><span style="color: red">*</span>用户名称：</label>
		<div class="col-md-8">
			<input type="text" class="form-control" id="name" name="name" maxlength="16" class="input-xlarge"  required/> 
		</div>
	</div>
	<div class="form-group col-md-6" >
		<label class="col-md-4 control-label no-padding-right" for="account"><span style="color: red">*</span>登录账号：</label>
		<div class="col-md-8">
			<input type="text" class="form-control" id="account" name="account" maxlength="16" class="input-xlarge" required/>
		</div>
	</div>
	<div class="form-group col-md-6">
		<label class="col-md-4 control-label no-padding-right" for="password"><span style="color: red">*</span>登录密码：</label>
		<div class="col-md-8">
			<input type="text" class="form-control" class="input-xlarge" id="password" name="password" maxlength="16" required/> 
		</div>
	</div>
	<div class="form-group col-md-12">
		<label class="col-md-2 control-label no-padding-right" for="orgFullName"><span style="color: red">*</span>所属于组织：</label>
		<div class="col-md-10 input-group">
			<input type="hidden" id="orgId" name="org.id">
			<input class="form-control input-large" type="text" id="orgFullName" name="org.fullName"  readonly="true" required />
			<span id="orgFullName_addon" class="input-group-addon"><i class="icon-search"></i></span>
		</div>
	</div>
	
	<div class="form-group col-md-6">
		<label class="col-md-4 control-label no-padding-right" for="mobile">手机号：</label>
		<div class="col-md-8">
			<input type="text" class="form-control" class="input-xlarge" id="mobile" name="mobile" minlength="11" maxlength="24"/> 
		</div>
	</div>
	<div class="form-group col-md-6">
		<label class="col-md-4 control-label no-padding-right" for="phone">联系电话：</label>
		<div class="col-md-8">
			<input type="text" class="form-control" class="input-xlarge" id="phone" name="phone" minlength="7" maxlength="24"/> 
		</div>
	</div>
	<div class="form-group col-md-6">
		<label class="col-md-4 control-label no-padding-right" for="email">电子邮箱：</label>
		<div class="col-md-8">
			<input type="email" class="form-control" class="input-xlarge" id="email" name="email" maxlength="64"/> 
		</div>
	</div>
	

	<div class="row"> 
		<div class="form-group col-md-12">
			<label class="col-md-2 control-label no-padding-right" for="description">备注:</label>
			<textarea class="col-md-10" id="description" name="description"></textarea>
		</div>
	</div>
</form>

<script type="text/javascript">
	$(function() {
		var orgTree = $("#orgFullName").dropDownTree({ssource:"${ctx}/sys/org/getOrgTree",fieldId:"orgId",selectParent:true});
		$("#orgFullName_addon").click(function(event){
			$("#orgFullName").click();
		});
		
		$("#orgFullName").click(function(event){
			 orgTree.show();
		 });
		
		validate("#addNew_form");
	});
</script>