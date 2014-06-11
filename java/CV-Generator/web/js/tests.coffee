
## IMPORTANT NOTE!
## Angular is not updating model when value is not correct. Also, just setting value (directly) won't apply binding (when applied manually first problem appears).
## That's why we cannot check, if valid error message is shown, we can just test if the value was ignored.



describe 'Prefix title validation', ()->

	# Config

	$field = null
	$scope = null

	afterEach ()->

		$scope.pretitle = ''
		$scope.$apply()


	beforeEach ()->

		$field = $('input[name="pretitle"]')
		$scope = window.angular.element($('form')).scope()


	# Tests

	it 'should display error, when filled with numbers', ()->

		$scope.pretitle = '007'
		$scope.$apply()	

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 1


	it 'should display error when filled with wrong input', ()->

		# Starting with dot

		$scope.pretitle = '.Ing'
		$scope.$apply()	

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 1


		# Starting with dot (second title)

		$scope.pretitle = 'Ing..Mgr'
		$scope.$apply()	

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 1	

	
	it 'should work, when filled correctly', ()->

		# Single titles

		$scope.pretitle = 'Ing.'
		$scope.$apply()	

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 0


		# Multiple titles

		$scope.pretitle = 'Ing. Mgr'
		$scope.$apply()	

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 0


		# Multiple titles, no space

		$scope.pretitle = 'Ing.Mgr.'
		$scope.$apply()	

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 0


		# All smallcaps

		$scope.pretitle = 'ing.mgr.'
		$scope.$apply()	

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 0


		# starting & ending space & multiple spaces inside

		$scope.pretitle = '    ing.   mgr.    '
		$scope.$apply()	

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 0


describe 'Sufix title validation', ()->

	# Config

	$field = null
	$scope = null

	afterEach ()->

		$scope.posttitle = ''
		$scope.$apply()


	beforeEach ()->

		$field = $('input[name="posttitle"]')
		$scope = window.angular.element($('form')).scope()


	# Tests

	it 'should display error, when filled with numbers', ()->

		$scope.posttitle = '007'
		$scope.$apply()	

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 1


	it 'should display error when filled with wrong input', ()->

		# Starting with dot

		$scope.posttitle = '.Ing'
		$scope.$apply()	

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 1


		# Starting with dot (second title)

		$scope.posttitle = 'Ing..Mgr'
		$scope.$apply()	

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 1	

	
	it 'should work, when filled correctly', ()->

		# Single titles

		$scope.posttitle = 'Ing.'
		$scope.$apply()	

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 0


		# Multiple titles

		$scope.posttitle = 'Ing. Mgr'
		$scope.$apply()	

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 0


		# Multiple titles, no space

		$scope.posttitle = 'Ing.Mgr.'
		$scope.$apply()	

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 0


		# All smallcaps

		$scope.posttitle = 'ing.mgr.'
		$scope.$apply()	

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 0


		# starting & ending space & multiple spaces inside

		$scope.posttitle = '    ing.   mgr.    '
		$scope.$apply()	

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 0


describe 'Name validation', ()->

	# Config

	$field = null
	$scope = null

	afterEach ()->

		$scope.name = ''
		$scope.$apply()


	beforeEach ()->

		$field = $('input[name="name"]')
		$scope = window.angular.element($('form')).scope()


	# Tests

	it 'should display error when empty', ()->	

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 1

	
	it 'should hide the error, when filled correctly', ()->

		$scope.name = 'Tomas'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 0


describe 'Surname validation', ()->

	# Config

	$field = null
	$scope = null

	afterEach ()->

		$scope.surname = ''
		$scope.$apply()


	beforeEach ()->

		$field = $('input[name="surname"]')
		$scope = window.angular.element($('form')).scope()


	# Tests

	it 'should display error when empty', ()->	

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 1

	
	it 'should hide the error, when filled correctly', ()->

		$scope.surname = 'Voda'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 0


describe 'Phone validation', ()->

	# Config

	$field = null
	$scope = null

	afterEach ()->

		$scope._phone[0] = ''
		$scope.$apply()


	beforeEach ()->

		$field = $('input[name="phone[0]"]')
		$scope = window.angular.element($('form')).scope()


	# Tests

	it 'should display error when empty', ()->	

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 1

	
	it 'should display error, when filled with letters', ()->

		$scope._phone[0] = 'Letters'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 1		


	it 'should display error, when plus is used wrong way', ()->
		
		# Plus in the mddle

		$scope._phone[0] = '420+33'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 1


		# Plus multiple times

		$scope._phone[0] = '+++42000'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 1


		# Just plus

		$scope._phone[0] = '+'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 1


	it 'should display error, when brackets are used wrong way', ()->
		
		# Only brackets

		$scope._phone[0] = '()'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 1



		# Plus with brackets

		$scope._phone[0] = '+()'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 1


	it 'should work, when passed valid data', ()->
		
		# With plus sign, and spaces

		$scope._phone[0] = '+420 000 000 000'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'

		$error.length.should.be.equal 0


		# With plus sign spaces and brackets

		$scope._phone[0] = '+(432) 000 22421'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 0


		# Without plus, with spaces

		$scope._phone[0] = '(432) 000 22421'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 0


		# Without spaces

		$scope._phone[0] = '+(432)00022421'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 0


		# Without spaces and plus

		$scope._phone[0] = '(432)00022421'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 0


		# Without spaces and brackets

		$scope._phone[0] = '+43200022421'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 0


		# Plain

		$scope._phone[0] = '43200022421'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 0


describe 'Add new phone', ()->

	it 'should work, when "Add phone number" is clicked', ()->	

		$phones = $('input[name*="phone"]')

		$phones.should.have.length 1

		$a = $($phones[0]).parent().find 'a'
		angular.element($a).triggerHandler 'click'

		$phones = $('input[name*="phone"]').should.have.length 2

		
		# Clear

		$scope = window.angular.element($('form')).scope()
		$scope.phones.pop()
		$scope._phone.pop()
		$scope.$apply()


describe 'Email validation', ()->

	## Email validation is using native HTML validation
	## That's why we don't have to test all particular emails

	# Config

	$field = null
	$scope = null

	afterEach ()->

		$scope._email[0] = ''
		$scope.$apply()


	beforeEach ()->

		$field = $('input[name="email[0]"]')
		$scope = window.angular.element($('form')).scope()


	# Tests

	it 'should display error when email empty ', ()->

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 1


	it 'should display error when @ is missing', ()->

		$scope._email[0] = 'tonystarkindustries.com'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 1


	it 'should work when . is missing', ()->

		## Even if it seems like an error, it's not, see:
		## http://www.w3.org/TR/html-markup/datatypes.html#form.data.emailaddress

		$scope._email[0] = 'tony@starkindustriescom'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 0


	it 'should display error when @ is first character', ()->

		$scope._email[0] = '@starkindustries.com'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 1


	it 'should work, when filled correctly', ()->

		$scope._email[0] = 'tony@starkindustries.com'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 0


describe 'Add new email', ()->

	it 'should work, when "Add phone email" is clicked', ()->	

		$emails = $('input[name*="email"]')

		$emails.should.have.length 1

		$a = $($emails[0]).parent().find 'a'
		angular.element($a).triggerHandler 'click'

		$emails = $('input[name*="email"]').should.have.length 2

		
		# Clear

		$scope = window.angular.element($('form')).scope()
		$scope.emails.pop()
		$scope._email.pop()
		$scope.$apply()


describe 'Street validation', ()->

	# Config

	$field = null
	$scope = null

	afterEach ()->

		$scope.street = ''
		$scope.$apply()


	beforeEach ()->

		$field = $('input[name="street"]')
		$scope = window.angular.element($('form')).scope()


	# Tests

	it 'should display error when empty', ()->	

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 1

	
	it 'should hide the error, when filled correctly', ()->

		$scope.street = 'Bakery Street 62'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 0


describe 'City validation', ()->

	# Config

	$field = null
	$scope = null

	afterEach ()->

		$scope.city = ''
		$scope.$apply()


	beforeEach ()->

		$field = $('input[name="city"]')
		$scope = window.angular.element($('form')).scope()


	# Tests

	it 'should display error when empty', ()->	

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 1

	
	it 'should hide the error, when filled correctly', ()->

		$scope.city = 'Brno'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 0


describe 'PSC validation', ()->

	# Config

	$field = null
	$scope = null

	afterEach ()->

		$scope.postal = ''
		$scope.$apply()


	beforeEach ()->

		$field = $('input[name="postal"]')
		$scope = window.angular.element($('form')).scope()


	# Tests

	it 'should display error when empty', ()->	

		$error = $field.parent().find '.error'	

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 1


	it 'should throw error, when filled with letters', ()->

		$scope.postal = 'Brno'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 1


	it 'should throw error, when filled with more then 5 numbers or less then 5', ()->

		# More then 5

		$scope.postal = '123456'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 1


		# Less then 5

		$scope.postal = '1234'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 1


	it 'should throw error, when space is at wrong position', ()->

		# Scenario 1 

		$scope.postal = '1 2345'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 1


		# Scenario 2

		$scope.postal = '12 345'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 1


		# Scenario 3

		$scope.postal = '1234 5'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 1


		# Scenario 4 - multiple spaces

		$scope.postal = '123  45'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 1

	
	it 'should hide the error, when filled correctly', ()->

		# WIth space

		$scope.postal = '030 00'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 0


		# Without space

		$scope.postal = '03000'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 0


describe 'Employment since validation', ()->

	# Config

	$field = null
	$scope = null

	afterEach ()->

		$scope._c_since[0] = ''
		$scope.$apply()


	beforeEach ()->

		$field = $('input[name="c_since[0]"]')
		$scope = window.angular.element($('form')).scope()


	# Tests

	it 'should throw error, when filled with letters', ()->

		# With space

		$scope._c_since[0] = 'ukulele'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 1


	it 'should throw error, when filled with data in wrong format', ()->

		# No dash

		$scope._c_since[0] = '201010'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 1


		# Huge first number

		$scope._c_since[0] = '20103-10'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 1


		# Huge second number

		$scope._c_since[0] = '2010-103'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 1


		# Way too early (year under 1900)

		$scope._c_since[0] = '1899-10'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 1


		# Way too late (year above 2099)

		$scope._c_since[0] = '2105-10'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 1


		# Wrong month

		$scope._c_since[0] = '2010-13'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 1


	it 'should work when filled correctly', ()->

		# Wrong month

		$scope._c_since[0] = '2010-12'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 0


describe 'Employment to validation', ()->

	# Config

	$field = null
	$scope = null

	afterEach ()->

		$scope._c_to[0] = ''
		$scope.$apply()


	beforeEach ()->

		$field = $('input[name="c_to[0]"]')
		$scope = window.angular.element($('form')).scope()


	# Tests

	it 'should throw error, when filled with letters', ()->

		# With space

		$scope._c_to[0] = 'ukulele'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 1


	it 'should throw error, when filled with data in wrong format', ()->

		# No dash

		$scope._c_to[0] = '201010'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 1


		# Huge first number

		$scope._c_to[0] = '20103-10'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 1


		# Huge second number

		$scope._c_to[0] = '2010-103'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 1


		# Way too early (year under 1900)

		$scope._c_to[0] = '1899-10'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 1


		# Way too late (year above 2099)

		$scope._c_to[0] = '2105-10'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 1


		# Wrong month

		$scope._c_to[0] = '2010-13'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 1	


	it 'should work when filled correctly', ()->		

		# Wrong month

		$scope._c_to[0] = '2010-12'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 0


describe 'Add new Employment', ()->

	it 'should work, when "Add Employment" is clicked', ()->	

		$companies = $('input[name*="c_name"]')

		$companies.should.have.length 1

		$a = $('a', '#employment')
		angular.element($a).triggerHandler 'click'

		$companies = $('input[name*="c_name"]').should.have.length 2

		
		# Clear

		$scope = window.angular.element($('form')).scope()

		$scope.companies.pop()
		$scope._c_name.pop()
		$scope._c_position.pop()
		$scope._c_since.pop()
		$scope._c_to.pop()

		$scope.$apply()


describe 'Education since validation', ()->

	# Config

	$field = null
	$scope = null

	afterEach ()->

		$scope._e_since[0] = ''
		$scope.$apply()


	beforeEach ()->

		$field = $('input[name="e_since[0]"]')
		$scope = window.angular.element($('form')).scope()


	# Tests

	it 'should throw error, when filled with letters', ()->

		# With space

		$scope._e_since[0] = 'ukulele'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 1

	
	it 'should throw error, when filled with data in wrong format', ()->

		# No dash

		$scope._e_since[0] = '201010'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 1


		# Huge first number

		$scope._e_since[0] = '20103-10'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 1


		# Huge second number

		$scope._e_since[0] = '2010-103'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 1


		# Way too early (year under 1900)

		$scope._e_since[0] = '1899-10'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 1


		# Way too late (year above 2099)

		$scope._e_since[0] = '2105-10'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 1


		# Wrong month

		$scope._e_since[0] = '2010-13'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 1


	it 'should work when filled correctly', ()->

		# Wrong month

		$scope._e_since[0] = '2010-12'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 0


describe 'Education to validation', ()->

	# Config

	$field = null
	$scope = null

	afterEach ()->

		$scope._e_to[0] = ''
		$scope.$apply()


	beforeEach ()->

		$field = $('input[name="e_to[0]"]')
		$scope = window.angular.element($('form')).scope()


	# Tests

	it 'should throw error, when filled with letters', ()->

		# With space

		$scope._e_to[0] = 'ukulele'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		console.log $field
		console.log $field.parent()
		$error.length.should.be.equal 1


	it 'should throw error, when filled with data in wrong format', ()->

		# No dash

		$scope._e_to[0] = '201010'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 1


		# Huge first number

		$scope._e_to[0] = '20103-10'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 1


		# Huge second number

		$scope._e_to[0] = '2010-103'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 1


		# Way too early (year under 1900)

		$scope._e_to[0] = '1899-10'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 1


		# Way too late (year above 2099)

		$scope._e_to[0] = '2105-10'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 1


		# Wrong month

		$scope._e_to[0] = '2010-13'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 1


	it 'should work when filled correctly', ()->		

		# Wrong month

		$scope._e_to[0] = '2010-12'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 0


describe 'Add new Education', ()->

	it 'should work, when "Add Education" is clicked', ()->	

		$studies = $('input[name*="e_name"]')

		$studies.should.have.length 1

		$a = $('a', '#education')
		angular.element($a).triggerHandler 'click'

		$studies = $('input[name*="e_name"]').should.have.length 2

		
		# Clear

		$scope = window.angular.element($('form')).scope()

		$scope.studies.pop()
		$scope._c_name.pop()
		$scope._c_position.pop()
		$scope._c_since.pop()
		$scope._c_to.pop()

		$scope.$apply()


describe 'Add new Language', ()->

	it 'should work, when "Add Language" is clicked', ()->	

		$language = $('input[name*="l_language"]')

		$language.should.have.length 1

		$a = $('a', '#languages')
		angular.element($a).triggerHandler 'click'

		$language = $('input[name*="l_language"]').should.have.length 2

		
		# Clear

		$scope = window.angular.element($('form')).scope()

		$scope.languages.pop()
		$scope._l_language.pop()
		$scope._l_level.pop()

		$scope.$apply()


describe 'Add new Certificate', ()->

	it 'should work, when "Add Certificate" is clicked', ()->	

		$certificate = $('input[name*="certificate"]')

		$certificate.should.have.length 1

		$a = $('a', '#certificates')
		angular.element($a).triggerHandler 'click'

		$certificate = $('input[name*="certificate"]').should.have.length 2

		
		# Clear

		$scope = window.angular.element($('form')).scope()

		$scope.certificates.pop()
		$scope._c_certificate.pop()

		$scope.$apply()


describe 'Add new Skills', ()->

	it 'should work, when "Add Skill" is clicked', ()->	

		$skill = $('input[name*="skill"]')

		$skill.should.have.length 1

		$a = $('a', '#skills')
		angular.element($a).triggerHandler 'click'

		$skill = $('input[name*="skill"]').should.have.length 2

		
		# Clear

		$scope = window.angular.element($('form')).scope()

		$scope.skills.pop()
		$scope._s_skill.pop()
		$scope._s_level.pop()

		$scope.$apply()		


describe 'Add new Hobby', ()->

	it 'should work, when "Add Hobby" is clicked', ()->	

		$hobby = $('input[name*="hobby"]')

		$hobby.should.have.length 1

		$a = $('a', '#hobbies')
		angular.element($a).triggerHandler 'click'

		$hobby = $('input[name*="hobby"]').should.have.length 2

		
		# Clear

		$scope = window.angular.element($('form')).scope()

		$scope.hobbies.pop()
		$scope._hobbies.pop()

		$scope.$apply()


describe 'Password validation', ()->

	# Config

	$field = null
	$scope = null

	afterEach ()->

		$scope.password = ''
		$scope.$apply()


	beforeEach ()->

		$field = $('input[name="password"]')
		$scope = window.angular.element($('form')).scope()


	# Tests

	it 'should display error when empty', ()->	

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 1

	
	it 'should hide the error, when filled correctly', ()->

		$scope.password = 'MySecret'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 0


describe 'Submit button', ()->

	# Config

	$field = null
	$scope = null

	afterEach ()->

		$scope.pretitle = ''
		$scope.posttitle = ''

		$scope.name = ''
		$scope.surname = ''

		$scope._phone[0] = ''
		$scope._email[0] = ''

		$scope.street = ''
		$scope.city = ''
		$scope.postal = ''

		$scope._e_since[0] = ''
		$scope._e_to[0] = ''
		$scope._c_since[0] = ''
		$scope._c_to[0] = ''

		$scope.password = ''
		$scope.$apply()


	beforeEach ()->

		$scope = window.angular.element($('form')).scope()


	# Tests

	it 'should be hidden when name is not filled', ()->	

		$scope.surname = 'Smith'

		$scope._phone[0] = '+420 123 456 78'
		$scope._email[0] = 'john@smith.com'

		$scope.street = 'Bakery Street 64'
		$scope.city = 'Boston'
		$scope.postal = '405 32'

		$scope.password = 'MySecret'

		$scope.$apply()

		$submit = $('input[type="submit"]').is(':visible')
		$submit.should.be.false


	it 'should be hidden when surname is not filled', ()->	

		$scope.name = 'John'

		$scope._phone[0] = '+420 123 456 78'
		$scope._email[0] = 'john@smith.com'

		$scope.street = 'Bakery Street 64'
		$scope.city = 'Boston'
		$scope.postal = '405 32'

		$scope.password = 'MySecret'

		$scope.$apply()

		$submit = $('input[type="submit"]').is(':visible')
		$submit.should.be.false


	it 'should be hidden when phone is not filled or wrong', ()->	

		# Not filled

		$scope.name = 'John'
		$scope.surname = 'Smith'

		$scope._email[0] = 'john@smith.com'

		$scope.street = 'Bakery Street 64'
		$scope.city = 'Boston'
		$scope.postal = '405 32'

		$scope.password = 'MySecret'

		$scope.$apply()

		$submit = $('input[type="submit"]').is(':visible')
		$submit.should.be.false


		# Wrong

		$scope.name = 'John'
		$scope.surname = 'Smith'

		$scope._phone[0] = 'Wrong'
		$scope._email[0] = 'john@smith.com'

		$scope.street = 'Bakery Street 64'
		$scope.city = 'Boston'
		$scope.postal = '405 32'

		$scope.password = 'MySecret'

		$scope.$apply()

		$submit = $('input[type="submit"]').is(':visible')
		$submit.should.be.false

	
	it 'should be hidden when email is not filled or wrong', ()->	

		# Not filled

		$scope.name = 'John'
		$scope.surname = 'Smith'

		$scope._phone[0] = '+420 123 456 78'

		$scope.street = 'Bakery Street 64'
		$scope.city = 'Boston'
		$scope.postal = '405 32'

		$scope.password = 'MySecret'

		$scope.$apply()

		$submit = $('input[type="submit"]').is(':visible')
		$submit.should.be.false


		# Wrong

		$scope.name = 'John'
		$scope.surname = 'Smith'

		$scope._phone[0] = '+420 123 456 78'
		$scope._email[0] = 'johnsmith.com'

		$scope.street = 'Bakery Street 64'
		$scope.city = 'Boston'
		$scope.postal = '405 32'

		$scope.password = 'MySecret'

		$scope.$apply()

		$submit = $('input[type="submit"]').is(':visible')
		$submit.should.be.false


	it 'should be hidden when street is not filled', ()->	

		$scope.name = 'John'
		$scope.surname = 'Smith'

		$scope._phone[0] = '+420 123 456 78'
		$scope._email[0] = 'john@smith.com'

		$scope.city = 'Boston'
		$scope.postal = '405 32'

		$scope.password = 'MySecret'

		$scope.$apply()

		$submit = $('input[type="submit"]').is(':visible')
		$submit.should.be.false


	it 'should be hidden when city is not filled', ()->	

		$scope.name = 'John'
		$scope.surname = 'Smith'

		$scope._phone[0] = '+420 123 456 78'
		$scope._email[0] = 'john@smith.com'

		$scope.street = 'Bakery Street 64'
		$scope.postal = '405 32'

		$scope.password = 'MySecret'

		$scope.$apply()

		$submit = $('input[type="submit"]').is(':visible')
		$submit.should.be.false


	it 'should be hidden when postal is not filled or wrong', ()->	

		# Not filled

		$scope.name = 'John'
		$scope.surname = 'Smith'

		$scope._phone[0] = '+420 123 456 78'
		$scope._email[0] = 'john@smith.com'

		$scope.street = 'Bakery Street 64'
		$scope.city = 'Boston'

		$scope.password = 'MySecret'

		$scope.$apply()

		$submit = $('input[type="submit"]').is(':visible')
		$submit.should.be.false


		# Wrong

		$scope.name = 'John'
		$scope.surname = 'Smith'

		$scope._phone[0] = '+420 123 456 78'
		$scope._email[0] = 'john@smith.com'

		$scope.street = 'Bakery Street 64'
		$scope.city = 'Boston'
		$scope.postal = 'Wrong'

		$scope.password = 'MySecret'

		$scope.$apply()

		$submit = $('input[type="submit"]').is(':visible')
		$submit.should.be.false


	it 'should be hidden when password is not filled', ()->	

		$scope.name = 'John'
		$scope.surname = 'Smith'

		$scope._phone[0] = '+420 123 456 78'
		$scope._email[0] = 'john@smith.com'

		$scope.street = 'Bakery Street 64'
		$scope.city = 'Boston'
		$scope.postal = '405 32'

		$scope.$apply()

		$submit = $('input[type="submit"]').is(':visible')
		$submit.should.be.false


	it 'should be hidden, when prefix title is not filled correctly', ()->

		$scope.pretitle = '031'
		$scope.name = 'John'
		$scope.surname = 'Smith'

		$scope._phone[0] = '+420 123 456 78'
		$scope._email[0] = 'john@smith.com'

		$scope.street = 'Bakery Street 64'
		$scope.city = 'Boston'
		$scope.postal = '405 32'

		$scope._c_since[0] = '201010'

		$scope.password = 'MySecret'

		$scope.$apply()

		$submit = $('input[type="submit"]').is(':visible')
		$submit.should.be.false


	it 'should be hidden, when prefix title is not filled correctly', ()->

		$scope.posttitle = '031'
		$scope.name = 'John'
		$scope.surname = 'Smith'

		$scope._phone[0] = '+420 123 456 78'
		$scope._email[0] = 'john@smith.com'

		$scope.street = 'Bakery Street 64'
		$scope.city = 'Boston'
		$scope.postal = '405 32'

		$scope._c_since[0] = '201010'

		$scope.password = 'MySecret'

		$scope.$apply()

		$submit = $('input[type="submit"]').is(':visible')
		$submit.should.be.false


	it 'should be hidden, when employment is not filled correctly', ()->

		# Wrong since

		$scope.name = 'John'
		$scope.surname = 'Smith'

		$scope._phone[0] = '+420 123 456 78'
		$scope._email[0] = 'john@smith.com'

		$scope.street = 'Bakery Street 64'
		$scope.city = 'Boston'
		$scope.postal = '405 32'

		$scope._c_since[0] = '201010'

		$scope.password = 'MySecret'

		$scope.$apply()

		$submit = $('input[type="submit"]').is(':visible')
		$submit.should.be.false


		# Wrong to

		$scope.name = 'John'
		$scope.surname = 'Smith'

		$scope._phone[0] = '+420 123 456 78'
		$scope._email[0] = 'john@smith.com'

		$scope.street = 'Bakery Street 64'
		$scope.city = 'Boston'
		$scope.postal = '405 32'

		$scope._c_since[0] = ''
		$scope._c_to[0] = '201010'

		$scope.password = 'MySecret'

		$scope.$apply()

		$submit = $('input[type="submit"]').is(':visible')
		$submit.should.be.false


	it 'should be hidden, when education is not filled correctly', ()->

		# Wrong since

		$scope.name = 'John'
		$scope.surname = 'Smith'

		$scope._phone[0] = '+420 123 456 78'
		$scope._email[0] = 'john@smith.com'

		$scope.street = 'Bakery Street 64'
		$scope.city = 'Boston'
		$scope.postal = '405 32'

		$scope._e_since[0] = '201010'

		$scope.password = 'MySecret'

		$scope.$apply()

		$submit = $('input[type="submit"]').is(':visible')
		$submit.should.be.false


		# Wrong to

		$scope.name = 'John'
		$scope.surname = 'Smith'

		$scope._phone[0] = '+420 123 456 78'
		$scope._email[0] = 'john@smith.com'

		$scope.street = 'Bakery Street 64'
		$scope.city = 'Boston'
		$scope.postal = '405 32'

		$scope._e_since[0] = ''
		$scope._e_to[0] = '201010'

		$scope.password = 'MySecret'

		$scope.$apply()

		$submit = $('input[type="submit"]').is(':visible')
		$submit.should.be.false


	it 'should not be hidden, when form is correct', ()->

		$scope.name = 'John'
		$scope.surname = 'Smith'

		$scope._phone[0] = '+420 123 456 78'
		$scope._email[0] = 'john@smith.com'

		$scope.street = 'Bakery Street 64'
		$scope.city = 'Boston'
		$scope.postal = '405 32'

		$scope.password = 'MySecret'

		$scope.$apply()

		$submit = $('input[type="submit"]').is(':visible')
		$submit.should.be.true