var grid_render_id = "#roleTable";
var pager_render_id = "#roleTablepager";
$(function() {
	resizeGrid(grid_render_id);
	$(grid_render_id).jqGrid(
			{
				caption : "角色列表",
				height : 'auto',
				rowNum : 15,
				rowList : [ 15, 25, 50, 100 ],
				url : "sys/role/listData",
				datatype : "json",
				colNames : [ 'ID', '角色编码', '角色名称', '创建时间', '创建人', '状态', '更新时间','操作'],
				colModel : [ {name : 'id',index : 'id',hidden : true},
				             {name : 'code',index : 'code',width : 60},
				             {name : 'name',index : 'name',width : 80},
				             {name : 'createTime',index : 'createTime',width : 120},
				             {name : 'creator',index : 'creator',width : 60},
				             {name : 'status',index : 'status',width : 60},
				             {name : 'updateTime',index : 'updateTime',width : 120},
				             {name : 'id', index: 'id', width : 120, align:"center",formatter: function (cellvalue, options, rowObject) {
				            	 var actions = "<a href='javascript:void(0)'  onclick=\"assignResource('" + cellvalue + "')\">分配资源权限</a>";
				            	 actions += "|<a href='javascript:void(0)'  onclick=\"edit('" + cellvalue + "')\">修改</a>";
				            	 actions += "|<a href='javascript:void(0)'  onclick=\"remove2('" + cellvalue + "')\">删除</a>";
				            	 return actions;
				             }}
				            ],
				viewrecords : true,
				pager : pager_render_id,
				altRows : true,
				sortname : "createTime",
				autowidth : true,
				rownumbers : true,
				multiselect : false,
				//multikey: "ctrlKey",
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
			},
			{}/* view parameters*/
			
	).jqGrid('navButtonAdd',pager_render_id,{
		   caption:"提交", 
		   title : "提交用户信息",
		   buttonicon:"fa fa-check-circle", 
		   onClickButton: submit,
		   position:"before refresh_userTable"
		});

	
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

function addNew() {
	$("#add_dialog_content").load('sys/role/addNew');
	$("#add_dialog").modal("show");
}

function edit(id) {
	$("#edit_dialog_content").load('sys/role/edit?id=' + id);
	$("#edit_dialog").modal("show");
}

function assignResource(id) {
	$("#assign_resource_dialog_content").load('sys/role/assignResource?id=' + id);
	$("#assign_resource_dialog").modal("show");
}

function remove2(id) {
	bootbox.confirm("您确认要删除该角色吗?", function(result) {
		if (result) {
			$.get("sys/role/remove/" + id,
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

function submit(id) {
	var selectedId = jQuery(grid_render_id).jqGrid('getGridParam','selrow');
	if(null == selectedId || "" == selectedId){
		bootbox.alert("请选择要提交的记录！");
		return;
	}
	bootbox.confirm("您确认要提交该角色信息吗?", function(result) {
		if (result) {
			$.get("sys/role/submit/" + selectedId,
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

function getQueryFilter() {
	var rules = '';
	var form = document.getElementById("role-form-search");
    for (var i = 0; i < form.elements.length; i++) {
        var e = form.elements[i];
        if("" == e.value){
    		continue;
    	}
        
        if(e.name == 'code'){
			rules += '{"t":"s","f":"code","op":"like","v":"'+e.value+'"},';
		}else if(e.name == 'name'){
			rules += '{"t":"s","f":"name","op":"like","v":"'+e.value+'"},';
		}else if(e.name == 'status'){
			rules += '{"t":"com.tenfi.enums.Status","f":"status","op":"eq","v":"'+e.value+'"},';
		}
    } 
    if(0 == rules.length){
    	return "";
    }
    rules = rules.substring(0,rules.length-1);
    return "{\"groupOp\":\"AND\",\"rules\":["+rules+"]}";
};

function search(){
	$(grid_render_id).jqGrid('setGridParam',{postData:{"filters":getQueryFilter()}}).trigger("reloadGrid", [{page:1}]);
}
