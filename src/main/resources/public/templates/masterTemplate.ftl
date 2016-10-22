<#macro masterTemplate title="">
    <html>
    <head>
        <title>${msg.get("COMMON_TITLE")}</title>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

        <link rel="stylesheet" href="/bootstrap/css/bootstrap.css">
        <link rel="stylesheet" href="/style.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="/bootstrap/js/bootstrap.js"></script>

        <link rel="icon" href="/img/favicon.png">
        <meta name="viewport" content="width=device-width, initial-scale=1">
    </head>
    <body>
    <header>
        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>

                    <a class="navbar-brand" href="${WebPath.getINDEX()}">eLearning</a>
                </div>

                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">

                        <li class="active"><a href="${WebPath.getINDEX()}">${msg.get("COMMON_NAV_INDEX")}</a></li>
                        <li><a href="${WebPath.getDASHBOARD()}">${msg.get("COMMON_NAV_DASHBOARD")}</a></li>
                        <li><a href="${WebPath.getGAME()}">${msg.get("COMMON_NAV_GAME")}</a></li>


                        <li class="dropdown nav navbar-right">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Administrationsbereich<span class="caret"></span></a>

                        <ul class="dropdown-menu">
                            <li><a href="${WebPath.getUSERCONTROL()}">${msg.get("COMMON_NAV_USERCONTROL")}</a></li>
                            <li><a href="${WebPath.getGAMECONTROL()}">${msg.get("COMMON_NAV_GAMECONTROL")}</a></li>

                            <li role="separator" class="divider"></li>

                            <li class="dropdown-header">Account</li>

                            <#if currentUser?has_content>
                            <li class="logoutLi">
                                <form method="post" action="${WebPath.getLOGOUT()}" name="logout" id="logout">
                                    <a id="logout" href="#" class="navLogout" onclick="document.getElementById('logout').submit();">${msg.get("COMMON_NAV_LOGOUT")}  (${currentUser})</a>
                                </form>
                            </li>

                            <#else>
                                <li><a href="${WebPath.getLOGIN()}">${msg.get("COMMON_NAV_LOGIN")}</a></li>
                            </#if>
                        </ul>

                        </li>
                    </ul>

                </div><!--/.nav-collapse -->

            </div>
        </nav>

    </header>
    <main>
        <div id="container" class="container" role="main">
        <div class="row">
            <div class="col-md-6-col-md-push-4">
<#nested />

<#list .data_model?keys as key>
  ${key}
</#list>

WEBPATH:
${WebPath.getLOGIN()}
${WebPath.getDASHBOARD()}
___



            </div>
       </div>
        </div>
    </main>
    <footer>
        <div class="container">
            ${msg.get("COMMON_FOOTER_TEXT")}
        </div>

    </footer>
    <div id="footer2">
    ${msg.get("COMMON_FOOTER_TEXT")}
    </div>
    </body>
    </html>
</#macro>