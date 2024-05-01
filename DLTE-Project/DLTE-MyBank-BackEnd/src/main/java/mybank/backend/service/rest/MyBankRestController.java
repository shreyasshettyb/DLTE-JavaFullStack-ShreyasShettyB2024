package mybank.backend.service.rest;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import mybank.db.dao.dltemybankdaolayer.MyBankRemote;
import mybank.db.dao.dltemybankdaolayer.entity.DepositsAvailed;
import mybank.db.dao.dltemybankdaolayer.exception.DepositsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

@RestController
@RequestMapping("/mybank/deposits")
@Validated
//@ComponentScan("mybank.db.dao.dltemybankdaolayer")
public class MyBankRestController {

    Logger logger = LoggerFactory.getLogger(MyBankRestController.class);
    ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    @Autowired
    MyBankRemote myBankRemote;

    //Post Mapping for Avail Deposit
    @PostMapping("/avail")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deposit added successfully"),
            @ApiResponse(responseCode = "403", description = "Customer is Inactive"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Object> availDepositApi(@Valid @RequestBody DepositsAvailed depositsAvailRequest) {
        try {
            myBankRemote.availDeposits(depositsAvailRequest);
            logger.info(resourceBundle.getString("app.rest.success"));
        } catch (DepositsException depositsException) {
            logger.error(depositsException.toString());
            return ResponseEntity.status(HttpServletResponse.SC_OK).body("0xR001 "+depositsException.getMessage());
        } catch (SQLException e) {
            logger.error(e.toString());
            return ResponseEntity.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).body(resourceBundle.getString("app.rest.error.unknown"));
        }
        return ResponseEntity.ok(depositsAvailRequest);
    }

    //Exception Handler for BAD REQUEST
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
            logger.error(errorMessage);
        });
        return errors;
    }

}
