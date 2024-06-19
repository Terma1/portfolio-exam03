package de.fhws.fiw.fds.client_server.client.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import de.fhws.fiw.fds.sutton.client.converters.ClientLinkJsonConverter;
import de.fhws.fiw.fds.sutton.client.model.AbstractClientModel;
import de.fhws.fiw.fds.sutton.client.utils.Link;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class ModuleClientModel extends AbstractClientModel {
    private String moduleName;

    private int semester;

    private int creditPoints;

    @JsonDeserialize(using = ClientLinkJsonConverter.class)
    private transient Link selfLinkOnSecond;

    @JsonDeserialize(using = ClientLinkJsonConverter.class)
    private transient Link selfLink;

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getCreditPoints() {
        return creditPoints;
    }

    public void setCreditPoints(int creditPoints) {
        this.creditPoints = creditPoints;
    }

    @JsonIgnore
    public Link getSelfLinkOnSecond() {
        return selfLinkOnSecond;
    }
    @JsonIgnore
    public void setSelfLinkOnSecond(Link selfLinkOnSecond) {
        this.selfLinkOnSecond = selfLinkOnSecond;
    }
    @JsonIgnore
    public Link getSelfLink() {
        return selfLink;
    }
    @JsonIgnore
    public void setSelfLink(Link selfLink) {
        this.selfLink = selfLink;
    }

    @Override
    public String toString() {
        return "ModuleClientModel{" +
                "moduleName='" + moduleName + '\'' +
                ", semester=" + semester +
                ", creditPoints=" + creditPoints +
                '}';
    }
}
