package service;

import dao.LoginDAO;

public class LoginService {

	LoginDAO dao;
	
	public LoginService() {
		
		dao = new LoginDAO();
	}
	
	//1. 로그인 기능
	public int login(String email, String password) {
		
		return dao.login(email, password);
		
	}

	//2. 이메일로 id값 가져오는 기능
	public int getUserIDByEmail(String email) {

		return dao.getUserIDByEmail(email);
	}
	
	//2. 이메일로 사용자 등급값 가져오는 기능
	public int getGradeIDByEmail(String email) {
		
		return dao.getGradeIDByEmail(email);
	}
	
}
