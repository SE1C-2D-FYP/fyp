
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="bg-black">
    <head>
        <meta charset="UTF-8" content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <title>MIDLAND CRM | Log in</title>
        <!-- bootstrap 3.0.2 -->
        <link href="static/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- font Awesome -->
        <link href="static/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <!-- Theme style -->
        <link href="static/css/styles.css" rel="stylesheet" type="text/css" />

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->
    </head>
    <body class="bg-black">

        <div class="form-box" id="login-box">
            <div class="logo">
                <!-- Add the class icon to your logo image or logo icon to add the margining -->
                <p align="center">MIDLAND Customer Relation Management System</p>
            </div>
            <div class="header">Sign In</div>
            <form action="j_spring_security_check" method="post">
                <div class="body bg-gray">
                    <div class="form-group">
                        <input type="text" name="j_username" class="form-control" placeholder="User ID"/>
                    </div>
                    <div class="form-group">
                        <input type="password" name="j_password" class="form-control" placeholder="Password"/>
                    </div>          
                    <div class="form-group">
                        <input type="checkbox" name="_spring_security_remember_me"/> Remember me
                    </div>
                    <% String errorMsg = "";
                        if(request.getParameter("error") != null)
                            errorMsg = "Your login attempt was not successful, try again.";
                    %>
                    <div class="error" style="color: red;">
                        <%=errorMsg%>
                    </div>
                </div>
                <div class="footer">                                                               
                    <button type="submit" class="btn bg-olive btn-block">Sign me in</button>                  
                    
                </div>
            </form>

        </div>

        <!-- jQuery 2.0.2 -->
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js" type="text/javascript"></script>
        <!-- Bootstrap -->
        <script src="static/js/bootstrap.min.js" type="text/javascript"></script>        

    </body>
</html>