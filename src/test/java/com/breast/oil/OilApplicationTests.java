package com.breast.oil;

import com.breast.oil.repository.StatisticsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OilApplicationTests {
	@Autowired
	private StatisticsRepository mStatisticsRepository;
	@Test
	public void contextLoads() {
	}

	@Test
	public void sum(){
		long price = mStatisticsRepository.totalMoney("fx1",1511777042292L,1511835772435L);
		assert(129*3L==price);
	}
}
