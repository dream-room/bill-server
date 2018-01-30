package com.dream.room.bill.service.base;

import com.dream.room.bill.common.model.ErrorResult;
import com.dream.room.bill.repository.base.MyCrudRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/1/30.
 */
public abstract class BaseCrudService<T, ID, R extends MyCrudRepository<T,ID>> {

    @Resource
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

    public void deleteById(ID id){
        repository.deleteById(id);
    }

    public List<T> findAll(){
        return repository.findAll();
    }

    public T findById(ID id){
        return repository.findById(id)
                .orElseThrow(() -> ErrorResult.builder()
                        .status(HttpStatus.NOT_FOUND)
                        .title("不存在该值！")
                        .build()
                        .toException()
                );
    }

    public List<T> findAllById(Iterable<ID> ids){
        return repository.findAllById(ids);
    }

    public abstract Page<T> findAll(Example<T> example, Pageable pageable);

}
