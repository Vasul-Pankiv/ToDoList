<#include "security.ftl">
<#list tasks as task>
    <div class="card my-3" >
        <div class="card-body">
            <h5 class="card-title">${task.title}</h5>
            <h6 class="card-subtitle mb-2 text-muted">#${task.tag}</h6>
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
    Task List is Empty
</#list>