package de.fhws.fiw.fds.sutton.server;

import de.fhws.fiw.fds.client_server.client.models.ModuleClientModel;
import de.fhws.fiw.fds.client_server.client.models.PartnerUniClientModel;
import de.fhws.fiw.fds.client_server.client.rest.RestClient;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TestAppIT {
    private static @NotNull PartnerUniClientModel makeUniExample() {
        var uni = new PartnerUniClientModel();
        uni.setName("THWS");
        uni.setCountry("Germany");
        uni.setDepartmentName("FIW");
        uni.setWebsiteURL("thws.de");
        uni.setContactPerson("Prof. Braun");
        uni.setStudentsReceived(12);
        uni.setStudentsSent(23);
        uni.setSpringSemesterStart(LocalDate.of(2024,6,10));
        uni.setAutumnSemesterStart(LocalDate.of(2024,12,12));

        return uni;
    }
    private static @NotNull ModuleClientModel makeModuleExample() {
        var module = new ModuleClientModel();
        module.setModuleName("Verteillte Systeme");
        module.setSemester(4);
        module.setCreditPoints(5);
        return module;
    }
    private RestClient client;

    @BeforeEach
    public void setUp() throws IOException {
        this.client = new RestClient();
        this.client.resetDatabase();
    }

    @Test
    public void test_dispatcher_is_available() throws IOException {
        client.start();
        int statusCode = client.getLastStatusCode();
        assertEquals(200, statusCode);
    }
    @Test
    public void test_dispatcher_is_get_all_unis_allowed() throws IOException {
        client.start();
        assertTrue(client.isGetAllUnisAllowed());
    }
    @Test
    public void test_dispatcher_is_get_single_uni_allowed() throws IOException {
        client.start();
        assertTrue(client.isGetSingleUniversityAllowed());
    }
    @Test
    public void test_dispatcher_is_get_all_unis_available() throws IOException {
        client.start();
        client.getAllUniversities();
        assertEquals(200,client.getLastStatusCode());
    }
    @Test
    public void test_dispatcher_is_get_all_unis_by_name_asc_allowed() throws IOException {
        client.start();
        assertTrue(client.isGetAllUnisByNameAscAllowed());
    }
    @Test
    public void test_dispatcher_is_get_all_unis_by_name_and_country_allowed() throws IOException {
        client.start();
        assertTrue(client.isGetAllUnisByNameAndCountryAllowed());
    }
    @Test
    public void test_dispatcher_is_get_all_unis_by_name_dsc_allowed() throws IOException {
        client.start();
        assertTrue(client.isGetAllUnisByNameDscAllowed());
    }
    @Test
    public void test_dispatcher_is_get_all_unis_by_name_and_country_order_asc_allowed() throws IOException {
        client.start();
        assertTrue(client.isGetAllUnisByNameAndCountryOrderAscAllowed());
    }
    @Test
    public void test_dispatcher_is_get_all_unis_by_name_and_country_order_dsc_allowed() throws IOException {
        client.start();
        assertTrue(client.isGetAllUnisByNameAndCountryOrderDscAllowed());
    }

    @Test
    void test_get_all_unis_order_asc() throws IOException{
        client.start();
        var uni1 = makeUniExample();
        uni1.setName("BBB");
        var uni2 = makeUniExample();
        uni2.setName("CCC");
        var uni3 = makeUniExample();
        uni3.setName("AAAA");
        client.createUniversity(uni1);
        client.createUniversity(uni2);
        client.createUniversity(uni3);
        assertTrue(client.isGetAllUnisByNameAscAllowed());
        client.getAllUniversities_order_asc();
        assertEquals("AAAA",client.universityData().getFirst().getName());
    }
    @Test
    void test_get_all_unis_order_dsc() throws IOException{
        client.start();
        var uni1 = makeUniExample();
        uni1.setName("BBB");
        var uni2 = makeUniExample();
        uni2.setName("CCC");
        var uni3 = makeUniExample();
        uni3.setName("AAAA");
        client.createUniversity(uni1);
        client.createUniversity(uni2);
        client.createUniversity(uni3);
        assertTrue(client.isGetAllUnisByNameAscAllowed());
        client.getAllUniversities_order_dsc();
        assertEquals("CCC",client.universityData().getFirst().getName());
    }
    @Test
    void test_get_all_unis_by_name_and_country() throws IOException{
        client.start();
        var uni1 = makeUniExample();
        uni1.setName("THWS");
        uni1.setCountry("Germany");
        var uni2 = makeUniExample();
        uni2.setName("CCC");
        uni2.setCountry("Germany");
        var uni3 = makeUniExample();
        uni3.setName("THWS");
        uni3.setCountry("Belarus");
        client.createUniversity(uni1);
        client.createUniversity(uni2);
        client.createUniversity(uni3);
        client.isGetAllUnisAllowed();
        client.getAllUniversities();
        client.isGetAllUnisByNameAndCountryAllowed();
        client.getAllUnisByNameAndCountry("THWS","Germany");
        assertEquals(1,client.universityData().size());
    }
    @Test
    void test_get_all_unis_by_name() throws IOException{
        client.start();
        var uni1 = makeUniExample();
        uni1.setName("THWS");
        uni1.setCountry("Germany");
        var uni2 = makeUniExample();
        uni2.setName("CCC");
        uni2.setCountry("Germany");
        var uni3 = makeUniExample();
        uni3.setName("THWS");
        uni3.setCountry("Belarus");
        client.createUniversity(uni1);
        client.createUniversity(uni2);
        client.createUniversity(uni3);
        client.isGetAllUnisAllowed();
        client.getAllUniversities();
        client.isGetAllUnisByNameAndCountryAllowed();
        client.getAllUnisByNameAndCountry("THWS","");
        assertEquals(2,client.universityData().size());
    }
    @Test
    void test_get_all_unis_by_country() throws IOException{
        client.start();
        var uni1 = makeUniExample();
        uni1.setName("THWS");
        uni1.setCountry("Germany");
        var uni2 = makeUniExample();
        uni2.setName("CCC");
        uni2.setCountry("Germany");
        var uni3 = makeUniExample();
        uni3.setName("THWS");
        uni3.setCountry("Belarus");
        client.createUniversity(uni1);
        client.createUniversity(uni2);
        client.createUniversity(uni3);
        client.isGetAllUnisAllowed();
        client.getAllUniversities();
        client.isGetAllUnisByNameAndCountryAllowed();
        client.getAllUnisByNameAndCountry("","Germany");
        assertEquals(2,client.universityData().size());
    }
    @Test
    void test_get_all_unis_by_country_order_asc() throws IOException{
        client.start();
        var uni1 = makeUniExample();
        uni1.setName("BBB");
        uni1.setCountry("Germany");
        var uni2 = makeUniExample();
        uni2.setName("AAA");
        uni2.setCountry("Germany");
        var uni3 = makeUniExample();
        uni3.setName("THWS");
        uni3.setCountry("Belarus");
        client.createUniversity(uni1);
        client.createUniversity(uni2);
        client.createUniversity(uni3);
        client.isGetAllUnisAllowed();
        client.getAllUniversities();
        client.isGetAllUnisByNameAndCountryOrderAscAllowed();
        client.getAllUnisByNameAndCountryOrderAsc("","Germany");
        assertEquals(2,client.universityData().size());
        assertEquals("AAA",client.universityData().getFirst().getName());
    }
    @Test
    void test_get_all_unis_by_country_order_dsc() throws IOException{
        client.start();
        var uni1 = makeUniExample();
        uni1.setName("BBB");
        uni1.setCountry("Germany");
        var uni2 = makeUniExample();
        uni2.setName("AAA");
        uni2.setCountry("Germany");
        var uni3 = makeUniExample();
        uni3.setName("THWS");
        uni3.setCountry("Belarus");
        client.createUniversity(uni1);
        client.createUniversity(uni2);
        client.createUniversity(uni3);
        client.isGetAllUnisAllowed();
        client.getAllUniversities();
        client.isGetAllUnisByNameAndCountryOrderDscAllowed();
        client.getAllUnisByNameAndCountryOrderDsc("","Germany");
        assertEquals(2,client.universityData().size());
        assertEquals("BBB",client.universityData().getFirst().getName());
    }
    @Test
    void test_create_uni() throws IOException {
        client.start();
        client.getAllUniversities();

        var uni = new PartnerUniClientModel();
        uni.setCountry("Germany");
        uni.setName("THWS");
        assertTrue(client.isCreateUniversityAllowed());
        client.createUniversity(uni);
        assertEquals(201, client.getLastStatusCode());
    }
    @Test
    void test_create_and_then_get_new_uni() throws IOException{
        client.start();
        client.getAllUniversities();
        var university = makeUniExample();
        client.createUniversity(university);
        assertEquals(201, client.getLastStatusCode());
        client.getSingleUniversity();
        assertEquals(200, client.getLastStatusCode());
        assertEquals(12, client.universityData().getFirst().getStudentsReceived());
    }
    @Test void test_create_2_unis_and_get_all() throws IOException {
        client.start();
        var uni1 = makeUniExample();
        var uni2 = makeUniExample();
        client.createUniversity(uni1);
        client.createUniversity(uni2);
        assertTrue( client.isGetAllUnisAllowed() );
        client.getAllUniversities();
        assertEquals(200, client.getLastStatusCode());
        assertEquals(2, client.universityData().size());
    }
    @Test
    void test_create_uni_and_update() throws IOException {
        client.start();
        client.getAllUniversities();

        var university = makeUniExample();
        client.createUniversity(university);

        assertEquals(201, client.getLastStatusCode());
        assertTrue(client.isGetSingleUniversityAllowed());

        client.getSingleUniversity();

        assertEquals(200, client.getLastStatusCode());

        var uniNew = client.universityData().getFirst();
        var newCountry = "Belarus";
        uniNew.setCountry(newCountry);
        assertTrue(client.isUpdateUniversityAllowed());
        client.updateUniversity(uniNew);
        assertEquals(204, client.getLastStatusCode());
        client.start();
        client.getAllUniversities();
        assertEquals(newCountry, client.universityData().getFirst().getCountry());
    }
    @Test
    void test_create_and_delete_uni() throws IOException {

        client.start();
        client.getAllUniversities();
        assertEquals(200,client.getLastStatusCode());
        assertTrue(client.isCreateUniversityAllowed());
        var uni = makeUniExample();
        client.createUniversity(uni);
        assertEquals(201, client.getLastStatusCode());
        assertTrue(client.isGetSingleUniversityAllowed());
        client.getSingleUniversity();
        assertEquals(200,client.getLastStatusCode());
        assertTrue(client.isDeleteUniversityAllowed());
        client.deleteUniversity();
        assertEquals(204, client.getLastStatusCode());
        assertTrue(client.isGetAllUnisAllowed());
        client.getAllUniversities();
        assertEquals(200, client.getLastStatusCode());
        assertEquals(0, client.universityData().size());

    }
    @Test void test_paging() throws IOException {

        for( int i=0; i<70; i++ ) {
            client.start();

            var uni= makeUniExample();

            client.createUniversity(uni);
            assertEquals(201, client.getLastStatusCode());
        }
        assertTrue(client.isGetAllUnisAllowed());
        client.getAllUniversities();
        assertEquals(200, client.getLastStatusCode());

        assertTrue(client.isNextAvailable());
        client.getNextPageOfUniversities();
        assertEquals(200, client.getLastStatusCode());
        assertTrue(client.isPrevAvailable());
        assertTrue(client.isNextAvailable());
        assertEquals(20, client.getNumberOfResults());
        assertEquals(70, client.getTotalNumberOfResults());
        client.getNextPageOfUniversities();
        client.getNextPageOfUniversities();
        assertEquals(200, client.getLastStatusCode());
        assertEquals(10, client.getNumberOfResults());
        assertFalse(client.isNextAvailable());
    }
    private void createUni() throws IOException {
        client.start();
        client.getAllUniversities();

        var uni = makeUniExample();

        client.createUniversity(uni);
        assertEquals(201, client.getLastStatusCode());
        client.getSingleUniversity();
    }
    @Test
    void test_get_all_modules() throws IOException {

        createUni();

        assertTrue(client.isGetAllModulesAllowed());
        client.getAllModules();
        assertEquals(200, client.getLastStatusCode());
    }
    @Test
    void test_get_single_module() throws IOException{

    }
    @Test void test_create_university_and_module() throws IOException {
        createUni();
        assertTrue(client.isGetAllModulesAllowed());
        client.getAllModules();
        assertEquals(200, client.getLastStatusCode());
        assertTrue(client.isCreateModuleAllowed());
        var module = makeModuleExample();
        client.createModule(module);
        assertEquals(201, client.getLastStatusCode());
        assertTrue(client.isGetSingleModuleAllowed());
        client.getSingleModule();
        assertTrue(client.isGetAllModulesAllowed());
        client.getAllModules();
        assertEquals(200, client.getLastStatusCode());
        assertEquals(1, client.universityData().size());
    }
    @Test void test_create_module_and_delete() throws IOException {
        createUni();
        assertTrue(client.isGetAllModulesAllowed());
        client.getAllModules();
        assertEquals(200, client.getLastStatusCode());
        assertEquals(0, client.moduleData().size());
        assertTrue(client.isCreateModuleAllowed());
        var module = makeModuleExample();
        client.createModule(module);
        assertEquals(201, client.getLastStatusCode());
        assertTrue(client.isGetSingleModuleAllowed());
        client.getSingleModule();
        assertTrue(client.isDeleteModuleAllowed());
        client.deleteModule();
        assertEquals(204, client.getLastStatusCode());
        assertTrue(client.isGetAllModulesAllowed());
        client.getAllModules();
        assertEquals(200, client.getLastStatusCode());
        assertEquals(0, client.moduleData().size());
    }
    @Test void test_create_module_and_update() throws IOException {

        createUni();
        assertTrue(client.isGetAllModulesAllowed());
        client.getAllModules();
        assertEquals(200, client.getLastStatusCode());
        assertTrue(client.isCreateModuleAllowed());
        var module= makeModuleExample();
        client.createModule(module);
        assertEquals(201, client.getLastStatusCode());
        assertTrue(client.isGetSingleModuleAllowed());
        client.getSingleModule();
        var updated = client.moduleData().getFirst();
        updated.setModuleName("Distributed Systems");
        assertTrue(client.isUpdateModuleAllowed());
        client.updateModule(updated);
        assertEquals(204, client.getLastStatusCode());
        assertTrue(client.isGetSingleModuleAllowed());
        client.getSingleModule();
        assertTrue(client.isGetAllModulesAllowed());
        client.getAllModules();
        assertEquals(200, client.getLastStatusCode());
        assertEquals("Distributed Systems", client.moduleData().getFirst().getModuleName());
    }
    @Test void test_create_2_modules_and_get_all() throws IOException {
        createUni();
        var module1 =makeModuleExample();
        var module2= makeModuleExample();
        assertTrue(client.isGetAllModulesAllowed());
        client.getAllModules();
        assertEquals(200, client.getLastStatusCode());
        assertTrue(client.isCreateModuleAllowed());
        client.createModule(module1);
        assertTrue(client.isGetAllModulesAllowed());
        client.getAllModules();
        assertEquals(200, client.getLastStatusCode());
        assertTrue(client.isCreateModuleAllowed());
        client.createModule(module2);
        assertTrue( client.isGetAllModulesAllowed());
        client.getAllModules();
        assertEquals(200, client.getLastStatusCode());
        assertEquals(2, client.moduleData().size());
    }
}
