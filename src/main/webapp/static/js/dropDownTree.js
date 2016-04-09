(function($) {  
var dropDownTree_divArr =[];
var dropDownTree_trees ={};
var dropDownTree_currcontext =null;
var display2HidenMap = {};
$.fn.dropDownTree = function(options) {
	var root = null;     
    var defaults = {    
    ssource:"",
    fieldId:"orgId",
    treeNodeClickCallback:"",
    selectEmptyText: ""	,
    queryAddon : true,
    selectParent:false     
  	};     
  	var opts = $.extend(defaults, options); 
  	var $this = $(this); 
  	display2HidenMap[this.attr("id")]= opts.fieldId;
  	var tree = dropDownTree_trees[options.ssource];
  	if(tree == undefined){
    	tree = createTreeObj();
	}
  	
	//新建下拉树dom对象，并把对象id加入缓存列表
	function createTreeObj(){
		var objId = Math.random()+"";
		objId = objId.substring(2,objId.length);
	  	var treeContainerDivId = "container_"+objId;
	  	var treeDivId = "tree_"+objId;
	  
	  	var	orgTreeObjDiv = "<div id='"+treeContainerDivId+"' class='zTreeDemoBackground left' style='background:white;width:270;height:300;z-index:2010;position:absolute;overflow: scroll'><ul id='"+treeDivId+"' class='ztree'></div>"; 
	  	
	  	if($("#"+treeContainerDivId).attr("id")==undefined){
	  		$(document.body).append(orgTreeObjDiv);
	  		dropDownTree_divArr.push(treeContainerDivId);
	  	}
	  	$("#"+treeContainerDivId).css({'display':'none','border-style':'solid','border-width':'1', 'border-color':'black','background-color':'white','overflow': 'scroll'});
	    
	  	var setting = {
				async: {
					enable: true,
					dataType: "json",
					url:opts.ssource,
					autoParam:["id"]
				},
				data: {
					simpleData: {
						enable: true,
						idKey: "id",
						pIdKey: "pId",
						rootPId: null
					}
				},
				callback: {
					onClick: treeNodeClick,
					onAsyncSuccess:asyncSuccess
				}
		};
	  	$.fn.zTree.init($("#"+treeDivId), setting);
	
		dropDownTree_trees[options.ssource] = treeContainerDivId;
		return treeContainerDivId;
	}
	
	function asyncSuccess(event, treeId, treeNode, msg) {
		var treeObj = $.fn.zTree.getZTreeObj(treeId);
		var nodes = treeObj.getNodes();
		var r=nodes[0];
		if(root==null && r){
			while(r.parentNode){r=r.parentNode};
			root = r;
			
			treeObj.expandNode(r, true, true, false);
			if(opts.selectEmptyText!=""){
				treeObj.addNodes(null, {"id":"","pId":null,"name":"("+opts.selectEmptyText+")"}, false);
				var emptyNode = treeObj.getNodeByParam("id", "");
				treeObj.moveNode(root, emptyNode, "prev");
			}
		}
	}
	
	
	$("body").unbind("mousedown");
	$("body").bind("mousedown", function(event){
			for(var i =0;i<dropDownTree_divArr.length;i++){
				if(event.target.id!=$this.attr("id")){
					var treeId = "tree"+dropDownTree_divArr[i].substring(9);
					if($(event.target.id).attr('type') != 'hidden' && event.target.id.indexOf(treeId)==-1)
						hiden(dropDownTree_divArr[i]);
				}
			}
    });
	
	function hiden(id){
		$("#"+id).fadeOut("fast");
	}
	
	function treeNodeClick(even,treeId,treeNode){
			if(treeNode){
		 		if((!treeNode.isParent)||(opts.selectParent)){
		 			if("" == treeNode.id){
		 				dropDownTree_currcontext.val("");
		 			}else{
		 				dropDownTree_currcontext.val(treeNode.name == "" ? treeNode.fullName : treeNode.name); //treeNode.id==""?"":
		 			}
			 		
			 		$("#"+display2HidenMap[dropDownTree_currcontext.attr("id")]).val(treeNode.id);
			 		hiden(tree);
			 		if (typeof opts.treeNodeClickCallback == "function") {
			 			opts.treeNodeClickCallback(even,treeId,treeId==""?"":treeNode,dropDownTree_currcontext);
			 		}
			 		else if (typeof treeNodeClickHandler == "function") { //remove this hardcode
						treeNodeClickHandler(dropDownTree_currcontext);
			 		}
		 		}
		 	}
	 	
	}
	$("#"+tree).blur(function(){
		$("#"+tree).css({'display':'none'});
	});
	this.show = function show(){
	    dropDownTree_currcontext = $this;
	    var width = $this.width()+10;
	    if(opts.queryAddon){
	    	width += 24;
	    }
		var offset = $this.offset();
  		var div_top = $this.offset().top +  $this.outerHeight();
		var div_left = $this.offset().left;
		$("#"+tree).css({'top':div_top,'left':div_left,'width':width});
		$("#"+tree).slideDown("fast");
	}
return this;
};     
})(jQuery); 