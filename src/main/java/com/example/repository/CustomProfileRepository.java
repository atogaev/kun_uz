package com.example.repository;

import com.example.dto.ProfileFilterDTO;
import com.example.entity.ProfileEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CustomProfileRepository {

    @Autowired
    private EntityManager entityManager;

    public List<ProfileEntity> filter(ProfileFilterDTO filterDTO) {
        StringBuilder s = new StringBuilder("select p from ProfileEntity as p where p.visible = true ");
        Map<String, Object> param = new HashMap<>();
        if (filterDTO.getName() != null) {
            s.append("and p.name =:name ");
            param.put("name", filterDTO.getName());
        }
        if (filterDTO.getSurname() != null) {
            s.append("and p.surname =: surname ");
            param.put("surname", filterDTO.getSurname());
        }
        if (filterDTO.getPhone() != null) {
            s.append("and p.phone =:phone ");
            param.put("phone", filterDTO.getPhone());
        }
        if (filterDTO.getRole() != null) {
            s.append("and p.role =: role ");
            param.put("role", filterDTO.getRole());
        }
        if (filterDTO.getCreated_date_from() != null) {
            s.append("and p.created_date >=:created_data_from ");
            param.put("created_data_from",filterDTO.getCreated_date_to());
        }
        if (filterDTO.getCreated_date_to() != null){
            s.append("and p.created_date <=:created_data_to ");
            param.put("created_data_to",filterDTO.getCreated_date_to());
        }
         Query query =  entityManager.createQuery(s.toString());
        for (Map.Entry<String,Object> params: param.entrySet()){
            query.setParameter(params.getKey(),params.getValue());
        }

        return (List<ProfileEntity>) query.getResultList();
    }
}
