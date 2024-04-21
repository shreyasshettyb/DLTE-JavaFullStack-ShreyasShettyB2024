function validateForm() {
    var accNumber = document.getElementById("acc_number").value;
    var accHolder = document.getElementById("acc_holder").value;
    var accType = document.querySelector('input[name="acc_type"]:checked');
    var chequebookType = document.getElementById("chequebook_type").value;
    var date = document.getElementById("date").value;
    var address = document.getElementById("address").value;
    var signature = document.getElementById("signature").value;
    var phone = document.getElementById("phone").value;
    var email = document.getElementById("email").value;
    var tAndC = document.getElementById("t_and_c").checked;

    // Reset error messages
    document.querySelectorAll('.error').forEach(function (el) {
        el.innerText = "";
    });

    // Null validation
    if (accNumber === "") {
        document.getElementById("acc_number_error").innerText = "Account number is required!";
        return false;
    }
    if (accHolder === "") {
        document.getElementById("acc_holder_error").innerText = "Account holder name is required!";
        return false;
    }
    if (!accType) {
        document.getElementById("acc_type_error").innerText = "Account type is required!";
        return false;
    }
    if (chequebookType === "") {
        document.getElementById("chequebook_type_error").innerText = "Chequebook type is required!";
        return false;
    }
    if (date === "") {
        document.getElementById("date_error").innerText = "Date is required!";
        return false;
    }
    if (address === "") {
        document.getElementById("address_error").innerText = "Address is required!";
        return false;
    }
    if (signature === "") {
        document.getElementById("signature_error").innerText = "Signature is required!";
        return false;
    }
    if (phone === "") {
        document.getElementById("phone_error").innerText = "Phone number is required!";
        return false;
    }
    if (email === "") {
        document.getElementById("email_error").innerText = "Email is required!";
        return false;
    }
    if (!tAndC) {
        document.getElementById("t_and_c_error").innerText = "Please accept terms and conditions!";
        return false;
    }

    // Account number length validation
    if (String(accNumber).length !== 12) {
        document.getElementById("acc_number_error").innerText = "Account number must be 12 digits long!";
        return false;
    }

    // Email validation
    var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailPattern.test(email)) {
        document.getElementById("email_error").innerText = "Invalid email address!";
        return false;
    }

    // Phone number validation
    var phonePattern = /^\d{10}$/;
    if (!phonePattern.test(String(phone))) {
        document.getElementById("phone_error").innerText = "Phone number must be 10 digits long!";
        return false;
    }

    return true; // Form submission
}