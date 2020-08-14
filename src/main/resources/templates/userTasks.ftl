<#import "parts/common.ftl" as c>
<#import "parts/loginForm.ftl" as l>

<@c.page>
    <#if isCurrentUser>
        <#include "parts/taskEdit.ftl">
    </#if>
    <#include "parts/taskList.ftl">
</@c.page>