package com.breast.oil;

import com.breast.oil.domain.WebAndWXCount;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by B04e on 2017/12/19.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CountTests {

    @Test
    public void countWxAndWeb(){
        WebAndWXCount webAndWXCount = new WebAndWXCount("1","方案1",20L,1000L);
        String percentage = webAndWXCount.getPercentage();
        assert ("2.0000%".equals(percentage));
    }
}
