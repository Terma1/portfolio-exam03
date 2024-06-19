// Copyright 2022 Peter Braun
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package de.fhws.fiw.fds.client_server.server.api.states.dispatcher;

import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.JerseyResponse;
import de.fhws.fiw.fds.sutton.server.api.services.ServiceContext;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetDispatcherState;
import de.fhws.fiw.fds.client_server.server.api.states.partneruniversities.PartnerUniRelTypes;
import de.fhws.fiw.fds.client_server.server.api.states.partneruniversities.PartnerUniUri;
import jakarta.ws.rs.core.Response;

public class GetDispatcher extends AbstractGetDispatcherState<Response> {

    public GetDispatcher(ServiceContext serviceContext) {
        super(serviceContext);
        this.suttonResponse = new JerseyResponse<>();
    }

    @Override
    protected void defineTransitionLinks() {
        addLink(PartnerUniUri.REL_PATH, PartnerUniRelTypes.GET_ALL_UNIS, getAcceptRequestHeader());
        addLink(PartnerUniUri.REL_PATH, PartnerUniRelTypes.CREATE_UNI,getAcceptRequestHeader());
        addLink(PartnerUniUri.REL_PATH, PartnerUniRelTypes.GET_SINGLE_UNI,getAcceptRequestHeader());
        addLink(PartnerUniUri.REL_PATH+"?orderByName=asc", PartnerUniRelTypes.GET_ALL_UNIS_ORDER_BY_NAME_ASC, getAcceptRequestHeader());
        addLink(PartnerUniUri.REL_PATH+"?orderByName=dsc", PartnerUniRelTypes.GET_ALL_UNIS_ORDER_BY_NAME_DSC, getAcceptRequestHeader());
        addLink(PartnerUniUri.REL_PATH+"?name={NAME}&country={COUNTRY}", PartnerUniRelTypes.GET_ALL_UNIS_BY_NAME_AND_COUNTRY, getAcceptRequestHeader());
        addLink(PartnerUniUri.REL_PATH+"?name={NAME}&country={COUNTRY}&orderByName=asc", PartnerUniRelTypes.GET_ALL_UNIS_BY_NAME_AND_COUNTRY_ORDER_BY_NAME_ASC, getAcceptRequestHeader());
        addLink(PartnerUniUri.REL_PATH+"?name={NAME}&country={COUNTRY}&orderByName=dsc", PartnerUniRelTypes.GET_ALL_UNIS_BY_NAME_AND_COUNTRY_ORDER_BY_NAME_DSC, getAcceptRequestHeader());
    }
}
