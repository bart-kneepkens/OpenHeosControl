/*
 * Copyright (C) 2017 bartkneepkens
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package SystemCommands;

/**
 *
 * @author bartkneepkens
 */
public class Song {
    private final String title;
    private final String artist;
    private final String album;
    private final String url;
    private final String imageURL;
    private final int duration;

    public Song(String title, String artist, String album, String url, String imageURL, int duration) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.url = url;
        this.imageURL = imageURL;
        this.duration = duration;
    }
    
    public Song(String title){
        this.title = title;
        this.artist = "";
        this.album = "";
        this.url = "";
        this.imageURL = "";
        this.duration = 0;
    }
    
    public String getTitle(){
        return this.title;
    }

    public String getArtist() {
        return this.artist;
    }

    public String getAlbum() {
        return this.album;
    }

    public String getUrl() {
        return this.url;
    }
    
    public String getMediaURL() {
        return this.imageURL;
    }
    
    public int getDuration() {
        return this.duration;
    }
}
