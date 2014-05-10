$(function() {
    $('#calendar').fullCalendar({
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
                }
            });
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
        }
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
                }
            });
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
        }
    });
});