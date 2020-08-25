<#macro profile isEditEnable>
    <form method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> User Name :</label>
            <div class="col-sm-6">
                <input type="text" name="username"
                       class="form-control ${(usernameError??)?string('is-invalid','')}"
                       placeholder="User name"
                       value="${user.username!""}"
                       <#if !isEditEnable>readonly</#if>/>
                <#if usernameError??>
                    <div class="invalid-feedback">
                        ${usernameError}
                    </div>
                </#if>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Password :</label>
            <div class="col-sm-6">
                <input type="password" name="password" class="form-control ${(passwordError??)?string("is-invalid","")}"
                       placeholder="Password"
                       <#if !isEditEnable>readonly</#if>/>
                <#if passwordError??>
                    <div class="invalid-feedback">
                        ${passwordError}
                    </div>
                </#if>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Email :</label>
            <div class="col-sm-6">
                <input type="email" name="email" class="form-control ${(emailError??)?string("is-invalid","")}"
                       placeholder="${user.email!""}"
                       value="${user.email!""}"
                       <#if !isEditEnable>readonly</#if>/>
                <#if emailError??>
                    <div class="invalid-feedback">
                        ${emailError}
                    </div>
                </#if>
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