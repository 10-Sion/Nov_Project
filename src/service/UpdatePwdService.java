package service;

import dao.UpdatePwdDAO;

public class UpdatePwdService {
	
	UpdatePwdDAO dao;
	
	public UpdatePwdService() {
		
		dao = new UpdatePwdDAO();
	}
	
	//1. 비밀번호 변경하는 기능
	public void updatePwd (String email, String newPwd) {
		
		dao.updatePwd(email, newPwd);
	}
}
