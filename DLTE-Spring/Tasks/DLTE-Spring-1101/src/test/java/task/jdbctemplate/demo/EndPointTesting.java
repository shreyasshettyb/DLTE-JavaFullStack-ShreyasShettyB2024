//package task.jdbctemplate.demo;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import task.jdbctemplate.demo.controller.TransactionController;
//import task.jdbctemplate.demo.model.Transaction;
//import task.jdbctemplate.demo.service.TransactionService;
//
//import java.util.Date;
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@ExtendWith(MockitoExtension.class)
//@AutoConfigureMockMvc
//@SpringBootTest
//public class EndPointTesting {
//
//    @MockBean
//    private TransactionService transactionService;
//
//    @InjectMocks
//    private TransactionController transactionController;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    void callSave() throws Exception {
//        String request = "{\n" +
//                "  \"transactionId\": 96385245,\n" +
//                "  \"transactionDate\": \"2024-03-12\",\n" +
//                "  \"sentTo\": \"rakesh\",\n" +
//                "  \"receivedBy\": \"shreyas\",\n" +
//                "  \"amount\": 963415,\n" +
//                "  \"remarks\": \"education\"\n" +
//                "}\n";
//        Transaction transaction = new Transaction(96385245L,  new Date("03/12/2024"),"rakesh","shreyas",963415.0,"education");
//
//        when(transactionService.apiAddTransaction(any())).thenReturn(transaction);
//
//        mockMvc.perform(post("/transactions/save").contentType(MediaType.APPLICATION_JSON).content(request)).
//                andExpect(status().isOk()).
//                andExpect(jsonPath("$.transactionId").value(96385245L)).
//                andExpect(jsonPath("$.transactionDate").value("2024-03-11T18:30:00.000+00:00")).
//                andExpect(jsonPath("$.sentTo").value("rakesh")).
//                andExpect(jsonPath("$.receivedBy").value("shreyas")).
//                andExpect(jsonPath("$.amount").value(963415.0)).
//                andExpect(jsonPath("$.remarks").value("education"));
//
//        //Fail
////        mockMvc.perform(post("/transactions/save").contentType(MediaType.APPLICATION_JSON).content(request)).
////                andExpect(status().isOk()).
////                andExpect(jsonPath("$.transactionId").value(968541L)).
////                andExpect(jsonPath("$.transactionDate").value("2024-03-11T18:30:00.000+00:00")).
////                andExpect(jsonPath("$.sentTo").value("rakesh")).
////                andExpect(jsonPath("$.receivedBy").value("shreyas")).
////                andExpect(jsonPath("$.amount").value(963415.0)).
////                andExpect(jsonPath("$.remarks").value("education"));
//    }
//
//    @Test
//    void testSendTo() throws Exception {
//        Transaction transaction1 = new Transaction(96385245L,  new Date("03/12/2024"),"rakesh","shreyas",963415.0,"education");
//        Transaction transaction2 = new Transaction(111L,new Date("03/23/2023"),"prashanth","varun",5261511.0,"education");
//        Transaction transaction3 = new Transaction(895775L,new Date("03/24/2024"),"varun","rakesh",9632.0,"family");
//        List<Transaction> transactions = Stream.of(transaction1,transaction2,transaction3).collect(Collectors.toList());
//        when(transactionService.apiFindBySender(eq("rakesh"))).thenReturn((transactions));
//        mockMvc.perform(get("/transactions/view/sender/rakesh")).andExpect(status().isOk()).
//                andExpect(status().isOk()).
//                andExpect(jsonPath("$[0].transactionId").value(96385245L)).
//                andExpect(jsonPath("$[0].transactionDate").value("2024-03-11T18:30:00.000+00:00")).
//                andExpect(jsonPath("$[0].sentTo").value("rakesh")).
//                andExpect(jsonPath("$[0].receivedBy").value("shreyas")).
//                andExpect(jsonPath("$[0].amount").value(963415.0)).
//                andExpect(jsonPath("$[0].remarks").value("education"));
//    }
//
//    @Test
//    void testReceivedBy() throws Exception {
//        Transaction transaction1 = new Transaction(96385245L,  new Date("03/12/2024"),"rakesh","shreyas",963415.0,"education");
//        Transaction transaction2 = new Transaction(111L,new Date("03/23/2023"),"prashanth","varun",5261511.0,"education");
//        Transaction transaction3 = new Transaction(895775L,new Date("03/24/2024"),"varun","rakesh",9632.0,"family");
//        List<Transaction> transactions = Stream.of(transaction1,transaction2,transaction3).collect(Collectors.toList());
//        when(transactionService.apiFindByReceiver(eq("shreyas"))).thenReturn((transactions));
//
//        mockMvc.perform(get("/transactions/view/receiver/shreyas")).andExpect(status().isOk()).
//                andExpect(status().isOk()).
//                andExpect(jsonPath("$[0].transactionId").value(96385245L)).
//                andExpect(jsonPath("$[0].transactionDate").value("2024-03-11T18:30:00.000+00:00")).
//                andExpect(jsonPath("$[0].sentTo").value("rakesh")).
//                andExpect(jsonPath("$[0].receivedBy").value("shreyas")).
//                andExpect(jsonPath("$[0].amount").value(963415.0)).
//                andExpect(jsonPath("$[0].remarks").value("education"));
//    }
//
//
//
//    @Test
//    void testAmount() throws Exception {
//        Transaction transaction1 = new Transaction(96385245L,  new Date("03/12/2024"),"rakesh","shreyas",963415.0,"education");
//        Transaction transaction2 = new Transaction(111L,new Date("03/23/2023"),"prashanth","varun",5261511.0,"education");
//        Transaction transaction3 = new Transaction(895775L,new Date("03/24/2024"),"varun","rakesh",9632.0,"family");
//        List<Transaction> transactions = Stream.of(transaction2,transaction1,transaction3).collect(Collectors.toList());
//        when(transactionService.apiFindByAmount(eq(963415.0))).thenReturn((transactions));
//        mockMvc.perform(get("/transactions/view/amount/963415")).
//                andExpect(status().isOk()).
//                andExpect(jsonPath("$[1].transactionId").value(96385245L)).
//                andExpect(jsonPath("$[1].transactionDate").value("2024-03-11T18:30:00.000+00:00")).
//                andExpect(jsonPath("$[1].sentTo").value("rakesh")).
//                andExpect(jsonPath("$[1].receivedBy").value("shreyas")).
//                andExpect(jsonPath("$[1].amount").value(963415.0)).
//                andExpect(jsonPath("$[1].remarks").value("education"));
//    }
//
//
//
//}
