package cn.itcast.expec;

public class DeleteErrorException extends RuntimeException{
    public DeleteErrorException() {
        super();
    }

    public DeleteErrorException(String message) {
        super(message);
    }
}
