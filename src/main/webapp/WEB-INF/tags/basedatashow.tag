<%@ tag language="java" pageEncoding="UTF-8"%>
<!-- 标签tableName属性，必传字段  表 名 -->
<%@ attribute name="tableName" type="java.lang.String" required="true" %>
<!-- 表id属性值，必传字段 -->
<%@ attribute name="id" type="java.lang.String" required="true" %>
<!-- 表id属性值，必传字段 -->
<%@ attribute name="idType"  type="java.lang.String" required="true" %>
 <!-- 显示列-->
<%@ attribute name="showColumn" type="java.lang.String" required="true" %> 
<!-- 标签id属性，必传字段 -->
<%@ attribute name="lableid" type="java.lang.String" required="true" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<lable id="${lableid }"></lable>
<script>

 $(function() {
	$.ajax({
		  type: "post",
		  cache: false,
		  url: "${ctx}/basicdata/dict/getBaseDataAll?table=${tableName}&id=${id}&showColumn=${showColumn}&idType=${idType}",
		  dataType: "json",
		  success: function(data){
			  $("#${lableid}").replaceWith(data.data);
		  }  
		});
}) 
</script>