package service;


import dao.JoinDAO;
import vo.UsersVO;

public class JoinService {

	JoinDAO dao;
	
	public JoinService() {
		
		dao = new JoinDAO();
	}
	
	//1. 회원가입 기능
	public void insertUsers(UsersVO vo) {
		
		dao.insertUsers(vo);
	}
}
