jQuery(function($) {
	var grid_selector = "#drugTable";
	var pager_selector = "#drugTablepager";

	$(window).on('resize.jqGrid', function () {
		$(window).unbind("resize.jqGrid");
		var parent_width = $(grid_selector).closest('.page-content').width();
		$(grid_selector).jqGrid( 'setGridWidth', parent_width );
	})

	jQuery(grid_selector).jqGrid({
		url : "${ctx}/module/drug/listData?parentId=${parentId}",
		datatype : "json",
		height: 'auto',
		colNames:[' ','ID', '药物名称', '单价金额', '数量', '总额'],
		colModel:[
			{name:'myac',index:'', width:80, fixed:true, sortable:false, resize:false,
				formatter:'actions', 
				formatoptions:{ 
					keys:true,
					//delbutton: false,//disable delete button
					afterSave: function(rowId,data){
						var responseText = data.responseText;
						responseText = eval('('+responseText+')');
						var status = responseText.status;
						$(grid_selector).jqGrid().trigger("reloadGrid");
//						if(status == 'success'){
//							bootbox.alert("修改成功！");
//						}
					},
					delOptions:{recreateForm: true},
					//editformbutton:true, editOptions:{recreateForm: true, beforeShowForm:beforeEditCallback}
				}
			},
			{name:'id',index:'id', hidden : true, editable: true},
			{name:'drugName',index:'drugName', width:50,editable: true,edittype:"select",
				editoptions: {
					dataUrl:"${ctx}/basicdata/dict/getDictByParentCode?parentCode=drugName",
					dataEvents: [{type: 'change',fn: function(e) {
						   var ele = $("select[name=drugName]");
						   var rowId = ele.attr("id").split("_")[0];
						   var feeAmount = $.dict.getNameByCode('drugPrice', ele.val());
						   $("#"+rowId+"_feeAmount").val(feeAmount);
						   
						   var number = $("#"+rowId+"_number").val();
						   $("#"+rowId+"_feeSumAmount").val(new Decimal(feeAmount).mul(number));
					   }}]
				}},
			{name:'feeAmount',index:'feeAmount', width:50, editable: true},
			{name:'number',index:'number', width:20, editable: true,edittype:"select",
				editoptions: {
					dataUrl:"${ctx}/basicdata/dict/getDictByParentCode?parentCode=number",
					dataEvents: [{type: 'change',fn: function(e) {
						var ele = $("select[name=number]");
						var rowId = ele.attr("id").split("_")[0];
						var feeAmount = $("#"+rowId+"_feeAmount").val();
						$("#"+rowId+"_feeSumAmount").val(new Decimal(feeAmount).mul(ele.val()));
					}}]
				
				},
			},
			{name:'feeSumAmount',index:'feeSumAmount', width:50, editable: true}
		], 
		viewrecords : true,
		rowNum : 15,
		rowList : [ 15, 25, 50, 100 ],
		pager : pager_selector,
		altRows: true,
		jsonReader : {
			root : "result",
			page : "currPage",
			total : "totalPage",
			records : "totalCount"
		},
		//toppager: true,
		
//		multiselect: true,
		//multikey: "ctrlKey",
//        multiboxonly: true,
		footerrow: true,//分页上添加一行，用于显示统计信息
		loadComplete : function() {
			var table = this;
			setTimeout(function(){
				var rowData = $(grid_selector).jqGrid("getRowData");
				var feeSumAmount = new Decimal(0);
			    $(rowData).each(function(){
			    	feeSumAmount = Decimal.add(feeSumAmount, this["feeSumAmount"]);
			    });
				$(grid_selector).footerData("set",{number:"合计",feeSumAmount:feeSumAmount});
			}, 0);
		},
		pgbuttons: false,
		editurl: "${ctx}/module/drug/editOrDel?parentId=${parentId}",//nothing is saved
		caption: "药物列表"

		//,autowidth: true,

	});
	$(window).triggerHandler('resize.jqGrid');//trigger window resize to make the grid get the correct size
	
	function addNewLine(){
		$(grid_selector).jqGrid().addRowData(0,{},'last');
	}

	//navButtons
	jQuery(grid_selector).jqGrid('navGrid',pager_selector,
		{ 	//navbar options
			edit: true,
			editicon : 'ace-icon fa fa-pencil blue',
			add: true,
			addicon : 'ace-icon fa fa-plus-circle purple',
			addfunc : addNewLine,
			del: true,
			delicon : 'ace-icon fa fa-trash-o red',
			search: true,
			searchicon : 'ace-icon fa fa-search orange',
			refresh: true,
			refreshicon : 'ace-icon fa fa-refresh green',
			view: true,
			viewicon : 'ace-icon fa fa-search-plus grey',
		},
		{},
		{},
		{},
		{},
		{}
	)

});