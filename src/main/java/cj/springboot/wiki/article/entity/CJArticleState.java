package cj.springboot.wiki.article.entity;


import java.io.Serializable;



public enum CJArticleState implements Serializable {
    CJStart("start","开始"),
    CJImproved("improve","待补充"),
    CJVerify("verify","待验证"),
    CJFinish("finish","完成");

    private  String articleState;
    private  String stateDesc;
    //enum对象构造器
    CJArticleState(String articleState,String stateDesc){
        this.articleState = articleState;
        this.stateDesc = stateDesc;
    }

    public String getArticleState(){
        return  this.articleState;
    }
    public String getStateDesc(){
        return  this.stateDesc;
    }

    public static CJArticleState valueOfCode(String code) {
        for (CJArticleState status : CJArticleState.values()) {
            if (status.getArticleState().equals(code)) {
                return status;
            }
        }
        throw new IllegalArgumentException(
                "CJArticleState status cannot be resolved for code " + code);
    }


    @Override
    public String toString() {
        return articleState;
    }
}
