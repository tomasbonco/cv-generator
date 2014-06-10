describe('Prefix title validation', function() {
  var $field, $scope;
  $field = null;
  $scope = null;
  afterEach(function() {
    $scope.pretitle = '';
    return $scope.$apply();
  });
  beforeEach(function() {
    $field = $('input[name="pretitle"]');
    return $scope = window.angular.element($('form')).scope();
  });
  it('should display error, when filled with numbers', function() {
    var $error;
    $scope.pretitle = '007';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    return $error.length.should.be.equal(1);
  });
  it('should display error when filled with wrong input', function() {
    var $error;
    $scope.pretitle = '.Ing';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(1);
    $scope.pretitle = 'Ing..Mgr';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    return $error.length.should.be.equal(1);
  });
  return it('should work, when filled correctly', function() {
    var $error;
    $scope.pretitle = 'Ing.';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(0);
    $scope.pretitle = 'Ing. Mgr';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(0);
    $scope.pretitle = 'Ing.Mgr.';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(0);
    $scope.pretitle = 'ing.mgr.';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(0);
    $scope.pretitle = '    ing.   mgr.    ';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    return $error.length.should.be.equal(0);
  });
});

describe('Sufix title validation', function() {
  var $field, $scope;
  $field = null;
  $scope = null;
  afterEach(function() {
    $scope.posttitle = '';
    return $scope.$apply();
  });
  beforeEach(function() {
    $field = $('input[name="posttitle"]');
    return $scope = window.angular.element($('form')).scope();
  });
  it('should display error, when filled with numbers', function() {
    var $error;
    $scope.posttitle = '007';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    return $error.length.should.be.equal(1);
  });
  it('should display error when filled with wrong input', function() {
    var $error;
    $scope.posttitle = '.Ing';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(1);
    $scope.posttitle = 'Ing..Mgr';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    return $error.length.should.be.equal(1);
  });
  return it('should work, when filled correctly', function() {
    var $error;
    $scope.posttitle = 'Ing.';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(0);
    $scope.posttitle = 'Ing. Mgr';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(0);
    $scope.posttitle = 'Ing.Mgr.';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(0);
    $scope.posttitle = 'ing.mgr.';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(0);
    $scope.posttitle = '    ing.   mgr.    ';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    return $error.length.should.be.equal(0);
  });
});

describe('Name validation', function() {
  var $field, $scope;
  $field = null;
  $scope = null;
  afterEach(function() {
    $scope.name = '';
    return $scope.$apply();
  });
  beforeEach(function() {
    $field = $('input[name="name"]');
    return $scope = window.angular.element($('form')).scope();
  });
  it('should display error when empty', function() {
    var $error;
    $error = $field.parent().find('.error:visible');
    return $error.length.should.be.equal(1);
  });
  return it('should hide the error, when filled correctly', function() {
    var $error;
    $scope.name = 'Tomas';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    return $error.length.should.be.equal(0);
  });
});

describe('Surname validation', function() {
  var $field, $scope;
  $field = null;
  $scope = null;
  afterEach(function() {
    $scope.surname = '';
    return $scope.$apply();
  });
  beforeEach(function() {
    $field = $('input[name="surname"]');
    return $scope = window.angular.element($('form')).scope();
  });
  it('should display error when empty', function() {
    var $error;
    $error = $field.parent().find('.error:visible');
    return $error.length.should.be.equal(1);
  });
  return it('should hide the error, when filled correctly', function() {
    var $error;
    $scope.surname = 'Voda';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    return $error.length.should.be.equal(0);
  });
});

describe('Phone validation', function() {
  var $field, $scope;
  $field = null;
  $scope = null;
  afterEach(function() {
    $scope._phone[0] = '';
    return $scope.$apply();
  });
  beforeEach(function() {
    $field = $('input[name="phone[0]"]');
    return $scope = window.angular.element($('form')).scope();
  });
  it('should display error when empty', function() {
    var $error;
    $error = $field.parent().find('.error:visible');
    return $error.length.should.be.equal(1);
  });
  it('should display error, when filled with letters', function() {
    var $error;
    $scope._phone[0] = 'Letters';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    return $error.length.should.be.equal(1);
  });
  it('should display error, when plus is used wrong way', function() {
    var $error;
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
    return $error.length.should.be.equal(1);
  });
  it('should display error, when brackets are used wrong way', function() {
    var $error;
    $scope._phone[0] = '()';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(1);
    $scope._phone[0] = '+()';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    return $error.length.should.be.equal(1);
  });
  return it('should work, when passed valid data', function() {
    var $error;
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
    return $error.length.should.be.equal(0);
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
  var $field, $scope;
  $field = null;
  $scope = null;
  afterEach(function() {
    $scope._email[0] = '';
    return $scope.$apply();
  });
  beforeEach(function() {
    $field = $('input[name="email[0]"]');
    return $scope = window.angular.element($('form')).scope();
  });
  it('should display error when email empty ', function() {
    var $error;
    $error = $field.parent().find('.error:visible');
    return $error.length.should.be.equal(1);
  });
  it('should display error when @ is missing', function() {
    var $error;
    $scope._email[0] = 'tonystarkindustries.com';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    return $error.length.should.be.equal(1);
  });
  it('should work when . is missing', function() {
    var $error;
    $scope._email[0] = 'tony@starkindustriescom';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    return $error.length.should.be.equal(0);
  });
  it('should display error when @ is first character', function() {
    var $error;
    $scope._email[0] = '@starkindustries.com';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    return $error.length.should.be.equal(1);
  });
  return it('should work, when filled correctly', function() {
    var $error;
    $scope._email[0] = 'tony@starkindustries.com';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    return $error.length.should.be.equal(0);
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
  var $field, $scope;
  $field = null;
  $scope = null;
  afterEach(function() {
    $scope.street = '';
    return $scope.$apply();
  });
  beforeEach(function() {
    $field = $('input[name="street"]');
    return $scope = window.angular.element($('form')).scope();
  });
  it('should display error when empty', function() {
    var $error;
    $error = $field.parent().find('.error:visible');
    return $error.length.should.be.equal(1);
  });
  return it('should hide the error, when filled correctly', function() {
    var $error;
    $scope.street = 'Bakery Street 62';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    return $error.length.should.be.equal(0);
  });
});

describe('City validation', function() {
  var $field, $scope;
  $field = null;
  $scope = null;
  afterEach(function() {
    $scope.city = '';
    return $scope.$apply();
  });
  beforeEach(function() {
    $field = $('input[name="city"]');
    return $scope = window.angular.element($('form')).scope();
  });
  it('should display error when empty', function() {
    var $error;
    $error = $field.parent().find('.error:visible');
    return $error.length.should.be.equal(1);
  });
  return it('should hide the error, when filled correctly', function() {
    var $error;
    $scope.city = 'Brno';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    return $error.length.should.be.equal(0);
  });
});

describe('PSC validation', function() {
  var $field, $scope;
  $field = null;
  $scope = null;
  afterEach(function() {
    $scope.postal = '';
    return $scope.$apply();
  });
  beforeEach(function() {
    $field = $('input[name="postal"]');
    return $scope = window.angular.element($('form')).scope();
  });
  it('should display error when empty', function() {
    var $error;
    $error = $field.parent().find('.error');
    $error = $field.parent().find('.error:visible');
    return $error.length.should.be.equal(1);
  });
  it('should throw error, when filled with letters', function() {
    var $error;
    $scope.postal = 'Brno';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    return $error.length.should.be.equal(1);
  });
  it('should throw error, when filled with more then 5 numbers or less then 5', function() {
    var $error;
    $scope.postal = '123456';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(1);
    $scope.postal = '1234';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    return $error.length.should.be.equal(1);
  });
  it('should throw error, when space is at wrong position', function() {
    var $error;
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
    var $error;
    $scope.postal = '030 00';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    $error.length.should.be.equal(0);
    $scope.postal = '03000';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    return $error.length.should.be.equal(0);
  });
});

describe('Employment since validation', function() {
  var $field, $scope;
  $field = null;
  $scope = null;
  afterEach(function() {
    $scope._c_since[0] = '';
    return $scope.$apply();
  });
  beforeEach(function() {
    $field = $('input[name="c_since[0]"]');
    return $scope = window.angular.element($('form')).scope();
  });
  it('should throw error, when filled with letters', function() {
    var $error;
    $scope._c_since[0] = 'ukulele';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    return $error.length.should.be.equal(1);
  });
  it('should throw error, when filled with data in wrong format', function() {
    var $error;
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
    return $error.length.should.be.equal(1);
  });
  return it('should work when filled correctly', function() {
    var $error;
    $scope._c_since[0] = '2010-12';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    return $error.length.should.be.equal(0);
  });
});

describe('Employment to validation', function() {
  var $field, $scope;
  $field = null;
  $scope = null;
  afterEach(function() {
    $scope._c_to[0] = '';
    return $scope.$apply();
  });
  beforeEach(function() {
    $field = $('input[name="c_to[0]"]');
    return $scope = window.angular.element($('form')).scope();
  });
  it('should throw error, when filled with letters', function() {
    var $error;
    $scope._c_to[0] = 'ukulele';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    return $error.length.should.be.equal(1);
  });
  it('should throw error, when filled with data in wrong format', function() {
    var $error;
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
    return $error.length.should.be.equal(1);
  });
  return it('should work when filled correctly', function() {
    var $error;
    $scope._c_to[0] = '2010-12';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    return $error.length.should.be.equal(0);
  });
});

describe('Add new Employment', function() {
  return it('should work, when "Add Employment" is clicked', function() {
    var $a, $companies, $scope;
    $companies = $('input[name*="c_name"]');
    $companies.should.have.length(1);
    $a = $('a', '#employment');
    angular.element($a).triggerHandler('click');
    $companies = $('input[name*="c_name"]').should.have.length(2);
    $scope = window.angular.element($('form')).scope();
    $scope.companies.pop();
    $scope._c_name.pop();
    $scope._c_position.pop();
    $scope._c_since.pop();
    $scope._c_to.pop();
    return $scope.$apply();
  });
});

describe('Education since validation', function() {
  var $field, $scope;
  $field = null;
  $scope = null;
  afterEach(function() {
    $scope._e_since[0] = '';
    return $scope.$apply();
  });
  beforeEach(function() {
    $field = $('input[name="e_since[0]"]');
    return $scope = window.angular.element($('form')).scope();
  });
  it('should throw error, when filled with letters', function() {
    var $error;
    $scope._e_since[0] = 'ukulele';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    return $error.length.should.be.equal(1);
  });
  it('should throw error, when filled with data in wrong format', function() {
    var $error;
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
    return $error.length.should.be.equal(1);
  });
  return it('should work when filled correctly', function() {
    var $error;
    $scope._e_since[0] = '2010-12';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    return $error.length.should.be.equal(0);
  });
});

describe('Education to validation', function() {
  var $field, $scope;
  $field = null;
  $scope = null;
  afterEach(function() {
    $scope._e_to[0] = '';
    return $scope.$apply();
  });
  beforeEach(function() {
    $field = $('input[name="e_to[0]"]');
    return $scope = window.angular.element($('form')).scope();
  });
  it('should throw error, when filled with letters', function() {
    var $error;
    $scope._e_to[0] = 'ukulele';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    console.log($field);
    console.log($field.parent());
    return $error.length.should.be.equal(1);
  });
  it('should throw error, when filled with data in wrong format', function() {
    var $error;
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
    return $error.length.should.be.equal(1);
  });
  return it('should work when filled correctly', function() {
    var $error;
    $scope._e_to[0] = '2010-12';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    return $error.length.should.be.equal(0);
  });
});

describe('Add new Education', function() {
  return it('should work, when "Add Education" is clicked', function() {
    var $a, $scope, $studies;
    $studies = $('input[name*="e_name"]');
    $studies.should.have.length(1);
    $a = $('a', '#education');
    angular.element($a).triggerHandler('click');
    $studies = $('input[name*="e_name"]').should.have.length(2);
    $scope = window.angular.element($('form')).scope();
    $scope.studies.pop();
    $scope._c_name.pop();
    $scope._c_position.pop();
    $scope._c_since.pop();
    $scope._c_to.pop();
    return $scope.$apply();
  });
});

describe('Add new Language', function() {
  return it('should work, when "Add Language" is clicked', function() {
    var $a, $language, $scope;
    $language = $('input[name*="l_language"]');
    $language.should.have.length(1);
    $a = $('a', '#languages');
    angular.element($a).triggerHandler('click');
    $language = $('input[name*="l_language"]').should.have.length(2);
    $scope = window.angular.element($('form')).scope();
    $scope.languages.pop();
    $scope._l_language.pop();
    $scope._l_level.pop();
    return $scope.$apply();
  });
});

describe('Add new Certificate', function() {
  return it('should work, when "Add Certificate" is clicked', function() {
    var $a, $certificate, $scope;
    $certificate = $('input[name*="certificate"]');
    $certificate.should.have.length(1);
    $a = $('a', '#certificates');
    angular.element($a).triggerHandler('click');
    $certificate = $('input[name*="certificate"]').should.have.length(2);
    $scope = window.angular.element($('form')).scope();
    $scope.certificates.pop();
    $scope._c_certificate.pop();
    return $scope.$apply();
  });
});

describe('Add new Skills', function() {
  return it('should work, when "Add Skill" is clicked', function() {
    var $a, $scope, $skill;
    $skill = $('input[name*="skill"]');
    $skill.should.have.length(1);
    $a = $('a', '#skills');
    angular.element($a).triggerHandler('click');
    $skill = $('input[name*="skill"]').should.have.length(2);
    $scope = window.angular.element($('form')).scope();
    $scope.skills.pop();
    $scope._s_skill.pop();
    $scope._s_level.pop();
    return $scope.$apply();
  });
});

describe('Add new Hobby', function() {
  return it('should work, when "Add Hobby" is clicked', function() {
    var $a, $hobby, $scope;
    $hobby = $('input[name*="hobby"]');
    $hobby.should.have.length(1);
    $a = $('a', '#hobbies');
    angular.element($a).triggerHandler('click');
    $hobby = $('input[name*="hobby"]').should.have.length(2);
    $scope = window.angular.element($('form')).scope();
    $scope.hobbies.pop();
    $scope._hobbies.pop();
    return $scope.$apply();
  });
});

describe('Password validation', function() {
  var $field, $scope;
  $field = null;
  $scope = null;
  afterEach(function() {
    $scope.password = '';
    return $scope.$apply();
  });
  beforeEach(function() {
    $field = $('input[name="password"]');
    return $scope = window.angular.element($('form')).scope();
  });
  it('should display error when empty', function() {
    var $error;
    $error = $field.parent().find('.error:visible');
    return $error.length.should.be.equal(1);
  });
  return it('should hide the error, when filled correctly', function() {
    var $error;
    $scope.password = 'MySecret';
    $scope.$apply();
    $error = $field.parent().find('.error:visible');
    return $error.length.should.be.equal(0);
  });
});

describe('Submit button', function() {
  var $field, $scope;
  $field = null;
  $scope = null;
  afterEach(function() {
    $scope.pretitle = '';
    $scope.posttitle = '';
    $scope.name = '';
    $scope.surname = '';
    $scope._phone[0] = '';
    $scope._email[0] = '';
    $scope.street = '';
    $scope.city = '';
    $scope.postal = '';
    $scope._e_since[0] = '';
    $scope._e_to[0] = '';
    $scope._c_since[0] = '';
    $scope._c_to[0] = '';
    $scope.password = '';
    return $scope.$apply();
  });
  beforeEach(function() {
    return $scope = window.angular.element($('form')).scope();
  });
  it('should be hidden when name is not filled', function() {
    var $submit;
    $scope.surname = 'Smith';
    $scope._phone[0] = '+420 123 456 78';
    $scope._email[0] = 'john@smith.com';
    $scope.street = 'Bakery Street 64';
    $scope.city = 'Boston';
    $scope.postal = '405 32';
    $scope.password = 'MySecret';
    $scope.$apply();
    $submit = $('input[type="submit"]').is(':visible');
    return $submit.should.be["false"];
  });
  it('should be hidden when surname is not filled', function() {
    var $submit;
    $scope.name = 'John';
    $scope._phone[0] = '+420 123 456 78';
    $scope._email[0] = 'john@smith.com';
    $scope.street = 'Bakery Street 64';
    $scope.city = 'Boston';
    $scope.postal = '405 32';
    $scope.password = 'MySecret';
    $scope.$apply();
    $submit = $('input[type="submit"]').is(':visible');
    return $submit.should.be["false"];
  });
  it('should be hidden when phone is not filled or wrong', function() {
    var $submit;
    $scope.name = 'John';
    $scope.surname = 'Smith';
    $scope._email[0] = 'john@smith.com';
    $scope.street = 'Bakery Street 64';
    $scope.city = 'Boston';
    $scope.postal = '405 32';
    $scope.password = 'MySecret';
    $scope.$apply();
    $submit = $('input[type="submit"]').is(':visible');
    $submit.should.be["false"];
    $scope.name = 'John';
    $scope.surname = 'Smith';
    $scope._phone[0] = 'Wrong';
    $scope._email[0] = 'john@smith.com';
    $scope.street = 'Bakery Street 64';
    $scope.city = 'Boston';
    $scope.postal = '405 32';
    $scope.password = 'MySecret';
    $scope.$apply();
    $submit = $('input[type="submit"]').is(':visible');
    return $submit.should.be["false"];
  });
  it('should be hidden when email is not filled or wrong', function() {
    var $submit;
    $scope.name = 'John';
    $scope.surname = 'Smith';
    $scope._phone[0] = '+420 123 456 78';
    $scope.street = 'Bakery Street 64';
    $scope.city = 'Boston';
    $scope.postal = '405 32';
    $scope.password = 'MySecret';
    $scope.$apply();
    $submit = $('input[type="submit"]').is(':visible');
    $submit.should.be["false"];
    $scope.name = 'John';
    $scope.surname = 'Smith';
    $scope._phone[0] = '+420 123 456 78';
    $scope._email[0] = 'johnsmith.com';
    $scope.street = 'Bakery Street 64';
    $scope.city = 'Boston';
    $scope.postal = '405 32';
    $scope.password = 'MySecret';
    $scope.$apply();
    $submit = $('input[type="submit"]').is(':visible');
    return $submit.should.be["false"];
  });
  it('should be hidden when street is not filled', function() {
    var $submit;
    $scope.name = 'John';
    $scope.surname = 'Smith';
    $scope._phone[0] = '+420 123 456 78';
    $scope._email[0] = 'john@smith.com';
    $scope.city = 'Boston';
    $scope.postal = '405 32';
    $scope.password = 'MySecret';
    $scope.$apply();
    $submit = $('input[type="submit"]').is(':visible');
    return $submit.should.be["false"];
  });
  it('should be hidden when city is not filled', function() {
    var $submit;
    $scope.name = 'John';
    $scope.surname = 'Smith';
    $scope._phone[0] = '+420 123 456 78';
    $scope._email[0] = 'john@smith.com';
    $scope.street = 'Bakery Street 64';
    $scope.postal = '405 32';
    $scope.password = 'MySecret';
    $scope.$apply();
    $submit = $('input[type="submit"]').is(':visible');
    return $submit.should.be["false"];
  });
  it('should be hidden when postal is not filled or wrong', function() {
    var $submit;
    $scope.name = 'John';
    $scope.surname = 'Smith';
    $scope._phone[0] = '+420 123 456 78';
    $scope._email[0] = 'john@smith.com';
    $scope.street = 'Bakery Street 64';
    $scope.city = 'Boston';
    $scope.password = 'MySecret';
    $scope.$apply();
    $submit = $('input[type="submit"]').is(':visible');
    $submit.should.be["false"];
    $scope.name = 'John';
    $scope.surname = 'Smith';
    $scope._phone[0] = '+420 123 456 78';
    $scope._email[0] = 'john@smith.com';
    $scope.street = 'Bakery Street 64';
    $scope.city = 'Boston';
    $scope.postal = 'Wrong';
    $scope.password = 'MySecret';
    $scope.$apply();
    $submit = $('input[type="submit"]').is(':visible');
    return $submit.should.be["false"];
  });
  it('should be hidden when password is not filled', function() {
    var $submit;
    $scope.name = 'John';
    $scope.surname = 'Smith';
    $scope._phone[0] = '+420 123 456 78';
    $scope._email[0] = 'john@smith.com';
    $scope.street = 'Bakery Street 64';
    $scope.city = 'Boston';
    $scope.postal = '405 32';
    $scope.$apply();
    $submit = $('input[type="submit"]').is(':visible');
    return $submit.should.be["false"];
  });
  it('should be hidden, when prefix title is not filled correctly', function() {
    var $submit;
    $scope.pretitle = '031';
    $scope.name = 'John';
    $scope.surname = 'Smith';
    $scope._phone[0] = '+420 123 456 78';
    $scope._email[0] = 'john@smith.com';
    $scope.street = 'Bakery Street 64';
    $scope.city = 'Boston';
    $scope.postal = '405 32';
    $scope._c_since[0] = '201010';
    $scope.password = 'MySecret';
    $scope.$apply();
    $submit = $('input[type="submit"]').is(':visible');
    return $submit.should.be["false"];
  });
  it('should be hidden, when prefix title is not filled correctly', function() {
    var $submit;
    $scope.posttitle = '031';
    $scope.name = 'John';
    $scope.surname = 'Smith';
    $scope._phone[0] = '+420 123 456 78';
    $scope._email[0] = 'john@smith.com';
    $scope.street = 'Bakery Street 64';
    $scope.city = 'Boston';
    $scope.postal = '405 32';
    $scope._c_since[0] = '201010';
    $scope.password = 'MySecret';
    $scope.$apply();
    $submit = $('input[type="submit"]').is(':visible');
    return $submit.should.be["false"];
  });
  it('should be hidden, when employment is not filled correctly', function() {
    var $submit;
    $scope.name = 'John';
    $scope.surname = 'Smith';
    $scope._phone[0] = '+420 123 456 78';
    $scope._email[0] = 'john@smith.com';
    $scope.street = 'Bakery Street 64';
    $scope.city = 'Boston';
    $scope.postal = '405 32';
    $scope._c_since[0] = '201010';
    $scope.password = 'MySecret';
    $scope.$apply();
    $submit = $('input[type="submit"]').is(':visible');
    $submit.should.be["false"];
    $scope.name = 'John';
    $scope.surname = 'Smith';
    $scope._phone[0] = '+420 123 456 78';
    $scope._email[0] = 'john@smith.com';
    $scope.street = 'Bakery Street 64';
    $scope.city = 'Boston';
    $scope.postal = '405 32';
    $scope._c_since[0] = '';
    $scope._c_to[0] = '201010';
    $scope.password = 'MySecret';
    $scope.$apply();
    $submit = $('input[type="submit"]').is(':visible');
    return $submit.should.be["false"];
  });
  it('should be hidden, when education is not filled correctly', function() {
    var $submit;
    $scope.name = 'John';
    $scope.surname = 'Smith';
    $scope._phone[0] = '+420 123 456 78';
    $scope._email[0] = 'john@smith.com';
    $scope.street = 'Bakery Street 64';
    $scope.city = 'Boston';
    $scope.postal = '405 32';
    $scope._e_since[0] = '201010';
    $scope.password = 'MySecret';
    $scope.$apply();
    $submit = $('input[type="submit"]').is(':visible');
    $submit.should.be["false"];
    $scope.name = 'John';
    $scope.surname = 'Smith';
    $scope._phone[0] = '+420 123 456 78';
    $scope._email[0] = 'john@smith.com';
    $scope.street = 'Bakery Street 64';
    $scope.city = 'Boston';
    $scope.postal = '405 32';
    $scope._e_since[0] = '';
    $scope._e_to[0] = '201010';
    $scope.password = 'MySecret';
    $scope.$apply();
    $submit = $('input[type="submit"]').is(':visible');
    return $submit.should.be["false"];
  });
  return it('should not be hidden, when form is correct', function() {
    var $submit;
    $scope.name = 'John';
    $scope.surname = 'Smith';
    $scope._phone[0] = '+420 123 456 78';
    $scope._email[0] = 'john@smith.com';
    $scope.street = 'Bakery Street 64';
    $scope.city = 'Boston';
    $scope.postal = '405 32';
    $scope.password = 'MySecret';
    $scope.$apply();
    $submit = $('input[type="submit"]').is(':visible');
    return $submit.should.be["true"];
  });
});
