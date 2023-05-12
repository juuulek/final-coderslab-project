package io.example.advancetodo.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    private Task task;

    @BeforeEach
    void setUp() {
        task = new Task();
        task.setAlert(LocalDateTime.parse("2022-12-21T12:12:12"));
    }

    @Test
    public void shouldSetDeadlineAfterAlert() {
        // given
        LocalDateTime time = LocalDateTime.parse("2099-01-01T00:00");
        // when
        task.setDeadline(time);
        // then
        assertEquals(time, task.getDeadline());
    }

    @Test
    public void shouldSetDoneBeforeAlert() {
        // given
        LocalDateTime time = LocalDateTime.parse("1999-01-01T00:00:00");
        // when
        task.setDone(time);
        // then
        assertEquals(time, task.getDone());
    }

    @Test
    public void shouldNotSetDeadlineBeforeAlert() {
        // given
        LocalDateTime time = LocalDateTime.parse("1999-01-01T00:00:00");
        LocalDateTime deadlineBefore = task.getDeadline();
        // then
        assertThrows(IllegalArgumentException.class, () -> task.setDeadline(time));
        assertEquals(deadlineBefore, task.getDeadline());
    }

    @Test
    public void shouldHasRightTagsDespiteSeparateByOtherTags() {
        // when
        task.setTags("zaiste,owszem,taktak,nie,nope2");
        // then
        assertTrue(task.hasTag("zaiste"));
        assertTrue(task.hasTag("taktak"));
        assertTrue(task.hasTag("nope2"));
        assertFalse(task.hasTag("ale takiego tu nie ma"));
    }

    @Test
    public void shouldNotHasTagWhichWasWrittenDuplicateOrSimilar() {
        // when
        task.setTags("zaiste,owszem,taktak,nie,nope2");
        // then
        assertFalse(task.hasTag("tak"));
        assertFalse(task.hasTag("nope"));
    }

    @Test
    public void shouldLookNotAppearedBeforeAppearance() {
        // given
        task.setAlert(null); // w przeciwnym wypadku wykrzacza się na błędzie, że nie może mieć alarm przed pojawieniem się
        // when
        task.setAppearance(LocalDateTime.parse("2163-05-13T00:00:00")); // granicą ludzkiej śmiertelności prawdopodobnie jest obecnie 140 lat
        // then
        assertFalse(task.isAppeared());
    }

    @Test
    public void shouldLookAppearedAfterAppearance() {
        // when
        task.setAppearance(LocalDateTime.parse("1911-02-12T00:00:00")); // urodziny najstarszej Polki
        // then
        assertTrue(task.isAppeared());
    }
}
