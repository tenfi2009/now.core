<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<form id="addNew_form" class="form-horizontal" action="sys/res/save" method="post">
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
				<input name="type" value="GROUP" type="radio" required class="ace" />
				<span class="lbl">资源组</span>
			</label>
		</div>
		<div class="radio col-sm-2">
			<label>
				<input name="type" value="MENU" type="radio" class="ace" />
				<span class="lbl">菜单</span>
			</label>
		</div>
		<div class="radio col-sm-2">
			<label>
				<input name="type" value="FUNCTION" type="radio" class="ace" />
				<span class="lbl">功能</span>
			</label>
		</div>
	</div>

	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="name"><span style="color: red">*</span>资源名称：</label>
		<div class="col-sm-9">
			<input type="text" id="name" name="name" maxlength="32" required class="input-xlarge" />
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
			<input type="text" id="uri" name="uri"  class="input-xlarge" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="icon">资源图标：</label>
		<div class="col-sm-9">
			<input type="text" id="icon" name="icon" class="input-xlarge" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="permission">权限标识：</label>
		<div class="col-sm-9">
			<input type="text" id="permission" name="permission" maxlength="255" placeholder="user:edit:*" class="input-xlarge" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="sortNo"><span style="color: red">*</span>排序号：</label>
		<div class="col-sm-3">
			<input type="text" id="sortNo" name="sortNo" required digits="true" class="col-xs-10 col-sm-5" />
		</div>
		<div class="col-sm-6 checkbox">
			<label>
				<input id="isSuper" name="isSuper" type="checkbox" class="ace" /><span class="lbl">超级管理员权限</span>
			</label>
		</div>
	</div>
	<div>
		<label for="description">描述</label>
		<textarea class="form-control" id="description" name="description" placeholder=""></textarea>
	</div>
</form>

<script type="text/javascript">
	$(function() {
		validate("#addNew_form");
	});
</script>