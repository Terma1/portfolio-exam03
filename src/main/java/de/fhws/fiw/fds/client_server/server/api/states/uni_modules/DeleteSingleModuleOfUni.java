package de.fhws.fiw.fds.client_server.server.api.states.uni_modules;

import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.JerseyResponse;
import de.fhws.fiw.fds.sutton.server.api.services.ServiceContext;
import de.fhws.fiw.fds.sutton.server.api.states.delete.AbstractDeleteRelationState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import de.fhws.fiw.fds.client_server.server.api.models.Module;
import de.fhws.fiw.fds.client_server.server.database.DaoFactory;
import jakarta.ws.rs.core.Response;

public class DeleteSingleModuleOfUni extends AbstractDeleteRelationState<Response, Module> {
    public DeleteSingleModuleOfUni(ServiceContext serviceContext, long modelIdToDelete, long primaryId) {
        super(serviceContext, modelIdToDelete, primaryId);
        this.suttonResponse = new JerseyResponse<>();
    }


    @Override
    protected SingleModelResult<Module> loadModel() {
        return DaoFactory.getInstance().getUniModuleDao().readById(this.primaryId, this.modelIdToDelete);
    }

    @Override
    protected NoContentResult deleteModel() {
        return DaoFactory.getInstance().getUniModuleDao().deleteRelation(this.primaryId, this.modelIdToDelete);
    }

    @Override
    protected void defineTransitionLinks() {
        addLink(UniModuleUri.REL_PATH,
                UniModuleRelTypes.GET_ALL_MODULES,
                getAcceptRequestHeader(),
                this.primaryId);
    }

}
