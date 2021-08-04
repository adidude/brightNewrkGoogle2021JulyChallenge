package com.google;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class VideoPlayer {
  
  private final VideoLibrary videoLibrary;
  // Stores the currently playing video
  private String currVid;
  private boolean isPaused;

  /**
   * VideoPlayer default Constructor
   */
  public VideoPlayer() {
    this.videoLibrary = new VideoLibrary();
    this.currVid = "";
    this.isPaused = true;
  }

  /**
   * Prints number of videos in the videoLibrary.
   */
  public void numberOfVideos() {
    System.out.printf("%s videos in the library%n", videoLibrary.getVideos().size());
  }

/**
 * Shows all the videos in the library.
 */
  public void showAllVideos() {
    System.out.println("Here's a list of all available videos:");
    // Get videos as a list and sort lexicographically
    List<Video> vidList = videoLibrary.getVideos();
    Collections.sort(vidList);
    // Stream to loop through videos.
    vidList.stream().forEach(vid -> 
    {
      System.out.print(vid.getTitle() + " (" + vid.getVideoId() + ") [");
      List<String> tags = vid.getTags();
      int tagLen = tags.size();
      // If there are tags loop through the list of tags and print.
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

  /**
   * Plays video as well as informs user of video being stopped and stared.
   * @param videoId Is the ID of the video to play
   */
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
      // Occurs when no video is currently playing.
      this.currVid = identifiedVid.getTitle();
      System.out.println("Playing video: " + this.currVid);
    }
  }

  /**
   * Stops currently playing video
   */
  public void stopVideo() {
    if(this.currVid != "")
    {
      System.out.println("Stopping video: " + this.currVid);
      this.currVid = "";
    }
    else
    {
      System.out.println("Cannot stop video: No video is currently playing");
    }
  }

  /**
   * Plays a random video
   */
  public void playRandomVideo() {
    List<Video> videos = this.videoLibrary.getVideos();
    int noOfVids = videos.size();
    Random generator = new Random();
    int randomVid = generator.nextInt(noOfVids);
    String vidID = videos.get(randomVid).getVideoId();
    playVideo(vidID);
  }

  /**
   * Pauses a video
   */
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