package pl.equipRental.assignment;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.equipRental.assets.Asset;
import pl.equipRental.assets.AssetRepository;

import pl.equipRental.exception.AssignmentNotFoundException;
import pl.equipRental.exception.AssignmentAlreadyFinishedException;
import pl.equipRental.exception.InvalidAssignmentException;
import pl.equipRental.user.User;
import pl.equipRental.user.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AssignmentService {
    private final AssignmentRepository assignmentRepository;
    private final AssetRepository assetRepository;
    private final UserRepository userRepository;
    AssignmentDto createAssignment(AssignmentDto assignmentDto) {
        Optional<Assignment> activeAssignmentForAsset = assignmentRepository
                .findByAsset_IdAndEndIsNull(assignmentDto.getAssetId());
        activeAssignmentForAsset.ifPresent((a) -> {
            throw new InvalidAssignmentException("To wyposażenie jest aktualnie do kogoś przypisane");
        });
        Optional<User> user = userRepository.findById(assignmentDto.getUserId());
        Optional<Asset> asset = assetRepository.findById(assignmentDto.getAssetId());
        Assignment assignment = new Assignment();
        Long userId = assignmentDto.getUserId();
        Long assetId = assignmentDto.getAssetId();
        assignment.setUser(user.orElseThrow(() ->
                new InvalidAssignmentException("Brak użytkownika z id: " + userId)));
        assignment.setAsset(asset.orElseThrow(() ->
                new InvalidAssignmentException("Brak wyposażenia z id: " + assetId)));
        assignment.setStart(LocalDateTime.now());
        return AssignmentMapper.toDto(assignmentRepository.save(assignment));
    }
    @Transactional
    public LocalDateTime finishAssignment(Long assignmentId) {
        Optional<Assignment> assignment = assignmentRepository.findById(assignmentId);
        Assignment assignmentEntity = assignment.orElseThrow(AssignmentNotFoundException::new);
        if (assignmentEntity.getEnd() != null) {
            throw new AssignmentAlreadyFinishedException();
        } else {
            assignmentEntity.setEnd(LocalDateTime.now());
        }
        return assignmentEntity.getEnd();
    }
}
