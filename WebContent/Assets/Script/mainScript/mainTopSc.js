$(document).ready(function() {
    const $searchContainer = $('.search-container');
    const $searchInput = $('.search-input');

    // 1번 처리
    $searchContainer.on('mouseenter', function() {
        $searchContainer.addClass('moved');

        // 2번 처리
        $searchInput.addClass('expanded');
    });

    $searchContainer.on('mouseleave', function() {
        $searchContainer.removeClass('moved');

        // 2번 처리 (이 부분에 대한 추가 로직 필요)
        $searchInput.removeClass('expanded');
    });
});
