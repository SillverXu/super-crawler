package com.bmsoft.xu.bean;

/**
 * Created by 81954 on 2017/11/19.
 */
public class RuleBean {

    /**
     * 返回的html，第一次过滤所用的标签，请先设置type
     */
    private String resultTagName;   //jsoup解析操作字符串
    private int type = ID;           //使用解析规则
    private int index = -1;         //是否有获取第N个元素

    public final static int CLASS = 0;
    public final static int ID = 1;
    public final static int SELECTION = 2;
    public final static int TAG = 3;

    public RuleBean() {
    }

    public RuleBean(String resultTagName, int type) {
        this.resultTagName = resultTagName;
        this.type = type;
    }

    public RuleBean(String resultTagName, int type, int index) {
        this.index = index;
        this.resultTagName = resultTagName;
        this.type = type;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getResultTagName() {
        return resultTagName;
    }

    public void setResultTagName(String resultTagName) {
        this.resultTagName = resultTagName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


}
