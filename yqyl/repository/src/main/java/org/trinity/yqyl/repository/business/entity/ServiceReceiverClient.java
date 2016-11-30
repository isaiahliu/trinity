//Cleaned
package org.trinity.yqyl.repository.business.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.trinity.repository.entity.AbstractAuditableEntity;
import org.trinity.yqyl.common.message.lookup.CompanyType;
import org.trinity.yqyl.common.message.lookup.FamilyRelationship;
import org.trinity.yqyl.common.message.lookup.Gender;
import org.trinity.yqyl.common.message.lookup.ServiceReceiverClientStatus;

/**
 * The persistent class for the service_receiver_client database table.
 *
 */
@Entity
@Table(name = "service_receiver_client")
@NamedQuery(name = "ServiceReceiverClient.findAll", query = "SELECT s FROM ServiceReceiverClient s")
public class ServiceReceiverClient extends AbstractAuditableEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ServiceReceiverClient_PK_IdGenerator")
    @TableGenerator(name = "ServiceReceiverClient_PK_IdGenerator", table = "id_table", pkColumnName = "type", pkColumnValue = "ServiceReceiverClient_PK", valueColumnName = "value", initialValue = 1, allocationSize = 1)
    private Long id;

    private String address;

    private String category;

    @Column(name = "cellphone_no")
    private String cellphoneNo;

    private String comment;

    private String community;

    @Temporal(TemporalType.DATE)
    private Date dob;

    @Column(name = "family_relationship")
    private FamilyRelationship familyRelationship;

    private String email;

    private Gender gender;

    @Column(name = "homephone_no")
    private String homephoneNo;

    @Column(name = "identity_card")
    private String identityCard;

    @Column(name = "identity_card_copy")
    private String identityCardCopy;

    @Column(name = "medical_insurance_status")
    private String medicalInsuranceStatus;

    private String name;

    private ServiceReceiverClientStatus status;

    private CompanyType type;

    @Column(name = "video_id")
    private String videoId;

    @Column(name = "videophone_no")
    private String videophoneNo;

    // uni-directional many-to-one association to ServiceReceiverClient
    @ManyToOne
    @JoinColumn(name = "spouse_client_id")
    private ServiceReceiverClient spouse;

    // bi-directional many-to-one association to Account
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    // bi-directional one-to-one association to
    // ServiceReceiverClientHealthIndicator
    @OneToOne(mappedBy = "serviceReceiverClient")
    private ServiceReceiverClientHealthIndicator healthIndicator;

    // bi-directional one-to-one association to
    // ServiceReceiverClientHealthInformation
    @OneToOne(mappedBy = "serviceReceiverClient")
    private ServiceReceiverClientHealthInformation healthInformation;

    // bi-directional one-to-one association to ServiceReceiverClientInterest
    @OneToOne(mappedBy = "serviceReceiverClient")
    private ServiceReceiverClientInterest interest;

    // bi-directional one-to-one association to ServiceReceiverClientOther
    @OneToOne(mappedBy = "serviceReceiverClient")
    private ServiceReceiverClientOther other;

    // bi-directional many-to-one association to Favorite
    @OneToMany(mappedBy = "serviceReceiverClient")
    private List<Favorite> favorites;

    // bi-directional many-to-one association to Yiquan
    @OneToOne(mappedBy = "serviceReceiverClient")
    private ServiceReceiverClientYiquan yiquan;

    // bi-directional many-to-one association to Order
    @OneToMany(mappedBy = "serviceReceiverClient")
    private List<ServiceOrder> orders;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public ServiceReceiverClient() {
    }

    public Favorite addFavorite(final Favorite favorite) {
        getFavorites().add(favorite);
        favorite.setServiceReceiverClient(this);

        return favorite;
    }

    public ServiceOrder addOrder(final ServiceOrder order) {
        getOrders().add(order);
        order.setServiceReceiverClient(this);

        return order;
    }

    public Account getAccount() {
        return this.account;
    }

    public String getAddress() {
        return this.address;
    }

    public String getCategory() {
        return this.category;
    }

    public String getCellphoneNo() {
        return this.cellphoneNo;
    }

    public String getComment() {
        return this.comment;
    }

    public String getCommunity() {
        return this.community;
    }

    public Date getDob() {
        return this.dob;
    }

    public String getEmail() {
        return this.email;
    }

    public FamilyRelationship getFamilyRelationship() {
        return familyRelationship;
    }

    public List<Favorite> getFavorites() {
        return this.favorites;
    }

    public Gender getGender() {
        return this.gender;
    }

    public ServiceReceiverClientHealthIndicator getHealthIndicator() {
        return this.healthIndicator;
    }

    public ServiceReceiverClientHealthInformation getHealthInformation() {
        return this.healthInformation;
    }

    public String getHomephoneNo() {
        return this.homephoneNo;
    }

    public Long getId() {
        return this.id;
    }

    public String getIdentityCard() {
        return this.identityCard;
    }

    public String getIdentityCardCopy() {
        return identityCardCopy;
    }

    public ServiceReceiverClientInterest getInterest() {
        return this.interest;
    }

    public String getMedicalInsuranceStatus() {
        return this.medicalInsuranceStatus;
    }

    public String getName() {
        return this.name;
    }

    public List<ServiceOrder> getOrders() {
        return this.orders;
    }

    public ServiceReceiverClientOther getOther() {
        return this.other;
    }

    public ServiceReceiverClient getSpouse() {
        return this.spouse;
    }

    public ServiceReceiverClientStatus getStatus() {
        return this.status;
    }

    public CompanyType getType() {
        return this.type;
    }

    public User getUser() {
        return this.user;
    }

    public String getVideoId() {
        return this.videoId;
    }

    public String getVideophoneNo() {
        return this.videophoneNo;
    }

    public ServiceReceiverClientYiquan getYiquan() {
        return this.yiquan;
    }

    public Favorite removeFavorite(final Favorite favorite) {
        getFavorites().remove(favorite);
        favorite.setServiceReceiverClient(null);

        return favorite;
    }

    public ServiceOrder removeOrder(final ServiceOrder order) {
        getOrders().remove(order);
        order.setServiceReceiverClient(null);

        return order;
    }

    public void setAccount(final Account account) {
        this.account = account;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public void setCategory(final String category) {
        this.category = category;
    }

    public void setCellphoneNo(final String cellphoneNo) {
        this.cellphoneNo = cellphoneNo;
    }

    public void setComment(final String comment) {
        this.comment = comment;
    }

    public void setCommunity(final String community) {
        this.community = community;
    }

    public void setDob(final Date dob) {
        this.dob = dob;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public void setFamilyRelationship(final FamilyRelationship familyRelationship) {
        this.familyRelationship = familyRelationship;
    }

    public void setFavorites(final List<Favorite> favorites) {
        this.favorites = favorites;
    }

    public void setGender(final Gender gender) {
        this.gender = gender;
    }

    public void setHealthIndicator(final ServiceReceiverClientHealthIndicator healthIndicator) {
        this.healthIndicator = healthIndicator;
    }

    public void setHealthInformation(final ServiceReceiverClientHealthInformation healthInformation) {
        this.healthInformation = healthInformation;
    }

    public void setHomephoneNo(final String homephoneNo) {
        this.homephoneNo = homephoneNo;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setIdentityCard(final String identityCard) {
        this.identityCard = identityCard;
    }

    public void setIdentityCardCopy(final String identityCardCopy) {
        this.identityCardCopy = identityCardCopy;
    }

    public void setInterest(final ServiceReceiverClientInterest interest) {
        this.interest = interest;
    }

    public void setMedicalInsuranceStatus(final String medicalInsuranceStatus) {
        this.medicalInsuranceStatus = medicalInsuranceStatus;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setOrders(final List<ServiceOrder> orders) {
        this.orders = orders;
    }

    public void setOther(final ServiceReceiverClientOther other) {
        this.other = other;
    }

    public void setSpouse(final ServiceReceiverClient spouse) {
        this.spouse = spouse;
    }

    public void setStatus(final ServiceReceiverClientStatus status) {
        this.status = status;
    }

    public void setType(final CompanyType type) {
        this.type = type;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public void setVideoId(final String videoId) {
        this.videoId = videoId;
    }

    public void setVideophoneNo(final String videophoneNo) {
        this.videophoneNo = videophoneNo;
    }

    public void setYiquan(final ServiceReceiverClientYiquan yiquan) {
        this.yiquan = yiquan;
    }
}
