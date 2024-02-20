<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Список книг сотрудника</title>
</head>
<body>
<h1>Список книг сотрудника ${employee.getName()}</h1>
<h2>Взятые книги:</h2>
<ul>
    <#list employee.getCurrentBooks() as book>
        <li>${book.getTitle()} (${book.getAuthor()})</li>
    </#list>
</ul>
<h2>Ранее взятые книги:</h2>
<ul>
    <#list employee.getPreviousBooks() as book>
        <li>${book.getTitle()} (${book.getAuthor()})</li>
    </#list>
</ul>
</body>
</html>
