package de.fhws.fiw.fds.client_server.server.api.states.uni_modules;

import de.fhws.fiw.fds.sutton.server.api.caching.CachingUtils;
import de.fhws.fiw.fds.sutton.server.api.caching.EtagGenerator;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.JerseyResponse;
import de.fhws.fiw.fds.sutton.server.api.services.ServiceContext;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetRelationState;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;
import de.fhws.fiw.fds.client_server.server.api.models.Module;
import de.fhws.fiw.fds.client_server.server.database.DaoFactory;
import jakarta.ws.rs.core.Response;

public class GetSingleModuleOfUni extends AbstractGetRelationState<Response, Module> {
    public GetSingleModuleOfUni(ServiceContext serviceContext, long primaryId, long requestedId) {
        super(serviceContext, primaryId, requestedId);
        this.suttonResponse = new JerseyResponse<>();
    }


    @Override
    protected boolean clientKnowsCurrentModelState(AbstractModel modelFromDatabase) {
        return this.suttonRequest.clientKnowsCurrentModel(modelFromDatabase);
    }

    @Override
    protected void defineHttpCaching() {
        final String modelFromDBEtag = EtagGenerator.createEtag(this.requestedModel.getResult());
        this.suttonResponse.entityTag(modelFromDBEtag);
        this.suttonResponse.cacheControl(CachingUtils.create30SecondsPublicCaching());
    }

    @Override protected SingleModelResult<Module> loadModel( )
    {
        SingleModelResult<Module> module = DaoFactory.getInstance( ).getModuleDao().readById( this.requestedId );
        if(isUniLinkedToThisModule()) {
            module.getResult().setPrimaryId(this.primaryId);
        }
        return module;
    }

    @Override protected void defineTransitionLinks( )
    {
        addLink( UniModuleUri.REL_PATH_SHOW_ONLY_LINKED,
                UniModuleRelTypes.GET_ALL_MODULES,
                getAcceptRequestHeader( ),
                this.primaryId );

        if ( isUniLinkedToThisModule() )
        {
            addLink( UniModuleUri.REL_PATH_ID,
                    UniModuleRelTypes.UPDATE_SINGLE_MODULE,
                    getAcceptRequestHeader( ),
                    this.primaryId, this.requestedId );

            addLink( UniModuleUri.REL_PATH_ID,
                    UniModuleRelTypes.DELETE_SINGLE_MODULE,
                    getAcceptRequestHeader( ),
                    this.primaryId, this.requestedId );
        }
        else
        {
            addLink( UniModuleUri.REL_PATH_ID,
                    UniModuleRelTypes.CREATE_LINK_FROM_UNI_TO_MODULE,
                    getAcceptRequestHeader( ),
                    this.primaryId, this.requestedId );
        }
    }

    private boolean isUniLinkedToThisModule( )
    {
        return !DaoFactory.getInstance( )
                .getUniModuleDao()
                .readById( this.primaryId, this.requestedId )
                .isEmpty( );
    }
}
