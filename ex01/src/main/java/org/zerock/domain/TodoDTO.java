package org.zerock.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class TodoDTO {
	private String title;
	@DateTimeFormat(pattern = "yyyy/MM/dd")  //파라메터 값은 문자열로 넘어가기 때문에 날짜 데이터는 형변환이 필요함 => @DateTimeFormat 어노테이션을 통해 날짜정보를 문자열로 변환함
	private Date dueDate;  
}
