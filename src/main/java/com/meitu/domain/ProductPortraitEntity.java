package com.meitu.domain;

/**
 * 商品画像
 */
public class ProductPortraitEntity {

    private int areScore;
    private int categoryScore;


    public int getAreScore() {
        return areScore;
    }

    public void setAreScore(int areScore) {
        this.areScore = areScore;
    }

    public int getCategoryScore() {
        return categoryScore;
    }

    public void setCategoryScore(int categoryScore) {
        this.categoryScore = categoryScore;
    }

    public int getTotal(){
        int ret = 0;
        ret += (areScore*areScore) + (categoryScore*categoryScore);
        return ret;
    }
}
