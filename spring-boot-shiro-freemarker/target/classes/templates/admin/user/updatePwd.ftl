<#include "/admin/layout/layout.ftl">
<#import "/admin/layout/macro.ftl" as macro>
<#assign css>
</#assign>
<#assign js>
<script>
    $(".btn-submit").click(function () {
        $.ajax({
            type: "POST",
            dataType: "json",
            url: "${ctx!}/admin/user/updatePwd",
            data: $(".form-edit").serialize(),
            success: function(msg){
                layer.msg(msg.message, {time: 2000},function(){
                    if(msg.code ==0){
                        window.location.href = "${ctx!}/admin/logout";
                    } else {
                        location.reload();
                    }
                });
            }
        });
    });
</script>
</#assign>

<@layout title="修改密码" active="user">
<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>
        修改密码
        <small>修改当前登录用户密码</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-cog"></i> 系统</a></li>
        <li><a href="#"><i class="fa fa-list-ul"></i> 用户管理</a></li>
        <li class="active"><i class="fa fa-edit"></i> 修改密码</li>
    </ol>
</section>
<!-- Main content -->
<section class="content">
    <div class="row">
        <div class="col-md-10">
            <!-- Default box -->
            <div class="box  box-primary">
                <form class="form-horizontal form-edit" method="post" action="${ctx!}/admin/user/updatePwd">
                    <div class="box-body">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">原始密码：</label>
                            <div class="col-sm-8">
                                <input id="oldPassword" name="oldPassword" class="form-control" type="password">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">新 密 码：</label>
                            <div class="col-sm-8">
                                <input id="password1" name="password1" class="form-control" type="password">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">重复密码：</label>
                            <div class="col-sm-8">
                                <input id="password2" name="password2" class="form-control" type="password">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-8 col-sm-offset-3">
                                <button class="btn btn-primary" type="submit">提交</button>
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