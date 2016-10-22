<#import "/masterTemplate.ftl" as layout />
<@layout.masterTemplate title="">

<h1>${msg.get("PLANSPIEL_HEADING_CONTROL")}</h1>

<div class="panel panel-default">
  <div class="panel-body">
  <h2>${msg.get("PLANSPIEL_UPDATE_HEADING")}</h2>


    <form id="gameForm" action="/updateQuestion/" method="post" class="form-horizontal">

        <div class="col-md-8">

            <input type="hidden" name="id" id="id" value="${question.getId()}" required>
            <div class="form-group">
                <label for="newSituation" class="">${msg.get("PLANSPIEL_NEW_SITUATION")}</label>
                <textarea class="form-control" rows="5" name="situation" id="situation" placeholder="${msg.get("PLANSPIEL_NEW_SITUATION")}" >${question.getDescription()}</textarea>

            </div>

            <div class="form-group">
                <label for="question">${msg.get("PLANSPIEL_NEW_QUESTION")}</label>
                <textarea class="form-control" rows="2" name="question" id="question" placeholder="${msg.get("PLANSPIEL_NEW_QUESTION")}">${question.getQuestion()}</textarea>

            </div>

           <div class="form-group">
            <div class="col-md-3">
                <label for="answer1">${msg.get("PLANSPIEL_ANSWER1")}</label>
                <input type="text" name="answer1" id="answer1" class="form-control" placeholder="${msg.get("PLANSPIEL_ANSWER1")}" value="${question.getAnswer1()}" required>
            </div>
                <div class="col-md-3">
                 <label for="category1">Kategorie 1</label>
                    <select id="category1" name="category1" class="form-control">
                      <optgroup label="Kategorie">
                        <option value="1" <#if question.getCatId1() == 1> selected </#if>>${msg.get("PLANSPIEL_CATEGORY_A")}</option>
                        <option value="2" <#if question.getCatId1() == 2> selected </#if>>${msg.get("PLANSPIEL_CATEGORY_B")}</option>
                        <option value="3" <#if question.getCatId1() == 3> selected </#if>>${msg.get("PLANSPIEL_CATEGORY_C")}</option>
                      </optgroup>
                    </select>
                </div>

                <div class="col-md-3">
                    <label for="answer2">${msg.get("PLANSPIEL_ANSWER2")}</label>
                    <input type="text" name="answer2" class="form-control" placeholder="${msg.get("PLANSPIEL_ANSWER2")}" value="${question.getAnswer2()}" required>
                </div>
                <div class="col-md-3">
                    <label for="category2">Kategorie 2</label>
                    <select id="category2" name="category2" class="form-control">
                      <optgroup label="Kategorie">
                        <option value="1" <#if question.getCatId2() == 1> selected </#if>>${msg.get("PLANSPIEL_CATEGORY_A")}</option>
                        <option value="2" <#if question.getCatId2() == 2> selected </#if>>${msg.get("PLANSPIEL_CATEGORY_B")}</option>
                        <option value="3" <#if question.getCatId2() == 3> selected </#if>>${msg.get("PLANSPIEL_CATEGORY_C")}</option>
                      </optgroup>
                    </select>
                </div>
            </div>




           <div class="form-group">
                <div class="col-md-3">
                    <label for="answer3">${msg.get("PLANSPIEL_ANSWER3")}</label>
                    <input type="text" name="answer3" class="form-control" placeholder="${msg.get("PLANSPIEL_ANSWER3")}" value="${question.getAnswer3()}" required>
                </div>
                <div class="col-md-3">
                 <label for="category3">Kategorie 3</label>
                    <select id="category3" name="category3" class="form-control">
                      <optgroup label="Kategorie">
                        <option value="1" <#if question.getCatId3() == 1> selected </#if>>${msg.get("PLANSPIEL_CATEGORY_A")}</option>
                        <option value="2" <#if question.getCatId3() == 2> selected </#if>>${msg.get("PLANSPIEL_CATEGORY_B")}</option>
                        <option value="3" <#if question.getCatId3() == 3> selected </#if>>${msg.get("PLANSPIEL_CATEGORY_C")}</option>
                      </optgroup>
                    </select>
                </div>

                <div class="col-md-3">
                    <label for="answer4">${msg.get("PLANSPIEL_ANSWER4")}</label>
                    <input type="text" name="answer4" class="form-control" placeholder="${msg.get("PLANSPIEL_ANSWER4")}" value="${question.getAnswer4()}" >
                </div>
                <div class="col-md-3">
                    <label for="category4">Kategorie 4</label>
                    <select id="category4" name="category4" class="form-control">
                      <optgroup label="Kategorie">
                        <option value="1" <#if question.getCatId4() == 1> selected </#if>>${msg.get("PLANSPIEL_CATEGORY_A")}</option>
                        <option value="2" <#if question.getCatId4() == 2> selected </#if>>${msg.get("PLANSPIEL_CATEGORY_B")}</option>
                        <option value="3" <#if question.getCatId4() == 3> selected </#if>>${msg.get("PLANSPIEL_CATEGORY_C")}</option>
                      </optgroup>
                    </select>
                </div>
            </div>

            <div class="form-group">
            <div class="col-md-3">
                <label for="answer5">${msg.get("PLANSPIEL_ANSWER5")}</label>
                <input type="text" name="answer5" class="form-control" placeholder="${msg.get("PLANSPIEL_ANSWER5")}" value="${question.getAnswer5()}" >
            </div>
                <div class="col-md-3">
                 <label for="category1">Kategorie 5</label>
                    <select id="category5" name="category5" class="form-control">
                      <optgroup label="Kategorie">
                        <option value="1" <#if question.getCatId5() == 1> selected </#if>>${msg.get("PLANSPIEL_CATEGORY_A")}</option>
                        <option value="2" <#if question.getCatId5() == 2> selected </#if>>${msg.get("PLANSPIEL_CATEGORY_B")}</option>
                        <option value="3" <#if question.getCatId5() == 3> selected </#if>>${msg.get("PLANSPIEL_CATEGORY_C")}</option>
                      </optgroup>
                    </select>
                </div>
            </div>


            <div class="form-group">
               <label for="active">${msg.get("PLANSPIEL_IS_ACTIVE")}</label>
               <input type="checkbox" name="active" id="active" class="" <#if question.isActive() == true> checked </#if> placeholder="${msg.get("PLANSPIEL_IS_ACTIVE")}" value="true" required>
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
				<label for="exampleInputFile">File input</label>
				<input type="file" class="form-control-file" id="exampleInputFile" aria-describedby="fileHelp">
				<small id="fileHelp" class="form-text text-muted">This is some placeholder block-level help text for the above input. It's a bit lighter and easily wraps to a new line.</small>
			</div>


        </div>
        <div class="col-md-6">
            <div class="form-group">
                <input type="submit" class="btn btn-primary" value="${msg.get("PLANSPIEL_UPDATE")}">
            </div>
        </div>
        </div>
    </div>


</form>

</@layout.masterTemplate>