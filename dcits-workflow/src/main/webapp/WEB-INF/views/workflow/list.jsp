<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<!DOCTYPE html>  
<html>  
<head>  
    <jsp:include page="/pub.jsp"></jsp:include>  
    <title>流程列表</title>  
<script type="text/javascript">  
var userDataGrid;  
$(function() {  
    userDataGrid = $('#user_datagrid').datagrid({  
        url : '${pageContext.request.contextPath}/workflow/list',  
        fit : true,  
        fitColumns : true,  
        border : false,  
        pagination : true,  
        idField : 'id',  
        pagePosition : 'both',  
        checkOnSelect:true,  
        selectOnCheck:true,  
        columns : [ [ {  
            field : 'id',  
            title : 'ID'  
        }, {  
            field : 'key',  
            title : 'KEY',  
            sortable : true  
        }, {  
            field : 'name',  
            title : '名称',  
            sortable : true  
        }, {  
            field : 'version',  
            title : '版本'  
        }, {  
            field : 'createTime',  
            title : '创建时间'  
        }, {  
            field : 'lastUpdateTime',  
            title : '更新时间'  
        }, {  
            field : 'metaInfo',  
            title : '元数据'  
        }] ],  
        toolbar : [ {  
            text : '增加',  
            iconCls : 'ext-icon-add',  
            handler : function() {  
                userAdd();  
            }  
        }],  
        onRowContextMenu:function(e, rowIndex, rowData){  
            e.preventDefault();  
            $(this).datagrid('unselectAll');  
            $(this).datagrid('selectRow',rowIndex);  
            $('#user_menu').menu('show', {  
                left : e.pageX,  
                top : e.pageY  
            });  
        }  
    });  
      
    $('#user_edit_depid').combotree({  
        url : '${pageContext.request.contextPath}/dep/tree.do',  
        idField:'departmentId',  
        textField:'departmentname',  
        parentField : 'parentId',  
        lines : true,  
        panelHeight : 'auto',  
        onClick: function(node){  
            $("#user_edit_depid").combotree('setValue', node.departmentId);  
        }  
    });  
});  
  
function userAdd() {  
    fromReset("admin_addUserForm");  
    $('#admin_addUser').dialog('open');  
}  
  
function addUserForm(){  
    $('#admin_addUserForm').submit();  
}  
  
function userEdit(){  
    var rows = userDataGrid.datagrid('getChecked');  
    if(rows.length==1){  
        window.open("${pageContext.request.contextPath}/service/editor?id="+rows[0].id);  
    }else{  
        parent.$.messager.alert('提示','请选择一条记录进行修改');  
    }  
}  
function exportModel(){  
    var rows = userDataGrid.datagrid('getChecked');  
    if(rows.length==1){  
        window.open("${pageContext.request.contextPath}/workflow/export?modelId="+rows[0].id);  
    }else{  
        parent.$.messager.alert('提示','请选择一条记录进行修改');  
    }  
}  
  
function deployModel(){  
    var rows = userDataGrid.datagrid('getChecked');  
    if(rows.length==1){  
        $.post('${pageContext.request.contextPath}/workflow/deploy', {modelId:rows[0].id}, function(j) {  
            parent.$.messager.progress({  
                title : '提示',  
                text : '数据处理中，请稍后....'  
            });  
            if (j.success) {  
                parent.$.messager.progress('close');  
                userDataGrid.datagrid('load');  
            }  
            userDataGrid.datagrid('uncheckAll');  
            $.messager.show({  
                title : '提示',  
                msg : j.msg,  
                timeout : 5000,  
                showType : 'slide'  
            });  
        }, 'json');  
    }else{  
        parent.$.messager.alert('提示','请选择一条记录进行修改');  
    }  
}  
  
function userDelete(){  
    var rows = userDataGrid.datagrid('getChecked');  
    var ids = [];  
    if(rows.length>0){  
        $.messager.confirm('确认','您确认想要删除记录吗？',function(r){  
            if (r){  
                for(var i=0;i<rows.length;i++){  
                    ids.push(rows[i].id);  
                }  
                $.post('${pageContext.request.contextPath}/user/delete.do', {ids:ids.join(',')}, function(j) {  
                    if (j.success) {  
                        userDataGrid.datagrid('load');  
                        $('#admin_addUser').dialog('close');  
                    }  
                    userDataGrid.datagrid('uncheckAll');  
                    $.messager.show({  
                        title : '提示',  
                        msg : j.msg,  
                        timeout : 5000,  
                        showType : 'slide'  
                    });  
                }, 'json');  
            }      
        });  
    }else{  
        $.messager.show({  
            title : '提示',  
            msg : '请勾选要删除的记录',  
            timeout : 5000,  
            showType : 'slide'  
        });  
    }  
}  
</script>  
</head>  
  
<body>  
<div class="easyui-layout" data-options="fit:true,border:false">  
    <div data-options="region:'center',border:false,title:'模型列表'" style="overflow: hidden;">  
        <table id="user_datagrid"></table>  
    </div>  
</div>  
  
<div id="user_menu" class="easyui-menu" style="width: 120px;display: none;">  
<div onclick="userEdit()" iconCls="icon-edit">编辑</div>  
<div onclick="deployModel()" iconCls="icon-edit">部署</div>  
<div onclick="exportModel()" iconCls="icon-edit">导出</div>  
<div onclick="userDelete()" iconCls="icon-remove">删除</div>  
</div>  
  
<div id="admin_addUser" class="easyui-dialog" data-options="closed:true,modal:true,title:'添加用户',buttons:[{  
                text : '保存',  
                iconCls : 'icon-save',  
                handler : function() {  
                    addUserForm();  
                }  
            }]" style="width: 300px;height: 200px;">  
    <form id="admin_addUserForm" action="${pageContext.request.contextPath}/model/create.do" target="_blank" method="post">  
        <table>  
            <tr>  
                <th>名称</th>  
                <td><input name="name" id="name"  class="easyui-validatebox" data-options="required:true,missingMessage:'名称必填'" /></td>  
            </tr>  
            <tr>  
                <th>KEY</th>  
                <td><input name="key"  id="key"  class="easyui-validatebox" data-options="required:true,missingMessage:'KEY必填'" /></td>  
            </tr>  
            <tr>  
                <th>描述</th>  
                <td><textarea id="description" name="description"></textarea></td>  
            </tr>  
        </table>  
    </form>  
</div>  
</body>  
</html>  