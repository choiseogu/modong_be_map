package com.my.modong_prac.service.jtService;

import com.my.modong_prac.dto.jjimTitleDto.JtRequestDto;
import com.my.modong_prac.dto.jjimTitleDto.JtResponseDto;
import com.my.modong_prac.entity.JjimTitleEntity;
import com.my.modong_prac.entity.UserEntity;
import com.my.modong_prac.repository.JtRepository;
import com.my.modong_prac.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JtServiceImpl implements JtService {
    private JtRepository jtRepository;
    private UserRepository userRepository;

    public JtServiceImpl(JtRepository jtRepository, UserRepository userRepository) {
        this.jtRepository = jtRepository;
        this.userRepository = userRepository;
    }

    @Override
    public JtResponseDto createJt(JtRequestDto jtRequestDto) {
        UserEntity userId = userRepository.findById(jtRequestDto.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        JjimTitleEntity jjimTitleEntity = new JjimTitleEntity();
        jjimTitleEntity.setUserId(userId);
        jjimTitleEntity.setJjimTitle(jtRequestDto.getTitle());
        jtRepository.save(jjimTitleEntity);

        return new JtResponseDto(jjimTitleEntity);
    }

    @Override
    public JtResponseDto updateJt(Integer jtId, JtRequestDto jtRequestDto) {
        return jtRepository.findByJtId(jtId)
                .map(jjimTitleEntity -> {
                    jjimTitleEntity.setJjimTitle(jtRequestDto.getTitle());
                    jtRepository.save(jjimTitleEntity);
                    return new JtResponseDto(jjimTitleEntity);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"해당 찜 제목이 없습니다."));
    }

    @Override
    public List<JjimTitleEntity> getJtByUserId(Integer jtId) {
        JjimTitleEntity jjimTitleEntity = jtRepository.findByJtId(jtId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "jtId not found"));
        return Collections.singletonList(jjimTitleEntity);
    }

    @Override
    public List<JjimTitleEntity> getAllJt() {
        List<JjimTitleEntity> allJt = jtRepository.findAll();
        
        if (allJt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "등록된 찜 제목이 없습니다.");
        }
        
        return allJt;
    }

    @Override
    public void deleteJt(Integer jtId) {
        JjimTitleEntity jt = jtRepository.findByJtId(jtId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "jtId not found"));
        jtRepository.delete(jt);
    }
}
