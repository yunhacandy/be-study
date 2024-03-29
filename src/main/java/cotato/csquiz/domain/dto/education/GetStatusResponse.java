package cotato.csquiz.domain.dto.education;

import cotato.csquiz.domain.enums.EducationStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetStatusResponse {

    private EducationStatus status;
}
