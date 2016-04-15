<%@ tag language="java" pageEncoding="UTF-8"%>
<!-- 标签id属性，必传字段 -->
<%@ attribute name="code" type="java.lang.String" required="true" %>
<!-- 标签id属性，必传字段 -->
<%@ attribute name="lableid" type="java.lang.String" required="true" %>
<!-- 如果是字典，字典分类是必须传过来的 -->
<%@ attribute name="category" type="java.lang.String" required="true" %>

<lable id="${lableid }"></lable>
<script>
	 var name = $.dict.getNameByCode("${category}","${code}");
	 $("#${lableid}").replaceWith(name);
</script>
