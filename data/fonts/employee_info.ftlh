<!DOCTYPE html>
<html>
<head>
    <title>Employee Details</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h1>Employee Details</h1>
    <div class="card">
        <div class="card-body">
            <p class="card-text"><strong>ID:</strong> ${employee.id}</p>
            <p class="card-text"><strong>Name:</strong> ${employee.firstName} ${employee.lastName}</p>
            <p class="card-text"><strong>Email:</strong> ${employee.email}</p>
            </ul>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
