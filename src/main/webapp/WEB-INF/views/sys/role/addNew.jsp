<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<form id="addNew_form" class="form-horizontal" action="sys/role/save" method="post">
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="code"><span style="color: red">*</span>角色编码：</label>
		<div class="col-sm-9">
			<input type="text" id="code" name="code" maxlength="32" required class="input-xlarge" />
		</div>
	</div>

	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="name"><span style="color: red">*</span>角色名称：</label>
		<div class="col-sm-9">
			<input type="text" id="name" name="name" maxlength="64" required class="input-xlarge" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="sortNo">排序号：</label>
		<div class="col-sm-9">
			<input type="text" id="sortNo" name="sortNo" digits="true" class="col-xs-10 col-sm-5" />
		</div>
	</div>
	<div>
		<label for="description">描述</label>
		<textarea class="form-control" id="description" name="description" ></textarea>
	</div>
</form>

<script type="text/javascript">
	$(function() {
		$("#addNew_form").validate({
			errorElement: 'div',
			errorClass: 'help-block',
			focusInvalid: false,
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
	});
</script>