<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form id="edit_form" class="form-horizontal" action="sys/res/save" method="post">
	<input type="hidden" name="id" value="${res.id}"/>
	<input type="hidden" name="parent.id" value="${res.parent.id}"/>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="form-input-readonly">上级资源：</label>
		<div class="col-sm-9">
			<input disabled="disabled" type="text" class="input-xlarge" id="form-input-readonly" value="${res.parent.fullName }" /> 
		</div>
	</div>

	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right"><span style="color: red">*</span>资源类型：</label>
		<div class="radio col-sm-2">
			<label>
				<input name="type" value="0" type="radio" <c:if test="${res.type.value == 0}">checked</c:if> required class="ace" />
				<span class="lbl">资源组</span>
			</label>
		</div>
		<div class="radio col-sm-2">
			<label>
				<input name="type" value="1" type="radio" <c:if test="${res.type.value == 1}">checked</c:if> class="ace" />
				<span class="lbl">菜单</span>
			</label>
		</div>
		<div class="radio col-sm-2">
			<label>
				<input name="type" value="2" type="radio" <c:if test="${res.type.value == 2}">checked</c:if> class="ace" />
				<span class="lbl">功能</span>
			</label>
		</div>
	</div>

	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="name"><span style="color: red">*</span>资源名称：</label>
		<div class="col-sm-9">
			<input type="text" id="name" name="name" value="${res.name }" maxlength="32" required class="input-xlarge" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="fullName"><span style="color: red">*</span>资源全称：</label>
		<div class="col-sm-9">
			<input type="text" id="fullName" name="fullName" value="${res.fullName}" maxlength="86" required class="input-xlarge" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" maxlength="255" for="uri">资源路径：</label>
		<div class="col-sm-9">
			<input type="text" id="uri" name="uri"  value="${res.uri }" class="input-xlarge" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="icon">资源图标：</label>
		<div class="col-sm-9">
			<input type="text" id="icon" name="icon" value="${res.icon }" class="input-xlarge" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="permission">权限标识：</label>
		<div class="col-sm-9">
			<input type="text" id="permission" name="permission" value="${res.permission }" maxlength="255" placeholder="user:edit:*" class="input-xlarge" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="sortNo"><span style="color: red">*</span>排序号：</label>
		<div class="col-sm-3">
			<input type="text" id="sortNo" name="sortNo" value="${res.sortNo }" required digits="true" class="col-xs-10 col-sm-5" />
		</div>
		<div class="col-sm-6 checkbox">
			<label>
				<input id="isSuper" name="isSuper" <c:if test="${res.isSuper}">checked</c:if> type="checkbox" class="ace" /><span class="lbl">超级管理员权限</span>
			</label>
		</div>
	</div>
	<div>
		<label for="description">描述</label>
		<textarea class="form-control" id="description" name="description" placeholder="">${res.description }</textarea>
	</div>
</form>

<script type="text/javascript">
	$(function() {
		$("#edit_form").validate({
			errorElement: 'div',
			errorClass: 'help-block',
			focusInvalid: false,
			invalidHandler: function (event, validator) { //display error alert on form submit   
				$('.alert-danger', $('.login-form')).show();
			},
	
			highlight: function (e) {
				$(e).closest('.form-group').removeClass('has-info').addClass('has-error');
			},
	
			success: function (e) {
				$(e).closest('.form-group').removeClass('has-error').addClass('has-info');
				$(e).remove();
			},
	
			errorPlacement: function (error, element) {
				if(element.is(':checkbox') || element.is(':radio')) {
					var controls = element.closest('div[class*="col-"]');
					if(controls.find(':checkbox,:radio').length > 1) controls.append(error);
					else error.insertAfter(element.nextAll('.lbl:eq(0)').eq(0));
				}
				else if(element.is('.select2')) {
					error.insertAfter(element.siblings('[class*="select2-container"]:eq(0)'));
				}
				else if(element.is('.chosen-select')) {
					error.insertAfter(element.siblings('[class*="chosen-container"]:eq(0)'));
				}
				else error.insertAfter(element.parent());
			},
	
			submitHandler: function (form) {
			}
		});
	});
</script>