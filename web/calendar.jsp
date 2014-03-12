<%-- 
    Document   : calendar
    Created on : Mar 12, 2014, 7:50:25 PM
    Author     : hong
--%>
<div class="box box-warning" id="dialog_calendar" hidden="">
    <div class="box-header">
        <i class="fa fa-calendar"></i>
        <div class="box-title">Calendar</div>

        <!-- tools box -->
        <div class="pull-right box-tools">
            <!-- button with a dropdown -->
            <div class="btn-group">
                <button class="btn btn-warning btn-sm dropdown-toggle" data-toggle="dropdown"><i class="fa fa-bars"></i></button>
                <ul class="dropdown-menu pull-right" role="menu">
                    <li><a href="#">Add new event</a></li>
                    <li><a href="#">Clear events</a></li>
                    <li class="divider"></li>
                    <li><a href="#">View calendar</a></li>
                </ul>
            </div>
        </div><!-- /. tools -->                                    
    </div><!-- /.box-header -->
    <div class="box-body no-padding">
        <!--The calendar -->
        <div id="calendar" style="height: 300px"></div>
    </div><!-- /.box-body -->
</div><!-- /.box -->
