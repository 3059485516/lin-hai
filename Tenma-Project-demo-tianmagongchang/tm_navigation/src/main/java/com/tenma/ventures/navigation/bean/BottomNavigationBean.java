package com.tenma.ventures.navigation.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Bin on 2018/1/25.
 */

public class BottomNavigationBean implements Parcelable {

    /**
     * key : ct_1
     * native : true
     * androidSrc : com.sobey.anative.NativeFragment
     * iosSrc : Comp01ViewController
     * wwwFolderIos :
     * wwwFolderAndroid :
     * title : 专题
     * image : https://www.easyicon.net/api/resizeApi.php?id=1201101&size=48
     * selectedImage : https://www.easyicon.net/api/resizeApi.php?id=1201100&size=48
     */

    private String key;
    @SerializedName("native")
    private boolean nativeX;
    private String androidSrc;
    private String iosSrc;
    private String wwwFolderIos;
    private String wwwFolderAndroid;
    private String title;
    private String image;
    private String selectedImage;
    private boolean isInterceptScroll;
    private boolean isNeedTitleBar;


    public BottomNavigationBean(){

    }

    private BottomNavigationBean(Parcel in) {
        key = in.readString();
        nativeX = in.readByte() != 0;
        androidSrc = in.readString();
        iosSrc = in.readString();
        wwwFolderIos = in.readString();
        wwwFolderAndroid = in.readString();
        title = in.readString();
        image = in.readString();
        selectedImage = in.readString();
        isInterceptScroll = in.readByte() != 0;
        isNeedTitleBar = in.readByte() != 0;
    }

    public static final Creator<BottomNavigationBean> CREATOR = new Creator<BottomNavigationBean>() {
        @Override
        public BottomNavigationBean createFromParcel(Parcel in) {
            return new BottomNavigationBean(in);
        }

        @Override
        public BottomNavigationBean[] newArray(int size) {
            return new BottomNavigationBean[size];
        }
    };


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean isNativeX() {
        return nativeX;
    }

    public void setNativeX(boolean nativeX) {
        this.nativeX = nativeX;
    }

    public String getAndroidSrc() {
        return androidSrc;
    }

    public void setAndroidSrc(String androidSrc) {
        this.androidSrc = androidSrc;
    }

    public String getIosSrc() {
        return iosSrc;
    }

    public void setIosSrc(String iosSrc) {
        this.iosSrc = iosSrc;
    }

    public String getWwwFolderIos() {
        return wwwFolderIos;
    }

    public void setWwwFolderIos(String wwwFolderIos) {
        this.wwwFolderIos = wwwFolderIos;
    }

    public String getWwwFolderAndroid() {
        return wwwFolderAndroid;
    }

    public void setWwwFolderAndroid(String wwwFolderAndroid) {
        this.wwwFolderAndroid = wwwFolderAndroid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSelectedImage() {
        return selectedImage;
    }

    public void setSelectedImage(String selectedImage) {
        this.selectedImage = selectedImage;
    }

    public boolean isInterceptScroll() {
        return isInterceptScroll;
    }

    public void setInterceptScroll(boolean interceptScroll) {
        isInterceptScroll = interceptScroll;
    }

    public boolean isNeedTitleBar() {
        return isNeedTitleBar;
    }

    public void setNeedTitleBar(boolean needTitleBar) {
        isNeedTitleBar = needTitleBar;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(key);
        dest.writeByte((byte) (nativeX ? 1 : 0));
        dest.writeString(androidSrc);
        dest.writeString(iosSrc);
        dest.writeString(wwwFolderIos);
        dest.writeString(wwwFolderAndroid);
        dest.writeString(title);
        dest.writeString(image);
        dest.writeString(selectedImage);
        dest.writeByte((byte) (isInterceptScroll ? 1 : 0));
        dest.writeByte((byte) (isNeedTitleBar ? 1 : 0));
    }
}
