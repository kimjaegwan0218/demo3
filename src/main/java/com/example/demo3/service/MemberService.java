package com.example.demo3.service;


import com.example.demo3.dao.MemberDAO;
import com.example.demo3.domain.MemberVO;
import com.example.demo3.dto.MemberDTO;
import com.example.demo3.util.MapperUtil;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

@Log4j2
public enum MemberService {
    instance;

    private MemberDAO dao;
    private ModelMapper modelMapper;

    MemberService(){
        dao = new MemberDAO();
        modelMapper = MapperUtil.instance.get();
    }

    public MemberDTO login(String mid, String mpw)throws Exception{
        MemberVO vo = dao.getWithPassword(mid,mpw);
        MemberDTO memberDTO = modelMapper.map(vo,MemberDTO.class);

        return memberDTO;
    }

    public void updateUuid(String mid, String uuid) throws Exception{
        dao.updateUuid(mid,uuid);
    }

    public MemberDTO getByUUID(String uuid)throws Exception{

        MemberVO vo = dao.selectUUID(uuid);

        MemberDTO memberDTO = modelMapper.map(vo,MemberDTO.class);

        return memberDTO;
    }
}
