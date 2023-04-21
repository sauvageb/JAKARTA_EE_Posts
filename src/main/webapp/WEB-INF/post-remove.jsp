<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>The Blog | Post Confirmation</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>
<body>

<c:import url="header.jsp"></c:import>

<div class="container">
    <h1>Deletion confirmation</h1>

    <div class="row">
        <div class="col-4 offset-4">
            <div class="card p-5 justify-content-center">
                <p class="text-center">Are you sure you want to delete?</p>
                <form class="d-flex d-flex justify-content-center"
                      action="${pageContext.request.contextPath}/post-remove?id=${post.id}" method="post">
                    <button name="choice" value="yes" class="btn btn-danger me-5">Yes</button>
                    <button name="choice" value="no" class="btn btn-primary">No</button>
                </form>
            </div>
        </div>
    </div>
</div>
</div>


</body>
</html>
