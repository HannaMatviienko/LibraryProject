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
            window.location.href = '?s=' + sort + '&d=' + dir + '&p=' + page;
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
            window.location.href = '?s=' + sort + '&d=' + dir + '&p=' + page;
        });
    });
};
