package de.fhws.fiw.fds.client_server.server.api.states.partneruniversities;

import de.fhws.fiw.fds.sutton.server.api.caching.CachingUtils;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.JerseyResponse;
import de.fhws.fiw.fds.sutton.server.api.services.ServiceContext;
import de.fhws.fiw.fds.sutton.server.api.states.put.AbstractPutState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;
import de.fhws.fiw.fds.client_server.server.api.models.PartnerUniversity;
import de.fhws.fiw.fds.client_server.server.database.DaoFactory;
import jakarta.ws.rs.core.Response;


public class PutSingleUni extends AbstractPutState<Response, PartnerUniversity> {
    public PutSingleUni(ServiceContext serviceContext, long requestedId, PartnerUniversity modelToUpdate) {
        super(serviceContext, requestedId, modelToUpdate);
        this.suttonResponse = new JerseyResponse<>();
    }

    @Override
    protected SingleModelResult<PartnerUniversity> loadModel() {
        return DaoFactory.getInstance().getPartnerUniversityDao().readById(this.modelToUpdate.getId());
    }

    @Override
    protected NoContentResult updateModel() {
        return DaoFactory.getInstance().getPartnerUniversityDao().update(this.modelToUpdate);
    }

    @Override
    protected boolean clientDoesNotKnowCurrentModelState(AbstractModel modelFromDatabase) {
        return this.suttonRequest.clientKnowsCurrentModel(modelFromDatabase);
    }

    @Override
    protected void defineHttpCaching() {
        this.suttonResponse.cacheControl(CachingUtils.create30SecondsPublicCaching());
    }

    @Override
    protected void defineTransitionLinks() {
        addLink(PartnerUniUri.REL_PATH_ID, PartnerUniRelTypes.GET_SINGLE_UNI, getAcceptRequestHeader(),
                this.modelToUpdate.getId());
    }
}
