<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="cm" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="row">
	<div class="form-group col-md-12" >
		<lable class="col-md-2" for="productType"><span style="color: red">*</span>任务名称：</lable>
		<div class="col-md-5">
			${taskScheduler.job.jobName }
		</div> 
	</div>
	<div class="form-group col-md-12" >
		<lable class="col-md-2" for="contractNumber"><span style="color: red">*</span>计划名称：</lable>
		<div class="col-md-5">
			${taskScheduler.schedulerName }
		</div>
	</div>
		<h4 class="header green celarfix col-md-12" >
			执行计划的方式&nbsp;&nbsp;
		</h4>
	<div class="form-group col-md-12" >
		<div class="col-xs-12 col-sm-6" for="amount">
		${taskScheduler.desc }
		</div>
	</div>
	<h4 class="header green celarfix col-md-12" >
		执行计划&nbsp;&nbsp;
	</h4>
	<div class="form-group col-md-12" >
		<div class="col-xs-12 col-sm-2" style="width: 120px;">
		<label>
			<span class="lbl">开始时间:</span>
		</label>
		</div>
		<div class="col-xs-12 col-sm-2" style="width: 160px;">
		<label >
			<fmt:formatDate value="${startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
		</label>
		</div>
		<div class="col-xs-12 col-sm-2" style="width: 120px;">
		<label>
			<span class="lbl">结束时间:</span>
		</label>
		</div>
		<div class="col-xs-12 col-sm-2" style="width: 160px;">
		<label>
			<span class="lbl">
			<fmt:formatDate value="${endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</span>
		</label>
		</div>
	</div>
	<div class="form-group col-md-12" >
		<div class="col-xs-12 col-sm-2" style="width: 120px;">
		<label>
			<span class="lbl">状态:</span>
		</label>
		</div>
		<div class="col-xs-12 col-sm-2" style="width: 160px;">
		<label>
			<span class="lbl">
			<c:choose>
			<c:when test="${triggerState=='NONE'}">任务结束</c:when>
			<c:when test="${triggerState=='NORMAL'}">正常状态</c:when>
			<c:when test="${triggerState=='PAUSED'}">暂停状态</c:when>
			<c:when test="${triggerState=='COMPLETE'}">触发器完成</c:when>
			<c:when test="${triggerState=='BLOCKED'}">线程阻塞</c:when>
			<c:when test="${triggerState=='ERROR'}">出现错误</c:when>
			<c:otherwise>没有查询到状态</c:otherwise>
			</c:choose>
			</span>
		</label>
		</div>
		<div class="col-xs-12 col-sm-2" style="width: 120px;">
		<label>
			<span class="lbl">运行次数:</span>
		</label>
		</div>
		<div class="col-xs-12 col-sm-2" style="width: 160px;">
		<label >
			<span class="lbl">${timesTriggered}</span>
		</label>
		</div>
	</div>
	<div class="form-group col-md-12" >
		<div class="col-xs-12 col-sm-2" style="width: 120px;">
		<label>
			<span class="lbl">下次执行时间:</span>
		</label>
		</div>
		<div class="col-xs-12 col-sm-2" style="width: 160px;">
		<label>
			<span class="lbl">
				<fmt:formatDate value="${nextFireTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</span>
		</label>
		</div>
		<div class="col-xs-12 col-sm-2" style="width: 120px;">
		<label>
			<span class="lbl">上次执行时间:</span>
		</label>
		</div>
		<div class="col-xs-12 col-sm-2" style="width: 160px;">
		<label >
			<fmt:formatDate value="${previousFireTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
		</label>
		</div>
	</div>