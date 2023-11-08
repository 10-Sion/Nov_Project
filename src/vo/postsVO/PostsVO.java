package vo.postsVO;

import java.sql.Timestamp;

public class PostsVO {
	private int post_id; // 게시물 식별자
    private String post_name; // 작성자 이름  
    private String post_title; // 게시물 제목
    private String post_content; // 게시물 내용
    private Timestamp post_date; // 게시 날짜
    private int post_user_id; // 게시 사용자 식별자
    private int good; // 추천수
    private int bad; // 비추천수
    private int view_count; // 조회수
	
    //생성자
    
    public PostsVO() {
		// TODO Auto-generated constructor stub
	}
    
    
    
    public PostsVO(int post_id, String post_name, String post_title, String post_content, Timestamp post_date,
			int post_user_id, int good, int bad, int view_count) {
		super();
		this.post_id = post_id;
		this.post_name = post_name;
		this.post_title = post_title;
		this.post_content = post_content;
		this.post_date = post_date;
		this.post_user_id = post_user_id;
		this.good = good;
		this.bad = bad;
		this.view_count = view_count;
	}



	//getter & setter
    
    
	public int getPost_id() {
		return post_id;
	}

	public int getGood() {
		return good;
	}



	public void setGood(int good) {
		this.good = good;
	}



	public int getBad() {
		return bad;
	}



	public void setBad(int bad) {
		this.bad = bad;
	}



	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}

	public String getPost_name() {
		return post_name;
	}

	public void setPost_name(String post_name) {
		this.post_name = post_name;
	}

	public String getPost_title() {
		return post_title;
	}

	public void setPost_title(String post_title) {
		this.post_title = post_title;
	}

	public String getPost_content() {
		return post_content;
	}

	public void setPost_content(String post_content) {
		this.post_content = post_content;
	}

	public Timestamp getPost_date() {
		return post_date;
	}

	public void setPost_date(Timestamp post_date) {
		this.post_date = post_date;
	}

	public int getPost_user_id() {
		return post_user_id;
	}

	public void setPost_user_id(int post_user_id) {
		this.post_user_id = post_user_id;
	}

	public int getView_count() {
		return view_count;
	}

	public void setView_count(int view_count) {
		this.view_count = view_count;
	}
    
    
}
