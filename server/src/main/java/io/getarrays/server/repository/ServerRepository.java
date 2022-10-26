package io.getarrays.server.repository;

import io.getarrays.server.Model.ServerModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerRepository extends JpaRepository<ServerModel, Long> {

    ServerModel findByIpAddress(String ipAddress);
}
