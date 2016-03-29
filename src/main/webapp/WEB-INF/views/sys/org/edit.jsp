<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<form id="org_edit_form" class="form-horizontal" action="${ctx}/sys/org/save" method="post">
	<input type="hidden" name="id" value="${org.id}"/>
	<input type="hidden" name="parent.id" value="${org.parent.id}"/>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="form-input-readonly">上级组织：</label>
		<div class="col-sm-9">
			<input disabled="disabled" type="text" class="input-xlarge" id="form-input-readonly" value="${org.parent.name }" /> 
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="code">组织编码：</label>
		<div class="col-sm-9">
			<input type="text" id="code" name="code" value="${org.code }" class="input-xlarge" />
		</div>
	</div>

	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="name">组织名称：</label>
		<div class="col-sm-9">
			<input type="text" id="name" name="name" value="${org.name }" class="input-xlarge" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="fullName">组织全称：</label>
		<div class="col-sm-9">
			<input type="text" id="fullName" name="fullName" value="${org.fullName }" class="input-xlarge" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="sortNo">排序号：</label>
		<div class="col-sm-9">
			<input type="text" id="sortNo" name="sortNo" value="${org.sortNo }" class="col-xs-10 col-sm-5" />
		</div>
	</div>
	<div>
		<label for="form-field-8">描述</label>
		<textarea class="form-control" id="description" name="description" >${org.description}</textarea>
	</div>
</form>

<script type="text/javascript">
	$(function() {
		
	});
</script>