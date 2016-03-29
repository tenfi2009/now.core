<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<form id="org_add_form" class="form-horizontal" action="${ctx}/sys/org/save" method="post">
	<input type="hidden" name="parent.id" value="${org.parent.id}"/>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="form-input-readonly">上级组织：</label>
		<div class="col-sm-9">
			<input disabled="disabled" type="text" class="input-xlarge" id="form-input-readonly" value="${org.parent.name }" /> 
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="form-field-1">组织编码：</label>
		<div class="col-sm-9">
			<input type="text" id="form-field-1" name="code" placeholder="" class="input-xlarge" />
		</div>
	</div>

	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="form-field-1">组织名称：</label>
		<div class="col-sm-9">
			<input type="text" id="form-field-1" name="name" placeholder="" class="input-xlarge" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="form-field-1">组织全称：</label>
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