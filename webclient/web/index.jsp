<%--
  Created by IntelliJ IDEA.
  User: JanTore
  Date: 16.10.2014
  Time: 10:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html >
  <head>
      <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.0/css/bootstrap-combined.min.css">
      <link rel="stylesheet" href="/css/app.css">
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
      <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.0/angular.js"></script>
      <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.0/angular-resource.min.js"></script>
      <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.0/angular-sanitize.js"></script>
      <script src="js/app.js"></script>
      <title>My news feed</title>
  </head>
  <body ng-app="app" ng-controller="AppCtrl">
        <div class="container">
            <div class="feed-container" ng-repeat="headline in news">
                <h1><a class="feed-headline" role="button" target="_blank" href="{{headline._source.link}}">{{createTrustedHtml(headline._source.title)}}</a></h1>
                <div ng-bind-html="createTrustedHtml(headline._source.description)"></div>
                <p class="text-right">{{getSource(headline._source.link)}}</p>
            </div>
        </div>
  </body>
</html>
