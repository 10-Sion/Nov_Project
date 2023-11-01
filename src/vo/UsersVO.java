package vo;


//Users 테이블 VO
public class UsersVO {

	int user_id; 		// 사용자 식별자
    String username; 	// 사용자 이름
    String email; 		// 이메일 주소
    String password; 	// 비밀번호    
    int grade_id; 		// 등급 식별자
    int count; 			// 추천/비추천 갯수
    
    //기본 생성자
    public UsersVO() {
		
	}

	public UsersVO(int user_id, String username, String email, String password, int grade_id,
			int count) {
		this.user_id = user_id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.grade_id = grade_id;
		this.count = count;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getGrade_id() {
		return grade_id;
	}

	public void setGrade_id(int grade_id) {
		this.grade_id = grade_id;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
    
    
	
}
