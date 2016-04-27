<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cm" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<form id="addNew_form" class="form-horizontal" action="${ctx}/quartz/task/save" method="post">
<div class="row">
	<cm:token/>
	<div class="form-group col-md-12" >
		<lable class="col-md-2" for="productType"><span style="color: red">*</span>任务名称：</lable>
		<div class="col-md-10">
		<select id="name" name="job.id" class="col-xs-10 col-sm-8">
		  <c:forEach items="${listJob }" var="list">
		    <option value="${list.id }" >${list.jobName }</option>
		  </c:forEach>
		</select>
		</div> 
	</div>
	<div class="form-group col-md-12" >
		<lable class="col-md-2" for="contractNumber"><span style="color: red">*</span>计划名称：</lable>
		<div class="col-md-10">
			<input type="text" id="schedulerName" name="schedulerName" required class="col-xs-10 col-sm-8" />
			<input type="hidden" id="day" name="day">
		</div>
	</div>
		<h4 class="header green celarfix col-md-12" >
			执行计划的方式&nbsp;&nbsp;
		</h4>
	<div class="form-group col-md-12 allClass" id="1" >
		<div class="col-xs-12 col-sm-2" for="amount">
		<div class="radio">
		<label>
			<input  name="type" type="radio" class="ace" value="1" />
			<span class="lbl">一次  | 开始</span>
		</label>
	    </div>
	    </div>
	    <div class="col-xs-12 col-sm-6 " for="startDate">
	    <input id="startDateOnce" name="startDate" type="text" class="input-xlarge form-control bootstrap-timepicker Once" required readonly="readonly"/>
		</div>
	</div>
	<div class="form-group col-md-12  allClass" id="2">
		<div class="col-xs-12 col-sm-2" for="amount">
		<div class="radio">
		<label>
			<input  name="type" type="radio" class="ace" value="2" />
			<span class="lbl">每隔  | </span>
		</label>
	    </div>
	    </div>
	    <div class="col-xs-12 col-sm-2" for="timeInterval">
	    <input type="text" id="timeInterval" name="timeInterval" required class="input-xlarge timeInterval"/>
		</div>
		<div class="col-xs-12 col-sm-2" for="unit">
		<select id="unit" name="unit" class="form-control timeInterval" required>
		   <option value="">  </option>
		   <option value="1">秒</option>
		   <option value="2">分</option>
		   <option value="3">小时</option>
		</select>
		</div>
		<div class="col-xs-12 col-sm-2" for="unit">
			<span class="lbl">重复次数：</span>
		</div>
		<div class="col-xs-12 col-sm-3" for="unit">
		<label>
			<input type="text" id="repeatCount" name="repeatCount" class="form-control timeInterval" />
		</label>
		</div>
	</div>
	<div class="form-group col-md-12  allClass" id="3">
		<div class="col-xs-12 col-sm-2" for="amount">
		<div class="radio">
		<label>
			<input  name="type" type="radio" class="ace" value="3" />
			<span class="lbl">每天  | </span>
		</label>
	    </div>
	    </div>
	    <div class="col-xs-12 col-sm-6" for="startDate">
	    <input type="text" id="startDateEveryDay" name="runTime"  class="input-xlarge form-control day timePicker" required />
		</div>
	</div>
	<div class="form-group col-md-12 allClass" id="4">
		<div class="col-xs-12 col-sm-2" for="amount">
		<div class="radio">
		<label>
			<input  name="type" type="radio" class="ace" value="4" />
			<span class="lbl">每周  | </span>
		</label>
	    </div>
	    </div>
	    <div class="col-xs-12 col-sm-6" for="startDate">
	    <div class="checkbox week">
			<label><input type="checkbox" id="day1" name="days"  class="ace week" value="2"/><span class="lbl">星期一</span></label>
			<label><input type="checkbox" id="day2" name="days"  class="ace week" value="3"/><span class="lbl">星期二</span></label>
			<label><input type="checkbox" id="day3" name="days"  class="ace week" value="4"/><span class="lbl">星期三</span></label>
			<label><input type="checkbox" id="day4" name="days"  class="ace week" value="5"/><span class="lbl">星期四</span></label>
			<label><input type="checkbox" id="day5" name="days"  class="ace week" value="6"/><span class="lbl">星期五</span></label>
			<label><input type="checkbox" id="day6" name="days"  class="ace week" value="7"/><span class="lbl">星期六</span></label>
			<label><input type="checkbox" id="day7" name="days"  class="ace week" value="1"/><span class="lbl">星期日</span></label>
		</div>
		</div>
		<div class="col-xs-12 col-sm-3" for="amount">
			<input type="text" id="startDateWeek" name="runTime"  class="input-xlarge form-control week timePicker" required  />
	    </div>
	</div>
	<div class="form-group col-md-12 allClass" id="5">
		<div class="col-xs-12 col-sm-2" for="amount">
		<div class="radio">
		<label>
			<input  name="type" type="radio" class="ace" value="5" />
			<span class="lbl">每月  | </span>
		</label>
	    </div>
	    </div>
	    <div class="col-xs-12 col-sm-6" >
	    <div class="control-group">
	    <div class="checkbox" >
			<label><input type="checkbox" id="day1" name="days"  class="ace month" value="1"/><span class="lbl">1</span></label>
			<label><input type="checkbox" id="day2" name="days"  class="ace month" value="2"/><span class="lbl">2</span></label>
			<label><input type="checkbox" id="day3" name="days"  class="ace month" value="3"/><span class="lbl">3</span></label>
			<label><input type="checkbox" id="day4" name="days"  class="ace month" value="4"/><span class="lbl">4</span></label>
			<label><input type="checkbox" id="day5" name="days"  class="ace month" value="5"/><span class="lbl">5</span></label>
			<label><input type="checkbox" id="day6" name="days"  class="ace month" value="6"/><span class="lbl">6</span></label>
			<label><input type="checkbox" id="day7" name="days"  class="ace month" value="7"/><span class="lbl">7</span></label>
			<label><input type="checkbox" id="day8" name="days"  class="ace month" value="8"/><span class="lbl">8</span></label>
			<label><input type="checkbox" id="day9" name="days"  class="ace month" value="9"/><span class="lbl">9</span></label>
			<label><input type="checkbox" id="day10" name="days"  class="ace month" value="10"/><span class="lbl">10</span></label>
			<label><input type="checkbox" id="day11" name="days"  class="ace month" value="11"/><span class="lbl">11</span></label>
			<label><input type="checkbox" id="day12" name="days"  class="ace month" value="12"/><span class="lbl">12</span></label>
			<label><input type="checkbox" id="day13" name="days"  class="ace month" value="13"/><span class="lbl">13</span></label>
			<label><input type="checkbox" id="day14" name="days"  class="ace month" value="14"/><span class="lbl">14</span></label>
			<label><input type="checkbox" id="day15" name="days"  class="ace month" value="15"/><span class="lbl">15</span></label>
			<label><input type="checkbox" id="day16" name="days"  class="ace month" value="16"/><span class="lbl">16</span></label>
			<label><input type="checkbox" id="day17" name="days"  class="ace month" value="17"/><span class="lbl">17</span></label>
			<label><input type="checkbox" id="day18" name="days"  class="ace month" value="18"/><span class="lbl">18</span></label>
			<label><input type="checkbox" id="day19" name="days"  class="ace month" value="19"/><span class="lbl">19</span></label>
			<label><input type="checkbox" id="day20" name="days"  class="ace month" value="20"/><span class="lbl">20</span></label>
			<label><input type="checkbox" id="day21" name="days"  class="ace month" value="21"/><span class="lbl">21</span></label>
			<label><input type="checkbox" id="day22" name="days"  class="ace month" value="22"/><span class="lbl">22</span></label>
			<label><input type="checkbox" id="day23" name="days"  class="ace month" value="23"/><span class="lbl">23</span></label>
			<label><input type="checkbox" id="day24" name="days"  class="ace month" value="24"/><span class="lbl">24</span></label>
			<label><input type="checkbox" id="day25" name="days"  class="ace month" value="25"/><span class="lbl">25</span></label>
			<label><input type="checkbox" id="day26" name="days"  class="ace month" value="26"/><span class="lbl">26</span></label>
			<label><input type="checkbox" id="day27" name="days"  class="ace month" value="27"/><span class="lbl">27</span></label>
			<label><input type="checkbox" id="day28" name="days"  class="ace month" value="28"/><span class="lbl">28</span></label>
			<label><input type="checkbox" id="day29" name="days"  class="ace month" value="29"/><span class="lbl">29</span></label>
			<label><input type="checkbox" id="day30" name="days"  class="ace month" value="30"/><span class="lbl">30</span></label>
			<label><input type="checkbox" id="day31" name="days"  class="ace month" value="31"/><span class="lbl">31</span></label>
		</div>
		</div>
		</div>
		<div class="col-xs-12 col-sm-3" for="amount">
			<input type="text" id="startDateMonth" name="runTime"  class="input-xlarge form-control  month timePicker"  />
	    </div>
		
	</div>
	<div class="form-group col-md-12 allClass" id="6">
		<div class="col-xs-12 col-sm-2" for="amount">
		<div class="radio">
		<label>
			<input name="type" type="radio" class="ace" value="6" />
			<span class="lbl">cron表达式 </span>
		</label>
	    </div>
	    </div>
	    <div class="col-xs-12 col-sm-6" for="startDate">
	    <input type="text" id="cron" name="cron" class="input-xlarge cron" required/>
		</div>
	</div>
	</div>
</form>
<script type="text/javascript">
$(function() {
	$(".bootstrap-timepicker").datetimepicker({
		format: "YYYY-MM-DD HH:mm:ss",
		autoclose: true,
		showSecond: true
	}).next().on(ace.click_event, function(){
		$(this).prev().focus();
	});
	$('.timePicker').timepicker({
		minuteStep: 1,
		showSeconds: false,
		showMeridian: false,
		showInputs:false
	}).next().on(ace.click_event, function(){
		$(this).prev().focus();
	});
	$('.timePicker').val("");
	$("input[name='type']").bind("click",function(){
		var val = this.value;
		$(".allClass").each(function(){
			var idVal = ($(this).attr("id"));
			if(val == idVal){
				$(".allClass").find(":input").each(function(){
					if(this.name!='type'&&this.name!='days'){
						$(this).val("");
						$(this).attr("disabled",true);
					}
					if(this.name=='days'){
						$(this).removeAttr("checked");
						$(this).attr("disabled",true);
					}
				});
				$(".allClass[id='"+val+"']").find(":input").removeAttr("disabled");
			} 
		});
	});
	
})


</script>