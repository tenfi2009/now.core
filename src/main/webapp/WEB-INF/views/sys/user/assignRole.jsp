<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
<!--
table.gridtable {
	font-size: 13px;
	color:#333333;
	border-width: 1px;
	border-collapse: collapse;
	width: 100%;
	background-color:transparent;
}
table.gridtable th {
	border-width: 1px;
	padding: 0 2px;
	border-style: solid;
	border-color: #666666;
	background-color: #EDEDED;
	border-right:1px solid #e1e1e1 !important;
	text-align:center !important;
	height:45px;
}
table.gridtable thead tr { 
display:block; 
} 

table.gridtable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;
}
table.gridtable tbody { 
display: block; 
height: 360px; 
overflow: auto; 
} 
-->
</style>
<div class="row">
	<div class="col-xs-12">
			<table id="assign-role-table" class="gridtable table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th width="100" class="center">
							<label>选择</label>
						</th>
						<th width="150">角色编码</th>
						<th width="250">角色名称</th>
						<th width="365">角色描述</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="role" items="${roles}">
						<tr>
							<td width="100" class="center">
								<label>
									<c:choose>
    									<c:when test="${role.additional.isAssigned }">
    										<input type="checkbox" name="roleId" value="${role.id }" checked class="ace" />
    									</c:when>
    									<c:otherwise>
    										<input type="checkbox" name="roleId" value="${role.id }" class="ace" />
    									</c:otherwise>
									</c:choose>
									
									<span class="lbl"></span>
								</label>
							</td>
							<td width="150">${role.code}</a></td>
							<td width="250">${role.name}</td>
							<td width="349">${role.description}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
	</div>
	<!-- /span -->
</div>
<!-- /row -->
<script type="text/javascript">
	$(function() {
		//保存分配的角色
		$("#assign_role_dialog_save").off().on('click', function() {
			var sData ="";    
			$('input[name="roleId"]:checked',"#assign-role-table").each(function(i,item){
				if(0 != i){
					sData +=",";
				}
				sData += $(item).val(); 
			}); 
		
			$.post('sys/ur/save', {userId:"${userId}",roleIds:sData},function(data) {
				if ("success" == data.status) {
					$('#assign_role_dialog').modal("hide");
				} else {
					bootbox.alert(data.msg);
				}
			},"json");
		});
	})
</script>

