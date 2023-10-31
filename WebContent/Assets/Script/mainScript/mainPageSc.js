$(document).ready(function() {
    const $cards = $('.card');

    // Open and close card when clicked on card
    $cards.find('.js-expander').click(function() {
        const $thisCard = $(this).closest('.card');

        if ($thisCard.hasClass('is-collapsed')) {
            $cards.not($thisCard).removeClass('is-expanded').addClass('is-collapsed').addClass('is-inactive');
            $thisCard.removeClass('is-collapsed').addClass('is-expanded');

            if ($cards.not($thisCard).hasClass('is-inactive')) {
                // Do nothing
            } else {
                $cards.not($thisCard).addClass('is-inactive');
            }
        } else {
            $thisCard.removeClass('is-expanded').addClass('is-collapsed');
            $cards.not($thisCard).removeClass('is-inactive');
        }
    });

    // Close card when clicking on cross
    $cards.find('.js-collapser').click(function() {
        const $thisCard = $(this).closest('.card');

        $thisCard.removeClass('is-expanded').addClass('is-collapsed');
        $cards.not($thisCard).removeClass('is-inactive');
    });
});
