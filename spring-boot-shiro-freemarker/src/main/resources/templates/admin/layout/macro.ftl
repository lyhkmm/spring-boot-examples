<#macro page pageInfo url>
    <#local pageNo = pageInfo.number + 1>
    <#local totalPage = pageInfo.totalPages>
    <#local showPages = 6>
    <ul class="pagination pagination-sm no-margin pull-right">
        <#if pageNo!=1 && totalPage gt 1>
            <li><a href="${url!}&pageNumber=1">首页</a></li>
            <li><a href="${url!}&pageNumber=${pageNo - 1}">上一页</a></li>
        </#if>
        <#if pageNo-showPages/2 gt 0>
            <#assign start = pageNo-(showPages-1)/2/>
            <#if showPages gt totalPage>
                <#assign start = 1/>
            </#if>
        <#else>
            <#assign start = 1/>
        </#if>
        <#if totalPage gt showPages>
            <#assign end = (start+showPages-1)/>
            <#if end gt totalPage>
                <#assign start = totalPage-showPages+1/>
                <#assign end = totalPage/>
            </#if>
        <#else>
            <#assign end = totalPage/>
        </#if>
        <#assign pages=start..end/>
        <#list pages as page>
            <#if page==pageNo>
                <li class="active"><a href="${url!}&pageNumber=${page}">${page}</a></li>
            <#else>
                <li><a href="${url!}&pageNumber=${page}">${page}</a></li>
            </#if>
        </#list>
        <#if pageNo!=totalPage && pageNo lt totalPage>
            <li><a href="${url!}&pageNumber=${pageNo + 1}">下一页</a></li>
            <li><a href="${url!}&pageNumber=${totalPage}">尾页</a></li>
        </#if>
    </ul>
</#macro>