package com.tenbit.beep.clubroom.controller;

import com.tenbit.beep.clubroom.dto.ClubRoomRequest;
import com.tenbit.beep.clubroom.dto.ClubRoomResponse;
import com.tenbit.beep.clubroom.entity.ClubRoom;
import com.tenbit.beep.clubroom.repository.ClubRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clubroom")
@RequiredArgsConstructor
public class ClubRoomController {

    private final ClubRoomRepository repository;

    @GetMapping
    public ResponseEntity<List<ClubRoomResponse>> getAll() {
        List<ClubRoomResponse> clubRooms = repository.findAll().stream()
                .map(ClubRoomResponse::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(clubRooms);
    }

    @GetMapping("/search")
    public ResponseEntity<ClubRoomResponse> getById(@RequestParam Long id) {
        ClubRoom clubRoom = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("ClubRoom not found"));
        return ResponseEntity.ok(ClubRoomResponse.from(clubRoom));
    }

    @GetMapping("/category")
    public ResponseEntity<List<ClubRoomResponse>> getByCategory(@RequestParam String category) {
        List<ClubRoomResponse> clubRooms = repository.findByCategory(category).stream()
                .map(ClubRoomResponse::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(clubRooms);
    }

    @GetMapping("/search-name")
    public ResponseEntity<List<ClubRoomResponse>> searchByName(@RequestParam String clubName) {
        List<ClubRoomResponse> clubRooms = repository.findByClubNameContaining(clubName).stream()
                .map(ClubRoomResponse::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(clubRooms);
    }

    @PostMapping
    public ResponseEntity<ClubRoomResponse> create(@RequestBody ClubRoomRequest request) {
        ClubRoom clubRoom = ClubRoom.builder()
                .clubName(request.getClubName())
                .description(request.getDescription())
                .maxMembers(request.getMaxMembers())
                .currentMembers(request.getCurrentMembers())
                .leaderName(request.getLeaderName())
                .leaderStudentNumber(request.getLeaderStudentNumber())
                .category(request.getCategory())
                .build();

        ClubRoom savedClubRoom = repository.save(clubRoom);
        return ResponseEntity.status(HttpStatus.CREATED).body(ClubRoomResponse.from(savedClubRoom));
    }

    @PutMapping
    public ResponseEntity<ClubRoomResponse> update(@RequestParam Long id, @RequestBody ClubRoomRequest request) {
        ClubRoom clubRoom = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("ClubRoom not found"));

        clubRoom.setClubName(request.getClubName());
        clubRoom.setDescription(request.getDescription());
        clubRoom.setMaxMembers(request.getMaxMembers());
        clubRoom.setCurrentMembers(request.getCurrentMembers());
        clubRoom.setLeaderName(request.getLeaderName());
        clubRoom.setLeaderStudentNumber(request.getLeaderStudentNumber());
        clubRoom.setCategory(request.getCategory());

        ClubRoom updatedClubRoom = repository.save(clubRoom);
        return ResponseEntity.ok(ClubRoomResponse.from(updatedClubRoom));
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("ClubRoom not found");
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
