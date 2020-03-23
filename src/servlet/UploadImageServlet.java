package servlet;

import bean.Message;
import bean.UserBean;

import com.alibaba.fastjson.JSON;

import dao.UserDao;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
@WebServlet("/UploadImageServlet")
public class UploadImageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
  
        Message message = new Message();
        UserBean userBean=new UserBean();
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> items = null;
        try {
            items = (List<FileItem>) upload.parseRequest(request);
        } catch (FileUploadException e1) {
            // TODO 自动生成的 catch 块
            e1.printStackTrace();
        }
        Iterator<FileItem> it = items.iterator();//迭代器遍历
        if (items != null) {
            while (it.hasNext()) {
                FileItem tempitemFileItem = it.next();
                String itemNameString = tempitemFileItem.getFieldName();
                System.out.println(itemNameString);
                if (tempitemFileItem.isFormField()) {//判断是否为表单字段
                    String content = tempitemFileItem.getString("utf-8");
                    
                    if("userID".equals(itemNameString))
                    	userBean.setUserID(Integer.valueOf(content));
                     } else {
                    System.out.println(request.getSession().getServletContext().getRealPath("/"));
                    File tempFile = new File(request.getSession().getServletContext().getRealPath("/") + "image" + File.separator
                            + new File(System.currentTimeMillis() + "_" + tempitemFileItem.getName()).getName());//拼接路径保存导一个路径下，时间戳+文件名
          
                    if (!tempFile.getParentFile().exists()) tempFile.getParentFile().mkdirs();//image是否存在判断，不存在就创建
                    System.out.println(tempFile);
                    try {
                        tempitemFileItem.write(tempFile);//写入

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    userBean.setUserPicture(tempFile.getName());
         
                }

            }
        }
        try {
             UserDao userDao = new UserDao();
            boolean addFlag =userDao.update_picture(userBean.getUserID(), userBean.getUserPicture());
            if (addFlag) {
                System.out.println("成功");
                message.setCode(200);
                message.setData("token");
                message.setMessage("修改头像成功");
                out.print(JSON.toJSONString(message));
            } else {
                System.out.println("false");
                message.setCode(-11);
                message.setData("null");
                message.setMessage("修改头像失败");
                out.print(JSON.toJSONString(message));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block

            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
