package ru.ndavs.atp.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ndavs.atp.DTO.GroupDTO;
import ru.ndavs.atp.Services.GroupService;

@RestController
@RequestMapping("/apishechka/group")
@RequiredArgsConstructor
@CrossOrigin(origins = "https://ylzaporozhskiy.ru/")
public class GroupController {
    private final GroupService service;
    @GetMapping
    private ResponseEntity getAllGroups(){
        return ResponseEntity.ok(service.getAllGroups());
    }
    @GetMapping("/{id}")
    private ResponseEntity getGroupById(@PathVariable Long id){
        return ResponseEntity.ok(service.getGroupById(id));
    }

    @PostMapping
    private ResponseEntity addNewGroup(@RequestParam String name){
        return ResponseEntity.ok(service.addGroup(name));
    }

    @PutMapping
    private ResponseEntity updateGroup(@RequestBody GroupDTO dto){
        return ResponseEntity.ok(service.updateGroup(dto));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity deleteGroup(@PathVariable Long id){
        return ResponseEntity.ok(service.deleteGroup(id));
    }
}
