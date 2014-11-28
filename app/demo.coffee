angular.module 'demo', []

.service 'Messages', ($http) ->
  list: ->
    $http.get '/message/list', {cached: false}
  create: (message) ->
    $http.post '/message', message

.controller 'IndexController', class
  constructor: (@Messages) ->
    @refresh()

  refresh: ->
    @Messages.list().success (data) =>
      @messages = data

  create: (message) ->
    @Messages.create(message).success =>
      @refresh()
