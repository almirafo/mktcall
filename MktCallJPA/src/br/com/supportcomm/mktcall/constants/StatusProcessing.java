package br.com.supportcomm.mktcall.constants;

public enum StatusProcessing
{
    // Declaration of constants
	NEW_REGISTER(0, "New register to process"),
	SUCCESS(2, "Success"),
    ERROR(5, "Error"),
    PROCESSING(3, "In progress"),
    TIMEOUT(4, "Timeout"),
    TIMEOUT_RESCHEDULED(4, "Timeout. Rescheduled to be dialed."),    
    REMOTE_SERVER_ERROR(253, "Error from remote server"),
    INTERNAL_ERROR(254, "Internal error"),
    OPERATION_PENDING(255, "Operation pending"),
    AUTHENTICATION_FAILED_ERROR(256, "Authentication to Asterisk Server failed."),
    NETWORK_PROBLEMS_ERROR(257, "Network connection is disrupted"),
    ACTION_NULL_ERROR(258, "Action is null"),
    SERVER_ERROR(259, "Not connected to an Asterisk server");

    private final int value;
    private final String description;

    // Construtor
    private StatusProcessing(int value, String description)
    {
        this.value = value;
        this.description = description;
    }

    public int value()
    {
        return this.value;
    }

    public String description()
    {
        return this.description;
    }
}
