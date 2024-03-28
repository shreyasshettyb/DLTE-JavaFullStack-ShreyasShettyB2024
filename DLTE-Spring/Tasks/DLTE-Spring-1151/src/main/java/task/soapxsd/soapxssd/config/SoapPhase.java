package task.soapxsd.soapxssd.config;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import service.transaction.*;
import task.soapxsd.soapxssd.dao.model.Transaction;
import task.soapxsd.soapxssd.dao.service.TransactionService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Endpoint
public class SoapPhase {

    @Autowired
    private TransactionService transactionService;

    private final String url = "http://transaction.service";

    @PayloadRoot(namespace = url, localPart = "save")
    @ResponsePayload
    public AddTransactionResponse addTransactionResponse(@RequestPayload AddTransactionRequest addTransactionRequest) {
        AddTransactionResponse addTransactionResponse = new AddTransactionResponse();
        ServiceStatus serviceStatus = new ServiceStatus();

        service.transaction.Transaction input = addTransactionRequest.getTransaction();
        Transaction daoTransaction = new Transaction();
        BeanUtils.copyProperties(input, daoTransaction);
        daoTransaction = transactionService.apiAddTransaction(daoTransaction);

        if (daoTransaction != null) {
            serviceStatus.setStatus("SUCCESS");
            BeanUtils.copyProperties(daoTransaction, input);
            addTransactionResponse.setTransaction(input);
            serviceStatus.setMessage(input.getTransactionId() + "Transaction added");
        } else {
            serviceStatus.setStatus("Failure");
            serviceStatus.setMessage(input.getTransactionId() + "insertion fail");
        }

        addTransactionResponse.setServiceStatus(serviceStatus);

//        System.out.println(newTransactionResponse.getServiceStatus().toString());

        return addTransactionResponse;
    }

    @PayloadRoot(namespace = url, localPart = "findBySenderRequest")
    @ResponsePayload
    public FindBySenderResponse findBySenderResponse(@RequestPayload FindBySenderRequest findBySenderRequest) {
        FindBySenderResponse findBySenderResponse = new FindBySenderResponse();
        ServiceStatus serviceStatus = new ServiceStatus();

        List<service.transaction.Transaction> returnTransaction = new ArrayList<>();
        List<Transaction> transactionsList = transactionService.apiFindBySender(findBySenderRequest.getSentTo());

        Iterator<Transaction> iterator = transactionsList.iterator();
        while (iterator.hasNext()) {
            service.transaction.Transaction currentTransaction = new service.transaction.Transaction();
            BeanUtils.copyProperties(iterator.next(), currentTransaction);
            returnTransaction.add(currentTransaction);
        }

        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Transaction available");

        findBySenderResponse.setServiceStatus(serviceStatus);
        findBySenderResponse.getTransaction().addAll(returnTransaction);

        return findBySenderResponse;
    }

    @PayloadRoot(namespace = url, localPart = "findByReceiverRequest")
    @ResponsePayload
    public FindByReceiverResponse findByReceiverResponse(@RequestPayload FindByReceiverRequest findByReceiverRequest) {
        FindByReceiverResponse findByReceiverResponse = new FindByReceiverResponse();
        ServiceStatus serviceStatus = new ServiceStatus();

        List<service.transaction.Transaction> returnTransaction = new ArrayList<>();
        List<Transaction> transactionsList = transactionService.apiFindByReceiver(findByReceiverRequest.getReceiver());

        Iterator<Transaction> iterator = transactionsList.iterator();
        while (iterator.hasNext()) {
            service.transaction.Transaction currentTransaction = new service.transaction.Transaction();
            BeanUtils.copyProperties(iterator.next(), currentTransaction);
            returnTransaction.add(currentTransaction);
        }
        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Transaction available");

        findByReceiverResponse.setServiceStatus(serviceStatus);
        findByReceiverResponse.getTransaction().addAll(returnTransaction);

        System.out.println(findByReceiverResponse.getTransaction().toString());

        return findByReceiverResponse;
    }

    @PayloadRoot(namespace = url, localPart = "findByAmountRequest")
    @ResponsePayload
    public FindByAmountResponse findByAmountResponse(@RequestPayload FindByAmountRequest findByAmountRequest) {
        FindByAmountResponse findByAmountResponse = new FindByAmountResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        List<service.transaction.Transaction> returnTransaction = new ArrayList<>();

        List<Transaction> transactionsList = transactionService.apiFindByAmount(findByAmountRequest.getAmount());

        Iterator<Transaction> iterator = transactionsList.iterator();
        while (iterator.hasNext()) {
            service.transaction.Transaction currentTransaction = new service.transaction.Transaction();
            BeanUtils.copyProperties(iterator.next(), currentTransaction);
            returnTransaction.add(currentTransaction);
        }

        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Transaction available");

        findByAmountResponse.setServiceStatus(serviceStatus);
        findByAmountResponse.getTransaction().addAll(returnTransaction);

        return findByAmountResponse;
    }


    @PayloadRoot(namespace = url, localPart = "updateRemarksRequest")
    @ResponsePayload
    public UpdateRemarksResponse updatingTransaction(@RequestPayload UpdateRemarksRequest updateRemarksRequest) {
        UpdateRemarksResponse updateRemarksResponse = new UpdateRemarksResponse();
        ServiceStatus serviceStatus = new ServiceStatus();

        String result = transactionService.apiUpdateRemarks(updateRemarksRequest.getRemarks(), updateRemarksRequest.getTransactionId());
        if (result.equals("success")) {
            serviceStatus.setStatus("updated");
        } else {
            serviceStatus.setStatus("update fail");
        }
        serviceStatus.setMessage(result);

        updateRemarksResponse.setServiceStatus(serviceStatus);

        return updateRemarksResponse;
    }

    @PayloadRoot(namespace = url, localPart = "removeTransactionByDates")
    @ResponsePayload
    public RemoveTransactionByDatesResponse removeTransactionByDatesResponse(@RequestPayload RemoveTransactionByDatesRequest removeTransactionByDatesRequest) {
        RemoveTransactionByDatesResponse removeTransactionByDatesResponse = new RemoveTransactionByDatesResponse();
        ServiceStatus serviceStatus = new ServiceStatus();

        String result = transactionService.apiRemoveTransactionByDates(removeTransactionByDatesRequest.getStartDate(), removeTransactionByDatesRequest.getEndDate());
        if (result.equals("success")) {
            serviceStatus.setStatus("removed");
        } else {
            serviceStatus.setStatus("remove failed");
        }
        serviceStatus.setMessage(result);

        removeTransactionByDatesResponse.setServiceStatus(serviceStatus);

        return removeTransactionByDatesResponse;
    }
}
