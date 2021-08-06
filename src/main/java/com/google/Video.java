package com.google;

import java.util.Collections;
import java.util.List;

/** A class used to represent a video. */
class Video implements Comparable<Video>{

  private final String title;
  private final String videoId;
  private final List<String> tags;
  private boolean isFlagged;
  private String flagReason;

  Video(String title, String videoId, List<String> tags) {
    this.title = title;
    this.videoId = videoId;
    this.tags = Collections.unmodifiableList(tags);
    this.isFlagged = false;
  }

  /** Returns the title of the video. */
  String getTitle() {
    return title;
  }

  /** Returns the video id of the video. */
  String getVideoId() {
    return videoId;
  }

  /** Returns a readonly collection of the tags of the video. */
  List<String> getTags() {
    return tags;
  }

  public String getFlagReason() {
    return flagReason;
  }

  public boolean getIsFlagged()
  {
    return isFlagged;
  }

  public void setFlagReason(String flagReason) {
    this.flagReason = flagReason;
  }

  public void setFlagged(boolean isFlagged) {
    this.isFlagged = isFlagged;
  }

  /**
   * Returns a comparison in lexicograpical order to this video.
   */
  @Override
  public int compareTo(Video vidToCompare)
  {
    return this.title.compareTo(vidToCompare.getTitle());
  }
}
