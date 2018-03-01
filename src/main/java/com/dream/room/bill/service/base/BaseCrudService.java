package com.dream.room.bill.service.base;

import com.dream.room.bill.common.PageQueryDto;
import com.dream.room.bill.common.model.BaseEntity;
import com.dream.room.bill.common.model.ErrorResult;
import com.dream.room.bill.repository.base.MyCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/1/30.
 */
public abstract class BaseCrudService<T extends BaseEntity, R extends MyCrudRepository<T>> {

    @Autowired
    private R repository;

    public R getRepository() {
        return repository;
    }

    public T save(T entity){
        return repository.save(entity);
    }

    public List<T> saveAll(Iterable<T> list){
        return repository.saveAll(list);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public List<T> findAll(){
        return repository.findAll();
    }

    public T findById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> ErrorResult.builder()
                        .status(HttpStatus.NOT_FOUND)
                        .title("不存在该值！")
                        .build()
                        .toException()
                );
    }

    public List<T> findAllById(Iterable<Long> ids){
        return repository.findAllById(ids);
    }

    public <Q extends PageQueryDto> Page<T> findAll(Example<T> example, Q dto){
        if (StringUtils.isEmpty(dto.getSort())){
            return repository.findAll(example, PageRequest.of(dto.getPage(), dto.getSize()));
        }
        Sort.Direction direction = "desc".equals(dto.getDirection())?Sort.Direction.DESC:Sort.Direction.ASC;
        Sort sort = Sort.by(direction, dto.getSort());
        return repository.findAll(example,PageRequest.of(dto.getPage(),dto.getSize(),sort));
    }

}
