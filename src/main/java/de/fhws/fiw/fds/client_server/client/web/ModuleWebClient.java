package de.fhws.fiw.fds.client_server.client.web;

import de.fhws.fiw.fds.sutton.client.web.GenericWebClient;
import de.fhws.fiw.fds.sutton.client.web.WebApiResponse;
import de.fhws.fiw.fds.client_server.client.models.ModuleClientModel;

import java.io.IOException;

public class ModuleWebClient {
    private GenericWebClient<ModuleClientModel> client;
    public ModuleWebClient()
    {
        this.client = new GenericWebClient<>();
    }

    public ModuleWebResponse getDispatcher(String url) throws IOException
    {
        return createResponse(this.client.sendGetSingleRequest(url));
    }

    public ModuleWebResponse deleteModule(String url) throws IOException
    {
        return createResponse(this.client.sendDeleteRequest(url));
    }

    public ModuleWebResponse getCollectionOfModules(String url) throws IOException
    {
        return createResponse(this.client.sendGetCollectionRequest(url, ModuleClientModel.class));
    }

    public ModuleWebResponse resetDatabaseOnServer(String url) throws IOException
    {
        return createResponse(this.client.sendGetSingleRequest(url + "/resetdatabase"));
    }

    public ModuleWebResponse getSingleModule(String url) throws IOException
    {
        return createResponse(this.client.sendGetSingleRequest(url, ModuleClientModel.class));
    }

    public ModuleWebResponse postNewModule(String url, ModuleClientModel module) throws IOException
    {
        return createResponse(this.client.sendPostRequest(url, module));
    }

    public ModuleWebResponse putNewModule(String url, ModuleClientModel module) throws IOException
    {
        return createResponse(this.client.sendPutRequest(url, module));
    }

    public ModuleWebResponse fillDatabase(String url) throws IOException
    {
        return createResponse(this.client.sendGetSingleRequest(url + "/filldatabase"));
    }

    private ModuleWebResponse createResponse(WebApiResponse<ModuleClientModel> response)
    {
        System.out.println("Response Data: " + response.getResponseData());
        System.out.println("Response Headers: " + response.getResponseHeaders());
        System.out.println("Last Status Code: " + response.getLastStatusCode());
        return new ModuleWebResponse( response.getResponseData(), response.getResponseHeaders(), response.getLastStatusCode());
    }
}
