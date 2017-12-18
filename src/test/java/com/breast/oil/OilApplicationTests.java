package com.breast.oil;

import com.breast.oil.repository.StatisticsRepository;
import com.breast.oil.repository.WXInfoRepository;
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
	@Autowired
	private WXInfoRepository mWXInfoRepository;
	@Test
	public void contextLoads() {
	}

	@Test
	public void sum(){
		long price = mStatisticsRepository.totalMoney("fx1",1511777042292L,1511835772435L);
		assert(129*3L==price);
	}

	@Test
	public void countKeyWord(){
		String keyWord = "1001";
		long start = 0;
		long end = 1513586285412L;
		long count = mWXInfoRepository.countByKeyWordAndCreateTimeGreaterThanEqualAndCreateTimeLessThan(keyWord,start,end);
		assert (1 == count);
	}
}
