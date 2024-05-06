package mybank.backend.service;


import mybank.backend.service.mvc.MVCController;
import mybank.db.dao.dltemybankdaolayer.exception.DepositsException;
import mybank.db.dao.dltemybankdaolayer.service.CustomerAuthService;
import mybank.db.dao.dltemybankdaolayer.service.RepositoryMyBank;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.ui.Model;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class MVCTests {

    @Autowired
    MVCController mvcController;

    @Autowired
    RepositoryMyBank repositoryMyBank;
    @Autowired
    CustomerAuthService customerAuthService;


    @Test
    @WithMockUser(username = "shreyas12")
    public void testDashBoardView() {
        String viewName = mvcController.dashboard();
        assertEquals("dashboard", viewName);
    }

    @Test
    @WithMockUser(username = "shreyas12")
    public void testIndexView() {
        String viewName = mvcController.index();
        assertEquals("index", viewName);
    }

    @Test
    @WithMockUser(username = "shreyas12")
    public void testRedirectLoginView() {
        String viewName = mvcController.redirectLogin();
        assertEquals("index", viewName);
    }

    @Test
    @WithMockUser(username = "shreyas12")
    public void testViewDepositsView() {
        String viewName = mvcController.viewDeposits();
        assertEquals("viewDeposits", viewName);
    }

    @Test
    @WithMockUser(username = "shreyas12")
    public void testAvailDepositView() throws DepositsException, SQLException {
        Model model = mock(Model.class);
        String result = mvcController.availDeposit("FD", model);
        assertEquals("depositForm", result);
    }

    @Test
    @WithMockUser(username = "shreyas12")
    public void testErrorView() {
        String viewName = mvcController.errorPage();
        assertEquals("error", viewName);
    }

    @Test
    @WithMockUser(username = "shreyas12")
    public void testAvailDepositError1View() {
        Model model = mock(Model.class);
        String result = mvcController.availDeposit("", model);
        assertEquals("error?code=404&msg=Could not find Deposit Information", result);
    }

//    @Test
//    @WithMockUser(username = "shreyas12")
//    public void testAvailDepositError2View() throws DepositsException, SQLException {
//        Model model = mock(Model.class);
//        when(mvcController.availDeposit("FD",model)).thenThrow(new SQLException());
//        String result = mvcController.availDeposit("FD", model);
//        assertEquals("depositForm", result);
//    }


}
