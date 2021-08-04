package com.google;

import java.util.Collections;
import java.util.List;

public class VideoPlayer {

  private final VideoLibrary videoLibrary;
  private String currVid;

  public VideoPlayer() {
    this.videoLibrary = new VideoLibrary();
    this.currVid = "";
  }

  public void numberOfVideos() {
    System.out.printf("%s videos in the library%n", videoLibrary.getVideos().size());
  }

  public void showAllVideos() {
    System.out.println("Here's a list of all available videos:");
    List<Video> vidList = videoLibrary.getVideos();
    Collections.sort(vidList);
    vidList.stream().forEach(vid -> 
    {
      System.out.print(vid.getTitle() + " (" + vid.getVideoId() + ") [");
      List<String> tags = vid.getTags();
      int tagLen = tags.size();
      if(tagLen > 0)
      {
        for(int i = 0;i < tagLen; i++)
        {
          if(i != tagLen-1)
          {
            System.out.print(tags.get(i) + " ");
          }
          else
          {
            System.out.print(tags.get(i));
          }
        }
      }
      System.out.println(']');

    });
  }

  public void playVideo(String videoId) {
    Video identifiedVid = this.videoLibrary.getVideo(videoId);
    if(identifiedVid == null)
    {
      System.out.println("Cannot play video: Video does not exist");
    }
    else if(this.currVid != "")
    {
      System.out.println("Stopping video: " + this.currVid);
      this.currVid = identifiedVid.getTitle();
      System.out.println("Playing video: " + this.currVid);
    }
    else
    {
      this.currVid = identifiedVid.getTitle();
      System.out.println("Playing video: " + this.currVid);
    }
  }

  public void stopVideo() {
    System.out.println("stopVideo needs implementation");
  }

  public void playRandomVideo() {
    System.out.println("playRandomVideo needs implementation");
  }

  public void pauseVideo() {
    System.out.println("pauseVideo needs implementation");
  }

  public void continueVideo() {
    System.out.println("continueVideo needs implementation");
  }

  public void showPlaying() {
    System.out.println("showPlaying needs implementation");
  }

  public void createPlaylist(String playlistName) {
    System.out.println("createPlaylist needs implementation");
  }

  public void addVideoToPlaylist(String playlistName, String videoId) {
    System.out.println("addVideoToPlaylist needs implementation");
  }

  public void showAllPlaylists() {
    System.out.println("showAllPlaylists needs implementation");
  }

  public void showPlaylist(String playlistName) {
    System.out.println("showPlaylist needs implementation");
  }

  public void removeFromPlaylist(String playlistName, String videoId) {
    System.out.println("removeFromPlaylist needs implementation");
  }

  public void clearPlaylist(String playlistName) {
    System.out.println("clearPlaylist needs implementation");
  }

  public void deletePlaylist(String playlistName) {
    System.out.println("deletePlaylist needs implementation");
  }

  public void searchVideos(String searchTerm) {
    System.out.println("searchVideos needs implementation");
  }

  public void searchVideosWithTag(String videoTag) {
    System.out.println("searchVideosWithTag needs implementation");
  }

  public void flagVideo(String videoId) {
    System.out.println("flagVideo needs implementation");
  }

  public void flagVideo(String videoId, String reason) {
    System.out.println("flagVideo needs implementation");
  }

  public void allowVideo(String videoId) {
    System.out.println("allowVideo needs implementation");
  }
}