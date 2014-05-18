# CSS

set_section_height = ()->

	window_height = $(window).height()

	$('section.full').each ()->

		wrapper_height = $(@).find( '.wrapper-rs' ).outerHeight()

		if ( window_height + 20 ) < wrapper_height

			$(@).height wrapper_height

		else

			$(@).height window_height + 20


$(document).ready ()-> set_section_height()
$(window).resize ()-> set_section_height()



# Next-page

$('.next-page').click ()->

	parent =  $(@).parent()

	while ! parent.is 'section'

		if parent.is 'body' || parent.is 'html' || parent.is document

			console.error 'Check your code! Element .next-page MUST BE inside section.'

		parent = parent.parent()

	next = parent.next()

	$('html, body').animate { scrollTop: next.offset().top }, 750



# Angular controller

ctrl = ( $scope )->

	# "Hack" allowing Angular validate array of elements with same name (name[]).

	$scope.valid = ( field )->

		result = {}
		result.invalid = {}

		result.valid = $(form.elements[ field ]).hasClass('ng-valid')
		result.invalid.required = $(form.elements[ field ]).hasClass('ng-invalid-required')
		result.invalid.pattern = $(form.elements[ field ]).hasClass('ng-invalid-pattern')
	
		return result