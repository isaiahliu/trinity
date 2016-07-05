package org.trinity.yqyl.repository.business.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;

import org.trinity.repository.entity.AbstractAuditableEntity;

/**
 * The persistent class for the message database table.
 * 
 */
@Entity
@NamedQuery(name = "Message.findAll", query = "SELECT m FROM Message m")
public class Message extends AbstractAuditableEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Message_PK_IdGenerator")
    @TableGenerator(name = "Message_PK_IdGenerator", table = "id_table", pkColumnName = "type", pkColumnValue = "Message_PK", valueColumnName = "value", initialValue = 1, allocationSize = 1)
    private Long id;

    private String content;

    private String status;

    private String type;

    // bi-directional many-to-one association to Announcement
    @OneToMany(mappedBy = "message")
    private List<Announcement> announcements;

    public Message() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Announcement> getAnnouncements() {
        return this.announcements;
    }

    public void setAnnouncements(List<Announcement> announcements) {
        this.announcements = announcements;
    }

    public Announcement addAnnouncement(Announcement announcement) {
        getAnnouncements().add(announcement);
        announcement.setMessage(this);

        return announcement;
    }

    public Announcement removeAnnouncement(Announcement announcement) {
        getAnnouncements().remove(announcement);
        announcement.setMessage(null);

        return announcement;
    }

}
