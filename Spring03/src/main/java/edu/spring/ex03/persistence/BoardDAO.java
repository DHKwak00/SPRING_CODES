package edu.spring.ex03.persistence;

import java.util.List;

import edu.spring.ex03.domain.BoardVO;
import edu.spring.ex03.pageutil.PageCriteria;

public interface BoardDAO {
	int insert(BoardVO vo);
	List<BoardVO> select();
	BoardVO select(int boardId);
	int update(BoardVO vo);
	int delete(int boardId);
	List<BoardVO> select(PageCriteria criteria);
	int getTotalCounts();
	List<BoardVO> select(String memberId);
	List<BoardVO> selectByTitleOrContent(String keyword);
	
	int updateReplyCnt(int amount, int boardId); // 게시글 전체 댓글 수
}
