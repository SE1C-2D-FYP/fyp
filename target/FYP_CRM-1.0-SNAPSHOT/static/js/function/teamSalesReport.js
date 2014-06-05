$(function() {
    var img_totalSalesAmount;
    var img_totalSalesUnit;
    var img_salesByGeographics;
    var json_topUnitSales;
    var json_agentPerformanceSales;
    var json_agentPerformanceUnit;
    $('#teamSalesReport').click(function() {
        $.ajax({
            url: "SalesReportController",
            data: {chartType: "teamSalesReport"
            },
            success: function(jsonData) {
                //Messenger
                Messenger().post({
                    message: 'Team Sales Report is loaded success.',
                    type: 'success',
                    hideAfter:'3',
                    showCloseButton: true
                });
                
                //dialog
                $('#dialog_salesReport_teamSalesReport').dialog({
                    "title": "Team Sales Report",
                    width: 'auto',
                    height: 600,
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
                                google.visualization.events.addListener(chart, 'ready', function() {
                                    img_totalSalesAmount = chart.getImageURI();
                                });
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
                                google.visualization.events.addListener(chart, 'ready', function() {
                                    img_totalSalesUnit = chart.getImageURI();
                                });
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
                                google.visualization.events.addListener(chart, 'ready', function() {
                                    img_salesByGeographics = chart.getImageURI();
                                });
                                chart.draw(data, options);
                            }, 'packages': ['corechart']});
                        
                        google.load('visualization', '1', {'callback': function() {
                                var data = new google.visualization.DataTable();
                                data.addColumn('string', 'Unit Name');
                                data.addColumn('number', 'Price');
                                json = $.parseJSON(jsonData.topUnitSales);
                                data.addRows(json);
                                json_topUnitSales = json;
                                var table = new google.visualization.Table(document.getElementById('salesReport_teamSalesReport_topUnitSales'));
                                table.draw(data, {width: 550, showRowNumber: true});
                            }, 'packages': ['table']});
                        
                        google.load('visualization', '1', {'callback': function() {
                                var data = new google.visualization.DataTable();
                                data.addColumn('string', 'Employee ID');
                                data.addColumn('string', 'Name');
                                data.addColumn('number', 'Sales($)');
                                json = $.parseJSON(jsonData.agentPerformanceSales);
                                data.addRows(json);
                                json_agentPerformanceSales = json;
                                var table = new google.visualization.Table(document.getElementById('salesReport_teamSalesReport_agentPerformanceSales'));
                                table.draw(data, {width: 550, showRowNumber: true});
                            }, 'packages': ['table']});
                        
                        google.load('visualization', '1', {'callback': function() {
                                var data = new google.visualization.DataTable();
                                data.addColumn('string', 'Employee ID');
                                data.addColumn('string', 'Name');
                                data.addColumn('number', 'Number of Units');
                                json = $.parseJSON(jsonData.agentPerformanceUnit);
                                data.addRows(json);
                                json_agentPerformanceUnit = json;
                                var table = new google.visualization.Table(document.getElementById('salesReport_teamSalesReport_agentPerformanceUnit'));
                                table.draw(data, {width: 550, showRowNumber: true});
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
    
    $('#export_teamSalesReport_pdf').click(function() {
        var doc = new jsPDF('p', 'pt', 'a4', true);
        var specialElementHandlers = {
            '#editor': function(element, renderer) {
                return true;
            }
        };
        doc.setFontSize(20);
        doc.text(200, 50, "Team Sales Report");
        doc.setFontSize(10);
        doc.text(450, 50, new Date().toDateString());
        doc.addImage(img_totalSalesAmount, "PNG", 100, 70);
        doc.addImage(img_totalSalesUnit, "PNG", 100, 270);
        doc.setFontSize(9);
        doc.text(170, 460, "Sales By Geographics");
        doc.addImage(img_salesByGeographics, "PNG", 100, 470);
        doc.text(170, 660, "Top Unit Sales");
        doc.table(160, 670, formatTopUnitSales(json_topUnitSales), ["Unit Name", "Price"], {
            autoSize: true,
            fontSize: 10
        });
        doc.addPage();
        doc.text(170, 50, "Agent Performance (Sales)");
        doc.table(160, 60, formatAgentPerformanceSales(json_agentPerformanceSales), ["Employee ID", "Name", "Sales($)"], {
            autoSize: true,
            fontSize: 10
        });
        doc.text(170, 250, "Agent Performance (Unit)");
        doc.table(160, 260, formatAgentPerformanceUnit(json_agentPerformanceSales), null, {
            autoSize: true,
            fontSize: 10
        });
        doc.save('team_sales_report.pdf');
        function formatTopUnitSales(json) {
            var jsonObj = [];
            $.each(json, function(i, row) {
                jsonObj.push({
                    "Unit Name": row[0],
                    Price: row[1]
                });
            });
            return jsonObj;
        }
        function formatAgentPerformanceSales(json) {
            var jsonObj = [];
            $.each(json, function(i, row) {
                jsonObj.push({
                    "Employee ID": row[0],
                    Name: row[1],
                    "Sales($)": row[2]
                });
            });
            return jsonObj;
        }
        function formatAgentPerformanceUnit(json) {
            var jsonObj = [];
            $.each(json, function(i, row) {
                jsonObj.push({
                    "Employee ID": row[0],
                    Name: row[1],
                    "Number of Units": row[2]
                });
            });
            return jsonObj;
        }
    });

});