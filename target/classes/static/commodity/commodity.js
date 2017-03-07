'use strict';

angular.module('app.commodity', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/commodity', {
    templateUrl: './commodity/commodities.html',
    controller: 'CommodityCtrl'
  });
}])

.controller('CommodityCtrl', ['$http', function($http) {
  var self = this;

  self.init = function() {
    $http.get("/api/commodities").success(function(response) {
      self.commodities = response._embedded.commodities;
    }).error(function(error) {
      console.log('error: ' + error);
    });
  };

  self.init();
}]);

