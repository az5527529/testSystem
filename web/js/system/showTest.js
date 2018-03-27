$(function(){
    $('#showTestTable').datagrid({
        pageList: [10,20,50,100],
        pagination:true,
        rownumbers:true,
        pagePosition:"bottom",
        width: ($("#list").width()),
        height:$("#list").height(),
        fitColumns:true,
        toolbar:"#btn",
        singleSelect:true,
        sortOrder:"asc",
        // url:ctx+"/showTest/showGeneralTest.action?ids=" + Math.random(),
        columns:[[
            {field:'totalNum',title:'答题总人数',width:50,align:'center'},
            {field:'maxScore',title:'最高分',width:120,align:'center'},
            {field:'average',title:'平均分',width:80,align:'center'},

        ]]
    });


});

//查询方法
function searchTest(){
    $('#showTestTable').datagrid("options").queryParams={
        "testTimeBegin": $("#testTimeBegin").datebox('getValue'),"testTimeEnd": $("#testTimeEnd").datebox('getValue')
        // fields:JSON.stringify({"questionNo": $("#questionNo").val(),"content": $("#content").val()}),

    };
    $('#showTestTable').datagrid("options").url=ctx+"/showTest/showGeneralTest.action?ids=" + Math.random();
    $('#showTestTable').datagrid("load");
}

//导出
function exportAll(){
    var params =[
        {name: 'testTimeBegin', value: $("#testTimeBegin").datebox('getValue')}
        ,{name: 'testTimeEnd', value: $("#testTimeEnd").datebox('getValue')}
        ,{name: 'serviceName',value : "showTestService"}
    ];

    exportToExcel(params);
}