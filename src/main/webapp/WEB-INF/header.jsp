<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header class="mb-4">
    <nav class="navbar navbar-expand-md navbar-dark bg-dark">
        <div class="container-fluid">

            <img src="${pageContext.request.contextPath}/images/logo.png">

            <a class="navbar-brand" href="#">The Blog</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse"
                    aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarCollapse">
                <ul class="navbar-nav me-auto mb-2 mb-md-0">

                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="posts">Home</a>
                    </li>

                    <c:choose>
                        <c:when test="${empty sessionScope.username}">
                            <li class="nav-item">
                                <a class="nav-link active" aria-current="page" href="login">Login</a>
                            </li>
                        </c:when>

                        <c:otherwise>
                            <li class="nav-item">
                                <a class="nav-link active" aria-current="page" href="add-post">Add post</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </ul>

                <c:if test="${not empty sessionScope.username}">
                    <div class="text-white me-3">Welcome ${sessionScope.username} !</div>

                    <form class="d-flex m-0" action="${pageContext.request.contextPath}/user/logout" method="get">
                        <button class="btn btn-outline-danger" type="submit">Logout</button>
                    </form>
                </c:if>

            </div>
        </div>
    </nav>
</header>
