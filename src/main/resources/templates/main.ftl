<#import "parts/common.ftl" as c>
<#import "parts/loginForm.ftl" as l>

<@c.page>
    <@l.logout/>
    <div class="form-row">
        <div class="form-group col-md-6">
            <form method="get" action="/main" class="form-inline">
                <input type="text" name="filter" class="form-control mr-3" placeholder="Search by tag" value="${filter?ifExists}"/>
                <button class="btn btn-primary" type="submit" >Search</button>
            </form>
        </div>
    </div>
    <form method="post" enctype="multipart/form-data">
        <div class="form-group">
            <input type="text" name="text" class="form-control " placeholder="input message"/>
        </div>
        <div class="form-group">
            <input type="text" name="tag" class="form-control" placeholder="input tag"/>
        </div>
        <div class="form-group">
            <button class="btn btn-primary" type="submit">add</button>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        </div>
    </form>
    <#list tasks as task>
        <div class="card my-3" >
            <div class="m-2">
                <span>${task.text}</span>
                <i>${task.tag}</i>
            </div>
            <div class="card-footer text-muted">
                ${task.author.username}
            </div>

        </div>
    <#else>
        Task List is Empty
    </#list>
</@c.page>