package com.nttdata.client.ImplService;

import com.nttdata.client.document.Client;
import com.nttdata.client.repository.CLientRepository;
import com.nttdata.client.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private CLientRepository clientRepository;

    @Override
    public Mono<Client> findById(String id) {
        // TODO Auto-generated method stub
        return clientRepository.findById(id);
    }

    @Override
    public Mono<Client> save(Client document) {
        // TODO Auto-generated method stub
        return clientRepository.save(document);
    }

    @Override
    public Mono<Void> delete(Client document) {
        // TODO Auto-generated method stub
        return clientRepository.delete(document);
    }

    @Override
    public Flux<Client> findAll() {
        // TODO Auto-generated method stub
        return clientRepository.findAll();
    }

}
