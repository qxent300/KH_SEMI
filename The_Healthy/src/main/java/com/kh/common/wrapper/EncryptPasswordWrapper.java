package com.kh.common.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.kh.common.util.EncryptUtil;

public class EncryptPasswordWrapper extends HttpServletRequestWrapper {

	// Filter vs Wrapper
	// Filter : 서블릿 이름이나 URL 패턴을 통해 사용자의 요청과 응답에 대한 전처리를 진행할 수 있는 부
	// Wrapper : 국소적으로 특정 파라메터나 인자를 전처리 해주는 부분
	
	public EncryptPasswordWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getParameter(String name) {
		String value = super.getParameter(name); 
		
		if (name.equals("userPwd") || name.equals("enrollPassword")) {
			value = EncryptUtil.oneWayEnc(value, "SHA-256");
		}
		
		return value;
	}
}
