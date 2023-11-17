package service;

import dao.DelAccountDAO;

public class DelAccountService {

	DelAccountDAO dao;
	
	public DelAccountService() {
		
		dao = new DelAccountDAO();
		
	}
	
	//1. 회원탈퇴시키는 메소드
	public int delAccount(String email, String password) {
		
		return dao.delAccount(email, password);
		
	}
	
}
