$('#showClientList').click(function() {
    clientDataTable();
    openDialog($('#list'), "List");
});



$(function() {
    function openDialog(id, title) {
        id.dialog({
            "title": title,
            "width": 'auto',
            "buttons": {
                "OK": function() {
                    $(this).dialog("close");
                },
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
                console.log(evt.type);
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
    }

    function clientDataTable() {
        var oTable = $('#listClient').DataTable({
            "sAjaxSource": "./ShowClientList",
            "sPaginationType": "full_numbers",
            "bAutoWidth": true,
            "bDestroy": true,
            "aoColumns": [
                {"mData": "clientId"},
                {"mData": "clientName"},
                {"mData": "address"},
                {"mData": "phoneNo"}
            ],
            "aoCloumnDefs": [
                {"bVisible": false, "aTargets": [0]}
            ]
        });
    }

    function insertAClient() {
        $.ajax({
            type: "Post",
            url: './InsertClient',
            data: $(this).serialize(),
            success: function(data) {
                alert(data);
                $('#insertClient')[0].reset();
            }
        });
    }

    function showClientDetail(clientId) {
        $.ajax({
            type: "Get",
            url: './ShowClientDetail',
            data: {
                id: clientId
            },
            dataType: "json",
            success: function(data) {
                targetClientId = data.clientId;
                $('#clientDetail').text("");
                $('#client_tab_control').text("");
                $('#client_tab_control').append("<ul class=\"nav nav-tabs\">" +
                        "<li class=\"active\"><a href=\"#clientDetail_panel\" data-toggle=\"tab\">Detail</a></li>" +
                        "<li><a href=\"#clientContactInfo_panel\" data-toggle=\"tab\">Contact Info</a></li>" +
                        "<li><a href=\"#clientTransactionInfo_panel\" data-toggle=\"tab\">Transaction Info</a></li>" +
                        "<li><a href=\"#clientContactRecord_panel\" data-toggle=\"tab\">Contact Record</a></li>" +
                        "</ul>");
                $('#clientDetail').append("<div class=\"tab-pane active\" id=\"clientDetail_panel\"><div class=\"box box-success\"><div class=\"box-header\">"
                        + "<h3 class=\"box-title\">Client Detail</h3></div>"
                        + "<div class=\"box-body\"><label>Client Name: </label><label>"
                        + data.clientName + "</label><br />"
                        + "<label>Address: </label><label>"
                        + data.address + "</label><br />"
                        + "<label>Phone No.: </label><label>"
                        + data.phoneNo + "</label><br />"
                        + "<label>Mobile Phone No.: </label><label>"
                        + data.mphoneNo + "</label><br />"
                        + "<label>Fax No.: </label><label>"
                        + data.faxNo + "</label><br />"
                        + "<label>E-mail Address: </label><label>"
                        + data.email + "</label><br />"
                        + "</div></div>        <button id=\"editAClient\" class=\"btn btn-primary\">Edit</button></div>");
                $('[name=clientId]').val(data.clientId);
                $('[name=empId]').val(data.empId);
                $('[name=editClientName]').val(data.clientName);
                $('[name=editAddress]').val(data.address);
                $('[name=editPhone]').val(data.phoneNo);
                $('[name=editMphone]').val(data.mphoneNo);
                $('[name=editFax]').val(data.faxNo);
                $('[name=editEmail]').val(data.email);
                $('[name=ctClientId]').val(data.clientId);
                showContactInfo(data.clientId);
            }
        });
    }

    function showContactInfo(clientId) {
        $.ajax({
            type: "POST",
            url: "./ShowContactInfo",
            data: {
                id: clientId
            },
            dataType: "json",
            success: function(data) {
                htmlString = "<div class=\"tab-pane\" id=\"clientContactInfo_panel\"><div class=\"box box-warning\"><div class=\"box-header\">"
                        + "<h3 class=\"box-title\">Contact Information</h3></div>";
                $.each(data, function(i, element) {
                    htmlString = htmlString.concat("<div class=\"box-body\"><label>Name: </label><label>" + element['engName'] + "</label><br />");
                    htmlString = htmlString.concat("<label>Phone Type: </label><label>" + element['phoneType'] + "</label><br />");
                    htmlString = htmlString.concat("<label>Phone No.: </label><label>" + element['phoneNum'] + "</label><br /></div>");
                });
                htmlString = htmlString.concat("</div><button id=\"insertNewContact\" class=\"btn btn-primary\">Create Contact</button></div>");
                $('#clientDetail').append(htmlString);
                showTransactionInfo(clientId);
            }
        });
    }

    function showDetail() {
        $('#listClient tbody').on('click', 'tr', function() {
            var id = $('td', this).eq(0).text();
            showClientDetail(id);
            openDialog($('#detail'), "Client Detail");
        });
    }
    
    function showTransactionInfo(clientId) {
        $("#clientDetail").append("<div class=\"tab-pane\" id=\"clientTransactionInfo_panel\">" +
                "<table cellpadding=\"9\" cellspacing=\"9\" border=\"0\" id=\"clientTransactionInfo_table\">" +
                "<thead><tr>" +
                "<th style=\"text-align: center;\">Date</th>" +
                "<th style=\"text-align: center;\">Address</th>" +
                "<th style=\"text-align: center;\">Area</th>" +
                "<th style=\"text-align: center;\">Type</th>" +
                "<th style=\"text-align: center;\">Price</th>" +
                "<th style=\"text-align: center;\">Commission</th>" +  
                "<th style=\"text-align: center;\">Agreement</th>" + 
                "</tr></thead><tbody></tbody></table>" +
                "<div class=\"connectedSortable\">" +
//                "<button id=\"insertNewClient\" class=\"btn btn-primary\">Create New Client</button>" +
                "</div></div>");
        
        var transactionInfoTable = $('#clientTransactionInfo_table').DataTable({
            "fnServerParams": function(aoData) {
                aoData.push({
                    'name': 'clientId', 'value': clientId
                });
            },
            "sAjaxSource": "./ShowTransactionInfo",
            "sPaginationType": "full_numbers",
            "bAutoWidth": true,
            "bDestroy": true,
                "columns": [
                { "data": "0" },
                { "data": "1" },
                { "data": "2" },
                { "data": "3" },
                { "data": "4" },
                { "data": "5" },
                { "data": "6" }
                ],
            "aoCloumnDefs": [
                {"bVisible": false, "aTargets": [0]}
            ]
        });
        showContactRecord(clientId);
    }
    
    function showContactRecord(clientId) {
        $("#clientDetail").append("<div class=\"tab-pane\" id=\"clientContactRecord_panel\">" +
                "<table cellpadding=\"9\" cellspacing=\"9\" border=\"0\" id=\"clientContactRecord_table\">" +
                "<thead><tr>" +
                "<th style=\"text-align: center;\">Date</th>" +
                "<th style=\"text-align: center;\">Time</th>" +
                "<th style=\"text-align: center;\">Contact Person</th>" +
                "<th style=\"text-align: center;\">Phone Number</th>" +
                "<th style=\"text-align: center;\">Stock ID</th>" +
                "<th style=\"text-align: center;\">Type</th>" +
                "<th style=\"text-align: center;\">Price per feet</th>" +
                "<th style=\"text-align: center;\">Memo</th>" +
                "</tr></thead><tbody></tbody></table>" +
                "<div class=\"connectedSortable\">" +
//                "<button id=\"insertNewClient\" class=\"btn btn-primary\">Create New Client</button>" +
                "</div></div>");

        var contactRecordTable = $('#clientContactRecord_table').DataTable({
            "fnServerParams": function(aoData) {
                aoData.push({
                    'name': 'clientId', 'value': clientId
                });
            },
            "sAjaxSource": "./ShowContactRecord",
            "sPaginationType": "full_numbers",
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
                {"data": "7"}
            ],
            "aoCloumnDefs": [
                {"bVisible": false, "aTargets": [0]}
            ]
        });
    }

    $('#insertClient').on('submit', function(e) {
        e.preventDefault();
        $.ajax({
            type: "Post",
            url: './InsertClient',
            data: $(this).serialize(),
            success: function(data) {
                alert(data);
                $('#insertClient')[0].reset();
                $('#insertClientForm').dialog("close");
                clientDataTable();
                openDialog($('#list'), "List");
            }
        });
    });

    $('#editClient').on('submit', function(e) {
        e.preventDefault();
        $.ajax({
            type: "Post",
            url: './EditClientDetail',
            data: $(this).serialize(),
            dataType: "json",
            success: function(data) {
                alert(data.clientName + " has been updated.");
                $('#editClient')[0].reset();
                $('#editClientForm').dialog("close");
                clientDataTable();
                showClientDetail(data.clientId);
            }
        });
    });

    $('#insertContact').on('submit', function(e) {
        e.preventDefault();
        $.ajax({
            type: "Post",
            url: './InsertContactInfo',
            data: $(this).serialize(),
            dataType: "json",
            success: function(data) {
                alert("New contact [" + data.engName + "] has been created.");
                $('#insertContact')[0].reset();
                $('#insertContactForm').dialog("close");
                clientDataTable();
                showClientDetail(data.clientId);
            }
        });
    });

    $('#showClientList').click(function() {
        clientDataTable();
        openDialog($('#list'), "List");
        showDetail();
    });

    $('#clientDetail').on('click', '#editAClient', function() {
        openDialog($('#editClientForm'), "Edit");
    });

    $('#insertNewClient').click(function() {
        openDialog($('#insertClientForm'), "New Client");
    });

    $('#clientDetail').on('click', '#insertNewContact', function() {
        openDialog($('#insertContactForm'), "New Contact");
    });

});

