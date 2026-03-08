package com.grappling.grapplingcoach.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class KidTest {

    @Test
    void kidIsNotPresentByDefault() {
        Kid kid = new Kid("Max");

        assertThat(kid.isPresent()).isFalse();
    }

    @Test
    void kidCanBeMarkedPresent() {
        Kid kid = new Kid("Max");

        kid.markPresent();

        assertThat(kid.isPresent()).isTrue();
    }

    @Test
    void kidCanBeMarkedAbsent() {
        Kid kid = new Kid("Max");

        kid.markPresent();
        kid.markAbsent();

        assertThat(kid.isPresent()).isFalse();
    }

}