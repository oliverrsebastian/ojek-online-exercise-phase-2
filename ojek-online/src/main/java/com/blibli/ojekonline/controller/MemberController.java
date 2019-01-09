package com.blibli.ojekonline.controller;

import com.blibli.ojekonline.model.Member;
import com.blibli.ojekonline.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/api/member/all")
    public BaseResponse<List<Member>> getAllMember() {
        List<Member> value = memberService.getMemberList();
        BaseResponse<List<Member>> response = new BaseResponse<>();
        response.setValue(value);
        response.setStatus(HttpStatus.OK.toString());
        return response;
    }

    @GetMapping("/api/member/{id}")
    public BaseResponse<Member> getMember(@PathVariable int id) {
        BaseResponse response = new BaseResponse();
        try {
            Member member = memberService.getMemberById(id);
            response.setStatus(HttpStatus.OK.toString());
            response.setValue(member);
        } catch (RuntimeException e) {
            response.setStatus(HttpStatus.NOT_FOUND.toString());
            response.setValue(null);
        }
        return response;
    }

    @PostMapping("api/member")
    public BaseResponse<String> insertMember(@RequestBody Member member) {
        Member returned = memberService.saveMember(member);
        if (returned != null) {
            String responseMessage = "success!";
            BaseResponse response = new BaseResponse();
            response.setValue(responseMessage);
            response.setStatus(HttpStatus.OK.toString());
            return response;
        }
        BaseResponse response = new BaseResponse();
        response.setStatus(HttpStatus.NOT_FOUND.toString());
        response.setValue(null);
        return response;
    }

    @PutMapping("/api/member")
    public BaseResponse<String> updateMember(@RequestBody Member member) {
        Member returned = memberService.saveMember(member);
        BaseResponse response = new BaseResponse();
        if (returned != null) {
            response.setValue("Update success!");
            response.setStatus(HttpStatus.OK.toString());
        } else {
            response.setValue(null);
            response.setStatus(HttpStatus.NOT_FOUND.toString());
        }
        return response;
    }

    @DeleteMapping("/api/member/{id}")
    public BaseResponse<String> deleteMember(@PathVariable int id) {
        String returnMessage = memberService.deleteMemberById(id);
        BaseResponse response = new BaseResponse();
        response.setValue(returnMessage);
        response.setStatus(HttpStatus.OK.toString());
        return response;
    }
}
