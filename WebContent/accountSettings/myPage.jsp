<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<% request.setCharacterEncoding("UTF-8"); %> 

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="user_id" value="${sessionScope.user_id}" />
<c:set var="username" value="${sessionScope.username}" />
<c:set var="grade_id" value="${sessionScope.grade_id}" />

<jsp:include page="../Main/mainNavigate.jsp"/> <!-- 좌측 메뉴 페이지 -->
	
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>마이페이지 화면</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css" rel="stylesheet" />
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>	   
    <style type="text/css">
        body {
            background: #eceef1;
        }

        .inner {
            margin-left: 300px;
            max-width: calc(100% - 300px);
        }

        table.inner {
            width: 1000px;
            height: 800px;
            border-collapse: collapse;
            margin-top: 20px;
            background-color: white;
        }

        table.inner td {
            width: 200px;
            height: 80px;
            padding: 30px;
            border: 1px solid #ddd;
            text-align: left; /* 텍스트를 왼쪽 정렬 */
            transition: background-color 0.3s;
            font-size: 30px;
            cursor: pointer; /* 포인터 모양으로 변경하여 클릭 가능함을 나타냄 */
        }

        table.inner td:hover {
            background-color: #f0f0f0;
        }

		table.inner td b {
		    margin-left: 10px; /* 여백을 조절할 수 있는 값으로 변경 */		    
		    color: #007BFF;
		}
		
		table.inner td a {
		    text-decoration: none;
		    color: black;
		    display: inline-block; /* 블록 요소 대신 인라인 블록으로 변경 */
		    margin-left: 50px; /* 여백을 조절할 수 있는 값으로 변경 */
		}


        table.inner td a:hover {
            text-decoration: none;
            color: #007BFF;
        }

        p {
            color: black;
            font-size: 35px;
            margin-left: 300px;
            font-weight: bold;
        }
        
        .Btn {
			  --black: #000000;
			  --ch-black: #141414;
			  --eer-black: #1b1b1b;
			  --night-rider: #2e2e2e;
			  --white: #ffffff;
			  --af-white: #f3f3f3;
			  --ch-white: #e1e1e1;
			  display: flex;
			  align-items: center;
			  justify-content: flex-start;
			  width: 45px;
			  height: 45px;
			  border: none;
			  border-radius: 5px;
			  cursor: pointer;
			  position: relative;
			  overflow: hidden;
			  transition-duration: .3s;
			  box-shadow: 2px 2px 10px rgba(0, 0, 0, 0.199);
			  background-color: var(--night-rider);
		}
		
		/* plus sign */
		.sign {
			  width: 100%;
			  transition-duration: .3s;
			  display: flex;
			  align-items: center;
			  justify-content: center;
		}
		
		.sign svg {
		  	width: 17px;
		}
		
		.sign svg path {
		  	fill: var(--af-white);
		}
		
		/* text */
		.text {
			  position: absolute;
			  right: 0%;
			  width: 0%;
			  opacity: 0;
			  color: var(--af-white);
			  font-size: 1.2em;
			  font-weight: 600;
			  transition-duration: .3s;
		}
		
		/* hover effect on button width */
		.Btn:hover {
			  width: 160px;
			  border-radius: 5px;
			  transition-duration: .3s;
		}
		
		.Btn:hover .sign {
			  width: 30%;
			  transition-duration: .3s;
			  padding-left: 20px;
		}
		
		/* hover effect button's text */
		.Btn:hover .text {
			  opacity: 1;
			  width: 70%;
			  transition-duration: .3s;
			  padding-right: 10px;
		}
		
		/* button click effect*/
		.Btn:active {
		  	transform: translate(2px ,2px);
		}

		.sub {
			  display: none;
			  list-style-type: none;
			  padding: 0;
		}
		
		.sub li {
		  	padding: 10px;
		}
		
		.answer {
			color: black;
			margin-top: 30px;
			margin-bottom: 30px;

		}
		
	    .answer p {
	        font-size: 14px;
	        line-height: 1.6;
	        margin-bottom: 10px;
	    }
	
	    .answer ol {
	        padding-left: 20px;
	        margin-bottom: 10px;
	    }
	
	    .answer li {
	        font-size: 14px;
	        line-height: 1.6;
	        margin-bottom: 5px;
	    }
	    
        .answer p,
	    .answer ol,
	    .answer li  {
	        color: black;
	    }
	    
	    /* <p><strong> 태그 안의 내용을 왼쪽 정렬로 설정 */
	    .answer strong {
	        display: inline-block;
	        text-align: left;
	        margin-top: 20px; /* 간격 조절 */
	        margin-bottom: 5px; /* 간격 조절 */
	        font-size: 16px; /* 글씨 크기를 16픽셀로 설정 */

	    }
	    
	    .answer div {
		    font-size: 16px; /* 글씨 크기를 16픽셀로 설정 */
		}
    </style>
</head>
<body>
    <p>개인정보 관리</p>
    <table class="inner"> 
        <tr>
            <td colspan="2">
			  	<div style="display: flex; align-items: center;">
				  	<div id="gradeImage">
				        <c:choose>
				            <c:when test="${sessionScope.grade_id eq 1}">
				                <!-- 1단계(잡초)에 해당하는 이미지 -->
				                <img src="${contextPath}/Assets/Images/black.png" alt="Grade 1 Image" style="width: 50px; height: 50px; margin-left: 40px;">
				            </c:when>
				            <c:when test="${sessionScope.grade_id eq 2}">
				                <!-- 2단계(잔디)에 해당하는 이미지 -->
				                <img src="${contextPath}/Assets/Images/green.png" alt="Grade 2 Image" style="width: 50px; height: 50px; margin-left: 40px;">
				            </c:when>
				            <c:when test="${sessionScope.grade_id eq 3}">
				                <!-- 3단계(금잔디)에 해당하는 이미지 -->
				                <img src="${contextPath}/Assets/Images/gold.png" alt="Grade 3 Image" style="width: 50px; height: 50px; margin-left: 40px;">
				            </c:when>
				            <c:when test="${sessionScope.grade_id eq 4}">
				                <!-- 4단계(농부)에 해당하는 이미지 -->
				                <img src="${contextPath}/Assets/Images/manager.png" alt="Grade 4 Image" style="width: 50px; height: 50px; margin-left: 40px;">
				            </c:when>
				            <c:otherwise>
				                <!-- 기본값 또는 예외 처리 -->
				                <img src="${contextPath}/Assets/Images/green.png" alt="Default Image" style="width: 50px; height: 50px; margin-left: 40px;">
				            </c:otherwise>
				        </c:choose>
			    	</div>
		        	<b>${username}</b>님 환영합니다.	
		        	<button onclick="location.href='${contextPath}/Logout.do'" class="Btn" style="margin-left: 300px;">
				  	<div class="sign">
					  	<svg viewBox="0 0 512 512">
					  		<path d="M377.9 105.9L500.7 228.7c7.2 7.2 11.3 17.1 11.3 27.3s-4.1 20.1-11.3 27.3L377.9 406.1c-6.4 6.4-15 9.9-24 9.9c-18.7 0-33.9-15.2-33.9-33.9l0-62.1-128 0c-17.7 0-32-14.3-32-32l0-64c0-17.7 14.3-32 32-32l128 0 0-62.1c0-18.7 15.2-33.9 33.9-33.9c9 0 17.6 3.6 24 9.9zM160 96L96 96c-17.7 0-32 14.3-32 32l0 256c0 17.7 14.3 32 32 32l64 0c17.7 0 32 14.3 32 32s-14.3 32-32 32l-64 0c-53 0-96-43-96-96L0 128C0 75 43 32 96 32l64 0c17.7 0 32 14.3 32 32s-14.3 32-32 32z"></path>
					  	</svg>
				  	</div>				  
				  	<div class="text">Logout</div>
					</button>			        				        			       	        	        
				</div>
			</td>	
        </tr>
        <tr>
            <td colspan="2">
                <a href="${contextPath}/UpdateInfo/UpdateInfoPage.do?user_id=${user_id}"><i class="fa-solid fa-gear" style="color: #213454;"></i>
                &nbsp;개인정보수정</a>
            </td>
        </tr>
<!--         <tr> -->
<!--             <td> -->
<!--                 <a href="#"><i class="fa-solid fa-star" style="color: #213454;"></i> -->
<!--                 &nbsp;즐겨찾기 목록</a> -->
<!--             </td> -->
<!--             <td> -->
<!--                 <a href="#"><i class="fa-solid fa-user" style="color: #213454;"></i> -->
<!--                 &nbsp;내 게시글</a> -->
<!--             </td> -->
<!--         </tr> -->
        <tr>
        	<td colspan="2">
        		<div class="menu">
				    <a style="margin-left: 50px;">
				        <i class="fa-solid fa-circle-question" style="color: #213454;"></i>
				        &nbsp;자주묻는질문
				    </a>
				    <ul class="sub">
				       	<li>
							<a class="question">1. 등급 시스템이 궁금해요.</a>
							<div class="answer" style="display: none;">
							    <strong> 등급 시스템은 사용자의 활동에 따라 부여되는 레벨을 나타내며, 다음과 같은 등급이 있습니다: </strong>
							    <ul>
							        <li>
								        <img src="${contextPath}/Assets/Images/black.png" alt="Grade 1 Image" style="width: 30px; height: 30px;">
								        <strong> 잡초:</strong> 다른 유저들의 비추천 횟수가 일정 수준 이상일 경우 달성하는 등급입니다. <br>
								        <em>커뮤니티 활동을 계속하면 잔디로 다시 올라갈 수 있습니다. 다양한 활동에 참가해보세요!</em>
							        </li>
							        <li>
							        	<img src="${contextPath}/Assets/Images/green.png" alt="Grade 2 Image" style="width: 30px; height: 30px;">							        
							        	<strong> 잔디:</strong> 회원가입 시 자동으로 부여되는 등급이며 병원 리뷰 작성, 게시글 작성, 댓글 달기 등 다양한 활동에 접근할 수 있습니다. <br>
							        	<em>활동을 계속하면 3단계로 올라갈 수 있습니다.</em>
							        </li>
							        <li>
							        	<img src="${contextPath}/Assets/Images/gold.png" alt="Grade 3 Image" style="width: 30px; height: 30px;">
							        	<strong> 금잔디:</strong> 높은 활동과 참여로 얻어지는 등급입니다. 운영자의 검증을 통해 달성할 수 있는 등급으로, 특별한 혜택과 권한을 부여받습니다. 
							        </li>
							    </ul>
							    <div>
							        등급은 게시글이나 댓글을 활발히 작성하고 커뮤니티에 기여할수록 높아지며, 잔디에서는 비추천을 일정 수준 이상 받을 경우 잡초로 떨어질 수 있습니다. 높은 등급은 커뮤니티 내에서 더 많은 기능과 혜택을 제공하니 많은 활동을 해보세요!
							    </div>
							</div>
					   	</li>
					   	<li>
				            <a class="question">2. 병원 리뷰는 어떻게 작성하나요?</a>
							<div class="answer" style="display: none;">
							    <strong> 병원 리뷰 작성은 커뮤니티의 건강한 분위기를 유지하기 위해 중요합니다. 아래는 병원 리뷰를 작성하는 방법입니다. </strong>
							    <ol>
							        <li>
							            <strong>정직한 리뷰 작성:</strong> 병원 리뷰를 작성할 때에는 허위 정보나 과장된 내용을 포함하지 않도록 주의해주세요. 다른 사용자들에게 정확한 정보를 제공하여 도움이 되도록 노력해주세요.
							        </li>
							        <li>
							            <strong>다녀온 병원 확인:</strong> 리뷰를 작성하려면 실제로 해당 병원을 다녀온 것을 증명할 영수증이 필수입니다. 허위 리뷰는 커뮤니티에 해를 끼칠 수 있으며, 다녀온 경험을 공유함으로써 신뢰성 있는 정보를 제공할 수 있습니다.
							        </li>
							        <li>
							            <strong>영수증 인증:</strong> 작성한 리뷰가 더욱 신뢰성 있게 보이도록, 다녀온 병원의 영수증을 인증해주세요. 이를 통해 사용자들은 작성자가 실제로 해당 병원을 방문한 것임을 확인할 수 있습니다.
							        </li>
							    </ol>
							    <strong> 이러한 가이드라인을 따르면서 병원 리뷰를 작성하면 커뮤니티가 건강하게 운영될 수 있습니다. 감사합니다. 함께 더 나은 정보를 공유하여 유익한 커뮤니티를 만들어 나가요!</strong>
							</div>
					   	</li>
					   	<li>
				           <a class="question">3. 악성 유저가 있어요. 신고는 못하나요?</a>
				           <div class="answer" style="display: none;">
							    <strong>악성 유저 신고는 다음과 같이 진행됩니다.</strong>
							    <ol>
							        <li><strong>직접 신고:</strong> 악성 유저로부터 받은 메시지나 댓글을 해당 컨텐츠의 신고 기능을 사용하여 신고할 수 있습니다.</li>
							        <li><strong>커뮤니티 운영자에게 연락:</strong> 악성 행동이 지속될 경우, 커뮤니티 운영자에게 직접 연락하여 문제를 보고할 수 있습니다. (연락처: contact@hospitalreview.com)</li>
							    </ol>
							    <strong>운영자에게 신고 시 주의사항</strong>
							    <ol>
							        <li><strong>구체적인 내용 제공:</strong> 악성 행동을 신고할 때는 가능한 구체적인 내용을 제공해주시면 도움이 됩니다. 메시지 내용이나 댓글 링크를 첨부해주세요.</li>
							        <li><strong>개인 정보 보호:</strong> 신고할 때 다른 회원의 개인 정보는 제공하지 말아주세요.</li>
							    </ol>
							    <strong>운영자의 대응</strong>
							    <ol>
							        <li><strong>조사:</strong> 신고가 접수되면, 운영자는 사안을 조사합니다.</li>
							        <li><strong>대응:</strong> 악성 행동이 확인되면 적절한 대응을 합니다. 이는 경고, 계정 정지, 또는 법적 조치 등이 포함될 수 있습니다.</li>
							    </ol>
							    <strong>커뮤니티 안전을 위한 여러분의 협조</strong><br>
							    <div>모든 회원은 커뮤니티의 안전을 지키기 위해 적극 협조해주시기 바랍니다. 악성 행동을 발견하면 운영자에게 신고하여 커뮤니티를 좋은 환경으로 만들어 나가는 데 도움을 주시길 부탁드립니다.
								         감사합니다. 함께 더 즐거운 커뮤니티를 만들어 나가요!</div>
							</div>
						</li>
					</ul>
				</div>
            </td>
        </tr>
<!--         <tr> -->
<!--             <td colspan="2"> -->
<%--                 <a href="${contextPath}/DelAccount/DelPage.do">회원탈퇴</a> --%>
<!--             </td> -->
<!--         </tr> -->
    </table>
    <!-- Footer -->
    <div id="footer">
        <jsp:include page="/Main/footer.jsp" />
    </div>
    
	<script type="text/javascript">
	    $(document).ready(function () {
	        // 초기에 답변 숨기기
	        $('.answer').hide();
	
	        // 질문에 대한 답변 토글
	        $('.question').click(function () {
	            var answer = $(this).next('.answer');
	
	            // 다른 답변 닫기
	            $('.answer').not(answer).slideUp(500);
	
	            // 현재 질문에 대한 답변을 토글
	            answer.slideToggle(500);
	        });
	
	        // 자주하는 질문 클릭 이벤트
	        $('.menu a:not(.question)').click(function (e) {
	            e.preventDefault(); // 기본 링크 동작 방지
	
	            // 자주하는 질문 토글
	            $('.sub').slideToggle(500);
	        });
	    });
	</script>
</body>
</html>
