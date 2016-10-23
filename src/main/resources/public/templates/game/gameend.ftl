<#import "/masterTemplate.ftl" as layout />
<@layout.masterTemplate title="Gameend">

    <h1>${msg.get("PLANSPIEL_HEADING")}</h1>

    Planspiel nicht gestartet.

    <p>Wollen Sie das Planspiel (erneut) durchlaufen?</p>
    <form action="/restartGame/" method="post">
        <button type="submit" class="btn btn-primary btn-md">
            <span class="glyphicon glyphicon-play"></span> Planspiel starten
        </button>

    </form>

</@layout.masterTemplate>