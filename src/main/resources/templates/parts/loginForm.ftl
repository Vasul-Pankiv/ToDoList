<#macro login path isRegisterForm>
<form action="${path}" method="post">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label"> User Name :</label>
        <div class="col-sm-6">
            <input type="text" name="username"
                   class="form-control"
                   placeholder="User name"/>

        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label"> Password :</label>
        <div class="col-sm-6">
            <input type="password" name="password"
                   class="form-control"
                   placeholder="Password"/>
        </div>
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <#if !isRegisterForm><a href="registration">Add new user</a></#if>
    <button class="btn btn-primary" type="submit" ><#if isRegisterForm>Sign up <#else>Sign in </#if></button>
</form>
</#macro>
<#macro logout>
    <div>
        <form action="/logout" method="post">
            <button class="btn btn-primary" type="submit" >Sign out</button>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        </form>
    </div>
</#macro>