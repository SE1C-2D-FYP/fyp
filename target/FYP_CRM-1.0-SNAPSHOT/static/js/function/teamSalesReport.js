

$(function() {
    $('#teamSalesReport').click(function() {
        var session_empId = document.getElementById("session_empId").value;
        $.ajax({
            url: "salesReportController",
            data: {chartType: "teamSalesReport", empId: session_empId},
            success: function(jsonData) {
                //Messenger
                Messenger().post({
                    message: 'Personal Sales Report is loaded success.',
                    type: 'success',
                    hideAfter:'3',
                    showCloseButton: true
                });
                
                //dialog
                $('#dialog_salesReport_teamSalesReport').dialog({
                    "title": "Personal Sales Report",
                    width: 650,
                    height: 550,
                    "buttons": {
                        "cancel": function() {
                            $(this).dialog("close");
                        }
                    }
                }).dialogExtend({
                    "closable": true, // enable/disable close button
                    "maximizable": true, // enable/disable maximize button
                    "minimizable": true, // enable/disable minimize button
                    "collapsable": true, // enable/disable collapse button
                    "dblclick": "collapse", // set action on double click. false, 'maximize', 'minimize', 'collapse'
                    "titlebar": "transparent", // false, 'none', 'transparent'
                    "minimizeLocation": "left", // sets alignment of minimized dialogues
                    "icons": {// jQuery UI icon class
                        "close": "ui-icon-closethick",
                        "maximize": "ui-icon-extlink",
                        "minimize": "ui-icon-minus",
                        "collapse": "ui-icon-triangle-1-s",
                        "restore": "ui-icon-newwin"},
                    "load": function(evt, dlg) {
                        google.load('visualization', '1', {'callback': function() {
                                var data = new google.visualization.DataTable();
                                data.addColumn("string", "Year");
                                data.addColumn("number", "Amount($");
                                var json = $.parseJSON(jsonData.totalSalesAmount);
                                data.addRows(json);

                                var options = {
                                    title: 'Total Sales (Amount)',
                                    pointSize: 5,
                                    width: 550,
                                    chartArea: {width: '60%'}
                                };

                                var chart = new google.visualization.LineChart(document.getElementById('salesReport_teamSalesReport_totalSalesAmount'));
                                chart.draw(data, options);
                            }, 'packages': ['corechart']});
                        
                        google.load('visualization', '1', {'callback': function() {
                                var data = new google.visualization.DataTable(jsonData.totalSalesUnit);
                                data.addColumn("string", "Year");
                                data.addColumn("number", "Number of Unit");
                                json = $.parseJSON(jsonData.totalSalesUnit);
                                data.addRows(json);

                                var options = {
                                    title: 'Total Sales (Unit)',
                                    pointSize: 5,
                                    width: 550,
                                    chartArea: {width: '60%'}
                                };

                                var chart = new google.visualization.LineChart(document.getElementById('salesReport_teamSalesReport_totalSalesUnit'));
                                chart.draw(data, options);
                            }, 'packages': ['corechart']});
                                
                        
                        google.load('visualization', '1', {'callback': function() {
                                var data = new google.visualization.DataTable();
                                data.addColumn("string", "Region");
                                data.addColumn("number", "Sales($)");
                                json = $.parseJSON(jsonData.salesByGeog);
                                data.addRows(json);

                                var options = {
                                    region: 'HK',
                                    displayMode: 'markers',
                                    width: 450,
                                    colorAxis: {colors: ['#000000', '#4374e0']} // orange to blue
                                };

                                var chart = new google.visualization.GeoChart(document.getElementById('salesReport_teamSalesReport_salesByGeog'));
                                chart.draw(data, options);
                            }, 'packages': ['corechart']});
                        
                        google.load('visualization', '1', {'callback': function() {
                                var data = new google.visualization.DataTable();
                                data.addColumn('string', 'Unit Name');
                                data.addColumn('number', 'Price');
                                json = $.parseJSON(jsonData.topUnitSales);
                                data.addRows(json);

                                var table = new google.visualization.Table(document.getElementById('salesReport_teamSalesReport_topUnitSales'));
                                table.draw(data, {showRowNumber: true});
                            }, 'packages': ['table']});
                        
                        google.load('visualization', '1', {'callback': function() {
                                var data = new google.visualization.DataTable();
                                data.addColumn('string', 'Employee ID');
                                data.addColumn('string', 'Name');
                                data.addColumn('number', 'Sales($)');
                                json = $.parseJSON(jsonData.agentPerformanceSales);
                                data.addRows(json);
   
                                var table = new google.visualization.Table(document.getElementById('salesReport_teamSalesReport_agentPerformanceSales'));
                                table.draw(data, {showRowNumber: true});
                            }, 'packages': ['table']});
                        
                        google.load('visualization', '1', {'callback': function() {
                                var data = new google.visualization.DataTable();
                                data.addColumn('string', 'Employee ID');
                                data.addColumn('string', 'Name');
                                data.addColumn('number', 'Number of Units');
                                json = $.parseJSON(jsonData.agentPerformanceUnit);
                                data.addRows(json);

                                var table = new google.visualization.Table(document.getElementById('salesReport_teamSalesReport_agentPerformanceUnit'));
                                table.draw(data, {showRowNumber: true});
                            }, 'packages': ['table']});
                    }, // event
                    "beforeCollapse": function(evt, dlg) {
                        console.log(evt.type);
                    }, // event
                    "beforeMaximize": function(evt, dlg) {
                        console.log(evt.type);
                    }, // event
                    "beforeMinimize": function(evt, dlg) {
                        console.log(evt.type);
                    }, // event
                    "beforeRestore": function(evt, dlg) {
                        console.log(evt.type);
                    }, // event
                    "collapse": function(evt, dlg) {
                        console.log(evt.type);
                    }, // event
                    "maximize": function(evt, dlg) {
                        console.log(evt.type);
                    }, // event
                    "minimize": function(evt, dlg) {
                        console.log(evt.type);
                    }, // event
                    "restore": function(evt, dlg) {
                        console.log(evt.type);
                    } // event
                });
            },
            dataType: "json"
        });
    });

});