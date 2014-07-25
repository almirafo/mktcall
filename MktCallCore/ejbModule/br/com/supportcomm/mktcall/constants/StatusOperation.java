/**
 * 
 */
package br.com.supportcomm.mktcall.constants;

/**
 * @author Denio Robson
 * 
 */
public enum StatusOperation
{
    // Declaração das constantes
    OK(0, "Successful"),
    MISSING_MSISDN(1, "Missing msisdn parameter"),
    MISSING_CALLID(2, "Missing callid parameter"),
    MISSING_AREACODE(23, "Missing region"),
    MISSING_CFUCODE(3, "Missing cfucode parameter"),
    MISSING_CAMPAIGNID(10, "Missing campaignid parameter"),
    MISSING_DESTINATION_CALL(11, "Missing destinationcall parameter"),
    MISSING_HANGUP_CAUSE(12, "Missing hangupcause parameter"),
    MISSING_CALL_DATE_TIME(13, "Missing calldatetime parameter"),
    MISSING_ANSWER_DATE_TIME(14, "Missing answerdatetime parameter"),
    MISSING_DURATION(15, "Missing duration parameter"),
    MISSING_SESSION_TIME(16, "Missing sessiontime parameter"),
    MISSING_NEW_MSISDN(17, "New Msisdn"),
    MISSING_AUDIO_LIST(18, "Missing Audio List"),
    MISSING_ANSWER_LIST(19, "Missing Answer List"),
    MISSING_LISTENCOMPLETEAD(20, "Missing Listen Completead"),
    MISSING_DIAL_STATUS(21, "Missing Dial Status"),
    MISSING_WHITE_LIST(22, "Missing White List"),
    
    
    NO_CAMPAIGN_AVAILABLE(5, "No more campaign available or without promotional minutes"),
    USER_ONLIST_LIMIT_REACH(6, "On list access: use limit already reached for this user. Try again tomorrow"),
    USER_OFFLIST_LIMIT_REACH(7, "Off list access: use limit already reached for this user. Try again tomorrow"),
    GLOBAL_ACCESS_OFFLIST_REACH(8, "Limite de usuarios fora da lista foi alcançado"),
    NO_CAMPAIGN_RECORD(9,"No campaign available with this id"),

    ACCESS_DENIED(100, "Access denied"),
    USER_NOT_FOUND(101, "User not found"),
    INVALID_PASSWORD(102, "Invalid password"),
    FILE_ALREADY_EXISTS(201, "File already exists"),
    FILE_NOT_FOUND(203, "File not found"),
    FILE_PERMISSION_DENIED(204, "Permission denied on access file"),

    TIMEOUT(250, "Error timeout"),
    
    
    DIAL_STATUS_ANSWER(1,"ANSWER"),
    DIAL_STATUS_CONGESTION(2,"CONGESTION"),
    DIAL_STATUS_BUSY(3,"BUSY"),
    DIAL_STATUS_CHANUAVAIL(4,"CHANUNAVAIL"),
    DIAL_STATUS_NO_ANSWER(5,"NO ANSWER"),
    DIAL_STATUS_CANCEL(6,"CANCEL"),
    
    
    
    METHOD_POST_ERROR(252, "Method POST not supported. Use GET method instead"),
    METHOD_GET_ERROR(253, "Method GET not supported. Use POST method instead"),
    INTERNAL_ERROR(254, "Internal error"),
    OPERATION_PENDING(255, "Operation pending");
    
    
    
    

    // Definição das constantes
    private final int nStatus;
    private final String strDescription;

    // Construtor
    private StatusOperation(int nStatus, String strDescription)
    {
        this.nStatus = nStatus;
        this.strDescription = strDescription;
    }

    public int value()
    {
        return this.nStatus;
    }

    public String description()
    {
        return this.strDescription;
    }
}
