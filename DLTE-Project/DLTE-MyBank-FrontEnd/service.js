function initiate() {
    // Populate repository with deposits data
    let repository = [

            {
              "DEPOSIT_ID": 1000001,
              "DEPOSIT_NAME": "FD",
              "DEPOSIT_ROI": 10.2,
              "DEPOSIT_TYPE": "Lump Sum",
              "DEPOSIT_DESCRIPTION": "Standard Fixed Deposit"
            },
            {
              "DEPOSIT_ID": 1000002,
              "DEPOSIT_NAME": "RD",
              "DEPOSIT_ROI": 9.2,
              "DEPOSIT_TYPE": "Recurring",
              "DEPOSIT_DESCRIPTION": "Standard Recurring Deposit"
            },
            {
              "DEPOSIT_ID": 1000003,
              "DEPOSIT_NAME": "Senior Citizen FD",
              "DEPOSIT_ROI": 11.5,
              "DEPOSIT_TYPE": "Lump Sum",
              "DEPOSIT_DESCRIPTION": "Senior Citizen Fixed Deposit"
            },
            {
              "DEPOSIT_ID": 1000004,
              "DEPOSIT_NAME": "Employee FD",
              "DEPOSIT_ROI": 10.7,
              "DEPOSIT_TYPE": "Lump Sum",
              "DEPOSIT_DESCRIPTION": "Employee Fixed Deposit"
            },
            {
              "DEPOSIT_ID": 1000005,
              "DEPOSIT_NAME": "Women Empowerment RD",
              "DEPOSIT_ROI": 10.2,
              "DEPOSIT_TYPE": "Recurring",
              "DEPOSIT_DESCRIPTION": "Women Empowerment Recurring Deposit"
            }
               
    ];


    localStorage.setItem('deposits', JSON.stringify(repository));
}

initiate();


function iterateDeposits()  {
    let repository = JSON.parse(localStorage.getItem('deposits'));
    let info = "";

    repository.forEach((deposits, index) => {
        info += `<div class="col">
                        <div class="card text-dark bg-info btn btn-outline-dark p-2 me-4 shadow-lg" data-bs-toggle="modal" data-bs-target="#cardModal${index}">
                            <div class="card-header">${deposits.DEPOSIT_TYPE}</div>
                            <div class="card-body card-content">
                                <h5 class="card-title">${deposits.DEPOSIT_NAME}</h5>
                            </div>
                        </div>
                    </div>`;

        info += `<div class="modal fade" id="cardModal${index}" tabindex="-1" aria-labelledby="cardModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-lg">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="cardModalLabel">More Infomation...</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <div class="card text-dark bg-info">
                                        <div class="card-header">${deposits.DEPOSIT_TYPE}</div>
                                        <div class="card-body">
                                            <h3 class="card-title">${deposits.DEPOSIT_NAME}</h3>
                                            <h5 class="card-body">
                                                <p class="card-text ">deposits ID: ${deposits.DEPOSIT_ID}</p>
                                                <p class="card-text ">deposits Type: ${deposits.DEPOSIT_TYPE}</p>
                                                <p class="card-text ">Key Benefits: ${deposits.DEPOSIT_DESCRIPTION}</p>
                                                <p class="card-text ">ROI:${deposits.DEPOSIT_ROI}</p>
                                            </h5>
                                            <button type="button" class="btn btn-primary">Apply deposits</button>
                                            </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>`;
    });

    document.getElementById("Cards_Deposits").innerHTML = info;
}
iterateDeposits();