<#include "security.ftl">
<#list tasks as task>
    <div class="card-columns">
    <div class="card border-primary mb-3 my-3">
        <div class="card-header bg-primary ">#${task.tag}</div>
        <div class="card-body">
            <h5 class="card-title">${task.title}</h5>
            <p class="card-text">${task.description}</p>
        </div>
        <div class="card-footer text-muted">
            <a href="/task-list/${task.author.id}" class="card-link">${task.author.username}</a>
            <#if task.author.id == currentUserId>
                <a href="/task-list/${task.author.id}?task=${task.id}" class="card-link">
                    Edit
                </a>
            </#if>
        </div>

    </div>
<#else>
    <div class="alert alert-primary" role="alert">
        Task list is empty
    </div>
    </div>
</#list>