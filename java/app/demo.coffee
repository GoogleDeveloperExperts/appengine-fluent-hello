angular.module 'demo', []

.controller 'IndexController', class
  constructor: ($http) ->
    $http.get('/greeting').success (data) =>
      @greeting = data