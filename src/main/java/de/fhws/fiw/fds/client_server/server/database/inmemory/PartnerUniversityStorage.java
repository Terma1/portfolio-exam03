package de.fhws.fiw.fds.client_server.server.database.inmemory;

import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.inmemory.AbstractInMemoryStorage;
import de.fhws.fiw.fds.sutton.server.database.inmemory.InMemoryPaging;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.client_server.server.api.models.PartnerUniversity;
import de.fhws.fiw.fds.client_server.server.database.utils.PartnerUniversityDao;

import java.util.function.Predicate;

public class PartnerUniversityStorage extends AbstractInMemoryStorage<PartnerUniversity> implements PartnerUniversityDao {
    @Override
    public CollectionModelResult<PartnerUniversity> readByAllParameters(String universityName, String country, String departmentName, String websiteURL, String contactPerson, SearchParameter searchParameter) {
        return InMemoryPaging.page(
                this.readAllByPredicate(
                        byAllParameters(universityName, country, departmentName, websiteURL, contactPerson),
                        searchParameter
                ),
                searchParameter.getOffset(),
                searchParameter.getSize()
        );
    }

    public void resetDatabase() {
        this.storage.clear();
    }

    private Predicate<PartnerUniversity> byUniversityNameAndCountry(String universityName, String country) {
        return p -> (universityName == null || universityName.isEmpty() || (p.getName() != null && p.getName().toLowerCase().contains(universityName.toLowerCase())))
                && (country == null || country.isEmpty() || (p.getCountry() != null && p.getCountry().toLowerCase().contains(country.toLowerCase())));
    }

    private Predicate<PartnerUniversity> byAllParameters(String universityName, String country, String departmentName, String websiteURL, String contactPerson) {
        return p -> (universityName == null || universityName.isEmpty() || (p.getName() != null && p.getName().toLowerCase().contains(universityName.toLowerCase())))
                && (country == null || country.isEmpty() || (p.getCountry() != null && p.getCountry().toLowerCase().contains(country.toLowerCase())))
                && (departmentName == null || departmentName.isEmpty() || (p.getDepartmentName() != null && p.getDepartmentName().toLowerCase().contains(departmentName.toLowerCase())))
                && (websiteURL == null || websiteURL.isEmpty() || (p.getWebsiteURL() != null && p.getWebsiteURL().toLowerCase().contains(websiteURL.toLowerCase())))
                && (contactPerson == null || contactPerson.isEmpty() || (p.getContactPerson() != null && p.getContactPerson().toLowerCase().contains(contactPerson.toLowerCase())));
    }
}