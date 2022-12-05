package com.example.dao;

import com.example.bean.BoardVO;
import com.example.util.JDBCUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BoardDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;

	private static final String M_SELECT = "";
	//Connection conn = null;
	//PreparedStatement stmt = null;
	//ResultSet rs = null;
	@Autowired
	private JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	private final String BOARD_INSERT = "insert into BOARD (title, writer, content, category, read_yn) values (?,?,?,?,?)";
	private final String BOARD_UPDATE = "update BOARD set title=?, writer=?, content=?, category=? where seq=?";
	private final String BOARD_DELETE = "delete from BOARD  where seq=?";
	private final String BOARD_GET = "select * from BOARD  where seq=?";
	private final String BOARD_LIST = "select * from BOARD order by seq desc";

	public int insertBoard(BoardVO vo) {
		System.out.println("===> JDBC로 insertBoard() 기능 처리");
		return template.update(BOARD_INSERT,
				new Object[]{vo.getTitle(), vo.getWriter(), vo.getContent(), vo.getCategory(), vo.getRead_yn()});
	}

	// 글 삭제
	public int deleteBoard(int id) {
		System.out.println("===> JDBC로 deleteBoard() 기능 처리");
		return template.update(BOARD_DELETE, new Object[]{id});
	}
	public int updateBoard(BoardVO vo) {
		System.out.println("===> JDBC로 updateBoard() 기능 처리");

		return template.update(BOARD_UPDATE,
				new Object[]{vo.getTitle(), vo.getWriter(), vo.getContent(), vo.getCategory(), vo.getSeq()});
	}

	public BoardVO getBoard(int seq) {
		return template.queryForObject(BOARD_GET,
				new Object[]{seq},
				new BeanPropertyRowMapper<BoardVO>(BoardVO.class));
	}

	public List<BoardVO> getBoardList(){
		return template.query(BOARD_LIST, new RowMapper<BoardVO>() {
			@Override
			public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				BoardVO data = new BoardVO();
				data.setSeq(rs.getInt("seq"));
				data.setTitle(rs.getString("title"));
				data.setWriter(rs.getString("writer"));
				data.setContent(rs.getString("content"));
				data.setCategory(rs.getString("category"));
				data.setRead_yn(rs.getInt("read_yn"));
				data.setRegdate(rs.getDate("regdate"));
				data.setCnt(rs.getInt("cnt"));
				return data;
			}
		});
	}

	class BoardRowMapper implements RowMapper<BoardVO>{
		@Override
		public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			BoardVO vo = new BoardVO();
			vo.setSeq(rs.getInt("seq"));
			vo.setTitle(rs.getString("title"));
			vo.setWriter(rs.getString("writer"));
			vo.setContent(rs.getString("content"));
			vo.setCategory(rs.getString("category"));
			vo.setRead_yn(rs.getInt("read_yn"));
			vo.setRegdate(rs.getDate("regdate"));
			vo.setCnt(rs.getInt("cnt"));
			return vo;
		}
	}
}
