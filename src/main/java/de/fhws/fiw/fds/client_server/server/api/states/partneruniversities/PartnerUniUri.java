package de.fhws.fiw.fds.client_server.server.api.states.partneruniversities;

import de.fhws.fiw.fds.client_server.Start;

public interface PartnerUniUri {
    String PATH_ELEMENT = "partnerunis";
    String REL_PATH = Start.CONTEXT_PATH + "/api/" + PATH_ELEMENT;
    String REL_PATH_ID = REL_PATH + "/{id}";
}
