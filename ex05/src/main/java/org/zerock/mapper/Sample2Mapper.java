package org.zerock.mapper;

import org.apache.ibatis.annotations.Insert;

public interface Sample2Mapper {
	//DAO 역할의 인터페이스ㄴ
	
	@Insert("insert into tbl_sample2 (col2) values (#{data}) ")
	public int insertCol2(String data);
}
