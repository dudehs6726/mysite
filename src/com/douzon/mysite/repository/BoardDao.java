package com.douzon.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzon.mysite.vo.BoardVo;
import com.douzon.mysite.vo.GuestBookVo;
import com.douzon.mysite.vo.UserVo;

public class BoardDao {
	
	public boolean insert(BoardVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			String sql = "insert into borad( no, "
										+ "  title,"
										+ "  contents,"
										+ "  write_date,"
										+ "  hit,g_no,"
										+ "  o_no,"
										+ "  depth,";
									if(vo.getFileName() != null || "".equals(vo.getFileName()))
									{
										sql = sql + " upload_file_name, "
												+ " original_file_name, ";
									}
									sql = sql + "  user_no ) " 
								  + " select null,"
								  + 	   " ?,"
								  +        " ?, "
								  +        " now(), "
								  + "        0, "
								  + "		 ifnull(max(g_no) + 1, 1) ,"
								  + "		 1, "
								  + "		 0, ";
								  if(vo.getFileName() != null || "".equals(vo.getFileName()))
									{
										sql = sql + " '" + vo.getFileName() + "', "
												+ " '" + vo.getOriFileName() + "', ";
									}
								  sql = sql + "		 ? " 
					              +  "  from borad";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setLong(3, vo.getUserNo());
			System.out.println(pstmt.toString());
			int count = pstmt.executeUpdate();
			result = count == 1;
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public boolean insert(BoardVo vo, long v_no) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			String sql = "insert into borad( no, "
										+ "  title,"
										+ "  contents,"
										+ "  write_date,"
										+ "  hit,g_no,"
										+ "  o_no,"
										+ "  depth,";
										if(vo.getFileName() != null || "".equals(vo.getFileName()))
										{
											sql = sql + " upload_file_name, "
													+ " original_file_name, ";
										}
								sql = sql + "  user_no ) " 
								  + " select null, "
								  + " 		 ?, "
								  + " 		 ?, "
								  + " 		 now(), "
								  + " 		 0, "
								  + " 		 g_no , "
								  + " 		 o_no + 1, "
								  + " 		 depth + 1, ";
								if(vo.getFileName() != null || "".equals(vo.getFileName()))
								{
									sql = sql + " '" + vo.getFileName() + "', "
											+ " '" + vo.getOriFileName() + "', ";
								}
								sql = sql + " 		 ? " 
								  + "   from borad " 
								  + "  where no = ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setLong(3, vo.getUserNo());
			pstmt.setLong(4, v_no);
			
			int count = pstmt.executeUpdate();
			result = count == 1;
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public boolean updateHit(long no) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			String sql = " update borad " 
					+        "set hit = hit + 1 " 
					+     " where no = ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, no);
			
			int count = pstmt.executeUpdate();
			result = count == 1;
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public boolean update(BoardVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			String sql = " update borad set "
					+ "					title = ?, "
					+ "	  		  		contents = ? " 
					+ "     	  where no = ? " 
					+ "         	and user_no = ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setLong(3, vo.getNo());
			pstmt.setLong(4, vo.getUserNo());
			
			int count = pstmt.executeUpdate();
			result = count == 1;
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public boolean delete(BoardVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			String sql = "delete from borad " 
				  + "      where no = ? " 
				  + "        and user_no = ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, vo.getNo());
			pstmt.setLong(2, vo.getUserNo());
			
			int count = pstmt.executeUpdate();
			result = count == 1;
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public BoardVo get(Long v_no) {
		BoardVo result = null;
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			String sql = "select a.no, a.title, a.contents, a.user_no, a.upload_file_name, a.original_file_name " + 
						 "  from borad a " + 
						 " where a.no = ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, v_no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				long no	=	rs.getLong(1);
				String title = rs.getString(2);
				String contents = rs.getString(3);
				long userNo = rs.getLong(4);
				String fileName = rs.getString(5);
				String oriFileName = rs.getString(6);
				
				result = new BoardVo();
				result.setNo(no);
				result.setTitle(title);
				result.setContents(contents);
				result.setUserNo(userNo);
				result.setFileName(fileName);
				result.setOriFileName(oriFileName);
			}
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public List<BoardVo> getList(BoardVo v_vo, String kwd){
		
		List<BoardVo> list = new ArrayList<BoardVo>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int startPage = (v_vo.getPage() -1) * v_vo.getPagerowcount();
		//int endPage = 2 * page;
		//System.out.println(v_vo.getPage() + ":" + startPage);
		try {
			conn = getConnection();
			
			String sql = "select a.no, "
					+ "			 a.title, "
					+ "			 DATE_FORMAT(a.write_date, '%Y-%m-%d %h:%m:%s') as write_date, "
					+ "			 a.hit, "
					+ "          a.user_no, "
					+ "			 a.depth, "
					+ "			 b.name, "
					+ "          c.rownum, "
					+ "          a.upload_file_name "
					+ "  	from borad a, "
					+ "			 user b, "
					+ "          (select (count(*) - " + startPage + ") as rownum "
					+ "             from borad "
				    + "			   where 1=1 ";
					if(kwd != null || "".equals(kwd))
					{
						sql = sql + "      and (title LIKE CONCAT('%', " + kwd + " , '%')"
						+ "       or contents LIKE CONCAT('%', " + kwd + " , '%'))";
					}
					sql = sql + ") c"
					+ "    where a.user_no = b.no ";
					if(kwd != null || "".equals(kwd))
					{
						sql = sql + "      and (a.title LIKE CONCAT('%', " + kwd + " , '%')"
						+ "       or a.contents LIKE CONCAT('%', " + kwd + " , '%'))";
					}
					sql = sql + "    order by a.g_no desc, "
					+ "				a.o_no asc "
					+ "    limit " + startPage + " , " + v_vo.getPagerowcount();
					
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				long no = rs.getLong(1);
				String title = rs.getString(2);
				String writeDate = rs.getString(3);
				int hit = rs.getInt(4);
				long userNo = rs.getLong(5);
				int depth = rs.getInt(6);
				String userName = rs.getString(7);
				int rowNum = rs.getInt(8);
				String fileName = rs.getString(9);
				
				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setWriteDate(writeDate);
				vo.setHit(hit);
				vo.setUserNo(userNo);
				vo.setDepth(depth);
				vo.setUserName(userName);
				vo.setRownum(rowNum);
				vo.setFileName(fileName);
				
				list.add(vo);
			}
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	public void getPageList(BoardVo v_vo, String kwd){
		//BoardVo result = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			String sql = "select count(*) as count "
					+ "  	from borad a, "
					+ "			 user b " 
					+ "    where a.user_no = b.no ";
			if(kwd != null || "".equals(kwd))
			{
				sql = sql + "      and (a.title LIKE CONCAT('%', " + kwd + " , '%')"
				+ "       or a.contents LIKE CONCAT('%', " + kwd + " , '%'))";
			}
			sql = sql	+ "    order by a.g_no desc, "
					+ "				a.o_no asc ";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int totalCount = rs.getInt(1);
				
				//result = new BoardVo();
				v_vo.setTotalCount(totalCount);
				
			}
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public boolean getDeleteCheck(BoardVo v_vo){
		//BoardVo result = null;
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			String sql = "select a.no, a.title  " + 
					"       from borad a, (select g_no, o_no from borad where no = " + v_vo.getNo() + " ) b " + 
					"      where a.g_no = b.g_no " + 
					"        and a.o_no > b.o_no ";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			result = rs.next();
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
		
	}
	
	// block을 생성
	// 현재 페이지가 속한 block의 시작 번호, 끝 번호를 계산
	public void makeBlock(BoardVo vo, int curPage){
		
		int blockNum = 0;
	    blockNum = (int)Math.floor((curPage-1) / vo.getPagecount());
	    
	    vo.setBlockStartNum((vo.getPagecount() * blockNum) + 1);
	    vo.setBlockLastNum(vo.getBlockStartNum() + (vo.getPagecount() - 1));
	    
	    //return vo;
	}

	// 총 페이지의 마지막 번호
	public void makeLastPageNum(BoardVo vo) {

		vo.setLastPageNum((int)Math.ceil((double)vo.getTotalCount() / (double)vo.getPagerowcount()));
		/*
	    if( total % vo.getPagecount() == 0 ) {
	    	vo.setLastPageNum((int)Math.floor(total/vo.getPagerowcount()));
	    	//return vo;
	    }
	    else {
	    	vo.setLastPageNum((int)Math.floor(total/vo.getPagerowcount()) + 1);
	    	//return vo;
	    }
	    */
	  }

	// 검색을 했을 때 총 페이지의 마지막 번호
	/*
	public void makeLastPageNum(String kwd) {

		BoardDAO dao = new BoardDAO();
	    int total = dao.getCount(kwd);

	    if( total % pageCount == 0 ) {
	      lastPageNum = (int)Math.floor(total/pageCount);
	    }
	    else {
	      lastPageNum = (int)Math.floor(total/pageCount) + 1;
	    }
	  }
	*/
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		
		try {
			// 1.JDBC Driver(MySQL) 로딩
			Class.forName("com.mysql.jdbc.Driver");
			
			// 2.연결하기(Connection 객체 얻어오기)
			String url = "jdbc:mysql://localhost:3306/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		}catch(ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패" + e);
		}
		
		return conn;
	}
}
