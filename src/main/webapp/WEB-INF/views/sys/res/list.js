var grid_render_id = "#resTable";
var pager_render_id = "#resTablepager";
$(function() {
	resizeGrid(grid_render_id);
	$(grid_render_id).jqGrid(
			{
				caption : "资源列表",
				height : 'auto',
				rowNum : 15,
				rowList : [ 15, 25, 50, 100 ],
				treeGrid: true,
				
			    treeGridModel: 'adjacency',
			    ExpandColumn : 'name',
			    treeIcons : {plus:'fa fa-plus bigger-130 center blue',minus:'fa fa-minus bigger-130 center blue',leaf:'fa fa-leaf center bigger-130 center blue'},
			    
			    tree_root_level:0,
				url : "${ctx}/sys/res/listData",
				datatype : "json",
				colNames : [ 'ID','资源名称','资源类型','资源路径','权限标识','超级管理员权限', '状态','操作'],
				colModel : [ {name : 'id',index : 'id',hidden : true},
				             {name : 'name',index : 'name',width : 120},
				             {name : 'type',index : 'type',width : 60},
				             {name : 'uri',index : 'uri',width : 180},
				             {name : 'permission',index : 'permission',width : 120},
				             {name : 'isSuper',index : 'isSuper',width : 60},
				             {name : 'status',index : 'status',width : 40},
				             {name : 'id', index: 'id', align:"center",formatter: function (cellvalue, options, rowObject) {
				            	 var actions = "<a href='javascript:void(0)'  onclick=\"addNew('" + cellvalue + "')\">新建下级</a>";
				            	 actions += "|<a href='javascript:void(0)'  onclick=\"edit('" + cellvalue + "')\">修改</a>";
				            	 if(rowObject.leaf){
				            		 actions += "|<a href='javascript:void(0)'  onclick=\"remove2('" + cellvalue + "')\">删除</a>";
				            	 }else{
				            		 actions += "|删除";
				            	 }
				            	 return actions;
				             }}
				            ],
				viewrecords : true,
				pager : pager_render_id,
				altRows : true,
				sortname : "createTime",
				autowidth : true,
				multiselect : false,
				//multikey: "ctrlKey",
				loadonce : true,
				multiboxonly : true,
				jsonReader : {
					root : "result",
					page : "currPage",
					total : "totalPage",
					records : "totalCount"
				},
				treeReader : {
					level_field: "degree",
					parent_id_field: "parentId",
					leaf_field: "leaf",
					expanded_field: false
				},
				loadComplete : function() {
					var table = this;
					setTimeout(function() {
						$.jqGridExt.setStyleCheckbox(table);
						$.jqGridExt.updateActionIcons(table);
						$.jqGridExt.updatePagerIcons(table);
						$.jqGridExt.enableTooltips(table);
					}, 0);
				}
			});
	//navButtons
	$(grid_render_id).jqGrid(
			'navGrid',
			pager_render_id,
			{ //navbar options
				alertcap : "消息",
				alerttext : "请您先选择要操作的记录！",
				edit : true,
				edittext : "修改",
				editicon : 'ace-icon fa fa-pencil blue',
				editfunc : edit,
				add : true,
				addtext : "新建",
				addicon : 'ace-icon fa fa-plus-circle purple',
				addfunc : addNew,
				del : true,
				deltext : "删除",
				delicon : 'ace-icon fa fa-trash-o red',
				delfunc : remove2,
				search : false,
				searchtext : "查询",
				searchicon : 'icon-search orange',
				refresh : true,
				refreshtext : "刷新",
				refreshicon : 'ace-icon fa fa-refresh green',
				view : false,
				viewtext : "查看",
				viewicon : 'ace-icon fa fa-search-plus grey',
			},
			{
			//  default settings for edit
			},
			{ //  default settings for add
			},
			{}, // delete instead that del:false we need this
			{ // search options
				caption : "高级查询",
				recreateForm : true,
				afterShowSearch : function(e) {
					var form = $(e[0]);
					form.closest('.ui-jqdialog').find('.ui-jqdialog-title')
							.wrap('<div class="widget-header" />')
					$.jqGridExt.setStyleSearchForm(form);
				},
				afterRedraw : function() {
					$.jqGridExt.setStyleSearchFilters($(this));
				},
				showQuery : false,
				multipleSearch : true
			},
			{}/* view parameters*/
			
	).jqGrid('navButtonAdd',pager_render_id,{
		   caption:"提交", 
		   title : "提交用户信息",
		   buttonicon:"fa fa-check-circle", 
		   onClickButton: submit,
		   position:"before refresh_userTable"
		});

	//var selr = jQuery(grid_selector).jqGrid('getGridParam','selrow');
	$("#add_dialog_save").on('click', function() {
		if(!$("#addNew_form").valid()){
			return
		}
		$("#addNew_form").ajaxSubmit({
			dataType : 'json',
			success : function(data) {
				if ("success" == data.status) {
					$('#add_dialog').modal("hide");
					$(grid_render_id).jqGrid().trigger("reloadGrid");
				} else {
					bootbox.alert(data.msg);
				}
			}
		});
	});
	
	$("#edit_dialog_save").on('click', function() {
		if(!$("#edit_form").valid()){
			return
		}
		$("#edit_form").ajaxSubmit({
			dataType : 'json',
			success : function(data) {
				if ("success" == data.status) {
					$('#edit_dialog').modal("hide");
					$(grid_render_id).jqGrid().trigger("reloadGrid");
				} else {
					bootbox.alert(data.msg);
				}
			}
		});
	});
	
	
})

	function addNew(parentId) {
		var selectedId = null;
		if(undefined == parentId){
			selectedId = jQuery(grid_render_id).jqGrid('getGridParam','selrow');
		}else{
			selectedId = parentId;
		}
		
		selectedId = selectedId == null ? "" : selectedId;
		
		$("#add_dialog_content").load('sys/res/addNew?parentId='+selectedId);
		$("#add_dialog").modal("show");
	}

	function edit(id) {
		$("#edit_dialog_content").load('sys/res/edit?id=' + id);
		$("#edit_dialog").modal("show");
	}

	function remove2(id) {
		bootbox.confirm("您确认要删除该资源吗?", function(result) {
			if (result) {
				$.get("sys/res/remove/" + id,
						function(data) {
							if ("success" == data.status) {
								$(grid_render_id).jqGrid().trigger("reloadGrid");
							} else if ("error" == data.status) {
								bootbox.alert(data.msg);
							}
						}, "json");
			}
		});
	}
	
function resetPw(id) {
		
}

function submit(id) {
	var selectedId = jQuery(grid_render_id).jqGrid('getGridParam','selrow');
	if(null == selectedId || "" == selectedId){
		bootbox.alert("请选择要提交的记录！");
		return;
	}
	bootbox.confirm("您确认要提交该资源信息吗?", function(result) {
		if (result) {
			$.get("${ctx}/sys/res/submit/" + selectedId,
					function(data) {
						if ("success" == data.status) {
							$(grid_render_id).jqGrid().trigger("reloadGrid");
						} else if ("error" == data.status) {
							bootbox.alert(data.msg);
						}
					}, "json");
		}
	});
}

