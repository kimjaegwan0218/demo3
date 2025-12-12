package com.example.service;

import com.example.demo3.dto.MemberDTO;
import com.example.demo3.service.MemberService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

@Log4j2
public class MemberServiceTests {
    private MemberService memberService;

    @BeforeEach
    public void ready(){memberService = MemberService.instance;}

    @Test
    public void testLogin() throws  Exception{
        String mid = "hkd";
        String mpw = "hkd1234";

        MemberDTO memberDTO = memberService.login(mid,mpw);
        log.info(("멤버 서비스 테스트--->>"+memberDTO));
    }

    @Test
    public void testUpdateUuid() throws Exception{
        String mid = "hkd";
        String uuid = UUID.randomUUID().toString();
        memberService.updateUuid(mid,uuid);
    }

    @Test
    public void testGetByUUID() throws Exception{
        String uuid = "3a71cd51-c0c1-456b-837b-e13e48a5ff3a";
        MemberDTO dto = memberService.getByUUID(uuid);
        log.info("멤버DTO------->"+dto);
    }
}
