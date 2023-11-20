package me.fishydarwin.openrp.repository.exception;

public class MissingEntryException extends RepositoryException {
    public MissingEntryException(String whichEntry) {
        super("An entry for " + whichEntry + " could not be found.");
    }
}
