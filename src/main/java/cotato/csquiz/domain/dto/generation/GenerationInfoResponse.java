package cotato.csquiz.domain.dto.generation;

import cotato.csquiz.domain.entity.Generation;

public record GenerationInfoResponse(
        Long generationId,
        int generationNumber,
        int sessionCount
) {

    public static GenerationInfoResponse from(Generation generation) {
        return new GenerationInfoResponse(
                generation.getId(),
                generation.getNumber(),
                generation.getSessionCount()
        );
    }
}