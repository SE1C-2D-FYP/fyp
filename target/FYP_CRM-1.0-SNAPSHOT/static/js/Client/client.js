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
                        + "</div></div></div>");
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
            type: "Get",
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
                htmlString = htmlString.concat("</div></div>");
                $('#clientDetail').append(htmlString);
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

    $('#editAClient').click(function() {
        openDialog($('#editClientForm'), "Edit");
    });

    $('#insertNewClient').click(function() {
        openDialog($('#insertClientForm'), "New Client");
    });

    $('#insertNewContact').click(function() {
        openDialog($('#insertContactForm'), "New Contact");
    });

});

