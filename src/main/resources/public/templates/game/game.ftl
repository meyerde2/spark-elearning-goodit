<#import "/masterTemplate.ftl" as layout />
<@layout.masterTemplate title="">

    <h1>${msg.get("INDEX_HEADING")}</h1>
    <h2>Unternehmensplanspiel</h2>


    <div class="description">
        ${question.getDescription()}
    </div>

    <div>
        <h3>Frage: ${question.getId()}</h3>
        <h4>${question.getQuestion()}</h4>
    </div>

    <form id="answer" name="answer" action="/gamescore/" method="post">

        <input type="hidden" name="id" value="$question.getId()">

        <div class="answers">
            <div>
                <label class="radio-inline"><input type="radio" name="answer" value="1">${question.getAnswer1()}</label>
                <label class="radio-inline"><input type="radio" name="answer" value="2">${question.getAnswer2()}</label>
            </div>
            <div>
                <label class="radio-inline"><input type="radio" name="answer" value="3">${question.getAnswer3()}</label>
				${question.getAnswer4()}
                    <label class="radio-inline"><input type="radio" name="answer" value="4">${question.getAnswer4()}</label>

            </div>

            ${question.getAnswer5()}
                <div>
                    <label class="radio-inline"><input type="radio" name="answer" value="5">${question.getAnswer5()}</label>
                </div>

        </div>

        <div class="form-group">
            <input type="submit" class="btn btn-primary" value="antworten">
        </div>

    </form>


</@layout.masterTemplate>