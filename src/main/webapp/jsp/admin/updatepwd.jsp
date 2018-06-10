<%--
  Created by IntelliJ IDEA.
  User: wdg
  Date: 2018/6/5
  Time: 21:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${request.contextPath }"></c:set>

    <form role="form" id="pwdform">
        <div class="form-group">
            <label for="oldpwd">原密码</label>
            <input type="password" class="form-control" name="oldpwd" id="oldpwd"  placeholder="请输入原密码">
        </div>
        <div class="form-group">
            <label for="newpwd">新密码</label>
            <input type="password" class="form-control" name="newpwd" id="newpwd"  placeholder="请输入新密码">
        </div>
        <div class="form-group">
            <label for="confirmpwd">确认密码</label>
            <input type="password" class="form-control" name="confirmpwd" id="confirmpwd"  placeholder="确认密码">
        </div>

        <button type="submit" class="btn btn-default">提交</button>
    </form>


<script>
    $("#pwdform").submit(function () {
       $.post("${ctx}/admin/changepwd",$(this).serialize(),function(data){
           if(data.status!= null && data.status != 100){
               alert(data.message);
           }else {
               alert("密码修改成功，请重新登陆！！");
               window.location="${ctx}/login";
           }
       },"json")
        return false;
    })
</script>

