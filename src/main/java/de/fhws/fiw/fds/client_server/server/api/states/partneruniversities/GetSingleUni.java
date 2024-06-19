package de.fhws.fiw.fds.client_server.server.api.states.partneruniversities;

import de.fhws.fiw.fds.sutton.server.api.caching.CachingUtils;
import de.fhws.fiw.fds.sutton.server.api.caching.EtagGenerator;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.JerseyResponse;
import de.fhws.fiw.fds.sutton.server.api.services.ServiceContext;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetState;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;
import de.fhws.fiw.fds.client_server.server.api.models.PartnerUniversity;
import de.fhws.fiw.fds.client_server.server.api.states.uni_modules.UniModuleRelTypes;
import de.fhws.fiw.fds.client_server.server.api.states.uni_modules.UniModuleUri;
import de.fhws.fiw.fds.client_server.server.database.DaoFactory;
import jakarta.ws.rs.core.Response;

public class GetSingleUni extends AbstractGetState<Response, PartnerUniversity> {
    public GetSingleUni(ServiceContext serviceContext, long requestedId) {
        super(serviceContext, requestedId);
        this.suttonResponse = new JerseyResponse<>();
    }

    @Override
    protected SingleModelResult<PartnerUniversity> loadModel() {
        return DaoFactory.getInstance().getPartnerUniversityDao().readById(this.requestedId);
    }

    @Override
    protected boolean clientKnowsCurrentModelState(AbstractModel modelFromDatabase) {
        return this.suttonRequest.clientKnowsCurrentModel(modelFromDatabase);
    }

    @Override
    protected void defineHttpCaching() {
        final String eTagOfModel = EtagGenerator.createEtag(this.requestedModel.getResult());
        this.suttonResponse.entityTag(eTagOfModel);
        this.suttonResponse.cacheControl(CachingUtils.create30SecondsPublicCaching());
    }

    @Override
    protected void defineTransitionLinks() {
        addLink( PartnerUniUri.REL_PATH_ID, PartnerUniRelTypes.UPDATE_SINGLE_UNI, getAcceptRequestHeader( ),
                this.requestedId );
        addLink( PartnerUniUri.REL_PATH_ID, PartnerUniRelTypes.GET_ALL_UNIS, getAcceptRequestHeader( ));
        addLink( PartnerUniUri.REL_PATH_ID, PartnerUniRelTypes.DELETE_SINGLE_UNI, getAcceptRequestHeader( ),
                this.requestedId );
        addLink( UniModuleUri.REL_PATH, UniModuleRelTypes.CREATE_MODULE, getAcceptRequestHeader( ),
                this.requestedId );
        addLink( UniModuleUri.REL_PATH, UniModuleRelTypes.GET_ALL_MODULES, getAcceptRequestHeader( ),
                this.requestedId );
        addLink(PartnerUniUri.REL_PATH+"?order=asc", PartnerUniRelTypes.GET_ALL_UNIS_ORDER_BY_NAME_ASC, getAcceptRequestHeader());
        addLink(PartnerUniUri.REL_PATH+"?order=dsc", PartnerUniRelTypes.GET_ALL_UNIS_ORDER_BY_NAME_DSC, getAcceptRequestHeader());
        addLink(PartnerUniUri.REL_PATH+"?name={NAME}&country={COUNTRY}", PartnerUniRelTypes.GET_ALL_UNIS_BY_NAME_AND_COUNTRY, getAcceptRequestHeader());
        addLink(PartnerUniUri.REL_PATH+"?name={NAME}&country={COUNTRY}&orderByName=asc", PartnerUniRelTypes.GET_ALL_UNIS_BY_NAME_AND_COUNTRY_ORDER_BY_NAME_ASC, getAcceptRequestHeader());
        addLink(PartnerUniUri.REL_PATH+"?name={NAME}&country={COUNTRY}&orderByName=dsc", PartnerUniRelTypes.GET_ALL_UNIS_BY_NAME_AND_COUNTRY_ORDER_BY_NAME_DSC, getAcceptRequestHeader());

    }
}
