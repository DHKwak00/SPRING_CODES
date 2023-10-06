package edu.spring.ex02;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import edu.spring.ex02.domain.BoardVO;
import edu.spring.ex02.pageutil.PageCriteria;
import edu.spring.ex02.persistence.BoardDAO;

@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@WebAppConfiguration
public class BoardDAOTest {
	private static final Logger logger = 
			LoggerFactory.getLogger(BoardDAOTest.class);
	
	// 최고 중요*** 테스트 꼭!!!! 해봐야함
	@Autowired
	private BoardDAO dao; // 다형성*
	
	@Test
	public void testDAO() {
//		testInsert();
//		testSelectAll();
//		testSelectByBoardId();
//		testUpdate();
//		testDelete();
//		testSelectPaging();
//		testTotalCount();
		testSelectSearch();
	}

	private void testSelectSearch() {
//		List<BoardVO> list = dao.select("k");
		List<BoardVO> list = dao.selectByTitleOrContent("e");
		for(BoardVO vo : list) {
			logger.info(vo.toString());
		}
		
	}

	private void testTotalCount() {
		int totalCounts = dao.getTotalCounts();
		logger.info("총 게시글 수 : " + totalCounts);
	}

	private void testSelectPaging() {
		PageCriteria criteria = new PageCriteria(1, 3);
		List<BoardVO> list = dao.select(criteria);
		for(BoardVO vo : list) {
			logger.info(vo.toString());
		}
		
	}

	private void testDelete() {
		int result = dao.delete(2);
		if(result == 1) {
			logger.info("delete 성공");
		} else {
			logger.info("delete 실패");
		}
		
	}

	private void testUpdate() {
		BoardVO vo = new BoardVO(2, "update", "update", null, null, 0);
		int result = dao.update(vo);
		if(result == 1) {
			logger.info("update 성공");
		} else {
			logger.info("update 실패");
		}
		
	}

	private void testSelectByBoardId() {
		BoardVO vo = dao.select(2);
		logger.info(vo.toString());
	}

	private void testSelectAll() {
		List<BoardVO> list = dao.select();
		for(BoardVO vo : list) {
			logger.info(vo.toString());
		}
		
	}

	private void testInsert() {
		BoardVO vo = new BoardVO(0, "안녕", "목요일", "kwk", null, 0);
		int result = dao.insert(vo);
		if(result == 1) {
			logger.info("insert 성공");
		} else {
			logger.info("insert 실패");
		}
		
	}
}
