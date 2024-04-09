package employeebackend.service.validation;


import employeebackend.service.entity.Employee;
import employeebackend.service.exceptions.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;

public class Validation {
    static Logger logger = LoggerFactory.getLogger(Validation.class);

    public void validateEmployee(Employee employee) throws ValidationException {
        if (!Pattern.matches("^[A-Za-z]+$", employee.getFirstName())) {
            logger.warn("Invalid First Name Format");
            throw new ValidationException("VAL-001");
        }

        if (!Pattern.matches("^[A-Za-z]*$", employee.getMiddleName())) {
            logger.warn("Invalid Middle Name Format");
            throw new ValidationException("VAL-002");
        }

        if (!Pattern.matches("^[A-Za-z]*$", employee.getLastName())) {
            logger.warn("Invalid Last Name Format");
            throw new ValidationException("VAL-003");
        }
        if (!Pattern.matches("[0-9]{10}", employee.getPhone().toString())) {
            logger.warn("Invalid Phone Format");
            throw new ValidationException("VAL-004");
        }

        if (!Pattern.matches("^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$", employee.getEmail())) {
            logger.warn("Invalid Email Format");
            throw new ValidationException("VAL-005");
        }
//      Scanner scanner = new Scanner(System.in);

        if (employee.getPermanentAddress().getHouseName().length() == 0) {
            logger.warn("House Name Must be provided");
            throw new ValidationException("VAL-006");
        }
        if (employee.getPermanentAddress().getStreetName().length() == 0) {
            logger.warn("Street Name Must be provided");
            throw new ValidationException("VAL-007");
        }
        if (employee.getPermanentAddress().getCity().length() == 0) {
            logger.warn("City Must be provided");
            throw new ValidationException("VAL-008");
        }
        if (employee.getPermanentAddress().getState().length() == 0) {
            logger.warn("State Must be provided");
            throw new ValidationException("VAL-009");
        }
        if (!Pattern.matches("[0-9]{6}", employee.getPermanentAddress().getPincode().toString())) {
            logger.warn("Invalid Pincode Format");
            throw new ValidationException("VAL-010");
        }
        if (employee.getTemporaryAddress().getHouseName().length() == 0) {
            logger.warn("House Name Must be provided");
            throw new ValidationException("VAL-011");
        }
        if (employee.getTemporaryAddress().getStreetName().length() == 0) {
            logger.warn("Street Name Must be provided");
            throw new ValidationException("VAL-012");
        }
        if (employee.getTemporaryAddress().getCity().length() == 0) {
            logger.warn("City Must be provided");
            throw new ValidationException("VAL-013");
        }
        if (employee.getTemporaryAddress().getState().length() == 0) {
            logger.warn("State Must be provided");
            throw new ValidationException("VAL-014");
        }
        if (!Pattern.matches("[0-9]{6}", employee.getTemporaryAddress().getPincode().toString())) {
            logger.warn("Invalid Pincode Format");
            throw new ValidationException("VAL-015");
        }
//        return new Address(employeeID, permanentHouseName, permanentStreetName, permanentCity, permanentState, permanentPincode);
    }

}
