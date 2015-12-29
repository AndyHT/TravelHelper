//
//  Items+CoreDataProperties.swift
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

extension Items {

    @NSManaged var itemId: String?
    @NSManaged var itemDescription: String?
    @NSManaged var itemNum: NSNumber?
    @NSManaged var itemName: String?
    @NSManaged var itemType: String?

}
