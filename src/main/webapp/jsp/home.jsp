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
          <c:forEach items = "${page.data}" var = "article">
	          <div class="card mb-4">
	            <div class="card-body">
	              <h2 class="card-title">${article.title}</h2>
	              <p class="card-text">${article.summary}</p>
	              <a href="#" class="btn btn-primary">Read More &rarr;</a>
	            </div>
	            <div class="card-footer text-muted">
	            <c:set var="dateStr"><fmt:formatDate value="${article.createdDate}" pattern="yyyy-MM-dd HH:mm:ss" /></c:set>
	            	
	              Posted on ${dateStr} by ${article.author}
	            </div>
	          </div>
           </c:forEach>


          <!-- Pagination -->
          <ul class="pagination justify-content-center mb-4">

            <li class="page-item ">
              <c:set var="newPageLink">href="${ctx}/bloghome?start=${(page.start-page.length)}&tagId=${param.tagId}&length=${param.length}"</c:set>
              <a class="page-link" ${page.pageNum==1?'':newPageLink}>&larr; 上一页 </a>
            </li>
            <li class="page-item">
              <c:set var="oldPageLink">href="${ctx}/bloghome?start=${(page.start+page.length)}&tagId=${param.tagId}&length=${param.length}"</c:set>
              <a class="page-link" ${page.pageNum==page.totalPages?'':oldPageLink}  >下一页&rarr;</a>
            </li>
          </ul>

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
