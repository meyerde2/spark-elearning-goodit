<#import "/masterTemplate.ftl" as layout />
<@layout.masterTemplate title="Dashboard">

    <h1>${msg.get("DASHBOARD_HEADING")}</h1>

    <h2>Mein aktueller Spielfortschritt</h2>

    <div class="progress">
        <div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="<#if currentQuestionPosition??>${currentQuestionPosition}<#else>0</#if>"
            aria-valuemin="0" aria-valuemax="${numberOfAllActiveQuestions}" style="width:<#if currentQuestionPosition??>${(currentQuestionPosition / numberOfAllActiveQuestions * 100)?c}<#else>0</#if>%">
            Frage <#if currentQuestionPosition??>${currentQuestionPosition}<#else>0</#if> von ${numberOfAllActiveQuestions}
        </div>
    </div>

    <h2>Wie oft wurde bisher welche Frage gespielt?</h2>


        <#list playedQuestionsCountList>
			<#items as playedQuestion>

                <div class="text-md-center" id="currentGameProgress">
                    <#if playedQuestion??>Frage ${playedQuestion.getQuestionId()}<#else>0</#if> von ${numberOfAllQuestions}: Wurde bisher <#if playedQuestion??>${playedQuestion.getPlayScore()}<#else>0</#if> Mal in ${totalNumberOfAllPlayedGames} gestarteten Planspielen beantwortet/gespielt.
                </div>

                <div class="progress">
                    <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="<#if playedQuestion??>${playedQuestion.getPlayScore()}<#else>0</#if>"
                        aria-valuemin="0" aria-valuemax="${totalNumberOfAllPlayedGames}" style="width:<#if playedQuestion??>${(playedQuestion.getPlayScore() / totalNumberOfAllPlayedGames * 100)?c}<#else>0</#if>%">
                        <#if playedQuestion??>${playedQuestion.getPlayScore()}<#else>0</#if> von ${totalNumberOfAllPlayedGames}
                    </div>
                </div>
			</#items>
		<#else>
		  <p>No played questions
		</#list>

    <h2>Wie viele Planspiele wurden mit welchem Status abgeschlossen?</h2>

    <div class="progress">
        <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="<#if resultCategory1??>${resultCategory1}<#else>0</#if>"
            aria-valuemin="0" aria-valuemax="${numberOfTotalResults}" style="width:<#if resultCategory1??>${(resultCategory1 / numberOfTotalResults * 100)?c}<#else>0</#if>%">
            <#if resultCategory1??>${resultCategory1}<#else>0</#if> von ${numberOfTotalResults} durchgespielten Planspielen
        </div>
    </div>

    <div class="progress">
        <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="<#if resultCategory2??>${resultCategory2}<#else>0</#if>"
            aria-valuemin="0" aria-valuemax="${numberOfTotalResults}" style="width:<#if resultCategory2??>${(resultCategory2 / numberOfTotalResults * 100)?c}<#else>0</#if>%">
            <#if resultCategory1??>${resultCategory2}<#else>0</#if> von ${numberOfTotalResults} durchgespielten Planspielen
        </div>
    </div>

    <div class="progress">
        <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="<#if resultCategory3??>${resultCategory3}<#else>0</#if>"
            aria-valuemin="0" aria-valuemax="${numberOfTotalResults}" style="width:<#if resultCategory3??>${(resultCategory3 / numberOfTotalResults * 100)?c}<#else>0</#if>%">
            <#if resultCategory1??>${resultCategory3}<#else>0</#if> von ${numberOfTotalResults} durchgespielten Planspielen
        </div>
    </div>




</@layout.masterTemplate>
