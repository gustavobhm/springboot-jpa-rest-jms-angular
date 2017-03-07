'use strict';

angular.module('app.invoice', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/invoice', {
    templateUrl: './invoice/invoices.html',
    controller: 'InvoiceCtrl'
  });
}])
.controller('InvoiceCtrl', ['$http', function($http) {
  var self = this;

  self.detail = function(i) {
    self.i = i;
    $http.get(i._links.commodities.href).success(function(response) {
      self.commodities = response._embedded.commodities;
    }).error(function(error) {
      console.log('error: ' + error);
    });
  };

  self.init = function() {
    $http.get('/api/invoices').success(function(response) {
      self.invoices = response._embedded.invoices;
    }).error(function(error) {
      console.log('error: ' + error);
    });
  };

  self.init();
}]);

