package com.darayuth.randompicture;

public class Picture {
    private String mImage;
    private String mLocation;
    private String mName;
    public Picture(String name,String location,String image){
        if(location.equals("null") ){
           this.mLocation = "Not provided";
        }else{
            this.mLocation = location;
        }
        if (name.equals("null")) {
            this.mName = "Not Found";
        }else{
            this.mName = name;
        }
        this.mImage = image;
    }

    public String getmImage(){
        return this.mImage;
    }

    public String getmLocation(){
        return this.mLocation;
    }
    public String getmName(){
        return mName;
    }
}
