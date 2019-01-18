<div class="left-panel" id='cssmenu'>
    <ul>
    <#if groupList??>
        <#list groupList as list>
            <li class='has-sub'><a href="javascript:">${list.pgName}</a></li>
        </#list>
    </#if>
    </ul>
</div>