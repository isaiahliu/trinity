package org.trinity.yqyl.repository.business.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.trinity.repository.entity.AbstractAuditableEntity;
import org.trinity.yqyl.common.message.lookup.RecordStatus;
import org.trinity.yqyl.common.message.lookup.SystemAttributeKey;
import org.trinity.yqyl.common.message.lookup.ValueType;

/**
 * The persistent class for the system_attribute database table.
 *
 */
@Entity
@Table(name = "system_attribute")
@NamedQuery(name = "SystemAttribute.findAll", query = "SELECT s FROM SystemAttribute s")
public class SystemAttribute extends AbstractAuditableEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "SystemAttribute_PK_IdGenerator")
    @TableGenerator(name = "SystemAttribute_PK_IdGenerator", table = "id_table", pkColumnName = "type", pkColumnValue = "SystemAttribute_PK", valueColumnName = "value", initialValue = 1, allocationSize = 1)
    private Long id;

    private String format;

    private SystemAttributeKey key;

    private RecordStatus status;

    @Column(name = "value_type")
    private ValueType valueType;

    private String value;

    public SystemAttribute() {
    }

    public String getFormat() {
        return this.format;
    }

    public Long getId() {
        return this.id;
    }

    public SystemAttributeKey getKey() {
        return this.key;
    }

    public RecordStatus getStatus() {
        return this.status;
    }

    public String getValue() {
        return this.value;
    }

    public ValueType getValueType() {
        return this.valueType;
    }

    public void setFormat(final String format) {
        this.format = format;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setKey(final SystemAttributeKey key) {
        this.key = key;
    }

    public void setStatus(final RecordStatus status) {
        this.status = status;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    public void setValueType(final ValueType valueType) {
        this.valueType = valueType;
    }

}