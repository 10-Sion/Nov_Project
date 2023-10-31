$(document).ready(function() {
    const $searchContainer = $('.search-container');
    const $searchInput = $('.search-input');
    let animationInProgress = false; // 애니메이션 중인지 여부를 나타내는 플래그
    let isExpanded = false; // 검색 창이 확장되었는지 여부를 나타내는 플래그

    $searchInput.on('click', function(event) {
        event.stopPropagation();
        if (!animationInProgress) {
            animationInProgress = true;

            if (isExpanded) {
                // 3. 검색 창 크기 축소
                $searchInput.removeClass('expanded');
            } else {
                // 1. 검색 창 좌측 이동
                $searchContainer.addClass('moved-left');
            }

            // 애니메이션 종료 후 다음 단계 진행
            $searchInput.one('transitionend', function() {
                if (isExpanded) {
                    // 4. 검색 창 우측 이동
                    $searchContainer.removeClass('moved-left');
                    isExpanded = false;
                } else {
                    // 2. 검색 창 크기 확대
                    $searchInput.addClass('expanded');
                    isExpanded = true;
                }
                animationInProgress = false;
            });
        }
    });

    $(document).on('click', function(event) {
        if (isExpanded && !animationInProgress) {
            animationInProgress = true;

            // 3. 검색 창 크기 축소
            $searchInput.removeClass('expanded');

            // 애니메이션 종료 후 다음 단계 진행
            $searchInput.one('transitionend', function() {
                // 4. 검색 창 우측 이동
                $searchContainer.removeClass('moved-left');
                isExpanded = false;
                animationInProgress = false;
            });
        }
    });
});
