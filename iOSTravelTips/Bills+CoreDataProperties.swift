//
//  Bills+CoreDataProperties.swift
//  TravelTips
//
//  Created by Teng on 12/29/15.
//  Copyright © 2015 huoteng. All rights reserved.
//
//  Choose "Create NSManagedObject Subclass…" from the Core Data editor menu
//  to delete and recreate this implementation file for your updated model.
//

import Foundation
import CoreData

extension Bills {

    @NSManaged var billId: String?
    @NSManaged var billDescription: String?
    @NSManaged var billTime: NSDate?
    @NSManaged var value: NSNumber?
    @NSManaged var billType: String?

}
