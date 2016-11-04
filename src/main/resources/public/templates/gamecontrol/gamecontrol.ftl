<#import "/masterTemplate.ftl" as layout />
<@layout.masterTemplate title="">

   <h1>${msg.get("PLANSPIEL_HEADING_CONTROL")}</h1>

<div class="panel panel-default">
  <div class="panel-body">
  <h3>${msg.get("PLANSPIEL_TABLE_HEADING")}</h3>
<div class="table-responsive">
  <table class="table table-striped">
    <thead>
      <tr>
        <th>ID</th>
        <th>Frage</th>
        <th>Aktiv?</th>
        <th>bearbeiten</th>
      </tr>
    </thead>
    <tbody>
		<#list questions>
			<#items as question>
			  <tr>
				<td>${question.getId()}</td>
				<td>${question.getQuestion()}</td>
				<td>
				    <#if question.isActive()>
				        Aktiv
				    <#else>
				        Inaktiv
				    </#if>
				</td>
				<td><a href="/question/${question.getId()}/"><span class="glyphicon glyphicon-pencil"></span></td>

			  </tr>
			</#items>
		<#else>
		  <p>No questions
		</#list>
    </tbody>
  </table>
</div>
</div>
</div>

<div class="panel panel-default">
  <div class="panel-body">
  <h3>${msg.get("PLANSPIEL_NEW_HEADING")}</h3>

    <form id="gameForm" action="/createNewQuestion/" method="post" class="form-horizontal">

        <div class="col-md-8">

            <div class="form-group">
                <label for="newSituation" class="">${msg.get("PLANSPIEL_NEW_SITUATION")}</label>
                <textarea class="form-control" rows="5" name="situation" id="situation" placeholder="${msg.get("PLANSPIEL_NEW_SITUATION")}" required></textarea>

            </div>

            <div class="form-group">
                <label for="question">${msg.get("PLANSPIEL_NEW_QUESTION")}</label>
                <textarea class="form-control" rows="2" name="question" id="question" placeholder="${msg.get("PLANSPIEL_NEW_QUESTION")}" required></textarea>

            </div>

           <div class="form-group">
            <div class="col-md-3">
                <label for="answer1">${msg.get("PLANSPIEL_ANSWER1")}</label>
                <input type="text" name="answer1" id="answer1" class="form-control" placeholder="${msg.get("PLANSPIEL_ANSWER1")}" value="" required>
            </div>
                <div class="col-md-3">
                 <label for="category1">Kategorie 1</label>
                    <select id="category1" name="category1" class="form-control">
                      <optgroup label="Kategorie">
                        <option value="1">${msg.get("PLANSPIEL_CATEGORY_A")}</option>
                        <option value="2">${msg.get("PLANSPIEL_CATEGORY_B")}</option>
                        <option value="3">${msg.get("PLANSPIEL_CATEGORY_C")}</option>
                      </optgroup>
                    </select>
                </div>

                <div class="col-md-3">
                    <label for="answer2">${msg.get("PLANSPIEL_ANSWER2")}</label>
                    <input type="text" name="answer2" class="form-control" placeholder="${msg.get("PLANSPIEL_ANSWER2")}" value="" required>
                </div>
                <div class="col-md-3">
                    <label for="category2">Kategorie 2</label>
                    <select id="category2" name="category2" class="form-control">
                      <optgroup label="Kategorie">
                        <option value="1">${msg.get("PLANSPIEL_CATEGORY_A")}</option>
                        <option value="2">${msg.get("PLANSPIEL_CATEGORY_B")}</option>
                        <option value="3">${msg.get("PLANSPIEL_CATEGORY_C")}</option>
                      </optgroup>
                    </select>
                </div>
            </div>




           <div class="form-group">
                <div class="col-md-3">
                    <label for="answer3">${msg.get("PLANSPIEL_ANSWER3")}</label>
                    <input type="text" name="answer3" class="form-control" placeholder="${msg.get("PLANSPIEL_ANSWER3")}" value="" required>
                </div>
                <div class="col-md-3">
                 <label for="category3">Kategorie 3</label>
                    <select id="category3" name="category3" class="form-control">
                      <optgroup label="Kategorie">
                        <option value="1">${msg.get("PLANSPIEL_CATEGORY_A")}</option>
                        <option value="2">${msg.get("PLANSPIEL_CATEGORY_B")}</option>
                        <option value="3">${msg.get("PLANSPIEL_CATEGORY_C")}</option>
                      </optgroup>
                    </select>
                </div>

                <div class="col-md-3">
                    <label for="answer4">${msg.get("PLANSPIEL_ANSWER4")}</label>
                    <input type="text" name="answer4" class="form-control" placeholder="${msg.get("PLANSPIEL_ANSWER4")}" value="" required>
                </div>
                <div class="col-md-3">
                    <label for="category4">Kategorie 4</label>
                    <select id="category4" name="category4" class="form-control">
                      <optgroup label="Kategorie">
                        <option value="1">${msg.get("PLANSPIEL_CATEGORY_A")}</option>
                        <option value="2">${msg.get("PLANSPIEL_CATEGORY_B")}</option>
                        <option value="3">${msg.get("PLANSPIEL_CATEGORY_C")}</option>
                      </optgroup>
                    </select>
                </div>
            </div>

            <div class="form-group">
            <div class="col-md-3">
                <label for="answer5">${msg.get("PLANSPIEL_ANSWER5")}</label>
                <input type="text" name="answer5" class="form-control" placeholder="${msg.get("PLANSPIEL_ANSWER5")}" value="" required>
            </div>
                <div class="col-md-3">
                 <label for="category1">Kategorie 5</label>
                    <select id="category5" name="category5" class="form-control">
                      <optgroup label="Kategorie">
                        <option value="1">${msg.get("PLANSPIEL_CATEGORY_A")}</option>
                        <option value="2">${msg.get("PLANSPIEL_CATEGORY_B")}</option>
                        <option value="3">${msg.get("PLANSPIEL_CATEGORY_C")}</option>
                      </optgroup>
                    </select>
                </div>
            </div>


            <div class="form-group">
               <label for="active">${msg.get("PLANSPIEL_IS_ACTIVE")}</label>
               <input type="checkbox" name="active" id="active" class="" placeholder="${msg.get("PLANSPIEL_IS_ACTIVE")}" value="true" required>
            </div>

        </div>

        <div class="col-md-4">

        <div class="list-group">
          <a href="#" class="list-group-item disabled">
            Lernmittel
          </a>
          <a href="#" class="list-group-item">File1</a>
        </div>

        <div class="form-group">
            <div class="fileUploads">
                <label for="fileUpload">Dateiupload</label>
                <input type="file" class="form-control" id="fileUpload" >
                <small id="fileUpload" class="form-text text-muted">Noch nicht funktionsfähig.</small>
            </div>
        </div>

        </div>
        <div class="col-md-6">
            <div class="form-group">
                <input type="submit" class="btn btn-primary" value="${msg.get("PLANSPIEL_SAVE")}">
            </div>
        </div>
        </div>
    </div>




</form>

</@layout.masterTemplate>