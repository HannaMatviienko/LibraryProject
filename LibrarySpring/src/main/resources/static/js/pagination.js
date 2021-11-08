function getQuery() {
    let q = '';
    document.querySelectorAll(".qcol").forEach(function(item) {
        if (item.value)
        {
            const col = item.getAttribute("qcol");
            if (col)
                q = q + '&' + col + '=' + item.value;
        }
    });
    return q;
}

window.onload = function(){
    document.querySelectorAll(".sort").forEach(function(item) {
        item.addEventListener('click', function(e) {
            e.preventDefault();
            const page = document.getElementById("page").getAttribute("page");
            let dir = this.getAttribute("sdir");
            const sort = this.getAttribute("scol");
            const scol = document.getElementById("page").getAttribute("scol");
            if (scol === sort)
            {
               if (dir === 'asc')
                   dir = 'desc';
               else
                   dir = 'asc';
            }
            window.location.href = '?s=' + sort + '&d=' + dir + '&p=' + page + getQuery();
        });
    });

    document.querySelectorAll(".page-link").forEach(function(item) {
        item.addEventListener('click', function(e) {
            e.preventDefault();
            if (this.parentElement.classList.contains('active'))
                return;
            const page = this.getAttribute("page");
            const sort = document.getElementById("page").getAttribute("scol");
            const dir = document.getElementById("page").getAttribute("sdir");
            window.location.href = '?s=' + sort + '&d=' + dir + '&p=' + page + getQuery();
        });
    });

    document.querySelectorAll(".find").forEach(function(item) {
        item.addEventListener('click', function(e) {
            const page = document.getElementById("page");
            window.location.href =
                '?s=' + page.getAttribute("scol") +
                '&d=' + page.getAttribute("sdir") +
                '&p=' + page.getAttribute("page") +
                getQuery();
        });
    });

    document.querySelectorAll(".clear").forEach(function(item) {
        item.addEventListener('click', function(e) {
            const page = document.getElementById("page");
            window.location.href =
                '?s=' + page.getAttribute("scol") +
                '&d=' + page.getAttribute("sdir") +
                '&p=' + page.getAttribute("page");
        });
    });
};
