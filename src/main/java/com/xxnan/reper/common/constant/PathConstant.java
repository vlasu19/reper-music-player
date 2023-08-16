package com.xxnan.reper.common.constant;

public class PathConstant {
    //AliOss资源路径处理
    public static final String USER_AVATOR="img/user avator/";
    public static final String SINGER_PIC="img/singer pic/";
    public static final String SONGLIST_PIC="img/songList pic/";
    public static final String SONG_PIC="img/song pic/";
    public static final String SONG_URL="song/";

    //添加时直接设置的默认值
    public static final String DEFAULT_PIC="img/PicDefault.jpg";
    public static final String SONG_DEFAULT_PIC="img/SongDefault.jpg";

    //本地资源路径处理
    public static String ASSETS_PATH = System.getProperty("user.dir");
    public static String AVATOR_IMAGES_PATH = "file:" + ASSETS_PATH + "/img/avatorImages/";
    public static String SONGLIST_PIC_PATH = "file:" + ASSETS_PATH + "/img/songListPic/";
    public static String SONG_PIC_PATH = "file:" + ASSETS_PATH + "/img/songPic/";
    public static String SONG_PATH = "file:" + ASSETS_PATH + "/song/";
    public static String SINGER_PIC_PATH = "file:" + ASSETS_PATH + "/img/singerPic/";
    public static String BANNER_PIC_PATH = "file:" + ASSETS_PATH + "/img/swiper/";
}
