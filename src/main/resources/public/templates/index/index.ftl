<#import "/masterTemplate.ftl" as layout />
<@layout.masterTemplate title="Index">
    <h1>${msg.get("INDEX_HEADING")}</h1>
    <h2>${msg.get("INDEX_HEADING_INFO")}</h2>


 <div>
    <p>
        Im Unternehmensplanspiel von GoodIT übernimmt der Spieler die Verantwortung eines IT-Projektes innerhalb der Softwareentwicklung. Dabei hat der Spieler die Rolle des Projektleiters inne. Sowohl die Anzahl als auch die Komplexität der Entscheidungen, die im Laufe des Planspiels getroffen werden müssen, erhöhen sich mit zuneh-mender Spieldauer. Bevor der Spieler eine Entscheidung im Unternehmensplanspiel treffen muss, werden dem Spieler entsprechende Lernmittel offeriert und ausgiebig die jeweilige IST-Situation des Projektes bzw. Unternehmens beschrieben. Dadurch wird die Spieldauer je nach Lernbedarf massiv variieren.
    </p>
    <p>
        Dem Spieler werden zu jeder Fragesituation vorgegebene Antwortmöglichkeiten angeboten, die entsprechend ihrer Auswirkung auf das Gesamtspielergebnis klassifiziert sind.
        Das Planspiel kann mehrere Male durchgespielt werden. Dadurch, dass es sich je nach den getroffenen Entscheidungen im Ergebnis unterschiedlich verhält, sind die Teilnehmer spielerisch in der Lage herauszufinden, welche Entscheidungen zu einem besseren Gesamtergebnis führen.
    </p>
 </div>

 <h2>${msg.get("INDEX_HEADING_GOAL")}</h2>
 <div>

    <p>
    Da die Unternehmensleitung danach strebt, stets moderne Technologien einzusetzen, ist die kontinuierliche Weiter- und Fortbildung der Mitarbeiter essentiell.
    Aufgrund der unterschiedlichen Standorte und des in der Belegschaft sowie in der Branche veran-kerten problemorientierten Lernens,
    entschied sich die Unternehmensleitung für die Einführung einer E-Learning-Plattform.
    Derzeit wird ein besonderer Fokus von der Unternehmensleitung auf die Wissensvertiefung der IT-Führungskräfte im Bereich IT-Projektmanagement gelegt,
    da dort in der Vergangenheit häufig Planungs- sowie Kommunikationsprobleme und unklare Anforderungen aufgetreten sind.
    Zur Problem-beseitigung soll ein auf die Bedürfnisse von GoodIT eng optimiertes Unternehmens-planspiel erstellt werden,
    wodurch es sich um eine Individualsoftware handeln wird.
    Die Teilnehmer des Planspiels müssen selbstverantwortlich und eigenständig die Umsetzung eines Softwareentwicklungsprojektes und u. a.
    dem damit verbundenen Einsatz von Ressourcen planen und zugleich die Liquidität des Projektes bzw. des Unternehmens bewahren.
    Ein Teammodus ist zunächst nicht vorgesehen. Die Lehrenden respektive Spielbetreuer haben jederzeit die Möglichkeit
    den Lernfortschritt aller Teilnehmer einzusehen, um dadurch den Lernerfolg geschickter zu steuern und bei etwaigen Problemen zu agieren.
    </p>
    <p>
    Zu den Lernzielen des Unternehmensplanspiels gehören die folgenden Faktoren:
    </p>
        <ul>
            <li>Kennenlernen des Zusammenwirkens aller Funktionsbereiche im unternehmerischen Kontext (vernetztes Denken)</li>
            <li>Ausrichtung des eigenen Produktes bzw. Projektes auf die Kundenbedürfnisse </li>
            <li>Fähigkeit, strategische Entscheidungen zu treffen und in operative Maßnahmen umzusetzen </li>
            <li>Problemorientiertes Handeln und Einschätzung der Konsequenzen von Entscheidungen</li>
            <li>Steigerung der sog. Soft Skills und des Fachwissens </li>
        </ul>
        <p>
            Zu den mit den Lernzielen einhergehenden Lerninhalten zählen u. a. die folgenden Themengebiete:
        </p>
        <ul>
            <li>Produktlebenszyklen</li>
            <li>Softwareentwicklungsmodelle</li>
            <li>Personalplanung und -qualifikation, Produktivität und Fluktuation</li>
            <li>Investitions- und Auslastungsplanung</li>
        </ul>

        <p>
            Die Simulation wird damit IT-bezogene und betriebswirtschaftliche Denkweisen verbinden, wodurch die Kompetenzen der jeweiligen Führungskräfte nachhaltig gesteigert werden sollen und somit in die Lage versetzt werden, verantwortungsvolle Entscheidungen im Unternehmensalltag treffen zu können.
        </p>
    </p>

 </div>

 <h2>${msg.get("INDEX_HEADING_PROJECTINFO")}</h2>
 <div>
    <p>
        Der Quelltext der Webapplikation ist unter <a href="https://github.com/meyerde2/spark-elearning-goodit" target="onBlank_">Github</a> veröffentlicht.
    </p>

 </div>





</@layout.masterTemplate>
