package com.joelin.demo.microservice.sb;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.joelin.demo.microservice.sb.entity.UserEntity;


public class CurrentUserUtils {
	public final String CUR_USER_ID = "cur_user_id";
	public final String CUR_USER_NAME = "cur_user_name";
	public static CurrentUserUtils INSTANCE = null;
	
	private CurrentUserUtils(){
		
	}
	

	public static CurrentUserUtils getInstance(){
		if(INSTANCE == null){
			synchronized (CurrentUserUtils.class) {
				if(INSTANCE == null) {
					INSTANCE = new CurrentUserUtils();
				}
			}
		}
		
		return INSTANCE;
	}
	/**
	 * 获取当前Request
	 * @return
	 */
	private HttpServletRequest getRequest() {  
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();  
        return requestAttributes.getRequest();  
    } 
	
	/**
	 * 获取当前Session
	 * @return
	 */
	private HttpSession getSession() {  
        return getRequest().getSession(true);  
    }
	

	
	public String getUserId(){
		return (String)getSession().getAttribute(CUR_USER_ID);
	}

	public void setUser(UserEntity user){
		getSession().setAttribute(CUR_USER_ID, String.valueOf(user.getId()));
		getSession().setAttribute(CUR_USER_NAME, user.getName());
	}
	

	
}
