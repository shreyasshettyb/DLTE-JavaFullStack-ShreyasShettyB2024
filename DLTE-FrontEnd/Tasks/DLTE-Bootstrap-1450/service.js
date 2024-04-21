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
        },
        {
            "DEPOSIT_ID": 1000009,
            "DEPOSIT_NAME": "Employee FD",
            "DEPOSIT_ROI": 10.7,
            "DEPOSIT_TYPE": "Lump Sum",
            "DEPOSIT_DESCRIPTION": "Employee Fixed Deposit"
        },
        {
            "DEPOSIT_ID": 1000010,
            "DEPOSIT_NAME": "Women Empowerment RD",
            "DEPOSIT_ROI": 10.2,
            "DEPOSIT_TYPE": "Recurring",
            "DEPOSIT_DESCRIPTION": "Women Empowerment Recurring Deposit"
        },
        {
            "DEPOSIT_ID": 1000006,
            "DEPOSIT_NAME": "FD",
            "DEPOSIT_ROI": 10.2,
            "DEPOSIT_TYPE": "Lump Sum",
            "DEPOSIT_DESCRIPTION": "Standard Fixed Deposit"
        },
        {
            "DEPOSIT_ID": 1000007,
            "DEPOSIT_NAME": "RD",
            "DEPOSIT_ROI": 9.2,
            "DEPOSIT_TYPE": "Recurring",
            "DEPOSIT_DESCRIPTION": "Standard Recurring Deposit"
        },
        {
            "DEPOSIT_ID": 1000008,
            "DEPOSIT_NAME": "Senior Citizen FD",
            "DEPOSIT_ROI": 11.5,
            "DEPOSIT_TYPE": "Lump Sum",
            "DEPOSIT_DESCRIPTION": "Senior Citizen Fixed Deposit"
        }
        
    ];

    localStorage.setItem('deposits', JSON.stringify(repository));
}

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

    document.getElementById("container").innerHTML = info;
}


$(document).ready(function() {
    initiate();
    iterateDeposits();
    setupPagination();
});

function setupPagination() {
    let itemsPerPage = 5; // Number of items per page
    let $container = $('#container');
    let $pagination = $('#pagination');
    let repository = JSON.parse(localStorage.getItem('deposits'));

    // Calculate total pages
    let totalPages = Math.ceil(repository.length / itemsPerPage);

    // Create pagination controls
    let paginationControls = '';
    for (let i = 1; i <= totalPages; i++) {
        paginationControls += `<button class="btn btn-primary" onclick="changePage(${i})">${i}</button>`;
    }
    $pagination.html(paginationControls);

    // Function to display items for the selected page
    function displayPage(page) {
        $container.empty();
        let start = (page - 1) * itemsPerPage;
        let end = start + itemsPerPage;
        let slicedData = repository.slice(start, end);
        let info = "";
        slicedData.forEach((deposit, index) => {
            info += `<div class="box">
                        <div class="body">
                            <div class="imgContainer">
                                <h3>${deposit.DEPOSIT_NAME}</h3>
                                <h3>${deposit.DEPOSIT_TYPE}</h3>
                            </div>
                            <div class="content d-flex flex-column align-items-center justify-content-center">
                                <div>
                                    <h3 class="text-white fs-5">${deposit.DEPOSIT_ROI}</h3>
                                    <p class="fs-6 text-white">${deposit.DEPOSIT_DESCRIPTION}</p>
                                </div>
                            </div>
                        </div>
                    </div>`;
        });
        $container.html(info);
    }

    // Initially display the first page
    displayPage(1);

    // Function to change page
    window.changePage = function(page) {
        displayPage(page);
    };
}