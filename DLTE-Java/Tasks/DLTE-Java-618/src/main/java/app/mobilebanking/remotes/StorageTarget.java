package app.mobilebanking.remotes;

import app.mobilebanking.middleware.UserFileRepository;

/**
 * Here is the interface on which data store we are working (file or database)
 */
public interface StorageTarget {
    UserFileRepository getUserRepository();
}
