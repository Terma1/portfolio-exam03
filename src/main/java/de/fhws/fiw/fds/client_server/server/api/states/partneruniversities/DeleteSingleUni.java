package de.fhws.fiw.fds.client_server.server.api.states.partneruniversities;

import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.JerseyResponse;
import de.fhws.fiw.fds.sutton.server.api.services.ServiceContext;
import de.fhws.fiw.fds.sutton.server.api.states.delete.AbstractDeleteState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import de.fhws.fiw.fds.client_server.server.api.models.PartnerUniversity;
import de.fhws.fiw.fds.client_server.server.database.DaoFactory;
import jakarta.ws.rs.core.Response;


public class DeleteSingleUni extends AbstractDeleteState<Response, PartnerUniversity> {
    public DeleteSingleUni(ServiceContext serviceContext, long modelIdToDelete) {
        super(serviceContext, modelIdToDelete);
        this.suttonResponse = new JerseyResponse<>();
    }

    @Override
    protected SingleModelResult<PartnerUniversity> loadModel() {
        return DaoFactory.getInstance().getPartnerUniversityDao().readById(this.modelIdToDelete);
    }

    @Override
    protected NoContentResult deleteModel() {
        return DaoFactory.getInstance().getPartnerUniversityDao().delete(this.modelIdToDelete);
    }

    @Override
    protected void defineTransitionLinks() {
        addLink(PartnerUniUri.REL_PATH, PartnerUniRelTypes.GET_ALL_UNIS, getAcceptRequestHeader());
        addLink(PartnerUniUri.REL_PATH, PartnerUniRelTypes.CREATE_UNI,getAcceptRequestHeader());
        addLink(PartnerUniUri.REL_PATH+"?order=asc", PartnerUniRelTypes.GET_ALL_UNIS_ORDER_BY_NAME_ASC, getAcceptRequestHeader());
        addLink(PartnerUniUri.REL_PATH+"?order=dsc", PartnerUniRelTypes.GET_ALL_UNIS_ORDER_BY_NAME_DSC, getAcceptRequestHeader());
        addLink(PartnerUniUri.REL_PATH+"?name={NAME}&country={COUNTRY}", PartnerUniRelTypes.GET_ALL_UNIS_BY_NAME_AND_COUNTRY, getAcceptRequestHeader());
        addLink(PartnerUniUri.REL_PATH+"?name={NAME}&country={COUNTRY}&orderByName=asc", PartnerUniRelTypes.GET_ALL_UNIS_BY_NAME_AND_COUNTRY_ORDER_BY_NAME_ASC, getAcceptRequestHeader());
        addLink(PartnerUniUri.REL_PATH+"?name={NAME}&country={COUNTRY}&orderByName=dsc", PartnerUniRelTypes.GET_ALL_UNIS_BY_NAME_AND_COUNTRY_ORDER_BY_NAME_DSC, getAcceptRequestHeader());

    }
}
