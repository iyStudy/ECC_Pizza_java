package com.example.eccpizza.model;

// "Pizza"という名前のクラスを定義しています。
// このクラスは、ピザの情報（名前、価格、画像のURL）を保存するためのデータ構造を持っています。
public class Pizza {

    // ピザの名前を保存するための変数
    private String pname;

    // ピザの価格を保存するための変数
    private String price;

    // ピザの画像のURLを保存するための変数
    private String image_url;

    // pname変数の値を取得するためのメソッド
    public String getPname() {
        return pname;
    }

    // price変数の値を取得するためのメソッド
    public String getPrice() {
        return price;
    }

    // image_url変数の値を取得するためのメソッド
    public String getImage_url() {
        return image_url;
    }
}
