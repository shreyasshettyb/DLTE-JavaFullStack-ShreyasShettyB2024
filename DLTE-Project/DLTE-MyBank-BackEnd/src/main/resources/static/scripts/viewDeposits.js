let currentPage = 1;
const pageSize = 3;
// Function to fetch all deposits using AJAX
const listAll = (page) => {
    let soapRequest = `<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:dep="http://deposits.services">
   <soapenv:Header/>
   <soapenv:Body>
      <dep:viewAllDepositsAvailableRequest/>
   </soapenv:Body>
</soapenv:Envelope>`
    $.ajax({
        url: 'http://localhost:8082/depositsrepo',
        type: 'POST',
        dataType: 'xml',
        beforeSend: function (handler) {
            handler.setRequestHeader("SOAPAction", "viewAllDepositsAvailableRequest");
        },
        contentType: "text/xml;charset=utf-8",
        data: soapRequest,
        success: function (response) {
            // alert(JSON.stringify(response))
            // console.log(response)
            $('#depositList').empty();

            let count = 0;
            $(response).find('ns2\\:depositsAvailable').each(function () {
                if (count >= (page - 1) * pageSize && count < page * pageSize) {
                    var depositId = $(this).find('ns2\\:depositId').text();
                    var depositName = $(this).find('ns2\\:depositName').text();
                    var depositRoi = $(this).find('ns2\\:depositRoi').text();
                    var depositType = $(this).find('ns2\\:depositType').text();
                    var depositDescription = $(this).find('ns2\\:depositDescription').text();
                    const depositHtml = `
                            <div class="col-md-4 mb-4">
                                <div class="card">
                                    <div class="card-body">
                                        <h5 class="card-title">${depositName}</h5>
                                        <p class="card-text">ROI: ${depositRoi}</p>
                                        <button type="button" class="btn" data-bs-toggle="modal" data-bs-target="#knowmore${depositId}" style="background-color: #f25c19;color: white">
                                        Know More
                                        </button>
                                    </div>
                                </div>
                                <div class="modal fade" id="knowmore${depositId}" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h1 class="modal-title fs-5" id="staticBackdropLabel" style="color: #182052">${depositName}</h1>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"><span aria-hidden="true"></span></button>
                                            </div>
                                            <div class="modal-body" style="color: #182052">
                                                <h5>Type: ${depositType}</h5>
                                                <h5>ROI: ${depositRoi}</h5>
                                                <h5>${depositDescription}</h5>
                                            </div>
                                            <div class="modal-footer">
                                               <a href="/apply?depositName=${depositName}"> <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" >Apply</button></a>
                                                <a href="/calculate?roi=${depositRoi}"><button type="button" class="btn btn-primary" >Calculate returns</button></a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>`;
                    $('#depositList').append(depositHtml);
                }
                count++;
            });

        },
        error: function (xhr, status, error) {
            console.error(xhr.responseText);
        }
    });
}
$(document).ready(() => {
    $('#page').text(currentPage);
    listAll(currentPage); // Initially load the first page
});

// Function to handle next and previous pagination
$('#pagination-next').on('click', function (event) {
    event.preventDefault();
    if (currentPage < 2) {
        currentPage++;
        listAll(currentPage);
        $('#page').text(currentPage);
    }
});
$('#pagination-next-btn').on('click', function (event) {
    event.preventDefault();
    if (currentPage < 2) {
        currentPage++;
        listAll(currentPage);
        $('#page').text(currentPage);
    }
});

$('#pagination-prev').on('click',function (event) {
    event.preventDefault();
    if (currentPage > 1) {
        currentPage--;
        listAll(currentPage);
        $('#page').text(currentPage);
    }
});
$('#pagination-prev-btn').on('click',function (event) {
    event.preventDefault();
    if (currentPage > 1) {
        currentPage--;
        listAll(currentPage);
        $('#page').text(currentPage);
    }
});