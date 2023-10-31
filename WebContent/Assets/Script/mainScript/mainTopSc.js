$(document).ready(function() {
        const $searchContainer = $('.search-container');
        const $searchInput = $('.search-input');

        $searchInput.on('click', function(event) {
            event.stopPropagation();
            if ($searchContainer.hasClass('moved-left')) {
                $searchContainer.removeClass('moved-left');
                $searchInput.removeClass('expanded');
            } else {
                $searchContainer.addClass('moved-left');
                $searchInput.addClass('expanded');
            }
        });

        $(document).on('click', function(event) {
            $searchContainer.removeClass('moved-left');
            $searchInput.removeClass('expanded');
        });
    });