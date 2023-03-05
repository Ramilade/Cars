package dat3.car.api;

import dat3.car.dto.MemberRequest;
import dat3.car.dto.MemberResponse;
import dat3.car.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/members")
@CrossOrigin

class MemberController {
  MemberService memberService;

public MemberController(MemberService memberService) {
    this.memberService = memberService;
  }


  //ADMIN ONLY
  @GetMapping
  List<MemberResponse> getMembers(){
    return memberService.getMembers(true);
  }

  //ADMIN
  @GetMapping(path = "/{username}")
  MemberResponse getMemberById(@PathVariable String username) throws Exception {
    return memberService.getMemberByUsername(username);
  }



  //ANONYMOUS
  @PostMapping
  MemberResponse addMember(@RequestBody MemberRequest body){
    return memberService.addMember(body);
  }

  //MEMBER
  @PutMapping("/{username}")
  ResponseEntity<Boolean> updateMember(@PathVariable String username, @RequestBody MemberRequest body){
    return memberService.updateMember(username, body);
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







