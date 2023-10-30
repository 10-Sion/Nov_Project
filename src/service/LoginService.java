package service;

import dao.LoginDAO;

public class LoginService {

	LoginDAO dao;
	
	public LoginService() {
		
		dao = new LoginDAO();
	}
	
	//로그인 기능
	public int login(String user_id, String password) {
		
		return dao.login(user_id, password);
	}
}
