<!DOCTYPE html>
<html>
<head>
    <title>Информация о книге</title>
</head>
<body>
<h1>Информация о книге</h1>
<p><strong>Название:</strong> ${book.title}</p>
<p><strong>Автор:</strong> ${book.author}</p>
<p><strong>Статус:</strong> <#if book.issued> Выдана <#else> Не выдана </#if></p>
<#if book.issued>
    <p><strong>Выдана кому:</strong> ${book.issuedTo}</p>
</#if>
</body>
</html>
