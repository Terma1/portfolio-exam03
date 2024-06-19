package de.fhws.fiw.fds.client_server.server.api.states.partneruniversities;

import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.JerseyResponse;
import de.fhws.fiw.fds.sutton.server.api.services.ServiceContext;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetCollectionState;
import de.fhws.fiw.fds.client_server.server.api.models.PartnerUniversity;
import de.fhws.fiw.fds.sutton.server.api.queries.AbstractQuery;
import jakarta.ws.rs.core.Response;

public class GetAllPartnerUniversities extends AbstractGetCollectionState<Response, PartnerUniversity> {
    public GetAllPartnerUniversities(ServiceContext serviceContext, AbstractQuery<Response, PartnerUniversity> query) {
        super(serviceContext, query);
        this.suttonResponse = new JerseyResponse<>();
    }

    @Override
    protected void defineTransitionLinks() {
        addLink(PartnerUniUri.REL_PATH, PartnerUniRelTypes.CREATE_UNI, getAcceptRequestHeader());
        addLink(PartnerUniUri.REL_PATH, PartnerUniRelTypes.GET_SINGLE_UNI, getAcceptRequestHeader());
        addLink(PartnerUniUri.REL_PATH+"?orderByName=asc", PartnerUniRelTypes.GET_ALL_UNIS_ORDER_BY_NAME_ASC, getAcceptRequestHeader());
        addLink(PartnerUniUri.REL_PATH+"?orderByName=dsc", PartnerUniRelTypes.GET_ALL_UNIS_ORDER_BY_NAME_DSC, getAcceptRequestHeader());
        addLink(PartnerUniUri.REL_PATH+"?name={NAME}&country={COUNTRY}", PartnerUniRelTypes.GET_ALL_UNIS_BY_NAME_AND_COUNTRY, getAcceptRequestHeader());
        addLink(PartnerUniUri.REL_PATH+"?name={NAME}&country={COUNTRY}&orderByName=asc", PartnerUniRelTypes.GET_ALL_UNIS_BY_NAME_AND_COUNTRY_ORDER_BY_NAME_ASC, getAcceptRequestHeader());
        addLink(PartnerUniUri.REL_PATH+"?name={NAME}&country={COUNTRY}&orderByName=dsc", PartnerUniRelTypes.GET_ALL_UNIS_BY_NAME_AND_COUNTRY_ORDER_BY_NAME_DSC, getAcceptRequestHeader());
    }
}