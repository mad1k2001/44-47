<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${book.name}</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <h1>${book.name}</h1>
    <p><strong>Author:</strong> ${book.author}</p>
    <p><strong>Status:</strong> <#if book.issued>Выдана<#else>Не выдана</#if></p>
    <#if book.issued>
        <p><strong>Issued To:</strong> ${book.issuedTo}</p>
    </#if>
    <a href="/books">Back to Book List</a>
</div>
</body>
</html>
