<#include "/admin/layout/layout.ftl">
<#import "/admin/layout/macro.ftl" as macro>
<#assign css>
<style>
</style>
</#assign>
<#assign js>
<script>
    function del(id){
        layer.confirm('确定删除吗?', {icon: 3, title:'提示'}, function(index){
            $.ajax({
                type: "POST",
                dataType: "json",
                url: "${ctx!}/admin/resource/delete/" + id,
                success: function(res){
                    layer.msg(res.message, {time: 2000}, function () {
                        location.reload();
                    });
                }
            });
        });
    }
</script>
</#assign>
<@layout title="资源管理" active="resource">
<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>
        资源列表
        <small>一切从这里开始</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-cog"></i> 系统</a></li>
        <li><a href="#"><i class="fa fa-list-ul"></i> 资源管理</a></li>
        <li class="active"><i class="fa fa-table"></i> 资源列表</li>
    </ol>
</section>

<!-- Main content -->
<section class="content">
    <!-- Default box -->
    <div class="box box-primary">
        <div class="box-header">
		<@shiro.hasPermission name="system:resource:add">
			<a class="btn btn-sm btn-success" href="${ctx!}/admin/resource/add">新增</a>
		</@shiro.hasPermission>
        </div>
        <div class="box-body">
            <table class="table table-striped">
                <tr>
                    <th>ID</th>
                    <th>资源名称</th>
                    <th>资源key</th>
                    <th>类型</th>
                    <th>资源url</th>
                    <th>层级</th>
                    <th>排序</th>
                    <th>icon</th>
                    <th>状态</th>
                    <th>描述</th>
                    <th>更新时间</th>
                    <th>操作</th>
                </tr>
                <#list pageInfo.content as resourceInfo>
                <tr>
                    <td>${resourceInfo.id}</td>
                    <td>${resourceInfo.name}</td>
                    <td>${resourceInfo.sourceKey}</td>
                    <td>
                        <#if resourceInfo.type == 0>
                            <span class="label label-info">目录</span>
                        <#elseif resourceInfo.type == 1>
                            <span class="label label-danger">菜单</span>
                        <#else >
                            <span class="label label-warning">按钮</span>
                        </#if>
                    </td>
                    <td>${resourceInfo.sourceUrl}</td>
                    <td>${resourceInfo.level}</td>
                    <td>${resourceInfo.sort}</td>
                    <td>${resourceInfo.icon}</td>
                    <td>
                    <#if resourceInfo.isHide == 1>
                            <span class="label label-danger">隐藏</span>
                    <#else>
                            <span class="label label-info">显示</span>
                    </#if>
                    </td>
                    <td>${resourceInfo.description}</td>
                    <td>${resourceInfo.updateTime}</td>
                    <td>
					<@shiro.hasPermission name="system:resource:edit">
					<a class="btn btn-sm btn-primary" href="${ctx!}/admin/resource/edit/${resourceInfo.id}">编辑</a>
					</@shiro.hasPermission>
                    <@shiro.hasPermission name="system:resource:deleteBatch">
                        <button class="btn btn-sm btn-danger" onclick="del(${resourceInfo.id})">删除</button>
					</@shiro.hasPermission>
					</td>
                </tr>
				</#list>
            </table>
        </div>
        <!-- /.box-body -->
        <div class="box-footer clearfix">
            <@macro.page pageInfo=pageInfo url="${ctx!}/admin/resource/index?" />
        </div>
    </div>
    <!-- /.box -->

</section>
<!-- /.content -->
</@layout>