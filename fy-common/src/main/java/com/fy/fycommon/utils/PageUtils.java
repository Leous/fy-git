package com.fy.fycommon.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: cnc
 * @date: 2019-01-07 0:37
 */
public class PageUtils implements Serializable {

    private static final long serialVersionUID = 2242714664011021174L;

    private int total;  //总条数

    private int pageNumber;  //总页数

    private int pageIndex;  //当前页

    private List elements;  //保存结果集

    private int first;  //当前页从第几行开始

    private int pageSize;  //一页显示多少行

    private String sysTime;

    /**
     * 计算分页总数
     * @param total
     * @param pageSize
     * @return
     */
    public static long getPageCount(long total, long pageSize) {
        return (total + pageSize - 1) / pageSize;
    }

    /**
     * <p>
     * The default constrctor ,after new PageUtils() You should use 'setXXX()'methods
     * to give the value of this object
     * </p>
     */
    public PageUtils() {
        refresh();
    }

    public PageUtils(int pageIndex, int total, int pageSize) {
        this.total = total;
        this.pageSize = pageSize;
        if (total == 0) {
            return;
        }
        pageNumber = total / pageSize;
        if (total % pageSize > 0) {
            pageNumber++;
        }
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        if (pageIndex > pageNumber) {
            pageIndex = pageNumber;
        }
        this.pageIndex = pageIndex;
        this.first = (pageIndex - 1) * pageSize;
    }

    /**
     * 重置参数
     */
    public void refresh() {
        pageIndex = 1;
        pageNumber = 0;
        total = 0;
        pageSize = 30;
    }

    public int getTotal() {
        return total;
    }

    public int getPageNumber() {
        if (pageNumber < 1) {
            pageNumber = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
        }
        return pageNumber;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public List getElements() {
        return elements;
    }

    public void setElements(List elements) {
        this.elements = elements;
    }

    public int getFirst() {
        return first;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public String getSysTime() {
        return sysTime;
    }

    public void setSysTime(String sysTime) {
        this.sysTime = sysTime;
    }

    public String showAjaxPage(HttpServletRequest request, String ajaxDiv){

        String pageResult = ""; // 封装分页的内容
        String ajaxUrl = request.getRequestURI();
        if(request.getParameterMap().size() > 0){
            Map requestMap = FyUtils.formMap(request.getParameterMap());
            requestMap.remove("inajax");
            requestMap.remove("ajaxDiv");
            requestMap.remove("pageIndex");
            if(requestMap.size() > 0){
                ajaxUrl += "?inajax=1&" + FyUtils.createLinkString(requestMap);
            }else{
                ajaxUrl += "?inajax=1";
            }
        }else{
            ajaxUrl += "?inajax=1";
        }

        int endPage = 0;
        if("".equals(ajaxDiv)){
            ajaxDiv = "listDiv"; //默认ajax div
        }

        if(getTotal() == 0){
            return "";
        }

        if (getPageIndex() - 1 < 1) {//是第一页的时候
            pageResult += "<li><span>«</span></li>";
        } else {
            pageResult += "<li><a onclick=\"ajaxPage('"+ ajaxUrl + "&pageIndex=" + (getPageIndex()-1) + "&ajaxDiv=" +ajaxDiv+"','"+ajaxDiv+"')\" href=\"javascript:;\">«</a></li>";
        }

        if (getPageIndex() != 1) {
            pageResult += "<li><a onclick=\"ajaxPage('"+ ajaxUrl + "&pageIndex=1&ajaxDiv=" +ajaxDiv+"','"+ajaxDiv+"')\" href=\"javascript:;\">1</a></li>";
        }

        if (getPageIndex() >= 4) {
            pageResult += "<li><span>...</span></li>";
        }

        if (getPageNumber() > getPageIndex() + 2) {
            endPage = getPageIndex() + 2;
        } else {
            endPage = getPageNumber();
        }

        for (int i = getPageIndex() - 2; i <= endPage; i++) {
            if (i > 0) {
                if (i == getPageIndex()) {
                    pageResult += "<li class=\"active\"><a href=\"javascript:;\">" + i + "</a></li>";
                } else {
                    if (i != 1 && i != getPageNumber()) {
                        pageResult += "<li><a onclick=\"ajaxPage('"+ ajaxUrl + "&pageIndex=" + i + "&ajaxDiv=" +ajaxDiv+"', '"+ajaxDiv+"')\" href=\"javascript:;\">"+i+"</a></li>";
                    }
                }
            }
        }
        if (getPageIndex() + 3 < getPageNumber()) {
            pageResult += "<li><span>...</span></li>";
        }//从第3个开始就用。。。代替

        if (getPageIndex() != getPageNumber()) {
            pageResult += "<li><a onclick=\"ajaxPage('"+ ajaxUrl + "&pageIndex=" + getPageNumber() + "&ajaxDiv=" +ajaxDiv+"', '"+ajaxDiv+"')\" href=\"javascript:;\">"+ getPageNumber() +"</a></li>";
        }

        if (getPageIndex() + 1 > getPageNumber()) {
            pageResult += "<li><span title='下一页'>»</span></li>";
        } else {
            pageResult += "<li><a onclick=\"ajaxPage('"+ ajaxUrl + "&pageIndex=" + (getPageIndex()+1) + "&ajaxDiv=" +ajaxDiv+"', '"+ajaxDiv+"')\" href=\"javascript:;\">»</a></li>";
        }

//        pageResult += "<nav class=\"pull-right search-page\">共 "+getTotal()+" 页, 到第 <input size=\"2\"> 页 <a class=\"btn btn-default btn-ssm\" href=\"javascript:;\"> 确定 </a></nav>";
        return pageResult;
    }
}
