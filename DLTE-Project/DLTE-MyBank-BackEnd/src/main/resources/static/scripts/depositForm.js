$(document).ready(() => {
    let today = new Date();
    let dd = String(today.getDate()).padStart(2, '0');
    let mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
    let yyyy = today.getFullYear();
    today = yyyy + '-' + mm + '-' + dd;
    $("#date").val(today);


    $("#reset").click((event) => {
        event.preventDefault();
        let today = new Date();
        let dd = String(today.getDate()).padStart(2, '0');
        let mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
        let yyyy = today.getFullYear();
        today = yyyy + '-' + mm + '-' + dd;

        $("#Duration").val("");
        $("#Amount").val("");
        $("#date").val(today);
    });

    $("#submit").click((event) => {
        event.preventDefault();
        if(validation()) {
            const depositId = [[${depositId}]];
            const depositAmount = $("#Amount").val();
            const depositDuration = $("#Duration").val();
            const depositMaturity = $("#date").val();

            const request = {
                "depositId": depositId,
                "depositAmount": depositAmount,
                "depositDuration": depositDuration,
                "depositMaturity": depositMaturity
            };

            console.log(request);
            $.ajax({
                url: "http://localhost:8082/mybank/deposits/avail",
                type: "POST",
                dataType: "json",
                contentType: "application/json;charset=utf-8",
                data: JSON.stringify(request),
                success: function(response) {
                    if (response[0] === "Success") {
                        var dateString = response[1].depositMaturity;
                        var date = new Date(dateString);
                        var formattedDate = date.toLocaleDateString("en-US", { year: "numeric", month: "2-digit", day: "2-digit" }).replace(/\//g, "-");
                        const deposit = `<h4>Deposit Amount:<span>${response[1].depositAmount}</span></h4><h4>Deposit Duration:<span>${response[1].depositDuration}</span></h4><h4>Deposit Maturity Date: <span>${formattedDate}</span></h4><h4>Deposit Maturity Amount: <span>${response[1].depositMaturityAmt}</span></h4>`;
                        $("#depositDetails").html(deposit);
                        $("#ackModal").show();
                    } else {
                        alert("Error: " + response[1]); // Display error message
                    }
                },
                error: function(err) {
                    console.log(err);
                    alert("Error: " + err.status);
                }
            });
        }
    });
});

function validation(){
    const amountInput = $("#Amount").val();
    const durationInput = $("#Duration").val();
    let isValid = true;

    // Amount validation
    if (amountInput.trim() === '') {
        $("#amountError").text("Amount Cannot be Empty")
        isValid = false;
    }
    else if(amountInput<0){
        $("#amountError").text('Amount cannot be Zero or less than Zero');
        isValid = false;
    }
    else {
        $("#amountError").text('');
    }

    // Duration validation
    if (durationInput.trim() === '') {
        $("#durationError").text('Duration is required');
        isValid = false;
    }
    else if (!Number.isInteger(Number(durationInput))) {
        $("#durationError").text('Duration must be a whole number');
        isValid = false;
    }
    else if(durationInput<100){
        $("#durationError").text('Duration cannot be 100');
        isValid = false;
    }
    else {
        $("#durationError").text('');
    }

    // Terms and Conditions validation
    if (!$("#tAndC").is(':checked')) {
        $("#tAndCError").text('You must accept the terms and conditions');
        isValid = false;
    } else {
        $("#tAndCError").text('');
    }
    return isValid
}