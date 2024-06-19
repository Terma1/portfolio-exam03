package de.fhws.fiw.fds.client_server.client.rest;

import de.fhws.fiw.fds.sutton.client.rest2.AbstractRestClient;
import de.fhws.fiw.fds.client_server.client.models.ModuleClientModel;
import de.fhws.fiw.fds.client_server.client.models.PartnerUniClientModel;
import de.fhws.fiw.fds.client_server.client.web.ModuleWebClient;
import de.fhws.fiw.fds.client_server.client.web.PartnerUniWebClient;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class RestClient extends AbstractRestClient {
    private static final String BASE_URL = "http://localhost:8080/portfolio/api";

    private static final String GET_ALL_MODULES = "getAllModulesOfUni";
    private static final String GET_SINGLE_MODULE = "getModuleOfUni";
    private static final String CREATE_MODULE = "createModuleOfUni";
    private static final String UPDATE_SINGLE_MODULE = "updateModuleOfUni";
    private static final String DELETE_SINGLE_MODULE = "deleteSingleModule";

    private static final String GET_ALL_UNIS = "getAllUnis";
    private static final String GET_SINGLE_UNI = "getPartnerUni";
    private static final String CREATE_UNI = "createPartnerUni";
    private static final String UPDATE_SINGLE_UNI = "updatePartnerUni";
    private static final String DELETE_SINGLE_UNI = "deletePartnerUni";
    private static final String GET_ALL_UNIS_ORDER_BY_NAME_ASC = "getAllUnisOrderAsc";
    private static final String GET_ALL_UNIS_ORDER_BY_NAME_DSC = "getAllUnisOrderDsc";
    private static final String GET_ALL_UNIS_BY_NAME_AND_COUNTRY= "getAllUnisByNameAndCountry";
    private  static final String GET_ALL_UNIS_BY_NAME_AND_COUNTRY_ORDER_BY_NAME_ASC = "getAllUnisByNameAndCountryOrderAsc";
    private  static final String GET_ALL_UNIS_BY_NAME_AND_COUNTRY_ORDER_BY_NAME_DSC = "getAllUnisByNameAndCountryOrderDsc";
    private List<PartnerUniClientModel> currentUniData;
    public int cursorUniData = 0;
    private List<ModuleClientModel> currentModuleData;
    private int cursorModuleData = 0;

    final private PartnerUniWebClient universityWebClient;
    final private ModuleWebClient moduleWebClient;

    public RestClient() {
        super();
        this.universityWebClient = new PartnerUniWebClient();
        this.moduleWebClient = new ModuleWebClient();
        this.currentModuleData = Collections.emptyList();
        this.currentUniData = Collections.emptyList();
    }
    //Allowed methods
    public boolean isNextAvailable() {
        return isLinkAvailable("next");
    }

    public boolean isPrevAvailable() {
        return isLinkAvailable("prev");
    }
    public boolean isGetAllModulesAllowed() {
        return isLinkAvailable(GET_ALL_MODULES) || !this.currentUniData.isEmpty();
    }

    public boolean isGetAllUnisAllowed() {
        return isLinkAvailable(GET_ALL_UNIS);
    }
    public boolean isGetAllUnisByNameAndCountryOrderAscAllowed() {
        return isLinkAvailable(GET_ALL_UNIS_BY_NAME_AND_COUNTRY_ORDER_BY_NAME_ASC);
    }
    public boolean isGetAllUnisByNameAndCountryOrderDscAllowed() {
        return isLinkAvailable(GET_ALL_UNIS_BY_NAME_AND_COUNTRY_ORDER_BY_NAME_DSC);
    }
    public boolean isGetSingleUniversityAllowed() {
        return isLinkAvailable(GET_SINGLE_UNI);
    }
    public boolean isGetAllUnisByNameAscAllowed() {
        return isLinkAvailable(GET_ALL_UNIS_ORDER_BY_NAME_ASC);
    }
    public boolean isGetAllUnisByNameAndCountryAllowed(){return isLinkAvailable(GET_ALL_UNIS_BY_NAME_AND_COUNTRY);}
    public boolean isGetAllUnisByNameDscAllowed() {
        return isLinkAvailable(GET_ALL_UNIS_ORDER_BY_NAME_DSC);
    }
    public boolean isGetSingleModuleAllowed() {
        return !this.currentModuleData.isEmpty() ||
                isLocationHeaderAvailable() ||
                isLinkAvailable(GET_SINGLE_MODULE);
    }
    public boolean isUpdateUniversityAllowed() {
        return isLinkAvailable(UPDATE_SINGLE_UNI);
    }
    public boolean isDeleteModuleAllowed() {
        return isLinkAvailable(DELETE_SINGLE_MODULE);
    }
    public boolean isCreateModuleAllowed() {
        return isLinkAvailable(CREATE_MODULE);
    }
    public boolean isUpdateModuleAllowed() {
        return isLinkAvailable(UPDATE_SINGLE_MODULE);
    }
    public boolean isDeleteUniversityAllowed() {
        return isLinkAvailable(DELETE_SINGLE_UNI);
    }
    public boolean isCreateUniversityAllowed() {
        return isLinkAvailable(CREATE_UNI);
    }

    //Create Methods
    public void createUniversity(PartnerUniClientModel University) throws IOException {
        if (isCreateUniversityAllowed()) {
            processResponse(this.universityWebClient.postNewPartnerUni(getUrl(CREATE_UNI), University), (response) -> {
                this.currentUniData = Collections.emptyList();
                this.cursorUniData = 0;
            });
        } else {
            throw new IllegalStateException();
        }
    }

    public void createModule(ModuleClientModel module) throws IOException {
        if (isCreateModuleAllowed()) {
            processResponse(this.moduleWebClient.postNewModule(getUrl(CREATE_MODULE), module), (response) -> {
                this.currentModuleData = Collections.emptyList();
                this.cursorModuleData = 0;
            });
        } else {
            throw new IllegalStateException();
        }
    }

    //Read methods
    public void getAllModules() throws IOException {
        if (isLinkAvailable(GET_ALL_MODULES)) {
            processResponse(this.moduleWebClient.getCollectionOfModules(getUrl(GET_ALL_MODULES)), (response) -> {
                this.currentModuleData = new LinkedList<>(response.getResponseData());
                this.cursorModuleData = 0;
            });
        } else if (!this.currentUniData.isEmpty()) {
            processResponse(this.moduleWebClient.getCollectionOfModules(
                    this.currentUniData.get(this.cursorUniData).getModules().getUrl()), (response) -> {
                this.currentModuleData = new LinkedList<>(response.getResponseData());
                this.cursorModuleData = 0;
            });
        } else {
            throw new IllegalStateException();
        }
    }
    public void getSingleModule(int index) throws IOException {
        getSingleModule(this.currentModuleData.get(index).getSelfLink().getUrl());
    }

    private void getSingleModule(String url) throws IOException {
        processResponse(this.moduleWebClient.getSingleModule(url), (response) -> {
            this.currentModuleData = new LinkedList<>(response.getResponseData());
            this.cursorModuleData = 0;
        });
    }

    public void getSingleModule() throws IOException {
        if (isLocationHeaderAvailable()) {
            getSingleModule(getLocationHeaderURL());
        } else if (isLinkAvailable(GET_SINGLE_MODULE)) {
            getSingleModule(getUrl(GET_SINGLE_MODULE));
        } else if (!this.currentModuleData.isEmpty()) {
            getSingleModule(this.cursorModuleData);
        } else {
            throw new IllegalStateException();
        }
    }
    public void getAllUniversities() throws IOException {
        if (isGetAllUnisAllowed()) {
            processResponse(this.universityWebClient.getCollectionOfPartnerUnis(getUrl(GET_ALL_UNIS)), (response) -> {
                this.currentUniData = new LinkedList<>(response.getResponseData());
                this.cursorUniData = 0;
            });
        } else {
            throw new IllegalStateException();
        }
    }
    public void getAllUniversities_order_asc() throws IOException {
        if (isGetAllUnisByNameAscAllowed()) {
            processResponse(this.universityWebClient.getCollectionOfPartnerUnis(getUrl(GET_ALL_UNIS_ORDER_BY_NAME_ASC)), (response) -> {
                this.currentUniData = new LinkedList<>(response.getResponseData());
                this.cursorUniData = 0;
            });
        } else {
            throw new IllegalStateException();
        }
    }
    public void getAllUniversities_order_dsc() throws IOException {
        if (isGetAllUnisByNameAscAllowed()) {
            processResponse(this.universityWebClient.getCollectionOfPartnerUnis(getUrl(GET_ALL_UNIS_ORDER_BY_NAME_DSC)), (response) -> {
                this.currentUniData = new LinkedList<>(response.getResponseData());
                this.cursorUniData = 0;
            });
        } else {
            throw new IllegalStateException();
        }
    }
    public void getAllUnisByNameAndCountry(String name, String country) throws IOException {
        if (isGetAllUnisByNameAndCountryAllowed()) {
            var url = getUrl(GET_ALL_UNIS_BY_NAME_AND_COUNTRY);
            url = url.replace("{NAME}", name);
            url = url.replace("{COUNTRY}", country);
            processResponse(this.universityWebClient.getCollectionOfPartnerUnis(url), (response) -> {
                this.currentUniData = new LinkedList<>(response.getResponseData());
                this.cursorUniData = 0;
            });
        } else {
            throw new IllegalStateException();
        }
    }
    public void getAllUnisByNameAndCountryOrderAsc(String name, String country) throws IOException {
        if (isGetAllUnisByNameAndCountryAllowed()) {
            var url = getUrl(GET_ALL_UNIS_BY_NAME_AND_COUNTRY);
            url = url.replace("{NAME}", name);
            url = url.replace("{COUNTRY}", country);
            processResponse(this.universityWebClient.getCollectionOfPartnerUnis(url+"&orderByName=asc"), (response) -> {
                this.currentUniData = new LinkedList<>(response.getResponseData());
                this.cursorUniData = 0;
            });
        } else {
            throw new IllegalStateException();
        }
    }
    public void getAllUnisByNameAndCountryOrderDsc(String name, String country) throws IOException {
        if (isGetAllUnisByNameAndCountryAllowed()) {
            var url = getUrl(GET_ALL_UNIS_BY_NAME_AND_COUNTRY);
            url = url.replace("{NAME}", name);
            url = url.replace("{COUNTRY}", country);
            processResponse(this.universityWebClient.getCollectionOfPartnerUnis(url+"&orderByName=dsc"), (response) -> {
                this.currentUniData = new LinkedList<>(response.getResponseData());
                this.cursorUniData = 0;
            });
        } else {
            throw new IllegalStateException();
        }
    }
    public void getNextPageOfUniversities() throws IOException {
        if (isNextAvailable()) {
            processResponse(this.universityWebClient.getCollectionOfPartnerUnis(getUrl("next")), (response) -> {
                this.currentUniData = new LinkedList<>(response.getResponseData());
                this.cursorUniData = 0;
            });
        } else {
            throw new IllegalStateException();
        }
    }
    public void getSingleUniversity() throws IOException {
        if (isLocationHeaderAvailable()) {
            getSingleUniversity(getLocationHeaderURL());
        } else if (!this.currentUniData.isEmpty()) {
            getSingleUniversityById(this.cursorUniData);
        } else {
            throw new IllegalStateException();
        }
    }

    public void getSingleUniversityById(int index) throws IOException {
        getSingleUniversity(this.currentUniData.get(index).getSelfLink().getUrl());
    }

    private void getSingleUniversity(String url) throws IOException {
        processResponse(this.universityWebClient.getSinglePartnerUni(url), (response) -> {
            this.currentUniData = new LinkedList<>(response.getResponseData());
            this.cursorUniData = 0;
        });
    }

    //update methods

    public void updateUniversity(PartnerUniClientModel university) throws IOException {
        if (isUpdateUniversityAllowed()) {
            processResponse(this.universityWebClient.putPartnerUni(getUrl(UPDATE_SINGLE_UNI), university), (response) -> {
                this.currentUniData = Collections.emptyList();
                this.cursorUniData = 0;
            });
        } else {
            throw new IllegalStateException();
        }
    }
    public void updateModule(ModuleClientModel module) throws IOException {
        if (isUpdateModuleAllowed()) {
            processResponse(this.moduleWebClient.putNewModule(getUrl(UPDATE_SINGLE_MODULE), module), (response) -> {
                this.currentModuleData = Collections.emptyList();
                this.cursorModuleData = 0;
            });
        } else {
            throw new IllegalStateException();
        }
    }

    //delete methods
    public void deleteUniversity() throws IOException {
        if (isDeleteUniversityAllowed()) {
            processResponse(this.universityWebClient.deletePartnerUni(getUrl(DELETE_SINGLE_UNI)), (response) -> {
                this.currentUniData = Collections.emptyList();
                this.cursorUniData = 0;
            });
        } else {
            throw new IllegalStateException();
        }
    }

    public void deleteModule() throws IOException {
        if (isDeleteModuleAllowed()) {
            processResponse(this.moduleWebClient.deleteModule(getUrl(DELETE_SINGLE_MODULE)), (response) -> {
                this.currentModuleData = Collections.emptyList();
                this.cursorModuleData = 0;
            });
        } else {
            throw new IllegalStateException();
        }
    }
    public void resetDatabase() throws IOException {
        processResponse(this.universityWebClient.resetDatabaseOnServer(BASE_URL), (response) -> {
        });
    }
    public void start() throws IOException {
        processResponse(this.universityWebClient.getDispatcher(BASE_URL), (response) -> {
        });
    }
    public List<PartnerUniClientModel> universityData() {
        return this.currentUniData;
    }
    public List<ModuleClientModel> moduleData() {
        return this.currentModuleData;
    }
}