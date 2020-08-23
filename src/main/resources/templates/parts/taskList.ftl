<#include "security.ftl">

<div class="card-columns">
    <#list tasks as task>
    <div class="card border-primary mb-3 my-3">
        <div class="card-header bg-primary">
            #${task.tag}
        </div>
        <div class="card-body">
            <h5 class="card-title">${task.title}</h5>
            <p class="card-text">${task.description}</p>
        </div>
        <div class="card-footer text-muted  d-flex justify-content-between">
            <a href="/task-list?user=${task.author.id}" class="card-link">${task.author.username}</a>
            <#if task.author.id == currentUserId || isAdmin>
                <div>
                    <a href="/task-list?user=${task.author.id}&task=${task.id}" class="card-link">
                        Edit
                    </a>
                    <a href="/task-list/delete?user=${userId?if_exists}&task=${task.id}" class="btn py-0 btn-outline-dark">delete</a>
                </div>
            </#if>
        </div>
    </div>
    <#else>
</div>
<div class="alert alert-primary" role="alert">
    Task list is empty

    </#list>
</div>

