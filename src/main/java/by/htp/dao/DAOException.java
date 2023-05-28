package by.htp.dao;

public class DAOException extends Exception {
    private static final long serialVersionUID = 8814453066415187129L;

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Exception e) {
        super(message, e);
    }

    public DAOException(Exception e) {
        super(e);
    }
}
