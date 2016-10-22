<#import "/masterTemplate.ftl" as layout />
<@layout.masterTemplate title="Login">

      <h1>${msg.get("LOGIN_HEADING")}</h1>


  <form id="loginForm" method="post">
      <#if authenticationFailed?? && authenticationFailed?c == "true">
          <p class="btn-lg btn-danger bad notification">${msg.get("LOGIN_AUTH_FAILED")}</p>
      <#elseif authenticationSucceeded?? && authenticationSucceeded?c =="true">
          <p class="btn-lg btn-success good notification">${msg.get("LOGIN_AUTH_SUCCEEDED")} ${currentUser}</p>
      <#elseif loggedOut?? && loggedOut?c == "true">

          <p class="btn-lg btn-warning notification">${msg.get("LOGIN_LOGGED_OUT")}</p>
      </#if>

      <p>${msg.get("LOGIN_INSTRUCTIONS")}</p>



      <div class="form-group">
            <label for="username">${msg.get("LOGIN_LABEL_USERNAME")}</label>
            <div class="input-group">
                <span class="input-group-addon">
                <i class="glyphicon glyphicon-user"></i>
                </span>
                <input type="text" name="username"  class="form-control" placeholder="${msg.get("LOGIN_LABEL_USERNAME")}" value="" required>
            </div>
      </div>

      <div class="form-group">
          <label for="password">${msg.get("LOGIN_LABEL_PASSWORD")}</label>
          <div class="input-group">
                <span class="input-group-addon">
                <i class="glyphicon glyphicon-lock"></i>
                </span>
                <input type="password" name="password" class="form-control" placeholder="${msg.get("LOGIN_LABEL_PASSWORD")}" value="" required>
          </div>
          <#if loginRedirect??>
              <input type="hidden" name="loginRedirect" value="${loginRedirect}">
          </#if>
      </div>
      <div class="form-group">
        <button type="submit" class="btn btn-primary btn-md">
            <span class="glyphicon glyphicon-log-in"></span> ${msg.get("LOGIN_BUTTON_LOGIN")}
        </button>
      </div>
  </form>

</@layout.masterTemplate>