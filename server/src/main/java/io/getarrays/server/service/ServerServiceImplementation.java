package io.getarrays.server.service;

import io.getarrays.server.Model.ServerModel;
import io.getarrays.server.enumaration.Status;
import io.getarrays.server.repository.ServerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Random;

import static io.getarrays.server.enumaration.Status.*;
import static java.lang.Boolean.TRUE;
import static org.springframework.data.domain.PageRequest.*;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServerServiceImplementation implements ServerService{

    @Autowired
    private final ServerRepository serverRepository;

    @Override
    public ServerModel create(ServerModel server) {
        log.info("Saving new server: {} ",server.getName());
        server.setImgUrl(setServerImageUrl());
        return serverRepository.save(server);
    }



    @Override
    public ServerModel ping(String ipAddress) throws IOException {
        log.info("Pinging server ID: {}", ipAddress);
        ServerModel serverModel =  serverRepository.findByIpAddress(ipAddress);
        InetAddress address = InetAddress.getByName(ipAddress);
        serverModel.setStatus(address.isReachable(10000) ? SERVER_UP : SERVER_DOWN);
        serverRepository.save(serverModel);
        return serverModel;
    }

    @Override
    public Collection<ServerModel> list(int limit) {
        log.info("Fetching all servers");
        return serverRepository.findAll(of(0, limit)).toList();
    }

    @Override
    public ServerModel get(Long id) {
        log.info("Fetching server by id: {}", id);
        return serverRepository.findById(id).get();
    }

    @Override
    public ServerModel update(ServerModel server) {
        log.info("Updating server: {}", server.getName());
        return serverRepository.save(server);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Deleting server: {}", id);
        serverRepository.deleteById(id);
        return TRUE;
    }

    private String setServerImageUrl() {
        String[] imageNames = {"server1.png", "server2.png", "server3.png", "server4.png"};
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/server/image/" + imageNames[new Random().nextInt(4)]).toUriString();
    }
}
