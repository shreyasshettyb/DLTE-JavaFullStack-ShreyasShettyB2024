package app.mobilebanking.middleware;

import app.mobilebanking.remotes.StorageTarget;

/**
 * Here we specify the path of file or database usage
 */
public class FileStorageTarget implements StorageTarget {


    @Override
    public UserFileRepository getUserRepository() {
        return new UserFileRepository("mybank-acounts.doc") {
        };
    }
}
