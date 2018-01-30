function mybooks() {
    var head2 = document.getElementById("head2");
    var heads = head2.getElementsByTagName("a");
    var push = document.getElementById("push");
    var borrow = document.getElementById("borrow");
    heads[1].onclick = function () {
        push.style.display = "none";
        borrow.style.display = "block";
        heads[0].style.color = "black";
        heads[0].style.borderBottomColor = "transparent";
        this.style.color = "#5fb878";
        this.style.borderBottomColor = "#5fb878";
    }
    heads[0].onclick = function () {
        push.style.display = "block";
        borrow.style.display = "none";
        this.style.color = "#5fb878";
        this.style.borderBottomColor = "#5fb878";
        heads[1].style.color = "black";
        heads[1].style.borderBottomColor = "transparent";
    }
}

function box() {

    var form = document.getElementById("box");
    var mask = document.getElementById("mask");
    var height = document.documentElement.clientHeight || document.body.clientHeight;
    var width = document.documentElement.clientWidth || document.body.clientWidth;
    var Top = (height - 365) / 2;
    var left = (width - 400) / 2;
    form.style.top = Top + "px";
    form.style.left = left + "px";
    var button = document.getElementsByClassName("modify");
    for (var i = 0, len = button.length; i < len; i++) {
        button[i].onclick = function () {
            mask.style.display = "block";
            form.style.display = "block";
            mask.style.zIndex = "1";
        }
        var close = document.getElementById("close");
        close.onclick = function () {
            mask.style.zIndex = "-1";
            mask.style.display = "none";
            form.style.display = "none";
        }
    }
}
addLoadEvent(mybooks);
addLoadEvent(box);