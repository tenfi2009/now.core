<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="row">
	<div class="col-xs-12">
		<div class="widget-box">
			<div class="widget-header widget-header-small">
				<h5 class="lighter">角色查询</h5>
			</div>
			<div class="widget-body">
				<div class="widget-main">
					<form id="role-form-search" class="form-search">
					<fieldset>
						<label>角色编码：</label>
						<input type="text" name="code"/>&nbsp;&nbsp;
						<label>角色名称：</label>
						<input type="text" name="name"/>&nbsp;&nbsp;
						<label>状态：</label>
						<select name="status">
							<option value="" selected>--全部--</option>
							<option value="0">保存</option>
							<option value="1">有效</option>
							<option value="2">无效</option>
						</select>&nbsp;&nbsp;
						
						
						<button onclick="search();" type="button" class="btn btn-purple btn-sm">
							查询 <i class="icon-search icon-on-right bigger-110"></i>
						</button>							
						</fieldset>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="col-xs-12">
		<table id="roleTable"></table>
		<div id="roleTablepager"></div>
	</div>
	<!-- /.col -->
</div>
<!-- /.row -->
<!-- 新增角色 -->
<div class="modal fade" id="add_dialog" tabindex="-1" role="dialog" aria-labelledby="add_dialog_title" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="add_dialog_title">新增-角色</h4>
      </div>
      <div id="add_dialog_content" class="modal-body">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button id="add_dialog_save" type="button" class="btn btn-primary">保存</button>
      </div>
    </div>
  </div>
</div>

<!-- 修改角色 -->
<div class="modal fade" id="edit_dialog" tabindex="-1" role="dialog" aria-labelledby="edit_dialog_title" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="edit_dialog_title">修改-角色</h4>
      </div>
      <div id="edit_dialog_content" class="modal-body">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button id="edit_dialog_save" type="button" class="btn btn-primary">保存</button>
      </div>
    </div>
  </div>
</div>

<!-- 资源分配 -->
<div class="modal fade" id="assign_resource_dialog" tabindex="-1" role="dialog" aria-labelledby="assign_resource_dialog_title" aria-hidden="true">
  <div class="modal-dialog" style="width: 970px;">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="assign_resource_dialog_title">分配-权限资源</h4>
      </div>
      <div id="assign_resource_dialog_content" class="modal-body"  style="padding: 10px 10px 0px;">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button id="assign_resource_dialog_save" type="button" class="btn btn-primary">保存</button>
      </div>
    </div>
  </div>
</div>

<script type="text/javascript" charset="utf-8">
<%@ include file="list.js" %>
</script>