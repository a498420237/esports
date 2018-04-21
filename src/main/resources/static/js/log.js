function addtr() {
    var table = $(".user_tbody");
    var count = 0;
    $("table div[class='date form_datetime controls']").each(function () {
        count++;
    });
    var tr = $('#row-template').clone();
    tr.removeClass('hide');
    table.append(tr);
    enableListener();
}

function enableListener() {
    $('input').keyup(function () {
        $(this).closest('tr').addClass('updated');
    });

    $('select').change(function () {
        $(this).closest('tr').addClass('updated');
    })
}

function enableSubmitEvent() {
    $('#log-form').submit(function (e) {
        e.preventDefault();
        var jsonData = [];

        $('tr.updated').each(function () {

            var data = {
                projectName: $(this).find('#projectName').val(),
                contentType: $(this).find('#contentType').val(),
                duration: $(this).find('#duration').val(),
                content: $(this).find('#content').val(),
                status: $(this).find('#status').val(),
                remark: $(this).find('#remark').val()
            };


            jsonData.push(data);
        });
        $.ajax({
            url: "/log/save",
            method: "POST",
            data: JSON.stringify(jsonData),
            contentType: "application/json"
        }).done(function () {
            alert('updatedxxxxx!');
        });
    });
}


