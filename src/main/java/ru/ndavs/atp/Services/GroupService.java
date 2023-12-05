package ru.ndavs.atp.Services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.ndavs.atp.DTO.GroupDTO;
import ru.ndavs.atp.Repositories.GroupRepository;
import ru.ndavs.atp.models.Group;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    private final ModelMapper modelMapper;

    public List<Group> getAllGroups(){
        return groupRepository.findAll();
    }

    public GroupDTO getGroupById(Long id){
        Group group = groupRepository.findById(id).get();
        return modelMapper.map(group, GroupDTO.class);
    }
    public GroupDTO addGroup(String name){
        Group group = new Group();
        group.setName(name);
        groupRepository.save(group);
        return modelMapper.map(group, GroupDTO.class);
    }

    public GroupDTO updateGroup(GroupDTO groupDTO){
        Group group = groupRepository.findById(groupDTO.getGroup_id()).get();
        group.setName(groupDTO.getName());
        groupRepository.save(group);
        return modelMapper.map(group, GroupDTO.class);
    }

    public GroupDTO deleteGroup(Long id){
        Group group = groupRepository.findById(id).get();
        groupRepository.delete(group);
        return modelMapper.map(group, GroupDTO.class);
    }
}
