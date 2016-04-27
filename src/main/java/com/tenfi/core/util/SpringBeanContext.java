/**
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 人人公司 http://www.renren-inc.com </p>
 * <p>date: 2015年5月20日 下午3:03:15 </p>
 * <p>title: SpringBeanContext.java </p>
 */
package com.tenfi.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * <p>description:  通过程序的方式获取Spring Bean</p>
 *
 */
public class SpringBeanContext implements ApplicationContextAware {
	private static Logger logger = LoggerFactory.getLogger(SpringBeanContext.class);

	 // Spring应用上下文环境  
    private static ApplicationContext applicationContext;
	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringBeanContext.applicationContext = applicationContext;
		String profile = SpringBeanContext.applicationContext.getEnvironment().getProperty("spring.profiles.default");
		
		if(SystemUtils.ENV_PROFILE_DEV.equals(profile) 
				|| SystemUtils.ENV_PROFILE_TEST.equals(profile) 
				|| SystemUtils.ENV_PROFILE_PROD.equals(profile)){
			System.setProperty(SystemUtils.ENV_PROFILE, profile);
			logger.info("设置系统的环境配置参数: env_profile = {}",profile);
		}else{
			logger.error("设置系统的环境配置参数失败: env_profile = {}",profile);
		}
	}
	
	 /** 
     * @return ApplicationContext 
     */  
    public static ApplicationContext getApplicationContext() {  
        return applicationContext;  
    }  
  
    /**
     * 
     * <p>date: 2015年5月23日 上午11:24:40</p>
     * <p>description:  根据Bean 的名字和类型获取Bean对象</p>
     * @author rong.yang/阳荣
     * @param name 获取Bean 的名字
     * @param requiredType Bean类型
     * @return
     */
    public static <T> T getBean(String name,Class<T> requiredType) throws BeansException {  
        return applicationContext.getBean(name, requiredType) ;
    }
    public static <T> T getBean(String name) throws BeansException {  
        return (T) applicationContext.getBean(name) ;
    }
   
    
    /**
     * <p>date: 2015年5月23日 上午11:24:40</p>
     * <p>description:  根据Bean 类型获取Bean对象</p>
     * @author rong.yang/阳荣
     * @param requiredType Bean类型
     * @return
     */
	public static  <T> T getBeansOfType(Class<T> requiredType) throws BeansException {  
    	return applicationContext.getBean(requiredType);
    }  
}
