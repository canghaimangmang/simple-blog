<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${request.contextPath }"></c:set>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>博客管理后台 </title>

    <!-- Bootstrap -->
    <link href="${ctx}/static/admin/vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="${ctx}/static/admin/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="${ctx}/static/admin/vendors/nprogress/nprogress.css" rel="stylesheet">
    <link href="${ctx}/static/admin/vendors/iCheck/skins/flat/green.css" rel="stylesheet">
    <!-- Datatables -->
    <link href="${ctx}/static/admin/vendors/datatables.net-bs/css/dataTables.bootstrap.min.css" rel="stylesheet">
    <link href="${ctx}/static/admin/vendors/datatables.net-buttons-bs/css/buttons.bootstrap.min.css" rel="stylesheet">
    <link href="${ctx}/static/admin/vendors/datatables.net-fixedheader-bs/css/fixedHeader.bootstrap.min.css" rel="stylesheet">
    <link href="${ctx}/static/admin/vendors/datatables.net-responsive-bs/css/responsive.bootstrap.min.css" rel="stylesheet">
    <link href="${ctx}/static/admin/vendors/datatables.net-scroller-bs/css/scroller.bootstrap.min.css" rel="stylesheet">

    <link href="${ctx}/static/vendor/select2/select2.min.css" rel="stylesheet">
    <link href="${ctx}/static/vendor/jedate-6.5.0/skin/jedate.css" rel="stylesheet">

    <!-- Custom Theme Style -->
    <link href="${ctx}/static/admin/css/custom.min.css" rel="stylesheet">
  </head>

  <body class="nav-md">
    <div class="container body">
      <div class="main_container">
        <div class="col-md-3 left_col">
          <div class="left_col scroll-view">
            <div class="navbar nav_title" style="border: 0;">
              <a href="index.html" class="site_title"><i class="fa fa-paw"></i> <span>AI博客平台</span></a>
            </div>

            <div class="clearfix"></div>

            <!-- menu profile quick info -->
            <!-- <div class="profile clearfix">
              <div class="profile_pic">
                <img src="images/img.jpg" alt="..." class="img-circle profile_img">
              </div>
              <div class="profile_info">
                <span>Welcome,</span>
                <h2>John Doe</h2>
              </div>
              <div class="clearfix"></div>
            </div> -->
            <!-- /menu profile quick info -->

            <br />

            <!-- sidebar menu -->
            <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
              <div class="menu_section">
               <!--  <h3>General</h3> -->
                <ul class="nav side-menu">
                  <li><a><i class="fa fa-home"></i> 文章管理 <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="${ctx}/admin/article/index" data-part>文章列表</a></li>
                    </ul>
                  </li>
                  <li><a><i class="fa fa-edit"></i> 个人设置 <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="${ctx}/admin/updatepwd" data-part>修改密码</a></li>
                      <li><a href="form_advanced.html">修改个人信息</a></li>
                      
                    </ul>
                  </li>
                
                  
                </ul>
              </div>
             

            </div>
            <!-- /sidebar menu -->

            <!-- /menu footer buttons -->
            
            <!-- /menu footer buttons -->
          </div>
        </div>

        <!-- top navigation -->
        <div class="top_nav">
          <div class="nav_menu">
            <nav>
              <div class="nav toggle">
                <a id="menu_toggle"><i class="fa fa-bars"></i></a>
              </div>

              <ul class="nav navbar-nav navbar-right">
                <li class="">
                  <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                    	${user.nickname}
                    <span class=" fa fa-angle-down"></span>
                  </a>
                  <ul class="dropdown-menu dropdown-usermenu pull-right">
                   
                    
                    
                    <li><a href="${ctx}/admin/logout"><i class="fa fa-sign-out pull-right"></i> 退出</a></li>
                  </ul>
                </li>

                <li role="presentation" class="dropdown">
                  
                  <ul id="menu1" class="dropdown-menu list-unstyled msg_list" role="menu">
                   
                   
                    
                   
                   
                  </ul>
                </li>
              </ul>
            </nav>
          </div>
        </div>
        <!-- /top navigation -->

        <!-- page content -->
        <div class="right_col" role="main">

        </div>
        <!-- /page content -->

        <!-- footer content -->
        <footer>
          <div class="pull-right">
            版权所有，侵权必究
          </div>
          <div class="clearfix"></div>
        </footer>
        <!-- /footer content -->
      </div>
    </div>

    <!-- jQuery -->
    <script src="${ctx}/static/admin/vendors/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap -->
    <script src="${ctx}/static/admin/vendors/bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- FastClick -->
    <script src="${ctx}/static/admin/vendors/fastclick/lib/fastclick.js"></script>
    <!-- NProgress -->
    <script src="${ctx}/static/admin/vendors/iCheck/icheck.min.js"></script>
    <script src="${ctx}/static/admin/vendors/nprogress/nprogress.js"></script>
    <script src="${ctx}/static/admin/vendors/datatables.net/js/jquery.dataTables.min.js"></script>
    <script src="${ctx}/static/admin/vendors/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
    <script src="${ctx}/static/admin/vendors/datatables.net-buttons/js/dataTables.buttons.min.js"></script>
    <script src="${ctx}/static/admin/vendors/datatables.net-buttons-bs/js/buttons.bootstrap.min.js"></script>
    <script src="${ctx}/static/admin/vendors/datatables.net-buttons/js/buttons.flash.min.js"></script>
    <script src="${ctx}/static/admin/vendors/datatables.net-buttons/js/buttons.html5.min.js"></script>
    <script src="${ctx}/static/admin/vendors/datatables.net-buttons/js/buttons.print.min.js"></script>
    <script src="${ctx}/static/admin/vendors/datatables.net-fixedheader/js/dataTables.fixedHeader.min.js"></script>
    <script src="${ctx}/static/admin/vendors/datatables.net-keytable/js/dataTables.keyTable.min.js"></script>
    <script src="${ctx}/static/admin/vendors/datatables.net-responsive/js/dataTables.responsive.min.js"></script>
    <script src="${ctx}/static/admin/vendors/datatables.net-responsive-bs/js/responsive.bootstrap.js"></script>
    <script src="${ctx}/static/admin/vendors/datatables.net-scroller/js/dataTables.scroller.min.js"></script>


    <script src="${ctx}/static/vendor/select2/select2.min.js" type="application/javascript"></script>
    <script src="${ctx}/static/vendor/jedate-6.5.0/dist/jedate.min.js" type="application/javascript"></script>
    <!-- Custom Theme Scripts -->
    <script src="${ctx}/static/admin/js/custom.min.js"></script>
    <script type="application/javascript">
//      $("a[data-part]").click(function (event) {
//       //  event.preventDefault();
//          var href=$(this).attr("href")
//          $.post(href,null,function (data) {
//              $("[role='main']").html(data);
//          },"html")
//          return false;
//      })
      //事件委托 ,daili
      $("body").on("click",'a[data-part]',null,function () {
          var href=$(this).attr("href")
          __goto(href);
          return false;
      });

      function __goto(href){
          $.get(href,null,function (data) {
              $("[role='main']").html(data);
          },"html")
      }
      __goto("${ctx}/admin/article/index")
    </script>
  </body>
</html>
