package org.zerock.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.service.SampleTxService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@Log4j
public class SampleTxServiceTests {
	
	@Setter(onMethod_ = { @Autowired })
	private SampleTxService service;
	
	@Test
	public void testLong() {
		String str = "Starry\r\n" +
				"Starry night\r\n" +
				"Paint your palatte blue and grey\r\n" +
				"Look out on a summer's day";
		log.info(str.getBytes().length);
		
		service.addData(str);
	}//tbl_sample2 의 글자 수가 50개 제한이기 때문에 tbl_Sample1 테이블에만 값이 들어가고 2에는 들어가지 않음 => 트랜잭션 처리 필요
}
