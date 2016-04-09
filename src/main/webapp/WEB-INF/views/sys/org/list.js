var selectedOrgNode = null;
	var setting = {
		async : {
			enable : true,
			dataType : "json",
		url : "${ctx}/sys/org/getOrgTree",
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
			//$("#orgTable").jqGrid('setGridParam',{postData:{"parentId":selectedOrgId}}).trigger("reloadGrid");
			$("#orgTable").jqGrid().trigger("reloadGrid");
		},
		onAsyncSuccess : function() {
			$("#orgTable").jqGrid().trigger("reloadGrid");
		}
	}
};
$(function() {
	$.fn.zTree.init($("#orgTree"), setting);

	var grid_selector = "#orgTable";
	var pager_selector = "#orgTablepager";
	
	//resize to fit page size
	resizeGrid(grid_selector);

	$(grid_selector).jqGrid({
		caption : "组织列表",
		height : 'auto',
		rowNum : 15,
		rowList : [ 15, 25, 50, 100 ],
		url : "${ctx}/sys/org/listData",
		datatype : "json",
		colNames : [ 'ID', '组织编码', '组织名称', '创建时间', '创建人', '状态', '描述' ],
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
			name : 'createTime',
			index : 'createTime',
			width : 120
		}, {
			name : 'creator',
			index : 'creator',
			width : 60
		}, {
			name : 'status',
			index : 'status',
			width : 60
		}, {
			name : 'description',
			index : 'description',
			width : 180
		} ],
		viewrecords : true,//是否在浏览导航栏显示记录总数
		//emptyrecords : "",// 当返回的数据行数为0时显示的信息。只有当属性 viewrecords 设置为ture时起作用
		footerrow : false, // 分页上添加一行，用于显示统计信息

		gridComplete : function() { // 当表格所有数据都加载完成，处理统计行数据
			/**
			var rowNum = $(this).jqGrid('getGridParam', 'records'); // 获取当前 jqGrid 的总记录数；
			if (rowNum > 0) {
				var options = {
					url : "test.action", // 默认是 form 的 action ，如果写的话，会覆盖 from 的 action.
					dataType : "json", //'xml', 'script', or 'json' ( 接受服务端返回的类型 .)
					type : "POST",
					success : function(data) { // 成功后调用方法
						$(grid_selector).footerData("set", {
							productName : "合计",
							productAmt : data.productAmtSum
						});
					}
				};
			$("#searchForm").ajaxSubmit(options);
			}
			**/
		},
		postData : {
			"parentId" : function() {
				return selectedOrgNode == null ? "" : selectedOrgNode.id
			}
		},
		pager : pager_selector,
		altRows : true,//设置为交替行表格,默认为false
		sortname : "createTime",
		autowidth : true,
		rownumbers : true,//添加左侧行号
		multiselect : false,
		//multikey: "ctrlKey",
		multiboxonly : true,
		jsonReader : {// 描述json期望数据格式的数组
			root : "result",
			page : "currPage",
			total : "totalPage",
			records : "totalCount"
		},
		loadComplete : function() {// loadComplete 为jqGrid加载完成，执行的方法
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
	$(grid_selector).jqGrid(
			'navGrid',
			pager_selector,
			{ //navbar options
				alertcap : "消息",
				alerttext : "请您先选择要操作的记录！",
				edit : true,
				edittext : "修改",
				editicon : 'ace-icon fa fa-pencil blue',
				editfunc : function(id) {
					$("#org_edit_dialog_content").load(
							'${ctx}/sys/org/edit?id=' + id);
					$("#org_edit_dialog").modal("show");
				},
				add : true,
				addtext : "新建",
				addicon : 'ace-icon fa fa-plus-circle purple',
				addfunc : function() {
					var selectedOrgId = selectedOrgNode == null ? ""
							: selectedOrgNode.id;
					$("#org_add_dialog_content").load(
							'${ctx}/sys/org/addNew?parentId='
									+ selectedOrgId);
					$("#org_add_dialog").modal("show");
				},
				del : true,
				deltext : "删除",
				delicon : 'ace-icon fa fa-trash-o red',
				delfunc : function(id) {
					bootbox.confirm("您确认要删除该组织吗?", function(result) {
						if (result) {
							$.get("${ctx}/sys/org/remove/" + id, function(data) {
								if ("success" == data.status) {
									var treeObj = $.fn.zTree.getZTreeObj("orgTree");
									var nodes = treeObj.getSelectedNodes();
									if (nodes.length > 0) {
										treeObj.reAsyncChildNodes(nodes[0], "refresh");
									} else {
										treeObj.reAsyncChildNodes(null, "refresh");
									}
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
				view : false,
				viewtext : "查看",
				viewicon : 'ace-icon fa fa-search-plus grey',
			}, {}, //  default settings for edit
			{}, //  default settings for add
			{}, // delete instead that del:false we need this
			{}, // search options
			{} /* view parameters*/
	).jqGrid(
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
					bootbox.confirm("您确认要提交该组织吗?", function(result) {
						if (result) {
							$.get("${ctx}/sys/org/submit/" + selectedId,
									function(data) {
										if ("success" == data.status) {
											$("#orgTable").jqGrid().trigger("reloadGrid");
										} else if ("error" == data.status) {
											bootbox.alert(data.msg);
										}
									}, "json");
						}
					});
				}
			});

	$(window).triggerHandler('resize.jqGrid');//trigger window resize to make the grid get the correct size

	//var selr = jQuery(grid_selector).jqGrid('getGridParam','selrow');
	$("#org_add_dialog_save").on('click', function() {
		$("#org_add_form").ajaxSubmit({
			dataType : 'json',
			success : function(data) {
				if ("success" == data.status) {
					$('#org_add_dialog').modal("hide");

					var treeObj = $.fn.zTree.getZTreeObj("orgTree");
					var nodes = treeObj.getSelectedNodes();
					if (nodes.length > 0) {
						treeObj.reAsyncChildNodes(nodes[0], "refresh");
					} else {
						treeObj.reAsyncChildNodes(null, "refresh");
					}
				} else {
					bootbox.alert(data.msg);
				}
			}
		});
	});

	$("#org_edit_dialog_save").on('click', function() {
		$("#org_edit_form").ajaxSubmit({
			dataType : 'json',
			success : function(data) {
				if ("success" == data.status) {
					$('#org_edit_dialog').modal("hide");
					var treeObj = $.fn.zTree.getZTreeObj("orgTree");
					var nodes = treeObj.getSelectedNodes();
					if (nodes.length > 0) {
						treeObj.reAsyncChildNodes(nodes[0], "refresh");
					} else {
						treeObj.reAsyncChildNodes(null, "refresh");
					}
				} else {
					bootbox.alert(data.msg);
				}
			}
		});
	});

})