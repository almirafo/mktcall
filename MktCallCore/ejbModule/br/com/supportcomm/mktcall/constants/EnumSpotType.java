package br.com.supportcomm.mktcall.constants;

/**
 * @author Denio Robson
 * 
 */
public enum EnumSpotType
{
    // Declaração das constantes
    SIMPLES(1, "Spot para audio simples"),
    INTERATIVO(2, "Spot para obtenção de dados do usuário"),
    ASSINATURA(3, "Spot para assinatura via teclado do celuar"),
    RESERVED(4, "Spot reservado para uso futuro");

    // Definição das constantes
    private final int value;
    private final String description;

    // Construtor
    private EnumSpotType(int value, String description)
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
