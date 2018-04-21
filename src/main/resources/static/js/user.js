var totalElements = 20;
var defaultPageSize = 5;

function loadUsers(page) {
    $.ajax({
        url: "user/user-table",
        data: {
            page: page
        }
    }).done(function (data) {
        $(".user-table-container").empty().html(data);
        drawPagination(page);
    });
};

function drawPagination(page) {
    $(".pagination-holder").pagination({
        items: totalElements,
        itemsOnPage: defaultPageSize,
        currentPage: page,
        onPageClick: function (pageNumber, event) {
            event.preventDefault();
            loadUsers(pageNumber);
        }
    });

    if (totalElements > defaultPageSize) {
        $(".pagination-container").show();
    } else {
        $(".pagination-container").hide();
    }
}
