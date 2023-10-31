// BookmarkController.java (컨트롤러)
package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.BookMarkDAO;

public class BookmarkController extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        BookMarkDAO dao = new BookMarkDAO();
        
        // 기존 북마크 목록을 DAO를 통해 가져옴
        List<String> bookmarks = dao.getBookmarks();
        
        request.setAttribute("bookmarks", bookmarks);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/bookmarks.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    	    throws ServletException, IOException {
    	
    	    String newBookmark = request.getParameter("newBookmark");
    	    BookMarkDAO dao = new BookMarkDAO();
    	    
    	    if (request.getParameter("deleteBookmark") != null) {
    	        int index = Integer.parseInt(request.getParameter("deleteBookmark"));
    	        List<String> bookmarks = dao.getBookmarks();
    	        if (index >= 0 && index < bookmarks.size()) {
    	            dao.deleteBookmark(index);
    	        }
    	    } else {
    	        // 새 북마크를 DAO를 통해 추가
    	        dao.addBookmark(newBookmark);
    	    }
    	    
    	    response.sendRedirect(request.getContextPath() + "/bookmarks");
    	}

}
