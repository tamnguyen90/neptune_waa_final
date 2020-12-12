$(document).ready(function() {

    var win = $(window);
    var doc = $(document);
    let pageNum = 1;
    // var totalPage = data.charAt(data.length-1);

    // Each time the user scrolls
    win.scroll(function() {
        var totalPage=2;

        // Vertical end reached?
        if (doc.height() - win.height() == win.scrollTop() && totalPage>=pageNum) {
            // New row
            pageNum = pageNum+1;

            // $("#nextPage").submit(function ());
            let data = JSON.stringify($('#nextPage').serializeFormJSON());
            console.log(data);
            var conextRoot = "/" + window.location.pathname.split('/')[1];

            $.ajax({
                type: "POST",

                url: conextRoot + "/category/productsNext/",
                data: data,
                contentType: "application/json",
                success: function (data) {
                    if(data=="done" ||data==null){
                        console.log("do nothing");
                    }
                    else {
                        totalPage = parseInt(data.charAt(data.length - 1));
                        var newdata = data.substring(1, data.length - 2);
                        var arr = newdata.split(",");
                        for (var i = 0; i < arr.length; i++) {
                            console.log(arr[i]);
                            var objmo = arr[i].replaceAll(";", ",");
                            console.log(objmo);

                            var obj = JSON.parse(objmo);
                            console.log(obj["productId"]);

                            dispList(obj)
                        }

                        console.log(obj);
                    }

                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log('error');

                }
            });
            // tr.appendTo($('#dataTable'));
            // var tr = $('<tr />').append($('<td />')).appendTo($('#dataTable'));
            //
            // // Current number of columns to create
            // var n_cols = $('#dataTable tr:first-child th').length;
            // for (var i = 0; i < n_cols; ++i)
            //     tr.append($('<td />'));
        }

        // Horizontal end reached?
        // if (doc.width() - win.width() == win.scrollLeft()) {
        //     // New column in the heading row
        //     $('#dataTable tr:first-child').append($('<th />'));
        //
        //     // New column in each row
        //     $('#dataTable tr:not(:first-child)').each(function() {
        //         $(this).append($('<td />'));
        //     });
        // }
    });
});
(function ($) {
    $.fn.serializeFormJSON = function () {

        var o = {};
        var a = this.serializeArray();
        $.each(a, function () {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };
})(jQuery);

function dispList(resp) {
    // Remove old Data
    console.log(resp)

    // Update new data
    let $startrow='<tr>\n';
    let $endrow = '</tr>\n';
    let $productId=resp["productId"];

    // $.each(resp, function(i, data){
        // New Row
        // console.log(data);
        $('#dataTable').append($startrow);
        let $row ='<tbody>\n'+

            '                                <tr>\n' +
            '                                    <td >'+ resp["productId"]+'</td>\n'+
            '                                    <td >'+ resp["productName"]+'</td>\n' +
            '                                    <td >'+ resp["productDescription"]+'</td>\n' +
            '                                    <!--                                    <td th:text="${product.imageList}">Edinburgh</td>-->\n' +
            '                                    <td >'+ resp["productPrice"]+'</td>\n' +
            '                                    <td >'+ resp["uploadDate"]+'</td>\n' +
            '                                    <th><a href="/customer/product?id='+ resp["productId"]+'" class="btn ui-state-default">View Product Details</a></th>\n'+
            // '                                    <!--                                    <td>-->\n' +
            // '                                    <!--                                        <a class="edit" sec:authorize="hasAuthority(\'ADMIN\')"-->\n' +
            // '                                    <!--                                           th:href="@{/users/edit/{username}(username=${user.username})}">-->\n' +
            // '                                    <!--                                            <i class="material-icons" data-toggle="tooltip"-->\n' +
            // '                                    <!--                                               title="Edit">&#xE254;</i></a>-->\n' +
            // '                                    <!--                                        <a class="delete" sec:authorize="hasAuthority(\'ADMIN\')"-->\n' +
            // '                                    <!--                                           th:href="@{/users/delete/{username}(username=${user.username})}">-->\n' +
            // '                                    <!--                                            <i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>-->\n' +
            // '                                    <!--                                    </td>-->\n' +
            '                                </tr>\n' +
            '                                </tbody>';
        $('#dataTable').append($row);
        $('#dataTable').append($endrow);
    // });

}