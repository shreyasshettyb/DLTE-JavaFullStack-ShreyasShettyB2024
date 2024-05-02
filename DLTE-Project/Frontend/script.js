const getAllDeposits = () => {
    let soapRequest = `<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:dep="http://deposits.services">
<soapenv:Header/>
<soapenv:Body>
<dep:viewAllDepositsAvailableRequest/>
</soapenv:Body>
</soapenv:Envelope>`;

    $.ajax({
        url: "http://localhost:8082/depositsrepo",
        type: "POST",
        dataType: "xml",
        beforeSend: function (handler) {
            handler.setRequestHeader("Authorization", "Basic " + btoa("shreyas12:shreyas123"));
            handler.setRequestHeader("SOAPAction", "viewAllDepositsAvailableRequest");
        },
        contentType: "text/xml;charset=utf-8",
        data: soapRequest,
        success: function (response) {

            $('#depositList').empty();


            $(response).find('ns2\\:depositsAvailable').each(function () {

                const depositId = $(this).find('ns2\\:depositId').text();
                const depositName = $(this).find('ns2\\:depositName').text();
                const depositType = $(this).find('ns2\\:depositType').text();
                const depositDescription = $(this).find('ns2\\:depositDescription').text();
                const depositRoi = $(this).find('ns2\\:depositRoi').text();
                const depositHtml = `
                <div class="col-md-4 mb-4">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">${depositName}</h5>
                            <p class="card-text">Type: ${depositType}</p>
                            <p class="card-text">Description: ${depositDescription}</p>
                            <p class="card-text">ROI: ${depositRoi}%</p>
                        </div>
                    </div>
                </div>`;
                $('#depositList').append(depositHtml);
            });
        },
        error: function (xhr, status, error) {
            console.error(xhr.responseText);
        }
    });
}
$(document).ready(function () {
    getAllDeposits();
});