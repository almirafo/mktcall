package br.com.supportcomm.mktcall.exception;

public class MktCallException extends Exception
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public MktCallException()
    {
        super();
    }

    public MktCallException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public MktCallException(String message)
    {
        super(message);
    }

    public MktCallException(Throwable cause)
    {
        super(cause);
    }

}
