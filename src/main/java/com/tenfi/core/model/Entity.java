/**
 * <b>包名：</b>org.matrix.core.model<br/>
 * <b>文件名：</b>Entity.java<br/>
 * <b>版本信息：</b>1.0.0<br/>
 * <b>日期：</b>2013-6-26-下午9:18:49<br/>
 * <br/>
 */
package com.tenfi.core.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * <b>类名称：</b>Entity<br/>
 * <b>类描述：</b>可持久化的对象<br/>
 * <b>创建人：</b>rong yang<br/>
 * <b>修改人：</b>rong yang<br/>
 * <b>修改时间：</b>2013-6-26 下午9:18:49<br/>
 * <b>修改备注：</b><br/>
 * 
 * @version 1.0.0<br/>
 * 
 */
@MappedSuperclass
public abstract class Entity<ID extends Serializable> implements Serializable,Cloneable {
	/** 
	 * <p>description:  </p>
	 * @date 2015年11月5日 下午12:56:03
	 */
	private static final long serialVersionUID = 1L;

	public abstract ID getId();
	
	/** 额外的临时信息 */
	@JsonIgnore
	@SuppressWarnings("rawtypes")
	@Transient
	private Map additional;

	public abstract  void setId(ID id);
	
	@SuppressWarnings("rawtypes")
	public Map getAdditional() {
		if (null == additional) {
			additional = new HashMap();
		}
		return additional;
	}

	public Object getAdditional(Object key) {
		// TODO 参数合法性检查
		Object result = getAdditional().get(key);
		return result;
	}

	@SuppressWarnings("unchecked")
	public void setAdditional(Object key, Object value) {
		// TODO 参数合法性检查
		getAdditional().put(key, value);
	}

	@SuppressWarnings("rawtypes")
	@JsonIgnore
	public Collection getAdditionalKeys() {
		return (Collection) getAdditional().keySet();
	}

	public Object clone() throws CloneNotSupportedException {
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(this);

			ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(out.toByteArray()));
			return in.readObject();
		} catch (Exception e) {
			throw new RuntimeException("cannot clone class [" + this.getClass().getName() + "] var serialization: " + e.toString());
		}
	}
}
