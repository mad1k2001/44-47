<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Список сотрудников</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h1 class="mt-5">Список сотрудников</h1>
    <table class="table mt-4">
        <thead>
        <tr>
            <th>#</th>
            <th>Имя</th>
            <th>Взятые книги</th>
        </tr>
        </thead>
        <tbody>
        <#list employees as employee>
            <tr>
                <td>${employee?counter}</td>
                <td>${employee.getName()}</td>
                <td>
                    <ul class="list-unstyled">
                        <#list employee.getIssuedBooks() as book>
                            <li>${book.getName()} (${book.getAuthor()})</li>
                        </#list>
                    </ul>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
