var grid_render_id = "#taskQuartzTable";
var pager_render_id = "#taskQuartzTablepager";
$(function() {
	
	resizeGrid(grid_render_id);
	$(grid_render_id).jqGrid(
			{
				caption : "定时任务列表",
				height : 'auto',
				url : '${ctx}/quartz/task/listData',
				datatype : "json",
				colNames : [ 'ID','jobid','任务名称','任务描述', '执行计划','操作'],
				colModel : [ {name : 'id',index : 'id',hidden : true},
				             {name : 'job.id',index : 'job.id',hidden : true},
				             {name : 'job.jobName',index : 'job.jobName', width : 180},
				             {name : 'schedulerName',index : 'schedulerName', width : 250},
				             {name : 'desc',index : 'desc',align:"left", width : 200},
				             {name : 'id', index: 'id', align:"center", width : 250,formatter: function (cellvalue, options, rowObject) {
				            	 var actions = "<a href='javascript:void(0)'  onclick=\"executeJob('" + rowObject.id + "','"+rowObject.job.className+"','triggerJob')\">运行一次</a> " +
				            	 		"| <a href='javascript:void(0)'  onclick=executeJob('" + rowObject.id + "','"+rowObject.job.className+"','interrupt')>中止</a> " +
				            	 		"| <a href='javascript:void(0)'  onclick=executeJob('" + rowObject.id + "','"+rowObject.job.className+"','pauseJob')>暂停</a> " +
				            	 		"| <a href='javascript:void(0)'  onclick=executeJob('" + rowObject.id + "','"+rowObject.job.className+"','resumeJob')>恢复</a> " +
				            	 		"| <a href='javascript:void(0)'  onclick=remove2('" + rowObject.id + "')>删除</a> " +
				            	 		"| <a href='javascript:void(0)'  onclick=view('" + rowObject.id + "','"+rowObject.job.className+"','showJob')>查看</a> " +
				            	        "| <a href='javascript:void(0)'  onclick=edit('" + rowObject.id + "')>计划设置</a> ";
				            	 return actions;
				             }}
				            ],
				viewrecords : true,
				pager : pager_render_id,
				altRows : true,
				sortname : "job.id",
				autowidth : true,
				shrinkToFit: true,
				rownumbers : true,
				multiselect : false,
				multiboxonly : true,
				rows : 50,
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
						$('.frozen-bdiv',$("#gbox_billTable")).css({'background-color':'white'});
						$('.frozen-div',$("#gbox_billTable")).css({'height':'auto'});
						
						var $td = $(".ui-jqgrid-ftable",$("#gbox_billTable")).find("td[aria-describedby='billTable_amount']");
						var $nextTd = $td.next();
						if($.trim($nextTd.text())=="" && $td.attr("rowSpan")!=2){
							var width = $nextTd.width() + $td.width();
							$nextTd.remove();
							$td.attr("rowSpan", 2);
							$td.width(width);
						}
					}, 0);
				}
			});
	$(grid_render_id).jqGrid('setFrozenColumns');
	//navButtons
	$(grid_render_id).jqGrid(
			'navGrid',
			pager_render_id,
			{ //navbar options
				alertcap : "消息",
				alerttext : "请您先选择要操作的记录！",
				add : true,
				addtext : "新建",
				addfunc : addNew,
				addicon : 'ace-icon fa fa-plus-circle purple',
				edit : true,
				edittext : "计划设置",
				editfunc : edit,
				editicon : 'ace-icon fa fa-pencil blue',
				del : true,
				deltext : "删除",
				delfunc : remove2,
				delicon : 'ace-icon fa fa-trash-o red',
				search : false,
				searchtext : "查询",
				searchicon : 'icon-search orange',
				refresh : true,
				refreshtext : "刷新",
				refreshicon : 'ace-icon fa fa-refresh green',
				view : true,
				viewtext : "查看",
				viewfunc : view,
				viewicon : 'ace-icon fa fa-search-plus grey',
			},
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
			
	);
	
})
function addNew() {
	$("#add_dialog_content").load('${ctx}/quartz/task/addNew');
	$("#add_dialog").modal({backdrop: 'static', keyboard: false}).modal("show");
}
$("#add_dialog_save").on('click', function() {
	var dayVal = "";
	$("input[name='days']:checked").each(function(){
		dayVal = dayVal + (this.value+',');
	});
	 if(0 != dayVal.length){
		 dayVal = dayVal.substring(0,dayVal.length-1);
		 $("#day").val(dayVal);
	}
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
		},
		error : function(data){
		}
	});
});
$("#edit_dialog_save").on('click', function() {
	var dayVal ="";
	$("input[name='days']:checked").each(function(){
		dayVal += ($(this).val()+',');
	});
	 if(0 != dayVal.length){
		 dayVal = dayVal.substring(0,dayVal.length-1);
		 $("#day").val(dayVal);
	 }
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
		},
		error : function(data){
		}
	});
});
function edit(id) {
	$("#edit_dialog_content").load('${ctx}/quartz/task/edit?id=' + id);
	$("#edit_dialog").modal({backdrop: 'static', keyboard: false}).modal("show");
}
function remove2(id) {
	var rowObj = $(grid_render_id).jqGrid("getRowData",id);
	bootbox.confirm("您确认要删除吗?", function(result) {
		if (result) {
			$.get("${ctx}/quartz/task/remove/" + id,
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
function view(id) {
	$("#view_dialog_content").load('${ctx}/quartz/task/view?id=' + id);
	$("#view_dialog").modal({backdrop: 'static', keyboard: false}).modal("show");
}
function executeJob(id,className,type){
	var message = "";
	if(type=="triggerJob"){
		message = "您确认要运行一次任务吗?";
	}else if(type=="interrupt"){
		message = "您确认要停止运行任务吗?";
	}else if(type=="pauseJob"){
		message = "您确认要暂停任务吗?";
	}else if(type=="resumeJob"){
		message = "您确认要重新运行吗?";
	}else if(type=="cancelJob"){
		message = "您确认要取消运行吗?";
	}else if(type=="addJob"){
		message = "您确认要添加任务吗?";
	}else if(type=="showJob"){
		$("#edit_dialog_content").load('${ctx}/quartz/task/edit?id=' + id);
		$("#edit_dialog").modal({backdrop: 'static', keyboard: false}).modal("show");
		return;
	}
	bootbox.confirm(message, function(result) {
		if (result) {
			$.get("${ctx}/quartz/task/executeJob?id=" + id+"&type="+type+"&className="+className,
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