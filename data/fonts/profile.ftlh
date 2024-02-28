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
            <h2 class="card-title">Current Books</h2>
            <ul class="list-group">
                <#if employee.currentBooks?size != 0>
                    <#list employee.currentBooks as book>
                        <li class="list-group-item">
                            <strong>Title:</strong> ${book.title}<br>
                            <strong>Author:</strong> ${book.author}<br>
                            <strong>Description:</strong> ${book.description}<br>
                            <strong>Status:</strong> ${book.status}
                        </li>
                    </#list>
                <#else>
                    <li class="list-group-item">No current books</li>
                </#if>
            </ul>
            <h2 class="card-title">Past Books</h2>
            <ul class="list-group">
                <#if employee.pastBooks?size != 0>
                    <#list employee.pastBooks as book>
                        <li class="list-group-item">
                            <strong>Title:</strong> ${book.title}<br>
                            <strong>Author:</strong> ${book.author}<br>
                            <strong>Description:</strong> ${book.description}<br>
                            <strong>Status:</strong> ${book.status}
                        </li>
                    </#list>
                <#else>
                    <li class="list-group-item">No past books</li>
                </#if>
            </ul>
            <div class="text-center">
                <a href="/employees" class="btn btn-primary mt-2">Back to Employees</a>
                <a href="/books" class="btn btn-secondary mt-2">View Books</a>
            </div>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
