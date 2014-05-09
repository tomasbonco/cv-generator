var ctrl;

ctrl = function($scope) {
  return $scope.valid = function(field) {
    var result;
    result = {};
    result.invalid = {};
    result.valid = $(form.elements[field]).hasClass('ng-valid');
    result.invalid.required = $(form.elements[field]).hasClass('ng-invalid-required');
    result.invalid.pattern = $(form.elements[field]).hasClass('ng-invalid-pattern');
    console.log(result);
    return result;
  };
};
