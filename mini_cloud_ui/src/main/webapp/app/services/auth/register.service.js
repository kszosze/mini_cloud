(function () {
    'use strict';

    angular
        .module('miniCloudUiApp')
        .factory('Register', Register);

    Register.$inject = ['$resource'];

    function Register ($resource) {
        return $resource('api/register', {}, {});
    }
})();
