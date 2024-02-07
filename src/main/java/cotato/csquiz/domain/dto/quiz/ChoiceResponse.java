package cotato.csquiz.domain.dto.quiz;

import cotato.csquiz.domain.enums.ChoiceCorrect;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChoiceResponse {

    private Long choiceId;
    private int number;
    private String content;
    private ChoiceCorrect isAnswer;
}
