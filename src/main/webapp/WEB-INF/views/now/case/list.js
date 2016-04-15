var grid_render_id = "#caseTable";
var pager_render_id = "#caseTablepager";
$(function() {
	resizeGrid(grid_render_id);
	$(grid_render_id).jqGrid(
			{
				caption : "用户列表",
				subGrid : true,
				subGridOptions : {
					plusicon : "ace-icon fa fa-plus center bigger-110 blue",
					minusicon  : "ace-icon fa fa-minus center bigger-110 blue",
					openicon : "ace-icon fa fa-chevron-right center orange"
				},
			    subGrid: true,
			    subGridRowExpanded: function (subgridDivId, rowId) {
					var parentId = $(grid_render_id).getRowData(rowId).id;
					var subgridTableId = subgridDivId + "_t";
					$("#" + subgridDivId).html("<table id='" + subgridTableId + "'></table>");
					$("#" + subgridTableId).jqGrid({
						url : '${ctx}/module/drug/listData',
						datatype : "json",
						postData: {'parentId':parentId},
						colNames: ['药物名称', '单价金额', '数量', '总额'],
						colModel: [
							{name:'drugName',index:'drugName', width:100},
							{name:'feeAmount',index:'feeAmount', width:100},
							{name:'number',index:'number', width:100},
							{name:'feeSumAmount',index:'feeSumAmount', width:100}
						],
						sortname : "createTime",
						sortorder : "asc",
						jsonReader : {
							root : "result",
							page : "currPage",
							total : "totalPage",
							records : "totalCount"
						}
					});
				},
				height : 'auto',
				rowNum : 15,
				rowList : [ 15, 25, 50, 100 ],
				url : "${ctx}/module/case/listData",
				datatype : "json",
				colNames : [ 'ID','姓名','性别','年龄','手机号','操作'],
				colModel : [ {name : 'id',index : 'id',width : 20, hidden : true},
				             {name : 'name',index : 'name',align:"center",width : 30},
				             {name : 'sex',index : 'sex',align:"center",width : 20,formatter: function (cellvalue, options, rowObject) {
				            	 return $.dict.getNameByCode('sex', cellvalue);
				             }},
				             {name : 'age',index : 'age',align:"center",width : 20},
				             {name : 'mobileNumber',index : 'mobileNumber',align:"center",width : 30},
				             {name : 'operate', index: 'operate', align:"center",width : 60,formatter: function (cellvalue, options, rowObject) {
				            	 var parentId = rowObject["id"];
				            	 var actions = "<a href='javascript:void(0)'  onclick=\"edit('" + parentId + "')\">修改</a>";
				            	 actions += "|<a href='javascript:void(0)'  onclick=\"openDrug('" + parentId + "')\">开药</a>";
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
				addtitle : "",
				del : false,
				deltext : "删除",
				delicon : 'ace-icon fa fa-trash-o red',
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
			
	);

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
	
	function addNew() {
//		$("#page-content-area").load('${ctx}/module/case/addNew');
		$("#add_dialog_content").load('${ctx}/module/case/addNew');
		$("#add_dialog").modal("show");
	}
	
})

function edit(id) {
		$("#edit_dialog_content").load('${ctx}/module/case/edit?id=' + id);
		$("#edit_dialog").modal("show");
}

function openDrug(parentId) {
	$("#page-content-area").load('${ctx}/module/drug/list?parentId='+parentId);
//	$("#list_dialog_content").modal("show");
}

function getQueryFilter() {
	var rules = '';
	var form = document.getElementById("user-form-search");
    for (var i = 0; i < form.elements.length; i++) {
        var e = form.elements[i];
        if("" == e.value){
    		continue;
    	}
        
        if(e.name == 'account'){
        	rules += '{"t":"s","f":"account","op":"like","v":"'+e.value+'"},';
		}else if(e.name == 'code'){
			rules += '{"t":"s","f":"code","op":"like","v":"'+e.value+'"},';
		}else if(e.name == 'name'){
			rules += '{"t":"s","f":"name","op":"like","v":"'+e.value+'"},';
		}else if(e.name == 'org.id'){
			rules += '{"t":"s","f":"org.id","op":"eq","v":"'+e.value+'"},';
		}else if(e.name == 'isEnable'){
			rules += '{"t":"b","f":"isEnable","op":"eq","v":"'+e.value+'"},';
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
