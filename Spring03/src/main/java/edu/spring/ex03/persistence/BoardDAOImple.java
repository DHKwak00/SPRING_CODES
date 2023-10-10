package edu.spring.ex03.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.spring.ex03.domain.BoardVO;
import edu.spring.ex03.pageutil.PageCriteria;

@Repository // @Component
public class BoardDAOImple implements BoardDAO {
	
	private static final Logger logger = 
			LoggerFactory.getLogger(BoardDAO.class);
	
	public static final String NAMESPACE = 
			"edu.spring.ex03.BoardMapper";
	
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insert(BoardVO vo) {
		logger.info("insert() ȣ��");
		return sqlSession.insert(NAMESPACE + ".insert", vo);
		
	}

	@Override
	public List<BoardVO> select() {
		logger.info("select() ȣ��");
		return sqlSession.selectList(NAMESPACE + ".select_all");
	}

	@Override
	public BoardVO select(int boardId) {
		logger.info("select() ȣ�� boardId = " + boardId);
		return sqlSession.selectOne(NAMESPACE + ".select_by_board_id", boardId);
	}

	@Override
	public int update(BoardVO vo) {
		logger.info("update() ȣ�� vo = " + vo.toString());
		return sqlSession.update(NAMESPACE + ".update", vo);
	}

	@Override
	public int delete(int boardId) {
		logger.info("delete() ȣ�� boardId = " + boardId);
		return sqlSession.delete(NAMESPACE + ".delete", boardId);
	}

	@Override
	public List<BoardVO> select(PageCriteria criteria) {
		logger.info("select() ȣ��");
		logger.info("start = " + criteria.getStart());
		logger.info("end = " + criteria.getEnd());
		return sqlSession.selectList(NAMESPACE + ".paging", criteria);
	}

	@Override
	public int getTotalCounts() {
		logger.info("getTotalCounts()");
		return sqlSession.selectOne(NAMESPACE + ".total_count");
	}

	@Override
	public List<BoardVO> select(String memberId) {
		logger.info("ȣ�� : memberId = " + memberId);	
		return sqlSession.selectList(NAMESPACE + ".select_by_memberid", "%" + memberId + "%");
	}

	@Override
	public List<BoardVO> selectByTitleOrContent(String keyword) {
		logger.info("selectByTitleOrContent() ȣ��");
		return sqlSession.selectList(NAMESPACE + ".select_by_title_content", "%" + keyword + "%");
	}

	@Override
	public int updateReplyCnt(int amount, int boardId) {
		logger.info("updateReplyCnt() : boardId = " + boardId);
		Map<String, Integer> args = new HashMap();
		args.put("amount", amount);
		args.put("boardId", boardId);
		return sqlSession.update(NAMESPACE + ".update_reply_cnt", args);
	}

}
