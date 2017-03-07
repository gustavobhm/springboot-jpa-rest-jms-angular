'use strict';

angular.module('app', [
  'ngRoute',
  'app.commodity',
  'app.invoice'
])
.config(['$locationProvider', '$routeProvider', function($locationProvider, $routeProvider) {
  $locationProvider.hashPrefix('!');
  $routeProvider.otherwise({redirectTo: '/invoice'});

}]);
