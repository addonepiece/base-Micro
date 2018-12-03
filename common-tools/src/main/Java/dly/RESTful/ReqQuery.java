package dly.RESTful;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Slf4j
@Data
public class ReqQuery<T> implements Serializable {
    private static final long serialVersionUID = 5306877151386123312L;

    // 分页信息，pageRow>0时分页取数据
    // 页大小
    private int pageRow = 0;

    // 起始页
    private int startPage = 0;

    // 总页数
    private long totalRow = 0;

    // 排序字段
    private String orderBy = null;

    // 查询条件
    private T object;

    public ReqQuery()
    {
    }

    public ReqQuery(T object)
    {
        this.object=object;
    }

}
