package de.fhws.fiw.fds.client_server.server.api.states.partneruniversities;

import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.JerseyResponse;
import de.fhws.fiw.fds.sutton.server.api.services.ServiceContext;
import de.fhws.fiw.fds.sutton.server.api.states.post.AbstractPostState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.client_server.server.api.models.PartnerUniversity;
import de.fhws.fiw.fds.client_server.server.database.DaoFactory;
import jakarta.ws.rs.core.Response;

public class PostNewUni extends AbstractPostState<Response, PartnerUniversity> {
    public PostNewUni(ServiceContext serviceContext, PartnerUniversity modelToStore) {
        super(serviceContext, modelToStore);
        this.suttonResponse = new JerseyResponse<>();
    }

    @Override
    protected NoContentResult saveModel() {
        return DaoFactory.getInstance().getPartnerUniversityDao().create(this.modelToStore);
    }

    @Override
    protected void defineTransitionLinks() {
        addLink(PartnerUniUri.REL_PATH, PartnerUniRelTypes.GET_ALL_UNIS, getAcceptRequestHeader());
        addLink(PartnerUniUri.REL_PATH, PartnerUniRelTypes.CREATE_UNI,getAcceptRequestHeader());
        addLink(PartnerUniUri.REL_PATH, PartnerUniRelTypes.GET_SINGLE_UNI,getAcceptRequestHeader());
        addLink(PartnerUniUri.REL_PATH, PartnerUniRelTypes.DELETE_SINGLE_UNI,getAcceptRequestHeader());
        addLink(PartnerUniUri.REL_PATH+"?orderByName=asc", PartnerUniRelTypes.GET_ALL_UNIS_ORDER_BY_NAME_ASC, getAcceptRequestHeader());
        addLink(PartnerUniUri.REL_PATH+"?orderByName=dsc", PartnerUniRelTypes.GET_ALL_UNIS_ORDER_BY_NAME_DSC, getAcceptRequestHeader());

    }

}
