<#import "/masterTemplate.ftl" as layout />
<@layout.masterTemplate title="Login">

  hello world login

  <form id="loginForm" method="post">
      <#if authenticationFailed??>
          <p class="btn-lg btn-danger bad notification">${msg.get("LOGIN_AUTH_FAILED")}</p>
      <#elseif authenticationSucceeded??>
          <p class="btn-lg btn-success good notification">${msg.get("LOGIN_AUTH_SUCCEEDED")} ${currentUser}</p>
      <#elseif loggedOut??>
          <p class="btn-lg btn-warning notification">${msg.get("LOGIN_LOGGED_OUT")}</p>
      </#if>
      <h1>${msg.get("LOGIN_HEADING")} - ${msg.get("LOGIN_HEADING")}</h1>
      <p>${msg.get("LOGIN_INSTRUCTIONS")} .... ${WebPath}/></p>

      <div class="form-group">
          <label for="username">${msg.get("LOGIN_LABEL_USERNAME")}</label>
          <input type="text" name="username"  class="form-control" placeholder="${msg.get("LOGIN_LABEL_USERNAME")}" value="" required>
      </div>

      <div class="form-group">
          <label for="password">${msg.get("LOGIN_LABEL_PASSWORD")}</label>
          <input type="password" name="password" class="form-control" placeholder="${msg.get("LOGIN_LABEL_PASSWORD")}" value="" required>
          <#if loginRedirect??>
              <input type="hidden" name="loginRedirect" value="$loginRedirect">
          </#if>
      </div>
      <div class="form-group">
          <input type="submit" class="btn btn-primary" value="${msg.get("LOGIN_BUTTON_LOGIN")}">
      </div>
  </form>


</@layout.masterTemplate>