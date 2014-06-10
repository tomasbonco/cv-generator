describe 'Name validation', ()->

	$field = $('input[name="name"]')

	it 'should display error when empty', ()->	

		$error = $field.parent().find '.error'	

		($error.hasClass('ng-hide')).should.be.false

	
	it 'should hide the error, when filled correctly', ()->

		$scope = window.angular.element($('form')).scope()

		$scope.name = 'Tomas'
		$scope.$apply()

		$error = $field.parent().find '.error'
		($error.hasClass('ng-hide')).should.be.true

		# Clear

		$scope.name = ''
		$scope.$apply()



describe 'Surname validation', ()->

	$field = $('input[name="surname"]')

	it 'should display error when empty', ()->	

		$error = $field.parent().find '.error'	

		($error.hasClass('ng-hide')).should.be.false

	
	it 'should hide the error, when filled correctly', ()->

		$scope = window.angular.element($('form')).scope()

		$scope.surname = 'Voda'
		$scope.$apply()

		$error = $field.parent().find '.error'
		($error.hasClass('ng-hide')).should.be.true

		# Clear

		$scope.surname = ''
		$scope.$apply()



describe 'Phone validation', ()->

	## Angular is not updating model when value is not correct. Also, just setting value wont apply binding.
	## That's why we cannot check, if valid error message is shown, we can just test if the value was ignored.

	$field = $('input[name="phone[0]"]')

	it 'should display error when empty', ()->	

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 1

	
	it 'should display error, when filled with letters', ()->

		## $scope = window.angular.element($('form')).scope()

		$scope = window.angular.element($field).scope()

		$scope._phone[0] = 'Letters'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 1
	
		# Clear

		$scope._phone[0] = ''
		$scope.$apply()


	it 'should display error, when plus is used wrong way', ()->

		$scope = window.angular.element($('form')).scope()

		
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


		# Clear

		$scope._phone[0] = ''
		$scope.$apply()


	it 'should display error, when brackets are used wrong way', ()->

		$scope = window.angular.element($('form')).scope()

		
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


		# Clear

		$scope._phone[0] = ''
		$scope.$apply()


	it 'should work, when passed valid data', ()->

		$scope = window.angular.element($('form')).scope()

		
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


		# Clear

		$scope._phone[0] = ''
		$scope.$apply()


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

	$field = $('input[name="email[0]"]')

	it 'should display error when email empty ', ()->	

		$scope = window.angular.element($('form')).scope()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 1


	it 'should display error when @ is missing', ()->

		$scope = window.angular.element($('form')).scope()

		$scope._email[0] = 'tonystarkindustries.com'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 1


		# Clear

		$scope._email[0] = ''
		$scope.$apply()


	it 'should work when . is missing', ()->

		## Even if it seems like an error, it's not, see:
		## http://www.w3.org/TR/html-markup/datatypes.html#form.data.emailaddress

		$scope = window.angular.element($('form')).scope()

		$scope._email[0] = 'tony@starkindustriescom'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 0


		# Clear

		$scope._email[0] = ''
		$scope.$apply()


	it 'should display error when @ is first character', ()->

		$scope = window.angular.element($('form')).scope()

		$scope._email[0] = '@starkindustries.com'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 1


		# Clear

		$scope._email[0] = ''
		$scope.$apply()


	it 'should work, when filled correctly', ()->

		$scope = window.angular.element($('form')).scope()

		$scope._email[0] = 'tony@starkindustries.com'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 0


		# Clear

		$scope._email[0] = ''
		$scope.$apply()


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

	$field = $('input[name="street"]')

	it 'should display error when empty', ()->	

		$error = $field.parent().find '.error'	

		($error.hasClass('ng-hide')).should.be.false

	
	it 'should hide the error, when filled correctly', ()->

		$scope = window.angular.element($('form')).scope()

		$scope.street = 'Bakery Street 62'
		$scope.$apply()

		$error = $field.parent().find '.error'
		($error.hasClass('ng-hide')).should.be.true

		# Clear

		$scope.street = ''
		$scope.$apply()


describe 'City validation', ()->

	$field = $('input[name="city"]')

	it 'should display error when empty', ()->	

		$error = $field.parent().find '.error'	

		($error.hasClass('ng-hide')).should.be.false

	
	it 'should hide the error, when filled correctly', ()->

		$scope = window.angular.element($('form')).scope()

		$scope.city = 'Brno'
		$scope.$apply()

		$error = $field.parent().find '.error'
		($error.hasClass('ng-hide')).should.be.true

		# Clear

		$scope.city = ''
		$scope.$apply()


describe 'PSC validation', ()->

	$field = $('input[name="postal"]')

	it 'should display error when empty', ()->	

		$error = $field.parent().find '.error'	

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 1


	it 'should throw error, when filled with letters', ()->

		$scope = window.angular.element($('form')).scope()

		$scope.postal = 'Brno'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 1


		# Clear

		$scope.postal = ''
		$scope.$apply()


	it 'should throw error, when filled with more then 5 numbers or less then 5', ()->

		$scope = window.angular.element($('form')).scope()


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


		# Clear

		$scope.postal = ''
		$scope.$apply()


	it 'should throw error, when space is at wrong position', ()->

		$scope = window.angular.element($('form')).scope()


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

		$scope = window.angular.element($('form')).scope()

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


		# Clear

		$scope.postal = ''
		$scope.$apply()


describe 'Employment since validation', ()->

	$field = $('input[name="c_since[0"]')

	it 'should throw error, when filled with letters', ()->

		$scope = window.angular.element($('form')).scope()
		$field = $('input[name="c_since[0]"]')


		# With space

		$scope._c_since[0] = 'ukulele'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 1


		# Clear

		$scope._c_since[0] = ''
		$scope.$apply()	


	it 'should throw error, when filled with data in wrong format', ()->

		$scope = window.angular.element($('form')).scope()
		$field = $('input[name="c_since[0]"]')


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


		# Clear

		$scope._c_since[0] = ''
		$scope.$apply()		


	it 'should work when filled correctly', ()->

		$scope = window.angular.element($('form')).scope()
		$field = $('input[name="c_since[0]"]')


		# Wrong month

		$scope._c_since[0] = '2010-12'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 0


		# Clear

		$scope._c_since[0] = ''
		$scope.$apply()


describe 'Employment to validation', ()->

	it 'should throw error, when filled with letters', ()->

		$scope = window.angular.element($('form')).scope()
		$field = $('input[name="c_to[0]"]')


		# With space

		$scope._c_to[0] = 'ukulele'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		console.log $field
		console.log $field.parent()
		$error.length.should.be.equal 1


		# Clear

		$scope._c_to[0] = ''
		$scope.$apply()	


	it 'should throw error, when filled with data in wrong format', ()->

		$scope = window.angular.element($('form')).scope()
		$field = $('input[name="c_to[0]"]')


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


		# Clear

		$scope._c_to[0] = ''
		$scope.$apply()		


	it 'should work when filled correctly', ()->

		$scope = window.angular.element($('form')).scope()
		$field = $('input[name="c_to[0]"]')
		

		# Wrong month

		$scope._c_to[0] = '2010-12'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 0


		# Clear

		$scope._c_to[0] = ''
		$scope.$apply()



describe 'Education since validation', ()->

	$field = $('input[name="e_since[0"]')

	it 'should throw error, when filled with letters', ()->

		$scope = window.angular.element($('form')).scope()
		$field = $('input[name="e_since[0]"]')


		# With space

		$scope._e_since[0] = 'ukulele'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 1


		# Clear

		$scope._e_since[0] = ''
		$scope.$apply()	


	it 'should throw error, when filled with data in wrong format', ()->

		$scope = window.angular.element($('form')).scope()
		$field = $('input[name="e_since[0]"]')


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


		# Clear

		$scope._e_since[0] = ''
		$scope.$apply()		


	it 'should work when filled correctly', ()->

		$scope = window.angular.element($('form')).scope()
		$field = $('input[name="e_since[0]"]')


		# Wrong month

		$scope._e_since[0] = '2010-12'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 0


		# Clear

		$scope._e_since[0] = ''
		$scope.$apply()


describe 'Education to validation', ()->

	it 'should throw error, when filled with letters', ()->

		$scope = window.angular.element($('form')).scope()
		$field = $('input[name="e_to[0]"]')


		# With space

		$scope._e_to[0] = 'ukulele'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		console.log $field
		console.log $field.parent()
		$error.length.should.be.equal 1


		# Clear

		$scope._e_to[0] = ''
		$scope.$apply()	


	it 'should throw error, when filled with data in wrong format', ()->

		$scope = window.angular.element($('form')).scope()
		$field = $('input[name="e_to[0]"]')


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


		# Clear

		$scope._e_to[0] = ''
		$scope.$apply()		


	it 'should work when filled correctly', ()->

		$scope = window.angular.element($('form')).scope()
		$field = $('input[name="e_to[0]"]')
		

		# Wrong month

		$scope._e_to[0] = '2010-12'
		$scope.$apply()

		$error = $field.parent().find '.error:visible'
		$error.length.should.be.equal 0


		# Clear

		$scope._e_to[0] = ''
		$scope.$apply()	
