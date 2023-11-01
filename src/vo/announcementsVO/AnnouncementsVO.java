package vo.announcementsVO;

public class AnnouncementsVO {
	 private int announcement_id; // 공지사항 식별자 글번호
	 private String post_title; // 공지사항 제목
	 private String post_content; // 공지사항 내용
	 private int view_count;    // 조회수
	
	 public AnnouncementsVO() {
	
	}
	 
	 //생성자
	 public AnnouncementsVO(int announcement_id, String post_title, String post_content, int view_count) {
		super();
		this.announcement_id = announcement_id;
		this.post_title = post_title;
		this.post_content = post_content;
		this.view_count = view_count;
	}

	
	 //getter & setter
	 public int getAnnouncement_id() {
		return announcement_id;
	}

	public void setAnnouncement_id(int announcement_id) {
		this.announcement_id = announcement_id;
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

	public int getView_count() {
		return view_count;
	}

	public void setView_count(int view_count) {
		this.view_count = view_count;
	}
	 
	 
}
