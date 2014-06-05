//$('#showClientList').click(function() {
//    clientDataTable();
//    openDialog($('#list'), "Client List");
//});

$(function() {
    $(document).ready(function() {
        clientDataTable();
        showDetail();
        openDialog($('#list'), "Client List");
        $('#list').dialogExtend("maximize");
    });
    var selectedClientId = "";
    var transactionInfoTable;
    var contactRecordTable;
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

    function clientDataTable() {
        var oTable = $('#listClient').DataTable({
            "sAjaxSource": "./ShowClientList",
            "bPaginate": true,
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
                selectedClientId = data.clientId;
                $('#clientDetail').text("");
                $('#clientDetailContainer').text("");
                $('#clientDetailContainer').append("<div class=\"box-header\">"
                        + "<h3 class=\"box-title\">Client Detail</h3></div>"
                        + "<div class=\"box-body\"><table class=\"table-hover\" border=\"0\"><tr><td>Client Name: </td><td>"
                        + data.clientName + "</td></tr>"
                        + "<tr><td>Address: </td><td>"
                        + data.address + "</td></tr>"
                        + "<tr><td>Phone No.: </td><td>"
                        + data.phoneNo + "</td></tr>"
                        + "<tr><td>Mobile Phone No.: </td><td>"
                        + data.mphoneNo + "</td></tr>"
                        + "<tr><td>Fax No.: </td><td>"
                        + data.faxNo + "</td></tr>"
                        + "<tr><td>E-mail Address: </td><td>"
                        + data.email + "</td></tr></table>"
                        + "<button id=\"editAClient\" class=\"btn btn-primary\">Edit</button></div>");
                $('#client_tab_control').text("");
                $('#client_tab_control').append("<input type=\"hidden\" name=\"clientId\" id=\"selectedClientId\" value=\"" + data.clientId + "\" />" +
                        "<ul class=\"nav nav-tabs\">" +
//                        "<li class=\"active\"><a href=\"#clientDetail_panel\" data-toggle=\"tab\">Detail</a></li>" +
                        "<li><a href=\"#clientContactInfo_panel\" data-toggle=\"tab\">Contact Info</a></li>" +
                        "<li><a href=\"#clientTransactionInfo_panel\" data-toggle=\"tab\">Transaction Info</a></li>" +
                        "<li><a href=\"#clientContactRecord_panel\" data-toggle=\"tab\">Contact Record</a></li>" +
                        "</ul>");
//                $('#clientDetail').append("<div class=\"tab-pane active\" id=\"clientDetail_panel\"><div class=\"box box-success\">"
//                        +"</div>        </div>");
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
                htmlString = "<div class=\"tab-pane pull-left\" id=\"clientContactInfo_panel\"><div class=\"box box-warning\"><div class=\"box-header\">"
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
            openDialog($('#detail'), "Client Information");
        });
    }
    
    function showTransactionInfo(clientId) {
        $("#clientDetail").append("<div class=\"tab-pane\" id=\"clientTransactionInfo_panel\">" +
                "<table class=\"table-hover\" cellpadding=\"9\" cellspacing=\"9\" border=\"0\" id=\"clientTransactionInfo_table\">" +
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
                "<button id=\"insertTransactionInfoBtn\" class=\"btn btn-primary\">Create New Transaction Info</button>" +
                "</div></div>");
        
        transactionInfoTable = $('#clientTransactionInfo_table').DataTable({
            "fnServerParams": function(aoData) {
                aoData.push({
                    'name': 'clientId', 'value': clientId
                });
            },
            "sAjaxSource": "./ShowTransactionInfo",
            "bPaginate": true,
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
            ],
            "aoColumnDefs": [
                {"aTargets": [6],
                    "fnCreatedCell": function(nTd, sData, oData, iRow, iCol) {
//                        var value = $(nTd).html();
                        $(nTd).empty();
                        $(nTd).append("<a class='transactionInfoTable_agreementBtn' href='#' data-txId='" + oData['7'] + "'><img src='static/img/agreement.png' width='20'></img></a>");

                    }
                }
            ]
        });
        showContactRecord(clientId);
    }
    function insertTransactionInfo() {
        $('#insertTransactionInfoForm_clientId').val(selectedClientId);
        console.log($('#insertTransactionInfoForm_clientId').value);
        $.ajax({
            type: "Post",
            url: './InsertTransactionInfo',
            data: {
                clientId: selectedClientId,
                transactionDate: $('#insertTransactionInfoForm_transactionDate').val(),
                autocompleteTransactionAddressEstate_hidden: $('#autocompleteTransactionAddressEstate_hidden').val(),
                autocompleteTransactionAddressFlat_hidden: $('#autocompleteTransactionAddressFlat_hidden').val(),
                transactionType: $('#insertTransactionInfoForm_transactionType').val(),
                transactionPrice: $('#insertTransactionInfoForm_transactionPrice').val(),
                transactionCommission: $('#insertTransactionInfoForm_transactionCommission').val()            
            },
            success: function(data) {
                transactionInfoTable = $('#clientTransactionInfo_table').DataTable({
                    "fnServerParams": function(aoData) {
                        aoData.push({
                            'name': 'clientId', 'value': selectedClientId
                        });
                    },
                    "sAjaxSource": "./ShowTransactionInfo",
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
                        {"data": "6"}
                    ],
                    "aoCloumnDefs": [
                        {"bVisible": false, "aTargets": [0]}
                    ]
                });
                $('#insertTransactionInfoForm').dialog("destroy");
            },
            error: function() {
                alert("fail");
            }
        });
    }
    
    function showContactRecord(clientId) {
        $("#clientDetail").append("<div class=\"tab-pane\" id=\"clientContactRecord_panel\">" +
                "<table class=\"table-hover\" cellpadding=\"9\" cellspacing=\"9\" border=\"0\" id=\"clientContactRecord_table\">" +
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
                "<button id=\"insertContactRecordBtn\" class=\"btn btn-primary\">Create New Contact Record</button>" +
                "</div></div>");

        contactRecordTable = $('#clientContactRecord_table').DataTable({
            "fnServerParams": function(aoData) {
                aoData.push({
                    'name': 'clientId', 'value': clientId
                });
            },
            "sAjaxSource": "./ShowContactRecord",
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
                {"data": "7"}
            ],
            "aoCloumnDefs": [
                {"bVisible": false, "aTargets": [0]}
            ]
        });
    }
    
    function insertContactRecord() {
        $.ajax({
            type: "Post",
            url: './InsertContactRecord',
            data: {
                clientId: selectedClientId,
                date: $('#insertContactRecordForm_contactDate').val(),
                contactPerson: $('#insertContactRecordForm_contactPerson').val(),
                stockNumber: $('#autocompleteContactRecordStock').val(),
                type: $('#insertContactRecordForm_type').val(),
                price: $('#insertContactRecordForm_price').val(),
                memo: $('#insertContactRecordForm_memo').val(),
                action: 'insertContactRecord'
            },
            success: function(data) {
                contactRecordTable = $('#clientContactRecord_table').DataTable({
                    "fnServerParams": function(aoData) {
                        aoData.push({
                            'name': 'clientId', 'value': selectedClientId
                        });
                    },
                    "sAjaxSource": "./ShowContactRecord",
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
                        {"data": "7"}
                    ],
                    "aoCloumnDefs": [
                        {"bVisible": false, "aTargets": [0]}
                    ]
                });
                $('#insertContactRecordForm').dialog("destroy");
            },
            error: function() {
                alert("fail");
            }
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
            url: './EditClientInfo',
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

    $('#clientDetailContainer').on('click', '#editAClient', function() {
        openDialog($('#editClientForm'), "Edit");
    });

    $('#insertNewClient').click(function() {
        openDialog($('#insertClientForm'), "New Client");
    });

    $('#clientDetail').on('click', '#insertNewContact', function() {
        openDialog($('#insertContactForm'), "New Contact");
    });
    
    $('#clientDetail').on('click', '#insertTransactionInfoBtn', function() {
        openDialog($('#insertTransactionInfoForm'), "New Transaction Info");
    });
    
    $('#clientDetail').on('click', '#insertContactRecordBtn', function() {
        $('#insertContactRecordForm_contactPerson')
                .find('option')
                .remove()
                .end()
                .append('<option value="blank">----------</option>');
        $.ajax({
            url: "./InsertContactRecord",
            type: 'POST',
            dataType: 'json',
            data: {
                action: 'getContactPerson',
                clientId: selectedClientId
            },
            success: function(data) {
                console.log(data);
                $.each(data, function(i, element) {
                    $('#insertContactRecordForm_contactPerson')
                            .append($("<option></option>")
                                    .attr("value", element[0])
                                    .text(element[1]));
                });
            }
        });
        var now = new Date().format("yyyy-mm-dd'T'HH:MM");
        $('#insertContactRecordForm_contactDate').val(now);
        openDialog($('#insertContactRecordForm'), "New Contact Record");
    });
    
    $('#insertTransactionInfoForm_submit').click(function() {
        insertTransactionInfo();
    });
    
    $('#insertContactRecordForm_submit').click(function() {
        insertContactRecord();
    });
    
    $('#clientDetail').on('click', '.transactionInfoTable_agreementBtn', function() {
        txId = $(this).attr('data-txId');
        console.log(txId);
        pdfViewerString = "<object id=\"transactionAgreement_pdfViewer\" width=\"700\" height=\"400\" type=\"application/pdf\" data=\"/my_pdf.pdf?#zoom=85&scrollbar=0&toolbar=0&navpanes=0\">" +
                "<p>Insert your error message here, if the PDF cannot be displayed.</p>" +
            "</object>";
        $('#pdf').text("");
        $('#pdf').append(pdfViewerString);
        $('#transactionAgreement_pdfViewer').attr('data', 'static/uploads/transaction_agreement/' + txId + '.pdf?#zoom=20&scrollbar=0&toolbar=0&navpanes=0');
        $('#manageTransactionAgreement').text("");
        htmlString = "<input type=\"file\" id=\"manageTransactionAgreement_uploadBtn\" name=\"datafile\" />" +  
            "<div id=\"upload\" style=\"display:none;\">Uploading..." +
                "<div>" +
                    "<img src=\"static/img/progressbar.gif\" alt=\"progressbar\"></img>" +
                "</div>" +
            "</div>";
        $('#manageTransactionAgreement').append(htmlString);
        $('#manageTransactionAgreement_uploadBtn').ajaxfileupload({
                    'action': 'UploadFile?txId=' + txId,
                    'onComplete': function(response) {
                        $('#upload').hide();
                        clearUrl = "clear";
                        new_url = 'static/uploads/transaction_agreement/' + txId + '.pdf?#zoom=20&scrollbar=0&toolbar=0&navpanes=0&timestamp=' + new Date().getTime();
                        $('#transactionAgreement_pdfViewer').attr('data', clearUrl);
                        $('#transactionAgreement_pdfViewer').load(clearUrl);
                        $('#transactionAgreement_pdfViewer').attr('data', new_url);
                        $('#transactionAgreement_pdfViewer').load(new_url);
                    },
                    'onStart': function() {
                        $('#upload').show();
                    }
        });
        openDialog($('#transactionAgreementForm'), "Transaction Agreement");
    });

    $("#autocompleteTransactionAddressEstate").autocomplete({
        appendTo: '#insertTransactionInfoForm',
        source: function(request, response) {
            $.ajax({
                url: "./InsertTransactionInfo",
                data: {
                    term: request.term,
                    action: 'getAddressEstate'
//                    country: $('input[name=country]:checked').val(), //Pass the selected country to php
                },
                type: "GET",
                dataType: "json",                                                                                                                                 
                success: function(data) {
                    parsedData = $.map(data, function(item) {
                        console.log(item[1]);
                        return{
                            label: item[1],
                            value: item[1],
                            estateId: item[0]
                        };
                    });
                    console.log(parsedData);
                    response(parsedData);
                }
            });
        },
        minLength: 2,
            select: function(event, ui) {
                $("#autocompleteTransactionAddressEstate_hidden").val(ui.item.estateId);
                $('#autocompleteTransactionAddressEstate').val(ui.item.label);
                return false;
                },
        change: function(event, ui) {
            if (!ui.item) {
                var matcher = new RegExp("^" + $.ui.autocomplete.escapeRegex($(this).val()) + "$", "i"), valid = false;
                if (!valid) {
                    $(this).val("");
                    return false;
                }
            }
        }
    });
    
    $("#autocompleteTransactionAddressBlock").autocomplete({
        appendTo: '#insertTransactionInfoForm',
        source: function(request, response) {
            $.ajax({
                url: "./InsertTransactionInfo",
                data: {
                    term: request.term,
                    action: 'getAddressBlock',
                    estateId: $("#autocompleteTransactionAddressEstate_hidden").val()
                },
                type: "GET",
                dataType: "json",
                success: function(data) {
                    parsedData = $.map(data, function(item) {
                        console.log(item[1]);
                        return{
                            label: item[1],
                            value: item[1],
                            blockId: item[0]
                        };
                    });
                    console.log(parsedData);
                    response(parsedData);
                }
            });
        },
        select: function(event, ui) {
            $("#autocompleteTransactionAddressBlock_hidden").val(ui.item.blockId);
            $('#autocompleteTransactionAddressBlock').val(ui.item.label);
            return false;
        },
        change: function(event, ui) {
            if (!ui.item) {
                var matcher = new RegExp("^" + $.ui.autocomplete.escapeRegex($(this).val()) + "$", "i"), valid = false;
                if (!valid) {
                    $(this).val("");
                    return false;
                }
            }
        }
    });
    
    $("#autocompleteTransactionAddressFloor").autocomplete({
        
        appendTo: '#insertTransactionInfoForm',
        source: function(request, response) {
            console.log($("#autocompleteTransactionAddressEstate_hidden").val());
            console.log($("#autocompleteTransactionAddressBlock_hidden").val());
            
            $.ajax({
                url: "./InsertTransactionInfo",
                data: {
                    term: request.term,
                    action: 'getAddressFloor',
                    estateId: $("#autocompleteTransactionAddressEstate_hidden").val(),
                    bldgId: $("#autocompleteTransactionAddressBlock_hidden").val()
                },
                type: "GET",
                dataType: "json",
                success: function(data) {
                    console.log(data);
                    parsedData = $.map(data, function(item) {
                        return{
                            label: item,
                            value: item,
                            floorId: item
                        };
                    });
                    console.log(parsedData);
                    response(parsedData);
                }
            });
        },
        select: function(event, ui) {
            $("#autocompleteTransactionAddressFloor_hidden").val(ui.item.floorId);
            $('#autocompleteTransactionAddressFloor').val(ui.item.label);
            return false;
        },
        change: function(event, ui) {
            if (!ui.item) {
                var matcher = new RegExp("^" + $.ui.autocomplete.escapeRegex($(this).val()) + "$", "i"), valid = false;
                if (!valid) {
                    $(this).val("");
                    return false;
                }
            }
        }
    });
    
    $("#autocompleteTransactionAddressFlat").autocomplete({
        appendTo: '#insertTransactionInfoForm',
        source: function(request, response) {
            $.ajax({
                url: "./InsertTransactionInfo",
                data: {
                    term: request.term,
                    action: 'getAddressFlat',
                    estateId: $("#autocompleteTransactionAddressEstate_hidden").val(),
                    bldgId: $("#autocompleteTransactionAddressBlock_hidden").val(),
                    floor: $("#autocompleteTransactionAddressFloor_hidden").val()
                },
                type: "GET",
                dataType: "json",
                success: function(data) {
                    console.log(data);
                    parsedData = $.map(data, function(item) {
                        return{
                            label: item[0],
                            value: item[0],
                            unitId: item[1]
                        };
                    });
                    response(parsedData);
                }
            });
        },
        select: function(event, ui) {
            $("#autocompleteTransactionAddressFlat_hidden").val(ui.item.unitId);
            $('#autocompleteTransactionAddressFlat').val(ui.item.label);
            return false;
        },
        change: function(event, ui) {
            if (!ui.item) {
                var matcher = new RegExp("^" + $.ui.autocomplete.escapeRegex($(this).val()) + "$", "i"), valid = false;
                if (!valid) {
                    $(this).val("");
                    return false;
                }
            }
        }
    });
    
    $("#autocompleteContactRecordStock").autocomplete({
        appendTo: '#insertContactRecordForm',
        source: function(request, response) {
            $.ajax({
                url: "./InsertContactRecord",
                data: {
                    term: request.term,
                    action: 'getStock'
//                    country: $('input[name=country]:checked').val(), //Pass the selected country to php
                },
                type: "GET",
                dataType: "json",
                success: function(data) {
                    console.log(data);
                    parsedData = $.map(data, function(item) {
                        return{
                            label: item,
                            value: item
                        };
                    });
                    console.log(parsedData);
                    response(parsedData);
                }
            });
        },
        minLength: 2,
        select: function(event, ui) {
            $('#autocompleteContactRecordStock').val(ui.item.label);
            return false;
        },
        change: function(event, ui) {
            if (!ui.item) {
                var matcher = new RegExp("^" + $.ui.autocomplete.escapeRegex($(this).val()) + "$", "i"), valid = false;
                if (!valid) {
                    $(this).val("");
                    return false;
                }
            }
        }
    });
});

