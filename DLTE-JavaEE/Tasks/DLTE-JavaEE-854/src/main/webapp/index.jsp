<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Landing Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>

<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand text-info display-6 text-uppercase" style="font-weight: bold;" href="#">MyBank</a>
    </div>
</nav>
<div class="container">
    <div class="row justify-content-center">
       <form method="post" action="login" class="col-lg-3 col-md-6 col-12 align-self-center p-5 rounded-5 shadow card ">
            <h3>Login</h3>
            <div class="form group">
                <label>Username</label>
                <input type="text" name="username" id="username" class="form-control" placeholder="Username" />
            </div>
            <div class="form group">
                <label>Password</label>
                <input type="password" name="password" id="password" class="form-control" placeholder="Password" />
            </div>
            <div class="m-5 row justify-content-around">
                <button id="login" type="submit" class="col-3 btn btn-outline-primary bi bi-door-closed-fill"></button>
                <button id="cancel" type="reset" class="col-3 btn btn-outline-dark bi bi-x-circle-fill"></button>
            </div>
       </form>
    </div>
</div>
</body>

</html>
