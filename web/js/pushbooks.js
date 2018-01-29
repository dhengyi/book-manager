function changeoption(index) {
    var two = [
        [''],
        ['汇编', 'C', 'C++', 'JAVA', 'Python', 'PHP', 'C#', 'JavaScript', 'R', 'MATLAB', 'Go'],
        ['竞赛', '基础数据结构与算法', '进阶'],
        ['设计模式', 'UML', '软件测试', '软件开发'],
        ['MySQL', 'Redis', '关系型数据库', 'NoSQL', 'MongoDb', 'Oracle', '数据库设计与实现原理'],
        ['Linux', 'Unix', 'Windows', '系统开发', '操作系统理论'],
        ['网络管理', '网络组建', '网络协议', '网络理论', 'WebServer'],
        ['JAVA-EE', 'Spring', 'Hibernate', 'Struts', '后台开发'],
        ['HTML', 'CSS', 'JavaScript', 'Jquery', 'Json', 'Ajax', 'node.js', 'ES', 'Vue', 'React'],
        ['机器学习', '深度学习', '神经网络', 'AR、VR'],
        ['Hadoop', 'Sqark', '分布式存储', '数据挖掘', '数据分析'],
        ['内核剖析', '编译原理', '设备与驱动', '嵌入式', 'Vim'],
        ['密码学', '黑客文化', '信息安全', '防火墙'],
    ]
    var ul = document.getElementById("ul");
    var lis = ul.getElementsByTagName("li");
    var j, i, len;
    if (lis.length > 0) {
        ul.innerHTML = "";
    }
    var length = two[index].length;
    for (i = 0; i < length; i++) {
        var li = document.createElement("li");
        text = two[index][i];
        var text = document.createTextNode(text);
        ul.appendChild(li);
        li.appendChild(text);
    }
    var input = document.getElementById("select_input");
    for (i = 0; i < lis.length; i++) {
        lis[i].onclick = function () {
            var litext = this.innerHTML;
            input.value += litext + ' ';
        }
    }
}