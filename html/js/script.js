var ctrl, set_section_height;

set_section_height = function() {
  var window_height;
  window_height = $(window).height();
  return $('section.full').each(function() {
    var wrapper_height;
    wrapper_height = $(this).find('.wrapper-rs').outerHeight();
    if ((window_height + 20) < wrapper_height) {
      return $(this).height(wrapper_height);
    } else {
      return $(this).height(window_height + 20);
    }
  });
};

$(document).ready(function() {
  return set_section_height();
});

$(window).resize(function() {
  return set_section_height();
});

$('.next-page').click(function() {
  var next, parent;
  parent = $(this).parent();
  while (!parent.is('section')) {
    if (parent.is('body' || parent.is('html' || parent.is(document)))) {
      console.error('Check your code! Element .next-page MUST BE inside section.');
    }
    parent = parent.parent();
  }
  next = parent.next();
  return $('html, body').animate({
    scrollTop: next.offset().top
  }, 750);
});

ctrl = function($scope) {
  return $scope.valid = function(field) {
    var result;
    result = {};
    result.invalid = {};
    result.valid = $(form.elements[field]).hasClass('ng-valid');
    result.invalid.required = $(form.elements[field]).hasClass('ng-invalid-required');
    result.invalid.pattern = $(form.elements[field]).hasClass('ng-invalid-pattern');
    return result;
  };
};
