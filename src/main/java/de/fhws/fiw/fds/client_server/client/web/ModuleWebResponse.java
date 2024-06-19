package de.fhws.fiw.fds.client_server.client.web;

import de.fhws.fiw.fds.sutton.client.web.WebApiResponse;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.HttpHeaders;
import de.fhws.fiw.fds.client_server.client.models.ModuleClientModel;
import okhttp3.Headers;

import java.util.Collection;
import java.util.Optional;

public class ModuleWebResponse extends WebApiResponse<ModuleClientModel> {
    private final static String HEADER_LOCATION = HttpHeaders.LOCATION;
    public ModuleWebResponse(Collection<ModuleClientModel> responseData, Headers responseHeaders, int lastStatusCode) {
        super(responseData, responseHeaders, lastStatusCode);
    }
    public Optional<String> getLocationHeader() {
        return getResponseHeaders().values(HEADER_LOCATION).stream().findFirst();
    }
}
