<!DOCTYPE html>
<html>
<head>
    <title>${book.title}</title>
</head>
<body>
<h1>${book.title}</h1>
<p><strong>Автор:</strong> ${book.author}</p>
<p><strong>Статус:</strong> ${book.issued!"Выдана" : "В наличии"}</p>
<#if book.issued>
    <p><strong>Выдана:</strong> ${book.issuedTo}</p>
</#if>
<a href="/books">Назад к списку книг</a>
</body>
</html>
