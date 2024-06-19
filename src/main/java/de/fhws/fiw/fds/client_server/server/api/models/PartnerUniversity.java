package de.fhws.fiw.fds.client_server.server.api.models;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import de.fhws.fiw.fds.sutton.server.api.hyperlinks.Link;
import de.fhws.fiw.fds.sutton.server.api.hyperlinks.annotations.SuttonLink;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.time.LocalDate;

@JsonRootName("partneruni")
@JsonInclude(JsonInclude.Include.NON_NULL)
@XmlRootElement(name = "partneruni")
public class PartnerUniversity extends AbstractModel {
    private String name;
    private String country;
    private String departmentName;
    private String websiteURL;
    private String contactPerson;
    private int studentsSent;
    private int studentsReceived;
    @SuttonLink(
            value = "partnerunis/${id}",
            rel = "self"
    )
    private Link selfLink;
    @SuttonLink(
            value = "partnerunis/${id}/modules",
            rel = "getModulesOfPartnerUni"
    )
    private Link module;
    private LocalDate springSemesterStart;

    private LocalDate autumnSemesterStart;
    public PartnerUniversity() {
        // make JPA happy
    }
    public PartnerUniversity(final String name, final String country,final  String departmentName,final  String websiteURL,
                             final String contactPerson,final  int studentsSent,final  int studentsReceived,
                             final LocalDate springSemesterStart,final LocalDate autumnSemesterStart) {
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
    public Link getSelfLink() {
        return selfLink;
    }
    public void setSelfLink(Link selfLink) {
        this.selfLink = selfLink;
    }

    public Link getModule() {
        return module;
    }

    public void setModule(Link module) {
        this.module = module;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDepartmentName() {
        return this.departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getWebsiteURL() {
        return this.websiteURL;
    }

    public void setWebsiteURL(String websiteURL) {
        this.websiteURL = websiteURL;
    }

    public String getContactPerson() {
        return this.contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public int getStudentsSent() {
        return this.studentsSent;
    }

    public void setStudentsSent(int studentsSent) {
        this.studentsSent = studentsSent;
    }

    public int getStudentsReceived() {
        return this.studentsReceived;
    }

    public void setStudentsReceived(int studentsReceived) {
        this.studentsReceived = studentsReceived;
    }

    public LocalDate getSpringSemesterStart() {
        return this.springSemesterStart;
    }

    public void setSpringSemesterStart(LocalDate springSemesterStart) {
        this.springSemesterStart = springSemesterStart;
    }

    public LocalDate getAutumnSemesterStart() {
        return this.autumnSemesterStart;
    }

    public void setAutumnSemesterStart(LocalDate autumnSemesterStart) {
        this.autumnSemesterStart = autumnSemesterStart;
    }

    @Override
    public String toString() {
        return "PartnerUniversity{" +
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
