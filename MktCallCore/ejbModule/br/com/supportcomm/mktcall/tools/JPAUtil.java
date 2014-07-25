package br.com.supportcomm.mktcall.tools;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Classe de controle do JPA, criando a conexão com a base de dados
 * 
 * @author Denio Robson
 * 
 */
public class JPAUtil
{
    /**
     * Criação do entityManager de acordo com o xml de persistência.
     */
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("IVRBlockCore");

    public EntityManager getEntityManager()
    {
        return entityManagerFactory.createEntityManager();
    }
}
