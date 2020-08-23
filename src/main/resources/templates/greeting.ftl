<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">

<@c.page>
    <h5>Hello, <#if currentUser??>${name} <#else> guest</#if></h5>
    <div>
        Its my first web application))<br>
        <#if !currentUser??>
            <a href="/login">Log in</a>
            <a href="/registration">Sign up</a>
        </#if>

    </div>

</@c.page>