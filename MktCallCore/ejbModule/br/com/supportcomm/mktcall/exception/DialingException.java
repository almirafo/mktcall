package br.com.supportcomm.mktcall.exception;

public class DialingException extends Exception
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public DialingException()
    {
        super();
    }

    public DialingException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public DialingException(String message)
    {
        super(message);
    }

    public DialingException(Throwable cause)
    {
        super(cause);
    }

}
