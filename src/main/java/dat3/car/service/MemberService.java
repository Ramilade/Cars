package dat3.car.service;

import dat3.car.dto.MemberRequest;
import dat3.car.dto.MemberResponse;
import dat3.car.entity.Member;
import dat3.car.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MemberService {
  MemberRepository memberRepository;

  public MemberService(MemberRepository memberRepository) {

    this.memberRepository = memberRepository;
  }

  public MemberResponse addMember(MemberRequest memberRequest) {
    //Later you should add error checks --> Missing arguments, email taken etc.

    if (memberRepository.existsById(memberRequest.getUsername())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Member with this ID already exist");
    }
    if (memberRepository.existsByEmail(memberRequest.getEmail())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Member with this Email already exist");
    }


    Member newMember = MemberRequest.getMemberEntity(memberRequest);
    newMember = memberRepository.save(newMember);

    return new MemberResponse(newMember, false);
  }


  public List<MemberResponse> getMembers(boolean includeAll) {
    List<Member> members = memberRepository.findAll();
    List<MemberResponse> memberResponses = members.stream().map(m -> new MemberResponse(m, includeAll)).toList();
    return memberResponses;
  }

  public MemberResponse getMemberById(String username) throws Exception {
    Member member = memberRepository.findById(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found"));
    return new MemberResponse(member, true);
  }

  public void setRankingForUser(String username, int value) {
    Member member = memberRepository.findById(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found"));
    member.setRanking(value);
    memberRepository.save(member);
  }

  public void updateMember(String username, MemberRequest body) {
    Member member = memberRepository.findById(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found"));
    member.setFirstName(body.getFirstName());
    member.setLastName(body.getLastName());
    member.setEmail(body.getEmail());
    //member.setRanking(body.getRanking());
    memberRepository.save(member);
  }

  public void deleteMemberByUsername(String username) {
    memberRepository.deleteById(username);
  }




}