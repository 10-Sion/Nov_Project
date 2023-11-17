package vo.commentsVO;

import java.sql.Timestamp;

public class CommentsVO {
	private int comment_id; // 댓글 식별자
	private String  user_name; // 사용자 이름
    private int user_id;  //사용자 식별자
    private int post_id; //게시물 식별자
    private String comment_text; //댓글 내용
    private Timestamp comment_date; //댓글 작성일
    private int level; // 댓글/대댓글
    private int parent_id; //부모 댓글 식별자
	
    //생성자
    
    public CommentsVO() {
		
	}

	
	
	
	public CommentsVO(int comment_id, String user_name, int user_id, int post_id, String comment_text,
			Timestamp comment_date, int level, int parent_id) {
		super();
		this.comment_id = comment_id;
		this.user_name = user_name;
		this.user_id = user_id;
		this.post_id = post_id;
		this.comment_text = comment_text;
		this.comment_date = comment_date;
		this.level = level;
		this.parent_id = parent_id;
	}


	//getter & setter

	public int getComment_id() {
		return comment_id;
	}




	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}




	public String getUser_name() {
		return user_name;
	}




	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}




	public int getUser_id() {
		return user_id;
	}




	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}




	public int getPost_id() {
		return post_id;
	}




	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}




	public String getComment_text() {
		return comment_text;
	}




	public void setComment_text(String comment_text) {
		this.comment_text = comment_text;
	}




	public Timestamp getComment_date() {
		return comment_date;
	}




	public void setComment_date(Timestamp comment_date) {
		this.comment_date = comment_date;
	}




	public int getLevel() {
		return level;
	}




	public void setLevel(int level) {
		this.level = level;
	}




	public int getParent_id() {
		return parent_id;
	}




	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}




	
	

	
	
	
}
