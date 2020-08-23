<#import "parts/common.ftl" as c>
<#import "parts/loginForm.ftl" as l>

<@c.page>
    <div class="form-row">
        <div class="form-group col-md-6">
            <form method="get" action="/task-list" class="form-inline">
                <input type="text" name="filter" class="form-control mr-3" placeholder="Search by tag" value="${filter?ifExists}"/>
                <button class="btn btn-primary" type="submit" >Search</button>
            </form>
        </div>
    </div>
   <#include "parts/taskEdit.ftl">
   <#include "parts/taskList.ftl">
</@c.page>