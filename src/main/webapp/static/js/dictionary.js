var DictObject = (function(){
    function DictObjectConstructor(){
    	this.init = function(){
    		initSource();
    		initData();
    	}
    	this.getDictionary = function(){
    		return dictionary;
    	}
    	this.getItems = function(){
    		return items;
    	}
    	this.getAmounts = function(){
    		return amounts;
    	}
    	this.getAccounts = function(){
    		return accounts;
    	}
    	this.getTradeTypes = function(){
    		return tradeTypes;
    	}
    	this.getBankNos = function(){
    		return bankNos;
    	}
    	/**
    	 * 初始化数据
    	 */
    	function initSource(){
    		$.ajax({
    			url: 'basicdata/dict/getDictAll',
    			data: {},
    			type: 'post',
    			async : false,/**同步*/
    			success : function(response) {
    				if(response){
    					source = eval('('+response+')');
    				}
    			},
    			error : function(response){
    				console && console.log("数据字典请求失败!");
    			}
    		});
//    		// 金额类型
//    		$.ajax({
//    			url: 'loan/productRemAcc/amounts',
//    			data: {},
//    			type: 'post',
//    			async : false,/**同步*/
//    			success : function(response) {
//    				if(response){
//    					var len = response.length;
//    					for(var i = 0;i < len;i++){
//    						var res = response[i];
//    						if(res){
//    							var code = res.amountCode;
//    							var name = res.amountName;
//    							amounts[code] = name;
//    						}
//    					}
//    				}
//    			},
//    			error : function(response){
//    				console && console.log("金额列表请求失败!");
//    			}
//    		});
//    		// 账户列表
//    		$.ajax({
//    			url: 'loan/productRemAcc/singleDatas',
//    			data: {},
//    			type: 'post',
//    			async : false,/**同步*/
//    			success : function(response) {
//    				if(response){
//    					var len = response.length;
//    					for(var i = 0;i < len;i++){
//    						var res = response[i];
//    						if(res){
//    							var code = res.amountCode;
//    							var name = res.amountName;
//    							accounts[code] = name;
//    						}
//    					}
//    				}
//    			},
//    			error : function(response){
//    				console && console.log("账户列表请求失败!");
//    			}
//    		});
//    		// 交易类型
//    		$.ajax({
//    			url: 'loan/trade/tradeTypes',
//    			data: {},
//    			type: 'post',
//    			async : false,/**同步*/
//    			success : function(response) {
//    				if(response){
//    					var len = response.length;
//    					for(var i = 0;i < len;i++){
//    						var res = response[i];
//    						if(res){
//    							var code = res.code;
//    							var name = res.name;
//    							tradeTypes[code] = name;
//    						}
//    					}
//    				}
//    			},
//    			error : function(response){
//    				console && console.log("交易类型列表请求失败!");
//    			}
//    		});
//    		// 银行账号
//    		$.ajax({
//    			url: 'loan/bill/amountNo',
//    			data: {},
//    			type: 'post',
//    			async : false,/**同步*/
//    			success : function(response) {
//    				if(response.data){
//    					var len = response.data.length;
//    					for(var i = 0;i < len;i++){
//    						var res = response.data[i];
//    						if(res){
//    							var code = res.id;
//    							var name = res.name;
//    							bankNos[code] = name;
//    						}
//    					}
//    				}
//    			},
//    			error : function(response){
//    				console && console.log("银行账号列表请求失败!");
//    			}
//    		});
    	}
    	/**
    	 * 组装数据
    	 */
    	function initData(){
    		var index = 0;
    		for(;index < source.length;index++){
    			var item = source[index],
    				parentCode = item.parentCode,
    				code = item.code,
    				name = item.name;
    			if(parentCode && parentCode != 'null' && code != undefined){
    				var childItem = items[parentCode];
    				if(!childItem) childItem = [];
    				var len = childItem.length;
    				childItem[len] = {'code':code,'name':name};
    				items[parentCode] = childItem;
    				dictionary[parentCode+code] = name;
    			}else{
    				if(!items[code]) items[code] = [];
    			}
    		}
    	}
    	var	source,
			dictionary = [],
			items = [],
			amounts = [],
			accounts = [],
			tradeTypes = [],
			bankNos = [];
    	if(dictionary.length == 0){
    		this.init();
    	}
    }
    
    var dictSingle;
    return function(){
    	if(!dictSingle){
    		dictSingle = new DictObjectConstructor();
    	}
    	if(!dictSingle.getDictionary()){
    		dictSingle.init();
    	}
    	return dictSingle;
    }
})();


if (typeof jQuery === 'undefined') { throw new Error('Dictionary\'s JavaScript requires jQuery') };

$.extend({dict:{}})
$.extend($.dict,{
	/**
	 * 根据code获取name
	 * usage:$.dict.getNameByCode('payFlag',cellvalue);
	 * 	  or $.dict.getNameByCode('payFlag',cellvalue,'默认值');
	 */
	getNameByCode : function(category, code, defaultValue){
		var dictionary = new DictObject().getDictionary();
		return dictionary[category+code] ? dictionary[category+code] : (defaultValue ? defaultValue : '');
	},
	/**
	 * 根据category获取items
	 * usage:$.dict.getItems('payFlag')
	 */
	getItems : function(category){
		var items = new DictObject().getItems();
		return items[category] ? items[category] : false; 
	},
	getBankNo : function(code){
		var bankNos = new DictObject().getBankNos();
		return bankNos[code] ? bankNos[code] : "";
	}
})