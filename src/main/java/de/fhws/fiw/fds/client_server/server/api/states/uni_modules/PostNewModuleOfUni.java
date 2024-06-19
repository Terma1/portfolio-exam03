package de.fhws.fiw.fds.client_server.server.api.states.uni_modules;

import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.JerseyResponse;
import de.fhws.fiw.fds.sutton.server.api.services.ServiceContext;
import de.fhws.fiw.fds.sutton.server.api.states.post.AbstractPostRelationState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.client_server.server.api.models.Module;
import de.fhws.fiw.fds.client_server.server.database.DaoFactory;
import jakarta.ws.rs.core.Response;

public class PostNewModuleOfUni extends AbstractPostRelationState<Response, Module> {
    public PostNewModuleOfUni(ServiceContext serviceContext, long primaryId, Module modelToStore) {
        super(serviceContext, primaryId, modelToStore);
        this.suttonResponse = new JerseyResponse<>();
    }


    @Override protected NoContentResult saveModel( )
    {
        return DaoFactory.getInstance( ).getUniModuleDao().create(this.primaryId, this.modelToStore );
    }

    @Override protected void defineTransitionLinks( )
    {
        addLink(UniModuleUri.REL_PATH,
                UniModuleRelTypes.UPDATE_SINGLE_MODULE,
                getAcceptRequestHeader(),
                this.primaryId);

        addLink(UniModuleUri.REL_PATH,
                UniModuleRelTypes.CREATE_MODULE,
                getAcceptRequestHeader(),
                this.primaryId);


        addLink(UniModuleUri.REL_PATH_SHOW_ALL,
                UniModuleRelTypes.GET_ALL_MODULES,
                getAcceptRequestHeader(),
                this.primaryId);

        addLink(UniModuleUri.REL_PATH_SHOW_ALL,
                UniModuleRelTypes.DELETE_SINGLE_MODULE,
                getAcceptRequestHeader(),
                this.primaryId);
        addLink(UniModuleUri.REL_PATH_SHOW_ALL,
                UniModuleRelTypes.GET_SINGLE_MODULE,
                getAcceptRequestHeader(),
                this.primaryId);

    }
}
