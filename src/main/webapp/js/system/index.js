/**
 * Created by cheng on 2017/7/25.
 */

/**
 * 方法描述：单机一级菜单（页面上部的菜单），初始化子菜单（即页面左部菜单）
 * @param element 当前节点
 */
function clickFirstMenu(element) {
    //判断当前单击的节点是否是[选中模式]，如果是[选中模式],不再触发单机事件
    if ($(element).attr("class") != "on") {
        //将同级节点的[选中模式]清空
        $("#mainMenuUl").children().attr("class", "");
        //将当前节点置为[选中模式]
        $(element).attr("class", "on");
        //加载二级菜单内容
        $("#menuDiv").html("<h3 onclick='clickSecondMenu(this)'><a>广告管理</a></h3>");
    }
}

/**
 * 方法描述：单机二级菜单（页面左部菜单），初始化主页面
 * @param element 当前节点
 */
function clickSecondMenu(element,path) {
    //将其他有[选中模式]的节点清空
    $("#menuDiv").attr("class", "");
    //将当前单机的节点置为[选中模式]
    $(element).attr("class", "on");
    //将当前页面跳转到指定的地址
    $("#mainPage").attr("src",path);
}