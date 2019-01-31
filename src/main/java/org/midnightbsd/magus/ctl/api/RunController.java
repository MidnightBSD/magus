package org.midnightbsd.magus.ctl.api;

import org.midnightbsd.magus.model.Run;
import org.midnightbsd.magus.services.RunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author Lucas Holt
 */
@RestController
@RequestMapping("/api/runs")
public class RunController {

    private final RunService runService;

    @Autowired
    public RunController(final RunService runService) {
        this.runService = runService;
    }

    @GetMapping
    public ResponseEntity<Page<Run>> list(final Pageable page) {
        return ResponseEntity.ok(runService.get(page));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Run>> listByStatus(@PathVariable("status") final String status) {
        return ResponseEntity.ok(runService.listByStatus(status));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Run> get(@PathVariable("id") final int id) {
        final Run run = runService.get(id);

        if (run != null)
            return ResponseEntity.ok(run);

        return ResponseEntity.notFound().build();
    }
}
