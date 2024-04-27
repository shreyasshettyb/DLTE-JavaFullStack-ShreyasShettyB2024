function initiate() {
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

function iterateDeposits() {
    let repository = JSON.parse(localStorage.getItem('deposits'));
    let info = "";

    repository.forEach((deposits, index) => {

        info += `<div class="box">
        <div class="body">
            <div class="imgContainer">
                <h3>${deposits.DEPOSIT_NAME}</h3>
                <h3>${deposits.DEPOSIT_TYPE}</h3>
            </div>
            <div class="content d-flex flex-column align-items-center justify-content-center">
                <div>
                    <h3 class="text-white fs-5">${deposits.DEPOSIT_ROI}</h3>
                    <p class="fs-6 text-white">${deposits.DEPOSIT_DESCRIPTION}</p>
                </div>
            </div>
        </div>
    </div>`;
    });

    document.getElementById("depositList").innerHTML = info;
}

iterateDeposits();