<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="row">
	<div class="col-xs-12">
		<!-- PAGE CONTENT BEGINS -->
		<div class="row">
			<table width="100%">
			<tr>
				<td width="45%" valign="top">
					<div class="widget-box" style="margin-top: 0px;padding-left: 10px;">
					<div class="widget-header header-color-blue">
						<h6 class="bigger lighter">系统资源</h6>
					</div>
					<div class="widget-body">
						<div class="widget-main no-padding">
							<div class="table-responsive">
								<div class="zTreeDemoBackground left">
									<ul id="sysResourceTree" class="ztree" style="height:420px; overflow:auto;"></ul>
								</div>
							</div>
						</div>
					</div>
					</div>
				</td>
				<td width="10%" align ="center">
					<div class="form-horizontal">
						<div class="form-group">
							<button id="btn_select_all" class="btn btn-grey">
								 &nbsp;全 &nbsp;选<i class="icon-double-angle-right  align-top bigger-125 icon-on-right"></i>
							</button>		
						</div>
						<div class="form-group">
							<button id="btn_select" class="btn btn-grey">
								 &nbsp;选 &nbsp;择<i class="icon-angle-right  align-top bigger-125 icon-on-right"></i>
							</button>		
						</div>
						<div class="form-group">
							<button id="btn_remove" class="btn btn-grey">
								<i class="icon-angle-left  align-top bigger-125 icon-on-right"></i> &nbsp;移 &nbsp;除
							</button>		
						</div>
						<div class="form-group">
							<button id="btn_remove_all" class="btn btn-grey">
								<i class="icon-double-angle-left  align-top bigger-125 icon-on-right"></i>全移除
							</button>	
						</div>				
					</div> <!--/span-->
				</td>
				<td width="45%" valign="top">
					<div class="widget-box" style="margin-top: 0px;padding-right: 10px;">
					<div class="widget-header header-color-blue">
						<h6 class="bigger lighter">已分配到[${role.name}]角色的资源</h6>
					</div>
					<div class="widget-body">
						<div class="widget-main no-padding">
							<div class="table-responsive">
								<div class="zTreeDemoBackground left">
									<ul id="roleResourceTree" class="ztree" style="height:420px; overflow:auto;"></ul>
								</div>
							</div>
						</div>
					</div>
				</div>
				</td>
			</tr>
		</table>
		</div>
		<!-- PAGE CONTENT ENDS -->
	</div>
	<!-- /.col -->
</div>
<!-- /.row -->
<script type="text/javascript">
	var setting = {
			check: {
				enable: true
			},
			data: {
				simpleData: {
					enable: true,
					idKey: "id",
					pIdKey: "pId",
					rootPId: null
				}
			}
	};
	$(function() {
		$.get("sys/res/getResourceTree", function(result){
			$.fn.zTree.init($("#sysResourceTree"), setting,result);
		},"json");
		
		$.get("sys/rr/getAssignResourceTree",{roleId:"${role.id}"}, function(result){
			$.fn.zTree.init($("#roleResourceTree"), setting,result);
			var roleTreeObj = $.fn.zTree.getZTreeObj("roleResourceTree");
			roleTreeObj.expandAll(true);
		},"json");
		//全选
		$("#btn_select_all").on('click',function() {
			var treeObj = $.fn.zTree.getZTreeObj("sysResourceTree");
			var nodes = treeObj.getNodes();
			var newNodes =  $.fn.zTree._z.tools.clone(nodes);
			
			var roleTreeObj = $.fn.zTree.getZTreeObj("roleResourceTree");
			//清空已有的数据
			cleanTreeNode(roleTreeObj);
			
			roleTreeObj.addNodes(null, newNodes);
		});
		
		//选择
		$("#btn_select").on('click',function() {
			var treeObj = $.fn.zTree.getZTreeObj("sysResourceTree");
			var nodes = treeObj.getCheckedNodes(true);
			
			var roleTreeObj = $.fn.zTree.getZTreeObj("roleResourceTree");
			$.each(nodes, function(index, n) { 
				if(null == roleTreeObj.getNodeByParam("id", n.id, null)){
					var newNode =  $.fn.zTree._z.tools.clone(n);
					var pNode = null;
					if(null != n.pId){
						pNode = roleTreeObj.getNodeByParam("id", newNode.pId, null);
					}
					newNode.children=[];
					roleTreeObj.addNodes(pNode, newNode);
				}
			});
			
		});
		
		//移除
		$("#btn_remove").on('click',function() {
			var treeObj = $.fn.zTree.getZTreeObj("roleResourceTree");
			var nodes = treeObj.getCheckedNodes(true);
			$.each(nodes, function(index, n) { 
				var status = n.getCheckStatus();
				if(status.checked && !status.half){
					treeObj.removeNode(n);
				}
			});
		});
		
		//全移除
		$("#btn_remove_all").on('click',function() {
			var roleTreeObj = $.fn.zTree.getZTreeObj("roleResourceTree");
			cleanTreeNode(roleTreeObj);
		});
		
		function cleanTreeNode(treeObj){
			var nodes = treeObj.transformToArray(treeObj.getNodes());
			$.each(nodes, function(index, n) { 
				treeObj.removeNode(n);
			});
		}
		
		//保存分配的资源
		$("#assign_resource_dialog_save").off().on('click', function() {
			var treeObj = $.fn.zTree.getZTreeObj("roleResourceTree");
			var nodes = treeObj.transformToArray(treeObj.getNodes());
			var sData = "";
			$.each(nodes, function(i, n) { 
				if(0 != i){
					sData+=","
				}
				sData+=n.id;
			});
			
			$.post('sys/rr/save', {roleId:"${role.id}",resourceIds:sData},function(data) {
				if ("success" == data.status) {
					$('#assign_resource_dialog').modal("hide");
				} else {
					bootbox.alert(data.msg);
				}
			},"json");
		});
	})
</script>
