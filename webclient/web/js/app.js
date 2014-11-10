/**
 * Created by JanTore on 16.10.2014.
 */
angular.module('app', ['ngSanitize'])
    .factory('Backend', ['$http', function($http) {
        function getNews(query) {
            var request = $http({
                method: 'POST',
                url: 'http://localhost:9200/messages/message/_search?size=50',
                data: query
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
            $scope.interests = [];



//            function createQueryObject() {
//                var interestTuples = [];
//                var shouldArray = [];
//                var interestsString = "";
//
//                if($scope.interests.length === 0) {
//                    return {
//                        "query": {
//                            "match_all": {}
//                        },
//                        "sort": {
//                            "pubDate": {
//                                "order": "desc"
//                            }
//                        }
//                    }
//                }
//                for(var i = 0; i<$scope.interests.length; i++) {
//                    var interests = $scope.interests[i].interest;
//                    for(var j = 0; j<interests.length; j++) {
//                        var notFound = true;
//                        var interest = interests[j];
//                        for (var k = 0; k<interestTuples.length; k++) {
//                            if(interestTuples[k].interest === interest) {
//                                interestTuples[k].count++;
//                                notFound = false;
//                            }
//                        }
//                        if (interestTuples.length === 0 || notFound){
//                            var interestTuple = {'interest':interest, 'count':1};
//                            interestTuples.push(interestTuple);
//                        }
//                    }
//                }
//                for(var l = 0; l<interestTuples.length; l++) {
//                    interestsString += (interestTuples[l].interest + ' ');
//
//                    if(interestTuples[l].count > 1) {
//                        var should = {
//                            "multi_match": {
//                                "query": interestTuples[l].interest,
//                                "fields": [ "title" ],
//                                "boost": interestTuples[l].count
//                            }
//                        };
//                        shouldArray.push(should);
//                    }
//                }
//
//                var query = {
//                    "query": {
//                        "bool": {
//                            "must": [
//                                {
//                                    "multi_match": {
//                                        "query":    interestsString,
//                                        "fields": [ "title" ]
//                                    }
//                                }],
//                            "should": shouldArray
//                        }
//                    }
//                };
//                return query;
//            }



            document.addEventListener("feedRecorderInterestsEvent", function(ev) {
                $scope.query = ev.detail;

//                var query = createQueryObject();
//                console.log(query);
                if ($scope.query === undefined) {
                    $scope.query = {
                        "query": {
                            "match_all": {}
                        },
                        "sort": {
                            "pubDate": {
                                "order": "desc"
                            }
                        }
                    }
                }


                var newsPromise = Backend.getNews($scope.query);
                newsPromise.then(function(response){
                    $scope.news = response.data.hits.hits;
                    $scope.responseInfo = {};
                    $scope.responseInfo.time = response.data.took;
                    $scope.responseInfo.hitsTotal = response.data.hits.total;
                    $scope.responseInfo.hitsShowing = response.data.hits.hits.length;
                });
            });

            $scope.createTrustedHtml = function(html) {
                return $sce.trustAsHtml(html);
            };

            $scope.getSource = function(url) {
                var rem = url.replace(/.*?:\/\//g, "");
                var splits = rem.split('/');
                return splits[0].replace('www.', '');
            }

            $scope.parseDate = function(date) {
                return date.replace("T", " ").replace("Z", "");

            }
    }]);