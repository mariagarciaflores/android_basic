package com.example.grissgarcia.lyricssearchapp;

/**
 * Created by Griss Garcia on 16/07/2015.
 */
public class Lyric {
    private String artistName;
    private String songName;
    private String lyric;
    private String lyricUrl;

    /*public Lyric(String artistName, String songName, String lyric, String lyricUrl) {
        this.artistName = artistName;
        this.songName = songName;
        this.lyric = lyric;
        this.lyricUrl = lyricUrl;
    }*/

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public void setLyricUrl(String lyricUrl) {
        this.lyricUrl = lyricUrl;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getSongName() {
        return songName;
    }

    public String getLyric() {
        return lyric;
    }

    public String getLyricUrl() {
        return lyricUrl;
    }
}
