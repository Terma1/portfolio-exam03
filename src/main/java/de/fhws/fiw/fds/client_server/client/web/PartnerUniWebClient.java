package de.fhws.fiw.fds.client_server.client.web;

import de.fhws.fiw.fds.sutton.client.web.GenericWebClient;
import de.fhws.fiw.fds.sutton.client.web.WebApiResponse;
import de.fhws.fiw.fds.client_server.client.models.PartnerUniClientModel;

import java.io.IOException;

public class PartnerUniWebClient {

    private final GenericWebClient<PartnerUniClientModel> client;

    public PartnerUniWebClient() {
        this.client = new GenericWebClient<>();
    }

    public PartnerUniWebResponse getDispatcher(String url) throws IOException {
        return createResponse(this.client.sendGetSingleRequest(url));
    }

    public PartnerUniWebResponse getSinglePartnerUni(String url) throws IOException {
        return createResponse(this.client.sendGetSingleRequest(url, PartnerUniClientModel.class));
    }

    public PartnerUniWebResponse getCollectionOfPartnerUnis(String url) throws IOException {
        return createResponse(this.client.sendGetCollectionRequest(url, PartnerUniClientModel.class));
    }

    public PartnerUniWebResponse postNewPartnerUni(String url, PartnerUniClientModel partnerUni) throws IOException {
        return createResponse(this.client.sendPostRequest(url, partnerUni));
    }

    public PartnerUniWebResponse putPartnerUni(String url, PartnerUniClientModel partnerUni) throws IOException {
        return createResponse(this.client.sendPutRequest(url, partnerUni));
    }

    public PartnerUniWebResponse deletePartnerUni(String url) throws IOException {
        return createResponse(this.client.sendDeleteRequest(url));
    }

    public PartnerUniWebResponse resetDatabaseOnServer(String url) throws IOException {
        return createResponse(this.client.sendGetSingleRequest(url + "/resetdatabase"));
    }
    private PartnerUniWebResponse createResponse(WebApiResponse<PartnerUniClientModel> response) {
        System.out.println("Response Data: " + response.getResponseData());
        System.out.println("Response Headers: " + response.getResponseHeaders());
        System.out.println("Last Status Code: " + response.getLastStatusCode());
        return new PartnerUniWebResponse(response.getResponseData(), response.getResponseHeaders(), response.getLastStatusCode());
    }
}
