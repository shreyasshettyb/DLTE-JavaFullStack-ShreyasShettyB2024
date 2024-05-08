package mybank.backend.service.rest;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import mybank.db.dao.dltemybankdaolayer.entity.DepositsAvailed;
import mybank.db.dao.dltemybankdaolayer.exception.DepositsException;
import mybank.db.dao.dltemybankdaolayer.remotes.CustomerAuthInterface;
import mybank.db.dao.dltemybankdaolayer.remotes.MyBankRemote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;

@RestController
@RequestMapping("/mybank/deposits")
@Validated
//@ComponentScan("mybank.db.dao.dltemybankdaolayer")
public class MyBankRestController {

    @Autowired
    CustomerAuthInterface customerAuthInterface;

    Logger logger = LoggerFactory.getLogger(MyBankRestController.class);
    ResourceBundle resourceBundle = ResourceBundle.getBundle("backend");

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
        Object[] responseOut = new Object[2];
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Long customerId = customerAuthInterface.findByUsername(authentication.getName()).getCustomerId();
            depositsAvailRequest.setCustomerId(customerId);

            Double depositRoi = Objects.requireNonNull(myBankRemote.availableDeposits().stream().filter(each -> each.getDepositId() == depositsAvailRequest.getDepositId()).findAny().orElse(null)).getDepositRoi();
            Double maturityAmount = depositsAvailRequest.getDepositAmount() * (1 + (depositRoi * depositsAvailRequest.getDepositDuration()) / 100);
            depositsAvailRequest.setDepositMaturityAmt(maturityAmount);
            myBankRemote.availDeposits(depositsAvailRequest);
            responseOut[0]= "Success";
            responseOut[1]=depositsAvailRequest;
            logger.info(resourceBundle.getString("app.rest.success"));
        } catch (DepositsException depositsException) {
            logger.error(depositsException.toString());
            responseOut[0]="Error";
            responseOut[1]= resourceBundle.getString(depositsException.getMessage());
            return ResponseEntity.status(HttpServletResponse.SC_OK).body(responseOut);
        } catch (SQLException e) {
            responseOut[0]="Error";
            responseOut[1]= resourceBundle.getString(e.getMessage());
            resourceBundle.getString("app.rest.error.unknown");
            return ResponseEntity.status(HttpServletResponse.SC_OK).body(responseOut);
        }
        return ResponseEntity.ok(responseOut);
    }



    //Exception Handler for Bean Validation
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Object[] responseOut = new Object[2];
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = resourceBundle.getString(Objects.requireNonNull(error.getDefaultMessage()));
            errors.put(fieldName, errorMessage);
            logger.error(errorMessage);
        });
        responseOut[0]="Error";
        responseOut[1]=errors;
        return responseOut;
    }

}
