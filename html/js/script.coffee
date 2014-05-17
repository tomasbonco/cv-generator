# Angular

ctrl = ( $scope )->

    # "Hack" allowing Angular validate array of elements with same name (name[]).

    $scope.valid = ( field )->

        result = {}
        result.invalid = {}

        result.valid = hasClass form.elements[ field ], 'ng-valid'
        result.invalid.required = hasClass form.elements[ field ], 'ng-invalid-required'
        result.invalid.pattern = hasClass form.elements[ field ], 'ng-invalid-pattern'
    
        return result


# Source: http://youmightnotneedjquery.com/
# Checks if DOM element has specified class

hasClass = ( el, className )->

    if el.classList

        el.classList.contains className

    else

        new RegExp('(^| )' + className + '( |$)', 'gi').test(el.className);