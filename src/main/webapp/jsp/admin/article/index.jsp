<%--
  Created by IntelliJ IDEA.
  User: wdg
  Date: 2018/5/26
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${request.contextPath }"></c:set>
<div class="">
    <div class="page-title">
        <div class="title_left">
            <h3>文章列表</h3>
        </div>

        <div class="title_right">
            <%--<div class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search">--%>
                <%--<div class="input-group">--%>
                    <%--<input type="text" class="form-control" placeholder="Search for...">--%>
                    <%--<span class="input-group-btn">--%>
                      <%--<button class="btn btn-default" type="button">Go!</button>--%>
                    <%--</span>--%>
                <%--</div>--%>
            <%--</div>--%>
        </div>
    </div>

    <div class="clearfix"></div>

    <div class="row">
        <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel">
                <div class="x_title">
                    <%--<h2>Plain Page</h2>--%>
                    <div>
                        <form id="searchForm" class="form-inline" >
                            <div class="form-group">
                                <label for="title">标题:</label>
                                <input type="text" name="title" class="form-control" id="title" placeholder="请输入标题">
                            </div>
                            <div class="form-group">
                                <label for="startDate">发布日期:</label>
                                <input type="text" name="startDate" class="form-control" id="startDate" placeholder="请选择开始日期">
                                <input type="text" name="endDate" class="form-control" id="endDate" placeholder="请选择截止日期">
                            </div>

                            <button type="submit" class="btn btn-default">查找</button>
                            <a class="btn btn-default"  href="${ctx}/admin/article/add" data-part>发表文章</a>
                        </form>

                    </div>

                    <ul class="nav navbar-right panel_toolbox">
                        <%--<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>--%>
                        <%--</li>--%>

                    </ul>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">


                    <table id="datatable-responsive" class="table table-striped table-bordered dt-responsive nowrap" cellspacing="0" width="100%">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>标题</th>
                            <th>摘要</th>
                            <th>作者</th>
                            <th>发表日期</th>
                            <th>操作</th>

                        </tr>
                        </thead>

                    </table>

                </div>
            </div>
        </div>
    </div>
</div>
<script type="application/javascript">

    jeDate("#startDate",{
        format:"YYYY-MM-DD",
        isTime:false,
        minDate:"2000-01-01 00:00:00",
        festival:true,
        onClose:false
    })

    jeDate("#endDate",{
        format:"YYYY-MM-DD",
        isTime:false,
        minDate:"2000-01-01 00:00:00",
        festival:true,
        onClose:false
    })

    var table=$('#datatable-responsive').DataTable({
        "paging":true,
        "processing": true,
        "serverSide": true,
        "ordering": false,
        "searching": false,
        "ajax": {
            "url": "${ctx}/admin/article/list",
            "data":function (paramObj) {
                var arr=$("#searchForm").serializeArray();
                for(var i=0;i<arr.length;i++){
                    var param=arr[i];
                    paramObj[param.name]=param.value;
                }
            }
        },
        "columns": [
            {
                "render": function ( data, type, row ,meta) {
                    //return  meta.row.index()+1;
                    console.info("meta:"+meta)
                    return meta.settings._iDisplayStart+meta.row+1;
                },
            },
            { "data": "title",
                "render": function ( data, type, row ,meta) {
                    var str="<a class='' href='#' style='text-decoration: underline'> "+row.title+"</a>"
                    return str;
                }
            },
            { "data": "summary" },
            { "data": "author" },
            { "data": "createdDate" },
            { "render": function ( data, type, row ,meta) {
                    var str="<a class='btn btn-default' data-edit-btn='"+row.id+"'> 编辑</a><a class='btn btn-danger'>删除</a>"
                   return str;
                }
            }
        ],
    });

    $("#searchForm").submit(function () {

        table.ajax.reload();
        return false;
    })

  $("#datatable-responsive").on("click","a[data-edit-btn]",null,function(){
      console.log("进来了");
     var articleId =  $(this).attr("data-edit-btn");
      __goto("${ctx}/admin/article/edit?id="+articleId);
      return false;
  })

</script>