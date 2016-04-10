<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<form id="dict_edit_form" class="form-horizontal" action="${ctx}/basicdata/dict/save" method="post">
	<input type="hidden" name="id" value="${dict.id}"/>
	<input type="hidden" name="parent.id" value="${dict.parent.id}"/>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="form-input-readonly">上级字典：</label>
		<div class="col-sm-9">
			<input disabled="disabled" type="text" class="input-xlarge" id="form-input-readonly" value="${dict.parent.name }" /> 
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="code">字典编码：</label>
		<div class="col-sm-9">
			<input type="text" id="code" name="code" value="${dict.code }" class="input-xlarge" />
		</div>
	</div>

	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="name">字典名称：</label>
		<div class="col-sm-9">
			<input type="text" id="name" name="name" value="${dict.name }" class="input-xlarge" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="fullName">字典全称：</label>
		<div class="col-sm-9">
			<input type="text" id="fullName" name="fullName" value="${dict.fullName }" class="input-xlarge" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="sortNo">排序号：</label>
		<div class="col-sm-9">
			<input type="text" id="sortNo" name="sortNo" value="${dict.sortNo }" class="col-xs-10 col-sm-5" />
		</div>
	</div>
	<div>
		<label for="form-field-8">描述</label>
		<textarea class="form-control" id="description" name="description" >${dict.description}</textarea>
	</div>
</form>

<script type="text/javascript">
	$(function() {
		
	});
</script>