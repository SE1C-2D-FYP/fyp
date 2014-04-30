

$(function() {
    $('#teamMemberSalesReport').click(function() {
        var session_empId = document.getElementById("session_empId").value;
        $.ajax({
            url: "salesReportController",
            data: {chartType: "teamMemberList", empId: session_empId},
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
                                teamMemberjson = $.parseJSON(teamMemberData);
                                data.addRows(teamMemberjson);
                                
                                var table = new google.visualization.Table(document.getElementById('salesReport_teamMemberSalesReport_memberList'));
                                function selectHandler() {
                                    var selectedItem = table.getSelection()[0];
                                    if (selectedItem) {
                                        var value = data.getValue(selectedItem.row, 0);
                                        $.ajax({
                                            url: "salesReportController",
                                            data: {chartType: "personalSalesReport", empId: value},
                                            success: function(data) {
                                                //Messenger
                                                Messenger().post({
                                                    message: 'Team Member Sales Report is loaded success.',
                                                    type: 'success',
                                                    hideAfter: '3',
                                                    showCloseButton: true
                                                });

                                                //dialog
                                                $('#dialog_salesReport_personalSalesReport').dialog({
                                                    "title": "Team Member Sales Report",
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
                                                                var data = google.visualization.arrayToDataTable([
                                                                    ['Year', 'Total Sales', 'Company Client', 'Individual Client'],
                                                                    ['2004', 1000, 400, 600],
                                                                    ['2005', 1170, 460, 710],
                                                                    ['2006', 660, 300, 330],
                                                                    ['2007', 1030, 540, 480]
                                                                ]);

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
                                                                var data = google.visualization.arrayToDataTable([
                                                                    ['Year', 'Total Sales', 'Company Client', 'Individual Client'],
                                                                    ['2004', 45, 33, 12],
                                                                    ['2005', 23, 23, 0],
                                                                    ['2006', 12, 3, 9],
                                                                    ['2007', 66, 44, 22]
                                                                ]);

                                                                var options = {
                                                                    title: 'Total Sales (Unit)',
                                                                    pointSize: 5,
                                                                    width: 550,
                                                                    chartArea: {width: '60%'}
                                                                };

                                                                var chart = new google.visualization.LineChart(document.getElementById('salesReport_personalSalesReport_totalSalesUnit'));
                                                                chart.draw(data, options);
                                                            }, 'packages': ['corechart']});

                                                        google.load('visualization', '1', {'callback': function() {
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
                                                            }, 'packages': ['corechart']});

                                                        google.load('visualization', '1', {'callback': function() {
                                                                var data = google.visualization.arrayToDataTable([
                                                                    ['Region', 'Sales'],
                                                                    ['New Territories', 2761477],
                                                                    ['Kowloon', 1324110],
                                                                    ['Hong Kong Island', 959574]
                                                                ]);

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
                                                                data.addRows([
                                                                    ['Mike', {v: 100000, f: '$100,000'}],
                                                                    ['Jim', {v: 80000, f: '$180,000'}],
                                                                    ['Alice', {v: 125000, f: '$102,500'}],
                                                                    ['Bob', {v: 70000, f: '$700,000'}]
                                                                ]);

                                                                var table = new google.visualization.Table(document.getElementById('salesReport_personalSalesReport_topUnitSales'));
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