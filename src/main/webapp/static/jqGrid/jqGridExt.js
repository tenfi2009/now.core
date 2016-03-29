jQuery.jqGridExt = {
	setStyleSearchFilters : function(form) {
		form.find('.delete-rule').val('X');
		form.find('.add-rule').addClass('btn btn-xs btn-primary');
		form.find('.add-group').addClass('btn btn-xs btn-success');
		form.find('.delete-group').addClass('btn btn-xs btn-danger');
	},

	setStyleSearchForm : function(form) {
		var dialog = form.closest('.ui-jqdialog');
		var buttons = dialog.find('.EditTable')
		buttons.find('.EditButton a[id*="_reset"]').addClass('btn btn-sm btn-info').find('.ui-icon').attr('class', 'ace-icon fa fa-retweet');
		buttons.find('.EditButton a[id*="_query"]').addClass('btn btn-sm btn-inverse').find('.ui-icon').attr('class', 'ace-icon fa fa-comment-o');
		buttons.find('.EditButton a[id*="_search"]').addClass('btn btn-sm btn-purple').find('.ui-icon').attr('class', 'ace-icon fa fa-search');
	},

	// it causes some flicker when reloading or navigating grid
	// it may be possible to have some custom formatter to do this as the
	// grid
	// is being created to prevent this
	// or go back to default browser checkbox styles for the grid
	setStyleCheckbox : function(table) {
		/**
		 * $(table).find('input:checkbox').addClass('ace') .wrap('<label
		 * />') .after('<span class="lbl align-top" />')
		 * 
		 * 
		 * $('.ui-jqgrid-labels th[id*="_cb"]:first-child')
		 * .find('input.cbox[type=checkbox]').addClass('ace') .wrap('<label
		 * />').after('<span class="lbl align-top" />');
		 */
	},

	// unlike navButtons icons, action icons in rows seem to be hard-coded
	// you can change them like this in here if you want
	updateActionIcons : function(table) {
		/**
		 * var replacement = { 'ui-icon-pencil' : 'icon-pencil blue',
		 * 'ui-icon-trash' : 'icon-trash red', 'ui-icon-disk' : 'icon-ok
		 * green', 'ui-icon-cancel' : 'icon-remove red' };
		 * $(table).find('.ui-pg-div span.ui-icon').each(function(){ var
		 * icon = $(this); var $class =
		 * $.trim(icon.attr('class').replace('ui-icon', '')); if($class in
		 * replacement) icon.attr('class', 'ui-icon '+replacement[$class]); })
		 */
	},

	// replace icons with FontAwesome icons like above
	updatePagerIcons : function(table) {
		var replacement = 
		{
			'ui-icon-seek-first' : 'ace-icon fa fa-angle-double-left bigger-140',
			'ui-icon-seek-prev' : 'ace-icon fa fa-angle-left bigger-140',
			'ui-icon-seek-next' : 'ace-icon fa fa-angle-right bigger-140',
			'ui-icon-seek-end' : 'ace-icon fa fa-angle-double-right bigger-140'
		};
		$('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){
			var icon = $(this);
			var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
			
			if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
		})
	},

	enableTooltips : function(table) {
		$('.navtable .ui-pg-button').tooltip({container:'body'});
		$(table).find('.ui-pg-div').tooltip({container:'body'});
	}
};