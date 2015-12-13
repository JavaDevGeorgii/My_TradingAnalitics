<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 04.05.2015
  Time: 11:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">

        <div>
            <ul class="nav navbar-nav navbar-right-center">
                <li><a href="/"   ><span class="glyphicon glyphicon-home" style="font-size: 1.4em" ></span></a></li>
            </ul>

        </div>

        <div>

            <ul class="nav navbar-nav navbar-right-center">
                <li><a href="/charts/countDealsByTool">Count deals by tools</a></li>
                <li><a href="/charts/pAndLByDay">P&L by day</a></li>
                <li><a href="/deal/search">Import</a></li>
                <c:choose>
                    <c:when test="${pageContext.request.userPrincipal.authenticated}">
                        <li><a href="/home">Home</a></li>
                    </c:when>
                </c:choose>
            </ul>

            <form role="search" class="navbar-form navbar-left">
                <div class="form-group">
                    <input type="text" placeholder="Search" class="form-control">
                </div>
            </form>

            <ul class="nav navbar-nav navbar-right">
                <c:choose>
                    <c:when test="${pageContext.request.userPrincipal.authenticated}">

                        <c:if test="${pageContext.request.userPrincipal.name != null}">
                            <li>
                                <div style="height: 50px;">
                                    <p class="text-info" style="margin-bottom: 0; padding: 15px;"> Welcome, <strong>${pageContext.request.userPrincipal.name}  </strong></p>
                                </div>
                            </li>
                        </c:if>

                        <li><a href="/logout"><span class="glyphicon glyphicon-log-out" ></span> Logout</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="#" data-toggle="modal" data-target="#createUserModal" style="outline-color: #fff;" ><span class="glyphicon glyphicon-user"  ></span> Sign Up</a></li>
                        <li><a href="#" data-toggle="modal" data-target="#loginUserModal" style="outline-color: #fff;" ><span class="glyphicon glyphicon-log-in"  ></span> Log in</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>

        </div>
    </div>
</nav>


<div id="createUserModal" class="modal fade" role="dialog" aria-labelledby="gridSystemModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Create new user</h4>
            </div>

            <div class="modal-body">
                <form id="create_user_form">

                    <div class="form-group">
                        <label for="login" class="control-label">Login:</label>
                        <input type="text" class="form-control" name="login" id="login">
                    </div>
                    <div class="form-group">
                        <label for="password" class="control-label">Password:</label>
                        <input type="password" class="form-control" name="password" id="password">
                    </div>
                    <div class="form-group">
                        <label for="confpass" class="control-label">Confirm password:</label>
                        <input type="password" class="form-control" name="confpass" id="confpass">
                    </div>
                    <%--<input type="submit" style="display: none;"/>--%>
                </form>
            </div>
            <div class="modal-footer">
                <div class="grid">
                    <div class="row" style="padding: 15px;">
                        <div id="result" style=" font-size: 14px; text-align: center; display: none; "></div>
                    </div>
                    <div class="row" style="padding: 15px 90px;">
                        <button id="close_button" type="button" class="btn btn-default" data-dismiss="modal" style="margin-bottom: 0;">Close</button>
                        <button id="add_user" type="button" class="btn btn-primary" >Add user</button>
                        <a href="/home"  id="home_button" class="btn btn-primary pull-left" style="display: none;" role="button">Home</a>
                        <a href="javascript:location.reload();"  id="stay_button" class="btn btn-primary" style="display: none;" role="button">Stay here</a>
                        <div id="pass_match" class="pull-left" style=" font-size: 14px;  display: none;"></div>
                    </div>
                </div>
            </div>

        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<div id="loginUserModal" class="modal fade" role="dialog" aria-labelledby="gridSystemModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Log in</h4>
            </div>

            <div class="modal-body">
                <form id="login_form" >

                    <div class="form-group">
                        <label for="login" class="control-label">Login:</label>
                        <input type="text" class="form-control" name="check_login" id="check_login">
                    </div>
                    <div class="form-group">
                        <label for="password" class="control-label">Password:</label>
                        <input type="password" class="form-control" name="check_password" id="check_password">
                    </div>
                    <%--<input type="submit" style="display: none;"/>--%>
                    <div class="checkbox pull-right" style="padding: 15px;">
                        <label>
                            Remember Me: <input type="checkbox" name="remember-me" />
                        </label>
                    </div>
                </form>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" style="margin-bottom: 0;">Close</button>
                <button id="login_user" type="button" class="btn btn-primary" >Log in</button>
                <div id="result_login" class="pull-left" style=" font-size: 14px;  display: none; "></div>
            </div>

        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<%--<body onload='document.loginForm.username.focus();'>--%>
