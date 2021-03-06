<#import "/masterTemplate.ftl" as layout />
<@layout.masterTemplate title="">

<h1>${msg.get("USER_HEADING_CONTROL")}</h1>
<h2>${msg.get("USER_UPDATE")}</h2>

<form action="/updateUser/" id="userForm" method="post">
<div class="error">

</div>
    <div class="form-group">
        <label for="username">${msg.get("USER_USERNAME")}</label>
        <input type="text" name="username"  class="form-control" placeholder="${msg.get("USER_USERNAME")}" value="${user.getUsername()}" readonly>
    </div>

    <div class="form-group">
        <label for="password">${msg.get("USER_FIRSTNAME")}</label>
        <input type="text" name="firstname" class="form-control" placeholder="${msg.get("USER_FIRSTNAME")}" value="${user.getFirstname()}" required>
    </div>


   <div class="form-group">
        <label for="password">${msg.get("USER_LASTNAME")}</label>
        <input type="text" name="lastname" class="form-control" placeholder="${msg.get("USER_LASTNAME")}" value="${user.getLastname()}" required>
    </div>

    <div class="form-group">
        <label for="password">${msg.get("USER_PASSWORD")}</label>
        <input type="password" name="password" id="password" class="form-control" placeholder="${msg.get("USER_PASSWORD_UPDATE")}" value="">
    </div>

    <div class="form-group">
        <label for="password">${msg.get("USER_PASSWORD_CONFIRM")}</label>
        <input type="password" name="passwordConfirmed" id="passwordConfirmed" class="form-control" placeholder="${msg.get("USER_PASSWORD_CONFIRM_UPDATE")}" value="">
    </div>

    <div class="custom-controls-stacked">

      <label class="custom-control custom-radio">
        <input id="role" name="role" type="radio" class="custom-control-input" value="1" <#if user.getRole() == 1> checked </#if>>
        <span class="custom-control-indicator"></span>
        <span class="custom-control-description">${msg.get("USER_ADMIN")}</span>
      </label>


      <label class="custom-control custom-radio">
        <input id="role" name="role" type="radio" class="custom-control-input" value="2" <#if user.getRole() == 2> checked </#if>>
        <span class="custom-control-indicator"></span>
        <span class="custom-control-description">${msg.get("USER_PARTICIPANT")}</span>
      </label>



    </div>

    <div class="form-group">
        <input type="submit" name="submit" id="submit" class="btn btn-primary" value="${msg.get("USER_UPDATE")}">
    </div>
</form>


<script>
$(document).ready(function(){
	$("#submit").click(function(){
		var pw = $("#password").val();
		var pwConfirmed = $("#passwordConfirmed").val();

		 if (pw.length != 0 && (pw != pwConfirmed || pw.length <6 )) {
			event.preventDefault();

			$(".error").html("<p class='btn-lg btn-danger bad notification'>Passwörter ungleich oder zu kurz</p>");
		 }
	});

});
</script>


</@layout.masterTemplate>