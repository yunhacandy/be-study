package cotato.csquiz.service;

import cotato.csquiz.domain.dto.auth.MemberInfoResponse;
import cotato.csquiz.domain.entity.Member;
import cotato.csquiz.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final MemberRepository memberRepository;

    public List<MemberInfoResponse> getApplicantList() {
        List<Member> applicantList = memberRepository.findAll();

        return applicantList.stream()
                .map(member -> MemberInfoResponse.builder()
                        .name(member.getName())
                        .backFourNumber(member.getPhoneNumber().substring(member.getPhoneNumber().length() - 4))
                        .build())
                .collect(Collectors.toList());
    }
}

