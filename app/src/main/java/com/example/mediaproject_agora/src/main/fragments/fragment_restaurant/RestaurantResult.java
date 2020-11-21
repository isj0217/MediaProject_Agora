package com.example.mediaproject_agora.src.main.fragments.fragment_restaurant;

public class RestaurantResult {

    int tastehouse_idx;
    String tastehouse_name;
    float tastehouse_star;
    String menu_picture;
    String menu_name;
    int menu_price;
    String tastehouse_content;
    String nickname;
    int comment_num;
    String time;

    public RestaurantResult(int tastehouse_idx, String tastehouse_name, float tastehouse_star, String menu_picture, String menu_name, int menu_price, String tastehouse_content, String nickname, int comment_num, String time) {
        this.tastehouse_idx = tastehouse_idx;
        this.tastehouse_name = tastehouse_name;
        this.tastehouse_star = tastehouse_star;
        this.menu_picture = menu_picture;
        this.menu_name = menu_name;
        this.menu_price = menu_price;
        this.tastehouse_content = tastehouse_content;
        this.nickname = nickname;
        this.comment_num = comment_num;
        this.time = time;
    }

    public int getTastehouse_idx() {
        return tastehouse_idx;
    }

    public String getTastehouse_name() {
        return tastehouse_name;
    }

    public float getTastehouse_star() {
        return tastehouse_star;
    }

    public String getMenu_picture() {
        return menu_picture;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public int getMenu_price() {
        return menu_price;
    }

    public String getTastehouse_content() {
        return tastehouse_content;
    }

    public String getNickname() {
        return nickname;
    }

    public int getComment_num() {
        return comment_num;
    }

    public String getTime() {
        return time;
    }
}
