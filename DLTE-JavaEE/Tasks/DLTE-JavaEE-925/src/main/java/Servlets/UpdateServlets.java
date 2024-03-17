package Servlets;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
@WebServlet("/update/")
public class UpdateServlets extends HttpServlet {
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            InitialContext context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("java:/OracleDS");
            try (Connection connection = dataSource.getConnection()) {
                String accountNumberParam = req.getParameter("accountNumber");
                String emailParam = req.getParameter("email");

                if (accountNumberParam != null && emailParam != null) {
                    int accountNumber = Integer.parseInt(accountNumberParam);

                    String sql = "UPDATE my_bank SET email = ? WHERE account_number = ?";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                        preparedStatement.setString(1, emailParam);
                        preparedStatement.setLong(2, accountNumber);
                        preparedStatement.executeUpdate();
                    }
                } else {
                    throw new ServletException("Account Number Is Incorrect Or Doesn't Exist");
                }
            }
        } catch (NamingException | SQLException exception) {
            throw new ServletException("Error In Updating", exception);
        }
    }
}
