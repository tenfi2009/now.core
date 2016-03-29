<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
	<form id="pwd_edit_form" method="post" action="${ctx}/sys/user/saveEditPwd" class="form-horizontal " >
		<input type="hidden" name="userId" value="${user.id}" />
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="oldPwd"><span style="color: red">*</span>当前密码:</label>
			<div class="col-sm-9">
				<input type="password" id="oldPwd" name="oldPwd" placeholder="请输入当前密码" class="col-xs-10 col-sm-5" /> 
				<span class="help-inline col-xs-12 col-sm-7"> <span class="middle"></span>
				</span>
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="newpwd"><span style="color: red">*</span>新密码:</label>
			<div class="col-sm-9">
				<input type="password" id="newpwd" name="newpwd" placeholder="请输入新密码" class="col-xs-10 col-sm-5" /> 
				<span class="help-inline col-xs-12 col-sm-7"> <span class="middle">建议长度在6-16个字符</span>
				</span>
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="repwd"><span style="color: red">*</span>新密码确认:</label>
			<div class="col-sm-9">
				<input type="password" id="repwd" name="repwd" placeholder="请再次输入新密码" class="col-xs-10 col-sm-5" /> 
				<span class="help-inline col-xs-12 col-sm-7"> <span class="middle"></span>
				</span>
			</div>
		</div>
</form>

