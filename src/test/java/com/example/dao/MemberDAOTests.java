package com.example.dao;

import com.example.demo3.dao.MemberDAO;
import com.example.demo3.domain.MemberVO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

@Log4j2
public class MemberDAOTests {
    private MemberDAO memberDAO;

    @BeforeEach
    public void ready(){ memberDAO = new MemberDAO();}

    @Test
    public void testGetWithPW() throws  Exception{
        String mid = "hkd";
        String mpw = "hkd1234";

        MemberVO vo = memberDAO.getWithPassword(mid,mpw);;

        log.info("멤버vo------>"+vo);
    }

    @Test
    public void testUpdateUuid() throws  Exception{
        String mid = "hkd";
        String uuid = UUID.randomUUID().toString();
        memberDAO.updateUuid(mid, uuid);
    }

    @Test
    public void testSelectUUID() throws Exception{
        String uuid = "3a71cd51-c0c1-456b-837b-e13e48a5ff3a";

        MemberVO vo = memberDAO.selectUUID(uuid);

        log.info("멤버vo-------->>"+vo);
    }
}
