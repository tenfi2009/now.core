/**
 * 调整表格宽度,避免出现横向滚动条
 * @param grid_render_id
 * @param func
 */
function resizeGrid(grid_render_id,func){
	//resize to fit page size
	  $(window).on('resize.jqGrid', function () {
		  if(func) func();
		  $(grid_render_id).jqGrid( 'setGridWidth', $(".page-content").width() );
	  })
	  //resize on sidebar collapse/expand
	  var parent_column = $(grid_render_id).closest('[class*="col-"]');
	  $(document).on('settings.ace.jqGrid' , function(ev, event_name, collapsed) {
		  if( event_name === 'sidebar_collapsed' || event_name === 'main_container_fixed' ) {
			  //setTimeout is for webkit only to give time for DOM changes and then redraw!!!
			  setTimeout(function() {
				  if(func) func();
				  $(grid_render_id).jqGrid( 'setGridWidth', parent_column.width() );
			  }, 0);
		  }
	  });
	  /**
	  $('.modal').off().on('show.bs.modal', function (e) {
			$('.daterangepicker').hide();
	  })
	  $('.daterangepicker').hide();
	  **/
}

/**
 * 校验表单输入是否合法
 * @param formId
 */
function validate(formId, rules, messages) {
	if (rules ==null || rules == "") {
		rules = {};
	}
	if (messages ==null || messages == "") {
		messages = {};
	}
	$(formId).validate({
		rules : rules,
		messages : messages,
		errorElement: 'div',
		errorClass: 'help-block',
		focusInvalid: true,
		invalidHandler: function (event, validator) { //display error alert on form submit   
			$('.alert-danger', $('.login-form')).show();
		},
		
		highlight: function (e) {
			$(e).closest('.form-group').removeClass('has-info').addClass('has-error');
		},
		
		success: function (e) {
			$(e).closest('.form-group').removeClass('has-error').addClass('has-info');
			$(e).remove();
		},
		
		errorPlacement: function (error, element) {
			if(element.is(':checkbox') || element.is(':radio')) {
				var controls = element.closest('div[class*="col-"]');
				if(controls.find(':checkbox,:radio').length > 1) controls.append(error);
				else error.insertAfter(element.nextAll('.lbl:eq(0)').eq(0));
			}
			else if(element.is('.select2')) {
				error.insertAfter(element.siblings('[class*="select2-container"]:eq(0)'));
			}
			else if(element.is('.chosen-select')) {
				error.insertAfter(element.siblings('[class*="chosen-container"]:eq(0)'));
			}
			else error.insertAfter(element.parent());
		},
		
		submitHandler: function (form) {
		}
	});
}
