<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="row">
	<div class="col-xs-12">
		<!-- PAGE CONTENT BEGINS -->
		<div class="row">
			<div class="col-sm-3 widget-container-span" style="padding-right: 0px;">
				<div class="widget-box" style="margin-top: 0px; height: auto">
					<div class="widget-header header-color-blue">
						<h6 class="bigger lighter">数据字典树</h6>
					</div>
					<div class="widget-body">
						<div class="widget-main no-padding">
							<div class="table-responsive">
								<div class="zTreeDemoBackground left">
									<ul id="dictTree" class="ztree" style="height: auto; overflow: auto;"></ul>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- /span -->
			<div class="col-sm-9 page-content">
				<table id="dictTable"></table>
				<div id="dictTablepager"></div>
			</div>
		</div>
	</div>

	<!-- PAGE CONTENT ENDS -->
</div>
<!-- 新增字典 -->
<div class="modal fade" id="dict_add_dialog" tabindex="-1" role="dialog" aria-labelledby="dict_add_dialog_title" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="dict_add_dialog_title">新增-字典</h4>
			</div>
			<div id="dict_add_dialog_content" class="modal-body"></div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				<button id="dict_add_dialog_save" type="button" class="btn btn-primary">保存</button>
			</div>
		</div>
	</div>
</div>

<!-- 修改字典 -->
<div class="modal fade" id="dict_edit_dialog" tabindex="-1" role="dialog" aria-labelledby="dict_edit_dialog_title" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="dict_edit_dialog_title">修改-字典</h4>
			</div>
			<div id="dict_edit_dialog_content" class="modal-body"></div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				<button id="dict_edit_dialog_save" type="button" class="btn btn-primary">保存</button>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript" charset="utf-8">
<%@ include file="list.js" %>
</script>