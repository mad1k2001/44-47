<!-- journal.ftlh -->
<!DOCTYPE html>
<html>
<head>
    <title>Journal</title>
</head>
<body>
    <h1>Journal</h1>
    <table>
        <tr>
            <th>ID</th>
            <th>Employee</th>
            <th>Book</th>
            <th>Status</th>
            <th>Date</th>
        </tr>
        <#list journals as journal>
            <tr>
                <td>${journal.id}</td>
                <td>${journal.employee.firstName} ${journal.employee.lastName}</td>
                <td>${journal.book.title}</td>
                <td>${journal.status}</td>
                <td>${journal.date}</td>
            </tr>
        </#list>
    </table>
</body>
</html>
