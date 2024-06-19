package de.fhws.fiw.fds.client_server.server.database.utils;

import de.fhws.fiw.fds.client_server.server.database.DaoFactory;

public class InitializeDatabase {

    public static void initializeDBWithRelations(){
        DaoFactory.getInstance().getUniModuleDao().initializeDatabase();
    }
}
