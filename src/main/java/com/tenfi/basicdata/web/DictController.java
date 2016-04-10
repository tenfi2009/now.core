package com.tenfi.basicdata.web;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.googlecode.genericdao.search.Sort;
import com.tenfi.basicdata.model.Dict;
import com.tenfi.basicdata.service.DictService;
import com.tenfi.core.model.Page;
import com.tenfi.core.model.TreeVO;
import com.tenfi.core.util.TreeUtils;
import com.tenfi.core.web.AjaxResult;

/**
 * @author Administrator
 */
@Controller
@RequestMapping("/basicdata/dict")
public class DictController {

	@Autowired
	private DictService service;

	@ModelAttribute("dict")
	public Dict getValue(Integer id) {
		Dict dict = null;
		if (id != null) {
			dict = service.get(id);
		} else {
			dict = new Dict();
		}
		return dict;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list() {
		return "basicdata/dict/list";
	}

	/**
	 * 加载列表
	 * 
	 * @param parentId
	 * @param currPage
	 * @param pageSize
	 * @param sortField
	 * @param sortType
	 * @param filters
	 * @return
	 */
	@RequestMapping(value = "/listData", method = RequestMethod.GET)
	@ResponseBody
	public Object listData(@RequestParam String parentId, @RequestParam(value = "page", defaultValue = "1") Integer currPage, @RequestParam(value = "rows", defaultValue = "20") Integer pageSize, @RequestParam(value = "sidx") String sortField, @RequestParam(value = "sord") String sortType, String filters) {
		Page<Dict> page = new Page<Dict>();
		page.setPageSize(pageSize);
		page.setCurrPage(currPage);
		if (StringUtils.isNotEmpty(sortField)) {
			page.getSorts().add(new Sort(sortField, "desc".equalsIgnoreCase(sortType) ? true : false));
		}

		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("parentId", parentId);
		if (StringUtils.isNotEmpty(filters)) {
			queryParams.put("filters", filters);
		}

		page = service.findPage(page, queryParams);
		return page;
	}

	@RequestMapping(value = "/addNew", method = RequestMethod.GET)
	public @ModelAttribute("dict") Dict addNew(Integer parentId) {
		Dict dict = new Dict();
		if (parentId != null) {
			Dict parent = this.service.get(parentId);
			dict.setParent(parent);
		}
		return dict;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Long id) {
		return "basicdata/dict/edit";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult save(@ModelAttribute("dict") Dict dict) {
		AjaxResult rs = new AjaxResult();

		service.save(dict);
		rs.setStatus(AjaxResult.STATUS_SUCCESS);
		rs.setMsg("保存成功！");

		return rs;
	}

	@RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult remove(@PathVariable Integer id) {
		AjaxResult rs = new AjaxResult();
		if (id == null) {
			rs.setStatus(AjaxResult.STATUS_ERROR);
			rs.setMsg("删除时参数id不能为空！");
			return rs;
		}
		service.removeById(id);
		rs.setStatus(AjaxResult.STATUS_SUCCESS);
		rs.setMsg("删除成功！");

		return rs;
	}

	/**
	 * 提交数据
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/submit/{id}", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult submit(@PathVariable Integer id) {
		AjaxResult rs = new AjaxResult();
		if (id == null) {
			rs.setStatus(AjaxResult.STATUS_ERROR);
			rs.setMsg("提交时参数id不能为空！");
			return rs;
		}
		service.submit(id);
		rs.setStatus(AjaxResult.STATUS_SUCCESS);
		rs.setMsg("提交成功！");

		return rs;
	}

	@RequestMapping(value = "/getSonType", method = RequestMethod.POST)
	@ResponseBody
	public String getSonType(@RequestParam("code") String code) {
		return sonTypeToJson(service.getSonType(code));
	}

	/**
	 * 加载树
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getDictTree", method = RequestMethod.POST)
	@ResponseBody
	public String getDictTree(String id) {
		return TreeUtils.treeToJson(service.getChildren(id));
	}

	/**
	 * <p>
	 * date: 2015年5月28日 下午4:46:17
	 * </p>
	 * <p>
	 * description: 缓存
	 * </p>
	 * 
	 * @author yanfeng.li/李彦风
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/getDictAll", method = RequestMethod.POST)
	@ResponseBody
	public String getDictAll() {
		return sonTypeToJson(service.getAll());
	}

	/**
	 * 
	 * <p>
	 * date: 2015年7月24日 下午6:14:36
	 * </p>
	 * <p>
	 * description:
	 * </p>
	 * 
	 * @author fang.zhang/张芳
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/getBaseDataAll", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult getBaseData(@RequestParam("table") String tableName, @RequestParam("id") String id, @RequestParam("showColumn") String showColumn, @RequestParam("idType") String idType) {
		String showValue = service.getBaseDataById(tableName, id, showColumn, idType);
		AjaxResult rs = new AjaxResult();
		rs.setData(showValue);
		rs.setStatus(AjaxResult.STATUS_SUCCESS);
		return rs;
	}

	private static <T extends TreeVO> String sonTypeToJson(List<Dict> list) {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		if (null != list && list.size() > 0) {
			for (TreeVO node : list) {
				sb.append("{\"code\":\"").append(node.getCode()).append("\"");
				sb.append(",\"name\":\"").append(node.getName()).append("\"");
				sb.append("},");
			}
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append("]");

		return sb.toString();
	}

	private static <T extends TreeVO> String sonTypeToJson(Collection<Dict> list) {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		if (null != list && list.size() > 0) {
			for (TreeVO node : list) {
				TreeVO p = node.getParent();
				sb.append("{\"code\":\"").append(node.getCode()).append("\"");
				sb.append(",\"name\":\"").append(node.getName()).append("\"");
				sb.append(",\"parentCode\":\"").append(p == null ? null : p.getCode()).append("\"");
				sb.append("},");
			}
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append("]");

		return sb.toString();
	}

}
