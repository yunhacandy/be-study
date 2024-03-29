package cotato.csquiz.utils;

import cotato.csquiz.domain.entity.Quiz;
import cotato.csquiz.repository.QuizRepository;
import java.util.List;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ScorerExistRedisRepository {

    private static final String KEY_PREFIX = "$Scorer for ";
    private static final Long NONE_VALUE = Long.MAX_VALUE;
    private static final Integer SCORER_EXPIRATION = 60 * 24;
    private final QuizRepository quizRepository;
    private final RedisTemplate<String, Long> redisTemplate;

    public void saveAllScorerNone(Long educationId) {
        List<Quiz> quizzes = quizRepository.findAllByEducationId(educationId);
        quizzes.forEach(this::saveScorerNone);
    }

    private void saveScorerNone(Quiz quiz) {
        String quizKey = KEY_PREFIX + quiz.getId();
        redisTemplate.opsForValue().set(
                quizKey,
                NONE_VALUE,
                SCORER_EXPIRATION,
                TimeUnit.MINUTES
        );
    }

    public void saveScorer(Quiz quiz, Long ticketNumber) {
        String quizKey = KEY_PREFIX + quiz.getId();
        redisTemplate.opsForValue().set(
                quizKey,
                ticketNumber
        );
    }

    public boolean isNotExist(Quiz quiz) {
        String quizKey = KEY_PREFIX + quiz.getId();
        return NONE_VALUE.equals(redisTemplate.opsForValue().get(quizKey));
    }

    public Long getScorerTicketNumber(Quiz quiz) {
        String quizKey = KEY_PREFIX + quiz.getId();
        return redisTemplate.opsForValue().get(quizKey);
    }
}
