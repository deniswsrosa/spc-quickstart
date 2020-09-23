package com.test;

import com.couchbase.client.java.json.JsonObject;
import com.couchbase.transactions.TransactionGetResult;
import com.couchbase.transactions.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.couchbase.CouchbaseClientFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Components of the type CommandLineRunner are called right after
 * the application start up. So the method *run* is called as soon as
 * the application starts.
 */
@Component
public class CmdRunner implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;




    @Override
    public void run(String... strings) throws Exception {

        User u1 = createUser("user::0001", "perry", "perry.mason@acme.com", "password");
        userRepository.save(u1);

        User u2 = createUser("user::0002", "john", "john.mason@acme.com", "password");
        userRepository.save(u2);
//
//
//        User u3 = createUser("user::0003", "Jerry", "Wasaracecardriver", "jerry.wasaracecardriver@acme.com", "el sob number one");
//        userRepository.save(u3);

        Optional<User> user = userRepository.findById("user::0001");
        System.out.println("User found = "+user.get().getUsername());

//        List<User> result = userRepository.findByEmailLike("%@acme.com");
//
//        System.out.println( "Total of @acme.com users = "+result.size()  );


        transferCredit("user::0001", "user::0002", 10);

    }

    @Autowired
    private Transactions transactions;
    @Autowired
    private CouchbaseClientFactory couchbaseClientFactory;

    public void transferCredit(String user1, String user2, int creditsToTransfer) {
        transactions.run(ctx -> {

            TransactionGetResult u1DocTx = ctx.get(couchbaseClientFactory.getDefaultCollection(), user1);
            TransactionGetResult u2DocTx = ctx.get(couchbaseClientFactory.getDefaultCollection(), user2);
            JsonObject u1Doc = u1DocTx.contentAs(JsonObject.class);
            JsonObject u2Doc = u2DocTx.contentAs(JsonObject.class);

            u1Doc.put("credits", u1Doc.getInt("credits") - creditsToTransfer);
            u2Doc.put("credits", u2Doc.getInt("credits") + creditsToTransfer);
            ctx.replace(u1DocTx, u1Doc);
            ctx.replace(u2DocTx, u2Doc);

            ctx.commit();
        });
    }

    public static User createUser(String id, String username, String email,
                                  String password) {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setCredits(50);
        return user;
    }


}
