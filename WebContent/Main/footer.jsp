<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>푸터 영역</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
	<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css" rel="stylesheet" />
      <style type="text/css">
        footer {
            border-top: 1px solid #e4e4e4;
            background-color: #333;
            color: #999;
            padding: 1rem 0;
            text-align: left;
            font-size: 15px;
            font-family: 'Nanum Gothic', sans-serif;
        }

        .inner {
            margin-left: 300px;
            max-width: calc(100% - 300px);
        }

        .footer-message,
        .footer-contact,
        .footer-copyright,
        .footer-links ul li a {
            font-size: 0.9rem;
            text-decoration: none;
            color: #999;
            margin: 0.6rem;
        }

        .footer-message {
            font-weight: bold;
        }

        .footer-links ul {
            list-style: none;
            display: flex;
            flex-wrap: wrap; /* 화면 폭을 넘어가는 항목을 다음 줄로 내려가도록 설정 */
            justify-content: flex-start;
            padding: 0;
        }

         .footer-links { 
             padding: 0;
         
         } 

        .footer-links ul li a:hover {
            color: #007BFF;
        }

        /* · 추가 및 스타일 적용 */
        .footer-links ul li::before {
            content: " · ";
            color: #999;
            letter-spacing: -0.2em; /* 점 사이의 간격을 조절 */
            
        }
        .footer-links ul li:first-child::before {
	        content: ""; /* 첫 번째 항목 앞의 점을 제거 */
	    }
	    
		.icons {
		  list-style: none; /* 리스트 스타일 제거 */
		  display: flex; /* 아이콘들을 가로로 나란히 배치하기 위해 Flex 레이아웃 사용 */
		  justify-content: right; /* 아이콘들을 수평으로 가운데 정렬 */
		  padding: 0; /* 리스트의 패딩 제거 */
		}
		
		.icons li {
		  margin: 0 10px; /* 각 아이콘 사이에 10px의 좌우 마진을 적용 */
		}
		
		.icons li a {
		  color: #999; /* 글자 색상을 회색(#999)으로 설정 */
		  text-decoration: none; /* 링크 텍스트에 밑줄 제거 */
		}



    </style>

</head>
<body>
	<section>
		<br><br><br><br><br>
	</section>
	<footer>
		<section>		
			<br><br>		
		</section>
	    <div class="inner">        
	        <div class="footer-links">
	            <ul>
	                <li><a href="#">자주묻는질문</a></li>
	                <li><a href="#">커뮤니티가이드</a></li>
	                <li><a href="#">이용약관</a></li>
	                <li><a href="#">운영정책</a></li>
		            <li><a href="#">청소년보호정책</a></li>
	                <li><a href="#">위치정보이용약관</a></li>
	                <li><a href="#">개인정보처리방침</a></li>
	                <li><a href="#">웹접근성안내</a></li>
	                <li><a href="#">고객센터</a></li>
	            </ul>
	        </div>
	        <div class="footer-message">환영합니다! 병원 리뷰 커뮤니티에 참여해 주셔서 감사합니다. 함께 좋은 정보를 공유해요.</div>
	        <div class="footer-contact">문의 및 지원: contact@hospitalreview.com</div>
	        <div class="footer-copyright">Copyright ⓒ 2023 the Portal hospitalreview. All rights reserved.</div>
	        <ul class="icons">			
	        	<!-- bounce가 별로라면  fa-bounce만 빼고 정적 아이콘으로 변경가능-->
				<li><i class="fa-brands fa-facebook fa-bounce fa-lg"></i>&nbsp;<span class="label"><a href="#">Facebook&nbsp;&nbsp;</a></span></li>
				<li><i class="fa-brands fa-twitter fa-bounce fa-lg"></i>&nbsp;<span class="label"><a href="#">Twitter&nbsp;&nbsp;</a></span></li>
				<li><i class="fa-brands fa-instagram fa-bounce fa-lg"></i>&nbsp;<span class="label"><a href="#">Instagram&nbsp;&nbsp;</a></span></li>
				<li><i class="fa-brands fa-github fa-bounce fa-lg"></i>&nbsp;<span class="label"><a href="#">GitHub&nbsp;&nbsp;</a></span></li>				
			</ul>  
		</div>
	</footer>
</body>
</html>
