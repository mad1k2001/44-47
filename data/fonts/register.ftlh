<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Login form</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>

<body>
<main class="container">
    <form action="/register" method="post" class="border p-3 bg-white mx-auto mt-5" style="max-width: 400px;">
        <div class="text-center mb-4">
            <h2>Welcome!</h2>
        </div>
        <div class="mb-3">
            <label for="user-email" class="form-label">Email</label>
            <input type="email" name="email" id="user-email" class="form-control" placeholder="Your email" required autofocus>
        </div>
        <div class="mb-3">
            <label for="user-password" class="form-label">Password</label>
            <input type="password" name="password" id="user-password" class="form-control" placeholder="Your password" required>
        </div>
        <div class="mb-3 text-center">
            <span class="d-block">One more step to go</span>
        </div>
        <div class="mb-3 text-center">
            <button class="btn btn-primary" type="submit">Login</button>
        </div>
    </form>
</main>
</body>

</html>
