package org.trinity.yqyl.repository.business.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.TableGenerator;

import org.trinity.repository.entity.AbstractAuditableEntity;
import org.trinity.yqyl.common.message.lookup.Language;
import org.trinity.yqyl.common.message.lookup.LookupType;
import org.trinity.yqyl.common.message.lookup.RecordStatus;

/**
 * The persistent class for the lookup database table.
 * 
 */
@Entity
@NamedQuery(name = "Lookup.findAll", query = "SELECT l FROM Lookup l")
public class Lookup extends AbstractAuditableEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Lookup_PK_IdGenerator")
    @TableGenerator(name = "Lookup_PK_IdGenerator", table = "id_table", pkColumnName = "type", pkColumnValue = "Lookup_PK", valueColumnName = "value", initialValue = 1, allocationSize = 1)
    private Long id;

    private LookupType category;

    private String code;

    private String description;

    private Language language;

    private RecordStatus status;

    public Lookup() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LookupType getCategory() {
        return this.category;
    }

    public void setCategory(LookupType category) {
        this.category = category;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Language getLanguage() {
        return this.language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public RecordStatus getStatus() {
        return this.status;
    }

    public void setStatus(RecordStatus status) {
        this.status = status;
    }

}
