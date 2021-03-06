package com.mycom.blog.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycom.blog.dto.Item;
import com.mycom.blog.dto.Location;
import com.mycom.blog.dto.Shop;
import com.mycom.blog.dto.User;
import com.mycom.blog.dto.enumtype.AuthType;
import com.mycom.blog.dto.enumtype.ItemType;
import com.mycom.blog.dto.enumtype.RoleType;
import com.mycom.blog.repository.ChatRoomRepository;
import com.mycom.blog.repository.ItemRepository;
import com.mycom.blog.repository.LocationRepository;
import com.mycom.blog.repository.ShopRepository;
import com.mycom.blog.repository.UserRepository;

//스프링이 컴포넌트 스캔을 통해서 bean에 등록해줌 ioc 
@Service
public class ShopService extends BasicService<ShopRepository, Shop> {

	@Autowired
	UserRepository userRep;

	@Autowired
	ShopRepository shopRep;

	@Autowired
	ItemRepository itemRep;

	@PostConstruct
	public void init() {

		createShop();
	}

	@Transactional
	public void createShop() {
		System.out.println("상점 생성");
		List<Shop> list = findAll();

		if (list.size() == 0) {
			System.out.println("상점 생성");
			Shop shop = Shop.builder().itemType(ItemType.COIN).build();
			shopRep.save(shop);
			itemRep.save(createItem_coin(50, 100, shop,"/image/cm1.png"));
			itemRep.save(createItem_coin(100, 110, shop,"/image/cm2.png"));
			itemRep.save(createItem_coin(500, 120, shop,"/image/cm3.png"));
			itemRep.save(createItem_coin(1000, 130, shop,"/image/cm4.png"));
		}
	}

	public Item createItem_coin(int num, int price, Shop shop,String picture) {
		return Item.builder().type(ItemType.COIN).num(num).picture(picture).price(price).shop(shop).build();
	}

	@Autowired
	public ShopService(ShopRepository repository) {
		setRepository(repository);
	}

	public Shop findShop(ItemType type) {
		Shop shop = shopRep.findByItemType(type);

		return shop;
	}
}
