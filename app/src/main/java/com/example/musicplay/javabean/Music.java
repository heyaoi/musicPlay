package com.example.musicplay.javabean;

public class Music {

    /**
     * id：歌曲id
     * name:歌曲名
     * singer:歌手
     * size：歌曲所占空间大小
     * url:歌曲地址
     * duration:歌曲时长
     */

    private long id;
    private String name;
    private String singer;
    private long size;
    private String url;
    private long duration;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}
