package service;


import dao.JoinDAO;
import vo.UsersVO;

public class JoinService {

	JoinDAO dao;
	
	public JoinService() {
		
		dao = new JoinDAO();
	}
	
	//1-1 일반회원 회원가입 기능
	public void insertUsers(UsersVO vo) {
		
		dao.insertUsers(vo);
	}
	
	//1-2 관리자 회원가입 기능
	public void insertMasters(UsersVO vo) {
		
		dao.insertMasters(vo);
		
	}
}
