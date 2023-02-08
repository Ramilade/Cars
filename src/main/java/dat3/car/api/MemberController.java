package dat3.car.api;

import dat3.car.dto.MemberRequest;
import dat3.car.dto.MemberResponse;
import dat3.car.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/members")

class MemberController {
  MemberService memberService;

public MemberController(MemberService memberService) {
    this.memberService = memberService;
  }


  //ADMIN ONLY
  @GetMapping
  List<MemberResponse> getMembers(){
    return memberService.getMembers(false);
  }

  //ADMIN
  @GetMapping(path = "/{username}")
  MemberResponse getMemberById(@PathVariable String username)
      throws Exception {
  return memberService.getMemberById(username);
  }


  //ANONYMOUS
  //@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @PostMapping
  MemberResponse addMember(@RequestBody MemberRequest body){
    return memberService.addMember(body);
  }

  //MEMBER
  @PutMapping("/{username}")
  ResponseEntity<MemberResponse> updateMember(@PathVariable String username, @RequestBody MemberRequest body){
    return ResponseEntity.ok().body(memberService.addMember(body));
  }

  //ADMIN
  @PatchMapping("/ranking/{username}/{value}")
  void setRankingforUser(@PathVariable String username, @PathVariable int value){
    memberService.setRankingForUser(username, value);
  }

  //ADMIN
  @DeleteMapping("/{username}")
  void deleteMemberByUsername(@PathVariable String username) {
    memberService.deleteMemberByUsername(username);
  }






}







