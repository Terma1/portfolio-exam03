package de.fhws.fiw.fds.client_server.server.api.states.partneruniversities;

public interface PartnerUniRelTypes {
    String CREATE_UNI = "createPartnerUni";
    String GET_ALL_UNIS = "getAllUnis";
    String UPDATE_SINGLE_UNI = "updatePartnerUni";
    String DELETE_SINGLE_UNI = "deletePartnerUni";
    String GET_SINGLE_UNI = "getPartnerUni";
    String GET_ALL_MODULES = "getAllModules";
    String GET_ALL_UNIS_BY_NAME_AND_COUNTRY = "getAllUnisByNameAndCountry";
    String GET_ALL_UNIS_ORDER_BY_NAME_ASC = "getAllUnisOrderAsc";
    String GET_ALL_UNIS_ORDER_BY_NAME_DSC="getAllUnisOrderDsc";
    String GET_ALL_UNIS_BY_NAME_AND_COUNTRY_ORDER_BY_NAME_ASC = "getAllUnisByNameAndCountryOrderAsc";
    String GET_ALL_UNIS_BY_NAME_AND_COUNTRY_ORDER_BY_NAME_DSC = "getAllUnisByNameAndCountryOrderDsc";
}
