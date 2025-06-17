package jwtSecurity.example.jwtDemo.Controller;

import jwtSecurity.example.jwtDemo.Model.Stand;
import jwtSecurity.example.jwtDemo.Service.StandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/stands")
public class StandController {

    @Autowired
    private StandService standService;

    @GetMapping("/exposition/{expositionId}")
    public List<Stand> getStandsByExposition(@PathVariable Long expositionId) {
        return standService.getStandsByExposition(expositionId);
    }

    @GetMapping("/{id}")
    public Optional<Stand> getStandById(@PathVariable Long id) {
        return standService.getStandById(id);
    }

    @PostMapping("/stand/create")
    public Stand createStand(@RequestBody Stand stand) {
        return standService.saveStand(stand);
    }

    @PutMapping("/{id}")
    public Stand updateStand(@PathVariable Long id, @RequestBody Stand stand) {
        stand.setId(id);
        return standService.saveStand(stand);
    }

    @DeleteMapping("/{id}")
    public void deleteStand(@PathVariable Long id) {
        standService.deleteStand(id);
    }
}