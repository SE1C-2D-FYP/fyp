<%-- 
    Document   : index
    Created on : Feb 11, 2014, 8:19:52 PM
    Author     : hong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>

        <!-- bootstrap 3.0.2 -->
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- Typeahead -->
        <link href="css/typeahead.css" rel="stylesheet" type="text/css" />
        <!-- font Awesome -->
        <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <!-- Ionicons -->
        <link href="css/ionicons.min.css" rel="stylesheet" type="text/css" />
        <!-- Morris chart -->
        <link href="css/morris/morris.css" rel="stylesheet" type="text/css" />
        <!-- jvectormap -->
        <link href="css/jvectormap/jquery-jvectormap-1.2.2.css" rel="stylesheet" type="text/css" />
        <!-- fullCalendar -->
        <link href="css/fullcalendar/fullcalendar.css" rel="stylesheet" type="text/css" />
        <link href="css/fullcalendar/fullcalendar.print.css" rel="stylesheet" type="text/css" media='print' />
        <!-- Daterange picker -->
        <link href="css/daterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css" />
        <!-- bootstrap wysihtml5 - text editor -->
        <link href="css/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css" rel="stylesheet" type="text/css" />
        <!-- Messenger css -->
        <link href="css/Messenger/messenger.css" rel="stylesheet" type="text/css" />
        <link href="css/Messenger/messenger-theme-future.css" rel="stylesheet" type="text/css" />
        <!-- Pace -->
        <link href="css/pace.css" rel="stylesheet" type="text/css" />
        <!-- Theme style -->
        <link href="css/styles.css" rel="stylesheet" type="text/css" />

        <link href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.min.css" rel="stylesheet" type="text/css" />
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->

        <!-- jQuery 2.0.2 -->
        <script src="http://code.jquery.com/jquery-2.1.0.min.js"></script>
        <!-- jQuery UI 1.10.3 -->
        <script src="js/jquery-ui-1.10.3.min.js" type="text/javascript"></script>
        <!-- Bootstrap -->
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <!-- Typeahead -->
        <script src="js/typeahead.bundle.js" type="text/javascript"></script>
        <!-- Morris.js charts -->
        <script src="http://cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
        <script src="js/plugins/morris/morris.min.js" type="text/javascript"></script>
        <!-- Sparkline -->
        <script src="js/plugins/sparkline/jquery.sparkline.min.js" type="text/javascript"></script>
        <!-- jvectormap -->
        <script src="js/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js" type="text/javascript"></script>
        <script src="js/plugins/jvectormap/jquery-jvectormap-world-mill-en.js" type="text/javascript"></script>
        <!-- fullCalendar -->
        <script src="js/plugins/fullcalendar/fullcalendar.min.js" type="text/javascript"></script>
        <!-- jQuery Knob Chart -->
        <script src="js/plugins/jqueryKnob/jquery.knob.js" type="text/javascript"></script>
        <!-- daterangepicker -->
        <script src="js/plugins/daterangepicker/daterangepicker.js" type="text/javascript"></script>
        <!-- Bootstrap WYSIHTML5 -->
        <script src="js/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js" type="text/javascript"></script>
        <!-- iCheck -->
        <script src="js/plugins/iCheck/icheck.min.js" type="text/javascript"></script>
        <!-- Messenger -->
        <script src="js/plugins/Messenger/messenger.min.js" type="text/javascript"></script>
        <script src="js/plugins/Messenger/messenger-theme-future.js" type="text/javascript"></script>
        <!-- Pace -->
        <script src="js/plugins/pace.js" type="text/javascript"></script>

        <!-- AdminLTE App -->
        <script src="js/AdminLTE/app.js" type="text/javascript"></script>

        <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
        <script type="text/javascript" src="js/jquery.dialogextend.js"></script>

        
        <script>
            $(function() {
                //init
                Messenger.options = {
                    extraClasses: 'messenger-fixed messenger-on-bottom messenger-on-right',
                    theme: 'future'
                };

                $("[data-toggle='offcanvas']").click();

                var notiMsg;
                notiMsg = Messenger().post({
                    message: "You have 9 tasks uncomplete.",
                    type: "info",
                    actions: {
                        open: {
                            label: 'open',
                            action: function() {
                                $('#notification').click();
                                notiMsg.cancel();
                            }
                        },
                        cancel: {
                            label: 'cancel',
                            action: function() {
                                return notiMsg.cancel();
                            }
                        }
                    }
                });
                
                var msg;
                msg = Messenger().post({
                    message: "You have 5 messengers uncomplete.",
                    type: "info",
                    actions: {
                        open: {
                            label: 'open',
                            action: function() {
                                $('#msg').click();
                                msg.cancel();
                            }
                        },
                        cancel: {
                            label: 'cancel',
                            action: function() {
                                return msg.cancel();
                            }
                        }
                    }
                });

                Messenger().post({
                    message: "<h4>Welcome hong!</h4>",
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

                $('#geographicalSales').click(function() {
                    openDialog($('#dialog_salesReport_geographicalSales'), "Geographical of Sales");
                });

                $('#profit').click(function() {
                    openDialog($('#dialog_salesReport_profit'), "Profit Made");
                });

                $('#open_calendar').click(function() {
                    openDialog($('#dialog_calendar'), "Calenar");
                });

                //typeahead
                // instantiate the bloodhound suggestion engine
                var numbers = new Bloodhound({
                    datumTokenizer: function(d) {
                        return Bloodhound.tokenizers.whitespace(d.num);
                    },
                    queryTokenizer: Bloodhound.tokenizers.whitespace,
                    local: [
                        {num: 'one'},
                        {num: 'two'},
                        {num: 'three'},
                        {num: 'four'},
                        {num: 'five'},
                        {num: 'six'},
                        {num: 'seven'},
                        {num: 'eight'},
                        {num: 'nine'},
                        {num: 'ten'}
                    ]
                });

                // initialize the bloodhound suggestion engine
                numbers.initialize();

                // instantiate the typeahead UI
                $('#search').typeahead(null, {
                    displayKey: 'num',
                    source: numbers.ttAdapter()
                });
            });
        </script>

        <!-- Function JS -->
        <script src="js/function/noOfSales.js" type="text/javascript"></script>
        <script src="js/function/calendar.js" type="text/javascript"></script>

    </head>
    <body class="skin-black">
        <!-- header logo: style can be found in header.less -->
        <header class="header">
            <a href="index.jsp" class="logo">
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
                            <a href="#">
                                <i class="fa fa-users"></i>
                                <span>Client</span>
                            </a>
                        </li>

                        <li>
                            <a href="#" id="open_calendar">
                                <i class="glyphicon glyphicon-calendar"></i>
                                <span>Calendar</span>
                            </a>
                        </li>

                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <i class="fa fa-file-text"></i>
                                <span>Report</span>
                                <b class="caret"></b>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="#" id="noOfSales">Number of Sales</a></li>
                                <li><a href="#" id="geographicalSales">Geographical Sales</a></li>
                                <li><a href="#" id="profit">Profit</a></li>
                            </ul>
                        </li>

                        <!-- Messages: style can be found in dropdown.less-->
                        <li class="dropdown messages-menu">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" id="msg">
                                <i class="fa fa-envelope"></i>
                                <span class="label label-success">5</span>
                            </a>
                            <ul class="dropdown-menu">
                                <li class="header">You have 4 new messages</li>
                                <li>
                                    <!-- inner menu: contains the actual data -->
                                    <ul class="menu">
                                        <li><!-- start message -->
                                            <a href="#">
                                                <div class="pull-left">
                                                    <img src="img/avatar3.png" class="img-circle" alt="User Image"/>
                                                </div>
                                                <h4>
                                                    Support Team
                                                    <small><i class="fa fa-clock-o"></i> 5 mins</small>
                                                </h4>
                                                <p>Why not buy a new awesome theme?</p>
                                            </a>
                                        </li><!-- end message -->
                                        <li>
                                            <a href="#">
                                                <div class="pull-left">
                                                    <img src="img/avatar2.png" class="img-circle" alt="user image"/>
                                                </div>
                                                <h4>
                                                    Design Team
                                                    <small><i class="fa fa-clock-o"></i> 2 hours</small>
                                                </h4>
                                                <p>Why not buy a new awesome theme?</p>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="#">
                                                <div class="pull-left">
                                                    <img src="img/avatar.png" class="img-circle" alt="user image"/>
                                                </div>
                                                <h4>
                                                    Developers
                                                    <small><i class="fa fa-clock-o"></i> Today</small>
                                                </h4>
                                                <p>Why not buy a new awesome theme?</p>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="#">
                                                <div class="pull-left">
                                                    <img src="img/avatar2.png" class="img-circle" alt="user image"/>
                                                </div>
                                                <h4>
                                                    Sales Department
                                                    <small><i class="fa fa-clock-o"></i> Yesterday</small>
                                                </h4>
                                                <p>Why not buy a new awesome theme?</p>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="#">
                                                <div class="pull-left">
                                                    <img src="img/avatar.png" class="img-circle" alt="user image"/>
                                                </div>
                                                <h4>
                                                    Reviewers
                                                    <small><i class="fa fa-clock-o"></i> 2 days</small>
                                                </h4>
                                                <p>Why not buy a new awesome theme?</p>
                                            </a>
                                        </li>
                                    </ul>
                                </li>
                                <li class="footer"><a href="#">See All Messages</a></li>
                            </ul>
                        </li>
                        <!-- Notifications: style can be found in dropdown.less -->
                        <li class="dropdown notifications-menu" >
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" id="notification">
                                <i class="fa fa-warning"></i>
                                <span class="label label-warning">10</span>
                            </a>
                            <ul class="dropdown-menu">
                                <li class="header">You have 10 notifications</li>
                                <li>
                                    <!-- inner menu: contains the actual data -->
                                    <ul class="menu">
                                        <li>
                                            <a href="#">
                                                <i class="ion ion-ios7-people info"></i> 5 new members joined today
                                            </a>
                                        </li>
                                        <li>
                                            <a href="#">
                                                <i class="fa fa-warning danger"></i> Very long description here that may not fit into the page and may cause design problems
                                            </a>
                                        </li>
                                        <li>
                                            <a href="#">
                                                <i class="fa fa-users warning"></i> 5 new members joined
                                            </a>
                                        </li>

                                        <li>
                                            <a href="#">
                                                <i class="ion ion-ios7-cart success"></i> 25 sales made
                                            </a>
                                        </li>
                                        <li>
                                            <a href="#">
                                                <i class="ion ion-ios7-person danger"></i> You changed your username
                                            </a>
                                        </li>
                                    </ul>
                                </li>
                                <li class="footer"><a href="#">View all</a></li>
                            </ul>
                        </li>

                        <!-- Tasks: style can be found in dropdown.less -->
                        <li class="dropdown tasks-menu">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <i class="fa fa-tasks"></i>
                                <span class="label label-danger">9</span>
                            </a>
                            <ul class="dropdown-menu">
                                <li class="header">You have 9 tasks</li>
                                <li>
                                    <!-- inner menu: contains the actual data -->
                                    <ul class="menu">
                                        <li><!-- Task item -->
                                            <a href="#">
                                                <h3>
                                                    Design some buttons
                                                    <small class="pull-right">20%</small>
                                                </h3>
                                                <div class="progress xs">
                                                    <div class="progress-bar progress-bar-aqua" style="width: 20%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                                                        <span class="sr-only">20% Complete</span>
                                                    </div>
                                                </div>
                                            </a>
                                        </li><!-- end task item -->
                                        <li><!-- Task item -->
                                            <a href="#">
                                                <h3>
                                                    Create a nice theme
                                                    <small class="pull-right">40%</small>
                                                </h3>
                                                <div class="progress xs">
                                                    <div class="progress-bar progress-bar-green" style="width: 40%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                                                        <span class="sr-only">40% Complete</span>
                                                    </div>
                                                </div>
                                            </a>
                                        </li><!-- end task item -->
                                        <li><!-- Task item -->
                                            <a href="#">
                                                <h3>
                                                    Some task I need to do
                                                    <small class="pull-right">60%</small>
                                                </h3>
                                                <div class="progress xs">
                                                    <div class="progress-bar progress-bar-red" style="width: 60%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                                                        <span class="sr-only">60% Complete</span>
                                                    </div>
                                                </div>
                                            </a>
                                        </li><!-- end task item -->
                                        <li><!-- Task item -->
                                            <a href="#">
                                                <h3>
                                                    Make beautiful transitions
                                                    <small class="pull-right">80%</small>
                                                </h3>
                                                <div class="progress xs">
                                                    <div class="progress-bar progress-bar-yellow" style="width: 80%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                                                        <span class="sr-only">80% Complete</span>
                                                    </div>
                                                </div>
                                            </a>
                                        </li><!-- end task item -->
                                    </ul>
                                </li>
                                <li class="footer">
                                    <a href="#">View all tasks</a>
                                </li>
                            </ul>
                        </li>
                        <!-- User Account: style can be found in dropdown.less -->
                        <li class="dropdown user user-menu">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <i class="glyphicon glyphicon-user"></i>
                                <span>Hong<i class="caret"></i></span>
                            </a>
                            <ul class="dropdown-menu">
                                <!-- User image -->
                                <li class="user-header bg-black">
                                    <img src="img/Jane.png" class="img-circle" alt="User Image" />
                                    <p>
                                        Hong - Developer
                                        <small>Member since Nov. 2012</small>
                                    </p>
                                </li>
                                <!-- Menu Body -->
                                <li class="user-body">
                                    <div class="col-xs-12 text-center">
                                        <a href="#">Sales</a>
                                    </div>
                                </li>
                                <!-- Menu Footer-->
                                <li class="user-footer">
                                    <div class="pull-left">
                                        <a href="#" class="btn btn-default btn-flat" id="profile">Profile</a>
                                    </div>
                                    <div class="pull-right">
                                        <a href="#" class="btn btn-default btn-flat" id="logout">Sign out</a>
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
                            <img src="img/Jane.png" class="img-circle" alt="User Image" />
                        </div>
                        <div class="pull-left info">
                            <p>Hello, Hong</p>

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
                            <div id="container">Try to change your xml data to update this content</div>
                            <script src="socket.io/socket.io.js"></script>
                            <script>
                                // creating a new websocket
                                var socket = io.connect('http://localhost:8000');
                                socket.on('connect',function(){
                                    console.log("connected nodejs");
                                });
                                // on every message recived we print the new datas inside the #container div
                                socket.on('notification', function (data) {
                                    // convert the json string into a valid javascript object
                                    
                                    var _data = JSON.parse(data);
                                    console.log(_data);
                                    $('#container').html(_data.test.sample);
                                //$('time').html('Last Update:' + _data.time);
                            });
                            </script>
                        </div><!-- /.col -->
                    </div>
                    <!-- /.row -->
                </section><!-- /.content -->
            </aside><!-- /.right-side -->
        </div><!-- ./wrapper -->

        <!-- add new dialog / event modal -->

        <!-- Dialog -->
        <jsp:include page="noOfSales.jsp" />
        <jsp:include page="calendar.jsp" />

        <div id="dialog_profile" hidden="">
            <p>Hi , I am Hong</p>
        </div>

        <div id="dialog_salesReport_geographicalSales" hidden="">
        </div>

        <div id="dialog_salesReport_profit" hidden="">

        </div>

        <!-- Modal -->
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="myModalLabel"></h4>
                    </div>
                    <div class="modal-body">
                        Some Event.
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary">Save changes</button>
                    </div>
                </div>
            </div>
        </div><!-- ./modal -->
    </body>
</html>