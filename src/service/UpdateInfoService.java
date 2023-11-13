package service;

import dao.UpdateInfoDAO;
import vo.UsersVO;

public class UpdateInfoService {
	
	UpdateInfoDAO dao;
	
	public UpdateInfoService() {
		
		dao = new UpdateInfoDAO();
		
	}
	
	//1. 개인정보 수정을 위해 정보를 불러오는 기능
	public UsersVO getUsersById(int user_id) {
		
		return dao.getUsersById(user_id);
	}
	
	//2. 개인정보 수정하는 기능
	public void updateInfo (String email, String password, String username, int user_id) {
		
		dao.updateInfo(email, password, username, user_id);
	}
	
	//3. 중복이메일 확인 기능
	public boolean isEmailAvailable(String email, int user_id) {
		
		boolean available = true;
	       	   
	    available = dao.isEmailAvailable(email, user_id);
	
	    return available;
	}
	
}
