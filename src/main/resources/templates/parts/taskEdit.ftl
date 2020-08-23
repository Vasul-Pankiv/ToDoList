<a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
   aria-controls="collapseExample">
    Task Editor
</a>
<div class="collapse <#if task??>show</#if>" id="collapseExample">
    <div class="form-group  mt-3">
        <form method="post" enctype="multipart/form-data">
            <div class="form-group">
                <input type="text" name="title" class="form-control " placeholder="Title"
                       value="<#if task??>${task.title}</#if>"/>
            </div>
            <div class="form-group">
                <textarea name="description" class="form-control" id="exampleFormControlTextarea1" rows="3"
                          placeholder="description"><#if task??>${task.description}</#if></textarea>
            </div>
            <div class="form-group">
                <input type="text" name="tag" class="form-control" placeholder="tag"
                       value="<#if task??>${task.tag}</#if>"/>
            </div>
            <div class="form-group">
                <button class="btn btn-primary" type="submit">save</button>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            </div>
        </form>
    </div>
</div>