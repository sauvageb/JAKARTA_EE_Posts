<header class="mb-4">
    <nav class="navbar navbar-expand-md navbar-dark bg-dark">
        <div class="container-fluid">

            <img src="https://img.icons8.com/plasticine/64/null/blog.png"/>
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
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="add-post">Add post</a>
                    </li>
                </ul>

                <div class="text-white me-3">Welcome ${sessionScope.username} !</div>

                <form class="d-flex m-0" action="${pageContext.request.contextPath}/logout" method="get">
                    <button class="btn btn-outline-danger" type="submit">Logout</button>
                </form>


            </div>
        </div>
    </nav>
</header>
