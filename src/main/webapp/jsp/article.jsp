<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${request.contextPath }"></c:set>
<!DOCTYPE html>
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>首页</title>

   

  </head>

  <body>

    <!-- Page Content -->
    <div class="container">

      <div class="row">

        <!-- Blog Entries Column -->
        <div class="col-md-8">

         <br />

          <!-- Blog Post -->

	          <div class="card mb-4">
	            <div class="card-body" style="min-height: 26em;">
	              <h2 class="card-title">${article.title}</h2>
	              <p class="card-text" style="font-style:italic">摘要：${article.summary}</p>
	              <div>
                      ${article.content}
                  </div>
	            </div>
	            <div class="card-footer text-muted">
	            <c:set var="dateStr"><fmt:formatDate value="${article.createdDate}" pattern="yyyy-MM-dd HH:mm:ss" /></c:set>
	            	
	              Posted on ${dateStr} by ${article.author}
	            </div>
	          </div>





        </div>

        <!-- Sidebar Widgets Column -->
        <div class="col-md-4">


          <!-- Categories Widget -->
          <div class="card my-4">
            <h5 class="card-header">分类</h5>
            <div class="card-body">
              <div class="row">

               <div class="col-lg-6">
                  <ul class="list-unstyled mb-0">
                   <c:forEach items="${tags}" var="tag" begin="0" step="2" >
                    <li>
                      <a href="${ctx}/bloghome?tagId=${tag.id}">${tag.name}</a>
                    </li>
                   </c:forEach>

                  </ul>
                </div>
                <div class="col-lg-6">
                  <ul class="list-unstyled mb-0">
                    <c:forEach items="${tags}" var="tag" begin="1" step="2">
                    <li>
                      <a href="${ctx}/bloghome?tagId=${tag.id}">${tag.name}</a>
                    </li>
                    </c:forEach>
                  </ul>
                </div>

              </div>
            </div>
          </div>

          <!-- Side Widget -->

        </div>

      </div>
      <!-- /.row -->

    </div>
    <!-- /.container -->

  </body>

</html>
