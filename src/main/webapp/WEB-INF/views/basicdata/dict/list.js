function myRefresh(type) {
	/** 刷新grid */
	if (null != selectedOrgNode) {
		$("#dictTable").jqGrid('setGridParam', {
			postData : {
				"parentId" : selectedOrgNode.id
			}
		}).trigger("reloadGrid");
	} else {
		$("#dictTable").jqGrid().trigger("reloadGrid");
	}

	/** 刷新左侧tree */
	var treeObj = $.fn.zTree.getZTreeObj("dictTree");
	var nodes = treeObj.getSelectedNodes();
	try {
		var selectChildrens = nodes[0].children;
		if ('save' == type) {
			if (!selectChildrens) {
				treeObj.reAsyncChildNodes(null, "refresh");
			} else {
				var selectChildrenLength = selectChildrens.length;
				if (selectChildrenLength > 0) {
					treeObj.reAsyncChildNodes(nodes[0], "refresh");
				}
			}
		} else if ('delete' == type) {
			var selectChildrenLength = selectChildrens.length;
			if (selectChildrenLength > 1) {
				treeObj.reAsyncChildNodes(nodes[0], "refresh");
			} else {
				treeObj.reAsyncChildNodes(null, "refresh");
			}
		} else if ('update' == type || 'refresh') {
			// do nothing
		}
	} catch (e) {
		treeObj.reAsyncChildNodes(null, "refresh");
	}

}

var selectedOrgNode = null;
var setting = {
	async : {
		enable : true,
		dataType : "json",
		url : "${ctx}/basicdata/dict/getDictTree",
		autoParam : [ "id" ]
	},
	data : {
		simpleData : {
			enable : true,
			idKey : "id",
			pIdKey : "pId",
			rootPId : null
		}
	},
	callback : {
		onClick : function(event, treeId, treeNode) {
			selectedOrgNode = treeNode;
			$("#dictTable").jqGrid('setGridParam', {
				postData : {
					"parentId" : treeNode.id
				}
			}).trigger("reloadGrid");
		},
		onAsyncSuccess : function() {
			if (null != selectedOrgNode) {
				$("#dictTable").jqGrid('setGridParam', {
					postData : {
						"parentId" : selectedOrgNode.id
					}
				}).trigger("reloadGrid");
			} else {
				$("#dictTable").jqGrid().trigger("reloadGrid");
			}
		}
	}
};
$(function() {
	$.fn.zTree.init($("#dictTree"), setting);

	var grid_selector = "#dictTable";
	var pager_selector = "#dictTablepager";

	$(grid_selector).jqGrid({
		caption : "字典列表",
		height : 'auto',
		rowNum : 15,
		rowList : [ 15, 25, 50, 100 ],
		url : "${ctx}/basicdata/dict/listData",
		datatype : "json",
		colNames : [ 'ID', '字典编码', '字典名称', '描述', '状态', '创建时间', '创建人' ],
		colModel : [ {
			name : 'id',
			index : 'id',
			hidden : true
		}, {
			name : 'code',
			index : 'code',
			width : 80
		}, {
			name : 'name',
			index : 'name',
			width : 120
		}, {
			name : 'description',
			index : 'description',
			width : 120
		}, {
			name : 'status',
			index : 'status',
			width : 60
		}, {
			name : 'createTime',
			index : 'createTime',
			width : 60
		}, {
			name : 'creator',
			index : 'creator',
			width : 60
		} ],
		viewrecords : true,
		postData : {
			"parentId" : function() {
				return selectedOrgNode == null ? "" : selectedOrgNode.id
			}
		},
		pager : pager_selector,
		altRows : true,
		sortname : "sortNo",
		autowidth : true,
		rownumbers : true,
		multiselect : false,
		// multikey: "ctrlKey",
		multiboxonly : true,
		jsonReader : {
			root : "result",
			page : "currPage",
			total : "totalPage",
			records : "totalCount"
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
	// navButtons
	$(grid_selector)
			.jqGrid(
					'navGrid',
					pager_selector,
					{ // navbar options
						alertcap : "消息",
						alerttext : "请您先选择要操作的记录！",
						edit : true,
						edittext : "修改",
						editicon : 'ace-icon fa fa-pencil blue',
						editfunc : function(id, status) {
							var selectedId = $(grid_selector).jqGrid('getGridParam', 'selrow');
							var rowObj = $(grid_selector).jqGrid("getRowData",selectedId);
							$("#dict_edit_dialog_content").load('${ctx}/basicdata/dict/edit?id=' + id);
							$("#dict_edit_dialog").modal("show");
						},
						add : true,
						addtext : "新建",
						addicon : 'ace-icon fa fa-plus-circle purple',
						addfunc : function() {
							var selectedOrgId = selectedOrgNode == null ? "": selectedOrgNode.id;
							$("#dict_add_dialog_content").load('${ctx}/basicdata/dict/addNew?parentId='+ selectedOrgId);
							$("#dict_add_dialog").modal("show");
						},
						del : true,
						deltext : "删除",
						delicon : 'ace-icon fa fa-trash-o red',
						delfunc : function(id) {
							var selectedId = $(grid_selector).jqGrid('getGridParam', 'selrow');
							var rowObj = $(grid_selector).jqGrid("getRowData",selectedId);
							bootbox.confirm("您确认要删除该字典吗?",function(result) {
												if (result) {
													$.get("${ctx}/basicdata/dict/remove/"+ id,
															function(data) {
																if ("success" == data.status) {
																	myRefresh('delete');
																} else if ("error" == data.status) {
																	bootbox.alert(data.msg);
																}
															}, "json");
												}
											});
						},
						search : length,
						refresh : true,
						refreshtext : "刷新",
						refreshicon : 'ace-icon fa fa-refresh green',
						afterRefresh : function() {
							var treeObj = $.fn.zTree.getZTreeObj("dictTree");
							var nodes = treeObj.getSelectedNodes();

							if (nodes.length > 0) {
								treeObj.reAsyncChildNodes(nodes[0], "refresh");
							} else {
								treeObj.reAsyncChildNodes(null, "refresh");
							}
						},
						view : false,
						viewtext : "查看",
						viewicon : 'ace-icon fa fa-search-plus grey',
					},
					{
					// default settings for edit
					},
					{ // default settings for add
					},
					{}, // delete instead that del:false we need this
					{ // search options
					}, {} /* view parameters */
			)
			.jqGrid(
					'navButtonAdd',
					pager_selector,
					{
						caption : "提交",
						title : "提交",
						buttonicon : "fa fa-check-circle",
						onClickButton : function() {
							var selectedId = jQuery(grid_selector).jqGrid(
									'getGridParam', 'selrow');
							if (null == selectedId || "" == selectedId) {
								bootbox.alert("请选择要提交的记录！");
								return;
							}
							bootbox.confirm("您确认要提交该数据吗?",
									function(result) {
										if (result) {
											$.get("${ctx}/basicdata/dict/submit/"+ selectedId,
												function(data) {
													if ("success" == data.status) {
														if (null != selectedOrgNode) {
															$("#dictTable").jqGrid('setGridParam',
																	{postData : {"parentId" : selectedOrgNode.id}})
																						.trigger("reloadGrid");
														} else {
															$("#dictTable").jqGrid().trigger("reloadGrid");
														}
													} else if ("error" == data.status) {
														bootbox.alert(data.msg);
													}
												}, "json");
												}
									});
						}
					});

	//var selr = jQuery(grid_selector).jqGrid('getGridParam','selrow');
	//高度控制
	//$(".ztree").parents(".widget-box").css("height",$(".ztree").parents(".row").height());
	$("#dict_add_dialog_save").on('click', function() {
		$("#dict_add_form").ajaxSubmit({
			dataType : 'json',
			success : function(data) {
				if ("success" == data.status) {
					$('#dict_add_dialog').modal("hide");

					myRefresh('save');
				} else {
					bootbox.alert(data.msg);
				}
			}
		});
	});
	$("#dict_edit_dialog_save").on('click', function() {
		$("#dict_edit_form").ajaxSubmit({
			dataType : 'json',
			success : function(data) {
				if ("success" == data.status) {
					$('#dict_edit_dialog').modal("hide");

					myRefresh('update');
				} else {
					bootbox.alert(data.msg);
				}
			}
		});
	});

})