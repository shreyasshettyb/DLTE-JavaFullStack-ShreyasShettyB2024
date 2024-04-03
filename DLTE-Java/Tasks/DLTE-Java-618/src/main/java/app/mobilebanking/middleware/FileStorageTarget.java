package app.mobilebanking.middleware;

import org.example.remotes.StorageTarget;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Here we specify the path of file or database usage
 */
@Component("file")
public class FileStorageTarget implements StorageTarget {


    @Override
    public UserFileRepository getUserRepository() {
        return new UserFileRepository("mybank-acounts.doc") {
        };
    }
}
