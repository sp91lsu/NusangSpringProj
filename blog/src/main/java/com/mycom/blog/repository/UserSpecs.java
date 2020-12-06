package com.mycom.blog.repository;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.mockito.Mockito.RETURNS_SMART_NULLS;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EnumType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.mycom.blog.dto.User;
import com.mycom.blog.dto.enumtype.GenderType;
import com.mycom.blog.dto.enumtype.RoleType;

public class UserSpecs {
	public enum SearchKey {
		//findBy
		//form-name(테이블칼럼명)
		NO_MIN("userno"),
		NO_MAX("userno"),
        NAME("username"),
        ID("userid"),
		PW("password"),
		NIC("nickname"),
		AGE_MIN("age"),
		AGE_MAX("age"),
		GENDER("gender"),
		EMAIL("email"),
		COIN_MIN("coin"),
		COIN_MAX("coin"),
		TPAY_MIN("totalPay"),
		TPAY_MAX("totalPay"),
		TALK_MIN("availableTalk"),
		TALK_MAX("availableTalk"),
		DATE_MIN("createDate"),
		DATE_MAX("createDate"),
		ROLE("role"),
		;

        private final String value;

        SearchKey(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
	
	
	public static Specification<User> searchWith(Map<SearchKey, Object> searchKeyword) {
	    return (Specification<User>) ((root, query, builder) -> {
	        List<Predicate> predicate = getPredicateWithKeyword(searchKeyword, root, builder);
	        return builder.and(predicate.toArray(new Predicate[0]));
	    });
	}
	
	private static List<Predicate> getPredicateWithKeyword(Map<SearchKey, Object> searchKeyword, Root<User> root, CriteriaBuilder builder) {
	    List<Predicate> predicate = new ArrayList<>();
	    AA:for (SearchKey key : searchKeyword.keySet()) {
	        switch (key) {
	        	// EQUAL
	        	case GENDER:
	        		String keyword = (String) searchKeyword.get(key);
	        		if(keyword.equals("ALL")) continue AA;
	        		predicate.add(builder.equal(
	        				root.get(key.value),GenderType.valueOf(keyword)
	        				));
	        		break;
	        	case ROLE:
	        		String keyword1 = (String) searchKeyword.get(key);
	        		if(keyword1.equals("ALL")) continue AA;
	        		predicate.add(builder.equal(
	        				root.get(key.value),RoleType.valueOf(keyword1)
	        				));
	        		break;
	        	// LIKE
	            case NAME:
	            case ID:
	            case PW:
	            case NIC:
	            case EMAIL:
	                predicate.add(builder.like(
	                        root.get(key.value),"%"+searchKeyword.get(key)+"%"
	                ));
	                break;
                // GREATER THAN EQUAL
	            case NO_MIN:
	            case AGE_MIN:
	            case COIN_MIN:
	            case TPAY_MIN:
	            case TALK_MIN:
	                predicate.add(builder.greaterThanOrEqualTo(
	                        root.get(key.value), Integer.valueOf(searchKeyword.get(key).toString())
	                ));
	                break;
                // LESS THAN EQUAL
	            case NO_MAX:
	            case AGE_MAX:
	            case COIN_MAX:
	            case TPAY_MAX:
	            case TALK_MAX:
	            	predicate.add(builder.lessThanOrEqualTo(
	            			root.get(key.value), Integer.valueOf(searchKeyword.get(key).toString())
	            			));
	            	break;
	        }
	    }
	    return predicate;
	}
}
