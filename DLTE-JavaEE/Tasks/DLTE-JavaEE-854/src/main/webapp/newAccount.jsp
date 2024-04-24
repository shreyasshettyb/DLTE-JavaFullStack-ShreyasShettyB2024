<%--
  Created by IntelliJ IDEA.
  User: xxshetts
  Date: 24-04-2024
  Time: 10:03 AM
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>
<body>
    <% response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
    if(session.getAttribute("username")!=null){ %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand text-info display-6 text-uppercase" style="font-weight: bold;" href="#">MyBank</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                <li class="nav-item m-2">
                    <a href="list" class="buttons  btn btn-outline-dark rounded-5 me-2"><span class="bi bi-list-columns"></span>
                        View</a>
                </li>
                <li class="nav-item m-2">
                    <a href="newAccount.jsp" class=" btn btn-outline-dark rounded-5 me-2"><span
                            class="bi bi-file-earmark-plus"></span> Create Account</a>
                </li>
                <li class="nav-item m-2">
                    <a href="withdraw.jsp" class=" btn btn-outline-dark rounded-5 me-2"><span
                            class="bi bi-file-earmark-plus"></span> Withdraw</a>
                </li>
                <li class="nav-item m-2">
                    <a href="logout" class="logout btn btn-outline-dark rounded-5 me-2 bg-info"><span
                            class="bi bi-door-open"></span>
                        Logout</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

    <%
        String info = (String)request.getAttribute("info");
        String error = (String)request.getAttribute("error");
    %>
    <div class="container">
        <div class="row justify-content-center" style="height: 100vh">
            <% if(info!=null&&info!=""){ %>
            <h1 class="text-center text-success"><%=info%></h1>
            <%}%>
            <% if(error!=null&&error!=""){ %>
            <h1 class="text-center text-danger"><%=error%></h1>
            <%}%>
            <form action="create" method="post" class="col-lg-4 col-md-8 col-12 p-5 rounded-5 shadow-lg align-self-center">
                <div class="form-group">
                    <label>Account Number</label>
                    <input type="number" name="accountNumber" placeholder="Account number" class="form-control" />
                </div>
                <div class="form-group">
                    <label>Customer ID</label>
                    <input type="number" name="customerId" placeholder="Customer ID" class="form-control" />
                </div>
                <div class="form-group">
                    <label>Email</label>
                    <input type="email" name="email" placeholder="Email" class="form-control" />
                </div>
                <div class="form-group">
                    <label>Name</label>
                    <input type="text" name="name" placeholder="Name" class="form-control" />
                </div>
                <div class="form-group">
                    <label>Balance</label>
                    <input type="number" name="balance" placeholder="Balance" class="form-control" />
                </div>
                <div class="form-group">
                    <label>Username</label>
                    <input type="text" name="username" placeholder="Username" class="form-control" />
                </div>
                <div class="form-group">
                    <label>Password</label>
                    <input type="password" name="password" placeholder="Password" class="form-control" />
                </div>
                <div class="row justify-content-around mt-3">
                    <div class="col-2">
                        <button type="submit" class="btn btn-outline-info"><h1 class="bi bi-check-circle-fill"></h1></button>
                    </div>
                    <div class="col-2">
                        <button type="reset" class="btn btn-outline-secondary"><h1 class="bi bi-x-lg"></h1></button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <% }
    else {
        response.sendRedirect("index.jsp");
    }%>
</body>
</html>