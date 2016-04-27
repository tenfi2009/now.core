<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cm" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="row">
	<div class="col-xs-12">
		<table id="taskQuartzTable"></table>
		<div id="taskQuartzTablepager"></div>
	</div>
	<!-- /.col -->
</div>
<div class="modal fade" id="add_dialog" tabindex="-1" role="dialog" aria-labelledby="add_dialog_title" aria-hidden="true">
  <div class="modal-dialog" style="width: 789px;">
    <div class="modal-content">
      <div class="modal-header" style="padding:10px;">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="add_dialog_title">新增定时任务</h4>
      </div>
      <div id="add_dialog_content" class="modal-body" style="padding:10px;">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal" id="add_dialog_cancel">取消</button>
        <button id="add_dialog_save" type="button" class="btn btn-primary">保存</button>
      </div>
    </div>
  </div>
</div>
<div class="modal fade" id="edit_dialog" tabindex="-1" role="dialog" aria-labelledby="edit_dialog_title" aria-hidden="true">
  <div class="modal-dialog" style="width: 789px;">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="edit_dialog_title">计划设置</h4>
      </div>
      <div id="edit_dialog_content" class="modal-body" style="margin-top:0px;">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal" id="edit_dialog_cancel">取消</button>
        <button id="edit_dialog_save" type="button" class="btn btn-primary">保存</button>
      </div>
    </div>
  </div>
</div>
<div class="modal fade" id="view_dialog" tabindex="-1" role="dialog" aria-labelledby="view_dialog_title" aria-hidden="true">
  <div class="modal-dialog" style="width: 789px;">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="view_dialog_title">查看</h4>
      </div>
      <div id="view_dialog_content" class="modal-body" style="padding-top:10px;">
      </div>
    </div>
  </div>
</div>
<script type="text/javascript" charset="utf-8">
<%@ include file="list.js" %>
</script>