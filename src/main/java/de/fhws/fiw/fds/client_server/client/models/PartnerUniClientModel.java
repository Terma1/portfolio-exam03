package de.fhws.fiw.fds.client_server.client.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import de.fhws.fiw.fds.sutton.client.converters.ClientLinkJsonConverter;
import de.fhws.fiw.fds.sutton.client.model.AbstractClientModel;
import de.fhws.fiw.fds.sutton.client.utils.Link;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.time.LocalDate;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class PartnerUniClientModel extends AbstractClientModel {

    private String name;
    private String country;
    private String departmentName;
    private String websiteURL;
    private String contactPerson;
    private int studentsSent;
    private int studentsReceived;
    private LocalDate springSemesterStart = LocalDate.of(2024, 3,14);

    private LocalDate autumnSemesterStart = LocalDate.of(2024,10,1);

    @JsonDeserialize(using = ClientLinkJsonConverter.class)
    private Link selfLink;

    @JsonDeserialize(using = ClientLinkJsonConverter.class)
    private Link modules;



    public PartnerUniClientModel() {
    }

    public PartnerUniClientModel(final String name, final String country, final String departmentName,
                                 final String websiteURL, final String contactPerson, final int studentsSent,
                                 final int studentsReceived,final  LocalDate springSemesterStart,
                                 final LocalDate autumnSemesterStart) {
        this.name = name;
        this.country = country;
        this.departmentName = departmentName;
        this.websiteURL = websiteURL;
        this.contactPerson = contactPerson;
        this.studentsSent = studentsSent;
        this.studentsReceived = studentsReceived;
        this.springSemesterStart = springSemesterStart;
        this.autumnSemesterStart = autumnSemesterStart;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getWebsiteURL() {
        return websiteURL;
    }

    public void setWebsiteURL(String websiteURL) {
        this.websiteURL = websiteURL;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public int getStudentsSent() {
        return studentsSent;
    }

    public void setStudentsSent(int studentsSent) {
        this.studentsSent = studentsSent;
    }

    public int getStudentsReceived() {
        return studentsReceived;
    }

    public void setStudentsReceived(int studentsReceived) {
        this.studentsReceived = studentsReceived;
    }

    public LocalDate getSpringSemesterStart() {
        return springSemesterStart;
    }

    public void setSpringSemesterStart(LocalDate springSemesterStart) {
        this.springSemesterStart = springSemesterStart;
    }

    public LocalDate getAutumnSemesterStart() {
        return autumnSemesterStart;
    }

    public void setAutumnSemesterStart(LocalDate autumnSemesterStart) {
        this.autumnSemesterStart = autumnSemesterStart;
    }

    @JsonIgnore
    public Link getSelfLink() {
        return selfLink;
    }

    public void setSelfLink(Link selfLink) {
        this.selfLink = selfLink;
    }
    @JsonIgnore
    public Link getModules() {
        return modules;
    }

    public void setModules(Link modules) {
        this.modules = modules;
    }

    @Override
    public String toString() {
        return "PartnerUniClientModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", websiteURL='" + websiteURL + '\'' +
                ", contactPerson='" + contactPerson + '\'' +
                ", studentsSent=" + studentsSent +
                ", studentsReceived=" + studentsReceived +
                ", springSemesterStart=" + springSemesterStart +
                ", autumnSemesterStart=" + autumnSemesterStart +
                '}';
    }
}
