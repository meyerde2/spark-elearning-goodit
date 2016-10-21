<#import "/masterTemplate.ftl" as layout />
<@layout.masterTemplate title="Users">

$currentUser

    <#if users??>
            <a href="/spark/user">usernames</a>
        <table>
            <tr>
                <th>Id</th>
                <th>Username</th>
                <th>Firstname</th>
            </tr>
            <#list users as user>
                <tr>
                    <td>${user.getId()}</td>
                    <td>${user.getUsername()}</td>
                    <td>${user.getFirstname()}</td>
                </tr>
            </#list>
        </table>
    <#else>
        There are no phone numbers yet.
    </#if>

        <#if usernames??>

        <a href="/spark/hello">hello</a>
            <table>
                <tr>
                    <th>Id</th>
                    <th>Username</th>
                </tr>
                <#list usernames as name>
                    <tr>
                        <td>${name}</td>

                    </tr>
                </#list>
            </table>
        <#else>
            There are no phone numbers yet.
        </#if>

</@layout.masterTemplate>