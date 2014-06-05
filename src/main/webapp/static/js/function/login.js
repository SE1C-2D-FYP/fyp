$(function() {
    $(document).ready(function() {
        
    });
});



/*
 $.getJSON('/login', function (data) {
 converse.initialize({
 prebind: true,
 bosh_service_url: data.bosh_service_url,
 jid: data.jid,
 sid: data.sid,
 rid: data.rid
 });
 );*/
/*
 require(['converse'], function(converse) {
 converse.initialize({
 auto_list_rooms: true,
 allow_muc: false,
 debug: true,
 bosh_service_url: 'http://119.246.83.50:7070/http-bind/', // Please use this connection manager only for testing purposes
 hide_muc_server: false,
 i18n: locales.en, // Resfer to ./locale/locales.js to see which locales are supported
 prebind: false,
 show_controlbox_by_default: true,
 xhr_user_search: false
 });
 });*/
/*
 require(['converse'], function(converse) {
 converse.initialize({
 auto_list_rooms: false,
 auto_subscribe: true,
 bosh_service_url: 'http://119.246.83.50:7070/http-bind/',
 hide_muc_server: false,
 i18n: locales.en, // Refer to ./locale/locales.js to see which locales are supported
 prebind: true,
 show_controlbox_by_default: true,
 xhr_user_search: false,
 jid: '<%= session.getAttribute("jid")%>',
 sid: '<%= session.getAttribute("sid")%>',
 rid: '<%= session.getAttribute("rid")%>'
 });
 converse.attach();
 });*/
