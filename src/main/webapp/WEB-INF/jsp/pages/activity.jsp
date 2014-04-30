<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="context" value="${pageContext.request.contextPath}" />

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>List Activity</title>

        <script src="js/jquery-ui-1.10.4.custom/js/jquery-1.10.2.js"></script>
        <script src="js/date.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.serializeJSON.min.js"></script>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/site.css">
        <script src="js/activity/activity.js"></script>
        <!-- bootbox code -->
        <script src="js/bootbox.min.js"></script>

<!--        <link rel="stylesheet" href="${context}/js/jquery-ui-1.10.4.custom/css/redmond/jquery-ui-1.10.4.custom.min.css">
<script src="${context}/js/jquery-ui-1.10.4.custom/js/jquery-1.10.2.js"></script>
<script src="${context}/js/jquery-ui-1.10.4.custom/js/jquery-ui-1.10.4.custom.min.js"></script>
<script src="${context}/js/date.js"></script>
<script src="${context}/js/bootstrap.min.js"></script>
<script src="${context}/js/jquery.serializeJSON.min.js"></script>
<link rel="stylesheet" href="${context}/css/bootstrap.min.css">
<link rel="stylesheet" href="${context}/css/site.css">
<script src="${context}/js/activity/activity.js"></script>
 bootbox code 
<script src="${context}/js/bootbox.min.js"></script>-->
        <!--        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"></script>
                <script src="${context}/jquery.confirm/jquery.confirm.js"></script>
                <script src="${context}/js/script.js"></script>-->
    </head>
    <body>
        <!-- Nav tabs -->
        <ul class="nav nav-tabs">
            <li class="active"><a href="#home" data-toggle="tab">Activity</a></li>
            <li><a href="#profile" data-toggle="tab">Calendar</a></li>

        </ul>

        <!-- Tab panes -->
        <div class="tab-content">
            <div class="tab-pane active" id="home">
                <div class="container-fluid activity">
                    <div class="row">

                        <div class="col-lg-4">menu</div>

                        <div class="col-lg-8">
                            <div><button type="button" class="btn btn-primary addActivitybtn" data-toggle="modal" data-target="#insertModal">Set an activity</button></div>
                            <div class="accordion-expand-holder">
                            </div>
                            <form id="searchform" method="POST" action="${context}/activityNote/">
                                <div class="searchbanner">Selections Details: <input class="clear" type="reset"></div>

                                <div class="cat_filter"><label><span class="searchLab">Title:</span><input name="title" type="text"/></label></div>
                                <div class="cat_filter">
                                    <label><span class="searchLab">Status:</span><select class="activityStatus2 boxsize" name="type">
                                            <option value="">--choices--</option>
                                            <option value="incomplete">Incomplete</option>
                                            <option value="possessing">Possessing</option>
                                            <option value="complete">Complete</option>
                                        </select></label></div>
                                <div class="cat_filter"><label><span class="searchLab">Time From:</span><input id="aa" type="date" name="start"/></label><label><span class="to">To</span><input type="date" name="end"/></label></div>
                                <button type="button" id="searchBtn">Search</button>
                            </form>
                            <!--                    
                                                                    <div id="accordion">
                                                                        <h3>Today Records</h3>
                                                                        <div id="todayRecords"></div>
                                                
                                                                        <h3>Future Records</h3>
                                                                        <div id="futureRecords"></div>
                                                
                                                                        <h3>Past Records</h3>
                                                                        <div id="pastRecords"style="display: block; height: 300px;"></div>
                                                
                                                                    </div>-->
                            <div><button type="button" class="collapsebtn">Collapse all</button>
                                <button type="button" class="open">Expand all</button>
                            </div>
                            <div id="accordion2" class="ui-accordion2 ui-widget2 ui-helper-reset2">
                                <h3 class="formTitle"><span class="icon"></span>Today Records</h3>
                                <div id="todayRecords" class="formContent "></div>

                                <h3 class="formTitle"><span class="icon"></span>Future Records</h3>
                                <div id="futureRecords" class="formContent "></div>

                                <h3 class="formTitle"><span class="icon"></span>Past Records</h3>
                                <div id="pastRecords" class="formContent" style="height: 300px;"></div>

                            </div>                         
                        </div>
                    </div>
                </div>
            </div>

            <div class="tab-pane" id="profile">
                <div>
                    calendar
                </div>
            </div>

        </div>

        <!-- Button trigger modal -->
        <!--<button  data-toggle="modal" data-target="#myModal">
          Launch demo modal
        </button>-->

        <!-- edit activty Modal -->
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="myModalLabel">Update Record </h4>
                    </div>
                    <div class="modal-body">
                        <input class="formId" name="formId" type="hidden">
                        <input  name="deleMsg" type="hidden">

                        <div>
                            <label><span class="actLable">Activity Name</span></span><input type="text" class="actTitle"/></label>
                        </div>
                        <div><label><span class="actLable">Description</span><textarea class="description" rows="4" cols="50"></textarea></label>
                        </div>

                        <div>
                            <label><span class="actLable">Status</span><select class="activityStatus">
                                    <option value="incomplete">Incomplete</option>
                                    <option value="possessing">Possessing</option>
                                    <option value="complete">Complete</option>
                                </select>
                            </label>
                        </div>
                        <div>
                            <label><span class="actLable">Start</span><input type="datetime-local" class="startDate" name="start" ></label>

                        </div>
                        <div>
                            <label><span class="actLable">End</span><input type="datetime-local" class="endDate" name="end" ></label>

                        </div>


                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary delActivity">Delete</button>                        
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                        <button type="button" class="btn btn-primary updateBtn">Save changes</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- insert activty Modal -->
        <div class="modal fade" id="insertModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="myModalLabel">Insert Record </h4>
                    </div>
                    <div class="modal-body">
                        <form id="insertform" method="post" >
                            <div>
                                <label><span class="actLable">Activity Name</span><input name="title" type="text"/></label>
                            </div>
                            <div><label><span class="actLable">Description</span><textarea name="content"  rows="4" cols="50"></textarea></label>
                            </div>

                            <div>
                                <label><span class="actLable">Status</span><select name="type" >
                                        <option value="incomplete">Incomplete</option>
                                        <option value="possessing">Possessing</option>
                                        <!--<option value="complete">Complete</option>-->
                                    </select>
                                </label>
                            </div>
                            <div>
                                <label><span class="actLable">Start</span><input type="datetime-local"  name="start" ></label>

                            </div>
                            <div>
                                <label><span class="actLable">End</span><input type="datetime-local"  name="end" ></label>
                            </div>

                        </form>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                        <button type="button" id="insertBtn" class="btn btn-primary ">Save changes</button>
                    </div>
                </div>
            </div>
        </div>
        <!--        <button class="a" id="test">Button</button>
                <div id="12">hello</div>
                <input type="datetime-local" class="aa" name="startDate" >-->


    </body>
</html>