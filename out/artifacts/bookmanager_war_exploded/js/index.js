function addLoadEvent(func) {
    var oldonload = window.onload;
    if (typeof window.onload != 'function') {
        window.onload = func;
    } else {
        window.onload = function() {
            oldonload();
            func();
        }
    }
}

function header_search() {
    var search = document.getElementById("index1_input");
    var input = search.getElementsByTagName("input")[0];
    var btn = search.getElementsByTagName("button");
    var head = document.getElementById("hea");
    var links = search.getElementsByTagName("a");
    var windowWidth = $(window).width();
    if (windowWidth > 425) {
        input.onfocus = function() {
            input.style.width = "80%";
            links[0].style.display = "none";
            links[1].style.display = "none";
            btn[0].style.marginRight = "0";
        }

    }
    if (windowWidth <= 425) {
        input.onfocus = function() {
            input.style.width = "65%";
            links[0].style.display = "none";
            links[1].style.display = "none";
            btn[0].style.marginRight = "0";
        }
    }
    if (windowWidth <= 375) {
        input.onfocus = function() {
            input.style.width = "55%";
            links[0].style.display = "none";
            links[1].style.display = "none";
            btn[0].style.marginRight = "0";
        }
    }
    if (windowWidth <= 320) {
        input.onfocus = function() {
            input.style.width = "50%";
            links[0].style.display = "none";
            links[1].style.display = "none";
            btn[0].style.marginRight = "0";
        }
    }
    var timer;
    input.onblur = function() {
        input.style.width = "35%";
        timer = setTimeout(function() {
            links[0].style.display = "inline-block";
            links[1].style.display = "inline-block";
            btn[0].style.marginRight = "20px";
        }, 200);
    }
    clearTimeout(timer);
}
addLoadEvent(header_search);