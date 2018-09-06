package com.intercom

/*
* Description: Calculates distance in Kms between 2 coordinates using Haversine formula @ https://en.wikipedia.org/wiki/Great-circle_distance
* */

class Distance {

    final int EARTH_RADIUS = 6371                                                                                       //Radius of earth (approx) in Kms

    protected double calculateDistance(Coordinates source, Coordinates destination){
        def lat_diff  = Math.toRadians((destination.lat - source.lat))
        def lng_diff = Math.toRadians((destination.lng - source.lng))
        def s_lat = Math.toRadians(source.lat)
        def d_lat   = Math.toRadians(destination.lat)

        def angle = haversin(lat_diff) + Math.cos(s_lat) * Math.cos(d_lat) * haversin(lng_diff)
        return EARTH_RADIUS * 2 * Math.atan2(Math.sqrt(angle), Math.sqrt(1 - angle))                                    // distance between 2 coordinates in Kms
    }

    double haversin(double value) {
        return Math.pow(Math.sin(value/2), 2)
    }

}
