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
                url: "${ctx!}/admin/role/delete/" + id,
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
<@layout title="角色管理" active="role">
<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>
        角色列表
        <small>一切从这里开始</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-cog"></i> 系统</a></li>
        <li><a href="#"><i class="fa fa-list-ul"></i> 角色管理</a></li>
        <li class="active"><i class="fa fa-table"></i> 角色列表</li>
    </ol>
</section>

<!-- Main content -->
<section class="content">
    <!-- Default box -->
    <div class="box box-primary">
        <div class="box-header">
        <@shiro.hasPermission name="system:role:add">
            <a class="btn btn-sm btn-success" href="${ctx!}/admin/role/add">新增</a>
        </@shiro.hasPermission>
        </div>
        <div class="box-body">
            <table class="table table-striped">
                <tr>
                    <th>ID</th>
                    <th>角色名称</th>
                    <th>角色key</th>
                    <th>角色状态</th>
                    <th>角色描述</th>
                    <th>创建时间</th>
                    <th>更新时间</th>
                    <th>操作</th>
                </tr>
                <#list pageInfo.content as roleInfo>
                <tr>
					<td>${roleInfo.id}</td>
					<td>${roleInfo.name}</td>
					<td>${roleInfo.roleKey}</td>
                    <td>
                        <#if roleInfo.status == 1>
                            <span class="label label-danger">已删除</span>
						<#else>
                            <span class="label label-info">未删除</span>
						</#if>
                    </td>
					<td>${roleInfo.description}</td>
					<td>${roleInfo.createTime}</td>
					<td>${roleInfo.updateTime}</td>
                    <td>
                    <@shiro.hasPermission name="system:role:edit">
                        <a class="btn btn-sm btn-primary" href="${ctx!}/admin/role/edit/${roleInfo.id}">编辑</a>
					</@shiro.hasPermission>
                    <@shiro.hasPermission name="system:role:grant">
                        <a class="btn btn-sm btn-warning" href="${ctx!}/admin/role/grant/${roleInfo.id}">分配资源</a>
					</@shiro.hasPermission>
                    <@shiro.hasPermission name="system:role:deleteBatch">
                        <button class="btn btn-sm btn-danger" onclick="del(${roleInfo.id})">删除</button>
					</@shiro.hasPermission>
                    </td>
                </tr>
				</#list>
            </table>
        </div>
        <!-- /.box-body -->
        <div class="box-footer clearfix">
            <@macro.page pageInfo=pageInfo url="${ctx!}/admin/role/index?" />
        </div>
    </div>
    <!-- /.box -->

</section>
<!-- /.content -->
</@layout>