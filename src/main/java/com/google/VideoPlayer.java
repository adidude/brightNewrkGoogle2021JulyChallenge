package com.google;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Vector;

public class VideoPlayer {
  
  private final VideoLibrary videoLibrary;
  // Stores the currently playing video
  private Video currVid;
  private boolean isPaused;
  // A hashmap of vectors with videos in them. For easy access and variable growth.
  private HashMap<String,Vector<Video>> playlists = new HashMap<String,Vector<Video>>();
  private Vector<String> playlistNames = new Vector<String>();

  /**
   * VideoPlayer default Constructor
   */
  public VideoPlayer() {
    this.videoLibrary = new VideoLibrary();
    // isPaused is false because video has not been paused.
    this.isPaused = false;
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
      printVideoDetails(vid);
      // Seperates lines into new lines.
      System.out.println();
    });
  }

  /**
   * Will print the video's details.
   * @param vid The video to be printed.
   */
  public void printVideoDetails(Video vid)
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
      System.out.print(']');
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
    else if(this.currVid != null)
    {
      System.out.println("Stopping video: " + this.currVid.getTitle());
      // Set the identified video as the current video.
      this.currVid = identifiedVid;
      // song is no longer paused and starts playing if paused.
      this.isPaused = false;
      System.out.println("Playing video: " + this.currVid.getTitle());
    }
    else
    {
      // Occurs when no video is currently playing.
      this.currVid = identifiedVid;
      System.out.println("Playing video: " + this.currVid.getTitle());
    }
  }

  /**
   * Stops currently playing video
   */
  public void stopVideo() {
    if(this.currVid != null)
    {
      System.out.println("Stopping video: " + this.currVid.getTitle());
      this.currVid = null;
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
    if(this.currVid == null)
    {
      System.out.println("Cannot pause video: No video is currently playing");
    }
    else if(this.isPaused)
    {
      System.out.println("Video already paused: " + this.currVid.getTitle());
    }
    else
    {
      this.isPaused = true;
      System.out.println("Pausing video: " + this.currVid.getTitle());
    }
  }

  /**
   * Continues a paused video
   */
  public void continueVideo() {
    // If no video is playing or selected.
    if(this.currVid == null)
    {
      System.out.println("Cannot continue video: No video is currently playing");
    }
    else if(this.isPaused)
    {
      // Unpausing video
      System.out.println("Continuing video: " + this.currVid.getTitle());
      this.isPaused = false;
    }
    else
    {
      System.out.println("Cannot continue video: Video is not paused");
    }
  }

  /**
   * Shows the currently playing video.
   */
  public void showPlaying() {
    if(this.currVid == null)
    {
      System.out.println("No video is currently playing");
    }
    else
    {
      System.out.print("Currently playing: ");
      printVideoDetails(this.currVid);
      if(this.isPaused)
      {
        System.out.println(" - PAUSED");
      }
    }
  }

  /**
   * Creates a playlist
   * @param playlistName The name of the playlist to create.
   */
  public void createPlaylist(String playlistName) {
    // Playlist is made lowercase since it is not case sensitive.
    String tempPlaylistName = playlistName.toLowerCase();
    // If the playlist does not exist
    if(playlists.get(tempPlaylistName) == null)
    {
      // Add the playlist to the HashMap
      playlists.put(tempPlaylistName,new Vector<Video>());
      // Storing original playlist name
      playlistNames.add(playlistName);
      System.out.println("Successfully created new playlist: " + playlistName);
    }
    else
    {
      System.out.println("Cannot create playlist: A playlist with the same name already exists");
    }
  }

  /**
   * Adds a video to a playlist.
   * @param playlistName Name of the playlist to add to
   * @param videoId ID of the video to add
   */
  public void addVideoToPlaylist(String playlistName, String videoId) {
    String tempPlaylistName = playlistName.toLowerCase();
    // Get the playlist and video associated with the input.
    Vector<Video> currPlaylist = this.playlists.get(tempPlaylistName);
    Video vidToAdd = videoLibrary.getVideo(videoId);
    if(currPlaylist == null)
    {
      System.out.println("Cannot add video to " + playlistName + ": Playlist does not exist");
    }
    else if(vidToAdd == null)
    {
      System.out.println("Cannot add video to " + playlistName + ": Video does not exist");
    }
    else if(currPlaylist.contains(vidToAdd))
    {
      // If the video is already in the playlist display error
      System.out.println("Cannot add video to " + playlistName + ": Video already added");
    }
    else
    {
      // Test if the video was successfully added to playlist and add it to global playlist
      boolean isAdded = currPlaylist.add(vidToAdd);
      if(isAdded)
      {
        this.playlists.put(tempPlaylistName,currPlaylist);
        System.out.println("Added video to " + playlistName + ": " + vidToAdd.getTitle());
      }
      else
      {
        System.out.println("Cannot add video to " + playlistName + ": Failed to add video to playlist");
      }
    }
  }

  /**
   * Shows all created playlists.
   */
  public void showAllPlaylists() {
    if(playlists.size() == 0)
    {
      System.out.println("No playlists exist yet");
    }
    else
    {
      System.out.println("Showing all playlists:");
      //Sort before printing playlists
      Collections.sort(playlistNames);
      // Stream to print all playlists.
      playlistNames.stream().forEach(System.out::println);
    }
  }

  /**
   * Shows the playlist specified
   * @param playlistName The name of the playlist.
   */
  public void showPlaylist(String playlistName) {
    // Retrieve the playlist
    Vector<Video> vids = playlists.get(playlistName.toLowerCase());
    if(vids == null)
    {
      System.out.println("Cannot show playlist " + playlistName + ": Playlist does not exist");
    }
    else
    {
      System.out.println("Showing playlist: " + playlistName);
      
      if(vids.size() == 0)
      {
        System.out.println("No videos here yet");
      }
      else
      {
        // Print all values in vector.
        vids.stream().forEach(vid -> {
          printVideoDetails(vid);
          System.out.println();
        });
      }
    }
    
  
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