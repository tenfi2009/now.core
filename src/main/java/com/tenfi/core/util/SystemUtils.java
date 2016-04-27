package com.tenfi.core.util;

public class SystemUtils {
	public static final String ENV_PROFILE = "env_profile";
	public static final String ENV_PROFILE_DEV = "development";
	public static final String ENV_PROFILE_TEST = "test";
	public static final String ENV_PROFILE_PROD = "production";

	/**
	 * 
	 * <p>date: 2016年1月14日 下午4:00:50</p>
	 * <p>description:  是否生产环境</p>
	 * @return
	 */
	public final static boolean isProd() {
		if (ENV_PROFILE_PROD.equals(System.getProperty(ENV_PROFILE))) {
			return true;
		} else {
			return false;
		}
	}
}
