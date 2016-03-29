/**
 * <b>包名：</b>org.matrix.sys.model<br/>
 * <b>文件名：</b>Role.java<br/>
 * <b>版本信息：</b>1.0.0<br/>
 * <b>日期：</b>2013-6-25-下午9:46:23<br/>
 * <br/>
 */
package com.tenfi.sys.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenfi.core.model.BasicDataVO;

/**
 * <b>类名称：</b>Role<br/>
 * <b>类描述：</b>角色<br/>
 * <b>创建人：</b>rong yang<br/>
 * <b>修改人：</b>rong yang<br/>
 * <b>修改时间：</b>2013-6-25 下午9:46:23<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_sys_role")
public class Role extends BasicDataVO<String> implements Serializable {
	@Id
	@GenericGenerator(name="idGenerator",strategy="org.hibernate.id.UUIDGenerator")
	@GeneratedValue(generator="idGenerator")
	@Column(length=36)
	private String id;
	
	public Role() {
		super();
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
}
