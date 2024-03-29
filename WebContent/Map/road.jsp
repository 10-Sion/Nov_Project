<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="UTF-8">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="road.css" />
    <title>병원 지도 및 길찾기</title>
</head>
<body>

    <div class="map_wrap">
        <div id="map" style="width:100%;height:100%;position:relative;overflow:hidden;"></div>
    
        <div id="menu_wrap" class="bg_white">
            <div class="option">
                <div>
                    <form class="form" onsubmit="searchPlaces(); return false;">
                        키워드 : <input type="text" value="부산진구 치과" id="keyword" size="15"> 
                        <button type="submit">검색하기</button> 
                    </form>
                </div>
            </div>
            <hr>
            <ul id="placesList"></ul>
            <div id="pagination"></div>
        </div>
    </div>
    
    <!-- Footer -->
	<div id="footer">
		<jsp:include page="footer.jsp" />
	</div>
    
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=b6cf39fe7e4f670569d2010de6681ae5&libraries=services"></script>
    <script src="road.js"></script>
    
    
</body>
</html>