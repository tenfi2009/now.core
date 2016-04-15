<%@ tag language="java" pageEncoding="UTF-8"%>
<!-- 标签name属性，必传字段 -->
<%@ attribute name="name" type="java.lang.String" required="true" %>
<!-- 标签id属性，必传字段 -->
<%@ attribute name="id" type="java.lang.String" required="true" %>
 <!-- url参数 -->
<%@ attribute name="url" type="java.lang.String" required="true" %> 
<!-- 标签的作用域 -->
<%@ attribute name="scope" type="java.lang.String" required="false" %>
<!-- expression 其它的属性，事件通过该表达式直接添加 -->
<%@ attribute name="expression" type="java.lang.String" required="false" %>
<!-- 下拉框是否被选中，不是必选项 -->
<%@ attribute name="selectedValue" type="java.lang.String" required="false" %>
<!-- onchange事件 -->
<%@ attribute name="onchange" type="java.lang.String" required="false" %>
<!-- 是否显示请选择下拉框 -->
<%@ attribute name="displaySelectAll" type="java.lang.String" required="false" %>
<!-- 添加新样式 -->
<%@ attribute name="style" type="java.lang.String" required="false" %>
<!-- 添加新样式 -->
<%@ attribute name="myClass" type="java.lang.String" required="false" %>
<!-- 自定义数据类型:id,account(可以放到option中),用法：myData="id,account" -->
<%@ attribute name="myData" type="java.lang.String" required="false" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<select id='${id }' name='${name }' <c:if test="${not empty onchange}">  onchange="${onchange}" </c:if> <c:if test="${not empty style}">style='${style }'</c:if> <c:if test="${not empty myClass}">class='${myClass }'</c:if> ${expression }>
</select>
<script>

 $(function() {
	function getMyData(content){
		var myDataOption = "",
			myData = "${myData}";
		if(myData != ""){
			myData = myData.split(",");
			for(var index in myData){
				var data = myData[index];
				if(data == "") continue;
				try{
					myDataOption += ("data-"+data+"="+content[data]+" ");
				}catch(e){
					myDataOption += ("data-"+data+"=");
				}
			}
		}
		return myDataOption;
	}
	 
	$.ajax({
		  type: "post",
		  cache: false,
		  url: "${ctx}/${url}",
		  dataType: "json",
		  success: function(data){
			  data = eval(data.data);
			  var sel = "";
			  if(""=="${scope}"){
				  $("#${id} option","#page-content-area").remove(); 
				  sel = $("#${id}");
			  }else{
				  sel = $("#${id}","#${scope}").empty();
				  sel = $("#${id}","#${scope}");
			  } 
			  var options ="",
			  	  myDataHtml = "";
			  	
			  if("false"!="${displaySelectAll}"){
				  options += "<option value=''>请选择</option>";
			  }
			  	$.each( data, function(index, content) {
				  		var optionName = "${dataName}",
				  			optionValue = content.id;
					   if("${selectedValue }"==""){
						   options+="<option value='"+optionValue+"'"+getMyData(content)+">"+content.name+"</option>";
					   }else{
						   if("${selectedValue }"==optionValue){
							   options+="<option value='"+optionValue+"' "+getMyData(content)+" selected>"+content.name+"</option>";
						   }else{
							   options+="<option value='"+optionValue+"' "+getMyData(content)+">"+content.name+"</option>";
						   }
					   }
				});
			  sel.html(options);
			  }
		});
}) 
</script>