package org.midnightbsd.magus.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.midnightbsd.magus.model.Run;
import org.midnightbsd.magus.repository.RunRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Lucas Holt
 */
@RunWith(MockitoJUnitRunner.class)
public class RunServiceTest {

    @Mock
    private RunRepository runRepository;

    @InjectMocks
    private RunService runService;

    @Before
    public void setup() {
        Run run = new Run();
        run.setId(1);
        run.setArch("i386");
        run.setBlessed(false);
        run.setCreated(Calendar.getInstance().getTime());
        run.setOsVersion("0.0");
        run.setStatus("inactive");

        when(runRepository.findById(1)).thenReturn(Optional.of(run));
        when(runRepository.findAll()).thenReturn(Collections.singletonList(run));
    }

    @Test
    public void testGet() {
        Run run = runService.get(1);
        assertNotNull(run);
        assertEquals(1, run.getId());
        assertEquals("i386", run.getArch());

        verify(runRepository, times(1)).findById(1);
    }

    @Test
    public void testList() {
        List<Run> items = runService.list();
        assertNotNull(items);
        assertTrue(items.size() > 0);
        verify(runRepository, times(1)).findAll();
    }
}
