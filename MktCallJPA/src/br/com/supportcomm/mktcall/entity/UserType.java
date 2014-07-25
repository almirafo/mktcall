package br.com.supportcomm.mktcall.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

/**
 * The persistent class for the user_type database table.
 * 
 */
@Entity
@Table(name = "user_type")
@NamedQueries({ @NamedQuery(name = "UserType.All", query = "select u from UserType u"),
               @NamedQuery(name = "UserType.Id", query = "select u from UserType u where u.idUserType = :idUserType") })
public class UserType implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user_type", unique = true, nullable = false)
    @Index(name="idx_userType_id")
    private Long idUserType;

    @Index(name = "idx_usertype_code_user_type")
    @Column(name = "code_user_type", length = 5)
    private String codeUserType;

    @Column(name = "name_user_type", length = 40)
    private String nameUserType;

    //bi-directional many-to-one association to UserAccess
    @OneToMany
    @JoinColumn(name="id_user_access")
    private List<UserAccess> userAccesses;

    public UserType()
    {
    }

    public Long getIdUserType()
    {
        return this.idUserType;
    }

    public void setIdUserType(Long idUserType)
    {
        this.idUserType = idUserType;
    }

    public String getCodeUserType()
    {
        return this.codeUserType;
    }

    public void setCodeUserType(String codeUserType)
    {
        this.codeUserType = codeUserType;
    }

    public String getNameUserType()
    {
        return this.nameUserType;
    }

    public void setNameUserType(String nameUserType)
    {
        this.nameUserType = nameUserType;
    }

    public List<UserAccess> getUserAccesses()
    {
        return this.userAccesses;
    }

    public void setUserAccesses(List<UserAccess> userAccesses)
    {
        this.userAccesses = userAccesses;
    }

}
