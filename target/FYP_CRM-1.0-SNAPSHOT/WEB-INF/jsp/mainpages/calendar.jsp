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
                    <li><a href="#" data-toggle="modal" data-target="#insertModal">Add New Event</a></li>
                </ul>
            </div>
        </div><!-- /. tools -->                                    
    </div><!-- /.box-header -->
    <div class="box-body no-padding">
        <!--The calendar -->
        <div id="calendar"></div>
    </div><!-- /.box-body -->
</div><!-- /.box -->

<!-- Modal -->
<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel"></h4>
            </div>
            <div class="modal-body">
                <p id="calendarDescription">Some Event.</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div>
    </div>
</div><!-- ./modal -->