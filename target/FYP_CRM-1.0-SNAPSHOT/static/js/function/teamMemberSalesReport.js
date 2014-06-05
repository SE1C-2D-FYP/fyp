$(function() {
    $('#teamMemberSalesReport').click(function() {
        $.ajax({
            url: "SalesReportController",
            data: {chartType: "teamMemberList"
            },
            success: function(teamMemberData) {
                //Messenger
                Messenger().post({
                    message: 'Team Member Sales Report is loaded success.',
                    type: 'success',
                    hideAfter:'3',
                    showCloseButton: true
                });
                
                //dialog
                $('#dialog_salesReport_teamMemberSalesReport').dialog({
                    "title": "Team Member Sales Report",
                    width: 950,
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
                                data.addColumn('string', 'Employee ID');
                                data.addColumn('string', 'Surname');
                                data.addColumn('string', 'Other Name');
                                data.addRows(teamMemberData);
                                
                                var table = new google.visualization.Table(document.getElementById('salesReport_teamMemberSalesReport_memberList'));
                                function selectHandler() {
                                    var selectedItem = table.getSelection();
                                    if (selectedItem) {
                                        var value = data.getValue(selectedItem[0].row, 0);
                                        
                                     
                                        $.ajax({
                                            url: "SalesReportController",
                                            data: {chartType: "personalSalesReport", empId: value},
                                            success: function(teamMemberReportData) {
                                                //Messenger
                                                Messenger().post({
                                                    message: 'Team Member Sales Report is loaded success.',
                                                    type: 'success',
                                                    hideAfter: '3',
                                                    showCloseButton: true
                                                });

                                                //dialog
                                                if ($("#dialog_salesReport_personalSalesReport").length > 0)
                                                    $("#dialog_salesReport_personalSalesReport").dialog("destroy").remove();
                                                $(document.body).append("<div id=\"dialog_salesReport_personalSalesReport\" style=\"display:none;\">" + "<div class=\"box box-success\" >\n" + 
                                                        "<div class=\"box-header\">" +
                                                        "<h3 class=\"box-title\">" + data.getValue(selectedItem[0].row, 1) + " " + data.getValue(selectedItem[0].row, 2) + "</h3>" +
                                                        "</div>" + 
                                                        "<div class=\"box-body chart-responsive\">" +
                                                        "<div>" +
                                                        "<ul class=\"nav nav-tabs\" id=\"personalSalesReport_tab\">" +
                                                        "<li class=\"active\"><a href=\"#personalSalesReport_topSales_panel\" data-toggle=\"tab\">Total Sales</a></li>" +
                                                        "<li><a href=\"#personalSalesReport_geo_panel\" data-toggle=\"tab\">Geographical</a></li>" +
                                                        "<li><a href=\"#personalSalesReport_topUnitSales_panel\" data-toggle=\"tab\">Top Unit Sales</a></li>" +
                                                        "<div class=\"pull-right box-tools\">" +
                                                        "<div class=\"btn-group\">" +
                                                        "<button class=\"btn btn-warning btn-sm dropdown-toggle\" data-toggle=\"dropdown\"><i class=\"fa fa-bars\"></i></button>" +
                                                        "<ul class=\"dropdown-menu pull-right\" role=\"menu\">" +
                                                        "<li><a href=\"#\" id=\"export_personalSalesReport_pdf\">Export to PDF</a></li>" +
                                                        "</ul>" +
                                                        "</div>" +
                                                        "</div>" +
                                                        "</ul>" +
                                                        "</div>" +
                                                        "<div class=\"tab-content\">" +
                                                        "<div class=\"tab-pane active\" id=\"personalSalesReport_topSales_panel\">" +
                                                        "<div class = \"chart\" id = \"salesReport_personalSalesReport_totalSalesAmount\" style = \"height: 250px;\" > </div>" +
                                                        "<div class = \"chart\" id = \"salesReport_personalSalesReport_totalSalesUnit\" style = \"height: 250px\" > </div>" +
                                                        "</div>" +
                                                        "<div class=\"tab-pane\" id=\"personalSalesReport_geo_panel\">" +
                                                        "<div class = \"chart\" id = \"salesReport_personalSalesReport_salesByGeog\" style = \"height: 400px\"></div>" +
                                                        "</div>" +
                                                        "<div class=\"tab-pane\" id=\"personalSalesReport_topUnitSales_panel\">" +
                                                        "<div class = \"chart\" id = \"salesReport_personalSalesReport_topUnitSales\" style = \"height: 250px\" > </div>" +
                                                        "</div>" +
                                                        "</div>" +
                                                        "</div><!-- /.box - body -- >" +
                                                        "</div><!-- /.box -- >" +
                                                        "</div>" +
                                                        "</div>");
                                                
                                                var reportDialog = $('#dialog_salesReport_personalSalesReport').dialog({
                                                    "title": "Team Member Sales Report",
                                                    width: 650,
                                                    height: 550,
                                                    autoOpen: false,
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
                                                                var json = $.parseJSON(teamMemberReportData.totalSalesAmount);
                                                                data.addRows(json);



                                                                var options = {
                                                                    title: 'Total Sales (Amount)',
                                                                    pointSize: 5,
                                                                    width: 550,
                                                                    chartArea: {width: '60%'}
                                                                };

                                                                var chart = new google.visualization.LineChart(document.getElementById('salesReport_personalSalesReport_totalSalesAmount'));
                                                                chart.draw(data, options);
                                                            }, 'packages': ['corechart']});

                                                        google.load('visualization', '1', {'callback': function() {
                                                                var data = new google.visualization.DataTable(teamMemberReportData.totalSalesUnit);
                                                                data.addColumn("string", "Year");
                                                                data.addColumn("number", "Number of Unit");
                                                                json = $.parseJSON(teamMemberReportData.totalSalesUnit);
                                                                data.addRows(json);
                                                                var options = {
                                                                    title: 'Total Sales (Unit)',
                                                                    pointSize: 5,
                                                                    width: 550,
                                                                    chartArea: {width: '60%'}
                                                                };

                                                                var chart = new google.visualization.LineChart(document.getElementById('salesReport_personalSalesReport_totalSalesUnit'));
                                                                chart.draw(data, options);
                                                            }, 'packages': ['corechart']});

                                                        /* google.load('visualization', '1', {'callback': function() {
                                                         var data = google.visualization.arrayToDataTable([
                                                         ['Year', 'Male', 'Female', {role: 'annotation'}],
                                                         ['2010', 10, 24, ''],
                                                         ['2011', 16, 22, ''],
                                                         ['2012', 28, 19, '']
                                                         ]);
                                                         
                                                         var options = {
                                                         width: 550,
                                                         title: 'Sales by Age and Gender',
                                                         bar: {groupWidth: '75%'},
                                                         isStacked: true
                                                         };
                                                         var chart = new google.visualization.BarChart(document.getElementById('salesReport_personalSalesReport_salesByGenderAge'));
                                                         chart.draw(data, options);
                                                         }, 'packages': ['corechart']}); */
                                                        
                                                        google.load('visualization', '1', {'callback': function() {
                                                                var data = new google.visualization.DataTable();
                                                                data.addColumn("string", "Region");
                                                                data.addColumn("number", "Sales($)");
                                                                json = $.parseJSON(teamMemberReportData.salesByGeog);
                                                                data.addRows(json);
                                                                var options = {
                                                                    region: 'HK',
                                                                    displayMode: 'markers',
                                                                    width: 450,
                                                                    colorAxis: {colors: ['#000000', '#4374e0']} // orange to blue
                                                                };

                                                                var chart = new google.visualization.GeoChart(document.getElementById('salesReport_personalSalesReport_salesByGeog'));
                                                                chart.draw(data, options);
                                                            }, 'packages': ['corechart']});

                                                        google.load('visualization', '1', {'callback': function() {
                                                                var data = new google.visualization.DataTable();
                                                                data.addColumn('string', 'Unit Name');
                                                                data.addColumn('number', 'Price');
                                                                json = $.parseJSON(teamMemberReportData.topUnitSales);
                                                                data.addRows(json);

                                                                var table = new google.visualization.Table(document.getElementById('salesReport_personalSalesReport_topUnitSales'));
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
                                                    }
                                                });
                                                $('#dialog_salesReport_personalSalesReport').dialog('open');
                                            },
                                            dataType: "json"
                                        });
                                    }
                                }
                                google.visualization.events.addListener(table, 'select', selectHandler);
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