$(function() {
    var img_totalSalesAmount;
    var img_totalSalesUnit;
    var img_salesByGeographics;
    var json_topUnitSales;
    $('#personalSalesReport').click(function() {
        $.ajax({
            url: "SalesReportController",
            data: {
                chartType: "personalSalesReport"
            },
            success: function(jsonData) {

                //Messenger
                Messenger().post({
                    message: 'Personal Sales Report is loaded success.',
                    type: 'success',
                    hideAfter: '3',
                    showCloseButton: true
                });

                if ($("#dialog_salesReport_personalSalesReport").length > 0)
                    $("#dialog_salesReport_personalSalesReport").dialog("destroy").remove();
                $(document.body).append("<div id=\"dialog_salesReport_personalSalesReport\" style=\"display:none;\">" + "<div class=\"box box-success\" >\n" +
     
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

                //dialog
                var reportDialog = $('#dialog_salesReport_personalSalesReport').dialog({
                    "title": "Personal Sales Report",
                    width: 'auto',
                    height: 600,
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
                                data.addColumn("number", "Amount($)");
                                var json = $.parseJSON(jsonData.totalSalesAmount);
                                data.addRows(json);



                                var options = {
                                    title: 'Total Sales (Amount)',
                                    pointSize: 5,
                                    width: 550,
                                    chartArea: {width: '60%'}
                                };
                                chart_totalSalesAmount = new google.visualization.LineChart(document.getElementById('salesReport_personalSalesReport_totalSalesAmount'));
                                google.visualization.events.addListener(chart_totalSalesAmount, 'ready', function() {
                                    img_totalSalesAmount = chart_totalSalesAmount.getImageURI();
                                });
                                chart_totalSalesAmount.draw(data, options);
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

                                chart_totalSalesUnit = new google.visualization.LineChart(document.getElementById('salesReport_personalSalesReport_totalSalesUnit'));
                                google.visualization.events.addListener(chart_totalSalesUnit, 'ready', function() {
                                    img_totalSalesUnit = chart_totalSalesUnit.getImageURI();
                                });
                                chart_totalSalesUnit.draw(data, options);
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
                        console.log(jsonData);
                        google.load('visualization', '1', {'callback': function() {
                                var data = new google.visualization.DataTable();
                                data.addColumn("string", "Region");
                                data.addColumn("number", "Sales($)");
                                json = $.parseJSON(jsonData.salesByGeog);
                                data.addRows(json);
                                var options = {
                                    region: 'HK',
                                    displayMode: 'markers',
                                    width: 400,
                                    colorAxis: {colors: ['#000000', '#4374e0']} // orange to blue
                                };

                                chart_salesByGeographics = new google.visualization.GeoChart(document.getElementById('salesReport_personalSalesReport_salesByGeog'));
                                google.visualization.events.addListener(chart_salesByGeographics, 'ready', function() {
                                    img_salesByGeographics = chart_salesByGeographics.getImageURI();
                                });
                                chart_salesByGeographics.draw(data, options);
                            }, 'packages': ['corechart']});

                        google.load('visualization', '1', {'callback': function() {
                                var data = new google.visualization.DataTable();
                                data.addColumn('string', 'Unit Name');
                                data.addColumn('number', 'Price');
                                json = $.parseJSON(jsonData.topUnitSales);
                                data.addRows(json);
                                json_topUnitSales = json;
                                chart_topUnitSales = new google.visualization.Table(document.getElementById('salesReport_personalSalesReport_topUnitSales'));

                                chart_topUnitSales.draw(data, {showRowNumber: true, width: 400});
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
                if ($("#dialog_salesReport_personalSalesReport").dialog("isOpen"))
                    $("#dialog_salesReport_personalSalesReport").dialog("destroy");
                $('#dialog_salesReport_personalSalesReport').dialog('open');
                
            },
            error: function() {
                Messenger().post({
                    message: 'Cannot load Personal Sales Report.',
                    type: 'error',
                    hideAfter: '3',
                    showCloseButton: true
                });
            },
            dataType: "json"
        });
    });

    $(document.body).on('click', '#export_personalSalesReport_pdf', function() {
        var doc = new jsPDF('p', 'pt', 'a4', true);
        var specialElementHandlers = {
            '#editor': function(element, renderer) {
                return true;
            }
        };
        doc.setFontSize(20);
        doc.text(200, 50, "Personal Sales Report");
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
        doc.save('personal_sales_report.pdf');
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
    });
});