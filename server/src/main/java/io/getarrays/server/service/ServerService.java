package io.getarrays.server.service;

import io.getarrays.server.Model.ServerModel;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Collection;

public interface ServerService {

    ServerModel create(ServerModel server);
    ServerModel ping (String ipAddress) throws UnknownHostException, IOException;
    Collection<ServerModel> list(int limit);
    ServerModel get(Long id);
    ServerModel update(ServerModel server);
    Boolean delete(Long id);

}
