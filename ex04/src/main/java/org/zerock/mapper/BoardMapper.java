package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

public interface BoardMapper {
	//추상메소드 정의
	
	public List<BoardVO> getList();
	
	public List<BoardVO> getListWithPaging(Criteria cri);
	
	public void insert(BoardVO board);
	
	public void insertSelectKey(BoardVO board);
	
	public BoardVO read(Long bno);
	
	public int delete(Long bno);
	
	public int update(BoardVO board);
	
	public int getTotalCount(Criteria cri);
	
	//댓글이 삭제,추가 될 때마다 게시글 댓글 갯수를 업데이트 하기 위한 메소드
	public void updateReplyCnt(
			@Param("bno") Long bno, 
			@Param("amount") int amount);  //두가지 이상의 파라미터 값이 넘어가기 위해서는 @Param 어노테이션을 사용해야 한다.
}
