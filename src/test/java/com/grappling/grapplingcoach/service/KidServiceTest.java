package com.grappling.grapplingcoach.service;

import com.grappling.grapplingcoach.domain.Kid;
import com.grappling.grapplingcoach.repository.KidRepository;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class KidServiceTest {


    @Test
    void canAddKid() {

        KidRepository repo = mock(KidRepository.class);

        KidService service = new KidService(repo);

        service.addKid("Max");

        verify(repo).save(any(Kid.class));
    }


    @Test
    void canMarkKidPresent() {

        KidRepository repo = mock(KidRepository.class);

        KidService service = new KidService(repo);

        Kid kid = new Kid("Max");

        kid.markPresent();

        assertThat(kid.isPresent()).isTrue();
    }

}
