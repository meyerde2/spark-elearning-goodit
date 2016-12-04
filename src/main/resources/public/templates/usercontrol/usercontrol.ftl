<#import "/masterTemplate.ftl" as layout />
<@layout.masterTemplate title="">

<h1>${msg.get("USER_HEADING_CONTROL")}</h1>

<div class="panel panel-default">
  <div class="panel-body">
<div class="table-responsive">
  <table class="table table-striped">
    <thead>
      <tr>
        <th>ID</th>
        <th>Benutzername</th>
        <th>Vorname</th>
        <th>Name</th>
        <th>Rolle</th>
        <th>bearbeiten?</th>
      </tr>
    </thead>
    <tbody>
	
		<#list users>
		
			<#items as user>
			  <tr>
				<td>${user.getId()}</td>
				<td>${user.getUsername()}</td>
				<td>${user.getFirstname()}</td>
				<td>${user.getLastname()}</td>
				<td>				
					<#if user.getRole() == 1> Admin </#if>
					<#if user.getRole() == 2> Teilnehmer </#if>
				</td>
				<td><#if currentUser != user.getUsername()><a href="/user/${user.getUsername()}/"><span class="glyphicon glyphicon-pencil"></span></a></#if></td>
			  </tr>
			</#items>
		
		<#else>
		
		  <p>No users
		  
		</#list>

    </tbody>
  </table>
</div>
</div>
</div>

<h2>Neuen Benutzer erstellen</h2>

<form action="/createNewUser/" id="userForm" method="post">


<div class="error">

</div>




    <div class="form-group">
        <label for="username">${msg.get("USER_USERNAME")}</label>
        <input type="text" name="username"  class="form-control" placeholder="${msg.get("USER_USERNAME")}" value="" required>
    </div>

    <div class="form-group">
        <label for="password">${msg.get("USER_FIRSTNAME")}</label>
        <input type="text" name="firstname" class="form-control" placeholder="${msg.get("USER_FIRSTNAME")}" value="" required>
    </div>


   <div class="form-group">
        <label for="password">${msg.get("USER_LASTNAME")}</label>
        <input type="text" name="lastname" class="form-control" placeholder="${msg.get("USER_LASTNAME")}" value="" required>
    </div>

    <div class="form-group">
        <label for="password">${msg.get("USER_PASSWORD")}</label>
        <input type="password" id="password" name="password" class="form-control" placeholder="${msg.get("USER_PASSWORD")}" value="" required>
    </div>

    <div class="form-group">
        <label for="password">${msg.get("USER_PASSWORD_CONFIRM")}</label>
        <input type="password" id="passwordConfirmed" name="passwordConfirmed" class="form-control" placeholder="${msg.get("USER_PASSWORD_CONFIRM")}" value="" required>
    </div>

    <div class="custom-controls-stacked">

      <label class="custom-control custom-radio">
        <input id="role" name="role" type="radio" class="custom-control-input" value="1" required>
        <span class="custom-control-indicator"></span>
        <span class="custom-control-description">${msg.get("USER_ADMIN")}</span>
      </label>


      <label class="custom-control custom-radio">
        <input id="role" name="role" type="radio" class="custom-control-input" value="2" >
        <span class="custom-control-indicator"></span>
        <span class="custom-control-description">${msg.get("USER_PARTICIPANT")}</span>
      </label>



    </div>

    <div class="form-group">
        <input type="submit" id="submit" name="submit" class="btn btn-primary" value="${msg.get("USER_CREATE")}">
    </div>
</form>

<script>
$(document).ready(function(){
	$("#submit").click(function(){
		var pw = $("#password").val();
		var pwConfirmed = $("#passwordConfirmed").val();

		 if (pw != pwConfirmed || pw.length <6 ) {
			event.preventDefault();
			
			$(".error").html("<p class='btn-lg btn-danger bad notification'>Passwörter ungleich oder zu kurz</p>");
		 }
	});

});
</script>
</@layout.masterTemplate>