package de.fhws.fiw.fds.client_server.server.api.queries;

import de.fhws.fiw.fds.sutton.server.api.queries.AbstractQuery;
import de.fhws.fiw.fds.sutton.server.api.queries.PagingBehaviorUsingOffsetSize;
import de.fhws.fiw.fds.sutton.server.database.DatabaseException;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.client_server.server.api.models.PartnerUniversity;
import de.fhws.fiw.fds.client_server.server.database.DaoFactory;

import java.util.List;

public class QueryByAllNameAndCountry<R> extends AbstractQuery<R, PartnerUniversity> {
    private final String universityName;
    private final String country;
    private final String order;

    public QueryByAllNameAndCountry(String universityName, String country, String order, int offset, int size) {
        this.universityName = universityName;
        this.country = country;
        this.order=order;
        this.pagingBehavior = new PagingBehaviorUsingOffsetSize<>(offset, size);
    }
    @Override
    protected CollectionModelResult<PartnerUniversity> doExecuteQuery(SearchParameter searchParameter) throws DatabaseException {
        searchParameter.setOrderByAttribute(this.order);

        CollectionModelResult<PartnerUniversity> result = DaoFactory.getInstance().getPartnerUniversityDao().readAll();

        List<PartnerUniversity> filteredResults = result.getResult().stream()
                .filter(u -> (this.universityName.isEmpty() || u.getName().equalsIgnoreCase(this.universityName)))
                .filter(u -> (this.country.isEmpty() || u.getCountry().equalsIgnoreCase(this.country)))
                .toList();

        List<PartnerUniversity> sortedResults = filteredResults.stream()
                .sorted((u1, u2) -> {
                    if (this.order.equalsIgnoreCase("asc")) {
                        return u1.getName().compareTo(u2.getName());
                    } else if (this.order.equalsIgnoreCase("dsc")) {
                        return u2.getName().compareTo(u1.getName());
                    } else {
                        return 0;
                    }
                })
                .toList();

        List<PartnerUniversity> paginatedResults = sortedResults.stream()
                .skip(searchParameter.getOffset())
                .limit(searchParameter.getSize())
                .toList();

        result.getResult().clear();
        result.getResult().addAll(paginatedResults);
        result.setTotalNumberOfResult(sortedResults.size());

        return result;
    }
}
