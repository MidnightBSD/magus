package org.midnightbsd.magus.ctl;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.midnightbsd.magus.ctl.api.RunController;
import org.midnightbsd.magus.model.Run;
import org.midnightbsd.magus.services.RunService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test architecture controller
 *
 * @author Lucas Holt
 */
@RunWith(MockitoJUnitRunner.class)
public class RunControllerTest {

    private MockMvc mockMvc;

    @Mock
    private RunService runService;

    @InjectMocks
    private RunController controller;

    private Run run;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        run = new Run();
        run.setId(1);
        run.setCreated(Calendar.getInstance().getTime());

        when(runService.get(1)).thenReturn(run);

        Page<Run> pagedRuns = new PageImpl(Collections.singletonList(run));
        when(runService.get(org.mockito.Matchers.isA(Pageable.class))).thenReturn(pagedRuns);
    }

    @Test
    public void testList() {
        Pageable page = PageRequest.of(0, 10);
        final ResponseEntity<Page<Run>> result = controller.list(page);
        assertNotNull(result);
        assertEquals(1, result.getBody().getTotalPages());
    }

    @Test
    public void testGet() {
        final ResponseEntity<Run> result = controller.get(1);
        assertNotNull(result);
        assertEquals(1, result.getBody().getId());
    }

    @Ignore
    @Test
    public void mvcTestList() throws Exception {
        mockMvc.perform(get("/api/runs"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json;charset=UTF-8"));
    }

    @Test
    public void mvcTestGet() throws Exception {
        mockMvc.perform(get("/api/runs/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json;charset=UTF-8"));
    }
}
