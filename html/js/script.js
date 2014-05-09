var ctrl, hasClass;

ctrl = function($scope) {
  return $scope.valid = function(field) {
    var result;
    result = {};
    result.invalid = {};
    result.valid = hasClass(form.elements[field], 'ng-valid');
    result.invalid.required = hasClass(form.elements[field], 'ng-invalid-required');
    result.invalid.pattern = hasClass(form.elements[field], 'ng-invalid-pattern');
    return result;
  };
};

hasClass = function(el, className) {
  if (el.classList) {
    return el.classList.contains(className);
  } else {
    return new RegExp('(^| )' + className + '( |$)', 'gi').test(el.className);
  }
};
