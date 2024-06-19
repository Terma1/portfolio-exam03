package de.fhws.fiw.fds.client_server.server.database.utils;
import de.fhws.fiw.fds.sutton.server.database.IDatabaseAccessObject;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.client_server.server.api.models.PartnerUniversity;

public interface PartnerUniversityDao extends IDatabaseAccessObject<PartnerUniversity> {
    CollectionModelResult<PartnerUniversity> readByAllParameters(String universityName, String country,
                                                                 String departmentName, String websiteURL,
                                                                 String contactPerson, SearchParameter searchParameter);
    void resetDatabase();
}
