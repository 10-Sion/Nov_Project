package vo.memberListVO;

public class MemberListVO {
	private int user_id; //유저 식별자
	private int grade_id; // 등급번호
	private int post_user_id; // 자유게시판 유저식별자
	private int review_user_id; // 리뷰게시판 유저 식별자
	private int comments_user_id;// 댓글 유저 식별자
 	private String username; // 유저 이름
    private String email; // 유저 이메일
    private String password; // 유저 비번
    private int count; // 유저 추천수
    private String grade_name; //유저 등급 이름
    private int review_count; // 리뷰게시판 글 갯수
    private int post_count; // 자유게시판 글갯수
    private int comments_count; // 댓글 글갯수
	
    //생성자
    public MemberListVO() {
		// TODO Auto-generated constructor stub
	}
    
    

    public MemberListVO(int user_id, int grade_id, int post_user_id, int review_user_id, int comments_user_id,
			String username, String email, String password, int count, String grade_name, int review_count,
			int post_count, int comments_count) {
		super();
		this.user_id = user_id;
		this.grade_id = grade_id;
		this.post_user_id = post_user_id;
		this.review_user_id = review_user_id;
		this.comments_user_id = comments_user_id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.count = count;
		this.grade_name = grade_name;
		this.review_count = review_count;
		this.post_count = post_count;
		this.comments_count = comments_count;
	}



	//getter & setter
    
    
    
	public String getUsername() {
		return username;
	}


	public int getGrade_id() {
		return grade_id;
	}



	public void setGrade_id(int grade_id) {
		this.grade_id = grade_id;
	}



	public int getPost_user_id() {
		return post_user_id;
	}


	public void setPost_user_id(int post_user_id) {
		this.post_user_id = post_user_id;
	}


	public int getReview_user_id() {
		return review_user_id;
	}


	public void setReview_user_id(int review_user_id) {
		this.review_user_id = review_user_id;
	}


	public int getComments_user_id() {
		return comments_user_id;
	}


	public void setComments_user_id(int comments_user_id) {
		this.comments_user_id = comments_user_id;
	}


	public int getUser_id() {
		return user_id;
	}


	public void setUser_id(int user_id) {
		this.user_id = user_id;
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


	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}


	public String getGrade_name() {
		return grade_name;
	}


	public void setGrade_name(String grade_name) {
		this.grade_name = grade_name;
	}


	public int getReview_count() {
		return review_count;
	}


	public void setReview_count(int review_count) {
		this.review_count = review_count;
	}


	public int getPost_count() {
		return post_count;
	}


	public void setPost_count(int post_count) {
		this.post_count = post_count;
	}


	public int getComments_count() {
		return comments_count;
	}


	public void setComments_count(int comments_count) {
		this.comments_count = comments_count;
	}
    
    
}
