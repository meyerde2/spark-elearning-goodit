<#import "/masterTemplate.ftl" as layout />
<@layout.masterTemplate title="Gameend">

    <h1>${msg.get("INDEX_HEADING")}</h1>
    <h2>Unternehmensplanspiel</h2>

    Planspiel nicht gestartet.

    <p>Wollen Sie das Planspiel (erneut) durchlaufen?</p>
    <form action="/restartGame/" method="post">
        <div class="form-group">
            <input type="submit" class="btn btn-primary" name="submit"  id"submit" value="Planspiel starten" />
        </div>
    </form>

</@layout.masterTemplate>