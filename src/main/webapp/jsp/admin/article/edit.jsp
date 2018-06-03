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
<script type="text/javascript" src="${ctx}/static/admin/vendors/ueditor/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" src="${ctx}/static/admin/vendors/ueditor/ueditor.all.js"></script>

<div class="">
    <div class="page-title">
        <div class="title_left">
            <h3>文章列表&gt;发表文章</h3>
        </div>

        <div class="title_right">
            <div class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="Search for...">
                    <span class="input-group-btn">
                      <button class="btn btn-default" type="button">Go!</button>
                    </span>
                </div>
            </div>
        </div>
    </div>

    <div class="clearfix"></div>

    <div class="row">
        <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel">
                <form id="articleForm" class="form-horizontal" action="/action_page.php">
                    <input type="hidden" name="id" value="${article.id}">
                   <%-- <input type="hidden" name="updatedDate" value="${article.createdDate}">--%>
                <div class="x_title">



                </div>
                <div class="x_content">

                    <div class="form-group">
                        <label class="control-label col-sm-2" for="title">标题:</label>
                        <div class="col-sm-10">
                            <input name="title" type="text" class="form-control" id="title" placeholder="请输入标题" value="${article.title}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="summary">摘要:</label>
                        <div class="col-sm-10">
                            <textarea name="summary" class="form-control" id="summary" placeholder="请输入摘要" >${article.summary}</textarea>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-2" for="content">内容:</label>
                        <div class="col-sm-10" style="height: 45em;overflow:  auto;">
                            <%--<textarea class="form-control" id="content" placeholder="请输入内容"></textarea>--%>
                                <textarea id="content" name="content" type="text/plain">${article.content}</textarea>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-2" for="tags">标签:</label>
                        <div class="col-sm-10">
                            <select id="tags" class="js-example-basic-single" name="tags" multiple="multiple" style="width: 100%">
                                <c:forEach items="${tagList}" var="tag" >
                                    <option   selected value="${tag.id}">${tag.name}</option>
                                </c:forEach>
                            </select>

                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-2" for="indate">发表日期:</label>
                        <div class="col-sm-10">
                            <c:set var = "date" ><fmt:formatDate value="${article.createdDate}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate> </c:set>
                            <input name="createdDate" id="indate" type="text" placeholder="请选择"  readonly value="${date}">
                        </div>
                    </div>


                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="submit" class="btn btn-default">发表</button>
                            </div>
                        </div>

                </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    var ue = UE.getEditor('content',{
        UEDITOR_HOME_URL:'${ctx}/static/admin/vendors/ueditor/',
        serverUrl:'${ctx}/jsp/admin/ueditor/controller.jsp'
    });


    $('.js-example-basic-single').select2({
        tags: true,
        ajax: {
            url: '${ctx}/admin/tag/list',
            dataType: 'json'
            // Additional AJAX parameters go here; see the end of this chapter for the full code of this example
        }
    });

    jeDate("#indate",{
        format:"YYYY-MM-DD hh:mm:ss",
        isTime:true,
        minDate:"2000-01-01 00:00:00",
        festival:true,
        onClose:false
    })

    $("#articleForm").submit(function () {
        $.post("${ctx}/admin/article/editSave",$(this).serialize(),function (data) {
            if(data.status!= null && data.status != 100){
                alert(data.message);
            }else{
                alert("提交成功");
                __goto("${ctx}/admin/article/edit?id=${article.id}");
            }

        },"json");

        return false;
    })
</script>