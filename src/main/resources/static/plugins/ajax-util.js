// (function($) {
//     // 首先备份下jquery的ajax方法
//     var _ajax = $.ajax;
//
//     // 重写jquery的ajax方法
//     $.ajax = function(options) {
//         // 备份opt中error和success方法
//         var callback = {
//             "beforeSend" : function(XHR) {
//             },
//             "complete" : function(XHR, TS) {
//             },
//             "error" : function(XMLHttpRequest, textStatus, errorThrown) {
//             },
//             "success" : function(data, textStatus) {
//             }
//         }
//
//         // 判断参数中是否有beforeSend回调函数
//         if (options.beforeSend) {
//             callback.beforeSend = options.beforeSend;
//         }
//
//         // 判断参数中是否有complete回调函数
//         if (options.complete) {
//             callback.complete = options.complete;
//         }
//
//         // 判断参数中是否有error回调函数
//         if (options.error) {
//             callback.error = options.error;
//         }
//
//         // 判断参数中是否有success回调函数
//         if (options.success) {
//             callback.success = options.success;
//         }
//
//         // 扩展增强处理
//         var _opt = $.extend(options, {
//             error : function(XMLHttpRequest, textStatus, errorThrown) {
//                 // 错误方法增强处理
//                 callback.error(XMLHttpRequest, textStatus, errorThrown);
//             },
//             success : function(data,textStatus) {
//                 // 成功回调方法增强处理
//                 if(403==data.code){
//                     parent.location.href = '/login';
//                 }
//                 callback.success(data);
//             },
//             beforeSend : function(XHR) {
//                 // 提交前回调方法
//                 var index = layer.load(1, {
//                     shade: [0.1,'#fff'] //0.1透明度的白色背景
//                 });
//                 callback.beforeSend(XHR);
//             },
//             complete : function(XHR, TS) {
//                 // 请求完成后回调函数 (请求成功或失败之后均调用)。
//                 layer.closeAll('loading');
//                 callback.complete(XHR, TS);
//             }
//         });
//
//         // 返回重写的ajax
//         return _ajax(_opt);
//     };
// })(jQuery);
//
//
//
//

/*

//全站ajax加载提示
(function ($) {
    $(document).ajaxStart(function () {
        var index = layer.load(1, {
            shade: [0.1, '#fff'] //0.1透明度的白色背景
        });
    });

    $(document).ajaxStop(function () {
        layer.closeAll('loading');
    });
    //登录过期，shiro返回登录页面
    $.ajaxSetup({
        complete: function (event,xhr,options) {
            if('text/html;charset=UTF-8'==event.getResponseHeader('Content-Type')){
                top.location.href = '/login';
            }
        }
    });
})(jQuery);
*/

//Ajax
var ajaxRequest = function(obj){
    //设置请求超时事件，默认为：30*1000
    var timeout=30*1000;
    if(obj.timeout){
        timeout = obj.timeout;
    }
        //设置请求方式，默认为POST
        var type="POST";
        if(obj.type){
            type=obj.type;
        }

        //设置信息至服务器时内容编码类型，默认为"application/x-www-form-urlencoded"
        var contentType ="application/x-www-form-urlencoded";
        if(obj.contentType){
            contentType = obj.contentType;
        }

        //默认异步，可手动设置为同步
        var async = true;
        if(obj.async === false){
        	async = false;
        }

        //设置请求参数
        var data = obj.data;
        if(contentType == "application/json"){
            data = JSON.stringify(obj.param);
        }
    	var options = {
    			contentType:contentType,
    		    dataType : "json",
                traditional:true,
                cache:false,
                timeout: timeout,
                type: type,
                async:async,
                url: obj.url+"?timestamp="+new Date().getTime(),
                data: data,
                beforeSend:function(){
                 layer.load(1, {
                             shade: [0.1, '#fff'] //0.1透明度的白色背景
                      });
                },
                success: function(data, textStatus, jqXHR){
                    if("200"!=data.status){
                        //服务器错误
                        layer.msg(data.msg);
                    }else{
                        obj.success(data, textStatus, jqXHR);
                    }
                },
                error: function(e, xhr, type){
                     if(obj.error){
                         obj.error(e, xhr, type);
                     }else{
                         layer.msg("连接异常，请稍后重试");
                     }
                },
                complete:function(){
                      layer.closeAll('loading');
                }
            };
        $.ajax(options);
}


