package de.fhws.fiw.fds.client_server.server.api.states.uni_modules;

import de.fhws.fiw.fds.sutton.server.api.queries.AbstractRelationQuery;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.JerseyResponse;
import de.fhws.fiw.fds.sutton.server.api.services.ServiceContext;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetCollectionRelationState;
import de.fhws.fiw.fds.client_server.server.api.models.Module;
import jakarta.ws.rs.core.Response;

public class GetAllModulesOfUni extends AbstractGetCollectionRelationState<Response, Module> {
    public GetAllModulesOfUni(ServiceContext serviceContext, long primaryId, AbstractRelationQuery<Response, Module> query) {
        super(serviceContext, primaryId, query);
        this.suttonResponse = new JerseyResponse<>();
    }

    @Override
    protected void defineTransitionLinks() {
        addLink(UniModuleUri.REL_PATH,
                UniModuleRelTypes.CREATE_MODULE,
                getAcceptRequestHeader(),
                this.primaryId);


        addLink(UniModuleUri.REL_PATH,
                UniModuleRelTypes.GET_SINGLE_MODULE,
                getAcceptRequestHeader(),
                this.primaryId);

    }
}
