package com.machine.utils;

import javax.servlet.http.HttpSession;

public final class LoginHelper {

	public static boolean isLogin(HttpSession session){
		if(session.getAttribute("admin") == null){
			return false;
		}
		return true;
	}
}
