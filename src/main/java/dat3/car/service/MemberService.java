package dat3.car.service;

import dat3.car.dto.MemberRequest;
import dat3.car.dto.MemberResponse;
import dat3.car.entity.Member;
import dat3.car.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {
  MemberRepository memberRepository;

  public MemberService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  public List<MemberResponse> getMembers( boolean includeAll) {
    List<Member> members = memberRepository.findAll();
    List<MemberResponse> memberResponses = members.stream()
            .map(member -> new MemberResponse(member, includeAll)).toList();
    return memberResponses;
  }

  public MemberResponse addMember(MemberRequest memberRequest){
    if(memberRepository.existsById(memberRequest.getUsername())){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Member with this ID already exist");
    }
    if(memberRepository.existsByEmail(memberRequest.getEmail())){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Member with this Email already exist");
    }
    Member member = MemberRequest.getMemberEntity(memberRequest);
    memberRepository.save(member);
    return new MemberResponse(member, false);
  }

  public MemberResponse getMemberById(String username) {
    Member member = memberRepository.findById(username).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found"));
    return new MemberResponse(member, false);
  }

  public void deleteMemberByUsername(String username) {
    memberRepository.deleteById(username);
  }

  public void setRankingForUser(String username, int value) {
    Member member = memberRepository.findById(username).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found"));
    member.setRanking(value);
    memberRepository.save(member);
  }

  public ResponseEntity<Boolean> editMember(MemberRequest body, String username) {
    Member member = memberRepository.findById(username).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found"));
    member.setEmail(body.getEmail());
    member.setFirstName(body.getFirstName());
    member.setLastName(body.getLastName());
    member.setStreet(body.getStreet());
    member.setZip(body.getZip());
    member.setCity(body.getCity());

    memberRepository.save(member);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  public MemberResponse findMemberByUsername(String m1) {
    Member member = memberRepository.findById(m1).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found"));
    return new MemberResponse(member, false);
  }


}
