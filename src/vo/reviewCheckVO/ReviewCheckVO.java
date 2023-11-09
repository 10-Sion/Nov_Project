package vo.reviewCheckVO;

public class ReviewCheckVO {
	private int review_id; //review id
    private int user_id; // review의 user id
    private int hospital_id; //병원 테이블의 병원 아이디
    private String review_text; //리뷰 테이블의 리뷰 본문글
    private double rating; // 리뷰테이블의 평점
    private String verification; // 인증상태
    private int good; // 리뷰테이블의 좋아요 갯수
    private int bad; // 리뷰테이블의 싫어요 갯수
    private String username; // 유저테이블의 유저이름
    private String email; //유저테이블의 이메일
    private int grade_id; //유저테이블의 등급
    private int count; // 유저테이블의 추천 비추천 갯수
    private String name; // 병원테이블의 병원 이름
    private String address; // 병원테이블의 병원 주소
    private String receipt_image; // 영수증 테이블의 파일업로드 이름
    //생성자
    public ReviewCheckVO() {
		// TODO Auto-generated constructor stub
	}
    
    public ReviewCheckVO(int review_id, int user_id, int hospital_id, String review_text, double rating,
			String verification, int good, int bad, String username, String email, int grade_id, int count,
			String name, String address, String receipt_image) {
		super();
		this.review_id = review_id;
		this.user_id = user_id;
		this.hospital_id = hospital_id;
		this.review_text = review_text;
		this.rating = rating;
		this.verification = verification;
		this.good = good;
		this.bad = bad;
		this.username = username;
		this.email = email;
		this.grade_id = grade_id;
		this.count = count;
		this.name = name;
		this.address = address;
		this.receipt_image = receipt_image;
	}
    
    
    //getter & setter
    
    
	public int getReview_id() {
		return review_id;
	}

	public String getReceipt_image() {
		return receipt_image;
	}

	public void setReceipt_image(String receipt_image) {
		this.receipt_image = receipt_image;
	}

	public void setReview_id(int review_id) {
		this.review_id = review_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getHospital_id() {
		return hospital_id;
	}

	public void setHospital_id(int hospital_id) {
		this.hospital_id = hospital_id;
	}

	public String getReview_text() {
		return review_text;
	}

	public void setReview_text(String review_text) {
		this.review_text = review_text;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getVerification() {
		return verification;
	}

	public void setVerification(String verification) {
		this.verification = verification;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
