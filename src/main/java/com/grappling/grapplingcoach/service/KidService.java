package com.grappling.grapplingcoach.service;

import com.grappling.grapplingcoach.domain.Kid;
import com.grappling.grapplingcoach.repository.KidRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KidService {

    private final KidRepository repository;

    public KidService(KidRepository repository) {
        this.repository = repository;
    }

    public void addKid(String name) {
        repository.save(new Kid(name));
    }

    public List<Kid> getKids() {
        return repository.findAll();
    }

    public void save(Kid kid) {
        repository.save(kid);
    }
}
