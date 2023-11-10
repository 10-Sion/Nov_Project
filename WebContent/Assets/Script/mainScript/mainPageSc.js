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

// 카드 무한 스크롤 
$(document).ready(function () {
  var cards = $('#card-slider .slider-item').toArray();

  startAnim(cards);

  function startAnim(array) {
      if (array.length >= 4) {
          TweenMax.fromTo(
              array[0],
              0.5,
              { x: 0, y: 0, opacity: 0.75 },
              {
                  x: 0,
                  y: -120,
                  opacity: 0,
                  zIndex: 0,
                  delay: 0.03,
                  ease: Cubic.easeInOut,
                  onComplete: function () {
                      sortArray(array);
                  },
              }
          );

          TweenMax.fromTo(
              array[1],
              0.5,
              { x: 79, y: 125, opacity: 1, zIndex: 1 },
              {
                  x: 0,
                  y: 0,
                  opacity: 0.75,
                  zIndex: 0,
                  boxShadow: '-5px 8px 8px 0 rgba(82, 89, 129, 0.05)',
                  ease: Cubic.easeInOut,
              }
          );

          TweenMax.to(array[2], 0.5, {
              bezier: [
                  { x: 0, y: 250 },
                  { x: 65, y: 200 },
                  { x: 79, y: 125 },
              ],
              boxShadow: '-5px 8px 8px 0 rgba(82, 89, 129, 0.05)',
              zIndex: 1,
              opacity: 1,
              ease: Cubic.easeInOut,
          });

          TweenMax.fromTo(
              array[3],
              0.5,
              { x: 0, y: 400, opacity: 0, zIndex: 0 },
              { x: 0, y: 250, opacity: 0.75, zIndex: 0, ease: Cubic.easeInOut }
          );
      } else {
          $('#card-slider').append('<p>Sorry, carousel should contain more than 3 slides</p>');
      }
  }

  function sortArray(array) {
      clearTimeout(delay);
      var delay = setTimeout(function () {
          var firstElem = array.shift();
          array.push(firstElem);
          startAnim(array);
      }, 3000);
  }
});