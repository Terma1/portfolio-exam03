package de.fhws.fiw.fds.client_server.server.database.inmemory;

import de.fhws.fiw.fds.sutton.server.database.inmemory.AbstractInMemoryStorage;
import de.fhws.fiw.fds.client_server.server.api.models.Module;
import de.fhws.fiw.fds.client_server.server.database.ModuleDao;

public class ModuleStorage extends AbstractInMemoryStorage<Module> implements ModuleDao {
}
