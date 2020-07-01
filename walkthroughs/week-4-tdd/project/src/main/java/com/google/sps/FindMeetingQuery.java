// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Iterator;


public final class FindMeetingQuery {

  /***************************
   * @param events
   * @param request
   * @return openTimeRanges
   */
  public Collection<TimeRange> query(Collection<Event> events, MeetingRequest request) {
    Collection<TimeRange> openTimeRanges = new ArrayList<>();

    // When a the duration of the meeting is unrealistic and exceeds a day.
    if(request.getDuration() > TimeRange.END_OF_DAY){
      return openTimeRanges;
    }

    // When a meeting can take place any time on that day.
    if (events.isEmpty()){
      openTimeRanges.add(TimeRange.WHOLE_DAY);
      return openTimeRanges;
    }

    /*****************
     * General Case
     */
    Map<TimeRange, Set> attendeesMap = getAttendeesMap(events);
    TreeSet<TimeRange> bookedTimeRanges = getSortedTimeRanges(events);

    // Declare some helper variables
    int start = TimeRange.START_OF_DAY;
    TimeRange currentTimeRange = null;
    long minimumDuration = 0;
    Iterator<TimeRange> it = bookedTimeRanges.iterator();

    // Iterate through the sorted TreeSet of event.
    while(it.hasNext()){
      currentTimeRange = it.next();
      minimumDuration = start + request.getDuration();

      // If all attendees are available, check if there is enough room for the requested event to take place.
      // An if there is room, add TimeRange from start (i.e end of the previous event) to the beginning of the current event.
      if(allAttendeesAreAvailable(new ArrayList<>(request.getAttendees()), new ArrayList<>(attendeesMap.get(currentTimeRange)))) {
        if (minimumDuration <= currentTimeRange.start()) {
          openTimeRanges.add(TimeRange.fromStartEnd(start, currentTimeRange.start(), false));
        }
        // Update start
        start = currentTimeRange.end();
      }
    }

    // Check if there enough time left of the day for the requested event
    // If there is, add the the remaining time of the day
    minimumDuration = start + request.getDuration();
    if(minimumDuration <= TimeRange.END_OF_DAY){
      openTimeRanges.add(TimeRange.fromStartEnd(start, TimeRange.END_OF_DAY, true));
    }

    return openTimeRanges;
  }

  /**********************************************************************************
   * This method takes to list of attendees
   * If at least one attendees appears in both list, it returns FALSE otherwise TRUE
   * @param requestingAttendees
   * @param bookedAttendees
   * @return True/False
   */

  private boolean allAttendeesAreAvailable(ArrayList<String> requestingAttendees, ArrayList<String> bookedAttendees){
      return requestingAttendees.removeAll(bookedAttendees);
  }

  /***********************************************************
   * This method takes a collection of events and maps each
   * TimeRange to its corresponding attendees.
   *
   * @param events
   * @return attendeesMap
   */

  private Map<TimeRange, Set> getAttendeesMap(Collection<Event> events){
    SortedMap<TimeRange, Set> attendeesMap = new TreeMap<>(TimeRange.ORDER_BY_START);
    events.forEach(event -> {
      attendeesMap.put(event.getWhen(), event.getAttendees());
    });
    return attendeesMap;
  }

  /*******************************************************************
   * This method extracts all the time ranges from the events
   * and puts them in a TreeSet<TimeRange> sorted by START TIME
   *
   * Next it iterates though the TreeSet<TimeRange> again and removes
   * nested events.
   *
   * @param events
   * @return sortedTimeRanges
   */

  private TreeSet<TimeRange> getSortedTimeRanges(Collection<Event> events){
    TreeSet<TimeRange> sortedTimeRanges = new TreeSet<>(TimeRange.ORDER_BY_START);

    // Adds all the Time Ranges in a TreeSet<TimeRange> sorted by START TIME
    events.forEach(event ->{
      sortedTimeRanges.add(event.getWhen());
    });

    // Removes nested events from the TreeSet<TimeRange>
    TimeRange predecessor = sortedTimeRanges.first();
    sortedTimeRanges.forEach(timeRange -> {
      if(timeRange != predecessor) {
        if(predecessor.contains(timeRange)) sortedTimeRanges.remove(timeRange);
      }
    });

    return sortedTimeRanges;
  }
}