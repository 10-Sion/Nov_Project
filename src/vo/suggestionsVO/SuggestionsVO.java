package vo.suggestionsVO;

public class SuggestionsVO {
	 	private int suggestion_id; // 건의사항 식별자 
	    private String post_title; // 건의사항 제목
	    private String post_content; // 건의사항 내용
	    private int post_user_id; // 건의사항 작성자 식별자
	    private int view_count; // 조회수  default 값 설정
		
	    
	    public SuggestionsVO(int suggestion_id, String post_title, String post_content, int post_user_id,
				int view_count) {
			super();
			this.suggestion_id = suggestion_id;
			this.post_title = post_title;
			this.post_content = post_content;
			this.post_user_id = post_user_id;
			this.view_count = view_count;
		}


		public int getSuggestion_id() {
			return suggestion_id;
		}


		public void setSuggestion_id(int suggestion_id) {
			this.suggestion_id = suggestion_id;
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
