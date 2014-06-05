
<%@page import="com.crm.security.Role"%>
<%@page import="com.crm.security.User"%>
<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Midland CRM System</title>

        <!-- bootstrap 3.0.2 -->
        <link href="static/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- font Awesome -->
        <link href="static/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <!-- Ionicons -->
        <link href="static/css/ionicons.min.css" rel="stylesheet" type="text/css" />
        <!-- jvectormap -->
        <link href="static/css/jvectormap/jquery-jvectormap-1.2.2.css" rel="stylesheet" type="text/css" />
        <!-- fullCalendar -->
        <link href="static/css/fullcalendar/fullcalendar.css" rel="stylesheet" type="text/css" />
        <link href="static/css/fullcalendar/fullcalendar.print.css" rel="stylesheet" type="text/css" media='print' />
        <!-- Daterange picker -->
        <link href="static/css/daterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css" />
        <!-- bootstrap wysihtml5 - text editor -->
        <link href="static/css/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css" rel="stylesheet" type="text/css" />
        <!-- Messenger css -->
        <link href="static/css/Messenger/messenger.css" rel="stylesheet" type="text/css" />
        <link href="static/css/Messenger/messenger-theme-future.css" rel="stylesheet" type="text/css" />
        <!-- Pace -->
        <link href="static/css/pace.css" rel="stylesheet" type="text/css" />
        <!-- Theme style -->
        <link href="static/css/styles.css" rel="stylesheet" type="text/css" />
        <!-- Panorama style -->
        <link rel="stylesheet" href="static/css/panorama/index.css">
        <link rel="stylesheet" href="static/css/panorama/leanorama.css">
        <link rel="stylesheet" href="static/css/panorama/leanorama.hotspot.css">
        <link rel="stylesheet" href="static/css/panorama/leanorama.controlbar.css">
        <link rel="stylesheet" href="static/css/panorama/leanorama.infobox.css">
        
        <link href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.min.css" rel="stylesheet" type="text/css" />
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->
 
        <!-- DataTable css-->
        <link href="static/css/datatables/dataTables.bootstrap.css" rel="stylesheet" type="text/css" />
  

        <!-- Converse-->
        



        <script src="static/js/jquery-ui-1.10.4.custom/js/jquery-1.10.2.js"></script>

        <!-- jQuery 2.0.2 -->
        <!--<script src="http://code.jquery.com/jquery-2.1.0.min.js"></script>-->
        <!-- jQuery UI 1.10.3 -->
        <!--        <script src="js/jquery-ui-1.10.3.min.js" type="text/javascript"></script>-->
        <!-- Bootstrap -->
        <script src="static/js/bootstrap.min.js" type="text/javascript"></script>


        <!-- Sparkline -->
        <script src="static/js/plugins/sparkline/jquery.sparkline.min.js" type="text/javascript"></script>
        <!-- jvectormap -->
        <script src="static/js/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js" type="text/javascript"></script>
        <script src="static/js/plugins/jvectormap/jquery-jvectormap-world-mill-en.js" type="text/javascript"></script>
        <!-- fullCalendar -->
        <script src="static/js/plugins/fullcalendar/fullcalendar.min.js" type="text/javascript"></script>
        <!-- jQuery Knob Chart -->
        <script src="static/js/plugins/jqueryKnob/jquery.knob.js" type="text/javascript"></script>
        <!-- daterangepicker -->
        <script src="static/js/plugins/daterangepicker/daterangepicker.js" type="text/javascript"></script>
        <!-- iCheck -->
        <script src="static/js/plugins/iCheck/icheck.min.js" type="text/javascript"></script>
        <!-- Messenger -->
        <script src="static/js/plugins/Messenger/messenger.min.js" type="text/javascript"></script>
        <script src="static/js/plugins/Messenger/messenger-theme-future.js" type="text/javascript"></script>
        <!-- Pace -->
        <script src="static/js/plugins/pace.js" type="text/javascript"></script>

        <!-- jsPDF-->
        <script type="text/javascript" src="static/js/jspdf/jspdf.js"></script>
        <script type="text/javascript" src="static/js/jspdf/jspdf.plugin.addimage.js"></script>
        <script type="text/javascript" src="static/js/jspdf/jspdf.plugin.from_html.js"></script>
        <script type="text/javascript" src="static/js/jspdf/jspdf.plugin.autoprint.js"></script>
        <script type="text/javascript" src="static/js/jspdf/jspdf.plugin.split_text_to_size.js"></script>
        <script type="text/javascript" src="static/js/jspdf/jspdf.plugin.total_pages.js"></script>
        <script type="text/javascript" src="static/js/jspdf/jspdf.plugin.png_support.js"></script>
        <script type="text/javascript" src="static/js/jspdf/jspdf.plugin.addhtml.js"></script>
        <script type="text/javascript" src="static/js/jspdf/jspdf.plugin.standard_fonts_metrics.js"></script>
        <script type="text/javascript" src="static/js/jspdf/jspdf.plugin.cell.js"></script>
        <script type="text/javascript" src="static/js/jspdf/libs/png_support/png.js"></script>
        <script type="text/javascript" src="static/js/jspdf/libs/png_support/zlib.js"></script>
        <script type="text/javascript" src="static/js/jspdf/libs/FileSaver.js/FileSaver.min.js"></script>
        <script type="text/javascript" src="static/js/jspdf/libs/Deflate/adler32cs.js"></script>
        <script type="text/javascript" src="static/js/jspdf/libs/Deflate/deflate.js"></script>


        
        <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js" type="text/javascript"></script>
        <script type="text/javascript" src="static/js/jquery.dialogextend.js"></script>

        <!-- AdminLTE App -->
        <script src="static/js/AdminLTE/app.js" type="text/javascript"></script>

        <script src="static/js/date.js" type="text/javascript" ></script>
        <link rel="stylesheet" href="static/css/site.css" />
        <!-- bootbox code -->
        <script src="static/js/bootbox.min.js"></script>
        <!-- DataTable code-->
        <script src="static/js/DataTable/jquery.dataTables.js" type="text/javascript"></script>
        <script src="static/js/DataTable/dataTables.bootstrap.js" type="text/javascript"></script>
        <!-- Google Chart API-->
        <script type="text/javascript" src="https://www.google.com/jsapi"></script>
        <script type="text/javascript" src="static/js/jquery.serializejson/jquery.serializejson.min.js" ></script>
        <!-- ajaxfileupload-->
        <script src="static/js/jquery.ajaxfileupload.js"></script>
        <!-- Function JS -->
        <script src="static/js/function/personalSalesReport.js" type="text/javascript"></script>
        <script src="static/js/function/teamSalesReport.js" type="text/javascript"></script>
        <script src="static/js/function/teamMemberSalesReport.js" type="text/javascript"></script>
        <script src="static/js/function/property.js" type="text/javascript"></script>
        <script src="static/js/function/client.js" type="text/javascript"></script>
        <script src="static/js/function/activity.js" type="text/javascript"></script>
        <script src="static/js/function/login.js" type="text/javascript"></script>
        
        <!--                                Converse css-->

        <link rel="stylesheet" type="text/css" media="screen" href="static/js/converse/converse.css">
        <script type="text/javascript" src="static/js/converse/components/otr/build/dep/salsa20.js"></script>
        <script type="text/javascript" src="static/js/converse/components/otr/build/dep/bigint.js"></script>
        <!-- CryptoJS -->
        <script type="text/javascript" src="static/js/converse/components/otr/vendor/cryptojs/core.js"></script>
        <script type="text/javascript" src="static/js/converse/components/otr/vendor/cryptojs/enc-base64.js"></script>
        <script type="text/javascript" src="static/js/converse/components/crypto-js-evanvosberg/src/md5.js"></script>
        <script type="text/javascript" src="static/js/converse/components/crypto-js-evanvosberg/src/evpkdf.js"></script>
        <script type="text/javascript" src="static/js/converse/components/otr/vendor/cryptojs/cipher-core.js"></script>
        <script type="text/javascript" src="static/js/converse/components/otr/vendor/cryptojs/aes.js"></script>
        <script type="text/javascript" src="static/js/converse/components/otr/vendor/cryptojs/sha1.js"></script>
        <script type="text/javascript" src="static/js/converse/components/otr/vendor/cryptojs/sha256.js"></script>
        <script type="text/javascript" src="static/js/converse/components/otr/vendor/cryptojs/hmac.js"></script>
        <script type="text/javascript" src="static/js/converse/components/otr/vendor/cryptojs/pad-nopadding.js"></script>
        <script type="text/javascript" src="static/js/converse/components/otr/vendor/cryptojs/mode-ctr.js"></script>
        <!-- until here -->
        <script type="text/javascript" src="static/js/converse/components/otr/build/dep/eventemitter.js"></script>
        <script type="text/javascript" src="static/js/converse/components/otr/build/otr.js"></script>
        <script type="text/javascript" src="static/js/converse/components/strophe/strophe.js"></script>
        <script type="text/javascript" src="static/js/converse/components/strophe.roster/index.js"></script>
        <script type="text/javascript" src="static/js/converse/components/strophe.muc/index.js"></script>
        <script type="text/javascript" src="static/js/converse/components/strophe.vcard/index.js"></script>
        <script type="text/javascript" src="static/js/converse/components/strophe.disco/index.js"></script>
        <script type="text/javascript" src="static/js/converse/components/underscore/underscore.js"></script>
        <script type="text/javascript" src="static/js/converse/components/backbone//backbone.js"></script>
        <script type="text/javascript" src="static/js/converse/components/backbone.localStorage/backbone.localStorage.js"></script>
        <script type="text/javascript" src="static/js/converse/components/tinysort/src/jquery.tinysort.js"></script>
        <script type="text/javascript" src="static/js/converse/components/jed/jed.js"></script>
        <script type="text/javascript" src="static/js/converse/locale/en/LC_MESSAGES/en.js"></script>
        <script type="text/javascript" src="static/js/converse/converse.js"></script>
        <!-- Panorama -->
        <script src="static/js/panorama/jquery.transit.js"></script>
        <script src="static/js/panorama/jquery.leanorama.js"></script>
        <script src="static/js/panorama/jquery.leanorama.hotspot.js"></script>
        <script src="static/js/panorama/jquery.leanorama.controlbar.js"></script>
        <script src="static/js/panorama/jquery.leanorama.infobox.js"></script>

        <script>
            $.ajax({
                    url: 'Prebind',
                    type: 'GET',
                    success: function(data) {
                        console.log(data);
                            converse.initialize({
                                auto_list_rooms: true,
                                allow_muc: false,
                                debug: true,
                                bosh_service_url: 'http://14.199.25.192:7070/http-bind/', // Please use this connection manager only for testing purposes
                                hide_muc_server: false,
                                i18n: locales.en, // Resfer to ./locale/locales.js to see which locales are supported
                                prebind: true,
                                show_controlbox_by_default: true,
                                xhr_user_search: false,
                                jid: data.jid,
                                sid: data.sid,
                                rid: data.rid
                            });
                    },
                    error:function(){
                        converse.initialize({
                            auto_list_rooms: false,
                            auto_subscribe: false,
                            bosh_service_url: 'https://bind.conversejs.org', // Please use this connection manager only for testing purposes
                            hide_muc_server: false,
                            i18n: locales.en, // Refer to ./locale/locales.js to see which locales are supported
                            prebind: false,
                            show_controlbox_by_default: true,
                            xhr_user_search: false
                        });
                    }
                });
            $(function() { 
                //init
                Messenger.options = {
                    extraClasses: 'messenger-fixed messenger-on-bottom messenger-on-left',
                    theme: 'future'
                };

                $("[data-toggle='offcanvas']").click();

                Messenger().post({
                    message: "<h4>Welcome <%=((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getFirstName()%>!</h4>",
                    type: "info",
                    showCloseButton: true
                });


                function openDialog(id, title) {
                    id.dialog({
                        "title": title,
                        "width": "auto",
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

                $('#profile').click(function() {
                    openDialog($('#dialog_profile'), "Profile");
                });

                $('#open_activity').click(function() {
                    openDialog($('#dialog_activity'), "Activity");
                });
                
                $('#actNoticeShowAll').on('click', function(){
                    openDialog($('#dialog_activity'), "Activity");
                });
            });
        </script>
    </head>
    <body class="skin-black">
        <!-- header logo: style can be found in header.less -->
        <header class="header">
            <a href="index" class="logo">
                <!-- Add the class icon to your logo image or logo icon to add the margining -->
                MIDLAND
            </a>
            <!-- Header Navbar: style can be found in header.less -->
            <nav class="navbar navbar-static-top" role="navigation">
                <!-- Sidebar toggle button-->
                <a href="#" class="navbar-btn sidebar-toggle" data-toggle="offcanvas" role="button">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </a>
                <div class="navbar-right">
                    <ul class="nav navbar-nav">
                        <li>
                            <a href="#" id='showClientList'>
                                <i class="fa fa-users"></i>
                                <span>Client</span>
                            </a>
                        </li>
                        <li>
                            <a href="#" id='showPropertyList'>
                                <i class="glyphicon glyphicon-eye-open"></i>
                                <span>Property</span>
                            </a>
                        </li>
                        <li>
                            <a href="#" id="open_activity">
                                <i class="glyphicon glyphicon-calendar"></i>
                                <span>Activity</span>
                            </a>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <i class="glyphicon glyphicon-stats"></i>
                                <span>Report</span>
                                <b class="caret"></b>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="#" id="personalSalesReport">Personal Sales Report</a></li>
                         <% String authHtml;
                            if ((((Role) SecurityContextHolder.getContext().getAuthentication().getAuthorities().iterator().next()).getAuthority()).equalsIgnoreCase("ACCOUNT MANAGER"))
                                authHtml = "<li><a href=\"#\" id=\"teamSalesReport\">Team Sales Report</a></li><li><a href=\"#\" id=\"teamMemberSalesReport\">Team Member Sales Report</a></li>";
                            else
                                authHtml = "";
                         %>       
                                <%= authHtml%>
                                
                            </ul>
                        </li>

                        <!-- Notifications: style can be found in dropdown.less -->
                        <li class="dropdown notifications-menu" id="actNoticeDropdownMenu">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" id="notification">
                                <i id='actNoticeWarningIcon' class="fa fa-warning" ></i>
                            </a>
                            <ul class="dropdown-menu">        
                                <li id="actNoticeSentence" class="header">You have don't have any notification</li>
                                <li>
                                    <ul id="actNoticeDetail" class="menu">
                                        
                                    </ul>
                                </li>                            
                            </ul>
                    
                        </li>
                        <!-- User Account: style can be found in dropdown.less -->
                        <li class="dropdown user user-menu">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <i class="glyphicon glyphicon-user"></i>
                                <span><%=((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getFirstName()%><i class="caret"></i></span>
                            </a>
                            <ul class="dropdown-menu">
                                <!-- User image -->
                                <li class="user-header bg-black">
                                    <img src="static/img/Jane.png" class="img-circle" alt="User Image" />
                                    <p>
                                        <%=((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getFirstName()%>
                                        <small>Member since Nov. 2012</small>
                                    </p>
                                </li>
                                <!-- Menu Body -->
                                <li class="user-body">
                                    <div class="col-xs-12 text-center">
                                        <a href="#"><%=(((Role) SecurityContextHolder.getContext().getAuthentication().getAuthorities().iterator().next()).getAuthority())%></a>
                                    </div>
                                </li>
                                <!-- Menu Footer-->
                                <li class="user-footer">
                                    <div class="pull-left">
                                        <a href="#" class="btn btn-default btn-flat" id="profile">Profile</a>
                                    </div>
                                    <div class="pull-right">
                                        <a href="<%=request.getContextPath()%>/logout" class="btn btn-default btn-flat" id="logout">Sign out</a>
                                    </div>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </nav>
        </header>
        <div class="wrapper row-offcanvas row-offcanvas-left">
            <!-- Left side column. contains the logo and sidebar -->
            <aside class="left-side sidebar-offcanvas" style="z-index: 1003;">
                <!-- sidebar: style can be found in sidebar.less -->
                <section class="sidebar">
                    <!-- Sidebar user panel -->
                    <div class="user-panel">
                        <div class="pull-left image">
                            <img src="static/img/Jane.png" class="img-circle" alt="User Image" />
                        </div>

                        <div class="pull-left info">
                            <p>Hello, <%=((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getFirstName()%></p>

                            <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                        </div>
                    </div>
                    <!-- search form -->
                    <form action="#" method="get" class="sidebar-form">
                        <div class="input-group">
                            <input id="search" type="text" data-provide="typeahead" class="form-control" placeholder="Search"/>
                            <span class="input-group-btn">
                                <button type='submit' name='seach' id='search-btn' class="btn btn-flat"><i class="fa fa-search"></i></button>
                            </span>
                        </div>
                    </form>
                    <!-- /.search form -->
                    <!-- sidebar menu: : style can be found in sidebar.less -->
                    <ul class="sidebar-menu">

                    </ul>
                </section>
                <!-- /.sidebar -->
            </aside>

            <!-- Right side column. Contains the navbar and content of the page -->
            <aside class="right-side">

                <!-- Main content -->
                <section class="content">
                    <!-- top row -->
                    <div class="row">
                        <div class="col-xs-12">
                            <script src="static/socket.io/socket.io.js"></script>
                            <script>
            // creating a new websocket
            var counter = 0;
            $('#actNoticeDropdownMenu').on('hidden.bs.dropdown', function () {
                counter += $('#actNoticeDetail').children().length - counter;
                $('#actNoticeSentence').html("You don't have have any notification");
                $('#notification').html("<i id='actNoticeWarningIcon' class=\"fa fa-warning\" ></i>");
            });

            var socket = io.connect('tyt06326.no-ip.org:8000');

            socket.emit('connect', '<%=((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmpId()%>');
            // on every message recived we print the new datas inside the #container div
            socket.on('notification', function(data) {
                          console.log(data);
                $('#notification').html("<i id='actNoticeWarningIcon' class=\"fa fa-warning\" ></i><span id=\"actNoticeCounter\" class=\"label label-warning\">0</span>");
                $('#actNoticeSentence').html("You have have " + ($('#actNoticeDetail').children().length - counter + 1) + " notifications");
                $('#actNoticeDetail').prepend("<li><a href=\"#\"><i class=\"ion ion-ios7-people info\"></i>" +
                            data['A_N_TITLE'] + "</a></li>");
//                $('#actNoticeDetail').append("<li id=\"actNoticeShowAll\" class=\"footer\"><a href=\"#\">View all</a></li>");
                                
                $('#actNoticeCounter').html($('#actNoticeDetail').children().length - counter);
                var actPusgMsg = Messenger().post({
                    message: "You have an activity within 1 hour\n, '" + data['A_N_TITLE'] + "' \nat '" + data['START_DATE'] + "'",
                    type: "info",
                    actions: {
                        open: {
                            label: 'Got it!',
                            action: function() {
                                socket.emit('isRead', data['A_N_ID']);
                                actPusgMsg.cancel();
                            }
                        }
                    }
                });

   
            });
                            </script>
                        </div><!-- /.col -->
                    </div>
                    <!-- /.row -->
                </section><!-- /.content -->
            </aside><!-- /.right-side -->
        </div><!-- ./wrapper -->

        <!-- add new dialog / event modal -->

        <div id="conversejs" style="z-index: 999999"></div>

        <!-- Dialog -->
        <jsp:include page="personalSalesReport.jsp" />
        <jsp:include page="teamSalesReport.jsp" />
        <jsp:include page="teamMemberSalesReport.jsp"/>
        <jsp:include page="activity.jsp" />
        <jsp:include page="client.jsp" />
        <jsp:include page="property.jsp" />

        <div id="dialog_profile" hidden="">
            <p>Hi , I am <%=((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getFirstName()%></p>
        </div>
    </body>
</html>