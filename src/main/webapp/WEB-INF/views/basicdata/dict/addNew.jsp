<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<form id="dict_add_form" class="form-horizontal" action="${ctx}/basicdata/dict/save" method="post">
	<input type="hidden" name="parent.id" value="${dict.parent.id}"/>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="form-input-readonly">上级字典：</label>
		<div class="col-sm-9">
			<input disabled="disabled" type="text" class="input-xlarge" id="form-input-readonly" value="${dict.parent.name }" /> 
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="form-field-1">字典编码：</label>
		<div class="col-sm-9">
			<input type="text" id="form-field-1" name="code" placeholder="" class="input-xlarge" />
		</div>
	</div>

	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="form-field-1">字典名称：</label>
		<div class="col-sm-9">
			<input type="text" id="form-field-1" name="name" placeholder="" class="input-xlarge" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="form-field-1">字典全称：</label>
		<div class="col-sm-9">
			<input type="text" id="form-field-1" name="fullName" placeholder="" class="input-xlarge" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="form-field-1">排序号：</label>
		<div class="col-sm-9">
			<input type="text" id="form-field-1" name="sortNo" placeholder="" class="col-xs-10 col-sm-5" />
		</div>
	</div>
	<div>
		<label for="form-field-8">描述</label>
		<textarea class="form-control" id="form-field-8" name="description" placeholder=""></textarea>
	</div>
</form>

<script type="text/javascript">
	$(function() {
		
	});
</script>