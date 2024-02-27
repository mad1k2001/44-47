<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Coockies Sample Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
<div class="container mt-5">
    <h1>Visited ${times} <#if times==1>time<#else>times</#if></h1>

    <#if globalVisit??>
        <h2>Global visit counter ${globalVisit} <#if globalVisit==1>time<#else>times</#if> (until server restart)</h2>
    </#if>

    <#if cookies??>
        <ul class="list-group mt-3">
            <#list cookies as name, value>
                <li class="list-group-item">
                    <b>${name}</b> - <span class="badge bg-secondary">${value}</span>
                </li>
            </#list>
        </ul>
    </#if>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>