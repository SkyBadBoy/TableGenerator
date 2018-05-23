<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>代码生成工具- majian</title>
    <meta name="keywords" content="马健">
    <meta name="description" content="马健">
    <script src="js/web/include.js?v=1.0.5"></script>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content" id="app">
    <!-- Panel Other -->
    <div class="ibox float-e-margins">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                        <div class="form-horizontal">

							<#list xName as field>
							    <div class="hr-line-dashed" ></div>
	                            <div class="form-group">
	                                <label class="col-sm-2 control-label" >${field.xame}</label>
	                                <div class="col-sm-10">
	                                    <input type="text" v-model="data.${field.xame}" class="form-control">
	                            </div>
                            </div>
							</#list>
                          
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <div class="col-sm-4 col-sm-offset-2">
                                    <button class="btn btn-primary" @click="save" type="submit">保存内容</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    window.onload=function () {
        var app = new Vue({
            el: "#app",
            data: {
                data:{
                    iD:getUrlKey("id"),
                    <#list xName as field>
                    	${field.xame}:0,
                    </#list>
                }
            },
            methods: {
                save: function () {
                    savaData(this,"../${classdef}/modify${classdef}")
                }
            },
            mounted: function () {
                if(this.data.iD!=0){
                    findByID(this,this.data.iD,"../${classdef}/find${classdef}/")
                }
                bindImgError()
            }
        })
    }
</script>
</body>
</html>
