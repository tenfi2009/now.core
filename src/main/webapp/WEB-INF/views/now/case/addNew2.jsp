<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<form id="addNew_form" role="form" class="form-horizontal" action="${ctx}/module/save" method="post">
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
<!-- 	<div class="form-group col-md-6"> -->
<!-- 		<label class="col-md-4 control-label no-padding-right" for="drugName"><span style="color: red">*</span>药物名称：</label> -->
<!-- 		<div class="col-md-8"> -->
<!-- 			<input type="text" class="form-control" class="input-xlarge" id="drugName" name="drugName" maxlength="16" required/>  -->
<!-- 		</div> -->
<!-- 	</div> -->
<!-- 	<div class="form-group col-md-6"> -->
<!-- 		<label class="col-md-4 control-label no-padding-right" for="feeAmount">单价费用：</label> -->
<!-- 		<div class="col-md-8"> -->
<!-- 			<input type="text" class="form-control" class="input-xlarge" id="mobile" name="mobile" minlength="11" maxlength="24"/>  -->
<!-- 		</div> -->
<!-- 	</div> -->
<!-- 	<div class="form-group col-md-6"> -->
<!-- 		<label class="col-md-4 control-label no-padding-right" for="number">数量：</label> -->
<!-- 		<div class="col-md-8"> -->
<!-- 			<input type="text" class="form-control" class="input-xlarge" id="number" name="number" minlength="7" maxlength="24"/>  -->
<!-- 		</div> -->
<!-- 	</div> -->
<!-- 	<div class="form-group col-md-6"> -->
<!-- 		<label class="col-md-4 control-label no-padding-right" for="feeSumAmount">费用总额：</label> -->
<!-- 		<div class="col-md-8"> -->
<!-- 			<input type="email" class="form-control" class="input-xlarge" id="feeSumAmount" name="feeSumAmount" maxlength="64"/>  -->
<!-- 		</div> -->
<!-- 	</div> -->
<!-- 	<div class="form-group col-md-6"> -->
<!-- 		<label class="col-md-4 control-label no-padding-right" for="feeSumAmount">费用总额：</label> -->
<!-- 		<div class="col-md-8"> -->
<!-- 			<input type="email" class="form-control" class="input-xlarge" id="feeSumAmount" name="feeSumAmount" maxlength="64"/>  -->
<!-- 		</div> -->
<!-- 	</div> -->
	
</form>

<script type="text/javascript">
	$(function() {
		
		validate("#addNew_form");
	});
</script>