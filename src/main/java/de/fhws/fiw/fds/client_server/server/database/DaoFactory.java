package de.fhws.fiw.fds.client_server.server.database;

import de.fhws.fiw.fds.client_server.server.database.inmemory.*;
import de.fhws.fiw.fds.client_server.server.database.utils.PartnerUniversityDao;

public class DaoFactory {

    private static DaoFactory INSTANCE;

    public static DaoFactory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DaoFactory();
        }

        return INSTANCE;
    }

    private final ModuleDao moduleDao;
    private final PartnerUniversityDao partnerUniversityDao;
    private final UniModuleDao uniModuleDao;

    private DaoFactory() {
        this.partnerUniversityDao = new PartnerUniversityStorage();
        this.moduleDao = new ModuleStorage();
        this.uniModuleDao = new UniModuleStorage(this.moduleDao);
    }

    public PartnerUniversityDao getPartnerUniversityDao() {
        return this.partnerUniversityDao;
    }

    public ModuleDao getModuleDao(){return this.moduleDao;}
    public UniModuleDao getUniModuleDao() {return this.uniModuleDao;}
}
