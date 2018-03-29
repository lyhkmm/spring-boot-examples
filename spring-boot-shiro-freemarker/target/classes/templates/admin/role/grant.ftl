<#include "/admin/layout/layout.ftl">
<#import "/admin/layout/macro.ftl" as macro>
<#assign css>
<link href="${ctx!}/assets/plugins/zTree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet">
</#assign>
<#assign js>
<script src="${ctx!}/assets/plugins/zTree/jquery.ztree.all.min.js"></script>
<script>
    var setting = {
        check : {
            enable : true
        },
        data : {
            simpleData : {
                enable : true
            }
        }
    };
    setting.check.chkboxType = {
        "Y" : "ps",
        "N" : "s"
    };
    $.ajax({
        type : "POST",
        url : "${ctx!}/admin/resource/tree/" + ${role.id},
        dataType : 'json',
        success : function(msg) {
            $.fn.zTree.init($("#tree"), setting, msg);
        }
    });


    $(".btn-submit").click(function () {
        var treeObj = $.fn.zTree.getZTreeObj("tree");
        var nodes = treeObj.getCheckedNodes(true);
        var selectIds="";
        for(var index in nodes){
            var item=nodes[index];
            selectIds+=item.id+",";
        }
        $.ajax({
            url : "${ctx!}/admin/role/grant/${role.id}?t=" + Math.random(),
            type : "post",
            dataType : "json",
            data : {"resourceIds":selectIds},
            success : function(msg) {
                layer.msg(msg.message, {time: 2000},function(){
                    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                    parent.layer.close(index);
                });
            },
            error : function(r,s,m) {
            }
        });
    });
</script>
</#assign>
<@layout title="资源分配" active="role">
<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>
        资源分配
        <small>分配角色关联资源</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-cog"></i> 系统</a></li>
        <li><a href="#"><i class="fa fa-list-ul"></i> 角色管理</a></li>
        <li class="active"><i class="fa fa-edit"></i> 资源分配</li>
    </ol>
</section>
<!-- Main content -->
<section class="content">
    <div class="row">
        <div class="col-md-10">
            <!-- Default box -->
            <div class="box  box-primary">
				<div class="box-body">
                    <ul id="tree" class="ztree"></ul>
				</div>
				<div class="box-footer">
					<button type="button" class="btn btn-default btn-back">返回</button>
					<button type="button" class="btn btn-info pull-right btn-submit">提交</button>
				</div>
            </div>
        </div>
    </div>
</section>
<!-- /.content -->
</@layout>