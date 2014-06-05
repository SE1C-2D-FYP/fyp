$(function() {
//    var icons = {
//      header: "ui-icon-circle-arrow-e",
//      activeHeader: "ui-icon-circle-arrow-s"
//      header: "ui-icon-triangle-1-e",
//      activeHeader: "ui-icon-triangle-1-s"

//    };
//    $('#myTab a').click(function(e) {
//        e.preventDefault();
//        $(this).tab('show');
//    });
    $('#activity_tab').on('shown.bs.tab', 'a[data-toggle="tab"]', function(e) {
        $('#calendar_calendar_panel').fullCalendar('render');
    });
//    $('#activity_tab').tabs({
//        activate: function(event, ui) {
//            $('#calendar_calendar_panel').fullCalendar('render');
//        }
//    });
    $('.formContent').hide();
    $('.formTitle').click(function() {
        if ($(this).next().is(':hidden') != true) {
            $(this).removeClass('active');
            $(this).next().slideUp("normal");
        } else {
            $('.formTitle').removeClass('active');
            $('.formContent').slideUp('normal');
            if ($(this).next().is(':hidden') == true) {
                $(this).addClass('active');
                $(this).next().slideDown('normal');
            }
        }

    });
    $('.open').click(function(event)
    {
        $('.formTitle').next().slideDown('normal');
        {
            $('.formTitle').addClass('active');
        }
    }
    );

    $('.collapsebtn').click(function(event)
    {
        $('.formTitle').next().slideUp('normal');
        {
            $('.formTitle').removeClass('active');
        }
    }
    );

    loadActivityLists();
    $('#myModal').on('show.bs.modal', function(e) {
        loadActivityItem($(e.relatedTarget).data('id'));
    });
    $('#insertModal').on('show.bs.modal', function(e) {
        $('input[name="title"]').val("");
        $('textarea[name="content"]').val("");
        var now = new Date().format("yyyy-mm-dd'T'HH:MM");
        $('input[name="start"]').val(now);
        $('input[name="end"]').val(now);
    });
    $('#insertModal').on('hide.bs.modal', function(e) {
        loadActivityLists();
    });

    $('.delActivity').click(function() {
        if (confirm('You are about to delete this item.\nIt cannot be restored at a later time! Continue?') === true) {
            deleteActivityItem($('input[name="formId"]').val());
        }

    });
    $('.updateBtn').click(function() {
        updateActivity();
    });
    $('#insertBtn').click(function() {
        createActivity();
    });


    $('#searchBtn').click(function() {
//        var text = $("#searchform").find(":input").filter(function() {
//            return $.trim(this.value).length > 0
//        }).serializeJSON();
//        console.log('JavaScript done', JSON.stringify(text));
//        return false;
        loadActivityListsBySearch();
//                $('.ui-widget-content:first-child').hide();

//        open();
//$('input:text[value=""]', '#searchform').remove();
//alert($("#searchform").serialize());
//        alert(JSON.stringify($("#searchform").clone().find('input:text[value=""]').remove().end().serializeJSON()));
//        alert(JSON.stringify($("#searchform").clone().find('input').not('[value=""]').remove().end().serializeJSON()));
//alert($('#searchform').find('input').not('[value=""]').serialize());


    });
    
    $('#calendar_calendar_panel').fullCalendar({
        editable: true, //Enable drag and drop
        allDayDefault: false,
        events:
                {
                    url: 'CalendarController',
                    type: 'POST',
                    data: {
                        action: 'getEvent'
                    },
                    error: function() {
                        alert('there was an error while fetching events!');
                    },
                    color: 'yellow', // a non-ajax option
                    textColor: 'black'
                },
        buttonText: {//This is to add icons to the visible buttons
            prev: "<span class='fa fa-caret-left'></span>",
            next: "<span class='fa fa-caret-right'></span>",
            today: 'today',
            month: 'month',
            week: 'week',
            day: 'day'
        },
        header: {
            left: 'prev,next today',
            center: 'title',
            right: 'month,agendaWeek,agendaDay'
        },
        eventClick: function(calEvent, jsEvent, view) {
            $('#myModal').on('show.bs.modal', function(e) {
                loadActivityItem(calEvent.id);
            });
            $('#myModal').modal('show');
            //alert('Event: ' + calEvent.title);
            //alert('Coordinates: ' + jsEvent.pageX + ',' + jsEvent.pageY);
            //alert('View: ' + view.name);
        },
        eventDrop: function(event, dayDelta, minuteDelta, allDay, revertFunc, jsEvent, ui, view) {
            $.ajax({
                url: "CalendarController",
                type: "POST",
                data: {
                    action: "updateEvent",
                    type: "stop",
                    eventId: event.id,
                    dayDelta: dayDelta,
                    minuteDelta: minuteDelta
                },
                error: function() {
                    revertFunc;
                },
                success: function() {
                }
            });
            loadActivityLists();
        },
        eventResize: function(event, dayDelta, minuteDelta, revertFunc, jsEvent, ui, view) {
            $.ajax({
                url: "CalendarController",
                type: "POST",
                data: {
                    action: "updateEvent",
                    type: "resize",
                    eventId: event.id,
                    dayDelta: dayDelta,
                    minuteDelta: minuteDelta
                },
                error: function() {
                    revertFunc;
                }
            });
            loadActivityLists();
        }
    });
});
//    var icons = $("#accordion").accordion("option", "icons");
//
//var open = function() {
//    $('.ui-accordion-header').removeClass('ui-corner-all').addClass('ui-accordion-header-active ui-state-active ui-corner-top').attr({
//        'aria-selected': 'true',
//        'tabindex': '0'
//    });
//    $('.ui-accordion-header-icon').removeClass(icons.header).addClass(icons.headerSelected);
//    $('.ui-accordion-content').addClass('ui-accordion-content-active').attr({
//        'aria-expanded': 'true',
//        'aria-hidden': 'false'
//    }).show();
//};
//var collapse = function() {
//    $('.ui-accordion-header').removeClass('ui-accordion-header-active ui-state-active ui-corner-top').addClass('ui-corner-all').attr({
//        'aria-selected': 'false',
//        'tabindex': '-1'
//    });
//    $('.ui-accordion-header-icon').removeClass(icons.headerSelected).addClass(icons.header);
//    $('.ui-accordion-content').removeClass('ui-accordion-content-active').attr({
//        'aria-expanded': 'false',
//        'aria-hidden': 'true'
//    }).hide();
////        $(this).attr("disabled", "disabled");
////        $('.open').removeAttr("disabled");
//};
var loadActivityLists = function() {
    $.ajax({
        type: "GET",
        url: "./activityNote",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function(data) {
            loadFormDetails(data);
        },
        failure: function(errMsg) {
            alert(errMsg);
        }
    });

};

var loadActivityListsBySearch = function() {
    var text = JSON.stringify($("#searchform").find(":input").filter(function() {
        return $.trim(this.value).length > 0;
    }).serializeJSON());

//    alert($("#aa").val());
    $.ajax({
        type: "POST",
        url: "./activityNote/searchActivity",
//        data: JSON.stringify($("#searchform:input[value!='']").serializeJSON()),
        data: text,
        contentType: "application/json; charset=utf-8",
//        dataType: "json",


        success: function(data) {
            showSearchActivityResult(data);

        },
        failure: function(errMsg) {
            alert(errMsg);
        }
    });

};

var loadActivityItem = function(id) {
    $.ajax({
        type: "GET",
        url: "./activityNote/" + id,
        // The key needs to match your method's input parameter (case-sensitive).
//    data: JSON.stringify({ Markers: markers }),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function(data) {
            $('input[name="formId"]').val(data.id);
            $('.actTitle').val(data.title);
            $('.description').val(data.content);
            var start = new Date(data.start);
            var end = new Date(data.end);
            $('.startDate').val(start.format("yyyy-mm-dd'T'HH:MM"));
            $('.endDate').val(end.format("yyyy-mm-dd'T'HH:MM"));
            $('.activityStatus').val(data.type);

        },
        failure: function(errMsg) {
            alert(errMsg);
        }
    });
};

var deleteActivityItem = function(id) {

    $.ajax({
        type: "GET",
        url: "./activityNote/deleteActivityId=" + id,
        contentType: "application/json; charset=utf-8",
//        dataType: "json",
        success: function(data) {
//            $('input[name="deleMsg"]').val(data);
//            alert($('input[name="deleMsg"]').val());

            alert(data);
            $('#myModal').modal('hide');
            loadActivityLists();
            $('#calendar_calendar_panel').fullCalendar('refetchEvents');
        },
        failure: function(errMsg) {
            alert(errMsg);
        }
    });

};
var createActivity = function() {
    $.ajax({
        type: "POST",
        url: "./activityNote/createActivity",
        data: JSON.stringify($("#insertform").serializeJSON()),
//        data: $("#insertform").serializeJSON(),
//        data: JSON.stringify({end: "",start:"" ,title: "title"}),
        contentType: "application/json; charset=utf-8",
        success: function(html) {
            alert(html);
            loadActivityLists();
            $('#calendar_calendar_panel').fullCalendar('refetchEvents');
            $("#insertModal").modal('hide');
        }
    });
};

var showSearchActivityResult = function(data) {
    searchResult = "";
    if (data.todayRecords == '' && data.futureRecords == '' && data.pastRecords == '') {
        searchResult = '<h4 class="col-lg-12" style="text-align:center;">No records!</h4>';
    } else {
        $.each(data.todayRecords, function(key, val) {
            var start = new Date(val.start);
            var end = new Date(val.end);
            searchResult += '<div class="col-lg-12 title"><span ' + 'data-id="' + val.id + '" class="glyphicon glyphicon-pencil" data-toggle="modal" data-target="#myModal" style="font-size: 12px;"></span> ' + val.title + '</div>'
                    + '<div class="col-lg-4 type">' + val.type + '</div>'
                    + '<div class="col-lg-8 period" style="text-align: right;">' + start.format('yyyy-mm-dd HH:MM') + ' <b>To</b> ' + end.format('yyyy-mm-dd HH:MM') + '</div>';

        });
        $.each(data.futureRecords, function(key, val) {
            var start = new Date(val.start);
            var end = new Date(val.end);
            searchResult += '<div class="col-lg-12 title"><span ' + 'data-id="' + val.id + '" class="glyphicon glyphicon-pencil" data-toggle="modal" data-target="#myModal" style="font-size: 12px;"></span> ' + val.title + '</div>'
                    + '<div class="col-lg-4 type">' + val.type + '</div>'
                    + '<div class="col-lg-8 period" style="text-align: right;">' + start.format('yyyy-mm-dd HH:MM') + ' <b>To</b> ' + end.format('yyyy-mm-dd HH:MM') + '</div>';

        });

        $.each(data.pastRecords, function(key, val) {
            var start = new Date(val.start);
            var end = new Date(val.end);
            searchResult += '<div class="col-lg-12 title"><span ' + 'data-id="' + val.id + '" class="glyphicon glyphicon-pencil" data-toggle="modal" data-target="#myModal" style="font-size: 12px;"></span> ' + val.title + '</div>'
                    + '<div class="col-lg-4 type">' + val.type + '</div>'
                    + '<div class="col-lg-8 period" style="text-align: right;">' + start.format('yyyy-mm-dd HH:MM') + ' <b>To</b> ' + end.format('yyyy-mm-dd HH:MM') + '</div>';

        });
    }
    $('#activitySearchResult').html(searchResult);
}

var loadFormDetails = function(data) {

    var todayrcs = "";
    var futurercs = "";
    var pastrcs = "";
    if (data.todayRecords == '') {
        todayrcs += '<h4 class="col-lg-12" style="text-align:center;">No records!</h4>';
        $('#activityTodayTitle').html('<span class="icon"></span>Today Records (0)');
    }
    if (data.futureRecords == '') {
        futurercs += '<h4 class="col-lg-12" style="text-align:center;">No records!</h4>';
        $('#activityFutureTitle').html('<span class="icon"></span>Future Records (0)');
    }
    if (data.pastRecords == '') {
        pastrcs += '<h4 class="col-lg-12" style="text-align:center;">No records!</h4>';
        $('#activityPastTitle').html('<span class="icon"></span>Past Records (0)');
    }
    $.each(data.todayRecords, function(key, val) {
        var start = new Date(val.start);
        var end = new Date(val.end);
        todayrcs += '<div class="col-lg-12 title"><span ' + 'data-id="' + val.id + '" class="glyphicon glyphicon-pencil" data-toggle="modal" data-target="#myModal" style="font-size: 12px;"></span> ' + val.title + '</div>'
                + '<div class="col-lg-4 type">' + val.type + '</div>'
                + '<div class="col-lg-8 period" style="text-align: right;">' + start.format('yyyy-mm-dd HH:MM') + ' <b>To</b> ' + end.format('yyyy-mm-dd HH:MM') + '</div>';
        $('#activityTodayTitle').html('<span class="icon"></span>Today Records (' + data.todayRecords.length + ')');
    });
    $.each(data.futureRecords, function(key, val) {
        var start = new Date(val.start);
        var end = new Date(val.end);
        futurercs += '<div class="col-lg-12 title"><span ' + 'data-id="' + val.id + '" class="glyphicon glyphicon-pencil" data-toggle="modal" data-target="#myModal" style="font-size: 12px;"></span> ' + val.title + '</div>'
                + '<div class="col-lg-4 type">' + val.type + '</div>'
                + '<div class="col-lg-8 period" style="text-align: right;">' + start.format('yyyy-mm-dd HH:MM') + ' <b>To</b> ' + end.format('yyyy-mm-dd HH:MM') + '</div>';
        $('#activityFutureTitle').html('<span class="icon"></span>Future Records (' + data.futureRecords.length + ')');
    });

    $.each(data.pastRecords, function(key, val) {
        var start = new Date(val.start);
        var end = new Date(val.end);
        pastrcs += '<div class="col-lg-12 title"><span ' + 'data-id="' + val.id + '" class="glyphicon glyphicon-pencil" data-toggle="modal" data-target="#myModal" style="font-size: 12px;"></span> ' + val.title + '</div>'
                + '<div class="col-lg-4 type">' + val.type + '</div>'
                + '<div class="col-lg-8 period" style="text-align: right;">' + start.format('yyyy-mm-dd HH:MM') + ' <b>To</b> ' + end.format('yyyy-mm-dd HH:MM') + '</div>';
        $('#activityPastTitle').html('<span class="icon"></span>Past Records (' + data.pastRecords.length + ')');
    });

    $('#todayRecords').html(todayrcs);
    $('#futureRecords').html(futurercs);
    $('#pastRecords').html(pastrcs);
};
var updateActivity = function() {
    var id = $('input[name="formId"]').val();
    var title = $('.actTitle').val();
    var content = $('.description').val();
    var start = $('.startDate').val();
    var end = $('.endDate').val();
    var type = $('.activityStatus').val();

    $.ajax({
        type: "POST",
        url: "./activityNote/updateActivity",
        data: JSON.stringify({id: id, title: title, content: content, type: type, start: start, end: end}),
        contentType: "application/json; charset=utf-8",
        success: function(html) {
            alert(html);
            $('#calendar_calendar_panel').fullCalendar('refetchEvents');
            loadActivityLists();
            $('#myModal').modal('hide');
        }
    });

//                    data: JSON.stringify({name: "Gerry", age: 20, city: "Sydney"}),

//                  data: JSON.stringify({id: 1, title: "bob",  content: "", type: ""}),

//    alert($('input[name="formId"]').val() + $('.startDate').val());
//    $.ajax({
//        type: "GET",
//        url: "/FYP_CRM/activityNote/updateActivity",
//        contentType: "application/json; charset=utf-8",
////        data: "{\"title\":\"hmkcode\",\"id\":2}", 
////        dataType: "json",
////        data: JSON.stringify(),
//        
//            
////            name: "Gerry", age: 20, city: "Sydney" }),
////                JSON.stringify({
////            id: "1", title: "Bob",type:"",start:"",end:"",content:""
////        }),
//        success: function(data) {
////            $('input[name="deleMsg"]').val(data);
////            alert($('input[name="deleMsg"]').val());
//
//            alert(data);
////            $('#myModal').modal('hide');
////            loadActivityLists();
//        },
//        failure: function(errMsg) {
//            alert(errMsg);
//        }
//    });

    
};
