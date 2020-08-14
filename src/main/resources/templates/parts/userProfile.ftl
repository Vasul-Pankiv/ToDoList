<#macro profile isEditEnable>
    <h5>${user.username}</h5>
    <form method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Password :</label>
            <div class="col-sm-6">
                <input type="password" name="password" class="form-control" placeholder="Password"
                       <#if !isEditEnable>readonly</#if>/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Email :</label>
            <div class="col-sm-6">
                <input type="email" name="email" class="form-control" placeholder="${user.email!""}"
                       value="${user.email!""}"
                       <#if !isEditEnable>readonly</#if>/>
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <#if isEditEnable>
            <button class="btn btn-primary" type="submit">Save</button>
        <#else>
            <a href="/user/profile/edit" class="btn btn-primary stretched-link">Edit</a>
        </#if>
    </form>
</#macro>