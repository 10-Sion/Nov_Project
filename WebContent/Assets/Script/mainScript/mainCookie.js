// 현재 페이지의 주소를 가져오는 함수
function getCurrentPageUrl() {
    return window.location.href;
}

// 버튼 클릭 시 실행될 함수
function toggleLocalStorage() {
    const user_id = getCookie('user_id'); // 쿠키에서 user_id 가져오기
    if (user_id) {
        const currentPageUrl = getCurrentPageUrl();
        const savedPagesString = localStorage.getItem(user_id);
        
        if (savedPagesString) {
            // localStorage에 저장된 값이 있다면 문자열을 그대로 사용
            const savedPages = savedPagesString.split(',');
            
            // 현재 페이지가 이미 저장되어 있는지 확인
            const isPageSaved = savedPages.includes(currentPageUrl);

            if (isPageSaved) {
                // 이미 저장되어 있는 경우, 해당 주소를 배열에서 제거
                const updatedPages = savedPages.filter(page => page !== currentPageUrl);
                localStorage.setItem(user_id, updatedPages.join(','));
                alert('페이지가 삭제되었습니다.');
            } else {
                // 저장되어 있지 않은 경우, 해당 주소를 배열에 추가
                savedPages.push(currentPageUrl);
                localStorage.setItem(user_id, savedPages.join(','));
                alert('페이지가 저장되었습니다.');
            }
        } else {
            // 저장된 값이 없는 경우, 새로운 값으로 초기화
            localStorage.setItem(user_id, currentPageUrl);
            alert('페이지가 저장되었습니다.');
        }
    } else {
        alert('로그인이 필요합니다.');
    }
}


// 쿠키에서 특정 키의 값을 가져오는 함수
function getCookie(key) {
    const cookies = document.cookie.split('; ');
    for (const cookie of cookies) {
        const [cookieKey, cookieValue] = cookie.split('=');
        if (cookieKey === key) {
            return cookieValue;
        }
    }
    return null;
}
