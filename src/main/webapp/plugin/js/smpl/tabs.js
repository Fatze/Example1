/**
 * Created by Dostanko_VL on 22.10.2015.
 */

var Sample1 = new Object();
Sample1.pluginName = 'Sample1';
Sample1.log = Logger.get(Sample1.pluginName);

Sample1.contextPath = Context.plugin_context;
Sample1.templatePath = Sample1.contextPath + '/plugin/html/';
Sample1.mainPage = Sample1.templatePath + 'simpl1.html';
Sample1.templateDocPath = Sample1.contextPath + '/plugin/doc/';

Sample1.jmxDomain = "hawtio:type=plugin,name="; //hawtio:type=plugin,name=Connections
Sample1.mbeanType = "Connections";
Sample1.attribute = "PublishData";
Sample1.mbean = Sample1.jmxDomain/* + ":type="*/ + Sample1.mbeanType;

Sample1.RegisterInHawtIO = function (workspace, viewRegistry, layoutFull) {
    //Sample1.RegisterInHawtIO = function (workspace, viewRegistry, helpRegistry) {
        viewRegistry["Sample1"] =  layoutFull;


        workspace.topLevelTabs.push({
            id: "Sample1",
            content: "Sample1 content",
            title: "Sample1 title",
            isValid: function () {
                return true;
            },
            href: function () {
                return "#/Sample1";
            },
            isActive: function (workspace) {
                return workspace.isTopTabActive("Sample1");
            }
        });
    };

Sample1.SimpleController1 = function ($scope, jolokia) {

    $scope.userParam = "";
    $scope.hello = Sample1.templatePath;
    $scope.gridOptions = { data: 'myData' };
    //Sample1.GetInfo4Server($scope, jolokia);
    $scope.RequestInformation = function () {
        if (Core.isBlank($scope.userParam)) {
            return;
        }
        Sample1.log.debug("User searched : " + $scope.userParam);

        jolokia.request({
            type: 'exec',
            mbean: Sample1.mbean,
            operation: 'ConnectionsInfo(java.lang.String)',
            arguments: [$scope.userParam]
            //arguments: []
        }, {
            method: 'POST',
            success: function (response) {
                $scope.isReply = true;

                $scope.myData = JSON.parse(response.value);

                $scope.cpuLoad = JSON.parse(response.value);;
                Core.$apply($scope);
            },
            error: function (response) {
                $scope.isReply = false;
                Sample1.log.warn("Failed to search for Connections: ", response.error);
                Sample1.log.info("Stack trace: ", response.stacktrace);
                Core.$apply($scope);
            }

        });
    };
    $scope.hover = function (isReply) {
        // Shows/hides the delete button on hover
        return $scope.isReply == false;
    };
};

Sample1.GetInfo4Server = function ($scope, jolokia){

    Core.register(jolokia, $scope, {
            type: 'exec',
            mbean: Sample1.mbean,
            operation: 'ConnectionsInfo()',
            arguments: []
    }, onSuccess(render));

    // update display of metric
    function render (response) {
        $scope.myData = JSON.parse(response.value);
        //$scope.myData = response.value.map(function (val) {
        //    return {tweet: val};
        //});
        $scope.cpuLoad = JSON.parse(response.value);;
        Core.$apply($scope);
    }
};

Sample1.module = angular.module(
    Sample1.pluginName,
    ['ui', 'bootstrap', 'ui.bootstrap', 'ui.bootstrap.modal', 'ngResource', 'ngGrid', 'hawtioCore', 'hawtio-ui', 'hawtio-forms'])
    .config(function ($routeProvider) {
        $routeProvider.when('/Sample1', {templateUrl: Sample1.mainPage});
    });


Sample1.module.run(Sample1.RegisterInHawtIO);
hawtioPluginLoader.addModule(Sample1.pluginName);