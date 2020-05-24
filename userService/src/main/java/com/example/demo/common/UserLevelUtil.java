package com.example.demo.common;

import com.example.demo.model.User;

/**
 * 
 * @author dgm
 * @describe "用户级别判断"
 * @date 2020年5月21日
 */
public class UserLevelUtil {
	
	/**
	 * 判断用户是否为老师账号
	 * @param user
	 * @return
	 */
	public static boolean isUserTeacher(User user) {
		if(null == user) {
			return false;
		}
		if(null == user.getLevel()) {
			return false;
		}
		if(ConstantUtil.USERLEVEL_TEACHER == user.getLevel()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 判断用户是否为管理员账号
	 * @param user
	 * @return
	 */
	public static boolean isUserAdmin(User user) {
		if(null == user) {
			return false;
		}
		if(null == user.getLevel()) {
			return false;
		}
		if(ConstantUtil.USERLEVEL_ADMIN == user.getLevel()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 判断用户是否为学生账号
	 * @param user
	 * @return
	 */
	public static boolean isUserStudent(User user) {
		if(null == user) {
			return false;
		}
		if(null == user.getLevel()) {
			return false;
		}
		if(ConstantUtil.USERLEVEL_STUDENT == user.getLevel()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 判断用户是否不为管理员账号（即为学生或老师账号）
	 * @param user
	 * @return
	 */
	public static boolean isUserNotAdmin(User user) {
		if(null == user) {
			return false;
		}
		if(null == user.getLevel()) {
			return false;
		}
		if(ConstantUtil.USERLEVEL_ADMIN == user.getLevel()) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * 判断用户是否不为学生账号（即为管理员或老师账号）
	 * @param user
	 * @return
	 */
	public static boolean isUserNotStudent(User user) {
		if(null == user) {
			return false;
		}
		if(null == user.getLevel()) {
			return false;
		}
		if(ConstantUtil.USERLEVEL_STUDENT == user.getLevel()) {
			return false;
		} else {
			return true;
		}
	}
}
