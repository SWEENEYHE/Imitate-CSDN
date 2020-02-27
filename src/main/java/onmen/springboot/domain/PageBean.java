package onmen.springboot.domain;

import java.util.List;

/***
 * 分页封装类
 */
public class PageBean <T>{
    //总的条数
    private Integer totalItem;
    //总的页数
    private Integer pageCount;
    //当前所在页
    private Integer currentPage;
    //每页的大小
    private Integer pageSize;
    //封装的页
    private List<T> pageItems;

    public PageBean() {
    }

    public PageBean(Integer pageTotal, Integer pageCount, Integer currentPage, Integer pageSize, List<T> pageItems) {
        this.totalItem = pageTotal;
        this.pageCount = pageCount;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.pageItems = pageItems;
    }

    public Integer getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(Integer totalItem) {
        this.totalItem = totalItem;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getPageItems() {
        return pageItems;
    }

    public void setPageItems(List<T> pageItems) {
        this.pageItems = pageItems;
    }
}
