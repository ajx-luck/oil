/**
 * Created by B04e on 2017/12/4.
 */
function img()
{
    if (navigator.userAgent.match(/(iPhone|iPod|iPad);?/i)) {//区分iPhone设备
        window.getSelection().removeAllRanges();//这段代码必须放在前面否则无效
        var Url2=document.getElementById("biaoios");//要复制文字的节点
        var range = document.createRange();
        // 选中需要复制的节点
        range.selectNode(Url2);
        // 执行选中元素
        window.getSelection().addRange(range);
        // 执行 copy 操作
        var successful = document.execCommand('copy');

        // 移除选中的元素
        window.getSelection().removeAllRanges();
    }else{
        var Url2=document.getElementById("biao1");//要复制文字的节点
        Url2.select(); // 选择对象
        document.execCommand("Copy"); // 执行浏览器复制命令
    }


}

/*//获取url中的参数
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
}*/

function getUrlParam(name){
    // 用该属性获取页面 URL 地址从问号 (?) 开始的 URL（查询部分）
    var url = window.location.search;
    // 正则筛选地址栏
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    // 匹配目标参数
    var result = url.substr(1).match(reg);
    //返回参数值
    return result ? decodeURIComponent(result[2]) : null;
}

function setCookie(name, value, liveMinutes) {
    if (liveMinutes == undefined || liveMinutes == null) {
        liveMinutes = 60 * 2;
    }
    if (typeof (liveMinutes) != 'number') {
        liveMinutes = 60 * 2;//默认120分钟
    }
    var minutes = liveMinutes * 60 * 1000;
    var exp = new Date();
    exp.setTime(exp.getTime() + minutes + 8 * 3600 * 1000);
    //path=/表示全站有效，而不是当前页
    document.cookie = name + "=" + value + ";path=/;expires=" + exp.toUTCString();
}


