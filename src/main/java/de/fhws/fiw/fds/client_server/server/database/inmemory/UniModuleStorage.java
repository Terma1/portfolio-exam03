package de.fhws.fiw.fds.client_server.server.database.inmemory;

import de.fhws.fiw.fds.sutton.server.database.IDatabaseAccessObject;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.inmemory.AbstractInMemoryRelationStorage;
import de.fhws.fiw.fds.sutton.server.database.inmemory.InMemoryPaging;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.client_server.server.api.models.Module;
import de.fhws.fiw.fds.client_server.server.database.ModuleDao;
import de.fhws.fiw.fds.client_server.server.database.UniModuleDao;

public class UniModuleStorage extends AbstractInMemoryRelationStorage<Module> implements UniModuleDao {
    final private ModuleDao moduleStorage;

    public UniModuleStorage(ModuleDao moduleStorage) {
        this.moduleStorage = moduleStorage;
    }

    @Override
    protected IDatabaseAccessObject<Module> getSecondaryStorage() {
        return this.moduleStorage;
    }

    @Override
    public CollectionModelResult<Module> readByModuleName(long primaryId, String moduleName, SearchParameter searchParameter) {
        return InMemoryPaging.page(
                this.readAllLinkedByPredicate(primaryId, (p) -> moduleName.isEmpty() || p.getModuleName().equals(moduleName)),
                searchParameter.getOffset(), searchParameter.getSize()
        );
    }

    @Override
    public CollectionModelResult<Module> readAllLinked(long primaryId, SearchParameter searchParameter) {
        return InMemoryPaging.page(
                this.readAllLinkedByPredicate(primaryId, (p) -> true),
                searchParameter.getOffset(), searchParameter.getSize()
        );
    }

    @Override
    public void resetDatabase() {

    }

    @Override
    public void initializeDatabase() {

    }
}
