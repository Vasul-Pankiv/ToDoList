<#import "parts/common.ftl" as c>

<@c.page>
    <form method="post" enctype="multipart/form-data">
        <div class="form-group">
            <input type="text" name="text" class="form-control " placeholder="input message"/>
        </div>
        <div class="form-group">
            <input type="text" name="tag" class="form-control" placeholder="input tag"/>
        </div>
        <div class="form-group">
            <button class="btn btn-primary" type="submit">add</button>
        </div>
    </form>
    <#list tasks as task>
        <div class="card my-3" >
            <div class="m-2">
                <span>${task.text}</span>
                <i>${task.tag}</i>
            </div>
            <div class="card-footer text-muted">
                ${task.id}
            </div>

        </div>
    <#else>
        Task List is Empty
    </#list>
</@c.page>