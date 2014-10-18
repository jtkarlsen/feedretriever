/**
 * Created by JanTore on 16.10.2014.
 */
angular.module('app', ['ngSanitize'])
    .factory('Backend', ['$http', function($http) {
        function getNews() {
            var request = $http({
                method: 'GET',
                url: 'http://localhost:9200/messages/message/_search?size=100&sort=pubDate:desc'
            }).then(function(response) {
                return response;
            });
            return request;
        }
        return({
            getNews: getNews
        });
    }])
    .controller('AppCtrl', ['$scope', '$sce', 'Backend',
        function($scope, $sce, Backend) {
            $scope.news = [];

            var newsPromise = Backend.getNews();
            newsPromise.then(function(response){
                $scope.news = response.data.hits.hits;
            });

            $scope.createTrustedHtml = function(html) {
                return $sce.trustAsHtml(html);
            };

            $scope.getSource = function(url) {
                var rem = url.replace(/.*?:\/\//g, "");
                var splits = rem.split('/');
                return splits[0].replace('www.', '');
            }
    }]);