package de.fhws.fiw.fds.client_server.server.database.utils;

import de.fhws.fiw.fds.client_server.server.database.DaoFactory;

public class ResetDatabase {

    public static void resetAll() {
        DaoFactory.getInstance().getPartnerUniversityDao().resetDatabase();
    }

}
