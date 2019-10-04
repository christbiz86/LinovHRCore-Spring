package com.demo.dao;

import com.demo.model.Grade;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GradeDao extends ParentDao {

	@SuppressWarnings("unchecked")
    public List<Grade> findAll(){
        return super.entityManager.createQuery("FROM Grade").getResultList();
    }

    @SuppressWarnings("unchecked")
    public Grade findById(String id){
        List<Grade> grade = super.entityManager.createQuery("FROM Grade where id=:id").setParameter("id",id)
            .getResultList();
        if(grade.size() == 0){
             return new Grade();
        } else {
            return grade.get(0);
        }
    }

    @SuppressWarnings("unchecked")
    public List<Grade> findByCode(String code){
        return super.entityManager.createQuery("FROM Grade where code=:code").setParameter("code",code)
                .getResultList();
    }

    public void save(Grade grade){
        super.entityManager.merge(grade);
    }

    public void delete(String id){
        Grade grade = findById(id);
        super.entityManager.remove(grade);
    }

}
