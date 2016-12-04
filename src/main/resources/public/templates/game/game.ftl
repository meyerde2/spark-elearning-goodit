<#import "/masterTemplate.ftl" as layout />
<@layout.masterTemplate title="">

    <h1>${msg.get("PLANSPIEL_HEADING")}</h1>


    <div class="company">
        <h2>${msg.get("PLANSPIEL_COMPANY")}</h2>
        <p>
        GoodIT ist eine wachsende mittelständige Web- und Softwareagentur mit Unternehmensstandorten in Hamburg, München und Berlin.
        Insgesamt beschäftigt GoodIT 135 Mitarbeiter, bei einem Altersdurchschnitt von ca. 34 Jahren, und erzielte vergangenes Jahr einen Umsatz von 33 Millionen Euro.
         Das Unternehmen bietet ganzheitliche Dienstleistungen und individuelle Lösungen für eine vernetzte Welt an.
         Dabei deckt GoodIT insbesondere Kompetenzen in den Bereichen Internet, mobile Applikationen und Social Media ab.
         Das Tätigkeitsspektrum reicht von der Strategieberatung über die Planung und Entwicklung bis hin zum Betrieb.
         Die Projektrealisierung erfolgt im Regelfall open-source-orientiert.
        Zu den bisherigen Auftraggebern zählen sowohl kleine und mittelständische Unternehmen als auch internationale Großunternehmen.
        Gegenwärtig soll bei GoodIT eine neue mobile Applikation erstellt werden, welche es nun zu planen und umzusetzen gilt:</p>
    </div>

    <#if question??>

        <div class="description">
            <h2>${msg.get("PLANSPIEL_NEW_SITUATION")}</h2>
            ${question.getDescription()}
        </div>

        <div>
            <h3>Frage: <#if currentQuestionPosition??>${currentQuestionPosition}</#if> von <#if questionCount??>${questionCount}</#if></h3>
            <h4>${question.getQuestion()}</h4>
        </div>

        <form id="answer" name="answer" action="/gamescore/" method="post">

            <input type="hidden" name="id" value="${question.getId()}">

            <div class="answers">
                <div>
                    <label class="radio-inline"><input type="radio" name="answer" value="1" required>${question.getAnswer1()}</label>
                </div>
                <div>
                    <label class="radio-inline"><input type="radio" name="answer" value="2">${question.getAnswer2()}</label>
                </div>
                <div>
                    <label class="radio-inline"><input type="radio" name="answer" value="3">${question.getAnswer3()}</label>
                </div>
                <#if question.getAnswer4()?has_content>
                    <div>
                     <label class="radio-inline"><input type="radio" name="answer" value="4">${question.getAnswer4()}</label>
                    </div>
                </#if>

                <#if question.getAnswer5()?has_content>
                    <div>
                        <label class="radio-inline"><input type="radio" name="answer" value="5">${question.getAnswer5()}</label>
                    </div>
                </#if>
            </div>

            <div class="form-group">
                <button type="submit" class="btn btn-primary btn-md">
                    <span class="glyphicon glyphicon-ok"></span> antworten
                </button>

            </div>

        </form>
    </#if>
</@layout.masterTemplate>