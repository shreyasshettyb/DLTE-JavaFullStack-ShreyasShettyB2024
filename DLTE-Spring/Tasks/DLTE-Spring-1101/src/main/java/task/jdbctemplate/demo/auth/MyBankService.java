package task.jdbctemplate.demo.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyBankService implements UserDetailsService {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public MyBankUsers signingUp(MyBankUsers myBankUsers){
        int ack = jdbcTemplate.update("insert into mybank_users_role values(?,?,?,?,?,?)",new Object[]{
                myBankUsers.getName(),myBankUsers.getUsername(),
                myBankUsers.getPassword(),myBankUsers.getContact(),myBankUsers.getEmail(),myBankUsers.getRole()
        });
        return myBankUsers;
    }

    public MyBankUsers findByUsername(String username){
        MyBankUsers myBankUsers = jdbcTemplate.queryForObject("select * from mybank_users_role where username=?",
                new Object[]{username},new BeanPropertyRowMapper<>(MyBankUsers.class));
        return myBankUsers;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyBankUsers users = findByUsername(username);
        if(users==null)
            throw new UsernameNotFoundException(username);
        return users;
    }
}
