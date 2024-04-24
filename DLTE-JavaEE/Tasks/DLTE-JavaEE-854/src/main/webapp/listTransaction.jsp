<%@ page import="org.example.entity.Transaction" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: xxshetts
  Date: 24-04-2024
  Time: 10:02 AM
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
    List<Transaction> cards =(List<Transaction>) request.getAttribute("myCards");
    pageContext.setAttribute("myData",cards,PageContext.APPLICATION_SCOPE);
%>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-lg-4 col-md-8 col-12 table-responsive p-5 shadow-lg">
            <table class="table table-striped text-nowrap">
                <thead>
                <tr>
                    <th>Transaction ID</th><th>Username</th>
                    <th>Date of Transaction</th><th>Amount</th>
                    <th>Balance</th>
                </tr>
                </thead>
                <tbody>
                <%for(Transaction each:cards){%>
                <tr>
                    <td><%out.print(each.getTransactionID());%></td>
                    <td><%out.print(each.getUser());%></td>
                    <td><%out.print(each.getDate());%></td>
                    <td><%out.print(each.getAmount());%></td>
                    <td><%out.print(each.getBalance());%></td>
                </tr>
                <%}%>
                </tbody>
            </table>
        </div>
    </div>
</div>
<% }
else {
    response.sendRedirect("index.jsp");
}%>
</body>
</html>

