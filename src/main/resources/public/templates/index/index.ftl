<#import "/masterTemplate.ftl" as layout />
<@layout.masterTemplate title="Index">
    <h1>${msg.get("INDEX_HEADING")}</h1>
    <h2>${msg.get("INDEX_REGISTERED_USERS")}</h2>
    <ul>
        <#list users as user>
            <li>${user}</li>

        </#list>
    </ul>
    <p>${msg.get("INDEX_PASSWORD_INFO")}</p>

</@layout.masterTemplate>
