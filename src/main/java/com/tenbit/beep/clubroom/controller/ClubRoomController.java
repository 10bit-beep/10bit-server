package com.tenbit.beep.clubroom.controller;

import com.tenbit.beep.clubroom.dto.ClubRoomRequest;
import com.tenbit.beep.clubroom.dto.ClubRoomResponse;
import com.tenbit.beep.clubroom.entity.ClubRoom;
import com.tenbit.beep.clubroom.repository.ClubRoomRepository;
import jakarta.validation.Valid;
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

    @GetMapping("/{id}")
    public ResponseEntity<ClubRoomResponse> getById(@PathVariable Long id) {
        ClubRoom clubRoom = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("ClubRoom not found"));
        return ResponseEntity.ok(ClubRoomResponse.from(clubRoom));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<ClubRoomResponse>> getByCategory(@PathVariable String category) {
        List<ClubRoomResponse> clubRooms = repository.findByCategory(category).stream()
                .map(ClubRoomResponse::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(clubRooms);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ClubRoomResponse>> searchByName(@RequestParam String clubName) {
        List<ClubRoomResponse> clubRooms = repository.findByClubNameContaining(clubName).stream()
                .map(ClubRoomResponse::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(clubRooms);
    }

    @PostMapping
    public ResponseEntity<ClubRoomResponse> create(@Valid @RequestBody ClubRoomRequest request) {
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

    @PutMapping("/{id}")
    public ResponseEntity<ClubRoomResponse> update(@PathVariable Long id, @Valid @RequestBody ClubRoomRequest request) {
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("ClubRoom not found");
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
