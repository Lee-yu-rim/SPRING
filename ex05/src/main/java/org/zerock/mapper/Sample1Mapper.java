package org.zerock.mapper;

import org.apache.ibatis.annotations.Insert;

public interface Sample1Mapper {
	//DAO 역할의 인터페이스
	
	@Insert("insert into tbl_sample1 (col1) values (#{data}) ")
	public int insertCol1(String data);
}
