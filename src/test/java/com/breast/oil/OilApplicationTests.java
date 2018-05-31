package com.breast.oil;

import com.breast.oil.domain.KeyWord;
import com.breast.oil.domain.StatisticsInfo;
import com.breast.oil.domain.WebInfo;
import com.breast.oil.repository.*;
import com.breast.oil.services.WxTicketService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OilApplicationTests {
	@Autowired
	private StatisticsRepository mStatisticsRepository;
	@Autowired
	private WXInfoRepository mWXInfoRepository;
	@Autowired
	private StatisticsInfoRepository mStatisticsInfoRepository;
	@Autowired
	private WebInfoRepository mWebInfoRepository;
	@Autowired
	private KeyWordRepository mKeyWordRepository;
	@Autowired
	private WxTicketService mWxTicketService;
	@Test
	public void contextLoads() {

	}

	@Test
	public void sum(){
		long price = mStatisticsRepository.totalMoney("fx1",1511777042292L,1511835772435L);
		assert(129*3L==price);
	}

	@Test
	public void findWxByIp(){
		List<WebInfo> webInfo = mWebInfoRepository.findByIpOrderByIdDesc("127.0.0.1");
		assert (webInfo!=null);
	}

	@Test
	public void countKeyWord(){
		String keyWord = "1001";
		long start = 0;
		long end = 1513586285412L;
		long count = mWXInfoRepository.countByKeyWordAndCreateTimeGreaterThanEqualAndCreateTimeLessThan(keyWord,start,end);
		assert (1 == count);
	}

	@Test
	public void count(){
		List<StatisticsInfo> list = mStatisticsInfoRepository.findByUrlAndCreateTimeGreaterThanEqualAndCreateTimeLessThan("fx",0L,0L);
		assert (list!=null);

	}

	@Test
	public void testUpdateKeyWord(){
		KeyWord keyWord = new KeyWord("55","9",new Date().getTime());
		mKeyWordRepository.save(keyWord);
	}


	@Test
	public void testgetTicket(){
		mWxTicketService.getTicket();
	}
}
