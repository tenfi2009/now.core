<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="cm" tagdir="/WEB-INF/tags" %>
<form id="edit_form" role="form" class="form-horizontal page-content" action="${ctx}/module/case/save" method="post">
	<input type="hidden" name="id" value="${case.id}"/>
	<div class="form-group col-md-6" >
		<label class="col-md-4 control-label no-padding-right" for="name"><span style="color: red">*</span>姓名：</label>
		<div class="col-md-8">
			<input type="text" class="form-control" id="name" name="name" value="${case.name }" maxlength="32" required class="input-xlarge" />
		</div>
	</div>
	<div class="form-group col-md-6">
		<label class="col-md-4 control-label no-padding-right" for="sex"><span style="color: red">*</span>性别：</label>
		<div class="col-md-8">
			<cm:dict name="sex" category="sex" id="sex" displaySelectAll="true" selectedValue="${case.sex }" myClass="input-xlarge form-control" expression="required"></cm:dict>
		</div>
	</div>
	<div class="form-group col-md-6" >
		<label class="col-md-4 control-label no-padding-right" for="age"><span style="color: red">*</span>年龄：</label>
		<div class="col-md-8">
			<input type="text" class="form-control" id="age" name="age" value="${case.age }" maxlength="16" class="input-xlarge" required/>
		</div>
	</div>
	<div class="form-group col-md-6">
		<label class="col-md-4 control-label no-padding-right" for="mobileNumber"><span style="color: red">*</span>手机号：</label>
		<div class="col-md-8">
			<input type="text" class="form-control" class="input-xlarge" id="mobileNumber" name="mobileNumber" value="${case.mobileNumber }" maxlength="16" required/> 
		</div>
	</div>
	
	<div class="row"> 
	</div>
</form>

<script type="text/javascript">
	$(function() {
		//手机号码验证
		jQuery.validator.addMethod("isMobile", function(value, element) {
		    var length = value.length;
		    var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
		    return this.optional(element) || (length == 11 && mobile.test(value));
		}, "请填写正确的手机号");
		
		var rules = {
	    	mobileNumber : {
	            required : true,
	            minlength : 11,
	            isMobile : true
	        }
	    };
		
		var messages = {
	        phone : {
	            required : "请输入手机号",
	            //minlength : "确认手机不能小于11个字符",
	            isMobile : "请正确填写您的手机号码"
	        }
	    };
		
		validate("#edit_form", rules, messages);
	});
	
</script>