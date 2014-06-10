describe('Name validation', function() {
  var $field;
  $field = $('input[name="name"]');
  it('should display error when empty', function() {
    var $error;
    $error = $field.parent().find('.error');
    return ($error.hasClass('ng-hide')).should.be["false"];
  });
  return it('should hide the error, when filled correctly', function() {
    var $error, $scope;
    $scope = window.angular.element($('form')).scope();
    $scope.name = 'Tomas';
    $scope.$apply();
    $error = $field.parent().find('.error');
    ($error.hasClass('ng-hide')).should.be["true"];
    $scope.name = '';
    return $scope.$apply();
  });
});

describe('Surname validation', function() {
  var $field;
  $field = $('input[name="surname"]');
  it('should display error when empty', function() {
    var $error;
    $error = $field.parent().find('.error');
    return ($error.hasClass('ng-hide')).should.be["false"];
  });
  return it('should hide the error, when filled correctly', function() {
    var $error, $scope;
    $scope = window.angular.element($('form')).scope();
    $scope.surname = 'Voda';
    $scope.$apply();
    $error = $field.parent().find('.error');
    ($error.hasClass('ng-hide')).should.be["true"];
    $scope.surname = '';
    return $scope.$apply();
  });
});

describe('Phone validation', function() {
  var $field;
  $field = $('input[name="phone[0]"]');
  it('should display error when empty', function() {
    var $error;
    $error = $field.parent().find('.error:visible');
    return $error.length.should.be.equal(1);
  });
  it('should display error, when filled with letters', function() {
    var $error, $scope;
    $scope = window.angular.element($field).scope();
    $scope._phone[0] = 'Letters';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(1);
    $scope._phone[0] = '';
    return $scope.$apply();
  });
  it('should display error, when plus is used wrong way', function() {
    var $error, $scope;
    $scope = window.angular.element($('form')).scope();
    $scope._phone[0] = '420+33';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(1);
    $scope._phone[0] = '+++42000';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(1);
    $scope._phone[0] = '+';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(1);
    $scope._phone[0] = '';
    return $scope.$apply();
  });
  it('should display error, when brackets are used wrong way', function() {
    var $error, $scope;
    $scope = window.angular.element($('form')).scope();
    $scope._phone[0] = '()';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(1);
    $scope._phone[0] = '+()';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(1);
    $scope._phone[0] = '';
    return $scope.$apply();
  });
  return it('should work, when passed valid data', function() {
    var $error, $scope;
    $scope = window.angular.element($('form')).scope();
    $scope._phone[0] = '+420 000 000 000';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(0);
    $scope._phone[0] = '+(432) 000 22421';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(0);
    $scope._phone[0] = '(432) 000 22421';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(0);
    $scope._phone[0] = '+(432)00022421';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(0);
    $scope._phone[0] = '(432)00022421';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(0);
    $scope._phone[0] = '+43200022421';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(0);
    $scope._phone[0] = '43200022421';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(0);
    $scope._phone[0] = '';
    return $scope.$apply();
  });
});

describe('Add new phone', function() {
  return it('should work, when "Add phone number" is clicked', function() {
    var $a, $phones, $scope;
    $phones = $('input[name*="phone"]');
    $phones.should.have.length(1);
    $a = $($phones[0]).parent().find('a');
    angular.element($a).triggerHandler('click');
    $phones = $('input[name*="phone"]').should.have.length(2);
    $scope = window.angular.element($('form')).scope();
    $scope.phones.pop();
    $scope._phone.pop();
    return $scope.$apply();
  });
});

describe('Email validation', function() {
  var $field;
  $field = $('input[name="email[0]"]');
  it('should display error when email empty ', function() {
    var $error, $scope;
    $scope = window.angular.element($('form')).scope();
    $error = $field.parent().find('.error:visible');
    return $error.length.should.be.equal(1);
  });
  it('should display error when @ is missing', function() {
    var $error, $scope;
    $scope = window.angular.element($('form')).scope();
    $scope._email[0] = 'tonystarkindustries.com';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(1);
    $scope._email[0] = '';
    return $scope.$apply();
  });
  it('should work when . is missing', function() {
    var $error, $scope;
    $scope = window.angular.element($('form')).scope();
    $scope._email[0] = 'tony@starkindustriescom';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(0);
    $scope._email[0] = '';
    return $scope.$apply();
  });
  it('should display error when @ is first character', function() {
    var $error, $scope;
    $scope = window.angular.element($('form')).scope();
    $scope._email[0] = '@starkindustries.com';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(1);
    $scope._email[0] = '';
    return $scope.$apply();
  });
  return it('should work, when filled correctly', function() {
    var $error, $scope;
    $scope = window.angular.element($('form')).scope();
    $scope._email[0] = 'tony@starkindustries.com';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(0);
    $scope._email[0] = '';
    return $scope.$apply();
  });
});

describe('Add new email', function() {
  return it('should work, when "Add phone email" is clicked', function() {
    var $a, $emails, $scope;
    $emails = $('input[name*="email"]');
    $emails.should.have.length(1);
    $a = $($emails[0]).parent().find('a');
    angular.element($a).triggerHandler('click');
    $emails = $('input[name*="email"]').should.have.length(2);
    $scope = window.angular.element($('form')).scope();
    $scope.emails.pop();
    $scope._email.pop();
    return $scope.$apply();
  });
});

describe('Street validation', function() {
  var $field;
  $field = $('input[name="street"]');
  it('should display error when empty', function() {
    var $error;
    $error = $field.parent().find('.error');
    return ($error.hasClass('ng-hide')).should.be["false"];
  });
  return it('should hide the error, when filled correctly', function() {
    var $error, $scope;
    $scope = window.angular.element($('form')).scope();
    $scope.street = 'Bakery Street 62';
    $scope.$apply();
    $error = $field.parent().find('.error');
    ($error.hasClass('ng-hide')).should.be["true"];
    $scope.street = '';
    return $scope.$apply();
  });
});

describe('City validation', function() {
  var $field;
  $field = $('input[name="city"]');
  it('should display error when empty', function() {
    var $error;
    $error = $field.parent().find('.error');
    return ($error.hasClass('ng-hide')).should.be["false"];
  });
  return it('should hide the error, when filled correctly', function() {
    var $error, $scope;
    $scope = window.angular.element($('form')).scope();
    $scope.city = 'Brno';
    $scope.$apply();
    $error = $field.parent().find('.error');
    ($error.hasClass('ng-hide')).should.be["true"];
    $scope.city = '';
    return $scope.$apply();
  });
});

describe('PSC validation', function() {
  var $field;
  $field = $('input[name="postal"]');
  it('should display error when empty', function() {
    var $error;
    $error = $field.parent().find('.error');
    $error = $field.parent().find('.error:visible');
    return $error.length.should.be.equal(1);
  });
  it('should throw error, when filled with letters', function() {
    var $error, $scope;
    $scope = window.angular.element($('form')).scope();
    $scope.postal = 'Brno';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(1);
    $scope.postal = '';
    return $scope.$apply();
  });
  it('should throw error, when filled with more then 5 numbers or less then 5', function() {
    var $error, $scope;
    $scope = window.angular.element($('form')).scope();
    $scope.postal = '123456';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(1);
    $scope.postal = '1234';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(1);
    $scope.postal = '';
    return $scope.$apply();
  });
  it('should throw error, when space is at wrong position', function() {
    var $error, $scope;
    $scope = window.angular.element($('form')).scope();
    $scope.postal = '1 2345';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(1);
    $scope.postal = '12 345';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(1);
    $scope.postal = '1234 5';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(1);
    $scope.postal = '123  45';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    return $error.length.should.be.equal(1);
  });
  return it('should hide the error, when filled correctly', function() {
    var $error, $scope;
    $scope = window.angular.element($('form')).scope();
    $scope.postal = '030 00';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(0);
    $scope.postal = '03000';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(0);
    $scope.postal = '';
    return $scope.$apply();
  });
});

describe('Employment since validation', function() {
  var $field;
  $field = $('input[name="c_since[0"]');
  it('should throw error, when filled with letters', function() {
    var $error, $scope;
    $scope = window.angular.element($('form')).scope();
    $field = $('input[name="c_since[0]"]');
    $scope._c_since[0] = 'ukulele';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(1);
    $scope._c_since[0] = '';
    return $scope.$apply();
  });
  it('should throw error, when filled with data in wrong format', function() {
    var $error, $scope;
    $scope = window.angular.element($('form')).scope();
    $field = $('input[name="c_since[0]"]');
    $scope._c_since[0] = '201010';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(1);
    $scope._c_since[0] = '20103-10';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(1);
    $scope._c_since[0] = '2010-103';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(1);
    $scope._c_since[0] = '1899-10';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(1);
    $scope._c_since[0] = '2105-10';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(1);
    $scope._c_since[0] = '2010-13';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(1);
    $scope._c_since[0] = '';
    return $scope.$apply();
  });
  return it('should work when filled correctly', function() {
    var $error, $scope;
    $scope = window.angular.element($('form')).scope();
    $field = $('input[name="c_since[0]"]');
    $scope._c_since[0] = '2010-12';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(0);
    $scope._c_since[0] = '';
    return $scope.$apply();
  });
});

describe('Employment to validation', function() {
  it('should throw error, when filled with letters', function() {
    var $error, $field, $scope;
    $scope = window.angular.element($('form')).scope();
    $field = $('input[name="c_to[0]"]');
    $scope._c_to[0] = 'ukulele';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    console.log($field);
    console.log($field.parent());
    $error.length.should.be.equal(1);
    $scope._c_to[0] = '';
    return $scope.$apply();
  });
  it('should throw error, when filled with data in wrong format', function() {
    var $error, $field, $scope;
    $scope = window.angular.element($('form')).scope();
    $field = $('input[name="c_to[0]"]');
    $scope._c_to[0] = '201010';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(1);
    $scope._c_to[0] = '20103-10';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(1);
    $scope._c_to[0] = '2010-103';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(1);
    $scope._c_to[0] = '1899-10';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(1);
    $scope._c_to[0] = '2105-10';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(1);
    $scope._c_to[0] = '2010-13';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(1);
    $scope._c_to[0] = '';
    return $scope.$apply();
  });
  return it('should work when filled correctly', function() {
    var $error, $field, $scope;
    $scope = window.angular.element($('form')).scope();
    $field = $('input[name="c_to[0]"]');
    $scope._c_to[0] = '2010-12';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(0);
    $scope._c_to[0] = '';
    return $scope.$apply();
  });
});

describe('Education since validation', function() {
  var $field;
  $field = $('input[name="e_since[0"]');
  it('should throw error, when filled with letters', function() {
    var $error, $scope;
    $scope = window.angular.element($('form')).scope();
    $field = $('input[name="e_since[0]"]');
    $scope._e_since[0] = 'ukulele';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(1);
    $scope._e_since[0] = '';
    return $scope.$apply();
  });
  it('should throw error, when filled with data in wrong format', function() {
    var $error, $scope;
    $scope = window.angular.element($('form')).scope();
    $field = $('input[name="e_since[0]"]');
    $scope._e_since[0] = '201010';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(1);
    $scope._e_since[0] = '20103-10';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(1);
    $scope._e_since[0] = '2010-103';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(1);
    $scope._e_since[0] = '1899-10';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(1);
    $scope._e_since[0] = '2105-10';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(1);
    $scope._e_since[0] = '2010-13';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(1);
    $scope._e_since[0] = '';
    return $scope.$apply();
  });
  return it('should work when filled correctly', function() {
    var $error, $scope;
    $scope = window.angular.element($('form')).scope();
    $field = $('input[name="e_since[0]"]');
    $scope._e_since[0] = '2010-12';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(0);
    $scope._e_since[0] = '';
    return $scope.$apply();
  });
});

describe('Education to validation', function() {
  it('should throw error, when filled with letters', function() {
    var $error, $field, $scope;
    $scope = window.angular.element($('form')).scope();
    $field = $('input[name="e_to[0]"]');
    $scope._e_to[0] = 'ukulele';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    console.log($field);
    console.log($field.parent());
    $error.length.should.be.equal(1);
    $scope._e_to[0] = '';
    return $scope.$apply();
  });
  it('should throw error, when filled with data in wrong format', function() {
    var $error, $field, $scope;
    $scope = window.angular.element($('form')).scope();
    $field = $('input[name="e_to[0]"]');
    $scope._e_to[0] = '201010';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(1);
    $scope._e_to[0] = '20103-10';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(1);
    $scope._e_to[0] = '2010-103';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(1);
    $scope._e_to[0] = '1899-10';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(1);
    $scope._e_to[0] = '2105-10';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(1);
    $scope._e_to[0] = '2010-13';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(1);
    $scope._e_to[0] = '';
    return $scope.$apply();
  });
  return it('should work when filled correctly', function() {
    var $error, $field, $scope;
    $scope = window.angular.element($('form')).scope();
    $field = $('input[name="e_to[0]"]');
    $scope._e_to[0] = '2010-12';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(0);
    $scope._e_to[0] = '';
    return $scope.$apply();
  });
});
