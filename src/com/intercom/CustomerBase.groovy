package com.intercom

/*
* Description: Loads customer data file and finds nearest users of the given coordinates
* */

import groovy.json.JsonSlurper


class CustomerBase {

    def record = null
    Distance dist = new Distance()
    CustomerBase(String customer_list_path) throws FileNotFoundException{
        this.record = new JsonSlurper().parse(new File(customer_list_path))
    }

    private List findNearest(Coordinates location, double radius, boolean sortResults = false){
        List nearUsers = []
        record.each{
            if(it.longitude && it.longitude) {
                def userDistance = dist.calculateDistance(location, new Coordinates(it.latitude.toDouble(), it.longitude.toDouble())) // distance of the user
                if(userDistance<=radius)
                    nearUsers.add(it)
            }
        }

        if(!sortResults) return nearUsers
        return nearUsers.sort{a,b-> a.user_id <=> b.user_id} //sorts results if asked
    }
}
