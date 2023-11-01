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
}
