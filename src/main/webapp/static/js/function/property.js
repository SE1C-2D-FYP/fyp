$(function() {
    $('#showPropertyList').click(function() {
        openDialog($('#property_dialog'), "Property List");
        propertyDataTable();
    });
    
    $('#listProperty tbody').on('click', 'tr', function() {
        var id = $('td', this).eq(0).text();
        console.log(id);
        showPropertyDetail(id);
        openDialog($('#property_detail_dialog'), "Property Information");
    });
    
    function openDialog(id, title) {
        id.dialog({
            "title": title,
            "width": 'auto',
            "buttons": {
                "OK": function() {
                    $(this).dialog("destroy");
                },
                "cancel": function() {
                    $(this).dialog("destroy");
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
            }, // event
            "beforeCollapse": function(evt, dlg) {
            }, // event
            "beforeMaximize": function(evt, dlg) {
            }, // event
            "beforeMinimize": function(evt, dlg) {
            }, // event
            "beforeRestore": function(evt, dlg) {
            }, // event
            "collapse": function(evt, dlg) {
            }, // event
            "maximize": function(evt, dlg) {
            }, // event
            "minimize": function(evt, dlg) {
            }, // event
            "restore": function(evt, dlg) {
            } // event
        });
    }
    
    function propertyDataTable() {
        propertyListTable = $('#listProperty').DataTable({
            "fnServerParams": function(aoData) {
            },
            "sAjaxSource": "./PropertyController/PropertyList",
            "bPaginate": true,
            "bAutoWidth": true,
            "bDestroy": true,
            "columns": [
                {"data": "0"},
                {"data": "1"},
                {"data": "2"},
                {"data": "3"},
                {"data": "4"},
                {"data": "5"},
                {"data": "6"},
                {"data": "7"},
                {"data": "8"}
            ]
        });
    }
    
    function showPropertyDetail(id) {
        $('#property_detail_div').load('static/public/propertyDetail.html');
        $.ajax({
            type: "GET",
            url: './PropertyController/Property/' + id,
            dataType: 'json',
            success: function(data) {
                console.log(data);
                $.each(data[0], function(i, element) {
                    if (element == null)
                        data[0][i] = '-----';
                });
                $('#property_detail_table_address').text(data[0][0] + ', ' + data[0][1] + ', ' + data[0][2] + ', ' + data[0][3] + ', ' + data[0][4] + '/F, FLAT ' + data[0][5]);
                $('#property_detail_table_sittingRoom').text(data[0][6]);
                $('#property_detail_table_room').text(data[0][7]);
                $('#property_detail_table_bathroom').text(data[0][8]);
                $('#property_detail_table_balcony').text(data[0][9]);
                $('#property_detail_table_grossArea').text(data[0][10]);
                $('#property_detail_table_netArea').text(data[0][11]);
                $('#property_detail_table_view').text(data[0][12]);
                $('#property_detail_table_orientation').text(data[0][13]);
                $.getScript('static/public/panorama/example/tour.js', function() {});
            }
        });
    }
});


