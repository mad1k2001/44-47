<!DOCTYPE html>
<html>
<head>
    <title>Book Details</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .book-image {
            max-width: 200px;
            max-height: 300px;
            margin-right: 20px;
            float: left;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }
        .card {
            margin-top: 20px;
        }
        .card-body {
            padding: 20px;
        }
        .card-title {
            font-size: 24px;
            font-weight: bold;
        }
        .card-subtitle {
            font-size: 18px;
            color: #6c757d;
        }
        .card-text {
            font-size: 16px;
        }
        .btn {
            font-size: 16px;
            margin-top: 10px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1 class="mt-5">Book Details</h1>
    <div class="card mt-4">
        <div class="card-body">
            <div class="col-md-4">
                <img src="${book.imageUrl}" class="card-img-top book-image" alt="Book Image">
            </div>
            <h5 class="card-title">${book.title}</h5>
            <h6 class="card-subtitle mb-2 text-muted">Author: ${book.author}</h6>
            <h6 class="card-subtitle mb-2 text-muted">Year: ${book.data}</h6>
            <h6 class="card-subtitle mb-2 text-muted">Status: ${book.status}</h6>
            <p class="card-text">${book.description}</p>
            <#if book.status != "ISSUED">
                <form action="/books/issue" method="POST">
                    <input type="hidden" name="bookId" value="${book.id}">
                    <button type="submit" class="btn btn-dark">Issue Book</button>
                </form>
            <#else>
                <form action="/books/return" method="POST">
                    <input type="hidden" name="bookId" value="${book.id}">
                    <button type="submit" class="btn btn-dark">Return Book: ${book.title}</button>
                </form>
            </#if>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
