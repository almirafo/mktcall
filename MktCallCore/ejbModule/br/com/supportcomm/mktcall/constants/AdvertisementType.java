package br.com.supportcomm.mktcall.constants;

/**
 * @author Denio Robson
 * 
 */
public enum AdvertisementType
{
    // Declaração das constantes
    NEW_REGISTER(0, "New register to process"),
    EXPORTED_TO_FILE(1, "Record exported to file"),
    PROCESSED_VIA_API(2, "Record processed via API"),
    PROCESSING(3, "In progress"),
    REMOTE_SERVER_ERROR(252, "Error from remote server"),
    TIMEOUT(253, "Timeout error"),
    INTERNAL_ERROR(254, "Internal error"),
    OPERATION_PENDING(255, "Operation pending");

    // Definição das constantes
    private final int value;
    private final String description;

    // Construtor
    private AdvertisementType(int value, String description)
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
