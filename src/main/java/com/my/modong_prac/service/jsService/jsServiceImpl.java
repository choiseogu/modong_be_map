package com.my.modong_prac.service.jsService;

import com.my.modong_prac.dto.jjimStoreDto.JsRequestDto;
import com.my.modong_prac.dto.jjimStoreDto.JsResponseDto;
import com.my.modong_prac.entity.JjimStoreEntity;
import com.my.modong_prac.entity.JjimTitleEntity;
import com.my.modong_prac.entity.FavoriteStoreEntity;
import com.my.modong_prac.entity.StoreEntity;
import com.my.modong_prac.repository.JsRepository;
import com.my.modong_prac.repository.JtRepository;
import com.my.modong_prac.repository.FsRepository;
import com.my.modong_prac.repository.StoreRepository;
import org.apache.catalina.Store;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class jsServiceImpl implements jsService {

    private final JsRepository jsRepository;
    private final StoreRepository storeRepository;
    private final JtRepository jtRepository;

    public jsServiceImpl(JsRepository jsRepository, StoreRepository storeRepository, JtRepository jtRepository) {
        this.jsRepository = jsRepository;
        this.storeRepository = storeRepository;
        this.jtRepository = jtRepository;
    }

    @Override
    public JsResponseDto createJs(JsRequestDto jsRequestDto) {
        StoreEntity storeId = storeRepository.findById(jsRequestDto.getStoreId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "store not found"));
        JjimTitleEntity jtId = jtRepository.findByJtId(jsRequestDto.getJtId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "jt not found"));

        JjimStoreEntity jjimStoreEntity = new JjimStoreEntity();
        jjimStoreEntity.setStoreId(storeId);
        jjimStoreEntity.setJtId(jtId);

        jsRepository.save(jjimStoreEntity);

        return new JsResponseDto(jjimStoreEntity);
    }

    @Override
    public List<JjimStoreEntity> getJs(Integer jtId) {
        JjimTitleEntity jt = jtRepository.findByJtId(jtId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "jt not found"));
        return jsRepository.findByJtId(jt);
    }

    @Override
    public void deleteJs(Integer jtId, String storeId) {
        StoreEntity store = storeRepository.findById(storeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "store not found"));
        JjimTitleEntity jt = jtRepository.findByJtId(jtId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "jt not found"));

        JjimStoreEntity js = jsRepository.findByJtIdAndStoreId(jt, store);

        jsRepository.delete(js);
    }
}
