//Cleaned
package org.trinity.yqyl.repository.business.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.trinity.repository.entity.AbstractAuditableEntity;
import org.trinity.yqyl.common.message.lookup.RecordStatus;
import org.trinity.yqyl.common.message.lookup.TransactionType;

/**
 * The persistent class for the account_transaction database table.
 *
 */
@Entity
@Table(name = "account_transaction")
@NamedQuery(name = "AccountTransaction.findAll", query = "SELECT a FROM AccountTransaction a")
public class AccountTransaction extends AbstractAuditableEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "AccountTransaction_PK_IdGenerator")
    @TableGenerator(name = "AccountTransaction_PK_IdGenerator", table = "id_table", pkColumnName = "type", pkColumnValue = "AccountTransaction_PK", valueColumnName = "value", initialValue = 1, allocationSize = 1)
    private Long id;

    private TransactionType type;

    private String code;

    private Double amount;

    private RecordStatus status;

    public AccountTransaction() {
    }

    public Double getAmount() {
        return amount;
    }

    public String getCode() {
        return code;
    }

    public Long getId() {
        return this.id;
    }

    public RecordStatus getStatus() {
        return this.status;
    }

    public TransactionType getType() {
        return type;
    }

    public void setAmount(final Double amount) {
        this.amount = amount;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setStatus(final RecordStatus status) {
        this.status = status;
    }

    public void setType(final TransactionType type) {
        this.type = type;
    }

}
