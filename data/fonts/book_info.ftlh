<!DOCTYPE html>
<html>
<head>
    <title>Book Details</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h1 class="mt-5">Book Details</h1>
    <div class="card mt-4">
        <div class="card-body">
            <h5 class="card-title">Title: ${book.title}</h5>
            <h6 class="card-subtitle mb-2 text-muted">Author: ${book.author}</h6>
            <h6 class="card-subtitle mb-2 text-muted">Year: ${book.data}</h6>
            <h6 class="card-subtitle mb-2 text-muted">Status:${book.status}</h6>
            <h6 class="card-subtitle mb-2 text-muted">Employee ID:${book.employeeId}</h6>
            <p class="card-text">Description: ${book.description}</p>
        </div>
    </div>
</div>ъ
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>