package com.tenfi.quartz.web;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.googlecode.genericdao.search.Sort;
import com.matrix.core.exception.BizException;
import com.tenfi.core.model.Page;
import com.tenfi.core.web.AjaxResult;
import com.tenfi.quartz.model.QuartzJob;
import com.tenfi.quartz.model.QuartzJobScheduler;
import com.tenfi.quartz.service.QuartzJobSchedulerService;
import com.tenfi.quartz.service.QuartzJobService;
@Controller
@RequestMapping(value = "/quartz/task")
public class QuartJobSchedulerController  extends com.tenfi.core.web.BaseController {
	@Autowired
	private QuartzJobSchedulerService service;
	
	@Autowired
	private QuartzJobService quartzJobService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		super.initBinder(binder);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		
		DateFormat dateFormat1 = new DateFormatTime("HH:mm");
		CustomDateEditor orderDateEditor = new CustomDateEditor(dateFormat1,true);
		binder.registerCustomEditor(Time.class, "runTime", orderDateEditor);
	}
	public class DateFormatTime extends SimpleDateFormat {
		 /** 
		 * <p>description:  </p>
		 * @date 2016年1月21日 下午2:09:49
		 */
		private static final long serialVersionUID = 1L;
		public DateFormatTime(String pattern){
		     super(pattern);
		 }
		public Time parse(String value) throws ParseException{
			return Time.valueOf(value+":00");
		}
	}
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String list(Model model){
		return "quartz/list";
	}
	/**
	 * @Description：查询数据 
	 * @author：root
	 * @date：2015年12月23日 20:14:31
	 * @param type
	 * @param currPage
	 * @param pageSize
	 * @param sortField
	 * @param sortType
	 * @param filters
	 * @return
	 * @return Object
	 */
	@RequestMapping(value="/listData", method = RequestMethod.GET)
	@ResponseBody
	public Object listData(Integer type,
			@RequestParam(value = "page", defaultValue = "1") Integer currPage,
			@RequestParam(value = "rows", defaultValue = "20") Integer pageSize,
			@RequestParam(value = "sidx") String sortField,
			@RequestParam(value = "sord") String sortType,
			String filters){
		Page<QuartzJobScheduler> page = new Page<QuartzJobScheduler>();
		page.setPageSize(pageSize);
		page.setCurrPage(currPage);
		if(StringUtils.isNotEmpty(sortField)){
			page.getSorts().add(new Sort(sortField, "desc".equalsIgnoreCase(sortType) ? true : false));
		}
		Map<String,String> queryParams = new HashMap<String,String>();
		if(StringUtils.isNotEmpty(filters)){
			queryParams.put("filters", filters);
		}

		page = service.findPage(page, queryParams);
		return page;
	}
//	@SpringMVCToken(genToken=true)
	@RequestMapping(value="/addNew", method = RequestMethod.GET)
	public String addNew(Model model) {
		List<QuartzJob> list = (List<QuartzJob>)quartzJobService.getAll();
		model.addAttribute("listJob", list);
		return "quartz/addNew";
	}
//	@SpringMVCToken(validToken=true)
	@RequestMapping(value="/save", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult save(QuartzJobScheduler quartzJobScheduler) {
		AjaxResult rs = new AjaxResult();
		service.saveJobScheduler(quartzJobScheduler);
		rs.setStatus(AjaxResult.STATUS_SUCCESS);
		rs.setMsg("保存成功！");
		return rs;
	}
	@RequestMapping(value="/edit", method = RequestMethod.GET)
//	@SpringMVCToken(genToken=true)
	public String edit(Long id,Model model) {
		List<QuartzJob> list = (List<QuartzJob>)quartzJobService.getAll();
		model.addAttribute("listJob", list);
		QuartzJobScheduler taskScheduler = service.get(id);
		model.addAttribute("taskScheduler", taskScheduler);
		return "quartz/edit";
	}
	
	@RequestMapping(value="/remove/{id}", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult remove(@PathVariable Long id) {
		AjaxResult rs = new AjaxResult();
		if(null==id){
			rs.setStatus(AjaxResult.STATUS_ERROR);
			rs.setMsg("删除时参数id不能为空！");
			return rs;
		}
		try{
			service.deleteJobScheduler(service.get(id));
			rs.setStatus(AjaxResult.STATUS_SUCCESS);
			rs.setMsg("删除成功！");
		}catch(Exception e){
			rs.setStatus(AjaxResult.STATUS_ERROR);
			rs.setMsg("删除失败！<br/>" + e.getMessage());
		}
		
		return rs;
	}
	
	@RequestMapping(value="/view", method = RequestMethod.GET)
	public String view(Long id,Model model) {
		List<QuartzJob> list = (List<QuartzJob>)quartzJobService.getAll();
		model.addAttribute("listJob", list);
		QuartzJobScheduler taskScheduler = service.get(id);
		model.addAttribute("taskScheduler", taskScheduler);
		Map<String,Object> map = service.findJobDetail(taskScheduler);
		model.addAllAttributes(map);
		return "quartz/view";
	}
	@RequestMapping(value="/executeJob", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult executeJob(Long id,String type,String className) {
		AjaxResult rs = new AjaxResult();
		try{
			QuartzJobScheduler taskScheduler = service.get(id);
			switch(type){
			case "triggerJob":service.executeTriggerJob(taskScheduler);break;
			case "interrupt":service.executeInterrupt(taskScheduler);break;
			case "pauseJob":service.executePauseJob(taskScheduler);break;
			case "resumeJob":service.executeResumeJob(taskScheduler);break;
			case "cancelJob":service.deleteJob(taskScheduler);break;
			case "addJob":service.addJob(taskScheduler);break;
			default:throw new BizException("请输入运行类型！");
			}
			rs.setStatus(AjaxResult.STATUS_SUCCESS);
			rs.setMsg("更新成功！");
		}catch(Exception e){
			rs.setStatus(AjaxResult.STATUS_ERROR);
			rs.setMsg("更新失败！<br/>" + e.getMessage());
		}
		return rs;
	}
}
