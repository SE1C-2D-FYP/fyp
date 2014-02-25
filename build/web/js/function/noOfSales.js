/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(function() {
    $('#noOfSales').click(function() {
        //BAR CHART

        $.ajax({
            url: "salesReportController",
            data: {chartType: "noOfSales"},
            success: function(data) {            
                $('#dialog_salesReport_noOfSales').dialog({
                    "title": "Number of Sales",
                    "width": "450px",
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
                        var bar = new Morris.Bar(data);
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