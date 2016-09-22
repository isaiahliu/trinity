//Cleaned
package org.trinity.yqyl.repository.business.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.trinity.repository.entity.AbstractAuditableEntity;

/**
 * The persistent class for the service_receiver_client_interest database table.
 * 
 */
@Entity
@Table(name = "service_receiver_client_interest")
@NamedQuery(name = "ServiceReceiverClientInterest.findAll", query = "SELECT s FROM ServiceReceiverClientInterest s")
public class ServiceReceiverClientInterest extends AbstractAuditableEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "service_receiver_client_id")
	private Long serviceReceiverClientId;

	private String badminton;

	private String basketball;

	@Column(name = "comic_dialogue")
	private String comicDialogue;

	private String comment;

	private String corporation;

	@Column(name = "corporation_role")
	private String corporationRole;

	private String dancing;

	private String drama;

	private String football;

	private String handwriting;

	private String opusculum;

	@Column(name = "other_interest")
	private String otherInterest;

	@Column(name = "other_skill")
	private String otherSkill;

	private String status;

	private String swimming;

	@Column(name = "table_tennis")
	private String tableTennis;

	// bi-directional one-to-one association to ServiceReceiverClient
	@OneToOne
	@JoinColumn(name = "service_receiver_client_id")
	private ServiceReceiverClient serviceReceiverClient;

	public ServiceReceiverClientInterest() {
	}

	public String getBadminton() {
		return this.badminton;
	}

	public String getBasketball() {
		return this.basketball;
	}

	public String getComicDialogue() {
		return this.comicDialogue;
	}

	public String getComment() {
		return this.comment;
	}

	public String getCorporation() {
		return this.corporation;
	}

	public String getCorporationRole() {
		return this.corporationRole;
	}

	public String getDancing() {
		return this.dancing;
	}

	public String getDrama() {
		return this.drama;
	}

	public String getFootball() {
		return this.football;
	}

	public String getHandwriting() {
		return this.handwriting;
	}

	public String getOpusculum() {
		return this.opusculum;
	}

	public String getOtherInterest() {
		return this.otherInterest;
	}

	public String getOtherSkill() {
		return this.otherSkill;
	}

	public ServiceReceiverClient getServiceReceiverClient() {
		return this.serviceReceiverClient;
	}

	public Long getServiceReceiverClientId() {
		return this.serviceReceiverClientId;
	}

	public String getStatus() {
		return this.status;
	}

	public String getSwimming() {
		return this.swimming;
	}

	public String getTableTennis() {
		return this.tableTennis;
	}

	public void setBadminton(final String badminton) {
		this.badminton = badminton;
	}

	public void setBasketball(final String basketball) {
		this.basketball = basketball;
	}

	public void setComicDialogue(final String comicDialogue) {
		this.comicDialogue = comicDialogue;
	}

	public void setComment(final String comment) {
		this.comment = comment;
	}

	public void setCorporation(final String corporation) {
		this.corporation = corporation;
	}

	public void setCorporationRole(final String corporationRole) {
		this.corporationRole = corporationRole;
	}

	public void setDancing(final String dancing) {
		this.dancing = dancing;
	}

	public void setDrama(final String drama) {
		this.drama = drama;
	}

	public void setFootball(final String football) {
		this.football = football;
	}

	public void setHandwriting(final String handwriting) {
		this.handwriting = handwriting;
	}

	public void setOpusculum(final String opusculum) {
		this.opusculum = opusculum;
	}

	public void setOtherInterest(final String otherInterest) {
		this.otherInterest = otherInterest;
	}

	public void setOtherSkill(final String otherSkill) {
		this.otherSkill = otherSkill;
	}

	public void setServiceReceiverClient(final ServiceReceiverClient serviceReceiverClient) {
		this.serviceReceiverClient = serviceReceiverClient;
	}

	public void setServiceReceiverClientId(final Long serviceReceiverClientId) {
		this.serviceReceiverClientId = serviceReceiverClientId;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

	public void setSwimming(final String swimming) {
		this.swimming = swimming;
	}

	public void setTableTennis(final String tableTennis) {
		this.tableTennis = tableTennis;
	}

}
