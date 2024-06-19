package de.fhws.fiw.fds.client_server.server.api.models;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import de.fhws.fiw.fds.sutton.server.api.hyperlinks.Link;
import de.fhws.fiw.fds.sutton.server.api.hyperlinks.annotations.SecondarySelfLink;
import de.fhws.fiw.fds.sutton.server.api.hyperlinks.annotations.SelfLink;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;
import jakarta.xml.bind.annotation.XmlRootElement;

@JsonRootName("module")
@JsonInclude(JsonInclude.Include.NON_NULL)
@XmlRootElement(name = "module")
public class Module extends AbstractModel {
    private String moduleName;
    private int semester;
    private int creditPoints;
    @SecondarySelfLink(
            primaryPathElement = "partnerunis",
            secondaryPathElement = "modules"
    )
    private transient Link selfLinkOnSecond;

    @SelfLink(pathElement = "modules")
    private transient Link selfLink;

    public Module() {
        // make JPA happy
    }
    public Module(String moduleName, int semester, int creditPoints, PartnerUniversity partnerUniversity) {
        this.moduleName = moduleName;
        this.semester = semester;
        this.creditPoints = creditPoints;
    }
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

    public Link getSelfLink() {
        return selfLink;
    }

    public void setSelfLink(Link selfLink) {
        this.selfLink = selfLink;
    }


    @Override
    public String toString() {
        return "Module{" +
                "id=" + id +
                ", moduleName='" + moduleName + '\'' +
                ", semester=" + semester +
                ", creditPoints=" + creditPoints +
                '}';
    }

}
