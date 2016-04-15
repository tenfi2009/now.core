<%@ tag language="java" pageEncoding="UTF-8"%>
<!-- 标签name属性，必传字段 -->
<%@ attribute name="name" type="java.lang.String" required="true" %>
<!-- 标签id属性，必传字段 -->
<%@ attribute name="id" type="java.lang.String" required="true" %>
<!-- 如果是字典，字典分类是必须传过来的 -->
<%@ attribute name="category" type="java.lang.String" required="true" %>
<!-- 下拉框是否被选中，不是必选项 -->
<%@ attribute name="selectedValue" type="java.lang.String" required="false" %>
<!-- onchange事件 -->
<%@ attribute name="onchange" type="java.lang.String" required="false" %>
<!-- expression 其它的属性，事件通过该表达式直接添加 -->
<%@ attribute name="expression" type="java.lang.String" required="false" %>
<!-- 标签的作用域 -->
<%@ attribute name="scope" type="java.lang.String" required="false" %>
<!-- 是否显示请选择下拉框 -->
<%@ attribute name="displaySelectAll" type="java.lang.String" required="false" %>
<!-- 添加新样式 -->
<%@ attribute name="style" type="java.lang.String" required="false" %>
<!-- 添加新样式 -->
<%@ attribute name="myClass" type="java.lang.String" required="false" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<select id='${id }' name='${name }' <c:if test="${not empty onchange}">  onchange="${onchange}" </c:if> <c:if test="${not empty style}">style='${style }'</c:if><c:if test="${not empty myClass}">class='${myClass }'</c:if> ${expression }>
</select>
<script>

 $(function() {
	var data = $.dict.getItems("${category}");
	data = eval(data);
	var sel = "";
	 if(""=="${scope}"){
		  $("#${id} option","#page-content-area").remove(); 
		  sel = $("#${id}","#page-content-area");
	  }else{
		  sel = $("#${id}","#${scope}").empty();
		  sel = $("#${id}","#${scope}");
	  } 
	  var options ="";
	  if("true"=="${displaySelectAll}"){
		  options += "<option value=''>请选择</option>";
	  }
	  $.each( data, function(index, content)
			  {
		       if("${selectedValue }"==undefined){
		    	   options+="<option value="+content.code+">"+content.name+"</option>";
		       }else{
		    	   if("${selectedValue }"==content.code){
		    		   options+="<option value="+content.code+" selected>"+content.name+"</option>";
		    	   }else{
		    		   options+="<option value="+content.code+">"+content.name+"</option>";
		    	   }
		       }
			  });
	  sel.html(options);
}) 
</script>