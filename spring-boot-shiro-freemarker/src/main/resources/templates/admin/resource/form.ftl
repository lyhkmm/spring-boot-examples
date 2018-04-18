<#include "/admin/layout/layout.ftl">
<#import "/admin/layout/macro.ftl" as macro>
<#assign css>

</#assign>
<#assign js>
<script>
    $(".btn-submit").click(function () {
        $.ajax({
            type: "POST",
            url: "${ctx!}/admin/resource/edit",
            data: $(".form-edit").serialize(),
            dataType: "JSON",
            success: function(res){
                layer.msg(res.message, {time: 2000
                }, function(){
                    location.reload();
                });
            }
        });
    });
</script>
</#assign>
<@layout title="资源编辑" active="resource">
<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>
        资源编辑
        <small>编辑资源详细信息</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-cog"></i> 系统</a></li>
        <li><a href="#"><i class="fa fa-list-ul"></i> 资源管理</a></li>
        <li class="active"><i class="fa fa-edit"></i> 资源编辑</li>
    </ol>
</section>
<!-- Main content -->
<section class="content">
    <div class="row">
        <div class="col-md-10">
            <!-- Default box -->
            <div class="box  box-primary">
                <form class="form-horizontal form-edit" method="post" action="${ctx!}/admin/resource/edit">
                    <div class="box-body">
                        <input type="hidden" id="id" name="id" value="${resource.id}">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">上级资源：</label>
                            <div class="col-sm-8">
                                <select name="parentId" class="form-control">
                                    <option value="">菜单</option>
                                    <#list list as r>
                                        <option value="${r.id}" <#if resource.parent.id == r.id>selected="selected"</#if>>
                                            <#if r.level == 1>|-<#elseif  r.level == 2>　|-<#else>　　|-</#if>${r.name}
                                        </option>
                                    </#list>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">资源名称：</label>
                            <div class="col-sm-8">
                                <input id="name" name="name" class="form-control" type="text" value="${resource.name}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">唯一标识：</label>
                            <div class="col-sm-8">
                                <input id="sourceKey" name="sourceKey" class="form-control" type="text" value="${resource.sourceKey}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">资源类型：</label>
                            <div class="col-sm-8">
                                <select name="type" class="form-control">
                                    <option value="0" <#if resource.type == 0>selected="selected"</#if>>目录</option>
                                    <option value="1" <#if resource.type == 1>selected="selected"</#if>>菜单</option>
                                    <option value="2" <#if resource.type == 2>selected="selected"</#if>>按钮</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">资源url：</label>
                            <div class="col-sm-8">
                                <input id="sourceUrl" name="sourceUrl" class="form-control" value="${resource.sourceUrl}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">层级：</label>
                            <div class="col-sm-8">
                                <input id="level" name="level" class="form-control" value="${resource.level}" placeholder="目录：1，菜单：2，按钮：3">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">排序：</label>
                            <div class="col-sm-8">
                                <input id="sort" name="sort" class="form-control" value="${resource.sort}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">图标：</label>
                            <div class="col-sm-8">
                                <input id="icon" name="icon" class="form-control" value="${resource.icon}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">状态：</label>
                            <div class="col-sm-8">
                                <select name="isHide" class="form-control">
                                    <option value="0" <#if resource.locked == 0>selected="selected"</#if>>显示</option>
                                    <option value="1" <#if resource.locked == 1>selected="selected"</#if>>隐藏</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">描述：</label>
                            <div class="col-sm-8">
                                <textarea id="description" name="description" class="form-control" rows="6">${resource.description}</textarea>
                            </div>
                        </div>
                    </div>
                    <div class="box-footer">
                        <button type="button" class="btn btn-default btn-back">返回</button>
                        <button type="button" class="btn btn-info pull-right btn-submit">提交</button>
                    </div>
                </form>
            </div>
            <!-- /.box -->
        </div>
    </div>
</section>
<!-- /.content -->
</@layout>