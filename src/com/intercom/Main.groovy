package com.intercom

/*
* Description: Main of project, lists down all the users within the asked radius
* */

class Main {
    static final Coordinates officeCoordinates = new Coordinates(53.339428, -6.257664)
    static void main(def args) {
        if(args.size()==0){                                                                                             // takes search radius as 100 kms if not given
            println('This is not fair, you should have given me a radius to work with!!')
            println('Nevermind, I am taking the default radius of 100 Kms')
            args = [100]
        }
        CustomerBase base = new CustomerBase('./data/customer.json')
        List nearestCustomers = base.findNearest(officeCoordinates, args[0].toDouble(), true)
        if(nearestCustomers.size()>0) {                                                                                 // lists down all the users with asked radius
            println(String.format("%3s %2s %20s", "ID", "|", "Name"))
            println(String.format("%s", "----------------------------"))
            nearestCustomers.each {
                println(String.format("%3s %2s %20s", it.user_id, "|", it.name))
            }
        }
        println('Total customers within '+args[0]+' Kms of radius: '+nearestCustomers.size())
    }
}
